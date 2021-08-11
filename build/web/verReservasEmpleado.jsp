<%@page import="java.sql.Date"%>
<%@page import="Logica.Reserva"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <title>Huespedes</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>
        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">Reservas Empleado</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="table-wrap">
                            <table class="table table-bordered table-dark table-hover">
                                <thead>
                                    <tr class="row500 head">
                                        <th>IdReserva</th>
                                        <th>CheckIn</th>
                                        <th>CheckOut</th>
                                        <th class="eliminar">Eliminar</th>
                                        <th class="modificar">Modificar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%HttpSession miSession = request.getSession();
                                        List<Reserva> reservas = (List) request.getSession().getAttribute("reservas");
                                        int idEmpleado = (Integer) request.getSession().getAttribute("idEmpleado");
                                        for (Reserva reserva : reservas) {
                                            if(reserva.getEmpleado().getIdPersona()==idEmpleado){
                                    %>
                                    <tr>
                                        <%
                                            int idReserva = reserva.getId();
                                        %>

                                        <td><%=idReserva%></td>
                                        <td></td>
                                        <td></td>
                        
                                    </tr>
                                    <%}}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>