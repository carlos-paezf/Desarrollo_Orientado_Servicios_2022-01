package com.usta.crud_university.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is used to store the university information
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "university")
public class University {

    /**
     * This is a serial version UID that is used by the Java serialization
     * framework.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Telling the database that this field is the primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id")
    public Long universityId;

    /**
     * This is telling the database that the field `universityName` is a column in
     * the database table `university`.
     */
    @Column(name = "university_name")
    public String universityName;

    /**
     * This is telling the database that the field `universityNit` is a column in
     * the database table `university`.
     */
    @Column(name = "university_nit")
    public String universityNit;

    /**
     * This is the constructor of the class.
     */
    public University(Long universityId, String universityName, String universityNit) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.universityNit = universityNit;
    }

    /**
     * This is the default constructor of the class.
     */
    public University() {
    }

    /**
     * The getUniversityId function returns the universityId field of the University
     * class
     * 
     * @return The universityId field of the University class.
     */
    public Long getUniversityId() {
        return universityId;
    }

    /**
     * The setter method for the universityId property
     * 
     * @param universityId The universityId of the university.
     */
    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    /**
     * The getUniversityName function returns the universityName variable
     * 
     * @return The university name.
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * The function sets the university name
     * 
     * @param universityName The name of the university.
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * The getUniversityNit() function returns the universityNit variable
     * 
     * @return The universityNit variable.
     */
    public String getUniversityNit() {
        return this.universityNit;
    }

    /**
     * The setUniversityNit function sets the universityNit variable to the value
     * passed in as a
     * parameter
     * 
     * @param universityNit The university's nit.
     */
    public void setUniversityNit(String universityNit) {
        this.universityNit = universityNit;
    }
}
