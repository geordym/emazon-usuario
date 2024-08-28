package com.emazon.user.infraestructure.repositories;

import com.emazon.user.infraestructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrudRepositoryMySQL extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    boolean existsByIdentityDocument(String identityDocument);

}
