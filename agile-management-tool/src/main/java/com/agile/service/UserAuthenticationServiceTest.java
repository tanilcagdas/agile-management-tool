
package com.agile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agile.beans.AdminUserData;
import com.agile.beans.UserData;
import com.agile.beans.security.AuthorityData;
import com.agile.repository.UserRepository;
import com.agile.repository.security.AuthorityRepository;

@Service("userAuthenticationService")
@Profile("test")
public class UserAuthenticationServiceTest implements UserDetailsService {

	@Autowired
	UserRepository<UserData, ?> userRepository;

	@Autowired
	AuthorityRepository authorityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		UserData userData = (UserData) userRepository.findOne(username);
		if (userData == null) {
			// TODO workaround
			if (username.equals("admin")) {

				String algorithm = "MD5";
				MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder(algorithm);
				Object salt = "agile";

				AuthorityData roleAdmin = new AuthorityData();
				roleAdmin.setName("ROLE_ADMIN");

				authorityRepository.save(roleAdmin);

				userData = new AdminUserData();
				userData.setUsername(username);
				// userData.setPassword("4aed16f45fba209cf74d6e86a100806b");
				userData.setPassword(encoder.encodePassword("password", salt));
				userData.setAuthority(roleAdmin);
				userRepository.save(userData);
			} else {
				throw new UsernameNotFoundException("/nUser: " + username + " is not found!/n");
			}
		}

		userDetails = (UserDetails) userData;

		return userDetails;
	}
}
