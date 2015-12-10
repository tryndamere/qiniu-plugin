package com.mll.provider.plugin.service;

import com.mll.provider.plugin.config.QiniuProperties;
import com.mll.remote.plugin.service.QiniuService;
import com.mll.remote.plugin.vo.FileInfoRequest;
import com.mll.remote.plugin.vo.FileInfoResponse;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rocky on 2015/12/10.
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    @Autowired
    private QiniuProperties qiniuProperties;

    @Autowired
    private Auth auth;

    @Override
    public FileInfoResponse uploadFile(FileInfoRequest fileInfoRequest) {
        UploadManager uploadManager = new UploadManager();
//        uploadManager.put(fileInfoRequest.getData() , fileInfoRequest.getFileName() , )
        return null;
    }

    @Override
    public List<FileInfoResponse> getAllFileInfo(String sysCode) {
        return null;
    }

    @Override
    public void deleteFileInfo(String url, String sysCode) {

    }

}
