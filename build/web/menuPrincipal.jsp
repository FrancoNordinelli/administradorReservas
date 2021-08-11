<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Menu</title>
    </head>
    <body>

        <%
            HttpSession miSession = request.getSession();
            String usuario = (String) miSession.getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("index.jsp");
            } else {
        %>
    <center>
        <h1 style="color: white">Bienvenido <%=miSession.getAttribute("usuario")%></h1>
        <!--Para la creacion de nuevas habitaciones-->
        <a href="nuevaHabitacion.jsp">
            <button class="accordion"> Habitaciones </button>
        </a>
        <!--Para comenzar nuevas reservas, ver reservas por día,
        ver reservas hechas por empleados y listas de reservas hechas por 
        un huésped-->
        <a href="reservas.jsp">
            <button> Reservas </button>
        </a>
        <!--Dar de alta empleados-->
        <a href="empleadosMenu.jsp">
            <button>Empleados</button>
        </a>

        <a href="huespedesMenu.jsp">
            <button>Huespedes</button>
        </a>
        <% }%>
    </center>
</body>
</html>

