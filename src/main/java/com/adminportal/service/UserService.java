package com.adminportal.service;

import com.adminportal.domain.User;
import com.adminportal.domain.security.PasswordResetToken;
import com.adminportal.domain.security.UserRole;

import java.util.Set;

/**
 * Created by User on 16.11.2017.
 */
public interface UserService {

    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User save(User user) throws Exception;
}
