package com.usta.users_alerts.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is used to store the role information
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "roles")
public class RoleEntity {
    /**
     * This is a serial version identifier. It is used to ensure that the
     * serialization of the object is compatible with the previous version of the
     * object.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    /**
     * This is a column in the database.
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * This is a column in the database.
     */
    @Column(name = "role_status")
    private String roleStatus;

    /**
     * This is a constructor.
     */
    public RoleEntity(Long roleId, String roleName, String roleStatus) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleStatus = roleStatus;
    }

    /**
     * This is a constructor.
     */
    public RoleEntity() {
    }

    /**
     * The `getSerialVersionUID` function returns the serial version UID of the class
     * 
     * @return The serialVersionUID is a static long value that is set to the current time in
     * milliseconds.
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Get the role ID of the current user
     * 
     * @return The roleId.
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * The setter method sets the value of the roleId field
     * 
     * @param roleId The role ID of the role that you want to get.
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * Get the role name of the current user
     * 
     * @return The role name.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * The setter method sets the value of the roleName field
     * 
     * @param roleName The role name of the role that you want to get.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Get the role status of the current user
     * 
     * @return The role status.
     */
    public String getRoleStatus() {
        return roleStatus;
    }

    /**
     * The setter method sets the value of the roleStatus field
     * 
     * @param roleStatus The role status of the role that you want to get.
     */
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }
}
