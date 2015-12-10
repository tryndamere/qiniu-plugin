package com.mll.remote.plugin.vo;

import java.io.Serializable;

/**
 * Created by rocky on 2015/12/10.
 */
public class FileInfoRequest implements Serializable {

    /**
     * 文件
     */
    private byte[] data;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 系统自定义code
     */
    private String sysCode;

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
