package com.spring.cloud.authentic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
