package ar.com.ada.api.hoteltresvagos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.ada.api.hoteltresvagos.entities.Huesped;
import ar.com.ada.api.hoteltresvagos.models.response.GenericResponse;
import ar.com.ada.api.hoteltresvagos.services.HuespedService;

@RestController
public class HuespedController {

    @Autowired
    HuespedService huespedService;

    @GetMapping("/huespedes")
    public List<Huesped> getHuespedes(@RequestParam(value = "nombre", required = false) String nombre) {
        List<Huesped> lh;

        if (nombre == null) {
            lh = huespedService.buscarTodosOrdenadoPorNombre();
        } else {
            lh = huespedService.buscarTodosPorNombre(nombre);
        }

        return lh;
    }

    @GetMapping("/huespedes/{id}")
    public ResponseEntity<Huesped> getHuespedById(@PathVariable int id) {
        Huesped h = huespedService.buscarPorId(id);

        if (h == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(h);
    }

    @GetMapping("/huespedes/dni/{dni}")
    public ResponseEntity<Huesped> getHuespedByDni(@PathVariable int dni) {
        Huesped h = huespedService.buscarPorId(dni);

        if (h == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(h);
    }

    @PostMapping("/huespedes")
    public ResponseEntity<?> postHuesped(@RequestBody Huesped req) {

        GenericResponse r = new GenericResponse();

        boolean resultado = huespedService.crearHuesped(req);

        if (resultado) {
            r.isOk = true;
            r.id = req.getHuespedId();
            r.message = "Creaste una huesped con éxito.";
            return ResponseEntity.ok(r);
        } else {

            r.isOk = false;
            r.message = "No se pudo crear el huesped! ya existe alguien con ese DNI.";

            return ResponseEntity.badRequest().body(r);
        }

    }

    @PutMapping("/huespedes/{id}")
    public ResponseEntity<?> putHuesped(@PathVariable int id, @RequestBody Huesped req) {

        GenericResponse r = new GenericResponse();

        Huesped huespedOriginal = huespedService.buscarPorId(id);

        if (huespedOriginal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;
        resultado = huespedService.actualizarHuesped(huespedOriginal, req);

        if (resultado) {
            r.isOk = true;
            r.id = req.getHuespedId();
            r.message = "Huesped actualizado con éxito.";
            return ResponseEntity.ok(r);
        } else {

            r.isOk = false;
            r.message = "No se pudo actualizar el huesped.";

            return ResponseEntity.badRequest().body(r);
        }

    }

}