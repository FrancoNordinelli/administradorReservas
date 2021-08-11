<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Logica.Huesped"%>
<%@page import="java.util.List"%>
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
                        <h2 class="heading-section">Huespedes</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="table-wrap">
                            <table class="table table-bordered table-dark table-hover">
                                <thead>
                                    <tr class="row500 head">
                                        <th>Nombre</th>
                                        <th>Apellido</th>
                                        <th>DNI</th>
                                        <th class="eliminar">Eliminar</th>
                                        <th class="modificar">Modificar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%HttpSession miSession = request.getSession();
                                        List<Huesped> huespedes = (List) request.getSession().getAttribute("huespedes");
                                        for (Huesped huesped : huespedes) {
                                    %>
                                    <tr>
                                        <%
                                            String nombre = huesped.getNombre();
                                            String apellido = huesped.getApellido();
                                            String dni = huesped.getDni();
                                            int id = huesped.getIdPersona();
                                        %>

                                        <td><%=nombre%></td>
                                        <td><%=apellido%></td>
                                        <td><%=dni%></td>
                                        <td class="eliminar">
                                            <form action="SvEliminarHuesped" method="POST">
                                                <input type="hidden" name="id" value="<%=id%>">
                                                <button type="submit" class="btn btn-danger">Elimininar</button>
                                            </form>
                                        </td>
                                        <td class="modificar">
                                            <form action="SvModificarHuesped" method="POST">
                                                <input type="hidden" name="dni" value="<%=id%>">
                                                <button type="submit" class="btn btn-light">Modificar</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%}%>
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
        <a href="menuPrincipal.jsp">Menu</a><br>
        <a href="huespedesMenu.jsp">Volver</a>
    </body>
</html>




