package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Reserva implements Serializable {
    
    @Basic
    long totalReserva;
    @Temporal(TemporalType.DATE)
    Date checkIn;
    @Temporal(TemporalType.DATE)
    Date checkOut;
    @OneToOne
    Habitacion habitacion;
    @OneToOne
    Huesped huesped;
    @OneToOne
    Empleado empleado;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    
    public Reserva() {
    }

    public Reserva(Date checkIn, Date checkOut, Habitacion habitacion,
            Huesped huesped, Empleado empleado, int id, long totalReserva) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.habitacion = habitacion;
        this.huesped = huesped;
        this.empleado = empleado;
        this.id = id;
        this.totalReserva = totalReserva;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalReserva() {
        return totalReserva;
    }

    public void setTotalReserva(long totalReserva) {
        this.totalReserva = totalReserva;
    }
    
    
}
