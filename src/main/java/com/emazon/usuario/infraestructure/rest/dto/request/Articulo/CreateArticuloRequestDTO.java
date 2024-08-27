package com.emazon.usuario.infraestructure.rest.dto.request.Articulo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticuloRequestDTO {

    public Long id_categories[];
    public String name;
    public String description;
    public Integer quantity;
    public Double price;

}
