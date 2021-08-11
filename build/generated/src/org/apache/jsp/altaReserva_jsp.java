package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class altaReserva_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <script>\n");
      out.write("\n");
      out.write("        function Confirm() {\n");
      out.write("            //Ingresamos un mensaje\n");
      out.write("            var mensaje = confirm();\n");
      out.write("            //Verificamos si el usuario acepto el mensaje\n");
      out.write("            if (mensaje) {\n");
      out.write("            alert(\"¡Gracias por confirmar!\");\n");
      out.write("            }\n");
      out.write("            //Verificamos si el usuario denegó el mensaje\n");
      out.write("            else {\n");
      out.write("            alert(\"¡Haz denegado el mensaje!\");\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("    </script>\n");
      out.write("        <title>Reservas</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1 style=\"color: white\">Comenzar nueva reserva</h1>\n");
      out.write("        <form action=\"SvNuevaReserva\" method=\"POST\">\n");
      out.write("\n");
      out.write("            <h5>CheckIn <input type=\"date\" name=\"checkIn\"</h5>\n");
      out.write("            <h5>CheckOut <input type=\"date\" name=\"checkOut\"</h5>\n");
      out.write("            <h5>Numero Habitacion <input type=\"text\" name=\"habitacionNum\"</h5>\n");
      out.write("            <h5>Cantidad Personas <input type=\"text\" name=\"cantPersonas\"</h5>          \n");
      out.write("            <h5>Nombre <input type=\"text\" name=\"nombre\"></h5>\n");
      out.write("            <h5>Apellido <input type=\"text\" name=\"apellido\"></h5>\n");
      out.write("            <h5>Profesión <input type=\"text\" name=\"profesion\"></h5>\n");
      out.write("            <h5>Dirección <input type=\"text\" name=\"direccion\"></h5>\n");
      out.write("            <h5>DNI <input type=\"text\" name=\"dni\"></h5>\n");
      out.write("            <h5>Fec.Nacimiento <input type=\"date\" name=\"nacimiento\"></h5>\n");
      out.write("            <h5>Id Empleado <input type=\"text\" name=\"idE\"></h5>\n");
      out.write("            <input type=\"submit\" value=\"Reservar\"><br>           \n");
      out.write("        </form>\n");
      out.write("        <a href=\"menuPrincipal.jsp\">Menu</a><br>\n");
      out.write("        <a href=\"reservas.jsp\">Volver</a><br>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
