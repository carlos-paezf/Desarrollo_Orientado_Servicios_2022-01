package com.usta.crudspring.repositories;


import com.usta.crudspring.models.Professor;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Extending the JpaRepository interface and adding the Professor model and the Long data type.
 */
public interface IProfessorRepository extends JpaRepository<Professor, Long> {
}
