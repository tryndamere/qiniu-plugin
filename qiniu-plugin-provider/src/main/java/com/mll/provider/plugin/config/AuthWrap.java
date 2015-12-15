package com.mll.provider.plugin.config;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

/**
 * Created by rocky on 2015/12/10.
 */
public class AuthWrap implements DisposableBean , InitializingBean {

    private QiniuProperties qiniuProperties;

    private Auth auth;

    private String token;

    public AuthWrap(QiniuProperties qiniuProperties) {
        this.qiniuProperties = qiniuProperties;
    }

    @Override
    public void destroy() throws Exception {
        this.qiniuProperties = null;
        this.auth = null;
        this.token = null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.auth  = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        this.token = this.auth.uploadToken(qiniuProperties.getBucket());
    }

    public Auth getAuth() {
        return this.auth;
    }

    public String getToken() {
        return this.token;
    }

    public QiniuProperties getQiniuProperties() {
        return this.qiniuProperties;
    }
}
