package com.emazon.usuario.infraestructure.repositories;

import com.emazon.stock.infraestructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleCrudRepositoryMySQL extends JpaRepository<RoleEntity, Long> {


}
