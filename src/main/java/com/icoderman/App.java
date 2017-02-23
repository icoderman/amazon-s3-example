package com.icoderman;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AmazonS3Service amazonS3Service = context.getBean(AmazonS3Service.class);

        //amazonS3Service.uploadFile(inputStream, contentLength, contentType);
    }
}
