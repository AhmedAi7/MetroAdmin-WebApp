package com.javacode.Reporsitory;

import com.javacode.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsername(String username);
}