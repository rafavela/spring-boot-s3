package com.dragonfruit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.dragonfruit.components.CredentialSettings;
import com.dragonfruit.components.S3Settings;

@Configuration
@ConfigurationProperties(prefix="credentials")
public class AmazonConfig {
	
	@Autowired
	private CredentialSettings credentialSettings;

	@Autowired
	private S3Settings s3Settings;	
	
	@Bean
	public AmazonS3 s3() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(credentialSettings.getAccessKey()
				, credentialSettings.getSecretKey());
		AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
		
		return AmazonS3ClientBuilder.standard()
				.withRegion(s3Settings.getRegion())
				.withCredentials(credentialsProvider)
				.build();
	}
}
