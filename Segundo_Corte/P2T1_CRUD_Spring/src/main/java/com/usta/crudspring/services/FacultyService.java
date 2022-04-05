package com.usta.crudspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.usta.crudspring.models.Faculty;
import com.usta.crudspring.repositories.IFacultyRepository;


/**
 * The `FacultyService` class is a Spring Data JPA service class that provides methods for retrieving
 * and creating Faculty objects
 */
@Service
public class FacultyService {

    /**
     * This is a field injection. The field `_facultyRepository` is injected with
     * the value returned by
     * the method `getFacultyRepository()`.
     */
    @Autowired
    private IFacultyRepository _facultyRepository;

    /**
     * Get all the faculties from the database
     *
     * @return A list of Faculty objects.
     */
    public List<Faculty> getAllFaculties() {
        return _facultyRepository.findAll();
    }

    /**
     * Find a faculty by id
     *
     * @param id The id of the faculty to be retrieved.
     * @return Nothing.
     */
    public Optional<Faculty> getFacultyById(Long id) {
        return _facultyRepository.findById(id);
    }

    /**
     * Create a new Faculty object and save it to the database
     *
     * @param faculty The object that you want to save.
     * @return Nothing.
     */
    public Faculty createFaculty(Faculty faculty) {
        return _facultyRepository.save(faculty);
    }
}
