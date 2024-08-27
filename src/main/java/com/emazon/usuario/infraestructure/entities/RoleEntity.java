package com.emazon.usuario.infraestructure.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Constructor vacío
    public RoleEntity() {
    }

    // Constructor con parámetros
    public RoleEntity(String name) {
        this.name = name;
    }


}