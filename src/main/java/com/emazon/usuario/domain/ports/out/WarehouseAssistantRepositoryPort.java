package com.emazon.usuario.domain.ports.out;


import com.emazon.usuario.domain.model.WarehouseAssistant;

public interface WarehouseAssistantRepositoryPort {
    WarehouseAssistant createWarehouseAssistant(WarehouseAssistant warehouseAssistant);
}
