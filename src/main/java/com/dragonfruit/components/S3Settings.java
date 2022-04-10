package com.dragonfruit.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="s3")
@Data
public class S3Settings {
	  private String region;
	  private String bucketName;
}
