package com.emazon.user.infraestructure.repositories;

import com.emazon.user.infraestructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleCrudRepositoryMySQL extends JpaRepository<RoleEntity, Long> {


}
