package com.usta.crudspring.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * This is a class that represents a faculty member.
 */
@Entity
@Table(name = "faculties")
public class Faculty {

    /**
     * Telling the database to create a column named `faculty_id` and to use the `AUTO` strategy to
     * generate the values.
     */
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long facultyId;


    /**
     * This is telling the database to create a column named `faculty_name` and to use the
     * `faculty_name` as the name of the column.
     */
    @Column(name = "faculty_name")
    private String facultyName;


    /**
     * This is the constructor of the class.
     *
     * @param facultyID   The id of the faculty
     * @param facultyName The name of the faculty;
     */
    public Faculty(Long facultyId, String facultyName) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
    }

    /**
     * This is the default constructor of the class.
     */
    public Faculty() {
    }


    /**
     * The getFacultyId method returns the facultyId field
     *
     * @return The facultyId.
     */
    public Long getFacultyId() {
        return facultyId;
    }


    /**
     * The setter method for the facultyId property
     *
     * @param facultyId The id of the faculty member.
     */
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }


    /**
     * The getFacultyName function returns the faculty name
     *
     * @return The facultyName variable.
     */
    public String getFacultyName() {
        return facultyName;
    }


    /**
     * The setter method for the facultyName attribute
     *
     * @param facultyName The name of the faculty member.
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
