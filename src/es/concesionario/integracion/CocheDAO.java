package es.concesionario.integracion;

import java.sql.Connection;
//import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.concesionario.modelo.Coche;

public class CocheDAO { // esta es la Clase que se va a comunicar con la BBDD
    
	private Connection cx;
	
	//creamos 2 métodos (1 conectar 2 desconectar)
	
	private void conectar()
	{
		try 
		{// 2. Obtener la conexión dándole las credenciales. 
         Class.forName("com.mysql.jdbc.Driver");
		 cx= DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionario","root","root");
         cx.setAutoCommit(false);
        } catch (SQLException e) {e.printStackTrace();} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void desconectar()
	{     
	try{cx.close();} 
	catch (SQLException e){e.printStackTrace();}
    }
	
	public int darAlta(Coche coche) 
	{
		int idRetornar = 0;
		try 
		{
			// 1. Conectar
			conectar();
			
			// 2. preparar la consulta sql
			// "PreparedStatement" es un objeto que permite desde Java
			//  construir instrucciones SQL
			PreparedStatement ps;
			ps = cx.prepareStatement("INSERT INTO vehiculo VALUES(?, ?, ?, ?, ?, ?, ?)");
			
			// 2.1 setear los interrogantes
			ps.setInt(1, 0); // el "0" podría contener "99999"
			                 // porque lo calcula MySQL
			ps.setString(2, coche.getMatricula());
			ps.setString(3, coche.getMarca());
			ps.setString(4, coche.getModelo());
			ps.setString(5, coche.getColor());
			ps.setInt(6, coche.getNumcaballos());
			ps.setBoolean(7, coche.isMarchas());
			
			// 3. ejecutar la consulta sql
			// ps.executeQuery ... para "Select" que no modifica la BBDD
			int filasAfectadas = ps.executeUpdate();
			// 4. hacer el commit
			cx.commit();
						
			// no sabemos si el insert irá ok...
			if(filasAfectadas>=1)
			  {idRetornar=ultimoId();}
			else
			  {idRetornar=filasAfectadas;}
			
			// 5. cerrar la conexión
			desconectar();

		} catch (SQLException e){e.printStackTrace();}
				
		return idRetornar; // ojo!
	}
	public Coche consultarUno(int id) 
	{   Coche c=new Coche();
		try {
			// 1. Conectar
			conectar();
			// 2. preparar la consulta sql
			// "PreparedStatement" es un objeto que permite desde Java
			//  construir instrucciones SQL
			PreparedStatement ps;
			ps = cx.prepareStatement("SELECT * FROM vehiculo WHERE ID=?");
			// 2.1 setear los interrogantes
			ps.setInt(1, id);
			// 3. ejecutar la consulta sql que devuelve 1 ResultSet
			ResultSet rs = ps.executeQuery();
			//int filasAfectadas = rs.executeQuery(); //) ... para "Select" que no modifica la BBDD
					
			// 4. llenar el objeto coche con los datos de
			// respuesta de la Base de Datos que viene en 
			// un objeto del tipo ResultSet "rs".
			// Comprobamos si la consulta devuelve al menos 1 elemento...
			// Como la consulta la hacemos con la clave principal
			// sabemos que devolverá 1 sola fila por eso nos sirve
			// el "if" si no deberíamos usar "while" 
			
			if(rs.next())
			{// viene al menos 1 fila (tiene un next)
			   c.setId(rs.getInt("id"));
			   c.setMatricula(rs.getString("matricula"));
			   c.setMarca(rs.getString("marca"));
			   c.setModelo(rs.getString("modelo"));
			   c.setColor(rs.getString("color"));
			   c.setNumcaballos(rs.getInt("numcaballos"));
			   c.setMarchas(rs.getBoolean("marchas"));
			}  
			
			// 5. cerrar la conexión
			desconectar();
	} catch (SQLException e) {e.printStackTrace();}
	  return c;
  }
	
	public ArrayList<Coche> consultarTodos() {
		ArrayList<Coche> coches=new ArrayList<Coche>();
		try {
		// 1 conectar
		conectar();
		// 2 preparar la consulta
		PreparedStatement ps;
		
			ps = cx.prepareStatement("SELECT * FROM vehiculo");
		
		// 2.1 setear los interrogantes
		// en este caso no tenemos interrogantes porque no usamos where...
		
		// 3 ejecutar la consulta
		ResultSet rs = ps.executeQuery();
		
		// 4 bajar el resultado de la consulta y ponerlo en el ArrayList
		while(rs.next()){
			// entre el objeto "rs" y el objeto "arrayList"
			// me creo un objeto intermedio tipo Coche
			Coche c = new Coche();
			// muevo cada elemento del "rs" al objeto intermedio tipo Coche
			c.setId(rs.getInt("id"));
			c.setMatricula(rs.getString("matricula"));
			c.setMarca(rs.getString("marca"));
			c.setModelo(rs.getString("modelo"));
			c.setColor(rs.getString("color"));
			c.setNumcaballos(rs.getInt("numcaballos"));
			c.setMarchas(rs.getBoolean("marchas"));
			// muevo cada elemento del objeto intermedio tipo Coche
			// al objeto "arrayList"
			coches.add(c);
		}
		
		// 5 desconectar
		   desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    
		} return coches;
	}		
	public int ultimoId(){
		int idRecuperado=0;
		try {
			// 1 conectar
			conectar();
			// 2 preparar la consulta
			PreparedStatement ps;
			
			ps = cx.prepareStatement("SELECT MAX(id) as ultimoId FROM vehiculo");
			
			// 2.1 setear los interrogantes
			// en este caso no tenemos interrogantes porque no usamos where...
			
			// 3 ejecutar la consulta
			ResultSet rs = ps.executeQuery();
			
			// 4. llenar el objeto Coche con los datos de
			// respuesta de la Base de Datos que viene en 
			// un objeto del tipo ResultSet "rs".
			// Comprobamos si la consulta devuelve al menos 1 elemento...
			// Como la consulta la hacemos con max(id)
			// sabemos que devolverá 1 sola fila por eso nos sirve
						
		    if(rs.next())
			{// viene al menos 1 fila (tiene un next)
				idRecuperado=rs.getInt("ultimoId");
			}  
			
			// 5 desconectar
			   desconectar();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		return idRecuperado;
	}
	public ArrayList<Coche> consultarMatricula(String matricula) {
		ArrayList<Coche> coches=new ArrayList<Coche>();
		try {
		// 1 conectar
		conectar();
		// 2 preparar la consulta
		PreparedStatement ps = cx.prepareStatement("SELECT * FROM vehiculo WHERE matricula LIKE ?");
		// 2.1 setear los interrogantes
		// en este caso no tenemos interrogantes porque no usamos where...
		ps.setString(1, "%" + matricula + "%");
		// 3 ejecutar la consulta
		ResultSet rs = ps.executeQuery();
		
		// 4 bajar el resultado de la consulta y ponerlo en el ArrayList
		while(rs.next()){
			// entre el objeto "rs" y el objeto "arrayList"
			// me creo un objeto intermedio tipo Coche
			Coche c = new Coche();
			// muevo cada elemento del "rs" al objeto intermedio tipo Coche
			c.setId(rs.getInt("id"));
			c.setMatricula(rs.getString("matricula"));
			c.setMarca(rs.getString("marca"));
			c.setModelo(rs.getString("modelo"));
			c.setColor(rs.getString("color"));
			c.setNumcaballos(rs.getInt("numcaballos"));
			c.setMarchas(rs.getBoolean("marchas"));
			// muevo cada elemento del objeto intermedio tipo Coche
			// al objeto "arrayList"
			coches.add(c);
		}
		
		// 5 desconectar
		   desconectar();
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return coches;
    }
	public int borrar(int id) {
		int filasAfectadas = 0;
		try 
		{   // 1. Conectar
			conectar();
			
			// 2. preparar la consulta sql
			// "PreparedStatement" es un objeto que permite desde Java
			//  construir instrucciones SQL
			PreparedStatement ps;
			ps = cx.prepareStatement("DELETE FROM vehiculo WHERE id=?");
			
			// 2.1 setear los interrogantes
			ps.setInt(1, id); // el id que viene de fuera
			
			// 3. ejecutar la consulta sql
			filasAfectadas = ps.executeUpdate();
			
			// 4. hacer el commit
			cx.commit();
			
			// 5. cerrar la conexión
			desconectar();

		} catch (SQLException e){e.printStackTrace();}
				
		return filasAfectadas; //
	}
	public int actualizar(int id, 
			              String matricula,
			              String marca, 
			              String modelo, 
			              String color, 
			              int numcaballos,
			              Boolean marchas) 
	{
		int idRetornar = 0;
		try 
		{
			// 1. Conectar
			conectar();
			
			// 2. preparar la consulta sql
			// "PreparedStatement" es un objeto que permite desde Java
			//  construir instrucciones SQL
			PreparedStatement ps;
			ps = cx.prepareStatement("UPDATE vehiculo SET matricula=?, marca=?, modelo=?, color=?, numcaballos=?, marchas=? WHERE ID=?");
			
			// 2.1 setear los interrogantes
			
			ps.setString(1, matricula);
			ps.setString(2, marca);
			ps.setString(3, modelo);
			ps.setString(4, color);
			ps.setInt(5, numcaballos);

			// En "Alta" = ps.setBoolean(7, coche.isMarchas());
			ps.setBoolean(6, marchas);
			
			ps.setInt(7, id); // el id que viene de fuera
			
			// 3. ejecutar la consulta sql
			// ps.executeQuery ... para "Select" que no modifica la BBDD
			int filasAfectadas = ps.executeUpdate();
			// 4. hacer el commit
			cx.commit();
						
			// no sabemos si el insert irá ok...
			if(filasAfectadas>=1)
			{idRetornar=filasAfectadas;}
			
			// 5. cerrar la conexión
			desconectar();

		} catch (SQLException e){e.printStackTrace();}
				
		return idRetornar; // 
	}
}