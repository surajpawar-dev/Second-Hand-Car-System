package com.suraj.JWT_App.controller;

import com.suraj.JWT_App.entity.evaluation.CarEvaluationPhotos;
import com.suraj.JWT_App.entity.evaluation.ImageEntity;
import com.suraj.JWT_App.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/s3")

public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/actual-upload/{carId}")
    public ResponseEntity<String> actualPhotoUpload(@RequestParam("file") MultipartFile file, @PathVariable long carId ) {
        String fileUrl = s3Service.actualPhotoUpload(file,carId);
        return new ResponseEntity<>(fileUrl, HttpStatus.CREATED);
    }

    @PostMapping("/evaluation-upload/{carId}")
    public ResponseEntity<String> uploadEvaluationPhoto(@RequestParam("file") MultipartFile file, @PathVariable long carId) {
        String carPhotos = s3Service.uploadFile(file, carId);
        return new ResponseEntity<>(carPhotos, HttpStatus.CREATED);
    }

    @PostMapping("/upload-multiple/{carId}")
    public ResponseEntity<List<String>> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files,
                                                            @PathVariable long carId){
        List<String> fileUrls = s3Service.uploadMultipleFiles(files,carId);
        return ResponseEntity.ok(fileUrls); // Returns list of public URLs
    }
}
