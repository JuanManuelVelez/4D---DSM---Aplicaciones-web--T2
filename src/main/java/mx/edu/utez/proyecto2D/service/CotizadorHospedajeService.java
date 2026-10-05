package mx.edu.utez.proyecto2D.service;

import mx.edu.utez.proyecto2D.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.proyecto2D.controller.dto.ResponseHospedajeDTO;
import mx.edu.utez.proyecto2D.exception.ReglaNegocioException;
import org.springframework.stereotype.Service;

@Service
public class CotizadorHospedajeService {

    private static final double COSTO_DESAYUNO_POR_HUESPED_NOCHE = 150.0;
    private static final double COSTO_ESTACIONAMIENTO_POR_NOCHE = 100.0;

    public ResponseHospedajeDTO cotizar(RequestHospedajeDTO data) {

        if (data.getNumeroNoches() > 30) {
            throw new ReglaNegocioException("El número de noches no puede superar 30");
        }

        String tipoHabitacion = data.getTipoHabitacion().toUpperCase();
        double costoPorNoche = obtenerCostoPorNoche(tipoHabitacion);
        int capacidad = obtenerCapacidad(tipoHabitacion);

        if (data.getNumeroHuespedes() > capacidad) {
            throw new ReglaNegocioException(
                    "La habitación " + tipoHabitacion + " no admite más de " + capacidad + " huésped(es)"
            );
        }

        double costoHospedaje = costoPorNoche * data.getNumeroNoches();

        String temporada = data.getTemporada().toUpperCase();
        switch (temporada) {
            case "BAJA":
                costoHospedaje = costoHospedaje * 0.90;
                break;
            case "ALTA":
                costoHospedaje = costoHospedaje * 1.25;
                break;
            case "REGULAR":
                break;
            default:
                throw new ReglaNegocioException("Temporada no válida. Usa BAJA, REGULAR o ALTA");
        }

        if (data.getNumeroNoches() >= 7) {
            costoHospedaje = costoHospedaje * 0.92;
        }

        double costoDesayuno = 0;
        if (Boolean.TRUE.equals(data.getIncluyeDesayuno())) {
            costoDesayuno = data.getNumeroHuespedes() * data.getNumeroNoches() * COSTO_DESAYUNO_POR_HUESPED_NOCHE;
        }

        double costoEstacionamiento = 0;
        if (Boolean.TRUE.equals(data.getIncluyeEstacionamiento())) {
            costoEstacionamiento = data.getNumeroNoches() * COSTO_ESTACIONAMIENTO_POR_NOCHE;
        }

        double subtotal = costoHospedaje + costoDesayuno + costoEstacionamiento;
        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        ResponseHospedajeDTO respuesta = new ResponseHospedajeDTO();
        respuesta.setNombreHuesped(data.getNombreHuesped());
        respuesta.setTipoHabitacion(tipoHabitacion);
        respuesta.setNumeroNoches(data.getNumeroNoches());
        respuesta.setSubtotal(subtotal);
        respuesta.setImpuesto(impuesto);
        respuesta.setTotal(total);

        return respuesta;
    }

    private double obtenerCostoPorNoche(String tipoHabitacion) {
        switch (tipoHabitacion) {
            case "INDIVIDUAL":
                return 700.0;
            case "DOBLE":
                return 1100.0;
            case "SUITE":
                return 1800.0;
            default:
                throw new ReglaNegocioException("Tipo de habitación no válido. Usa INDIVIDUAL, DOBLE o SUITE");
        }
    }

    private int obtenerCapacidad(String tipoHabitacion) {
        switch (tipoHabitacion) {
            case "INDIVIDUAL":
                return 1;
            case "DOBLE":
                return 2;
            case "SUITE":
                return 4;
            default:
                throw new ReglaNegocioException("Tipo de habitación no válido. Usa INDIVIDUAL, DOBLE o SUITE");
        }
    }
}