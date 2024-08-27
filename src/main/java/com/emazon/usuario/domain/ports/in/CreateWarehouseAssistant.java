package com.emazon.usuario.domain.ports.in;


import com.emazon.usuario.domain.model.WarehouseAssistant;

public interface CreateWarehouseAssistant {
    WarehouseAssistant createWarehouseAssistant(WarehouseAssistant warehouseAssistant);
}
