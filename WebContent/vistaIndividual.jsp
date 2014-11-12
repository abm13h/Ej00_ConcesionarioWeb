<%@page import="es.concesionario.modelo.Coche"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vista individual</title>
<!--<style type="text/css"></style>-->
<!--<link rel="stylesheet" type="text/css" href="css/estilo.css"/>-->
</head>
<body>
      <!--  recuperar el Coche q está en el request -->
      <!--  le doy la vuelta al objeto "coche" para tener los 
      <!--  metodos get y set -->
<form action="Procesar" method="get">      
      <%Coche cocheDeCocheDAO= (Coche)request.getAttribute("coche");%>
<table>
 <tr>
     <th>Id </th>
     <th>Matrícula </th>
     <th>Marca </th>
     <th>Modelo </th>
 </tr>
 
<!--  mostrarlo -->
 <tr>
      <td><input type="text" name="id" value="<%=cocheDeCocheDAO.getId()%>" readonly="readonly"/></td>
      <td><input type="text" name="matricula" value="<%=cocheDeCocheDAO.getMatricula()%>"/></td>
      <td><input type="text" name="marca" value="<%=cocheDeCocheDAO.getMarca()%>"/></td>
      <td><input type="text" name="modelo" value="<%=cocheDeCocheDAO.getModelo()%>"/></td>
 </tr>
 <tr>    
     <th>Color </th>
     <th>Núm. Caballos </th>
     <th>Marchas: S/N </th>
</tr>
 <tr>
      <td><input type="text" name="color" value="<%=cocheDeCocheDAO.getColor()%>"/></td>
      <td><input type="text" name="numcaballos" value="<%=cocheDeCocheDAO.getNumcaballos()%>"/></td>
      <td><input type="text" name="marchas" value="<%=cocheDeCocheDAO.isMarchas()?"SÍ":"NO"%>"/></td>                                                 
 </tr>
</table>
<br></br>
<input type="submit" value="Borrar" id="borrar" name="borrar"/>
<input type="submit" value="Actualizar" id="actualizar" name="actualizar"/>
<br></br>
<a href="index.html">Ir a inicio</a>
</form> 
</body>
</html>