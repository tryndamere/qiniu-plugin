package com.mll.test;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageRequest;
import com.mll.provider.plugin.Application;
import com.mll.remote.plugin.service.QiniuService;
import com.mll.remote.plugin.vo.FileInfoRequest;
import com.mll.remote.plugin.vo.FileInfoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by rocky on 15/12/14.
 */
@SpringApplicationConfiguration(value = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest {

    @Autowired
    QiniuService qiniuService;

    @Test
    public void testUpload() {
        FileInfoRequest fileInfoRequest = new FileInfoRequest();
        fileInfoRequest.setFileName("aaa.png");
        fileInfoRequest.setSysCode("bm");
        fileInfoRequest.setData(getFile());
        FileInfoResponse fileInfoResponse = qiniuService.uploadFile(fileInfoRequest);
        System.out.println(fileInfoResponse.getUrl());
    }

    @Test
    public void testGetFileInfoByPage() {
        PageRequest pageRequest = new PageRequest(0, 10, "createTime desc");
        Page<FileInfoResponse> bm = qiniuService.getFileInfoByPage("bm", pageRequest, null);
        System.out.println(bm.getTotalElements());
    }

    @Test
    public void testDelete() {
        Integer bm = qiniuService.deleteFileInfo("http://7xotwj.com1.z0.glb.clouddn.com/49b2984f-3db8-44d3-b298-4f3db854d331.png", "bm");
        System.out.println(bm);
    }

    private byte[] getFile() {
        File file = new File("/Users/rocky/Downloads/u125.png");
        try {
            return FileCopyUtils.copyToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
