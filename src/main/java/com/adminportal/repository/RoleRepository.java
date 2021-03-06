package com.adminportal.repository;

import com.adminportal.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 21.11.2017.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
