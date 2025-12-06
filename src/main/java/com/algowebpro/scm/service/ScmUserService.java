package com.algowebpro.scm.service;

import java.util.Optional;

import com.algowebpro.scm.entity.ScmUser;

public interface ScmUserService {
	

    ScmUser saveScmUser(ScmUser scmUser);

    Optional<ScmUser> getScmUserById(String scmUserId);

    ScmUser updateUser(String id, ScmUser scmUser);

    void deleteUser(String id);


	ScmUser registerUser(ScmUser scmUser);
    
    boolean emailExists(String email);
    
    Optional<ScmUser> findByEmail(String email);
}
