package com.istv.banq.repository;

import com.istv.banq.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
@Primary
public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByEmail(String email);
    public User findByName(String name);
    public User findById(int id);
}