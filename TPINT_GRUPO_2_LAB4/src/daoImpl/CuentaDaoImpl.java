package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CuentaDao;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Genero;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Tipo_cuenta;


public class CuentaDaoImpl implements CuentaDao{
	
	private static final String readall = "SELECT * FROM cuentas INNER JOIN tipo_cuenta ON cuentas.id_tipo = tipo_cuenta.id_tipo INNER JOIN clientes "
			+ "ON cuentas.DNI = clientes.DNI INNER JOIN generos ON clientes.id_genero = generos.id_genero INNER JOIN "
			+ "localidades ON clientes.id_localidades = localidades.id INNER JOIN nacionalidades ON "
			+ "clientes.id_nacionalidad = nacionalidades.id INNER JOIN provincias ON clientes.id_provincia = provincias.id";
	private static final String insert = "INSERT INTO cuentas(CBU,id_tipo,DNI,fecha_creacion,nro_cuenta,saldo) values (?,?,?,?,?,?)";

	@Override
	public boolean insert(Cuenta cuenta) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, cuenta.getCBU());
			statement.setObject(2, cuenta.getId_tipo());
			statement.setObject(3, cuenta.getDNI());
			statement.setDate(4, cuenta.getFecha_creacion());
			statement.setString(5, cuenta.getNro_cuenta());
			statement.setFloat(6, cuenta.getSaldo());
			
			
			if(statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}


	@Override
	public boolean delete(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<Cuenta> readAll() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		
		Conexion conexion = Conexion.getConexion();
		try{
			
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readall);
	        ResultSet resultSet = statement.executeQuery();
		
			while(resultSet.next()){
				
				//clases necesarias para crear un obj cuenta
				Cuenta cuenta = new Cuenta();
				Tipo_cuenta tipoCuenta = new Tipo_cuenta();
				Genero genero = new Genero();
				Nacionalidad nacionalidad = new Nacionalidad();
				Provincia provincia = new Provincia();
				Localidad localidad = new Localidad();
				Cliente cliente = new Cliente();
				
				cuenta.setCBU(resultSet.getString("CBU"));
				tipoCuenta.setId_tipo(resultSet.getString(8));
				tipoCuenta.setDescripcion(resultSet.getString(9));
				cuenta.setId_tipo(tipoCuenta);
				cliente.setDNI(resultSet.getString("DNI"));
				genero.setId_genero(resultSet.getString(24));
				genero.setDescripcion(resultSet.getString(25));
				cliente.setId_genero(genero);
				nacionalidad.setId(resultSet.getInt(29));
				nacionalidad.setCode(resultSet.getShort(30));
				nacionalidad.setIso3166a1(resultSet.getString(31));
				nacionalidad.setIso3166a2(resultSet.getString(32));
				nacionalidad.setNombre_pais(resultSet.getString(33));
				cliente.setId_nacionalidad(nacionalidad);
				provincia.setId(resultSet.getInt("id_provincia"));
				provincia.setNombre_provincia(resultSet.getString(35));
				cliente.setId_provincia(provincia);
				localidad.setId(resultSet.getInt("id_localidades"));
				localidad.setId_provincia(provincia);
				localidad.setNombre_localidad(resultSet.getString("nombre_localidad"));
				cliente.setCUIL(resultSet.getString("CUIL"));
				cliente.setNombre(resultSet.getString("nombre"));
				cliente.setApellido(resultSet.getString("apellido"));
				cliente.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
				cliente.setDireccion(resultSet.getString("direccion"));
				cliente.setCorreo_electronico(resultSet.getString("correo_electronico"));
				cliente.setTelefono_primario(resultSet.getString("telefono_primario"));
				cliente.setTelefono_secundario(resultSet.getString("telefono_secundario"));
				cuenta.setDNI(cliente);
				cuenta.setFecha_creacion(resultSet.getDate("fecha_creacion"));
				cuenta.setNro_cuenta(resultSet.getString("nro_cuenta"));
				cuenta.setSaldo(resultSet.getFloat("saldo"));
				cuenta.setEstado(resultSet.getBoolean("estado"));
				
				lista.add(cuenta);
			}
			
		conexion.cerrarConexion();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return lista;
	}


	public ArrayList<Cuenta> getCuentasxDNI(String DNI) {
		
		String query = "SELECT * FROM cuentas where DNI = '"+ DNI + "'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		
		Conexion conexion = Conexion.getConexion();
		try{
			
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
		
			while(resultSet.next()){
			
			Cuenta cuenta = new Cuenta();
			Cliente cliente = new Cliente();
			ClienteDaoImpl clienteDImpl = new ClienteDaoImpl();
			
			cuenta.setCBU(resultSet.getString("CBU"));
			cliente = clienteDImpl.getClientexDNI(DNI);
			cuenta.setDNI(cliente);
			cuenta.setFecha_creacion(resultSet.getDate("fecha_creacion"));
			cuenta.setNro_cuenta(resultSet.getString("nro_cuenta"));
			cuenta.setSaldo(resultSet.getFloat("saldo"));
			cuenta.setEstado(resultSet.getBoolean("estado"));
			
			lista.add(cuenta);
			
			}
			
			conexion.cerrarConexion();
			
			}catch(Exception e){
				e.printStackTrace();
			}
		
			return lista;
		
		
	}

	@Override
	public Cuenta getCuentaxCBU(String CBU) {
	 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Cuenta cuenta = new Cuenta();
		Conexion conexion = Conexion.getConexion();
		
		try{
			
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readall);
	        ResultSet resultSet = statement.executeQuery();
		
			while(resultSet.next()){
				
				if(resultSet.getString("CBU").compareTo(CBU) == 0)
				{
				//clases necesarias para crear un obj cuenta
				Tipo_cuenta tipoCuenta = new Tipo_cuenta();
				Genero genero = new Genero();
				Nacionalidad nacionalidad = new Nacionalidad();
				Provincia provincia = new Provincia();
				Localidad localidad = new Localidad();
				Cliente cliente = new Cliente();
				
				cuenta.setCBU(resultSet.getString("CBU"));
				tipoCuenta.setId_tipo(resultSet.getString(8));
				tipoCuenta.setDescripcion(resultSet.getString(9));
				cuenta.setId_tipo(tipoCuenta);
				cliente.setDNI(resultSet.getString("DNI"));
				genero.setId_genero(resultSet.getString(24));
				genero.setDescripcion(resultSet.getString(25));
				cliente.setId_genero(genero);
				nacionalidad.setId(resultSet.getInt(29));
				nacionalidad.setCode(resultSet.getShort(30));
				nacionalidad.setIso3166a1(resultSet.getString(31));
				nacionalidad.setIso3166a2(resultSet.getString(32));
				nacionalidad.setNombre_pais(resultSet.getString(33));
				cliente.setId_nacionalidad(nacionalidad);
				provincia.setId(resultSet.getInt("id_provincia"));
				provincia.setNombre_provincia(resultSet.getString(35));
				cliente.setId_provincia(provincia);
				localidad.setId(resultSet.getInt("id_localidades"));
				localidad.setId_provincia(provincia);
				localidad.setNombre_localidad(resultSet.getString("nombre_localidad"));
				cliente.setCUIL(resultSet.getString("CUIL"));
				cliente.setNombre(resultSet.getString("nombre"));
				cliente.setApellido(resultSet.getString("apellido"));
				cliente.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
				cliente.setDireccion(resultSet.getString("direccion"));
				cliente.setCorreo_electronico(resultSet.getString("correo_electronico"));
				cliente.setTelefono_primario(resultSet.getString("telefono_primario"));
				cliente.setTelefono_secundario(resultSet.getString("telefono_secundario"));
				cuenta.setDNI(cliente);
				cuenta.setFecha_creacion(resultSet.getDate("fecha_creacion"));
				cuenta.setNro_cuenta(resultSet.getString("nro_cuenta"));
				cuenta.setSaldo(resultSet.getFloat("saldo"));
				cuenta.setEstado(resultSet.getBoolean("estado"));
				
				conexion.cerrarConexion();
				return cuenta;
				
				}
			}
			
		conexion.cerrarConexion();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return cuenta;
	
}

}






