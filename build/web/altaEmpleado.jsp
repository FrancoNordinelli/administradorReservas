<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Alta Empleado</title>
    </head>
    <body>
        <form action="SvAltaEmpleado" method="POST">
            <h3>Nombre <input type="text" name="nombre"></h3>
            <h3>Apellido <input type="text" name="apellido"></h3>
            <h3>dni <input type="text" name="dni"></h3>
            <h3>Direccion <input type="text" name="direccion"</h3>
            <h3>Fecha Nacimiento <input type="date" name="nacimiento"</h3>
            <h3>Cargo <select name="cargo">
                    <option value="administrador">Administrador/a</option>
                    <option value="recepcion">Recepción</option>
                    <option value="limpieza">Limpieza</option>
                    <option value="contador">Contador/a</option>
                </select></h3>
            <h3>Usuario <input type="text" name="usuario"></h3>
            <h3>Contraseña <input type="text" name="contra"></h3>
            <input type="submit" value="Dar Alta">
        </form>
        <a href="empleadosMenu.jsp">Volver</a>
        <a href="menuPrincipal.jsp">Menu</a>
    </body>
</html>

