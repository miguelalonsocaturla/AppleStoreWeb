package p2;
import java.sql.*;
import java.util.*;

public class AccesoBD {
	private static AccesoBD instanciaUnica = null;
	private Connection conexionBD = null;

	public static AccesoBD getInstance() {
		if (instanciaUnica == null) {
			instanciaUnica = new AccesoBD();
		}
		return instanciaUnica;
	}
	
	private AccesoBD() {
		abrirConexionBD();
		}
	
	public void abrirConexionBD() {
		if (conexionBD == null)
		{ // daw es el nombre de la base de datos que hemos creado con anterioridad.
			String nombreConexionBD = "jdbc:mysql://localhost/daw";
			try { // root y sin clave es el usuario por defecto que crea XAMPP.
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexionBD = DriverManager.getConnection(nombreConexionBD,"root","");
				}
			catch(Exception e) {
				System.err.println("No se ha podido conectar a la base de datos");
				System.err.println(e.getMessage());
			}
		}
	}
	
	public void cerrarConexionBD() {
		if (conexionBD != null) {
			try {
				conexionBD.close();
				conexionBD = null;
			}
			catch(Exception e) {
				System.err.println("No se ha podido desconectar de la base de datos");
				System.err.println(e.getMessage());
			}
		}
	}
	
	public boolean comprobarAcceso() {
		abrirConexionBD();
		boolean res = (conexionBD != null);
		cerrarConexionBD();
		return res;
	}
	
	public int ComprobarUsuarioBD(String correo, String contraseña) 
    {
		
        abrirConexionBD();
        
        try 
        {
            String con= "SELECT id FROM usuarios WHERE correo=? and contraseña=?";// Consulta si existe un correo/contraseña
            PreparedStatement s = conexionBD.prepareStatement(con);
            s.setString(1,correo);
            s.setString(2,contraseña);
            
            ResultSet resultado = s.executeQuery();//El resultado de la consulta se almacena en esta variable
            
            if ( resultado.next() ) // Existe, devuelve true
            {
            	return resultado.getInt("id");
            }
            
        }
        catch(Exception e) { // Err en la conexi�n con la base de datos
	        System.err.println("Error verificando usuario/clave");
	        System.err.println(e.getMessage());
        
        }
        return -1;
    }
	
public List<ProductoBD> obtenerProductosBD() {
		
		abrirConexionBD();
		ArrayList<ProductoBD> productos = new ArrayList<>();
		
		try {
		
			String con = "SELECT codigo,nombre,precio,unidades,imagen1 FROM productos";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			
			while(resultado.next()){
				
				ProductoBD producto = new ProductoBD();
				producto.setCodigo(resultado.getInt("codigo"));
				producto.setNombre(resultado.getString("nombre"));
				producto.setPrecio(resultado.getFloat("precio"));
				producto.setUnidades(resultado.getInt("unidades"));
				producto.setImagen(resultado.getString("imagen1"));
				productos.add(producto);
			}
		}
		catch(Exception e) {
			System.err.println("Error ejecutando la consulta a la base de datos");
			System.err.println(e.getMessage());
		}
		
		return productos;
	}
	
