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

    /**
     * 宽度
     */
    private int width;

    /**
     * 高度
     */
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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
