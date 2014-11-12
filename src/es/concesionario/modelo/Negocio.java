package es.concesionario.modelo;


import java.util.ArrayList;

import es.concesionario.integracion.CocheDAO;
import es.concesionario.modelo.Coche;

public class Negocio {
    // lo creo como un atributo de la clase porque lo voy
	// a invocar desde muchos puntos del proyecto...
	private CocheDAO cochedao = new CocheDAO();
	
	public int darAlta(//int id,
			           String matricula,
			           String marca,
			           String modelo,
			           String color,
			           int numcaballos,
			           boolean marchas) 
	{
		//Reglas de negocio: validar el vehículo...
		
	    Coche coche=new Coche(matricula, marca, modelo, color, numcaballos, marchas);
		
		// 2 maneras de hacerlo (elegir una de ellas):
		// 1era:
		int id=cochedao.darAlta(coche);
		return id;
		
		// 2nda:
		//return cochedao.darAlta(coche); // devolverá el id del País
	}

	public Coche consultarUno(int id) {
		// se podría validar si el que solicita la consulta tiene
		// autorización...
		Coche coche = cochedao.consultarUno(id);
		return coche; 
	}

	
	
	public ArrayList<Coche> consultarTodos() {
		// aquí las reglas si las hubiera...
		// llamar al DAO...
		ArrayList<Coche> coches=cochedao.consultarTodos();
		//ArrayList<Coche> coches=CocheDAO.consultarTodos();
		return coches;
		//
	
	}
	public ArrayList<Coche> consultarMatricula(String matricula) 
	{
		// se podría validar si el que solicita la consulta tiene
		// autorización...
		ArrayList<Coche> coches = cochedao.consultarMatricula(matricula);
		return coches; 
	}

	public String borrar(int id) {
		String msg;
		// reglas del Negocio... 
		// Verificar si el coche tiene deudas pendientes
		// mandar un correo al administrador
		// metodo que devuelve un numero entero...
		int cochesBorrados=cochedao.borrar(id);
		if(cochesBorrados>=1)
		{
			//creamos una variable tipo String para devolvérsela al Servlet
			msg="Se ha/han borrado " + cochesBorrados + " coche/coches";
			
		} else {msg="No se ha podido borrar. Quizá haya sido borrado por otro usuario.";}
		return msg;
	}

	public String actualizar(int id, 
			                 String matricula,
			                 String marca, 
			                 String modelo, 
			                 String color, 
			                 int numcaballos,
			                 Boolean marchas) {
		String msg;
		// reglas del Negocio... 
		// Verificar si el coche tiene multas pendientes
		// mandar un correo al administrador
		// metodo que devuelve un numero entero...
		int filas=cochedao.actualizar(id, matricula, marca, modelo, color, numcaballos, marchas);
		if(filas>=1)
		{
			//creamos una variable tipo String para devolvérsela al Servlet
			msg="Se ha actualizado " + filas + " vehículo";
			
		} else {msg="No se ha podido actualizar";}
	
		return msg;
	}

}
