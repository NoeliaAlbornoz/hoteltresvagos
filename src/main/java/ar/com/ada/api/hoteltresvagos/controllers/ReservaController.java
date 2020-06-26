package ar.com.ada.api.hoteltresvagos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.hoteltresvagos.entities.*;
import ar.com.ada.api.hoteltresvagos.services.ReservaService;

@RestController
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @GetMapping("/reservas")
    public List<Reserva> getReservas(@RequestParam(value = "nombre", required = false) String nombre) {
        List<Reserva> lr;

        if (nombre == null) {
            lr = reservaService.getReservas();
        } else {
            lr = reservaService.buscarReservasPorNombre(nombre);
        }

        return lr;
    }

    @GetMapping("/reservas/huespedes/{dni}")
    public List<Reserva> getReservasByDniHuesped(@PathVariable int dni) {
        List<Reserva> lr;
        
            lr = reservaService.buscarReservasPorDniHuesped(dni);
        
        return lr;
    }

}