package com.mll.provider.plugin.service;

import com.mll.remote.plugin.service.QiniuService;
import com.mll.remote.plugin.vo.FileInfoRequest;
import com.mll.remote.plugin.vo.FileInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rocky on 2015/12/10.
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    @Override
    public FileInfoResponse uploadFile(FileInfoRequest fileInfoRequest) {

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
