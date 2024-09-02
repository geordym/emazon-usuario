package com.emazon.user.infraestructure.repositories;

import com.emazon.user.infraestructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCrudRepositoryMySQL extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
    @Query("SELECT u FROM UserEntity u WHERE u.id = :clientId AND u.role.id = :rolClientId")
    Optional<UserEntity> findClientById(Long rolClientId, Long clientId);

    boolean existsByIdentityDocument(String identityDocument);

    @Query("SELECT u FROM UserEntity u WHERE u.role.id = :idRole")
    List<UserEntity> findAllByIdRole(@Param("idRole") Long idRole);
}
