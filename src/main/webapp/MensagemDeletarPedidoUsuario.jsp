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
            <!-- Comparando a sessão para impossilitar o usuário de ter acesso a outras páginas-->
            <c:if test="${user.idUsuario == null and gerente.idUsuario == null}">
                <c:redirect url="entrar.jsp"/>
            </c:if>
        </div>
    <nav id="nav" role="navigation">

        <a href="#nav" title="Menu">Menu</a>
        <a href="#" title="Esconder">Ocultar</a>
        <ul>
            <li><a href="logadoGerente.jsp">Início</a></li>
            <li><a href="farmacia.jsp">Farmácia</a></li>
            <li>
                <a href="#" aria-haspopup="true">Genéricos</a>
                <ul>
                    <id class="font">Genéricos
                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=alergiasInfeccoes">Alergia e Infecções</a></li>                                
                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=gripes">Gripes e Resfriados</a></li>
                        <li><a href="#">Diabetes</a></li>
                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=reguladorIntestinal">Regulador Intestinal</a></li>                    
                        <li><a href="#">Contusão e Dores</a></li>
                        <li><a href="#">Pele e Mucosa</a></li>
                        <li><a href="#">Pressão alta</a></li>                    
                        <li><a href="#">Saúde Masculina</a></li>                    
                        <li><a href="#">Saúde Feminina</a></li>                    
                        <li><a href="#">Respiratório</a></li>

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
                        <li><a href="#">Geriátrico</a></li>                    
                        <li><a href="#">Repelentes</a></li>
                        <li><a href="#">Pés</a></li>

                </ul>

            </li>
            <li>
                <a href="#" aria-haspopup="true">Estética</a>
                <ul>
                    <id class="font">Estética
                        <li><a href="#">Acessórios</a></li>
                        <li><a href="#">Saúde da Pele</a></li>
                        <li><a href="#">Depilação</a></li>                    
                        <li><a href="#">Estética Masculina</a></li>
                        <li><a href="#">Tratamento Capiar</a></li>
                        <li><a href="#">Kits</a></li>                 
                        <li><a href="#">Protetor Solar</a></li>                    
                        <li><a href="#">Tintura e Tratamento </a></li>                    
                        <li><a href="#">Unhas</a></li>                    

                </ul>
                <ul>
                    <id class="font">Nutrição
                        <li><a href="#">Alimentos</a></li>
                        <li><a href="#">Bebidas</a></li>
                        <li><a href="#">Nutrição Infantil</a></li>                    
                        <li><a href="#">Nutrição Masculina</a></li>
                        <li><a href="#">Nutrição Feminina</a></li>
                        <li><a href="#">Nutrição Esportiva</a></li>                   
                        <li><a href="#">Emagrecedores</a></li>
                        <li><a href="#">Hormônios</a></li>
                        <li><a href="#">Vitaminas</a></li>                   

                </ul>             
            </li>
            <li>
                <a href="#" aria-haspopup="true">Ortopedia </a>
                <ul>
                    <id class="font">Ortopedia
                        <li><a href="#">Acessórios</a></li>
                        <li><a href="#">Cabeça</a></li>                         
                        <li><a href="#">Mãos</a></li>
                        <li><a href="#">Braços</a></li>                    
                        <li><a href="#">Pés e Pernas</a></li><br>

                        <id class="font">Maquiagem
                            <li><a href="#">Rosto</a></li>
                            <li><a href="#">Lábios</a></li>                    
                            <li><a href="#">Olhos</a></li> 

                            </ul>
                            <ul>
                                <id class="font">Perfumes
                                    <li><a href="#">Desodorante Aerosol</a></li>
                                    <li><a href="#">Desodorante Creme</a></li>                         
                                    <li><a href="#">Desodorante Roll-on</a></li>
                                    <li><a href="#">Desodorante Spray</a></li>                    
                                    <li><a href="#">Perfume de Ambiente</a></li><br>

                                    <id class="font">Dermocosméticos

                                        <li><a href="#">Cabelo e Corpo</a></li>
                                        <li><a href="#">Olhos e Rosto</a></li>                    
                                        <li><a href="#">Nutricosméticos</a></li>                    

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
                                                &raquo <a href="#"><font color="white"> Início</font><br><br></a>
                                                &raquo <a href="mercado.jsp"><font color="white">Mercado</font><br><br></a>
                                                &raquo <a href="PedidoServlet?cmd=liquidos&categoria=${produto.categoria}"> <font color="white">Produtos</font><br><br></a>
                                                &raquo <a href="#"><font color="white">Promoções</font><br><br></a>
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
                                                © 2015 Mercado Central. Todos direitos reservados.

                                            </div>
                                        </div>

                                        </body>
                                        </html>
