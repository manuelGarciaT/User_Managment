package com.example.class02.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.class02.model.AppUser;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    List<AppUser> findAll();

}