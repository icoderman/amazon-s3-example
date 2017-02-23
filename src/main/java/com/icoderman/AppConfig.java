package com.icoderman;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
public class AppConfig {

    // Amazon beans
    @Bean
    public BasicAWSCredentials basicAWSCredentials(Environment environment) {
        String awsAccessKeyId = environment.getProperty("amazon.aws_access_key_id");
        String awsSecretAccessKey = environment.getProperty("amazon.aws_secret_access_key");
        return new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
    }

    @Bean
    public AmazonS3 amazonS3(Environment environment, BasicAWSCredentials basicAWSCredentials) {
        String awsRegion = environment.getProperty("amazon.aws_region");
        AmazonS3 amazonS3 = new AmazonS3Client(basicAWSCredentials);
        amazonS3.setRegion(Region.getRegion(Regions.fromName(awsRegion)));
        return amazonS3;
    }
}
