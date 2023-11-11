package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.CallableStatement;


import dao.MovimientoDao;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Genero;
import entidades.Localidad;
import entidades.Movimiento;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Tipo_Movimiento;
import entidades.Tipo_cuenta;

public class MovimientoDaoImpl implements MovimientoDao {
	
	
	private static final String readall = "SELECT * FROM movimientos INNER JOIN tipo_movimiento ON movimientos.id_tipo = tipo_movimiento.id_tipo";	
	//private static final String update = "UPDATE movimientos SET  = ?,  = ? WHERE CBU = ?";
	private static final String movimientosXcuenta = "SELECT * FROM movimientos " + 
			"INNER JOIN tipo_movimiento ON movimientos.id_tipo = tipo_movimiento.id_tipo " + 
			"WHERE CBU = ?";
	
	@Override
	public boolean insert(Movimiento movimiento) {
		String query =  "CALL AgregarMovimiento(?, ?, ?, ?, ?, ?, ?)";
		
	
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try { 
			CallableStatement statement = conexion.prepareCall(query);
			
			statement.setString(1, movimiento.getTipoMovimiento().getId_tipo());
			statement.setString(2, movimiento.getCBU().getCBU());
			statement.setString(3, movimiento.getCBU_Destino().getCBU());
			statement.setDate(4, movimiento.getFecha_Transaccion());
			statement.setFloat(5, movimiento.getImporte());
			statement.setString(6, movimiento.getDetalle());
			statement.setBoolean(7, movimiento.getEstado());
			


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
	public boolean delete(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/*public ArrayList<Movimiento> readAll() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Movimiento> lista = new ArrayList<Movimiento>();
		
		Conexion conexion = Conexion.getConexion();
		try{
			
			PreparedStatement statement = conexion.getSQLConexion().prepareStatement(readall);
	        ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				
				//habria que cargar cada objeto como en cuenta'
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
				
				
				

				//clases necesarias para crear un obj Movimiento
				Movimiento movimiento = new Movimiento();
				// Cargar los atributos
				Cuenta cbu = movimiento.getCBU();
				Cuenta cbuDestino = movimiento.getCBU_Destino();
				Date fechaTransaccion = movimiento.getFecha_Transaccion();
				float importe = movimiento.getImporte();
				String detalle = movimiento.getDetalle();
			Tipo_Movimiento tipoMovimiento = movimiento.getTipoMovimiento();
				boolean estado = movimiento.getEstado();
														
				lista.add(movimiento);
			}
			
		conexion.cerrarConexion();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}*/
	
public int getUltimoID() {
		
	    int UltimoId = 0;

	    Conexion conexion = Conexion.getConexion();

	    try {
	        PreparedStatement statement = conexion.getSQLConexion().prepareStatement("SELECT MAX(id_movimiento) AS maxId FROM movimientos");
	        ResultSet resultSet = statement.executeQuery();
	        
	
	        if (resultSet.next()) {
	            UltimoId = resultSet.getInt("maxId");
	        }

	  
	        conexion.cerrarConexion();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return UltimoId;
	}



public ArrayList<Movimiento> getMovimientosXCuenta (Cuenta cuentaAux) {
	
	String cbu = cuentaAux.getCBU();
	Tipo_Movimiento tipoMovimiento =new Tipo_Movimiento();
	Cuenta cuentaDest =new Cuenta();
	ArrayList<Movimiento> movimientosCliente = new ArrayList<Movimiento>();	
	
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
			
	Conexion conexion = Conexion.getConexion();
	
	try{
		PreparedStatement statement = conexion.getSQLConexion().prepareStatement(movimientosXcuenta);
		statement.setString(1, cbu);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()){
			
										
					Movimiento movimiento = new Movimiento();					
										
					tipoMovimiento.setId_tipo(resultSet.getString("id_tipo"));
					tipoMovimiento.setDescripcion((resultSet.getString("descripcion")));	
					cuentaDest.setCBU(resultSet.getString("CBU_Destino"));
					
					movimiento.setCBU(cuentaAux);
					movimiento.setCBU_Destino(cuentaDest);	
					movimiento.setDetalle(resultSet.getString("detalle"));					
					movimiento.setEstado(resultSet.getBoolean("estado"));					
					movimiento.setFecha_Transaccion(resultSet.getDate("fecha"));
					movimiento.setImporte(resultSet.getInt("importe"));
					movimiento.setTipoMovimiento(tipoMovimiento);	
					
					
					movimientosCliente.add(movimiento);
					
				}

			

		
		
	conexion.cerrarConexion();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return movimientosCliente;

	
}


}




