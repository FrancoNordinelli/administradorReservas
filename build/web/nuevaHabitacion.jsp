<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Habitacion</title>
    </head>
    <body>
        <form action="SvNuevoHabitacion" method="POST">
            <center><h1 style="color: white">Nueva habitacion</h1></center>
            <h3>Cantidad de personas: <select name="tipo">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                </select></h3>
            <h3>Piso: <select name="piso">
                    <option value="0">0</option>    
                    <option value="1">1</option>
                    <option value="2">2</option>
                </select></h3>
            <h3>Temática: <select name="tema">
                    <option value="-">-</option>
                    <option value="sol">sol</option>
                    <option value="luna">luna</option>
                    <option value="tierra">tierra</option>
                    <option value="estrella">estrella</option>
                </select></h3>
            <h3>Precio: <input type="text" name="precio"></h3>
            <input type="submit" value="Crear"><br>
            <a href="menuPrincipal.jsp">Volver</a> 
        </form>
    </body>
</html>

