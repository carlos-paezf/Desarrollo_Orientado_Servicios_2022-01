package com.usta.crudspring.repositories;


import com.usta.crudspring.models.Faculty;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Extending the JpaRepository interface and declaring the Faculty class as the entity.
 */
public interface IFacultyRepository extends JpaRepository<Faculty, Long> {
}
