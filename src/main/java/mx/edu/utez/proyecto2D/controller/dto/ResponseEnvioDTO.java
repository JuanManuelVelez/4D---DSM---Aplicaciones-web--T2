package mx.edu.utez.proyecto2D.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEnvioDTO {

    private String codigoPostal;
    private Double pesoKg;
    private String tipoEnvio;
    private Double volumenCm3;
    private Double costoTotal;
}