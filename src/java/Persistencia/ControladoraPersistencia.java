package Persistencia;

import Logica.Empleado;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.Persona;
import Logica.Reserva;
import Logica.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();
    HabitacionJpaController habitacionJpa = new HabitacionJpaController();
    HuespedJpaController huespedJpa = new HuespedJpaController();
    PersonaJpaController personaJpa = new PersonaJpaController();
    ReservaJpaController reservaJpa = new ReservaJpaController();
    UsuarioJpaController usuarioJpa = new UsuarioJpaController();

    public void altaEmpleado(Empleado empleado) {
        try {
            empleadoJpa.create(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nuevaHabitacion(Habitacion habitacion) {
        habitacionJpa.create(habitacion);
    }

    public void nuevoHuesped(Huesped huesped) {
        try {
            huespedJpa.create(huesped);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nuevaPersona(Persona persona) {
        try {
            personaJpa.create(persona);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nuevaReserva(Reserva reserva) {
        reservaJpa.create(reserva);
    }

    public void crearUsuario(Usuario usuario) {
        try {
            usuarioJpa.create(usuario);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Trae lista de usuarios de la tabla Usuario de BD
    public List<Usuario> traerUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }

    //Trae lista de reservas de la tabla Reserva de BD
    public List<Reserva> traerReservas() {
        return reservaJpa.findReservaEntities();
    }

    public List<Habitacion> traerHabitaciones() {
        return habitacionJpa.findHabitacionEntities();
    }
    
    public List <Huesped> traerhuespedes() {
        return huespedJpa.findHuespedEntities();
    }

    public void borrarHuesped(int id) {
        try {
            huespedJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Huesped traerhuesped(int id) {
        return huespedJpa.findHuesped(id);
    }

    public void modificarHuesped(Huesped huesped) {
        try {
            huespedJpa.edit(huesped);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    /*public List<Reserva> traerReservasEmpleado(int id, Empleado empleado) {       
        return  reservaJpa.findReserva(empleado.getIdPersona());
    }

}*/
