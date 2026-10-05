package mx.edu.utez.proyecto2D.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RequestRentaDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria")
    @Positive(message = "La edad debe ser mayor a 0")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    private String tipoVehiculo;

    @NotNull(message = "Los días de renta son obligatorios")
    @Positive(message = "Los días de renta deben ser mayores a 0")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios")
    @PositiveOrZero(message = "Los kilómetros no pueden ser negativos")
    private Integer kilometrosEstimados;

    @NotNull(message = "Debes indicar si contratas seguro completo")
    private Boolean seguroCompleto;
}