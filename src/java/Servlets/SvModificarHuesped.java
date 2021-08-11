package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarHuesped", urlPatterns = {"/SvModificarHuesped"})
public class SvModificarHuesped extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        String direccion = request.getParameter("direccion");
        String profesion = request.getParameter("profesion");
        String fechaNacimiento = request.getParameter("nacimiento");
        int id = Integer.parseInt(request.getParameter("id"));
        Date nacimiento = Date.valueOf(fechaNacimiento);
        Controladora control = new Controladora();
        Huesped huesped = control.traerHuesped(id);       
        huesped.setApellido(apellido);
        huesped.setDireccion(direccion);
        huesped.setDni(dni);
        huesped.setFechaNacimiento(nacimiento);
        huesped.setNombre(nombre);
        huesped.setProfesion(profesion);
        
        control.modificarHuesped(huesped);
        request.getSession().setAttribute("huespedes", control.traerHuespedes());
        response.sendRedirect("menuPrincipal.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int dni = Integer.parseInt(request.getParameter(("dni")));
        Controladora control = new Controladora();
        Huesped huesped = control.traerHuesped(dni);
        
        HttpSession miSession = request.getSession();
        miSession.setAttribute("huesped", huesped);
        response.sendRedirect("modificarHuesped.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
