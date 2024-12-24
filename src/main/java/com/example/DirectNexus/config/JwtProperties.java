package com.example.DirectNexus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String customClaim;

    public String getCustomClaim() {
        return customClaim;
    }

    public void setCustomClaim(String customClaim) {
        this.customClaim = customClaim;
    }
}
