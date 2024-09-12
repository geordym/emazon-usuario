package com.emazon.user.domain.enums;

import com.emazon.user.domain.model.Role;
import com.emazon.user.infraestructure.entities.RoleEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoleEnum {
    WAREHOUSE_ASSISTANT(1L, "ROLE_WAREHOUSE_ASSISTANT", "WAREHOUSE_ASSISTANT"),
    CLIENTE(2L, "ROLE_CLIENT", "CLIENT"),
    ADMINISTRADOR(3L, "ROLE_ADMINISTRATOR", "ADMINISTRATOR");


    private final Long id;
    private final String name_bd;
    private final String name;

    RoleEnum(Long id, String name_bd, String name) {
        this.id = id;
        this.name_bd = name_bd;
        this.name = name;
    }

    public Long getId() {
        return id;
    }


    public String getName_bd() {
        return name_bd;
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

    public static List<Long> getAllIds() {
        return Arrays.stream(values())
                .map(RoleEnum::getId)
                .collect(Collectors.toList());
    }

    public RoleEntity toEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(this.id);
        roleEntity.setName(this.name_bd);
        return roleEntity;
    }

    public Role toModel() {
        Role role = new Role(this.id, this.name);
        return role;
    }

}