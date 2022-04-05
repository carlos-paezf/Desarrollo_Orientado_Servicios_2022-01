package com.usta.crudspring.services;

import java.util.List;
import java.util.Optional;

import com.usta.crudspring.models.Professor;
import com.usta.crudspring.repositories.IProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    /**
     * This is a way to inject the dependency of the `IProfessorRepository`
     * interface.
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
     * @return A Professor object.
     */
    public Optional<Professor> getProfessorById(Long id) {
        return _professorRepository.findById(id);
    }

    /**
     * Create a new professor
     *
     * @param professor The professor object that you want to create.
     * @return The Professor object that was saved.
     */
    public Professor createProfessor(Professor professor) {
        return _professorRepository.save(professor);
    }
}