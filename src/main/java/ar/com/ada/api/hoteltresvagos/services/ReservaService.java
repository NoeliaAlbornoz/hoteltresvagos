package ar.com.ada.api.hoteltresvagos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.hoteltresvagos.entities.Reserva;
import ar.com.ada.api.hoteltresvagos.repos.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepo;

    public void grabar(Reserva reserva) {

        reservaRepo.save(reserva);
    }

    public Reserva buscarPorId(int reservaId) {

        Optional<Reserva> b = reservaRepo.findById(reservaId);

        if (b.isPresent())
            return b.get();

        return null;
    }

    public boolean baja(int reservaId) {

        Reserva reserva = buscarPorId(reservaId);

        if (reserva == null)
            return false;

        return baja(reserva);
    }

    public boolean baja(Reserva reserva) {

        reservaRepo.delete(reserva);
        return true;
    }

    public List<Reserva> getReservas() {

        return reservaRepo.findAll();
    }

    public List<Reserva> buscarReservasPorNombre(String nombre) {

        return reservaRepo.findByNombreHuesped(nombre);
    }

    public List<Reserva> buscarReservasPorNombreV2(String nombre) {

        return reservaRepo.findAllByReservaNombreHuesped(nombre);
    }

    public List<Reserva> buscarReservasPorDniHuesped(int dni) {

        return reservaRepo.findAllByReservaDniHuesped(dni);
    }


}