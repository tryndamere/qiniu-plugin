package com.mll.provider.plugin.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageImpl;
import com.fc.platform.commons.page.PageRequest;
import com.fc.platform.commons.page.Pageable;
import com.fc.platform.commons.util.BeanMapper;
import com.fc.platform.commons.util.GeneralUUID;
import com.mll.provider.plugin.config.AuthWrap;
import com.mll.provider.plugin.config.QiniuProperties;
import com.mll.provider.plugin.entity.FileInfo;
import com.mll.remote.plugin.service.QiniuService;
import com.mll.remote.plugin.vo.FileInfoRequest;
import com.mll.remote.plugin.vo.FileInfoResponse;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by rocky on 2015/12/10.
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuServiceImpl.class);

    @Autowired
    QiniuProperties qiniuProperties;

    @Autowired
    AuthWrap authWrap;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public FileInfoResponse uploadFile(FileInfoRequest fileInfoRequest) {
        validator(fileInfoRequest);
        UploadManager uploadManager = new UploadManager();
        try {
            String suffix = fileInfoRequest.getFileName().split("\\.")[1];
            Response response = uploadManager.put(fileInfoRequest.getData(),
                    GeneralUUID.UUID() + "." + suffix, authWrap.getToken());
            FileInfo fileInfo = response.jsonToObject(FileInfo.class);
            fileInfo.setFileName(fileInfoRequest.getFileName());
            fileInfo.setFileSize(fileInfoRequest.getData().length / 1024);
            fileInfo.setUrl(qiniuProperties.getUrl() + fileInfo.getKey());
            fileInfo.setSysCode(fileInfoRequest.getSysCode());
            fileInfo.setWidth(fileInfoRequest.getWidth());
            fileInfo.setHeight(fileInfoRequest.getHeight());
            fileInfo.setCreateTime(new Date());
            mongoTemplate.save(fileInfo);
            return new FileInfoResponse(fileInfo.getUrl(), fileInfo.getFileName());
        } catch (QiniuException e) {
            LOGGER.error("this is error msg : {} \n {}", e.getMessage(), e);
        }
        return null;
    }

    private void validator(FileInfoRequest fileInfoRequest) {
        String msg = null;
        if (fileInfoRequest == null) {
            msg = "fileInfoRequest is null!";
        }
        if (fileInfoRequest.getData() == null) {
            msg = "file is null!";
        }
        if (StringUtils.isEmpty(fileInfoRequest.getSysCode())) {
            msg = "sysCode is null!";
        }
        if (StringUtils.isNotEmpty(msg)) {
            throw new RuntimeException(msg, new NullPointerException(msg));
        }
    }

    @Override
    public Page<FileInfoResponse> getFileInfoByPage(String sysCode, Pageable pageable, Long count) {
        PageRequest pageRequest = (PageRequest) pageable;

        Criteria sysCodeCriteria = Criteria.where("sysCode").is(sysCode);
        if (count == null || count == 0L) {
            Query countQuery = new Query(sysCodeCriteria);
            count = mongoTemplate.count(countQuery, FileInfo.class);
        }
        pageRequest.setTotalElements(count);
        Query pageQuery = new Query(sysCodeCriteria).with(new Sort(Sort.Direction.DESC, pageRequest.getSort()))
                .limit(pageRequest.getPageSize()).skip(pageRequest.getOffset());
        List<FileInfo> fileInfos = mongoTemplate.find(pageQuery, FileInfo.class);
        List<FileInfoResponse> content = BeanMapper.getMapperFacade().mapAsList(fileInfos, FileInfoResponse.class);
        return new PageImpl<FileInfoResponse>(content, pageRequest, count);
    }

    @Override
    public Integer deleteFileInfo(String url, String sysCode) {
        Query query = new Query(Criteria.where("url").is(url).and("sysCode").is(sysCode));
        return mongoTemplate.remove(query, FileInfo.class).getN();
    }

}
