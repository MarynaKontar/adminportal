package com.adminportal.service.impl;

import com.adminportal.domain.User;
import com.adminportal.domain.security.PasswordResetToken;
import com.adminportal.domain.security.UserRole;
import com.adminportal.repository.PasswordResetTokenRepository;
import com.adminportal.repository.RoleRepository;
import com.adminportal.repository.UserRepository;
import com.adminportal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by User on 16.11.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            LOGGER.info("User with username {} already exists. Nothing will be done", user.getUsername());
//            throw new Exception("User with username " + user.getUsername() + "already exists. Nothing will be done");
        } else {
            userRoles.forEach(userRole -> roleRepository.save(userRole.getRole()));
            user.getUserRoles().addAll(userRoles);
            localUser = userRepository.save(user);
        }
        return localUser;
    }

    @Override
    @Transactional
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }
}


