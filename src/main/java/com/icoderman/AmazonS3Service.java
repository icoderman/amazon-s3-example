package com.icoderman;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
public class AmazonS3Service {

    private static final String FILE_FORMAT_TEMPLATE = "%s.%s";

    @Value("${amazon.aws_s3_bucket_name}")
    private String bucketName;

    @Value("${amazon.aws_s3_object_key}")
    private String defaultObjectKey;

    @Autowired
    private AmazonS3 amazonS3;

    private String formatObjectKey(String objectKey, ContentType contentType) {
        return String.format(FILE_FORMAT_TEMPLATE, objectKey, contentType.getFormat());
    }

    private String formatObjectPathKey(String objectKey) {
        return defaultObjectKey + objectKey;
    }

    public String uploadFile(InputStream inputStream, Long contentLength, ContentType contentType) {

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        metadata.setContentType(contentType.getMimeType());

        String objectKey = formatObjectKey(UUID.randomUUID().toString(), contentType);
        String objectPathKey = formatObjectPathKey(objectKey);

        PutObjectResult result = amazonS3.putObject(new PutObjectRequest(bucketName, objectPathKey, inputStream, metadata));
        return objectKey;
    }

    public Map deleteFile(String objectKey, ContentType contentType) {

        String objectPathKey = formatObjectPathKey(objectKey);
        amazonS3.deleteObject(bucketName, objectPathKey);
        return Collections.emptyMap();
    }

    public InputStream downloadFile(String objectKey, ContentType contentType) {

        String objectPath = formatObjectPathKey(objectKey);
        S3Object s3Object = amazonS3.getObject(bucketName, objectPath);
        return s3Object.getObjectContent();
    }


}
