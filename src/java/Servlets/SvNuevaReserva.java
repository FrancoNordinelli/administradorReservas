package Servlets;

import Logica.Controladora;
import Logica.Reserva;
import Persistencia.ControladoraPersistencia;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvNuevaReserva", urlPatterns = {"/SvNuevaReserva"})
public class SvNuevaReserva extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String checkIn = request.getParameter("checkIn");
        String checkOut = request.getParameter("checkOut");
        int numHabitacion = Integer.parseInt(request.getParameter("habitacionNum"));
        int cantPersonas = Integer.parseInt(request.getParameter("cantPersonas"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String profesion = request.getParameter("profesion");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        String fechaNacimiento = request.getParameter("nacimiento");
        
        Date fechaIngreso = Date.valueOf(checkIn);
        Date fechaEgreso = Date.valueOf(checkOut);
        Date nacimiento = Date.valueOf(fechaNacimiento);     
        int idE = Integer.parseInt(request.getParameter("idE"));
        
        Controladora control = new Controladora();
        boolean disponible = control.habitacionDisponible(fechaIngreso, fechaEgreso, numHabitacion);
        boolean capacidad = control.personasHabitacion(cantPersonas,numHabitacion);
        System.out.println("Habitacion disponible!!!!"+disponible);
        if (disponible == true && capacidad==true) {
            control.nuevaReserva(nombre, apellido, direccion, profesion,
                    dni, nacimiento, fechaIngreso, fechaEgreso, numHabitacion, idE);
            response.sendRedirect("menuPrincipal.jsp");
        }else{
            response.sendRedirect("altaReserva.jsp");
        }
      
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
 
        

}
