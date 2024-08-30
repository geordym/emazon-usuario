package com.emazon.user.infraestructure.seeder;

import com.emazon.user.domain.ports.out.RoleRepositoryPort;
import com.emazon.user.infraestructure.entities.RoleEntity;
import com.emazon.user.infraestructure.enums.RoleEnum;
import com.emazon.user.infraestructure.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner, Ordered {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public void run(String... args) throws Exception {
        List<RoleEntity> rolesEntitiesList = RoleEnum.getAllRoles().stream().map(roleEnum -> roleEnum.toEntity()).toList();
        List<RoleEntity> roleEntitiesFilter = rolesEntitiesList.stream().filter(roleEntity -> !roleRepositoryPort.existsRolById(roleEntity.getId())).collect(Collectors.toList());

        roleEntitiesFilter.stream().forEach(roleEntity -> {
            System.out.println("The role with name: " + roleEntity.getName() + " doesnot exist in bd then will be created");
            roleRepositoryPort.saveRole(RoleMapper.entityToDomain(roleEntity));
        });

    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
