package mx.edu.utez.proyecto2D.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RequestHospedajeDTO {

    @NotBlank(message = "El nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    private String tipoHabitacion;

    @NotNull(message = "El número de noches es obligatorio")
    @Positive(message = "El número de noches debe ser mayor a 0")
    private Integer numeroNoches;

    @NotNull(message = "El número de huéspedes es obligatorio")
    @Positive(message = "El número de huéspedes debe ser mayor a 0")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    @NotNull(message = "Debes indicar si incluye desayuno")
    private Boolean incluyeDesayuno;

    @NotNull(message = "Debes indicar si incluye estacionamiento")
    private Boolean incluyeEstacionamiento;
}