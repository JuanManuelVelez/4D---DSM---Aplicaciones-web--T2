package mx.edu.utez.proyecto2D.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto2D.controller.dto.RequestRentaDTO;
import mx.edu.utez.proyecto2D.controller.dto.ResponseRentaDTO;
import mx.edu.utez.proyecto2D.service.CotizadorRentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CotizadorRentaController {

    private final CotizadorRentaService cotizadorRentaService;

    public CotizadorRentaController(CotizadorRentaService cotizadorRentaService) {
        this.cotizadorRentaService = cotizadorRentaService;
    }

    @PostMapping("/rentas/cotizar")
    public ResponseEntity<ResponseRentaDTO> cotizar(@Valid @RequestBody RequestRentaDTO data) {
        ResponseRentaDTO respuesta = cotizadorRentaService.cotizar(data);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}