package mx.edu.utez.proyecto2D.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto2D.controller.dto.RequestEnvioDTO;
import mx.edu.utez.proyecto2D.controller.dto.ResponseEnvioDTO;
import mx.edu.utez.proyecto2D.service.CotizadorEnvioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CotizadorEnvioController {

    private final CotizadorEnvioService cotizadorEnvioService;

    public CotizadorEnvioController(CotizadorEnvioService cotizadorEnvioService) {
        this.cotizadorEnvioService = cotizadorEnvioService;
    }

    @PostMapping("/envios/cotizar")
    public ResponseEntity<ResponseEnvioDTO> cotizar(@Valid @RequestBody RequestEnvioDTO data) {
        ResponseEnvioDTO respuesta = cotizadorEnvioService.cotizar(data);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}