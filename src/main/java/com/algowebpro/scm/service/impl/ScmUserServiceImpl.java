package com.algowebpro.scm.service.impl;

import java.util.Optional;
import java.util.UUID;

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

		String userId = UUID.randomUUID().toString();
		scmUser.setUserId(userId);

		// Provide safe defaults
		if (scmUser.getProfilePic() == null) {
			scmUser.setProfilePic("default.png");
		}



		return scmUserRepository.save(scmUser);
	}

	@Override
	public Optional<ScmUser> getScmUserById(String scmUserId) {

		return scmUserRepository.findById(scmUserId);
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

	@Override
	public ScmUser updateUser(String id, ScmUser scmUser) {
		ScmUser existingUser = scmUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScmUser not found"));

        // Update fields (example)
        existingUser.setName(scmUser.getName());
        existingUser.setEmail(scmUser.getEmail());
        //existingUser.setPhone(scmUser.getPhone());
        //existingUser.setRole(scmUser.getRole());
        // Add any other fields that should be updated

        return scmUserRepository.save(existingUser);
	}

	@Override
	public void deleteUser(String id) {

		if (!scmUserRepository.existsById(id)) {
            throw new RuntimeException("ScmUser not found");
        }
        scmUserRepository.deleteById(id);
	}





}
