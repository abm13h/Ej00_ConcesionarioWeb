<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Respuesta </title>
<style type="text/css"></style>
<link rel="stylesheet" type="text/css" href="css/estilo.css"/>
<% String mensaje=(String)request.getAttribute("mensajeVistaMensajeJsp");%>
<% if(mensaje==null)
	mensaje="";%>
</head>
<body>
<h3>Respuesta de la base de datos</h3>
 <br></br>
 <p><%=mensaje %></p>
 <br></br>
 <p><a href="index.html">Ir a inicio</a></p>
</body>
</html>