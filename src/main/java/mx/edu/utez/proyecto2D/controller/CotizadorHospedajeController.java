package mx.edu.utez.proyecto2D.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto2D.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.proyecto2D.controller.dto.ResponseHospedajeDTO;
import mx.edu.utez.proyecto2D.service.CotizadorHospedajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CotizadorHospedajeController {

    private final CotizadorHospedajeService cotizadorHospedajeService;

    public CotizadorHospedajeController(CotizadorHospedajeService cotizadorHospedajeService) {
        this.cotizadorHospedajeService = cotizadorHospedajeService;
    }

    @PostMapping("/hospedajes/cotizar")
    public ResponseEntity<ResponseHospedajeDTO> cotizar(@Valid @RequestBody RequestHospedajeDTO data) {
        ResponseHospedajeDTO respuesta = cotizadorHospedajeService.cotizar(data);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}