package com.emazon.usuario.infraestructure.adapters;

import com.emazon.stock.domain.puertos.out.RoleRepositoryPort;
import com.emazon.stock.infraestructure.repositories.RoleCrudRepositoryMySQL;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleRepositoryMySQLAdapter implements RoleRepositoryPort {


    private final RoleCrudRepositoryMySQL roleCrudRepositoryMySQL;
    @Override
    public boolean existsRolById(Long id) {
        return roleCrudRepositoryMySQL.existsById(id);
    }


}
