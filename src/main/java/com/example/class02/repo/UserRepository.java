package com.example.class02.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.class02.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

	List<AppUser> findAll();

}