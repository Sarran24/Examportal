package com.examportal.repository.image;

import java.util.Optional;

import com.examportal.model.image.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<ImageData, Long> {

	Optional<ImageData> findByName(String fileName);
}
