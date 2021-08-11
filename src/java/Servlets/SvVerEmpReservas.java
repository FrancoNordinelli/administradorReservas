package Servlets;

import Logica.Controladora;
import Logica.Reserva;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvVerEmpReservas", urlPatterns = {"/SvVerEmpReservas"})
public class SvVerEmpReservas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {          
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        Controladora control = new Controladora();
        //List <Reserva> reservas = control.traerReservasEmpleado(idEmpleado);
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("idEmpleado", idEmpleado);
        response.sendRedirect("verReservasEmpleado.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
