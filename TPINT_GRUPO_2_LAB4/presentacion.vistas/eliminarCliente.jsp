<%@page import="java.util.ArrayList"%>
<%@ page import="entidades.Usuario"%>
<%@page import="entidades.Cliente" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	    <link rel="stylesheet" type="text/css" href="Recursos/css/mainAdmin.css">
	    <link rel="stylesheet" type="text/css" href="Recursos/css/eliminarCliente.css">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="icon" type="image/png" href="Recursos/img/BancoLogo.png" />
	    <title>Globank | Eliminar Cliente</title>
	</head>
	<body>
	<%
	Usuario admin = new Usuario ();
	admin = (Usuario) request.getAttribute("admin_actual");
	%>
		<header class="encabezado">
            <div class="contenedor-menu">
			<a href="ServletMenuAdmin">
           		<img class="imagen-menu" src="Recursos/img/BancoLogo.png" alt="nav" /> 
          	</a> 
                <h1 style="color:#ffefd5;"> GLOBANK </h1>
                <ul class="contenedor-links-menu">  
                    <li class="nav-item dropdown">
                        <a class= "nav-link dropdown-toggle links-menu" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                           Cuentas
                        </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="ServletAltaCuenta">Alta de cuentas</a>
                            <a class="dropdown-item" href="ServletModificarCuenta">Modificar Cuentas</a>
                            <a class="dropdown-item" href="ServletListarCuenta">Listar Cuentas</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="ServletEliminarCuenta">Baja de cuentas</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class= "nav-link dropdown-toggle links-menu" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                       Clientes
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="ServletAltaCliente">Alta de clientes</a>
                        <a class="dropdown-item" href="ServletModificarCliente">Modificar clientes</a>
                        <a class="dropdown-item" href="ServletListadoCliente">Listar clientes</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="ServletEliminarCliente">Baja de clientes</a>
                    </div>
                </li>
                    <li class="nav-item dropdown">
                        <a class= "nav-link dropdown-toggle links-menu" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                           Reportes de prestamos
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="ServletListarPrestamos">Ver todos los prestamos</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="ServletGestionarPrestamos">Gestionar prestamos</a>
                        </div>
                    </li>
              		<li>
               		<a class="btn btn-danger" href="logOut.jsp" role="button" >LogOut</a>
            		</li>                    
                    <li class="mensaje-bienvenida">
                        <h1> Bienvenid@, <%=admin.getNombreUsuario() %></h1> 
                    </li>
                </ul>
            </div> 
        </header>
        <% ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("listaClientes");%>
        <div class="container-table"  id="table-usuarios" style="margin-top: 150px">
	    	<h2> Clientes: </h2>
	        <table class="table">
	        <thead>
	            <tr>
	            	<th scope="col" class="table-header">DNI</th>
		        	<th scope="col" class="table-header">Nombre</th>
		        	<th scope="col" class="table-header">Apellido</th>
		        	<th scope="col" class="table-header">Genero</th>
		        	<th scope="col" class="table-header">Nacionalidad</th>
		        	<th scope="col" class="table-header">CUIL</th>
		        	<th scope="col" class="table-header">Fecha de nacimiento</th>
		        	<th scope="col" class="table-header">Direccion</th>
		        	<th scope="col" class="table-header">Correo electronico</th>
		        	<th scope="col" class="table-header">Provincia</th>
		        	<th scope="col" class="table-header">Localidad</th> 
		        	<th scope="col" class="table-header">Telefono primario</th>
		        	<th scope="col" class="table-header">Telefono secundario</th>
		        	<th scope="col" class="table-header"></th>
	            </tr>
	        </thead>
	        <tbody>    
	         <% if(clientes != null) {
	        	 	int cont = 0;
		        	for(Cliente cliente: clientes) { 
		        		cont++;
		        		String rowClass = (cont % 2 == 0) ? "table-row-even" : "table-row-odd"; %>
				        <tr class="<%=rowClass%>">
				        	<form action="ServletEliminarCliente" method="get">
					        	<th scope="row"><%=cliente.getDNI()%> <input type="hidden" name="DNI" value="<%=cliente.getDNI()%>"></th>
					        	<td><%=cliente.getNombre()%></td>
					       		<td><%=cliente.getApellido()%></td>
					       		<td><%=cliente.getId_genero().getDescripcion()%></td>
					       		<td><%=cliente.getId_nacionalidad().getNombre_pais()%></td>
					       		<td><%=cliente.getCUIL()%></td>
					       		<td><%=cliente.getFecha_nacimiento()%></td>
					       		<td><%=cliente.getDireccion()%></td>
					       		<td><%=cliente.getCorreo_electronico()%></td>
					       		<td><%=cliente.getId_provincia().getNombre_provincia()%></td>
					       		<td><%=cliente.getId_localidades().getNombre_localidad()%></td>
				        		<td><%=cliente.getTelefono_primario()%></td>
				        		<td><%=cliente.getTelefono_secundario()%></td>
				        		<td><input type="submit" name="buttonEliminar" value="Eliminar" id="button"></td> 		
								<div>
			                        <% if (request.getAttribute("confirm" + cliente.getDNI()) != null) { %>
			                        	<p class="confirm-message"><%= request.getAttribute("confirm" + cliente.getDNI()) %>
				                            <form action="ServletEliminarCliente" method="get">
				                                <input type="hidden" name="DNI" value="<%=cliente.getDNI()%>">
				                                <input type="submit" name="confirmEliminar" id="buttonSubmit" value="Eliminar">
				                                <input type="submit" name="buttonCancelar" id="button" value="Cancelar">
				                            </form>
			                            </p>
			                        <% } %>
			                    </div>
				       		</form>
				   		</tr>
				  <%}
		    }%>
	        </tbody>
	    </table>
	</div>
		
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		
	<script>
	    document.addEventListener("DOMContentLoaded", function () {
	    	var cancelButton = row.querySelector(".buttonCancelar");
	
	        if (cancelButton) {
	        	cancelButton.addEventListener("click", function () {
	            	buttonsInRow.forEach(function (btn) {
	            		btn.disabled = false;
	            	});
	        	});
	        }
	    });
	</script>
	</body>
</html>