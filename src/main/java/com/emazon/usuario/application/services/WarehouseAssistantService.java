package com.emazon.usuario.application.services;


import com.emazon.usuario.domain.model.WarehouseAssistant;
import com.emazon.usuario.domain.ports.in.CreateWarehouseAssistant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarehouseAssistantService implements CreateWarehouseAssistant {

    private final CreateWarehouseAssistant createWarehouseAssistant;

    @Transactional
    @Override
    public WarehouseAssistant createWarehouseAssistant(WarehouseAssistant warehouseAssistant) {
        return createWarehouseAssistant.createWarehouseAssistant(warehouseAssistant);
    }


}
