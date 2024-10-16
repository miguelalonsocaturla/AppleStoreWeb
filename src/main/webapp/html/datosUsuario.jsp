<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, p2.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Panel del usuario</title>
<link href="./css/registros.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
	//Utilizamos una variable en la sesión para informar de los mensajes de Error
	AccesoBD con = AccesoBD.getInstance();
	String  correo = (String) session.getAttribute("correo");
	UsuarioBD usuario = con.obtenerUsuarioActualBD(correo);
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
						<h1 class="card-title text-center mb-5">Tus Datos</h1>
						<form  method="post" onsubmit="ProcesarForm(this,'ActualizarUsuario','inicio');return false">
							<div class="mb-3">
								<label for="nombre" class="form-label">Nombre</label>
								<input type="text" class="form-control" id="nombre" name="nombre" value="<%=usuario.getNombre()%>" required>
							</div>
							<div class="mb-3">
								<label for="apellidos" class="form-label">Apellidos</label>
								<input type="text" class="form-control" id="apellidos" name="apellidos" value="<%=usuario.getApellidos()%>" required>
							</div>
							<div class="mb-3">
								<label for="direccion" class="form-label">Dirección</label>
								<input type="text" class="form-control" id="direccion" name="direccion" value="<%=usuario.getDireccion()%>" required>
							</div>
							<div class="mb-3">
								<label for="ciudad" class="form-label">Ciudad</label>
								<input type="text" class="form-control" id="ciudad" name="ciudad" value="<%=usuario.getCiudad()%>" required>
							</div>
							<div class="mb-3">
								<label for="cp" class="form-label">Codigo Postal</label>
								<input type="text" class="form-control" id="cp" name="cp"  value="<%=usuario.getCP()%>" required>
							</div>
							<div class="mb-3">
								<label for="telefono" class="form-label">Telefono</label>
								<input type="text" class="form-control" id="telefono" name="telefono" value="<%=usuario.getTelefono()%>" required>
							</div>
							
							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-primary" >Guardar y volver</button>
								<button  class="btn btn-primary" onclick="Cargar('./html/panelUsuario.jsp','inicio')">Volver</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>