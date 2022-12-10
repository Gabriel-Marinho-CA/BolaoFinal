package com.otaviolube.bolao.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
    @Query(value = "select * from usuarios where email = :email and password = :password", nativeQuery = true)
    public UserModel Login(String email, String password);
}