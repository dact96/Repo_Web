<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h2>LISTADO</h2>
  <table border="1">
    <tr>
      <th>NOMBRE</th>
      <th>PRECIO</th>
      <th>CANT</th>
      <th>IMPORTE</th>
    </tr>
    
    <c:forEach var="r" items="${carrito.lista}">
	    <tr>
	      <td>${r.nombre}</td>
	      <td>${r.precio}</td>
	      <td>${r.cant}</td>
	      <td>${r.importe}</td>
	    </tr>
    </c:forEach>
    
  </table>
  <p>Total: ${carrito.total}</p>
</body>
</html>