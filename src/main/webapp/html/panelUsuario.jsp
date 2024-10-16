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

 <div class="container mt-5">
    <div class="row">
      <div class="col-md-6">
        <h3>Tu sesion</h3>
        <form method="post" onsubmit="ProcesarForm(this, 'Logout', 'inicio'); return false">
          <div class="form-group">
            <label for="email">Correo electrónico:</label>
            <input type="email" class="form-control" id="correo" value="<%=usuario.getCorreo()%>" readonly>
          </div>
          <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" value="<%=usuario.getNombre() %>" readonly>
          </div>
          <div class="form-group">
            <label for="apellidos">Apellidos:</label>
            <input type="text" class="form-control" id="apellidos"  value="<%=usuario.getApellidos() %>" readonly>
          </div>
          <div class="form-group">
            <label for="direccion">Dirección:</label>
            <input type="text" class="form-control" id="direccion" value="<%=usuario.getDireccion()%>" readonly>
          </div>
          <div class="form-group">
            <label for="telefono">Teléfono:</label>
            <input type="tel" class="form-control" id="telefono" value="<%=usuario.getTelefono() %>" readonly>
          </div>
          <div>
          <br>
          <button type="submit" class="list-group-item list-group-item-action" >Cerrar Sesion</button>
          </div>
        </form>
      </div>

      <div class="col-md-6">
        <h3>Acciones</h3>
        <div class="list-group">
          <button type="button" class="list-group-item list-group-item-action" onclick="Cargar('./html/datosUsuario.jsp','inicio')">Modificar datos</button>
          <button type="button" class="list-group-item list-group-item-action" onclick="Cargar('./html/pedidosUsuario.jsp','inicio')">Mis pedidos</button>
          <button type="button" class="list-group-item list-group-item-action" onclick="Cargar('./html/carrito.jsp','inicio')">Mi carrito</button>
          
        </div>
      </div>
    </div>
  </div>
	

</body>
</html>