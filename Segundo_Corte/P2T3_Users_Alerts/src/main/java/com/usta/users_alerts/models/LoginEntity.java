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
 * This class is used to store the login information for the user
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "login")
public class LoginEntity {
    /**
     * This is a serial version UID that is used by the Java serialization
     * framework.
     */
    private static final Long serialVersionUID = 1L;

    /**
     * Telling the database to create a column named `login_id` and to use the
     * `Long` data type.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Long loginId;

    /**
     * This is telling the database to create a column named `user_id` and to use
     * the `user_id` column in the `user` table.
     */
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userId;

    /**
     * This is the constructor of the class. It is used to create an instance of the
     * class.
     */
    public LoginEntity(Long loginId, UserEntity userId) {
        this.loginId = loginId;
        this.userId = userId;
    }

    /**
     * This is the default constructor of the class. It is used to create an
     * instance of the class.
     */
    public LoginEntity() {
    }

    /**
     * Returns the serial version UID of the class
     * 
     * @return The serialVersionUID is a static long that is set to the current time
     *         in milliseconds.
     */
    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Get the login ID of the current user
     * 
     * @return The login ID of the user.
     */
    public Long getLoginId() {
        return loginId;
    }

    /**
     * The setter method for the loginId property
     * 
     * @param loginId The loginId of the user who is making the request.
     */
    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    /**
     * Get the user id of the current user
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
}
