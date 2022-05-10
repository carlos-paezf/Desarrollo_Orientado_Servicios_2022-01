package com.usta.p2t5_jwt_db.services;

import java.util.List;
import java.util.Optional;

import com.usta.p2t5_jwt_db.entities.UserEntity;
import com.usta.p2t5_jwt_db.repositories.IUserEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Páez
 */
@Service
public class UserEntityService {
    @Autowired
    private IUserEntityRepository _userEntityRepository;

    public List<UserEntity> getAllUsers() {
        return _userEntityRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return _userEntityRepository.findById(id);
    }

    public UserEntity getUserByUsername(String username) {
        return _userEntityRepository.findByUsername(username);
    }

    public UserEntity createUser(UserEntity user) {
        return _userEntityRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user) {
        return _userEntityRepository.save(user);
    }

    public void deleteUser(Long id) {
        _userEntityRepository.deleteById(id);
    }
}
