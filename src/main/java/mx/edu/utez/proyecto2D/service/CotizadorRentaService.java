package mx.edu.utez.proyecto2D.service;

import mx.edu.utez.proyecto2D.controller.dto.RequestRentaDTO;
import mx.edu.utez.proyecto2D.controller.dto.ResponseRentaDTO;
import mx.edu.utez.proyecto2D.exception.ReglaNegocioException;
import org.springframework.stereotype.Service;

@Service
public class CotizadorRentaService {

    private static final double COSTO_KM_ADICIONAL = 4.0;
    private static final double CARGO_SEGURO_DIA = 180.0;
    private static final int KM_INCLUIDOS_POR_DIA = 100;

    public ResponseRentaDTO cotizar(RequestRentaDTO data) {

        if (data.getEdadConductor() < 18) {
            throw new ReglaNegocioException("El conductor debe ser mayor de edad");
        }
        if (data.getDiasRenta() > 30) {
            throw new ReglaNegocioException("La renta no puede superar los 30 días");
        }
        if (data.getKilometrosEstimados() > 5000) {
            throw new ReglaNegocioException("Los kilómetros estimados no pueden superar 5,000");
        }

        String tipoVehiculo = data.getTipoVehiculo().toUpperCase();

        if (tipoVehiculo.equals("CAMIONETA") && data.getEdadConductor() < 25) {
            throw new ReglaNegocioException("Para rentar una camioneta el conductor debe tener al menos 25 años");
        }

        double costoDiario = obtenerCostoDiario(tipoVehiculo);

        double costoRenta = costoDiario * data.getDiasRenta();

        int kmIncluidos = data.getDiasRenta() * KM_INCLUIDOS_POR_DIA;
        int kmAdicionales = Math.max(0, data.getKilometrosEstimados() - kmIncluidos);
        double cargoKmAdicional = kmAdicionales * COSTO_KM_ADICIONAL;

        double cargoEdad = 0;
        if (data.getEdadConductor() >= 18 && data.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKmAdicional) * 0.15;
        }

        double cargoSeguro = 0;
        if (Boolean.TRUE.equals(data.getSeguroCompleto())) {
            cargoSeguro = CARGO_SEGURO_DIA * data.getDiasRenta();
        }

        if (data.getDiasRenta() >= 7) {
            costoRenta = costoRenta * 0.90;
        }

        double importeTotal = costoRenta + cargoKmAdicional + cargoEdad + cargoSeguro;

        ResponseRentaDTO respuesta = new ResponseRentaDTO();
        respuesta.setNombreCliente(data.getNombreCliente());
        respuesta.setTipoVehiculo(tipoVehiculo);
        respuesta.setDiasRenta(data.getDiasRenta());
        respuesta.setImporteTotal(importeTotal);

        return respuesta;
    }

    private double obtenerCostoDiario(String tipoVehiculo) {
        switch (tipoVehiculo) {
            case "COMPACTO":
                return 550.0;
            case "SEDAN":
                return 700.0;
            case "SUV":
                return 950.0;
            case "CAMIONETA":
                return 1200.0;
            default:
                throw new ReglaNegocioException("Tipo de vehículo no válido. Usa COMPACTO, SEDAN, SUV o CAMIONETA");
        }
    }
}