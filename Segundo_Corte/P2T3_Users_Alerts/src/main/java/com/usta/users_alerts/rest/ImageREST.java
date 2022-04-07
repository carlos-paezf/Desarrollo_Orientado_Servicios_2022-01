package com.usta.users_alerts.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.ImageEntity;
import com.usta.users_alerts.services.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/images")
public class ImageREST {
    /**
     * This is a way to inject the `ImageService` into the `ImageREST` class.
     */
    @Autowired
    private ImageService _imageService;

    /**
     * Get all images from the database
     * 
     * @return A list of ImageEntity objects.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<ImageEntity>> getAllImages() {
        return ResponseEntity.ok(_imageService.getAllImages());
    }

    /**
     * Get an image by its id
     * 
     * @param id The id of the image to retrieve.
     * @return The image entity.
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<ImageEntity>> getImageById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_imageService.getImageById(id));
    }

    /**
     * `@GetMapping(value = "/total-records")`
     * 
     * This is a Spring MVC annotation that tells Spring MVC to map this function to the URL
     * "/total-records"
     * 
     * @return The total number of records in the image table.
     */
    @GetMapping(value = "/total-records")
    private ResponseEntity<Integer> countTotalImageRecords() {
        return ResponseEntity.ok(_imageService.countTotalImageRecords());
    }

    /**
     * Create an image
     * 
     * @param image The image entity that will be created.
     * @return The URI of the newly created image.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<ImageEntity> createImage(@RequestBody ImageEntity image) {
        ImageEntity temp = _imageService.createImage(image);
        try {
            return ResponseEntity.created(new URI("/api/images/" + temp.getImageId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update an image
     * 
     * @param image The image entity that will be updated.
     * @return The updated image.
     */
    @PutMapping(value = "/update")
    private ResponseEntity<ImageEntity> updateImage(@RequestBody ImageEntity image) {
        ImageEntity temp = _imageService.updateImage(image);
        try {
            return ResponseEntity.created(new URI("/api/images/" + temp.getImageId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * `@DeleteMapping(value = "/delete/{id}")`
     * 
     * This annotation tells Spring MVC that this method is a handler method for HTTP DELETE requests. 
     * 
     * The `@PathVariable` annotation is used to get the id of the image to be deleted from the path. 
     * 
     * The function returns a `ResponseEntity` object with a message that the image was deleted
     * successfully.
     * 
     * @param id The id of the image to be deleted.
     * @return The response is a JSON object with a message.
     */
    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<String> deleteImage(@PathVariable("id") Long id) {
        _imageService.deleteImage(id);
        return ResponseEntity.ok("Image deleted successfully");
    }
}
