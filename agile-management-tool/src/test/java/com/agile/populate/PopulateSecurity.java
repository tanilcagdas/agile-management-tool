package com.agile.populate;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.AdminUserData;
import com.agile.beans.security.AuthorityData;
import com.agile.repository.UserRepository;
import com.agile.repository.security.AuthorityRepository;

public class PopulateSecurity extends TestCase {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
	private AuthorityRepository authRep;
	private UserRepository userRepo;
	private AdminUserData adminUser;
	

	@Test
	@Transactional
	public void test() {
		
		
		
		try {
			String algorithm = "MD5";
			MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder(algorithm);
			Object salt = "agile";
			
		userRepo = context.getBean(UserRepository.class);
		authRep= context.getBean(AuthorityRepository.class);
		
		AuthorityData notAssigned = new AuthorityData();
		notAssigned.setName("NOT_ASSIGNED");
		AuthorityData roleClient = new AuthorityData();
		roleClient.setName("ROLE_CLIENT");
		AuthorityData roleSuper = new AuthorityData();
		roleSuper.setName("ROLE_SUPER");
		AuthorityData roleAdmin = new AuthorityData();
		roleAdmin.setName("ROLE_ADMIN");

		authRep.save(notAssigned);
		authRep.save(roleClient);
		authRep.save(roleAdmin);
		authRep.save(roleSuper);
		
		adminUser = new AdminUserData();
		adminUser.setUsername("admin");
		adminUser.setPassword(encoder.encodePassword("password", salt ));
		adminUser.setAuthority(roleAdmin);
		
		userRepo.saveAndFlush(adminUser);
		
		} catch (Exception e) {
			fail();
		}
		
		
	}

}
