package com.mll.remote.plugin.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
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
     * 通过系统code查询，系统中所存储的图片 分页
     * @param sysCode  系统标识
     * @param pageable 分页内容
     * @param count    总个数 当总个数为0L时,后台将会去查询一下.如果有count值时,后台将不再查询.优化级别参数
     * @return
     */
    Page<FileInfoResponse> getFileInfoByPage(String sysCode , Pageable pageable , Long count);

    /**
     * 删除文件内容
     * @param url
     * @param sysCode
     */
    Integer deleteFileInfo(String url , String sysCode);

}
