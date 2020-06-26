package ar.com.ada.api.hoteltresvagos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.hoteltresvagos.entities.*;
import ar.com.ada.api.hoteltresvagos.repos.*;

@Service
public class HuespedService {

    @Autowired
    HuespedRepository huespedRepo;

    public List<Huesped> buscarTodos() {

        return huespedRepo.findAll();
    }

    public List<Huesped> buscarTodosOrdenadoPorNombre() {

        return huespedRepo.findAllOrderByNombre();
    }

    public List<Huesped> buscarTodosPorNombre(String nombre) {

        return huespedRepo.findAllByNombreContiene(nombre);
    }

    public Huesped buscarPorId(int huespedId) {

        Optional<Huesped> b = huespedRepo.findById(huespedId);

        if (b.isPresent())
            return b.get();
        return null;
    }

    public boolean crearHuesped(Huesped huesped) {

        if (existe(huesped.getDni()))
            return false;

        grabar(huesped);
        return true;
    }

    public void grabar(Huesped huesped) {

        huespedRepo.save(huesped);
    }

    public Huesped buscarPorDni(int dni) {

        return huespedRepo.findByDni(dni);
    }

    public boolean existe(int dni) {

        Huesped huesped = buscarPorDni(dni);

        return huesped != null;

    }

    public boolean actualizarHuesped(Huesped huespedOriginal, Huesped huespedConInfoNueva) {

        huespedOriginal.setNombre(huespedConInfoNueva.getNombre());
        huespedOriginal.setDomicilio(huespedConInfoNueva.getDomicilio());
        huespedOriginal.setDomicilioAlternativo(huespedConInfoNueva.getDomicilioAlternativo());

        grabar(huespedOriginal);

        return true;
    }

    public boolean baja(int huespedId) {

        Huesped huesped = buscarPorId(huespedId);

        if (huesped == null)
            return false;

        return baja(huesped);
    }

    public boolean baja(Huesped huesped) {

        huespedRepo.delete(huesped);
        return true;
    }

    public boolean bajaPorDNI(int dni) {

        Huesped huesped = huespedRepo.findByDni(dni);

        if (huesped == null)
            return false;

        return baja(huesped);
    }

}