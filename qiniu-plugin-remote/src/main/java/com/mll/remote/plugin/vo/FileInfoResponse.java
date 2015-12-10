package com.mll.remote.plugin.vo;

import java.io.Serializable;

/**
 * Created by rocky on 2015/12/10.
 */
public class FileInfoResponse implements Serializable{

    private String url;

    private String fileName;

    public FileInfoResponse(String url , String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
