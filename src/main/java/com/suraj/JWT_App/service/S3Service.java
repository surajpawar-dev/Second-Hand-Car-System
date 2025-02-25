package com.suraj.JWT_App.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;

@Service
public class S3Service {
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    private final S3Client s3Client;

    @Value("${aws.s3.singlePhotoBucketName}")
    private String singlePhotoBucketName;

    @Value("${aws.s3.multiplePhotoBucketName}")
    private String multiplePhotoBucketName;

    /**
     * Uploads a file to an AWS S3 bucket with a unique filename.
     *
     * @param file the MultipartFile containing the data to be uploaded
     * @return the public URL of the uploaded file
     * @throws RuntimeException if an error occurs during the upload process
     */
    public String uploadFile(MultipartFile file) {
        // Generate a unique filename using a timestamp and original name
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        try {
            // Upload file to S3 bucket
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(singlePhotoBucketName)
                            .key(fileName)
                            .build(),
                    RequestBody.fromBytes(file.getBytes()));

            // Return the public URL of the uploaded file
            return s3Client.utilities()
                    .getUrl(builder -> builder.bucket(singlePhotoBucketName).key(fileName))
                    .toString();

        } catch (IOException e) {
            throw new RuntimeException("Error uploading file", e);
        }
    }

    /**
     * Uploads multiple files to an AWS S3 bucket.
     *
     * @param files a list of MultipartFile objects to be uploaded
     * @return a list of public URLs for the uploaded files
     * @throws RuntimeException if an error occurs during the upload process
     */
    public List<String> uploadMultipleFiles(List<MultipartFile> files) {
        return files.stream()
                .map(file -> {
                    // Generate a unique filename for each file
                    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    try {
                        // Upload file to the multiple photo S3 bucket
                        s3Client.putObject(PutObjectRequest.builder()
                                        .bucket(multiplePhotoBucketName)
                                        .key(fileName)
                                        .contentType(file.getContentType())
                                        .contentDisposition("inline")
                                        .acl(ObjectCannedACL.PUBLIC_READ)
                                        .build(),
                                RequestBody.fromBytes(file.getBytes()));

                        return s3Client.utilities()
                                .getUrl(builder -> builder.bucket(multiplePhotoBucketName).key(fileName))
                                .toString();

                    } catch (IOException e) {
                        throw new RuntimeException("Error uploading file in bucket", e);
                    }
                })
                .toList();
    }
}
