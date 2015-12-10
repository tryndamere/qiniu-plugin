package util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;
import java.util.HashMap;

/**
 * Created by rocky on 2015/12/9.
 */
public class Test {

    public static void main(String[] args) throws QiniuException {
        UploadManager uploadManager = new UploadManager();
        Auth auth = Auth.create("eA0TNulRwPgZ9hOcVBHBwNzvvfGg7FBK7H7vbUXJ" , "XkO6L3SxhvhvF01pCMPcuQiwabWqXTIPZzM2GsxT");
        String token = auth.uploadToken("mll-mobile");
        File file = new File("D:\\hyxt-workspace\\activity\\activity-cash\\src\\main\\webapp\\resources\\images\\errorType.png");
        Response put = uploadManager.put(file , "errorType.png", token);
        HashMap hashMap = put.jsonToObject(HashMap.class);
        String url = "http://7xotwj.com1.z0.glb.clouddn.com/" + hashMap.get("key") + "?imageView2/1/w/100/h/200";
        System.out.println(url);
    }
}
