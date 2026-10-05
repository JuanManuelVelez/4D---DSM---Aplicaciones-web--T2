package mx.edu.utez.proyecto2D.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RequestEnvioDTO {

    @NotBlank(message = "El código postal es obligatorio")
    private String codigoPostal;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a 0")
    private Double pesoKg;

    @NotNull(message = "El largo es obligatorio")
    @Positive(message = "El largo debe ser mayor a 0")
    private Double largoCm;

    @NotNull(message = "El ancho es obligatorio")
    @Positive(message = "El ancho debe ser mayor a 0")
    private Double anchoCm;

    @NotNull(message = "El alto es obligatorio")
    @Positive(message = "El alto debe ser mayor a 0")
    private Double altoCm;

    @NotBlank(message = "El tipo de envío es obligatorio")
    private String tipoEnvio;

    @NotNull(message = "El valor declarado es obligatorio")
    @PositiveOrZero(message = "El valor declarado no puede ser negativo")
    private Double valorDeclarado;
}