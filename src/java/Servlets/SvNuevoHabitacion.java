package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvNuevoHabitacion", urlPatterns = {"/SvNuevoHabitacion"})
public class SvNuevoHabitacion extends HttpServlet {

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
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        String tema = request.getParameter("tema");
        int precioHabitacion = Integer.parseInt(request.getParameter("precio"));
        int piso = Integer.parseInt(request.getParameter("piso"));

        Controladora control = new Controladora();
        control.crearHabitacion(piso, tipo, tema, precioHabitacion);

        response.sendRedirect("nuevaHabitacion.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
