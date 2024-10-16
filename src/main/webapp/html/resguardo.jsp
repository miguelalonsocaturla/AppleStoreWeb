<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List, p2.*,java.util.ArrayList,java.util.List"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Resguardo</title>
<link href="./css/carrito.css" rel="stylesheet">
</head>
<body>
	<form method="post"
		onsubmit="ProcesarForm(this, 'Tramitacion', 'inicio'); return false;">
		<div class="container">
			<h2 class="mb-3">Tu resguardo</h2>
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
						ProductoBD producto = new ProductoBD();
						ArrayList<ProductoBD> carrito = new ArrayList<ProductoBD>();
						ArrayList<ProductoBD> productos = new ArrayList<ProductoBD>();
						carrito = (ArrayList<ProductoBD>) session.getAttribute("carro");
						int preciototal = 0;
						
						for (int i = 0; i < carrito.size(); i++) {
							producto = carrito.get(i);
							preciototal += producto.getPrecio();
							productos.add(producto);
						%>
						<tr>
							<td><%=producto.getNombre()%> <img class="rounded" alt="1"
								src="<%=producto.getImagen()%>" width="60"></td>
							<td><%=producto.getUnidades()%></td>
							<td><%=producto.getPrecio() / producto.getUnidades()%></td>
							<td><%=producto.getPrecio()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3" class="text-right">Total:</td>
							<td><input type="hidden" name="preciototal"
								value="<%=preciototal%>"><%=preciototal%></td>

						</tr>
					</tfoot>
				</table>
			</div>
			<%
			AccesoBD con = AccesoBD.getInstance();
			String correo = (String) session.getAttribute("correo");
			UsuarioBD usuario = con.obtenerUsuarioActualBD(correo);
			%>
			<div class="row">
				<div class="col-md-6">
					<div class="d-flex justify-content-between align-items-center">
						<span><strong>DETALLES DE LA DIRECCION DE ENVIO</strong></span>
					</div>

					<div>
						<label class="tarjetainfo">Dirección</label> <input type="text"
							class="form-control" id="direccion" name="direccion"
							value="<%=usuario.getDireccion()%>">
					</div>
					<div>
						<label class="tarjetainfo">Ciudad</label> <input type="text"
							class="form-control " id="ciudad" name="ciudad"
							value="<%=usuario.getCiudad()%>">
					</div>
					<div>
						<label class="tarjetainfo">Codigo Postal</label> <input
							type="text" class="form-control " id="cp" name="cp"
							value="<%=usuario.getCP()%>">
					</div>
				</div>
				<div class="col-md-6">

					<div class="d-flex justify-content-between align-items-center">
						<span><strong>DETALLES DE LA TARJETA DE CREDITO</strong></span>
					</div>

					<div>
						<label class="tarjetainfo">Nombre de la tarjeta</label> <input
							type="text" class="form-control " placeholder="Nombre..."
							required>
					</div>
					<div>
						<label class="tarjetainfo">Número de la tarjeta</label> <input
							type="text" class="form-control "
							placeholder="0000 0000 0000 0000" required>
					</div>
					<div class="row">
						<div class="col-md-6">
							<label class="tarjetainfo">Fecha de caducidad</label> <input
								type="text" class="form-control " placeholder="MM/YY" required>
						</div>
						<div class="col-md-6">
							<label class="tarjetainfo">CVV</label> <input type="text"
								class="form-control " placeholder="000" required>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<button
								class="btn btn-primary btn-block d-flex justify-content-between mt-3"
								type="submit">Confirmar Compra</button>
						</div>
						<div class="col-md-6">
							<button
								class="btn btn-danger btn-block d-flex justify-content-between mt-3"
								onclick="Cargar('./html/carrito.jsp','inicio')">Cancelar</button>
						</div>
					</div>
				</div>
			</div>
	</form>
</body>
</html>