package com.usta.users_alerts.services;

import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.AlertEntity;
import com.usta.users_alerts.repositories.IAlertRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The AlertService class is a service class that provides methods to retrieve
 * and create alerts
 * 
 * @author Carlos Páez
 */
@Service
public class AlertService {
    /**
     * This is a way to inject the dependency of the AlertRepository into the
     * AlertService class.
     */
    @Autowired
    private IAlertRepository _alertRepository;

    /**
     * Get all alerts from the database
     * 
     * @return A list of AlertEntity objects.
     */
    public List<AlertEntity> getAllAlerts() {
        return _alertRepository.findAll();
    }

    /**
     * Get an alert by its id
     * 
     * @param id The id of the alert to be retrieved.
     * @return An Optional<AlertEntity>
     */
    public Optional<AlertEntity> getAlertById(Long id) {
        return _alertRepository.findById(id);
    }

    /**
     * Count the total number of alert records in the database
     * 
     * @return The count of total alert records.
     */
    public Integer countTotalAlertRecords() {
        return _alertRepository.countTotalAlertRecords();
    }

    /**
     * Create an alert
     * 
     * @param alert The alert entity to be saved.
     * @return The saved alert.
     */
    public AlertEntity createAlert(AlertEntity alert) {
        return _alertRepository.save(alert);
    }

    /**
     * Update an alert
     * 
     * @param alert The alert to be updated.
     * @return The updated alert.
     */
    public AlertEntity updateAlert(AlertEntity alert) {
        return _alertRepository.save(alert);
    }

    /**
     * Delete an alert from the database
     * 
     * @param id The id of the alert to delete.
     */
    public void deleteAlert(Long id) {
        _alertRepository.deleteById(id);
    }
}
