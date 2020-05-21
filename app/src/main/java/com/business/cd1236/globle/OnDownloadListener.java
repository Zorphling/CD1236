package com.business.cd1236.globle;

import java.io.File;

public interface OnDownloadListener {
    /**
     * 下载成功
     * @param file
     */
    void onDownloadSuccess(File file);

    /**
     * @param progress 下载进度
     */
    void onDownloading(int progress);

    /**
     * 下载失败
     */
     void onDownloadFailed();
}
