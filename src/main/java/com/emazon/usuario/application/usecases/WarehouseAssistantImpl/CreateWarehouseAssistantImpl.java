package com.emazon.usuario.application.usecases.WarehouseAssistantImpl;


import com.emazon.usuario.application.services.UserService;
import com.emazon.usuario.domain.model.WarehouseAssistant;
import com.emazon.usuario.domain.ports.in.CreateWarehouseAssistant;
import com.emazon.usuario.domain.ports.out.WarehouseAssistantRepositoryPort;
import com.emazon.usuario.infraestructure.factories.UserFactory;
import lombok.RequiredArgsConstructor;
import com.emazon.usuario.domain.model.User;

@RequiredArgsConstructor
public class CreateWarehouseAssistantImpl implements CreateWarehouseAssistant {

    private final WarehouseAssistantRepositoryPort warehouseAssistantRepositoryPort;
    private final UserService userService;

    @Override
    public WarehouseAssistant createWarehouseAssistant(WarehouseAssistant warehouseAssistant) {

        User userCreated = userService.createUser(UserFactory.createUserWarehouseAssistant(warehouseAssistant.getUser().getUsername(), warehouseAssistant.getUser().getPassword()));
        warehouseAssistant.setUser(userCreated);
        return warehouseAssistantRepositoryPort.createWarehouseAssistant(warehouseAssistant);
    }


}
