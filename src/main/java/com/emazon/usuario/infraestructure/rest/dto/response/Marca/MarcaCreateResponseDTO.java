package com.emazon.usuario.infraestructure.rest.dto.response.Marca;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaCreateResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;

    
    
}
