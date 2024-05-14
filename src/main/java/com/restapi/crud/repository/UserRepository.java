package com.restapi.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.crud.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
