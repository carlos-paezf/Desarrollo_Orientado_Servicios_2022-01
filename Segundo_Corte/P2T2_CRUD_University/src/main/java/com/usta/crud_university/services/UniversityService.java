package com.usta.crud_university.services;

import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.University;
import com.usta.crud_university.repositories.IUniversityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for all the business logic of the University
 * microservice
 * 
 * @author Carlos Páez
 */
@Service
public class UniversityService {
    /**
     * This is a field injection. The field `_universityRepository` is injected with
     * the value of `_universityRepository` which is of type
     * `IUniversityRepository`.
     */
    @Autowired
    private IUniversityRepository _universityRepository;

    /**
     * Get all universities from the database
     * 
     * @return A list of University objects.
     */
    public List<University> getAllUniversities() {
        return _universityRepository.findAll();
    }

    /**
     * Get a university by its id
     * 
     * @param id The id of the university to retrieve.
     * @return The university that matches the id.
     */
    public Optional<University> getUniversityById(long id) {
        return _universityRepository.findById(id);
    }

    /**
     * Create a new university
     * 
     * @param university The university to be created.
     * @return The university object that was saved.
     */
    public University createUniversity(University university) {
        return _universityRepository.save(university);
    }

    /**
     * Delete a university from the database
     * 
     * @param id The id of the university to delete.
     */
    public void deleteUniversity(long id) {
        _universityRepository.deleteById(id);
    }
}
