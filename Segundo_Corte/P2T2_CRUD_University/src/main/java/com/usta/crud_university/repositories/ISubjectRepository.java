package com.usta.crud_university.repositories;

import com.usta.crud_university.models.Subject;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is a simple interface that extends the JpaRepository interface.
 * 
 * @author Carlos Páez
 */
public interface ISubjectRepository extends JpaRepository<Subject, Long> {    
}
