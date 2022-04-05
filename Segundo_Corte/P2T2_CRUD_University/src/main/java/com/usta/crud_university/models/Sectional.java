package com.usta.crud_university.models;

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
 * This class is used to store the sectional centers
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "sectional")
public class Sectional {

    /**
     * This is a Java serialization class. It is used to ensure that the object is
     * the same when it is deserialized.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Telling the database that this field is the primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sectional_id")
    public Long sectionalId;

    /**
     * This is telling the database that the field `sectionalAddress` is a column in
     * the database.
     */
    @Column(name = "sectional_address")
    public String sectionalAddress;

    /**
     * This is telling the database that the field `sectionalName` is a column in
     * the database.
     */
    @Column(name = "sectional_name")
    public String sectionalName;

    /**
     * This is telling the database that the field `universityId` is a column in the
     * database.
     */
    @JoinColumn(name = "university_id", referencedColumnName = "university_id")
    @ManyToOne(fetch = FetchType.EAGER)
    public University universityId;

    /**
     * This is the constructor of the class. It is used to create an object of the
     * class Sectional.
     */
    public Sectional(String sectionalAddress, String sectionalName, University universityId) {
        this.sectionalAddress = sectionalAddress;
        this.sectionalName = sectionalName;
        this.universityId = universityId;
    }

    /**
     * This is the default constructor of the class. It is used to create an object
     * of the class Sectional.
     */
    public Sectional() {
    }

    /**
     * The getSectionalId method returns the sectionalId field
     * 
     * @return The sectionalId.
     */
    public Long getSectionalId() {
        return sectionalId;
    }

    /**
     * The setter method for the sectionalId property
     * 
     * @param sectionalId The sectional id of the sectional that the user is trying
     *                    to access.
     */
    public void setSectionalId(Long sectionalId) {
        this.sectionalId = sectionalId;
    }

    /**
     * The getSectionalAddress method returns the sectional address of the person
     * 
     * @return The sectional address.
     */
    public String getSectionalAddress() {
        return sectionalAddress;
    }

    /**
     * The setSectionalAddress method sets the sectionalAddress instance variable to
     * the
     * sectionalAddress parameter
     * 
     * @param sectionalAddress The address of the sectional center.
     */
    public void setSectionalAddress(String sectionalAddress) {
        this.sectionalAddress = sectionalAddress;
    }

    /**
     * The getSectionalName method returns the sectional name
     * 
     * @return The sectional name.
     */
    public String getSectionalName() {
        return sectionalName;
    }

    /**
     * The setSectionalName function sets the sectionalName variable to the value
     * passed in
     * 
     * @param sectionalName The name of the sectional.
     */
    public void setSectionalName(String sectionalName) {
        this.sectionalName = sectionalName;
    }

    /**
     * Get the universityId of the sectional
     * 
     * @return Nothing.
     */
    public University getUniversityId() {
        return universityId;
    }

    /**
     * The setter method for the universityId property
     * 
     * @param universityId The universityId of the university.
     */
    public void setUniversityId(University universityId) {
        this.universityId = universityId;
    }
}
