<%--
    Document   : mostrarUsuario
    Created on : 16/09/2015, 11:48:00
    Author     : itagiba
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="controller.Usuario"%>
<%--<%@page import="java.util.List"%>--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Deletando Pedido</title>
        <link rel="stylesheet" type="text/css" href="Ajuste.css">
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="slide.js"></script>

    </head>
    <body>


        <div id="topo">
            <center> Bem vindo (<a href="UsuarioServlet?cmd=procuraUsuarioPerfil&idUsuario=${user.idUsuario}"><font color="white"><b>${user.nome}</b></font></a> |<a href="UsuarioServlet?cmd=sair"> <font color="white"><b>Sair</b></font></a>)</center>
            <!-- Comparando a sess�o para impossilitar o usu�rio de ter acesso a outras p�ginas-->
            <c:if test="${user.idUsuario == null and gerente.idUsuario == null}">
                <c:redirect url="entrar.jsp"/>
            </c:if>
        </div>
    <nav id="nav" role="navigation">

        <a href="#nav" title="Menu">Menu</a>
        <a href="#" title="Esconder">Ocultar</a>
        <ul>
            <li><a href="logadoGerente.jsp">In�cio</a></li>
            <li><a href="farmacia.jsp">Farm�cia</a></li>
            <li>
                <a href="#" aria-haspopup="true">Gen�ricos</a>
                <ul>
                    <id class="font">Gen�ricos
                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=alergiasInfeccoes">Alergia e Infec��es</a></li>                                
                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=gripes">Gripes e Resfriados</a></li>
                        <li><a href="#">Diabetes</a></li>
                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=reguladorIntestinal">Regulador Intestinal</a></li>                    
                        <li><a href="#">Contus�o e Dores</a></li>
                        <li><a href="#">Pele e Mucosa</a></li>
                        <li><a href="#">Press�o alta</a></li>                    
                        <li><a href="#">Sa�de Masculina</a></li>                    
                        <li><a href="#">Sa�de Feminina</a></li>                    
                        <li><a href="#">Respirat�rio</a></li>

                </ul>
                <ul>
                    <id class="font">Higiene                       
                        <li><a href="#">Absorventes</a></li>
                        <li><a href="#">Barbas</a></li>
                        <li><a href="#">Oral</a></li>                    
                        <li><a href="#">Pessoal</a></li>
                        <li><a href="#">Corporal</a></li>
                        <li><a href="#">Shampoo</a></li>                    
                        <li><a href="#">Condicionador</a></li>                    
                        <li><a href="#">Geri�trico</a></li>                    
                        <li><a href="#">Repelentes</a></li>
                        <li><a href="#">P�s</a></li>

                </ul>

            </li>
            <li>
                <a href="#" aria-haspopup="true">Est�tica</a>
                <ul>
                    <id class="font">Est�tica
                        <li><a href="#">Acess�rios</a></li>
                        <li><a href="#">Sa�de da Pele</a></li>
                        <li><a href="#">Depila��o</a></li>                    
                        <li><a href="#">Est�tica Masculina</a></li>
                        <li><a href="#">Tratamento Capiar</a></li>
                        <li><a href="#">Kits</a></li>                 
                        <li><a href="#">Protetor Solar</a></li>                    
                        <li><a href="#">Tintura e Tratamento </a></li>                    
                        <li><a href="#">Unhas</a></li>                    

                </ul>
                <ul>
                    <id class="font">Nutri��o
                        <li><a href="#">Alimentos</a></li>
                        <li><a href="#">Bebidas</a></li>
                        <li><a href="#">Nutri��o Infantil</a></li>                    
                        <li><a href="#">Nutri��o Masculina</a></li>
                        <li><a href="#">Nutri��o Feminina</a></li>
                        <li><a href="#">Nutri��o Esportiva</a></li>                   
                        <li><a href="#">Emagrecedores</a></li>
                        <li><a href="#">Horm�nios</a></li>
                        <li><a href="#">Vitaminas</a></li>                   

                </ul>             
            </li>
            <li>
                <a href="#" aria-haspopup="true">Ortopedia </a>
                <ul>
                    <id class="font">Ortopedia
                        <li><a href="#">Acess�rios</a></li>
                        <li><a href="#">Cabe�a</a></li>                         
                        <li><a href="#">M�os</a></li>
                        <li><a href="#">Bra�os</a></li>                    
                        <li><a href="#">P�s e Pernas</a></li><br>

                        <id class="font">Maquiagem
                            <li><a href="#">Rosto</a></li>
                            <li><a href="#">L�bios</a></li>                    
                            <li><a href="#">Olhos</a></li> 

                            </ul>
                            <ul>
                                <id class="font">Perfumes
                                    <li><a href="#">Desodorante Aerosol</a></li>
                                    <li><a href="#">Desodorante Creme</a></li>                         
                                    <li><a href="#">Desodorante Roll-on</a></li>
                                    <li><a href="#">Desodorante Spray</a></li>                    
                                    <li><a href="#">Perfume de Ambiente</a></li><br>

                                    <id class="font">Dermocosm�ticos

                                        <li><a href="#">Cabelo e Corpo</a></li>
                                        <li><a href="#">Olhos e Rosto</a></li>                    
                                        <li><a href="#">Nutricosm�ticos</a></li>                    

                                        </ul>

                                        </li>
                                        <li><a href="PedidoServlet?cmd=listarPedidosUsuario&idUsuario=${user.idUsuario}">Carrinho</a></li>
                                        </ul>
                                        </nav>
                                        <div id="conteudo" style="text-align: center;">
                                            <h2>Pedido Deletado!</h2><br>
                                            <c:if test="${user.idUsuario != null}">
                                                <a href="PedidoServlet?cmd=listarPedidosUsuario&idUsuario=${user.idUsuario}"><h3><font color="red">&raquoVoltar</font></h3></a>
                                                            <c:redirect url="PedidoServlet?cmd=listarPedidosUsuario&idUsuario=${user.idUsuario}"></c:redirect>            
                                                        </c:if>
                                                        <c:if test="${gid != null}">
                                                <a href="PedidoServlet?cmd=listarPedidosPendentes"><h3><font color="red">&raquoVoltar</font></h3></a>
                                                            <c:redirect url="PedidoServlet?cmd=listarPedidosPendentes"> </c:redirect>
                                                        </c:if>

                                        </div>

                                        <div id="rodape">
                                            <div class="table">
                                                <h3> Menu</h3>
                                                <hr size="2" width="100%" ><br>
                                                &raquo <a href="#"><font color="white"> In�cio</font><br><br></a>
                                                &raquo <a href="mercado.jsp"><font color="white">Mercado</font><br><br></a>
                                                &raquo <a href="PedidoServlet?cmd=liquidos&categoria=${produto.categoria}"> <font color="white">Produtos</font><br><br></a>
                                                &raquo <a href="#"><font color="white">Promo��es</font><br><br></a>
                                               &raquo <a href="entrar.jsp"><font color="white">Entrar | Cadastro</font></a>
                                            </div>
                                            <div class="table">
                                                <h3> Fale Conosco</h3>
                                                <hr size="2" width="100%"><br>

                                                <a href="#"><img src="imagem/facebook.png" border="1" width="50px" height="40px"></a> <font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo CentralMercado</font>
                                                <a href="#"><img src="imagem/skype.png" border="1" width="50px" height="40px"></a><font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo  MercadoCentralS</font>
                                                <a href="#"><img src="imagem/watsap.jpg" border="1" width="50px" height="40px"></a><font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo (61) 3356-7000</font>
                                            </div>
                                            <div class="table">
                                                � 2015 Mercado Central. Todos direitos reservados.

                                            </div>
                                        </div>

                                        </body>
                                        </html>
