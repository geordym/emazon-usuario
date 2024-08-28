package com.emazon.user.infraestructure.enums;

import com.emazon.user.infraestructure.entities.RoleEntity;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    AUX_BODEGA(1L, "AUX_BODEGA"),
    USUARIO(2L, "USUARIO");

    private final Long id;
    private final String name;

    RoleEnum(Long id, String name) {
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
    public static RoleEnum fromName(String name) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getName().equalsIgnoreCase(name)) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("No se encontró el rol con el nombre: " + name);
    }

    // Método para obtener un Role por su ID
    public static RoleEnum fromId(Long id) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getId() == id) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("No se encontró el rol con el ID: " + id);
    }

    public static List<RoleEnum> getAllRoles() {
        return Arrays.asList(RoleEnum.values());
    }

    public RoleEntity toEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(this.id);
        roleEntity.setName(this.name);
        return roleEntity;
    }
}