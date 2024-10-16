<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, p2.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Registro</title>
<link href="./css/registros.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
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
	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
						<h1 class="card-title text-center mb-5">Registro</h1>
						<form  method="post" onsubmit="ProcesarForm(this,'Registro','inicio');return false">
							<div class="mb-3">
								<label for="nombre" class="form-label">Nombre</label>
								<input type="text" class="form-control" id="nombre" name="nombre" required>
							</div>
							<div class="mb-3">
								<label for="apellidos" class="form-label">Apellidos</label>
								<input type="text" class="form-control" id="apellidos" name="apellidos" required>
							</div>
							<div class="mb-3">
								<label for="direccion" class="form-label">Dirección</label>
								<input type="text" class="form-control" id="direccion" name="direccion" required>
							</div>
							<div class="mb-3">
								<label for="ciudad" class="form-label">Ciudad</label>
								<input type="text" class="form-control" id="ciudad" name="ciudad" required>
							</div>
							<div class="mb-3">
								<label for="CP" class="form-label">CP</label>
								<input type="text" class="form-control" id="CP" name="CP" required>
							</div>
							<div class="mb-3">
								<label for="telefono" class="form-label">Telefono</label>
								<input type="text" class="form-control" id="telefono" name="telefono" required>
							</div>
							<div class="mb-3">
								<label for="email" class="form-label">Correo Electronico</label>
								<input type="email" class="form-control" id="correo" name="correo" required>
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Contraseña</label>
								<input type="password" class="form-control" id="contraseña" name="contraseña" required>
							</div>
							<div class="d-grid gap-2">
								<label>Al registrate estas aceptando los <a href="#" onclick="Cargar('./html/terminos.html','inicio')">Terminos y condiciones</a></label>
								<button type="submit" class="btn btn-primary" >Registrate</button>
							</div>
						</form>
						<div class="text-center mt-3">
							¿Ya Tienes una cuenta? <a href="#" onclick="Cargar('./html/inicioSesion.jsp','inicio')">Inicia Sesion aqui</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>