<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, p2.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Carrito</title>
    <script src="js/completarCarrito.js"></script>
</head>
<body>
<script>
        carro =  JSON.parse(localStorage.getItem("savecarrito"));
    </script>
<div class="container-fluid p-5 bg-primary text-white text-center" >
  <h1>iMovil Phone</h1>
  <h4>La mejor marca de moviles del mercado</h4>
</div>
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
	<div class="container ">
		<h2 class="mb-3">Carrito de Compra</h2>
			<div class="table-responsive">
				<table class="table table-bordered">
					<thead class="thead-light">
						<tr>
            				<th>Producto</th>
							<th>Cantidad</th>
							<th>Precio Unitario</th>
							<th>Precio</th>
							<th>Eliminar</th>
						</tr>
					</thead>
					<tbody id="productos">
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3" class="text-right">Total:</td>
							<td id="preciototal">
							</td>
							<td><button class="btn btn-primary btn-block d-flex justify-content-between mt-3" type="button" onclick="EnviarCarrito('RecogerCarrito','inicio',carro);">
                        <span>Pagar</span>
                    </button></td>
						</tr>
					</tfoot>
				</table>
			</div>
    </div>
</body>
</html>