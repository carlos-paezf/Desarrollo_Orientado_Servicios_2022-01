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
 * This class is used to create a new faculty
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "faculty")
public class Faculty {

    /**
     * This is a serial version UID that is used by the Java serialization
     * framework.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Telling the database that the `facultyId` is the primary key of the table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private long facultyId;

    /**
     * This is telling the database that the `facultyName` is a column in the table
     * `faculty` and the name of the column is `faculty_name`.
     */
    @Column(name = "faculty_name")
    private String facultyName;

    /**
     * This is telling the database that the `sectionalId` is a column in the table
     * `faculty` and the name of the column is `sectional_id`.
     */
    @JoinColumn(name = "sectional_id", referencedColumnName = "sectional_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Sectional sectionalId;

    /**
     * This is the constructor of the class Faculty. It is used to create a new
     * faculty.
     */
    public Faculty(long facultyId, String facultyName, Sectional sectionalId) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.sectionalId = sectionalId;
    }

    /**
     * This is the default constructor of the class Faculty. It is used to create a
     * new faculty.
     */
    public Faculty() {
    }

    /**
     * The getFacultyId function returns the facultyId variable
     * 
     * @return The facultyId.
     */
    public long getFacultyId() {
        return facultyId;
    }

    /**
     * The setter method for the facultyId property
     * 
     * @param facultyId The faculty ID of the faculty member.
     */
    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    /**
     * The getFacultyName method returns the facultyName field
     * 
     * @return The facultyName variable.
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * The setFacultyName method sets the facultyName instance variable to the value
     * of the facultyName parameter
     * 
     * @param facultyName The name of the faculty member.
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    /**
     * The getSectionalId() method returns the Sectional object that is associated
     * with the SectionalId
     * 
     * @return The sectionalId.
     */
    public Sectional getSectionalId() {
        return sectionalId;
    }

    /**
     * The setSectionalId method sets the sectionalId field of the class to the
     * sectionalId parameter
     * 
     * @param sectionalId The sectional that the horse is in.
     */
    public void setSectionalId(Sectional sectionalId) {
        this.sectionalId = sectionalId;
    }
}
