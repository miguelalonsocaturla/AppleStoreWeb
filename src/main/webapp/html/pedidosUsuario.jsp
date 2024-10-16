<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List, p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Pedidos Usuario</title>
</head>
<body>
	<h2>Tus pedidos</h2>
	<%
	AccesoBD con = AccesoBD.getInstance();
	List<PedidoBD> pedidos = con.obtenerPedidosUsuarioBD((int) session.getAttribute("id"));
	for (PedidoBD pedido : pedidos) {
		String estado=pedido.getEstado();
	%>
	<div class="container">
		<div class="row">
			<div class="col">
				<h2 class="mb-3">
					Pedido Nº
					<%=pedido.getCodigo()%></h2>
			</div>
			<div class="col">
				<h2 class="mb-3">
					Estado: <%=estado%></h2>
			</div>
			<% if(estado.equals("En proceso")){ %>
			<div class="col">
				<form method="post" onsubmit="ProcesarForm(this,'CancelarPedido','inicio');return false">
					<input type="hidden" value="<%=pedido.getCodigo()%>"
						name="codigopedido" id="codigopedido">
					<button class="btn btn-danger" type="submit">Cancelar
						Pedido</button>
				</form>
			</div>
			<%} %>
		</div>
		<div class="table-responsive">
			<table class="table table-bordered">
				<thead class="thead-light">
					<tr>
						<th>Producto</th>
						<th>Cantidad</th>
						<th>Precio</th>
						<th>Total</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<ProductoBD> productos = con.obtenerProductosPedidoBD(pedido.getCodigo());
					int preciototal = 0;
					for (ProductoBD producto : productos) {
						preciototal += (producto.getPrecio() * producto.getUnidades());
					%>
					<tr>
						<td><%=producto.getNombre()%> <img class="rounded" alt="1"
							src="<%=producto.getImagen()%>" width="60"></td>
						<td><%=producto.getUnidades()%></td>
						<td><%=producto.getPrecio()%>€</td>
						<td><%=(producto.getPrecio() * producto.getUnidades())%>€</td>
					</tr>
					<%
					}
					%>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="3" class="text-right">Total:</td>
						<td><%=preciototal%>€</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<%
	}
	%>

</body>
</html>