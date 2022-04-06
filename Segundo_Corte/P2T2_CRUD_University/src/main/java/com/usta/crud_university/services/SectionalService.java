package com.usta.crud_university.services;

import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.Sectional;
import com.usta.crud_university.repositories.ISectionalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for all the business logic of the University
 * microservice.
 * 
 * @author Carlos Páez
 */
@Service
public class SectionalService {
    /**
     * This is injecting the SectionalRepository into the SectionalService.
     */
    @Autowired
    private ISectionalRepository _sectionalRepository;

    /**
     * Get all the sections in the database
     * 
     * @return A list of Sectional objects.
     */
    public List<Sectional> getAllSectional() {
        return _sectionalRepository.findAll();
    }

    /**
     * Get a Sectional by its id
     * 
     * @param id The id of the Sectional to be retrieved.
     * @return The sectional that matches the id.
     */
    public Optional<Sectional> getSectionalById(long id) {
        return _sectionalRepository.findById(id);
    }

    /**
     * Create a new Sectional object and save it to the database
     * 
     * @param sectional The Sectional object to be saved.
     * @return The Sectional object that was saved;
     */
    public Sectional createSectional(Sectional sectional) {
        return _sectionalRepository.save(sectional);
    }

    /**
     * Delete a Sectional by id
     * 
     * @param id The id of the Sectional to delete.
     */
    public void deleteSectionalById(long id) {
        _sectionalRepository.deleteById(id);
    }

    /**
     * Count the total number of records in the sectional table
     * 
     * @return The total number of records in the Sectional table.
     */
    public Integer countTotalSectionalRecords() {
        return _sectionalRepository.countTotalSectionalRecords();
    }
}
