package com.hoaxify.hoaxifybackend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
    private boolean success = true;
    private String message = "";
}
