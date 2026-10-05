package mx.edu.utez.proyecto2D.controller.dto;

import lombok.Data;

@Data
public class ResponseRentaDTO {

    private String nombreCliente;
    private String tipoVehiculo;
    private Integer diasRenta;
    private Double importeTotal;
}