	public List<PedidoBD> obtenerPedidosBD() {
		
		abrirConexionBD();
		ArrayList<PedidoBD> pedidos = new ArrayList<>();
		
		try {
		
			String con = "SELECT codigo,persona,fecha,importe,estado FROM pedidos";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			
			while(resultado.next()){
				
				PedidoBD pedido = new PedidoBD();
				pedido.setCodigo(resultado.getInt("codigo"));
				pedido.setPersona(resultado.getInt("persona"));
				pedido.setImporte(resultado.getInt("importe"));
				pedido.setFecha(resultado.getString("fecha"));
				pedido.setEstado(resultado.getString("estado"));
				pedidos.add(pedido);
			}
		}
		catch(Exception e) {
		System.err.println("Error ejecutando la consulta a la base de datos");
		System.err.println(e.getMessage());
		}
		
		return pedidos;
	}
	
public void cancelarPedidoBD(int codigo) {
		
		abrirConexionBD();
		
		try {
		
			String con = "UPDATE  pedidos SET estado='Cancelado' where codigo='"+codigo+"'";
			Statement s = conexionBD.createStatement();
			s.executeUpdate(con);
			
		}
		catch(Exception e) {
		System.err.println("Error ejecutando la consulta a la base de datos");
		System.err.println(e.getMessage());
		}
		
	}
	
	
public List<PedidoBD> obtenerPedidosUsuarioBD(int idUsuario) {
		
		abrirConexionBD();
		ArrayList<PedidoBD> pedidos = new ArrayList<>();
		
		try {
		
			String con = "SELECT codigo,persona,fecha,importe,estado FROM pedidos where persona='"+idUsuario+"'";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			
			while(resultado.next()){
				
				PedidoBD pedido = new PedidoBD();
				pedido.setCodigo(resultado.getInt("codigo"));
				pedido.setPersona(resultado.getInt("persona"));
				pedido.setImporte(resultado.getInt("importe"));
				pedido.setFecha(resultado.getString("fecha"));
				pedido.setEstado(resultado.getString("estado"));
				pedidos.add(pedido);
			}
		}
		catch(Exception e) {
		System.err.println("Error ejecutando la consulta a la base de datos");
		System.err.println(e.getMessage());
		}
		
		return pedidos;
	}
	
public List<DetallePedidoBD> obtenerDetalleBD() {
		
		abrirConexionBD();
		ArrayList<DetallePedidoBD> detalles = new ArrayList<>();
		
		try 
        {
            String con;
           
            Statement s = conexionBD.createStatement();
            
             con = "SELECT codigo_pedido,codigo_producto,unidades,precio_unitario FROM detallepedido";
            ResultSet resultado = s.executeQuery(con);
            
            while(resultado.next())
            {
                DetallePedidoBD detallepedido = new DetallePedidoBD();
                detallepedido.setCodigo_pedido(resultado.getInt("codigo_pedido"));
                detallepedido.setCodigo_producto(resultado.getInt("codigo_producto"));
                detallepedido.setPrecio_unitario(resultado.getInt("precio_unitario"));
                detallepedido.setUnidades(resultado.getInt("unidades"));         
                detalles.add(detallepedido);
            }
        }
        catch(Exception e) 
        {
            System.err.println("Error ejecutando la consulta a la base de datos");
            System.err.println(e.getMessage());
        }
        return detalles;
	}

public List<ProductoBD> obtenerProductosPedidoBD(int codigo_pedido ) {
	
	abrirConexionBD();
	ArrayList<ProductoBD> productos = new ArrayList<>();
	
	try 
    {
        String con;
       
        Statement s = conexionBD.createStatement();
        
         con = "SELECT p.codigo,p.nombre,p.imagen1,p.precio,d.unidades FROM detallepedido d, productos p WHERE d.codigo_producto=p.codigo and d.codigo_pedido='"+codigo_pedido+"'";
        ResultSet resultado = s.executeQuery(con);
        
        while(resultado.next())
        {
            ProductoBD producto = new ProductoBD();
            producto.setCodigo(resultado.getInt("codigo"));
            producto.setNombre(resultado.getString("nombre"));
            producto.setImagen(resultado.getString("imagen1"));
            producto.setPrecio(resultado.getInt("precio"));
            producto.setUnidades(resultado.getInt("unidades"));         
            productos.add(producto);
        }
    }
    catch(Exception e) 
    {
        System.err.println("Error ejecutando la consulta a la base de datos");
        System.err.println(e.getMessage());
    }
    return productos;
}
	
public List<UsuarioBD> obtenerUsuariosBD() {
		
		abrirConexionBD();
		ArrayList<UsuarioBD> usuarios = new ArrayList<>();
		
		try {
		
			String con = "SELECT id,nombre,apellidos,direccion,ciudad,cp,telefono,correo,contraseña,estado,admin FROM usuarios";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			
			while(resultado.next()){
				
				UsuarioBD usuario = new UsuarioBD();
				usuario.setId(resultado.getInt("id"));
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setApellidos(resultado.getString("apellidos"));
				usuario.setDireccion(resultado.getString("direccion"));
				usuario.setCiudad(resultado.getString("ciudad"));
				usuario.setCP(resultado.getInt("cp"));
				usuario.setTelefono(resultado.getInt("telefono"));
				usuario.setCorreo(resultado.getString("correo"));
				usuario.setContraseña(resultado.getString("contraseña"));
				usuario.setEstado(resultado.getInt("estado"));
				usuario.setAdmin(resultado.getInt("admin"));
				usuarios.add(usuario);
			}
		}
		catch(Exception e) {
			System.err.println("Error ejecutando la consulta a la base de datos");
			System.err.println(e.getMessage());
		}
		
		return usuarios;
	}
	
public UsuarioBD obtenerUsuarioActualBD(String correo) {
		
		abrirConexionBD();
		UsuarioBD usuario = new UsuarioBD();
		
		try {
		
			String con = "SELECT id,nombre,apellidos,direccion,ciudad,cp,telefono,correo,contraseña,estado,admin FROM usuarios where correo = '" + correo + "'";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			
			while(resultado.next()){
				
				
				usuario.setId(resultado.getInt("id"));
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setApellidos(resultado.getString("apellidos"));
				usuario.setDireccion(resultado.getString("direccion"));
				usuario.setCiudad(resultado.getString("ciudad"));
				usuario.setCP(resultado.getInt("cp"));
				usuario.setTelefono(resultado.getInt("telefono"));
				usuario.setCorreo(resultado.getString("correo"));
				usuario.setContraseña(resultado.getString("contraseña"));
				usuario.setEstado(resultado.getInt("estado"));
				usuario.setAdmin(resultado.getInt("admin"));
			}
		}
		catch(Exception e) {
			System.err.println("Error ejecutando la consulta a la base de datos");
			System.err.println(e.getMessage());
		}
		
		return usuario;
	}


public void  añadirUsuarioBD(UsuarioBD user) 
{
     
     abrirConexionBD();

    try 
    {
        String con;
        Statement s = conexionBD.createStatement();
       con="INSERT into usuarios(nombre,apellidos,direccion,ciudad,cp,telefono,correo,contraseña,admin,estado) VALUES('"+user.getNombre()+"','" + user.getApellidos()+"','"+user.getDireccion()+"','"+ user.getCiudad()+"','"
        +user.getCP()+"','"+user.getTelefono()+"','"+user.getCorreo()+"','"+user.getContraseña()+"',\"1\",\"1\")";
      s.executeUpdate(con);         
    }
    catch(Exception e) 
    {
        System.err.println("Error ejecutando la consulta a la base de datos");
        System.err.println(e.getMessage());
    } 
}

public void  editarUsuarioBD(UsuarioBD usuario) 
{
    abrirConexionBD();
   
    try 
    {
        String con;
        Statement s = conexionBD.createStatement();
        
        con = "UPDATE usuarios SET nombre = '" + usuario.getNombre() + "', apellidos = '" + usuario.getApellidos() + "', telefono = '"+ usuario.getTelefono()
              + "', cp = '" + usuario.getCP() + "', ciudad = '"+ usuario.getCiudad() + "', direccion = '"+ usuario.getDireccion() + "' WHERE correo='" + usuario.getCorreo() + "'";

         s.executeUpdate(con);
   
    }
    catch(Exception e) 
    {
        System.err.println("Error ejecutando la consulta a la base de datos");
        System.err.println(e.getMessage());
    }
    
}

public int nuevoPedido(PedidoBD pedido) {
    abrirConexionBD();
    int id = 0;
    
    try {
        String con;
        Statement s = conexionBD.createStatement();
        con = "INSERT INTO pedidos(persona,fecha,importe,estado) VALUES ('" + pedido.getPersona() + "'"
                + ",'" + java.time.LocalDate.now() + "', '" + pedido.getImporte() + "', '"+pedido.getEstado()+"')";
        
        s.executeUpdate(con, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = s.getGeneratedKeys();
        if (rs.next()){
            id = rs.getInt(1);
        }
        

    } catch (SQLException e) {
        System.err.println("Error registrando un nuevo pedido");
        System.err.println(e.getMessage());
    }
    
    return id;
}

public void nuevoDetallePedido(DetallePedidoBD detallepedido) {
    abrirConexionBD();

    try {

        String con;
        Statement s = conexionBD.createStatement();
        con = "INSERT INTO detallepedido (codigo_pedido, codigo_producto,  unidades, precio_unitario) VALUES ('" + detallepedido.getCodigo_pedido() + "', '" + detallepedido.getCodigo_producto() + "',  '" + detallepedido.getUnidades() + "','" + detallepedido.getPrecio_unitario() + "' )";
        s.executeUpdate(con);
        

    } catch (SQLException e) {
        System.err.println("Error registrando un nuevo detallepedido");
        System.err.println(e.getMessage());
    }
}

public void ajusteStock(int codigo, int unidades) {
	abrirConexionBD();
	
	try {
	
		String con = "UPDATE productos SET unidades=unidades-'"+unidades+"' where codigo='"+codigo+"'";
		Statement s = conexionBD.createStatement();
		 s.executeUpdate(con);
		
	}
	catch(Exception e) {
		System.err.println("Error ejecutando la consulta a la base de datos");
		System.err.println(e.getMessage());
	}
	
	
	
	
}

}
