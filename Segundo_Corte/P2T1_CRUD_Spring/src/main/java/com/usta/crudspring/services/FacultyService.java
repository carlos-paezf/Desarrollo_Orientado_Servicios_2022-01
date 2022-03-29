package com.usta.crudspring.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.usta.crudspring.models.Faculty;
import com.usta.crudspring.repositories.IFacultyRepository;


/**
 * The `FacultyService` class is a Spring `@Service` class that has a field injection of the
 * `IFacultyRepository` interface. The `getAllFaculties()` method returns a list of `Faculty` objects
 */
@Service
public class FacultyService {

    /** 
     * This is a field injection. The field `_facultyRepository` is injected with the value returned by
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
}
