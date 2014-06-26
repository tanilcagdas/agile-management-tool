
package com.agile.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agile.beans.security.AuthorityData;

public interface AuthorityRepository extends JpaRepository<AuthorityData, String> {

   @Query(value = "SELECT EXISTS (SELECT * FROM SECURITY_AUTHORITY a WHERE a.name = ?1 AND a.name <> 'ROLE_CLIENT')", nativeQuery = true)
   boolean checkIfAuthorityExists(String name);

}
