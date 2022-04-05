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
 * A subject is a class that represents a subject in a university
 * 
 * @author Carlos Páez
 */
@Entity
@Table(name = "subject")
public class Subject {

    /**
     * This is a serial version UID that is used by the Java serialization
     * mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is the primary key of the table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private long subjectId;

    /**
     * This is a column of the table.
     */
    @Column(name = "subject_semester")
    private int subjectSemester;

    /**
     * This is a column of the table.
     */
    @Column(name = "subject_name")
    private String subjectName;

    /**
     * This is a column of the table.
     */
    @Column(name = "subject_cod")
    private Long subjectCod;

    /**
     * This is a column of the table.
     */
    @Column(name = "subject_type")
    private String subjectType;

    /**
     * This is a column of the table.
     */
    @JoinColumn(name = "professor_id", referencedColumnName = "professor_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Professor professorId;

    /**
     * This is the default constructor of the class.
     */
    public Subject() {
    }

    /**
     * This is the constructor of the class.
     */
    public Subject(long subjectId, int subjectSemester, String subjectName, long subjectCod,
            String subjectType, Professor professorId) {
        this.subjectId = subjectId;
        this.subjectSemester = subjectSemester;
        this.subjectName = subjectName;
        this.subjectCod = subjectCod;
        this.subjectType = subjectType;
        this.professorId = professorId;
    }

    /**
     * Get the subject ID of the current user
     * 
     * @return The subjectId.
     */
    public long getSubjectId() {
        return this.subjectId;
    }

    /**
     * Set the subject ID of the current user
     * 
     * @param subjectId The subjectId to set.
     */
    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Returns the semester of the subject
     * 
     * @return The value of the instance variable subjectSemester.
     */
    public int getSubjectSemester() {
        return this.subjectSemester;
    }

    /**
     * The setter method for the subjectSemester field
     * 
     * @param subjectSemester The semester that the subject is taught in.
     */
    public void setSubjectSemester(int subjectSemester) {
        this.subjectSemester = subjectSemester;
    }

    /**
     * Returns the name of the subject
     * 
     * @return The value of the instance variable subjectName.
     */
    public String getSubjectName() {
        return this.subjectName;
    }

    /**
     * The setter method for the subjectName property
     * 
     * @param subjectName The name of the subject that the message is about.
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * The getSubjectCod() method returns the subject cod of the current student
     * 
     * @return The subject cod.
     */
    public long getSubjectCod() {
        return this.subjectCod;
    }

    /**
     * The setter method for the subjectCod property
     * 
     * @param subjectCod The subject code of the subject that the student is taking.
     */
    public void setSubjectCod(long subjectCod) {
        this.subjectCod = subjectCod;
    }

    /**
     * Returns the subject type of the current subject
     * 
     * @return The subject type.
     */
    public String getSubjectType() {
        return this.subjectType;
    }

    /**
     * The `setSubjectType` function sets the `subjectType` property of the
     * `Subject` class
     * 
     * @param subjectType The type of subject. This can be either "User" or "Group".
     */
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    /**
     * Returns the professor ID of the current subject
     * 
     * @return The professor ID.
     */
    public Professor getProfessorId() {
        return this.professorId;
    }

    /**
     * The setter method for the professorId property
     * 
     * @param professorId The ID of the professor.
     */
    public void setProfessorId(Professor professorId) {
        this.professorId = professorId;
    }
}
