package com.spring.cloud.authentic.config;

import com.spring.cloud.framework.utils.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by yingying on 18-5-21.
 */
@Configuration
@ConfigurationProperties(prefix = "jwt.auth")
public class AuthServerConfig {
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 客户端密码
     */
    private String clientSecret;
    /**
     * 范围
     */
    private String scope;
    /**
     * RSA 秘钥
     */
    private String privateKey;
    /**
     * 公钥
     */
    private String publicKey;

    public void setClientId(String clientId) {
        this.clientId = base64Encode(clientId);
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = base64Encode(clientSecret);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    private String base64Encode(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String encode_ = "";
        byte[] decoded = null;
        try {
            decoded = Base64.getDecoder().decode(str);
            encode_ = new String(decoded, CommonConstant.UTF8);
        } catch (IllegalArgumentException var7) {
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encode_;
    }
}
