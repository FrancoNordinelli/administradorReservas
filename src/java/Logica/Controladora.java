package Logica;

import Persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void crearHabitacion(int piso, int tipo, String tematica,
            int precio) {
        Habitacion habitacion = new Habitacion();
        habitacion.setPiso(piso);
        habitacion.setPrecio(precio);
        habitacion.setTematica(tematica);
        habitacion.setTipo(tipo);
        controlPersis.nuevaHabitacion(habitacion);
    }

    public boolean verificarUsuario(String usuario, String contra) {
        List<Usuario> usuarios = controlPersis.traerUsuarios();
        if (usuarios != null) {
            for (Usuario usu : usuarios) {
                if (usu.getUsuario().equals(usuario) && usu.getContraseña().equals(contra)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void altaEmpleado(String nombre, String apellido, String dni,
            String direccion, String cargo, Date nacimiento, String contraseña) {
        Empleado empleado = new Empleado();
        Persona persona = new Persona();
        Usuario usuario = new Usuario();
        empleado.setApellido(apellido);
        empleado.setCargo(cargo);
        empleado.setDireccion(direccion);
        empleado.setDni(dni);
        empleado.setDireccion(direccion);
        empleado.setNombre(nombre);
        empleado.setFechaNacimiento(nacimiento);
        empleado.setUsuario(usuario);
        usuario.setUsuario(nombre);
        usuario.setContraseña(contraseña);       
        controlPersis.crearUsuario(usuario);
        controlPersis.altaEmpleado(empleado);
        controlPersis.nuevaPersona(persona);
    }

    public void nuevaReserva(String nombre, String apellido, String direccion,
            String profesion, String dni, Date nacimiento, Date checkIn,
            Date checkOut, int idHabitacion, int idE) {
        Huesped huesped = new Huesped();
        Habitacion habitacion = new Habitacion();
        Reserva reserva = new Reserva();
        Empleado empleado = new Empleado();
        Persona persona = new Persona();
        //setear datos huesped
        huesped.setApellido(apellido);
        huesped.setDireccion(direccion);
        huesped.setFechaNacimiento(nacimiento);
        huesped.setDni(dni);
        huesped.setNombre(nombre);
        huesped.setProfesion(profesion);
        List<Reserva> reservas = new ArrayList<>();
        reserva.setCheckIn(checkIn);
        reserva.setCheckOut(checkOut);
        reservas.add(reserva);
        huesped.setListaDeReservas(reservas);
        habitacion.setReservas(reservas);
        habitacion.setId(idHabitacion);
        reserva.setHabitacion(habitacion);
        long precio = precioHabitacion(idHabitacion);
        int dias = (int) ((checkOut.getTime() - checkIn.getTime()) / 86400000);
        long montoTotal = dias * precio;
        reserva.setTotalReserva(montoTotal);
        //empleado.setDni(dniE);
        huesped.setIdPersona(persona.getIdPersona());
        empleado.setIdPersona(idE);
        reserva.setEmpleado(empleado);
        
        controlPersis.nuevaPersona(persona);
        controlPersis.nuevaReserva(reserva);
        controlPersis.nuevoHuesped(huesped);

    }

    public List<Reserva> traerReservas() {
        return controlPersis.traerReservas();
    }

    public boolean habitacionDisponible(Date checkIn, Date checkOut, int numHab) {
        List<Reserva> reservas = controlPersis.traerReservas();
        
    
        boolean bandera = true;
        
        if(reservas==null){
            bandera = true;
            return bandera;
        }
        
        for(Reserva reserva : reservas){
            if(numHab==reserva.getHabitacion().getId()
                    && checkIn.before(reserva.checkOut)
                    && checkOut.after(reserva.checkIn)){
                bandera = false;
            }
                break;            
        }
       
       return bandera;   
    }

    public boolean personasHabitacion(int cantPersonas, int idHab) {
        List<Habitacion> habitaciones = controlPersis.traerHabitaciones();
        for (Habitacion habitacion : habitaciones) {
            if (idHab == habitacion.getId() && habitacion.getTipo() >= cantPersonas) {
                return true;
            }
        }
        return false;
    }

    public long precioHabitacion(int idHabitacion) {
        List<Habitacion> habitaciones = controlPersis.traerHabitaciones();
        long precio = 0;
        for (Habitacion habitacion : habitaciones) {
            if (idHabitacion == habitacion.getId()) {
                precio = habitacion.getPrecio();
            }
        }
        return precio;
    }

    public List <Huesped> traerHuespedes() {
        return controlPersis.traerhuespedes();
    }

    public void borrarHuesped(int id) {
        controlPersis.borrarHuesped(id);
    }

    public Huesped traerHuesped(int id) {
        return controlPersis.traerhuesped(id);
    }

    public void modificarHuesped(Huesped huesped) {
        controlPersis.modificarHuesped(huesped);
    }

    /*public List<Reserva> traerReservasEmpleado(int id) {
        return controlPersis.traerReservasEmpleado();
    }*/
}