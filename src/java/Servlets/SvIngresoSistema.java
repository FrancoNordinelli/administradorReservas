package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvIngresoSistema", urlPatterns = {"/SvIngresoSistema"})
public class SvIngresoSistema extends HttpServlet {

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
        Controladora control = new Controladora();
        //trae datos de index.jsp--->nombre usario del empleado
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");

        boolean autorizado = control.verificarUsuario(usuario, contra);

        if (autorizado == true) {
            HttpSession miSesion = request.getSession(true);
            miSesion.setAttribute("usuario", usuario);
            miSesion.setAttribute("contra", contra);
            response.sendRedirect("menuPrincipal.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
