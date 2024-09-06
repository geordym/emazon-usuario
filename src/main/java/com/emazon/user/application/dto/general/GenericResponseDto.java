package com.emazon.user.application.dto.general;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GenericResponseDto {
    private String message;
    private String timestamps;
}
