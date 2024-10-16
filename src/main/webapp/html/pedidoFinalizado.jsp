<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List, p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Pedido Finalizado</title>
</head>
<body>
	<script type="text/javascript">
		borrarCarro();
		localStorage.clear();
	</script>
	<div class="container-fluid p-5 bg-primary text-white text-center">
		<h1>iMovil Phone</h1>
		<h4>La mejor marca de moviles del mercado</h4>
	</div>
	<%
	int codigoPedido = (Integer) session.getAttribute("codigoPedido");
	%>

	<div
		class="alert alert-success alert-dismissible fade show align-items-center">
		<br /> <strong><h1>EL PEDIDO SE HA COMPLETADO</h1> <br />
			<h4>
				El identificador de su pedido es el siguiente: #<%=codigoPedido%>
				y su estado es: "En proceso".
			</h4> </strong> <br /> Gracias por comprar aqui. <br />
		<button type="button" class="btn bg-dark text-white" onclick="Cargar('./html/inicio.html','inicio');">ACEPTAR</button>
	</div>


</body>
</html>