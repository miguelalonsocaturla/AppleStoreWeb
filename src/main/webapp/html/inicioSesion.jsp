<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, p2.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Inicio Sesion</title>
<link href="./css/registros.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
	//Utilizamos una variable en la sesión para informar de los mensajes de Error
	AccesoBD con = AccesoBD.getInstance();
	List<UsuarioBD> usuarios = con.obtenerUsuariosBD();
	
	String mensaje = (String) session.getAttribute("mensaje");
	if (mensaje != null) { //Eliminamos el mensaje consumido
	session.removeAttribute("mensaje");
	%>
		<div class="alert alert-danger alert-dismissible fade show align-items-center" >
		    	<br/> <strong><%=mensaje%></strong><br/> 
		</div>
	<%
	}
	%>
	<%
	if ((session.getAttribute("correo") == null)) //Si no hay usuario o el usuario no es válido
	{ //Mostramos el formulario para la introducción del usuario y la clave
	%>
	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
						<h1 class="card-title text-center mb-5">Inicia Sesion</h1>
						<form  method="post" onsubmit="ProcesarForm(this,'Login','inicio');return false">
							<div class="mb-3">
								<label for="email" class="form-label">Correo Electronico</label>
								<input type="email" class="form-control" id="correo" name="correo"  required>
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Contraseña</label>
								<input type="password" class="form-control" id="contraseña" name="contraseña" required>
							</div>
							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-primary" >Iniciar Sesion</button>
							</div>
						</form>
						<div class="text-center mt-3">
							¿No tienes una cuenta? <a href="#" onclick="Cargar('./html/registro.jsp','inicio')">Regístrate aquí</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%

     }
     else{
 %>
         <script>
             
                 Cargar('./html/panelUsuario.jsp','inicio');
         </script>
 <%
     }
 %>
</body>
</html>