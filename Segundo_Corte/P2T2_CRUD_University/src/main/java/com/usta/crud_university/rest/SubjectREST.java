package com.usta.crud_university.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.Subject;
import com.usta.crud_university.services.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Get all subject, get a subject by id, and create a new subject
 * 
 * @author Carlos Páez
 */
@RestController
@RequestMapping(value = "/api/subject")
public class SubjectREST {
    /**
     * This is a field injection. The field `_subjectService` is injected with the
     * value of `subjectService` from the `@Autowired` constructor.
     */
    @Autowired
    private SubjectService _subjectService;

    /**
     * Get all subjects
     * 
     * @return A list of subjects.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(_subjectService.getAllSubjects());
    }

    /**
     * Get a subject by id
     * 
     * @param id The id of the subject to be retrieved.
     * @return The subject with the given id.
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<Subject>> getSubjectById(@PathVariable("id") long id) {
        return ResponseEntity.ok(_subjectService.getSubjectById(id));
    }

    /**
     * Create a new subject
     * 
     * @param subject The subject to be created.
     * @return The URI of the newly created resource.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject temp = _subjectService.createSubject(subject);
        try {
            return ResponseEntity.created(new URI("/api/subject" + temp.getSubjectId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
