package com.emazon.usuario.infraestructure.factories;


import com.emazon.usuario.domain.model.User;
import com.emazon.usuario.domain.model.WarehouseAssistant;
import com.emazon.usuario.infraestructure.rest.dto.request.WarehouseAssistant.CreateWarehouseAssistantRequestDTO;

public class WarehouseAssistantFactory {


    // Método para crear un WarehouseAssistant a partir de un DTO
    public static WarehouseAssistant createWarehouseAssistant(CreateWarehouseAssistantRequestDTO dto, User user) {
        WarehouseAssistant warehouseAssistant = new WarehouseAssistant();
        warehouseAssistant.setFirstName(dto.getFirstName());
        warehouseAssistant.setLastName(dto.getLastName());
        warehouseAssistant.setIdentityDocument(dto.getIdentityDocument());
        warehouseAssistant.setPhoneNumber(dto.getPhoneNumber());
        warehouseAssistant.setEmail(dto.getEmail());
        warehouseAssistant.setBirthDate(dto.getBirthDate());
        warehouseAssistant.setUser(user);

        return warehouseAssistant;
    }


}
