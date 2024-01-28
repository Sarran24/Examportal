package com.examportal.controller.image;

import java.io.IOException;

import com.examportal.service.image.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/image")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

    @Autowired
    private StorageService storageService;

    @PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		String uploadImage = storageService.uploadImage(file);
		log.info("Image uploaded successfully: {}", file.getOriginalFilename());
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName){
		byte[] imageData=storageService.downloadImage(fileName);
		log.info("Image downloaded successfully: {}", fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);
	}

}
