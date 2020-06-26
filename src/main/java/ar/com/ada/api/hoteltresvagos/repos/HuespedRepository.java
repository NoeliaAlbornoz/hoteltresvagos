package ar.com.ada.api.hoteltresvagos.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
import ar.com.ada.api.hoteltresvagos.entities.*;
@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Integer> {

    Huesped findByDni(Integer dni);

    @Query("SELECT h FROM Huesped h WHERE h.dni = :dni")
    List<Huesped> findByDNIVersion2(@Param("dni") Integer descripcion);

    @Query("select h from Huesped h order by nombre")
    List<Huesped> findAllOrderByNombre();

    @Query("FROM Huesped WHERE nombre like CONCAT('%', ?1,'%')")
    List<Huesped> findAllByNombreContiene(String nombre);

    Huesped findByNombre(String nombreHuesped);

    @Query("SELECT h FROM Huesped h WHERE h.nombre = ?1 AND h.dni = ?2")
    List<Huesped> findAllByNombreAndDNI(String nombre, Integer dni);

}