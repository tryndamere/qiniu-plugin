package com.mll.remote.plugin.service;

import com.mll.remote.plugin.vo.FileInfoRequest;
import com.mll.remote.plugin.vo.FileInfoResponse;

import java.util.List;

/**
 * Created by rocky on 2015/12/10.
 */
public interface QiniuService {

    /**
     * 上传图片
     * @param fileInfoRequest
     * @return
     */
    FileInfoResponse uploadFile(FileInfoRequest fileInfoRequest);

    /**
     * 通过系统code查询，系统中所存储的图片
     * @param sysCode 系统编码
     * @return
     */
    List<FileInfoResponse> getAllFileInfo(String sysCode);

    /**
     * 删除文件内容
     * @param url
     * @param sysCode
     */
    Integer deleteFileInfo(String url , String sysCode);

}
