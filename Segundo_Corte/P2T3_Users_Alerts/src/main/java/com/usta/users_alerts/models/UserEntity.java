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
 * This class is used to represent a user in the system
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "users")
public class UserEntity {
    /**
     * This is a serial version UID that is used by the Java serialization
     * framework.
     */
    private final static long serialVersionUID = 1L;

    /**
     * This is telling the database that the userId field is a column in the users
     * table and that the name of the column is userId.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * This is telling the database that the roleId field is a column in the users
     * table and that the name of the column is roleId.
     */
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private RoleEntity roleId;

    /**
     * This is telling the database that the userDocument field is a column in the
     * users table and that the name of the column is userDocument.
     */
    @Column(name = "user_doc")
    private String userDocument;

    /**
     * This is telling the database that the userTypeDocument field is a column in
     * the users table and that the name of the column is userTypeDocument.
     */
    @Column(name = "user_type_doc")
    private Integer userTypeDocument;

    /**
     * This is telling the database that the userName field is a column in the users
     * table and that the name of the column is userName.
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * This is telling the database that the userLastName field is a column in the
     * users table and that the name of the column is userLastName.
     */
    @Column(name = "user_lastname")
    private String userLastName;

    /**
     * This is telling the database that the userPhone field is a column in the
     * users table and that the name of the column is userPhone.
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * Telling the database that the userStatus field is a column in the users table
     * and that the name of the column is userStatus.
     */
    @Column(name = "user_status")
    private Integer userStatus;

    /**
     * This is the default constructor of the class.
     */
    public UserEntity() {
    }

    /**
     * The constructor of the class.
     */
    public UserEntity(Long userId, RoleEntity roleId, String userDocument, Integer userTypeDocument, String userName,
            String userLastName, String userPhone, Integer userStatus) {
        this.userId = userId;
        this.roleId = roleId;
        this.userDocument = userDocument;
        this.userTypeDocument = userTypeDocument;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userPhone = userPhone;
        this.userStatus = userStatus;
    }

    /**
     * The `getSerialVersionUID` function returns the serial version UID of the
     * class
     * 
     * @return The serialVersionUID is a static long variable that is initialized to
     *         a unique value at
     *         compile time.
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Get the user id of the current user
     * 
     * @return The userId.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Set the user id of the current user
     * 
     * @param userId The userId to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Get the role id of the current user
     * 
     * @return The roleId.
     */
    public RoleEntity getRoleId() {
        return roleId;
    }

    /**
     * The setter method sets the value of the roleId property
     * 
     * @param roleId The role that the user is being assigned to.
     */
    public void setRoleId(RoleEntity roleId) {
        this.roleId = roleId;
    }

    /**
     * Returns the user document
     * 
     * @return The user document.
     */
    public String getUserDocument() {
        return userDocument;
    }

    /**
     * The setter method sets the value of the userDocument property
     * 
     * @param userDocument The user document.
     */
    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }

    /**
     * The getUserTypeDocument function returns the userTypeDocument variable
     * 
     * @return The user type document.
     */
    public Integer getUserTypeDocument() {
        return userTypeDocument;
    }

    /**
     * The setter method for the userTypeDocument property
     * 
     * @param userTypeDocument The type of document that the user has.
     */
    public void setUserTypeDocument(Integer userTypeDocument) {
        this.userTypeDocument = userTypeDocument;
    }

    /**
     * Returns the user name of the current user
     * 
     * @return The userName variable.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name of the current user
     * 
     * @param userName The user name of the current user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the last name of the user
     * 
     * @return The last name of the user.
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * The function sets the user's last name
     * 
     * @param userLastName The last name of the user.
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * Get the user's phone number
     * 
     * @return The userPhone variable.
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * It sets the userPhone variable.
     * 
     * @param userPhone The phone number of the user.
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * The getUserStatus function returns the userStatus variable
     * 
     * @return The userStatus field.
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * The setUserStatus function sets the userStatus field of the User class
     * 
     * @param userStatus The user status of the user.
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}
