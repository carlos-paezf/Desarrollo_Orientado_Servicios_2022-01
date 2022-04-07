package com.usta.users_alerts.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class is used to create a new image
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "images")
public class ImageEntity {
    /**
     * This is a Java serialization thing. It's not important for us.
     */
    private static final Long serialVersionUID = 1L;

    /**
     * Telling the database to create an `image_id` column and to use the `Long`
     * data type.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    /**
     * This is telling the database to create a column called `user_id` and to use
     * the `user_id` column in the `users` table.
     */
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userId;

    /**
     * This is telling the database to create a column called `image_public_name`
     * and to use the `image_public_name` column in the `images` table.
     */
    @Column(name = "image_public_name")
    private String imagePublicName;

    /**
     * This is telling the database to create a column called `image_private_name`
     * and to use the `image_private_name` column in the `images` table.
     */
    @Column(name = "image_private_name")
    private String imagePrivateName;

    /**
     * This is telling the database to create a column called `image_type` and to
     * use the `image_type` column in the `images` table.
     */
    @Column(name = "image_type")
    private String imageType;

    /**
     * This is telling the database to create a column called `image_size` and to
     * use the `image_size` column in the `images` table.
     */
    @Column(name = "image_size")
    private String imageSize;

    /**
     * This is telling the database to create a column called `image_favorite` and
     * to use the `image_favorite` column in the `images` table.
     */
    @Column(name = "image_favorite")
    private Integer imageFavorite;

    /**
     * This is the constructor for the `ImageEntity` class. It is used to create a
     * new `ImageEntity` object.
     */
    public ImageEntity(Long imageId, UserEntity userId, String imagePublicName, String imagePrivateName,
            String imageType, String imageSize, Integer imageFavorite) {
        this.imageId = imageId;
        this.userId = userId;
        this.imagePublicName = imagePublicName;
        this.imagePrivateName = imagePrivateName;
        this.imageType = imageType;
        this.imageSize = imageSize;
        this.imageFavorite = imageFavorite;
    }

    /**
     * This is the default constructor for the `ImageEntity` class. It is used to
     * create a new `ImageEntity` object.
     */
    public ImageEntity() {
    }

    /**
     * Get the image ID of the image that was created by the previous step
     * 
     * @return The imageId.
     */
    public Long getImageId() {
        return imageId;
    }

    /**
     * It sets the imageId of the Image object.
     * 
     * @param imageId The ID of the image to be used for the new virtual machine.
     */
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    /**
     * Get the userId from the user entity
     * 
     * @return The userId property is being returned.
     */
    public UserEntity getUserId() {
        return userId;
    }

    /**
     * The setter method for the userId property
     * 
     * @param userId The userId of the user that is being updated.
     */
    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    /**
     * Get the image public name
     * 
     * @return The image public name.
     */
    public String getImagePublicName() {
        return imagePublicName;
    }

    /**
     * `setImagePublicName` sets the `imagePublicName` property of the `Image`
     * object
     * 
     * @param imagePublicName The name of the image that you want to use.
     */
    public void setImagePublicName(String imagePublicName) {
        this.imagePublicName = imagePublicName;
    }

    /**
     * Get the private name of the image
     * 
     * @return The image private name.
     */
    public String getImagePrivateName() {
        return imagePrivateName;
    }

    /**
     * `setImagePrivateName` is a function that sets the value of the
     * `imagePrivateName` variable
     * 
     * @param imagePrivateName The name of the image that you want to use.
     */
    public void setImagePrivateName(String imagePrivateName) {
        this.imagePrivateName = imagePrivateName;
    }

    /**
     * Returns the image type of the image
     * 
     * @return The image type.
     */
    public String getImageType() {
        return imageType;
    }

    /**
     * `setImageType` sets the image type of the image
     * 
     * @param imageType The type of image. Valid values are:
     */
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    /**
     * Returns the size of the image
     * 
     * @return The image size.
     */
    public String getImageSize() {
        return imageSize;
    }

    /**
     * `setImageSize` sets the image size to the value of the `imageSize` parameter
     * 
     * @param imageSize The size of the image to be returned.
     */
    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    /**
     * Returns the image favorite count
     * 
     * @return The imageFavorite variable is being returned.
     */
    public Integer getImageFavorite() {
        return imageFavorite;
    }

    /**
     * `setImageFavorite(Integer imageFavorite)`
     * 
     * The function name is `setImageFavorite` and it takes one parameter,
     * `imageFavorite`
     * 
     * @param imageFavorite The image to be displayed when the user clicks the
     *                      favorite button.
     */
    public void setImageFavorite(Integer imageFavorite) {
        this.imageFavorite = imageFavorite;
    }

    /**
     * Returns the serial version UID of the class
     * 
     * @return The serialVersionUID is a static final long that is set to the
     *         current time in milliseconds.
     */
    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }
}
