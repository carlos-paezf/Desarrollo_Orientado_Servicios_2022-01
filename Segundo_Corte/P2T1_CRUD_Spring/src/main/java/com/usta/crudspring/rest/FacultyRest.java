package com.usta.crudspring.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.usta.crudspring.models.Faculty;
import com.usta.crudspring.services.FacultyService;



@RestController
@RequestMapping("/api/faculties/")
public class FacultyRest {

    /** 
     * This is a injection of FacultyService.
     */
    @Autowired
    private FacultyService _facultyService;


    /**
     * Get all faculties from the database
     * 
     * @return A list of Faculty objects.
     */
    @GetMapping("get-all-faculties")
    private ResponseEntity<List<Faculty>> getAllFaculties() {
        // try {
        //     return ResponseEntity.ok(_facultyService.getAllFaculties());
        // } catch (Exception e) {
        //     return ResponseEntity.badRequest().body(null);
        // }
        return ResponseEntity.ok(_facultyService.getAllFaculties());
    }
}
