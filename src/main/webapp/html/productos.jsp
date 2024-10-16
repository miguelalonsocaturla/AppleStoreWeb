<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, p2.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Productos</title>
</head>
<body>
<%
		AccesoBD con = AccesoBD.getInstance();
		List<ProductoBD> productos = con.obtenerProductosBD();%>
     <div class="container-fluid">
     <br>
        <div class="row row-cols-2  justify-content-center">
        <%
			for(ProductoBD producto : productos){
				int codigo = producto.getCodigo();
				String nombre = producto.getNombre();
				int precio = (int) producto.getPrecio();
				int unidades = producto.getUnidades();
				String imagen = producto.getImagen();
						
		%>
        <div class="col align-item-center text-center">
         <img id="imagen<%=codigo%>" src="<%=imagen%>" alt="<%=nombre%>" class="d-50 w-50">
            <br>
            <label id="nombre<%=codigo%>"><%=nombre%></label><br>
            <label id="precio<%=codigo%>"><%=precio%>€</label><br>
            <%
				if (unidades > 0) {
			%>
            <button id="<%=codigo%>" class="btn btn-primary boton-agregar" onclick="comprar(this)" type="submit">comprar</button>
            <%
				}else{
            %>
            <strong>Sin existencias, pronto restock.</strong>
            <%
				}
            %>
        </div>
        <%
			}
        %>
        
    </div>
</div>
</body>
</html>