package com.usta.crud_university.services;

import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.Professor;
import com.usta.crud_university.repositories.IProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is used to create, retrieve, update, and delete professors from
 * the database.
 * 
 * @author Carlos Páez
 */
@Service
public class ProfessorService {
    /**
     * This is a way to inject the professorRepository into the ProfessorService
     * class.
     */
    @Autowired
    private IProfessorRepository _professorRepository;

    /**
     * Get all the professors from the database
     * 
     * @return A list of Professor objects.
     */
    public List<Professor> getAllProfessors() {
        return _professorRepository.findAll();
    }

    /**
     * Get a professor by id
     * 
     * @param id The id of the professor we want to retrieve.
     * @return The Professor object that matches the id.
     */
    public Optional<Professor> getProfessorById(long id) {
        return _professorRepository.findById(id);
    }

    /**
     * Create a new professor
     * 
     * @param professor The professor object that will be saved to the database.
     * @return The Professor object that was saved.
     */
    public Professor createProfessor(Professor professor) {
        return _professorRepository.save(professor);
    }

    /**
     * Delete a professor by id
     * 
     * @param id The id of the professor to be deleted.
     */
    public void deleteProfessorById(long id) {
        _professorRepository.deleteById(id);
    }
}
