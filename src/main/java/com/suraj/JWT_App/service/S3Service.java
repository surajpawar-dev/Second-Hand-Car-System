package com.suraj.JWT_App.service;


import com.suraj.JWT_App.Exception.ResourceNotFound;
import com.suraj.JWT_App.entity.car_details.Car;
import com.suraj.JWT_App.entity.evaluation.CarDetailedEvaluation;
import com.suraj.JWT_App.entity.evaluation.CarEvaluationPhotos;
import com.suraj.JWT_App.entity.evaluation.ImageEntity;
import com.suraj.JWT_App.repository.CarDetailsRepos.CarRepository;
import com.suraj.JWT_App.repository.CarDetailsRepos.ImageEntityRepository;
import com.suraj.JWT_App.repository.evaluation.CarDetailedEvaluationRepository;
import com.suraj.JWT_App.repository.evaluation.CarEvaluationPhotosRepository;
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
    private final S3Client s3Client;
    private final CarEvaluationPhotosRepository photosRepository;
    private final CarDetailedEvaluationRepository carDetailRepository;
    private final CarRepository carRepository;
    private final ImageEntityRepository imageRepository;

    @Value("${aws.s3.singlePhotoBucketName}")
    private String singlePhotoBucketName;

    @Value("${aws.s3.multiplePhotoBucketName}")
    private String multiplePhotoBucketName;

    public S3Service(S3Client s3Client, CarEvaluationPhotosRepository photosRepository, CarDetailedEvaluationRepository carDetailRepository, CarRepository carRepository, ImageEntityRepository imageRepository) {
        this.s3Client = s3Client;
        this.photosRepository = photosRepository;
        this.carDetailRepository = carDetailRepository;
        this.carRepository = carRepository;
        this.imageRepository = imageRepository;
    }

    /**
     * Uploads a file to an AWS S3 bucket with a unique filename.
     *
     * @param file the MultipartFile containing the data to be uploaded
     * @return the public URL of the uploaded file
     * @throws RuntimeException if an error occurs during the upload process
     */
    public String uploadFile(MultipartFile file, long carId) {

        CarDetailedEvaluation detailedEvaluation = carDetailRepository.
                findById(carId).orElseThrow(() -> new ResourceNotFound("car not found with id " + carId));

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
            String url = s3Client.utilities()
                    .getUrl(builder -> builder.bucket(singlePhotoBucketName).key(fileName))
                    .toString();

            // Save the photo details in the database
            CarEvaluationPhotos photos = new CarEvaluationPhotos();
            photos.setCarDetailedEvaluation(detailedEvaluation);
            photos.setPhotoUrl(url);

            return photosRepository.save(photos).getPhotoUrl();


        } catch (IOException e) {
            throw new RuntimeException("Error uploading file", e);
        }
    }

    /**
     * Uploads multiple files to an AWS S3 bucket.
     *
     * @param files a list of MultipartFile objects to be uploaded
     * @param carId
     * @return a list of public URLs for the uploaded files
     * @throws RuntimeException if an error occurs during the upload process
     */
    public List<String> uploadMultipleFiles(List<MultipartFile> files, long carId) {

        CarDetailedEvaluation detailedEvaluation = carDetailRepository.findById(carId).orElseThrow(() -> new ResourceNotFound("car not found with id " + carId));

        // Upload files to the multiple photo S3 bucket and return their public URLs

        List<String> urls = files.stream()
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

        // Save the photos details in the database
        urls.forEach(url -> {
            CarEvaluationPhotos photos = new CarEvaluationPhotos();
            photos.setCarDetailedEvaluation(detailedEvaluation);
            photos.setPhotoUrl(url);
            photosRepository.save(photos);
        });

        return urls;
    }

    public String actualPhotoUpload(MultipartFile file, long carId) {

        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFound("car not found with id " + carId));

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
            String url = s3Client.utilities()
                    .getUrl(builder -> builder.bucket(singlePhotoBucketName).key(fileName))
                    .toString();

            // Save the photo details in the database
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setCar(car);
            imageEntity.setImageUrl(url);

            return imageRepository.save(imageEntity).getImageUrl();

        } catch (IOException e) {
            throw new RuntimeException("Error uploading file", e);
        }
    }
}
