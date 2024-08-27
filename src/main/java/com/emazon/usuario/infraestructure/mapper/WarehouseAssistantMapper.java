package com.emazon.usuario.infraestructure.mapper;

import com.emazon.usuario.domain.model.User;
import com.emazon.usuario.domain.model.WarehouseAssistant;
import com.emazon.usuario.infraestructure.entities.WarehouseAssistantEntity;
import com.emazon.usuario.infraestructure.factories.UserFactory;
import com.emazon.usuario.infraestructure.factories.WarehouseAssistantFactory;
import com.emazon.usuario.infraestructure.rest.dto.request.WarehouseAssistant.CreateWarehouseAssistantRequestDTO;

public class WarehouseAssistantMapper {



    public static WarehouseAssistant dtoToDomain(CreateWarehouseAssistantRequestDTO createWarehouseAssistantRequestDTO){
        User user = UserFactory.createUserWarehouseAssistant(createWarehouseAssistantRequestDTO.getEmail(), createWarehouseAssistantRequestDTO.getPassword());
        WarehouseAssistant warehouseAssistant = WarehouseAssistantFactory.createWarehouseAssistant(createWarehouseAssistantRequestDTO, user);
        return warehouseAssistant;
    }

    public static WarehouseAssistantEntity domainToEntity(WarehouseAssistant warehouseAssistant){
        return new WarehouseAssistantEntity(warehouseAssistant.getId(), warehouseAssistant.getFirstName(), warehouseAssistant.getLastName(), warehouseAssistant.getIdentityDocument(), warehouseAssistant.getPhoneNumber(), warehouseAssistant.getEmail(), warehouseAssistant.getBirthDate(), UserMapper.domainToEntity(warehouseAssistant.getUser()));
    }

    public static WarehouseAssistant entityToDomain(WarehouseAssistantEntity warehouseAssistantEntity){
        return new WarehouseAssistant(
                warehouseAssistantEntity.getId(),
                warehouseAssistantEntity.getFirstName(),
                warehouseAssistantEntity.getLastName(),
                warehouseAssistantEntity.getIdentityDocument(),
                warehouseAssistantEntity.getPhoneNumber(),
                warehouseAssistantEntity.getEmail(),
                warehouseAssistantEntity.getBirthDate(),
                UserMapper.entityToDomain(warehouseAssistantEntity.getUser())
                );
    }




}
