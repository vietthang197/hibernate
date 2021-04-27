package com.thanglv.hibernate.repository;

import com.thanglv.hibernate.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author thanglv on 4/27/2021
 * @project hibernate
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
