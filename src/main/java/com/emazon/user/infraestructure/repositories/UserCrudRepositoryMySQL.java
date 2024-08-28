package com.emazon.usuario.infraestructure.repositories;

import com.emazon.stock.infraestructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCrudRepositoryMySQL extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);

}
