package es.concesionario.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.concesionario.modelo.Coche;
import es.concesionario.modelo.Negocio;

/**
 * Servlet implementation class DarAltaServlet
 */
@WebServlet("/DarAlta")
public class DarAltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/* Constructor por defecto */
    public DarAltaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /* Método doGet */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    /* 1 Recuperar los datos de la URL */
   	/* 2 Adaptarlos si es necesario al tipo de datos del modelo*/
    
    // recibe un String, devuelve un String
    String matricula = request.getParameter("matricula"); 
    
    // recibe un String, devuelve un String
    String marca = request.getParameter("marca"); 
    
    // recibe un String, devuelve un String
    String modelo = request.getParameter("modelo"); 
    
   // recibe un String, devuelve un String
    String color = request.getParameter("color"); 
    
    // recibe un String, le damos la vuelta para convertirlo en un entero con "Integer.parseInt("	
    int numcaballos = Integer.parseInt(request.getParameter("numcaballos")); 
 		
    
    // si en el html la "caja de chequeo" no está checheado por defecto
 	// nos puede fallar al ejecutar. Tenemos que preguntar si el campo
 	// "trabaja" está viajando en la URL (si la chequearon)
 	
    String tienemarchas= request.getParameter("marchas");
	boolean marchas=true;
	// true-> si;   false-> no
	if(tienemarchas.equals("true")){
			marchas=true;
	}
	else{
	        marchas=false;
	}
 	
 	/* 3 Pasarle los datos recuperados a la capa Negocio */
    Negocio negocio = new Negocio();
    int id=negocio.darAlta(matricula, marca, modelo, color, numcaballos, marchas);
    
    // además de darlo de alta en la BBDD lo muestro...
    // consultar el coche y...
    //->Coche p=negocio.consultarUno(id);
    // meter (setear) el coche en el request para que en vistaIndividual.jsp lo pueda recuperar
    //->request.setAttribute("coche", p); // voy a consultar en BBDD por si borran el coche
    
    //... redirigir a la vista individual
    //->RequestDispatcher rd;
    //->rd=request.getRequestDispatcher("vistaIndividual.jsp");
    
    //... o redirigir a la vista o consulta "mostrarTodos"
    ArrayList<Coche> coches=negocio.consultarTodos();
	
	// meter el arrayList en el request
	request.setAttribute("listado", coches);
	// redirigir al código jsp "mostrarTodos"
	RequestDispatcher rd;
	rd=request.getRequestDispatcher("mostrarTodos.jsp");
    // rd=request.getRequestDispatcher("mostrarTodos.jsp");
    
    rd.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
