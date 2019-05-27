package com.sy.chainproject.core.bluetooth;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;
import android.widget.Toast;
import com.sy.chainproject.activity.BluetoothActivity;
import com.sy.chainproject.interfance.BluetoothResult;
import com.sy.chainproject.utils.LogUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class BluetoothUtils {
    private Context context = null;
    private BluetoothAdapter bluetoothAdapter;
    private ArrayList<BluetoothDevice> devices = null; // 用于存放未配对蓝牙设备
    private BluetoothResult<BluetoothDevice> result;
    private boolean isConnection = false;
    private OutputStream outputStream = null;
    private final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private ProgressDialog progressDialog = null;


    public BluetoothUtils(Context context) {
        this.context = context;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        devices = new ArrayList<>();
        initIntentFilter();
    }

    public BluetoothUtils(Context context, BluetoothResult<BluetoothDevice> result) {
        this.context = context;
        this.result = result;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        devices = new ArrayList<>();
        initIntentFilter();
    }


    /**
     * @param sendData 发送蓝牙数据
     */
    public void send(String sendData) {
        if (this.isConnection) {
            try {
                if (TextUtils.isEmpty(sendData)) return;
                initPrinter();
                byte[] data = sendData.getBytes("GB18030");
                outputStream.write(data, 0, data.length);
                feedAndCut();
            } catch (IOException e) {
                LogUtils.e("tag", e.getMessage());
                searchDevices();
            }
        } else {
            context.startActivity(new Intent(context, BluetoothActivity.class));
        }
    }

    public void send(byte[] sendData, int num) {
        if (this.isConnection) {
            try {
                if (sendData == null) return;
                initPrinter();
                for (int i = 0; i < num; i++) {
                    outputStream.write(sendData, 0, sendData.length);
                    feedAndCut();
                }
            } catch (IOException e) {
                LogUtils.e("tag", e.getMessage());
                searchDevices();
            }
        } else {
            context.startActivity(new Intent(context, BluetoothActivity.class));
        }
    }
/*
    public void reset() {
        try {
            outputStream.write(0x1B);
            outputStream.flush();
            outputStream.write(new byte[]{0x1d, 0x21, 0x11});
            outputStream.write(new byte[]{0x1b, 0x45, 0x01});
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    /**
     * 对图片进行压缩（去除透明度）
     */
    public byte[] compressPic(Bitmap bitmap) {
        // 获取这个图片的宽和高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 指定调整后的宽度和高度
        int newWidth = 240;
        int newHeight = 240;
        Bitmap targetBmp = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas targetCanvas = new Canvas(targetBmp);
        targetCanvas.drawColor(0xffffffff);
        targetCanvas.drawBitmap(bitmap, new Rect(0, 0, width, height), new Rect(0, 0, newWidth, newHeight), null);
        return draw2PxPoint(targetBmp);
    }

    /**
     * 假设一个240*240的图片，分辨率设为24, 共分10行打印
     * 每一行,是一个 240*24 的点阵, 每一列有24个点,存储在3个byte里面。
     * 每个byte存储8个像素点信息。因为只有黑白两色，所以对应为1的位是黑色，对应为0的位是白色
     * 把一张Bitmap图片转化为打印机可以打印的字节流
     **/

    private byte[] draw2PxPoint(Bitmap bmp) {
        //用来存储转换后的 bitmap 数据。为什么要再加1000，这是为了应对当图片高度无法
        //整除24时的情况。比如bitmap 分辨率为 240 * 250，占用 7500 byte，
        //但是实际上要存储11行数据，每一行需要 24 * 240 / 8 =720byte 的空间。再加上一些指令存储的开销，
        //所以多申请 1000byte 的空间是稳妥的，不然运行时会抛出数组访问越界的异常。
        int size = bmp.getWidth() * bmp.getHeight() / 8 + 1000;
        byte[] data = new byte[size];
        int k = 0;
        //设置行距为0的指令
        data[k++] = 0x1B;
        data[k++] = 0x33;
        data[k++] = 0x00;
        // 逐行打印
        for (int j = 0; j < bmp.getHeight() / 24f; j++) {
            //打印图片的指令
            data[k++] = 0x1B;
            data[k++] = 0x2A;
            data[k++] = 33;
            data[k++] = (byte) (bmp.getWidth() % 256); //nL
            data[k++] = (byte) (bmp.getWidth() / 256); //nH
            //对于每一行，逐列打印
            for (int i = 0; i < bmp.getWidth(); i++) {
                //每一列24个像素点，分为3个字节存储
                for (int m = 0; m < 3; m++) {
                    //每个字节表示8个像素点，0表示白色，1表示黑色
                    for (int n = 0; n < 8; n++) {
                        byte b = px2Byte(i, j * 24 + m * 8 + n, bmp);
                        data[k] += data[k] + b;
                    }
                    k++;
                }
            }
            data[k++] = 10;//换行
        }
        return data;
    }

    /* *
    * 灰度图片黑白化，黑色是1，白色是0
    *
    * @param x   横坐标
    * @param y   纵坐标
    * @param bit 位图
    */
    private byte px2Byte(int x, int y, Bitmap bit) {
        if (x < bit.getWidth() && y < bit.getHeight()) {
            byte b;
            int pixel = bit.getPixel(x, y);
            int red = (pixel & 0x00ff0000) >> 16; // 取高两位
            int green = (pixel & 0x0000ff00) >> 8; // 取中两位
            int blue = pixel & 0x000000ff; // 取低两位
            int gray = RGB2Gray(red, green, blue);
            if (gray < 128) {
                b = 1;
            } else {
                b = 0;
            }
            return b;
        }
        return 0;
    }

    /**
     * 图片灰度的转化
     */
    private int RGB2Gray(int r, int g, int b) {
        return (int) (0.29900 * r + 0.58700 * g + 0.11400 * b);
    }

    /**
     * 进纸并全部切割
     */
    private void feedAndCut() throws IOException {
        outputStream.write(0x1D);
        outputStream.write(86);
        outputStream.write(65);
        // writer.write(0);
        // 切纸前走纸多少
//		 outputStream.write(110);
        outputStream.flush();
    }

    private void initPrinter() throws IOException {
        outputStream.write(new byte[]{0x1B, 0x40});
        outputStream.flush();
        outputStream.write(new byte[]{0x1d, 0x21, 0x11});
        outputStream.write(new byte[]{0x1b, 0x45, 0x01});
    }

    /**
     * 连接蓝牙设备
     */
    public void connect(BluetoothDevice device) {
        if (!this.isConnection) {
            try {
                BluetoothSocket bluetoothSocket = device.createRfcommSocketToServiceRecord(uuid);
                bluetoothSocket.connect();
                outputStream = bluetoothSocket.getOutputStream();
            } catch (Exception e) {
                e.printStackTrace();
                isConnection = false;
                Toast.makeText(this.context, device.getName() + "连接失败！", Toast.LENGTH_SHORT).show();
                try {
                    if (outputStream != null) outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            Toast.makeText(this.context, device.getName() + "连接成功！", Toast.LENGTH_SHORT).show();
        } else {
            LogUtils.e("tag", "蓝牙已连接");
        }
    }

    /**
     * 蓝牙监听广播
     */
    private void initIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        context.registerReceiver(receiver, intentFilter);
    }

    public void unregisterReceiver() {
        context.unregisterReceiver(receiver);
    }

    public void openBluetooth() {
        if (!bluetoothAdapter.isEnabled()) bluetoothAdapter.enable();
    }

    public void closeBluetooth() {
        if (bluetoothAdapter.isEnabled()) bluetoothAdapter.disable();
    }

    /**
     * 获取蓝牙状态
     */
    public boolean bState() {
        return bluetoothAdapter.isEnabled();
    }

    /**
     * 搜索蓝牙设备
     */
    public void searchDevices() {
        devices.clear();
        // 寻找蓝牙设备，android会将查找到的设备以广播形式发出去
        if (bluetoothAdapter.isDiscovering()) bluetoothAdapter.cancelDiscovery();
        bluetoothAdapter.startDiscovery();

    }

    /**
     * 关闭进度条
     */
    private void closeProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            //bluetoothAdapter.cancelDiscovery();
            progressDialog.dismiss();
        }
    }

    /**
     * 蓝牙广播接收器
     */
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtils.e("tag", "action" + action);
            if (TextUtils.isEmpty(action)) return;
            switch (action) {
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    if (blueState == BluetoothAdapter.STATE_ON) {
                        searchDevices();
                    } else if (blueState == BluetoothAdapter.STATE_OFF) {
                        devices.clear();
                        if (result != null) result.fail(devices);
                    }
                    break;
                case BluetoothDevice.ACTION_FOUND:
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    devices.add(device);
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                    progressDialog = ProgressDialog.show(context, "请稍等...", "搜索蓝牙设备中...", true);
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    if (result != null) result.success(devices);
                    closeProgress();
                    break;
                case BluetoothDevice.ACTION_ACL_CONNECTED:
                    isConnection = true;
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    isConnection = false;
                    break;
            }
        }
    };

}