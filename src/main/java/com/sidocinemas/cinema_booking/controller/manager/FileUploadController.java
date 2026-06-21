package com.sidocinemas.cinema_booking.controller.manager;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.UploadResponse;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/manager/upload")
public class FileUploadController {

    private final String UPLOAD_DIR = "assets/movie/";

    @PostMapping("/movie-poster")
    public ApiResponse<UploadResponse> uploadMoviePoster(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new AppException(ErrorCode.POSTER_NOT_FOUND);
        }

        try {
            // Get original filename and extension
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            // Generate UUID filename
            String newFilename = UUID.randomUUID().toString() + extension;

            // Create directory if it doesn't exist
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save file
            Path path = Paths.get(UPLOAD_DIR + newFilename);
            Files.write(path, file.getBytes());

            // Return URL
            String fileUrl = "/assets/movie/" + newFilename;
            return ApiResponse.<UploadResponse>builder()
                    .data(UploadResponse.builder().url(fileUrl).build())
                    .message("Upload successful")
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
