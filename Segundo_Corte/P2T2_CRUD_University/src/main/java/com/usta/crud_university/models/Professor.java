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
 * This class is used to store the information of the professors
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "professor")
public class Professor {
    /**
     * This is a serial version id. It is used to ensure that the serialization of
     * the object is done properly.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Telling the JPA that this field is the primary key of the table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Long professorId;

    /**
     * Telling the JPA that the professorDocument field is a column in the table
     * professor.
     */
    @Column(name = "professor_document")
    private String professorDocument;

    /**
     * Telling the JPA that the professorName field is a column in the table
     * professor.
     */
    @Column(name = "professor_name")
    private String professorName;

    /**
     * Telling the JPA that the professorLastname field is a column in the table
     * professor.
     */
    @Column(name = "professor_lastname")
    private String professorLastname;

    /**
     * Telling the JPA that the professorType field is a column in the table
     * professor.
     */
    @Column(name = "professor_type")
    private int professorType;

    /**
     * This is telling the JPA that the professor table has a foreign key to the
     * faculty table.
     */
    @JoinColumn(name = "faculty_id", referencedColumnName = "faculty_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty facultyId;

    /**
     * This is the default constructor of the class.
     */
    public Professor() {
    }

    /**
     * This is the constructor of the class.
     */
    public Professor(Long professorId, String professorDocument, String professorName,
            String professorLastname, int professorType, Faculty facultyId) {
        this.professorId = professorId;
        this.professorDocument = professorDocument;
        this.professorName = professorName;
        this.professorLastname = professorLastname;
        this.professorType = professorType;
        this.facultyId = facultyId;
    }

    /**
     * This is the getter method for the professorId field.
     * 
     * @return The professorId.
     */
    public Long getProfessorId() {
        return professorId;
    }

    /**
     * `setProfessorId` is a method that sets the professorId field of the Professor
     * class
     * 
     * @param professorId The id of the professor.
     */
    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    /**
     * `getProfessorDocument` is a method that returns the professorDocument field
     * of the Professor class
     * 
     * @return The professorDocument.
     */
    public String getProfessorDocument() {
        return professorDocument;
    }

    /**
     * `setProfessorDocument` is a method that sets the professorDocument field of
     * the Professor class
     * 
     * @param professorDocument The document of the professor.
     */
    public void setProfessorDocument(String professorDocument) {
        this.professorDocument = professorDocument;
    }

    /**
     * The getProfessorName function returns the professor's name
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
     * Get the professor's last name
     * 
     * @return The professor's last name.
     */
    public String getProfessorLastname() {
        return professorLastname;
    }

    /**
     * `setProfessorLastname` sets the professor's last name
     * 
     * @param professorLastname The last name of the professor.
     */
    public void setProfessorLastname(String professorLastname) {
        this.professorLastname = professorLastname;
    }

    /**
     * The getProfessorType function returns the professorType variable
     * 
     * @return The professorType field.
     */
    public int getProfessorType() {
        return professorType;
    }

    /**
     * The setProfessorType function sets the professorType variable to the value
     * passed in
     * 
     * @param professorType The type of professor.
     */
    public void setProfessorType(int professorType) {
        this.professorType = professorType;
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
     * @param facultyId The id of the faculty that the professor is being assigned
     *                  to.
     */
    public void setFacultyId(Faculty facultyId) {
        this.facultyId = facultyId;
    }
}
