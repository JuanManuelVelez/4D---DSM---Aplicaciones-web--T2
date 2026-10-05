package mx.edu.utez.proyecto2D.controller.dto;

import lombok.Data;

@Data
public class ResponseHospedajeDTO {

    private String nombreHuesped;
    private String tipoHabitacion;
    private Integer numeroNoches;
    private Double subtotal;
    private Double impuesto;
    private Double total;
}