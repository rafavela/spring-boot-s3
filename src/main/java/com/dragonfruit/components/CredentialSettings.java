package com.dragonfruit.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="credentials")
@Data
public class CredentialSettings {
	  private String accessKey;
	  private String secretKey;	
}
