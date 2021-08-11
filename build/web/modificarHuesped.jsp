<%@page import="Logica.Huesped"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Modificar Huesped</title>
    </head>
    <body>
        <form action="SvModificarHuesped" method="GET">
            <%HttpSession miSession = request.getSession();
            Huesped huesped = (Huesped) miSession.getAttribute("huesped");
            {%>
            <h3>Nombre <input type="text" name="nombre" value="<%=huesped.getNombre()%>"></h3>
            <h3>Apellido <input type="text" name="apellido" value="<%=huesped.getApellido()%>"></h3>
            <h3>dni <input type="text" name="dni" value="<%=huesped.getDni()%>"></h3>
            <h3>Direccion <input type="text" name="direccion" value="<%=huesped.getDireccion()%>"</h3>
            <h3>Profesion <input type="text" name="profesion" value="<%=huesped.getProfesion()%>"</h3>
            <h3>Fecha Nacimiento <input type="date" name="nacimiento" value="<%=huesped.getFechaNacimiento()%>"</h3>
            <input type="hidden" name="id" value="<%=huesped.getIdPersona()%>">
            <input type="submit" value="Modificar">
            <%}%>
        </form>
        <br><a href="huespedesMenu.jsp">Volver</a>
    </body>
</html>
