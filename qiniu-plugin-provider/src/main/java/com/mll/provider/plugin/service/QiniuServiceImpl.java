package com.mll.provider.plugin.service;

import com.mll.provider.plugin.config.AuthWrap;
import com.mll.provider.plugin.config.QiniuProperties;
import com.mll.provider.plugin.entity.FileInfo;
import com.mll.provider.plugin.repository.FileInfoRepository;
import com.mll.remote.plugin.service.QiniuService;
import com.mll.remote.plugin.vo.FileInfoRequest;
import com.mll.remote.plugin.vo.FileInfoResponse;
import com.mongodb.WriteResult;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by rocky on 2015/12/10.
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    @Autowired
    QiniuProperties qiniuProperties;

    @Autowired
    AuthWrap authWrap;

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    private static final String QINIU_RETURN_BODY = "{\"originFileName\": $(fname),\"fileSize\": $(fsize),\"width\": $(imageInfo.width),\"height\": $(imageInfo.height),\"hash\": $(etag),\"key\":$(key)}";

    @Override
    public FileInfoResponse uploadFile(FileInfoRequest fileInfoRequest) {
        UploadManager uploadManager = new UploadManager();
        try {
            String suffix = fileInfoRequest.getFileName().split("\\.")[1];
            Response response = uploadManager.put(fileInfoRequest.getData() ,
                    UUID.randomUUID().toString() + "." + suffix, authWrap.getToken() ,
                    new StringMap().put("returnBody" , QINIU_RETURN_BODY) , null , false);
            FileInfo fileInfo = response.jsonToObject(FileInfo.class);
            fileInfo.setUrl(qiniuProperties.getUrl() + fileInfo.getKey());
            fileInfo.setSysCode(fileInfoRequest.getSysCode());
            fileInfoRepository.save(fileInfo);
            return new FileInfoResponse(fileInfo.getUrl() , fileInfoRequest.getFileName());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FileInfoResponse> getAllFileInfo(String sysCode) {
        return null;
    }

    @Override
    public Integer deleteFileInfo(String url, String sysCode) {
        Query query = new Query(Criteria.where("url").is(url).and("sysCode").is(sysCode));
        return mongoTemplate.remove(query, FileInfo.class).getN();
    }

}
