package p2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RecogerCarrito
 */
@WebServlet("/RecogerCarrito")
public class RecogerCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccesoBD con = AccesoBD.getInstance();
		HttpSession session = request.getSession(true);
		List<ProductoBD> productos = con.obtenerProductosBD();
		ArrayList<ProductoBD> carrito = new ArrayList<ProductoBD>();
		JsonReader jsonReader = Json.createReader(new InputStreamReader(request.getInputStream())); // Extraemos para
																									// leer
		JsonArray carr = jsonReader.readArray();
		jsonReader.close();
		String mensaje = "";
		if (!carr.isEmpty()) {
			if (session.getAttribute("correo") != null) {
				for (int i = 0; i < carr.size(); i++) {

					if (!carr.get(i).getValueType().toString().equals("NULL")) {
						ProductoBD nuevo = new ProductoBD();
						nuevo.setCodigo(i);
						nuevo.setNombre(carr.get(i).asJsonObject().getString("nombre"));
						nuevo.setPrecio(carr.get(i).asJsonObject().getInt("precio"));
						nuevo.setUnidades(carr.get(i).asJsonObject().getInt("cantidad"));
						nuevo.setImagen(carr.get(i).asJsonObject().getString("img"));

						for (ProductoBD producto : productos) {
							if (nuevo.getCodigo() == producto.getCodigo()) {
								if (producto.getUnidades() - nuevo.getUnidades() >= 0) {
									carrito.add(nuevo);
								} else {
									mensaje = mensaje + "La cantidad de unidades del " + producto.getNombre()
											+ " es mayor al stock que tenemos.\n Modifica el carrito para que el numero maximo sea "
											+ producto.getUnidades() + ". <br>";

								}
							}
						}
					}
				}
				if ((ArrayList<?>) session.getAttribute("carro") != null) {
					session.removeAttribute("carro");
				}

				if (mensaje == "") {
					session.setAttribute("carro", carrito);
					response.sendRedirect("./html/resguardo.jsp");
				} else {
					session.setAttribute("carro", carrito);
					session.setAttribute("mensaje", mensaje);
					response.sendRedirect("./html/carrito.jsp");
				}

			} else {
				mensaje = "Has de iniciar sesion o registrarte primero.";
				session.setAttribute("mensaje", mensaje);
				response.sendRedirect("./html/inicioSesion.jsp");
			}
		} else {
			mensaje = "Añade primero algun producto al carrito.";
			session.setAttribute("mensaje", mensaje);
			response.sendRedirect("./html/inicioSesion.jsp");
		}

	}

}
