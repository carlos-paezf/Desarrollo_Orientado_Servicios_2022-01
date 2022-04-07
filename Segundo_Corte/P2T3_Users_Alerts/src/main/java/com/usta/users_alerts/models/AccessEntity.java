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
 * An AccessEntity is a user's login credentials
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "access")
public class AccessEntity {
    /**
     * This is a Java serialization thing. It's not important for us.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Telling the database that this field is the primary key.
     */
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Long userId;

    /**
     * It's telling the database that the field `accessEmail` is a column in the
     * table `access` and that its name is `access_email`.
     */
    @Column(name = "access_email")
    private String accessEmail;

    /**
     * It's telling the database that the field `accessPassword` is a column in the
     * table `access` and that its name is `access_password`.
     */
    @Column(name = "access_password")
    private String accessPassword;

    /**
     * It's creating a new instance of the class AccessEntity.
     */
    public AccessEntity(Long userId, String accessEmail, String accessPassword) {
        this.userId = userId;
        this.accessEmail = accessEmail;
        this.accessPassword = accessPassword;
    }

    /**
     * It's creating a new instance of the class AccessEntity.
     */
    public AccessEntity() {
    }

    /**
     * The `getSerialVersionUID` function returns the serial version UID of the
     * class
     * 
     * @return The serialVersionUID is a static long value that is set to the
     *         current time in milliseconds.
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Get the userId of the current user
     * 
     * @return The userId property is being returned.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * `setUserId` is a method that takes a `UserEntity` as a parameter and sets the
     * `userId` field of the `User` class to the `userId` field of the `UserEntity`
     * parameter
     * 
     * @param userId The userId of the user that is being updated.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Returns the access email address for the user
     * 
     * @return The access email.
     */
    public String getAccessEmail() {
        return accessEmail;
    }

    /**
     * `setAccessEmail` sets the access email to the value of the `accessEmail`
     * parameter
     * 
     * @param accessEmail The email address of the user.
     */
    public void setAccessEmail(String accessEmail) {
        this.accessEmail = accessEmail;
    }

    /**
     * Returns the access password for the database
     * 
     * @return The access password.
     */
    public String getAccessPassword() {
        return accessPassword;
    }

    /**
     * `setAccessPassword` sets the access password for the database
     * 
     * @param accessPassword The password for the user account that has access to
     *                       the database.
     */
    public void setAccessPassword(String accessPassword) {
        this.accessPassword = accessPassword;
    }
}
