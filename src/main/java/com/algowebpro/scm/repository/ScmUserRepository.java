package com.algowebpro.scm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algowebpro.scm.entity.ScmUser;

public interface ScmUserRepository extends JpaRepository<ScmUser,String>{
	
	Optional<ScmUser> findByEmail(String email);
    boolean existsByEmail(String email);

}
