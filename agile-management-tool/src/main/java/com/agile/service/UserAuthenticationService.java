
package com.agile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agile.beans.UserData;
import com.agile.repository.UserRepository;

@Service("userAuthenticationService")
@Profile("live")
public class UserAuthenticationService implements UserDetailsService {


  @Autowired
   UserRepository<UserData, ?>       userRepository;



   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserDetails userDetails = null;
      UserData userData = (UserData) userRepository.findOne(username);
      if (userData == null) {
         throw new UsernameNotFoundException("/nUser: " + username + " is not found!/n");
      }
      
      userDetails = (UserDetails) userData;

      return userDetails;
   }
}
