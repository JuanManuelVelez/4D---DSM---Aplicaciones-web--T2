package mx.edu.utez.proyecto2D.service;

import mx.edu.utez.proyecto2D.controller.dto.RequestEnvioDTO;
import mx.edu.utez.proyecto2D.controller.dto.ResponseEnvioDTO;
import mx.edu.utez.proyecto2D.exception.PaqueteNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class CotizadorEnvioService {

    private static final double COSTO_BASE = 80.0;
    private static final double COSTO_POR_KG = 12.0;
    private static final double VOLUMEN_LIMITE = 50000.0;
    private static final double RECARGO_VOLUMEN = 100.0;

    public ResponseEnvioDTO cotizar(RequestEnvioDTO data) {

        if (data.getPesoKg() > 50) {
            throw new PaqueteNoValidoException("El paquete no puede pesar más de 50 kg");
        }
        if (data.getLargoCm() > 150 || data.getAnchoCm() > 150 || data.getAltoCm() > 150) {
            throw new PaqueteNoValidoException("Ninguna dimensión puede superar los 150 cm");
        }

        double volumen = data.getLargoCm() * data.getAnchoCm() * data.getAltoCm();
        if (volumen > 1_000_000) {
            throw new PaqueteNoValidoException("El volumen no puede superar 1,000,000 cm³");
        }

        String tipoEnvio = data.getTipoEnvio().toUpperCase();
        if (!tipoEnvio.equals("ESTANDAR") && !tipoEnvio.equals("EXPRESS") && !tipoEnvio.equals("MISMO_DIA")) {
            throw new PaqueteNoValidoException("Tipo de envío no válido. Usa ESTANDAR, EXPRESS o MISMO_DIA");
        }

        double costo = COSTO_BASE + (COSTO_POR_KG * data.getPesoKg());

        if (volumen > VOLUMEN_LIMITE) {
            costo += RECARGO_VOLUMEN;
        }

        if (tipoEnvio.equals("EXPRESS")) {
            costo += costo * 0.40;
        } else if (tipoEnvio.equals("MISMO_DIA")) {
            costo += costo * 0.70;
        }

        if (data.getValorDeclarado() > 10000) {
            costo += data.getValorDeclarado() * 0.02;
        }

        ResponseEnvioDTO respuesta = new ResponseEnvioDTO();
        respuesta.setCodigoPostal(data.getCodigoPostal());
        respuesta.setPesoKg(data.getPesoKg());
        respuesta.setTipoEnvio(tipoEnvio);
        respuesta.setVolumenCm3(volumen);
        respuesta.setCostoTotal(costo);

        return respuesta;
    }
}