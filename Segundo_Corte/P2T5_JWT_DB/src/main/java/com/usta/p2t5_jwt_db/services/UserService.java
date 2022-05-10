package com.usta.p2t5_jwt_db.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.usta.p2t5_jwt_db.entities.UserEntity;
import com.usta.p2t5_jwt_db.repositories.IUserEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Carlos Páez
 */
@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private IUserEntityRepository _userEntityRepository;
    
    /**
     * It returns a User object with the username, password, and a list of
     * authorities
     * 
     * @param username The username of the user.
     * @return A User object.
     */
    @Override
    public UserDetails loadUserByUsername(@RequestBody String username) throws UsernameNotFoundException {
        UserEntity user = _userEntityRepository.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
