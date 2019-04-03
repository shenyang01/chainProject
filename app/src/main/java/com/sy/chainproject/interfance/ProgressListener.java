package com.sy.chainproject.interfance;

/**
 * @ data  2019/3/28 11:19
 * @ author  zxcg
 */
public interface ProgressListener {
    /**
     * @param progress 已经下载或上传字节数
     * @param total    总字节数
     * @param done     是否完成
     */
    void onProgress(long progress, long total, boolean done);
}
