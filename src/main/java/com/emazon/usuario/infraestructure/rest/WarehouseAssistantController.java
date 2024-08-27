package com.emazon.usuario.infraestructure.rest;


import com.emazon.usuario.application.services.WarehouseAssistantService;
import com.emazon.usuario.domain.model.WarehouseAssistant;
import com.emazon.usuario.infraestructure.mapper.WarehouseAssistantMapper;
import com.emazon.usuario.infraestructure.rest.dto.request.WarehouseAssistant.CreateWarehouseAssistantRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/warehouse-assistants")
public class WarehouseAssistantController {

    private final WarehouseAssistantService warehouseAssistantService;

    @PostMapping
    public ResponseEntity<String> createWarehouseAssistant(@RequestBody CreateWarehouseAssistantRequestDTO createWarehouseAssistantRequestDTO){


        warehouseAssistantService.createWarehouseAssistant(WarehouseAssistantMapper.dtoToDomain(createWarehouseAssistantRequestDTO));
        return new ResponseEntity<>("YAY", HttpStatus.OK);
    }


}
