package com.usta.crudspring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class is used to represent a professor
 */
@Entity
@Table(name = "professors")
public class Professor {

    /**
     * This is a serial version id. It is used to ensure that the object is
     * compatible with the
     * serialization process.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Telling the database that this field is the primary key.
     */
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "professor_id")
    public Long professorId;

    /**
     * This is telling the database that the field `professorName` is a column in
     * the database.
     */
    @Column(name = "professor_name")
    private String professorName;

    /**
     * Telling the database that the field `professorSurname` is a column in the
     * database.
     */
    @Column(name = "professor_surname")
    private String professorSurname;

    /**
     * Telling the database that the field `professorDocument` is a column in the
     * database.
     */
    @Column(name = "professor_document")
    private String professorDocument;

    /**
     * This is telling the database that the field `facultyId` is a column in the
     * database.
     */
    @JoinColumn(name = "faculty_id", referencedColumnName = "faculty_id")
    @ManyToOne(fetch = javax.persistence.FetchType.EAGER)
    private Faculty facultyId;

    /**
     * This is the constructor of the class. It is used to create a new object of the class.
     */
    public Professor(Long professorId, String professorName, String professorSurname, String professorDocument,
                     Faculty facultyId) {
        this.professorId = professorId;
        this.professorName = professorName;
        this.professorSurname = professorSurname;
        this.professorDocument = professorDocument;
        this.facultyId = facultyId;
    }

    /**
     * This is the constructor of the class. It is used to create a new object of the class.
     */
    public Professor() {
    }

    /**
     * The getProfessorId method returns the professorId field
     *
     * @return Nothing.
     */
    public Long getProfessorId() {
        return professorId;
    }

    /**
     * `setProfessorId` is a function that sets the professorId field of the Professor class
     *
     * @param professorId The id of the professor.
     */
    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    /**
     * Get the professor's name.
     *
     * @return The professor's name.
     */
    public String getProfessorName() {
        return professorName;
    }

    /**
     * The function sets the professor name
     *
     * @param professorName The name of the professor.
     */
    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    /**
     * The getProfessorSurname function returns the surname of the professor
     *
     * @return The professor's surname.
     */
    public String getProfessorSurname() {
        return professorSurname;
    }

    /**
     * The function sets the professor's surname
     *
     * @param professorSurname The professor's surname.
     */
    public void setProfessorSurname(String professorSurname) {
        this.professorSurname = professorSurname;
    }

    /**
     * The getProfessorDocument function returns the document of the professor
     *
     * @return The professor's document.
     */
    public String getProfessorDocument() {
        return professorDocument;
    }

    /**
     * `setProfessorDocument` is a function that sets the professor document
     *
     * @param professorDocument The document of the professor.
     */
    public void setProfessorDocument(String professorDocument) {
        this.professorDocument = professorDocument;
    }

    /**
     * The getFacultyId method returns the value of the facultyId field
     *
     * @return Nothing.
     */
    public Faculty getFacultyId() {
        return facultyId;
    }

    /**
     * The setter method for the facultyId property
     *
     * @param facultyId The id of the faculty that the professor is being assigned to.
     */
    public void setFacultyId(Faculty facultyId) {
        this.facultyId = facultyId;
    }
}
