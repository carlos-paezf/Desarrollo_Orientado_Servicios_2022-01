package com.usta.crud_university.services;

import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.Subject;
import com.usta.crud_university.repositories.ISubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is used to create, retrieve, update and delete subjects
 * 
 * @author Carlos Páez
 */
@Service
public class SubjectService {
    /**
     * This is a field injection. The field `_subjectRepository` is injected with
     * the value of `ISubjectRepository` which is provided by the `@Autowired`
     * annotation.
     */
    @Autowired
    private ISubjectRepository _subjectRepository;

    /**
     * Get all the subjects from the database
     * 
     * @return A list of all the subjects.
     */
    public List<Subject> getAllSubjects() {
        return _subjectRepository.findAll();
    }

    /**
     * Get a subject by its id
     * 
     * @param id The id of the subject to be retrieved.
     * @return The Subject object that matches the id.
     */
    public Optional<Subject> getSubjectById(long id) {
        return _subjectRepository.findById(id);
    }

    /**
     * Create a new subject
     * 
     * @param subject The subject to be created.
     * @return The Subject object that was save.
     */
    public Subject createSubject(Subject subject) {
        return _subjectRepository.save(subject);
    }

    /**
     * Delete a subject by ID
     * 
     * @param id The id of the subject to be deleted.
     */
    public void deleteSubjectById(long id) {
        _subjectRepository.deleteById(id);
    }
}
