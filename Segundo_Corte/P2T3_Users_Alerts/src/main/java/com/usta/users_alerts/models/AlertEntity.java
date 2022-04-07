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
 * An alert entity is a record of a user's alert settings
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "alerts")
public class AlertEntity {
    /**
     * This is a Java serialization thing. It's not important for us.
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Long alertId;

    /**
     * This is a many-to-one relationship. The `@JoinColumn` annotation is used to
     * specify the column name in the entity table that is used to join the tables.
     * The `@ManyToOne` annotation is used to specify that this is a many-to-one
     * relationship. The `fetch` attribute is used to specify the fetch type.
     */
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userId;

    /**
     * This is a column in the database. It is used to store the number of days that
     * the user wants to be alerted before the expiration date.
     */
    @Column(name = "alert_days")
    private Integer alertDays;

    /**
     * This is a column in the database. It is used to store the number of months
     * that the user wants to be alerted before the expiration date.
     */
    @Column(name = "alert_months")
    private Integer alertMonths;

    /**
     * This is a column in the database. It is used to store the title of the alert.
     */
    @Column(name = "alert_title")
    private String alertTitle;

    /**
     * This is a column in the database. It is used to store the status of the
     * alert.
     */
    @Column(name = "alert_status")
    private Integer alertStatus;

    /**
     * This is the constructor of the class. It is used to create an instance of the
     * class.
     */
    public AlertEntity(Long alertId, UserEntity userId, Integer alertDays, Integer alertMonths, String alertTitle,
            Integer alertStatus) {
        this.alertId = alertId;
        this.userId = userId;
        this.alertDays = alertDays;
        this.alertMonths = alertMonths;
        this.alertTitle = alertTitle;
        this.alertStatus = alertStatus;
    }

    /**
     * This is the default constructor of the class. It is used to create an
     * instance of the class.
     */
    public AlertEntity() {
    }

    /**
     * Returns the serial version UID of the class
     * 
     * @return The serial version UID of the class.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Get the alert ID from the alert name
     * 
     * @return The alert ID.
     */
    public Long getAlertId() {
        return alertId;
    }

    /**
     * Set the alert ID from the alert name
     * 
     * @param alertId The alert ID.
     */
    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    /**
     * Get the userId of the current user
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
     * The `getAlertDays()` function returns the value of the `alertDays` variable
     * 
     * @return The alertDays variable is being returned.
     */
    public Integer getAlertDays() {
        return alertDays;
    }

    /**
     * The `setAlertDays()` function sets the value of the `alertDays` variable
     * 
     * @param alertDays The alertDays variable is being set.
     */
    public void setAlertDays(Integer alertDays) {
        this.alertDays = alertDays;
    }

    /**
     * Returns the number of months that the alert will run
     * 
     * @return The alertMonths variable.
     */
    public Integer getAlertMonths() {
        return alertMonths;
    }

    /**
     * Sets the number of months that the alert will run
     * 
     * @param alertMonths The alertMonths variable.
     */
    public void setAlertMonths(Integer alertMonths) {
        this.alertMonths = alertMonths;
    }

    /**
     * The `getAlertTitle()` function returns the title of the alert
     * 
     * @return The alert title.
     */
    public String getAlertTitle() {
        return alertTitle;
    }

    /**
     * The `setAlertTitle()` function sets the title of the alert
     * 
     * @param alertTitle The alert title.
     */
    public void setAlertTitle(String alertTitle) {
        this.alertTitle = alertTitle;
    }

    /**
     * The alert status is set to 1 if the alert is active, and 0 if the alert is not active
     * 
     * @return The alert status.
     */
    public Integer getAlertStatus() {
        return alertStatus;
    }

    /**
     * The alert status is set to 1 if the alert is active, and 0 if the alert is not active
     * 
     * @param alertStatus The alert status.
     */
    public void setAlertStatus(Integer alertStatus) {
        this.alertStatus = alertStatus;
    }
}
