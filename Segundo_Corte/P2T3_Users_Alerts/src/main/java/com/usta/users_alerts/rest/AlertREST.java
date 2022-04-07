package com.usta.users_alerts.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.AlertEntity;
import com.usta.users_alerts.services.AlertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a Spring MVC controller that exposes REST endpoints for the
 * AlertService
 *
 * @author Carlos Páez
 */
@RestController
@RequestMapping(value = "/api/alerts")
public class AlertREST {
    /**
     * This is a way to inject the AlertService into the AlertREST class.
     */
    @Autowired
    private AlertService _alertService;

    /**
     * Get all alerts
     * 
     * @return A list of all alerts.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<AlertEntity>> getAllAlerts() {
        return ResponseEntity.ok(_alertService.getAllAlerts());
    }

    /**
     * Get an alert by its id
     * 
     * @param id The id of the alert to be retrieved.
     * @return The AlertEntity
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<AlertEntity>> getAlertById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_alertService.getAlertById(id));
    }

    /**
     * `@GetMapping("/total-records")`
     * 
     * This is a Spring MVC annotation that tells Spring MVC to map this function to
     * the URL "/total-records"
     * 
     * @return The count of total alert records.
     */
    @GetMapping(value = "/total-records")
    private ResponseEntity<Integer> countTotalAlertRecords() {
        return ResponseEntity.ok(_alertService.countTotalAlertRecords());
    }

    /**
     * Create an alert
     * 
     * @param alert The alert entity that will be created.
     * @return The URI of the newly created alert.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<AlertEntity> createAlert(@RequestBody AlertEntity alert) {
        AlertEntity temp = _alertService.createAlert(alert);
        try {
            return ResponseEntity.created(new URI("/api/alerts" + temp.getAlertId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update an alert
     * 
     * @param alert The alert entity that will be updated.
     * @return The updated alert.
     */
    @PutMapping(value = "/update")
    private ResponseEntity<AlertEntity> updateAlert(@RequestBody AlertEntity alert) {
        AlertEntity temp = _alertService.updateAlert(alert);
        try {
            return ResponseEntity.created(new URI("/api/alerts" + temp.getAlertId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * `@DeleteMapping("/delete/{id}")`
     * 
     * This is a Spring MVC annotation that tells Spring MVC to map this function to
     * the URL `/delete/{id}`.
     * 
     * The `@PathVariable("id")` annotation tells Spring MVC to pass the value of
     * the `id` parameter to the function.
     * 
     * The `return` statement tells Spring MVC what to return to the browser.
     * 
     * @param id The id of the alert to delete.
     * @return The HTTP status code and the resource.
     */
    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Void> deleteAlert(@PathVariable("id") Long id) {
        _alertService.deleteAlert(id);
        return ResponseEntity.ok().build();
    }
}
