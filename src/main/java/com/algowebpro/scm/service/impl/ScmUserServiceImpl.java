package com.algowebpro.scm.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algowebpro.scm.entity.ScmUser;
import com.algowebpro.scm.repository.ScmUserRepository;
import com.algowebpro.scm.service.ScmUserService;
@Service
public class ScmUserServiceImpl implements ScmUserService{
	
	@Autowired
    private ScmUserRepository scmUserRepository;

    @Override
    public ScmUser saveScmUser(ScmUser scmUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveScmUser'");
    }

    @Override
    public ScmUser getScmUserById(String ScmUserId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScmUserById'");
    }

	@Override
	public ScmUser registerUser(ScmUser scmUser) {
		 // Encode password
		//scmUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return scmUserRepository.save(scmUser);
	}

	@Override
	public boolean emailExists(String email) {
		return scmUserRepository.existsByEmail(email);
	}

	@Override
	public Optional<ScmUser> findByEmail(String email) {
		return scmUserRepository.findByEmail(email);
	}

}
