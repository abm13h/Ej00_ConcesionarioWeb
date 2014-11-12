<%@page import="java.util.ArrayList"%>
<%@page import="es.concesionario.modelo.Coche"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado</title>
<!--<style type="text/css"></style>-->
<!--<link rel="stylesheet" type="text/css" href="css/estilo.css"/>-->
</head>
<body>
      <!--  recuperar el Coche q est� en el request -->
      <!--  le doy la vuelta al objeto "coche" para tener los 
      <!--  metodos get y set -->
      <%ArrayList<Coche> coches= (ArrayList<Coche>)request.getAttribute("listado");%>
<table>
 <tr>
     <th>Id </th>
     <th>Matr�cula </th>
     <th>Marca </th>
     <th>Modelo </th>
     <th>Color </th>
     <th>N�m. Caballos </th>
     <th>Tiene Marchas? S/N </th>
</tr>
<!--  mostrarlo. Aqu� comienza el foreach -->
<%
	for(Coche coche:coches){
%>
 <tr>
      <td><%=coche.getId() %> </td>
      <td><%=coche.getMatricula() %> </td>
      <td><%=coche.getMarca() %> </td>
      <td><%=coche.getModelo() %> </td>
      <td><%=coche.getColor() %> </td>
      <td><%=coche.getNumcaballos() %> </td>
      <td><%=coche.isMarchas()?"S�":"NO" %> </td>
</tr>
<%}// cerramos el for %>
</table>
</br></br></br>
  <a href="index.html">Ir a inicio</a>
</body>

</html>