package com.algowebpro.ums.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algowebpro.ums.entity.Role;
import com.algowebpro.ums.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, UUID> {

	Optional<Role> findByName(RoleName name);

	Boolean existsByName(RoleName name);

}
