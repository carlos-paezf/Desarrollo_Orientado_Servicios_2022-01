package com.usta.users_alerts.repositories;

import com.usta.users_alerts.models.ImageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the `IImagesRepository` interface. It extends the `JpaRepository`
 * interface.
 * 
 * @author Carlos Páez
 */
public interface IImageRepository extends JpaRepository<ImageEntity, Long> {
    /**
     * Return the number of image records in the database.
     * 
     * @return The number of records in the ImageEntity table.
     */
    @Query("SELECT COUNT(i) FROM ImageEntity i")
    public Integer countTotalImageRecords();
}
