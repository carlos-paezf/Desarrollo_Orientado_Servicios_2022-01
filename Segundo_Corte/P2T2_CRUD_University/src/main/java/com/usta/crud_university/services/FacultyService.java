package com.usta.crud_university.services;

import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.Faculty;
import com.usta.crud_university.repositories.IFacultyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is used to create, retrieve, update, and delete Faculty objects
 * from the database
 * 
 * @author Carlos Páez
 */
@Service
public class FacultyService {
    /**
     * This is a field injection. The field `_facultyRepository` is injected with
     * the value returned by the method `getFacultyRepository()`.
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
     * Get a faculty by id
     * 
     * @param id The id of the faculty to be retrieved.
     * @return The Faculty Object that matches the id.
     */
    public Optional<Faculty> getFacultyById(long id) {
        return _facultyRepository.findById(id);
    }

    /**
     * Create a new Faculty object and save it to the database
     * 
     * @param faculty The object that will be saved to the database.
     * @return Teh Faculty object that was saved.
     */
    public Faculty createFaculty(Faculty faculty) {
        return _facultyRepository.save(faculty);
    }

    /**
     * Delete a faculty by id
     * 
     * @param id The id of the faculty to be deleted.
     */
    public void deleteFacultyById(long id) {
        _facultyRepository.deleteById(id);
    }
}
