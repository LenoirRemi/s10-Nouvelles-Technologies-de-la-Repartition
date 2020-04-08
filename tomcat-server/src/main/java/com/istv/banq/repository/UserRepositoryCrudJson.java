package com.istv.banq.repository;

import com.istv.banq.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepositoryCrudJson extends CrudRepository<User, Integer> {
    User findByName(@Param("name") String name);
}