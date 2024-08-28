package com.emazon.user.infraestructure.enums;

import com.emazon.user.infraestructure.entities.RoleEntity;

import java.util.Arrays;
import java.util.List;

public enum Role {
    AUX_BODEGA(1L, "AUX_BODEGA"),
    USUARIO(2L, "USUARIO");

    private final Long id;
    private final String name;

    Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Método para obtener un Role por su nombre
    public static Role fromName(String name) {
        for (Role role : Role.values()) {
            if (role.getName().equalsIgnoreCase(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No se encontró el rol con el nombre: " + name);
    }

    // Método para obtener un Role por su ID
    public static Role fromId(int id) {
        for (Role role : Role.values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("No se encontró el rol con el ID: " + id);
    }

    public static List<Role> getAllRoles() {
        return Arrays.asList(Role.values());
    }

    public RoleEntity toEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(this.id);
        roleEntity.setName(this.name);
        return roleEntity;
    }
}