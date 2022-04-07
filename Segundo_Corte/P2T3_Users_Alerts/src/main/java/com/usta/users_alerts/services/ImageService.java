package com.usta.users_alerts.services;

import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.ImageEntity;
import com.usta.users_alerts.repositories.IImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The ImageService class is a Spring Data JPA implementation of the
 * IImageService interface
 *
 * @author Carlos Páez
 */
@Service
public class ImageService {
    /**
     * This is a field injection. The `@Autowired` annotation is used to inject the
     * `IImageRepository` interface into the `_imageRepository` field.
     */
    @Autowired
    private IImageRepository _imageRepository;

    /**
     * Get all images from the database
     * 
     * @return A list of ImageEntity objects.
     */
    public List<ImageEntity> getAllImages() {
        return _imageRepository.findAll();
    }

    /**
     * Get an image by its id
     * 
     * @param id The id of the image to retrieve.
     * @return An Image object matches the id.
     */
    public Optional<ImageEntity> getImageById(Long id) {
        return _imageRepository.findById(id);
    }

    /**
     * Count the total number of image records in the database
     * 
     * @return The number of records in the image table.
     */
    public Integer countTotalImageRecords() {
        return _imageRepository.countTotalImageRecords();
    }

    /**
     * Create a new image
     * 
     * @param image The image to be saved.
     * @return The saved image.
     */
    public ImageEntity createImage(ImageEntity image) {
        return _imageRepository.save(image);
    }

    /**
     * Update an image
     * 
     * @param image The image to be updated.
     * @return The updated image.
     */
    public ImageEntity updateImage(ImageEntity image) {
        return _imageRepository.save(image);
    }

    /**
     * Delete an image from the database
     * 
     * @param id The id of the image to delete.
     */
    public void deleteImage(Long id) {
        _imageRepository.deleteById(id);
    }
}
