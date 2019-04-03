package com.sy.chainproject.download;

import android.support.annotation.NonNull;
import com.sy.chainproject.interfance.ProgressListener;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.*;

import java.io.IOException;

/**
 * @ data  2019/3/28 11:16
 * @ author  zxcg
 */
public class DownResponseBody extends ResponseBody {
    private ResponseBody responseBody;
    private ProgressListener progressListener;
    private BufferedSource bufferedSource;

    DownResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(@NonNull Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                progressListener.onProgress(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        };
    }
}
