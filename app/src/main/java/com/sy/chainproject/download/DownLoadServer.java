package com.sy.chainproject.download;

import android.app.*;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.RemoteViews;
import com.sy.chainproject.R;
import com.sy.chainproject.activity.LoginActivity;
import com.sy.chainproject.constant.Constants;
import com.sy.chainproject.handler.BaseHandler;
import com.sy.chainproject.interfance.ProgressListener;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @ data  2019/3/28 9:45
 * @ author  zxcg
 * 下载 并安装apk
 */
public class DownLoadServer extends IntentService implements ProgressListener, BaseHandler.OnReceiveMessageListener {
    private Notification notif;
    private NotificationManager manager;

    public DownLoadServer() {
        super("name");
    }

    private BaseHandler.HandlerHolder handler = new BaseHandler.HandlerHolder(this);

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        notificationInit();
        new HttpDown<ResponseBody>().doHttpDeal(new DownHttps(this).API().downLoad(Constants.DOWNLOAD)).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                InputStream stream = responseBody.byteStream();
                writeFile(stream);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     * 将输入流写入文件
     */
    private void writeFile(InputStream inputString) {
        String filesDir = getFilesDir().getAbsolutePath() + "/";
        File file = new File(filesDir, "chainproject.apk");
        try {
            if (file.exists()) file.delete();
            file.createNewFile();
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputString.close();
            fos.close();
            install(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param file 安装apk
     */
    private void install(File file) {
        if (!file.getName().endsWith(".apk")) {
            return;
        }
        manager.cancel(1);
        Intent install = new Intent();
        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean hasInstallPermission = getApplicationContext().getPackageManager().canRequestPackageInstalls();
            if (!hasInstallPermission) {
                return;
            } else {
                Uri apkUri = FileProvider.getUriForFile(getApplicationContext(), "com.sy.chainproject.fileprovider", file);
                // 添加这一句表示对目标应用临时授权该Uri所代表的文件
                install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            }
        } else if (Build.VERSION.SDK_INT >= 24) {
            Uri apkUri = FileProvider.getUriForFile(getApplicationContext(), "com.sy.chainproject.fileprovider", file);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        startActivity(install);
    }

    /**
     * 通知栏内显示下载进度条
     */
    private void notificationInit() {
        manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  //8.0
            Log.e("tag", "notificationInit");
            String id = "01";
            String name = "01Test";
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(mChannel);
            notif = new Notification.Builder(this, id).setCustomContentView(new RemoteViews(getPackageName(), R.layout.progressbar_content)).setChannelId(id).setSmallIcon(R.mipmap.down).build();
        } else {
            notif = new Notification();
            Intent intent = new Intent(this, LoginActivity.class);// 点击进度条，进入程序
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
            notif.icon = R.mipmap.down;
            notif.contentView = new RemoteViews(getPackageName(), R.layout.progressbar_content);// 通知栏中进度布局
            notif.contentIntent = pIntent;
        }
    }

    public void onProgress(long progress, long total, boolean done) {
        int percentage = (int) (progress * 100 / total);
        if (percentage % 2 == 0) {
            handler.sendEmptyMessage(percentage);
        }
    }

    @Override
    public void handlerMessage(Message msg) {
        notif.contentView.setTextViewText(R.id.pro_tv, msg.what + "%");
        notif.contentView.setProgressBar(R.id.pro_progressBar, 100, msg.what, false);
        manager.notify(1, notif);
    }
}
