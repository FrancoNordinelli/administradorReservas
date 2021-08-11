<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <script>

        function Confirm() {
            //Ingresamos un mensaje
            var mensaje = confirm();
            //Verificamos si el usuario acepto el mensaje
            if (mensaje) {
            alert("¡Gracias por confirmar!");
            }
            //Verificamos si el usuario denegó el mensaje
            else {
            alert("¡Haz denegado el mensaje!");
            }
        }

    </script>
        <title>Reservas</title>
    </head>
    <body>
        <h1 style="color: white">Comenzar nueva reserva</h1>
        <form action="SvNuevaReserva" method="POST">

            <h5>CheckIn <input type="date" name="checkIn"</h5>
            <h5>CheckOut <input type="date" name="checkOut"</h5>
            <h5>Numero Habitacion <input type="text" name="habitacionNum"</h5>
            <h5>Cantidad Personas <input type="text" name="cantPersonas"</h5>          
            <h5>Nombre <input type="text" name="nombre"></h5>
            <h5>Apellido <input type="text" name="apellido"></h5>
            <h5>Profesión <input type="text" name="profesion"></h5>
            <h5>Dirección <input type="text" name="direccion"></h5>
            <h5>DNI <input type="text" name="dni"></h5>
            <h5>Fec.Nacimiento <input type="date" name="nacimiento"></h5>
            <h5>Id Empleado <input type="text" name="idE"></h5>
            <input type="submit" value="Reservar"><br>           
        </form>
        <a href="menuPrincipal.jsp">Menu</a><br>
        <a href="reservas.jsp">Volver</a><br>
    </body>
</html>

