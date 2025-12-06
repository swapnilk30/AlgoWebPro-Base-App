package com.algowebpro.scm.service;

import java.util.Optional;

import com.algowebpro.scm.entity.ScmUser;

public interface ScmUserService {
	
	ScmUser registerUser(ScmUser scmUser);

    ScmUser saveScmUser(ScmUser scmUser);

    ScmUser getScmUserById(String ScmUserId);
    
    boolean emailExists(String email);
    
    Optional<ScmUser> findByEmail(String email);
}
