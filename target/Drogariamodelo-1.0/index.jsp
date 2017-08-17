<%--
    Document   : entrar
    Created on : 24/08/2015, 11:17:54
    Author     : itagiba
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <body>
        <jsp:useBean id="Usuario" scope="session"
                     class="controller.Usuario" />

        <c:redirect url="ProdutoServlet?cmd=listarPromocoes"> </c:redirect>

    </body>
</html>  