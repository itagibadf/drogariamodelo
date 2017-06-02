<%--
    Document   : entrar
    Created on : 24/08/2015, 11:17:54
    Author     : itagiba
--%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
               <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="Ajuste.css">
    </head>
    <body>
        <div id="topo">
                    </div>
        <div id="ajustenav">
            <ul class="menu">

                <li><a href="paginaInicial.jsp">Início</a></li>
                <li><a href="#">Mercado</a></li>
                <li><a href="#">Produtos</a>
                    <ul>
                        <!--Itens do sub menu drop down. Utilize os espaços
                          para os itens não ficarem um em cima do outro-->
                        <li><a href="liquidos.jsp">Líquidos</a></li>
                        <li><a href="organicos.jsp">Orgânicos</a></li>
                        <li><a href="carnes.jsp">Carnes</a></li>
                        <li><a href="guloseimas.jsp">Guloseimas</a></li>
                        <li><a href="frutas.jsp">Frutas</a></li>
                        <li><a href="verduras.jsp">Verduras</a></li>
                        <li><a href="legumes">Legumes</a></li>
                        <li><a href="biscoitos.jsp">Biscoitos</a></li>

                    </ul>
                </li>
               <li><a href="#">Promoções</a></li>
               <li><a href="registro.jsp">Registrar</a></li>


            </ul>
        </div>

        <div id="conteudo">
            <jsp:useBean id="pedidos" scope="session"
                     class="controller.Pedido"/>

             <div id="ajusteRegistro"><br><br>

                <form name="form1"  action="PedidoServlet?cmd=atualizarPedidoUsuario" method="POST">

                <div id="textoCadastro">Edição de Pedido<br></div>
                <!--Puxando o Id_usuario para que ele não possa digitar, facilitando o processo. -->
                  <div id="ajusteId"><input name="idPedido" value="${pedido.idPedido}" readonly="readonly" /></div>
                  <div id="ajusteId"><input type="hidden" name="idUsuario" value="${pedido.usuario.idUsuario}" readonly="readonly" /></div>
                  <div id="ajusteId"><input type="hidden" name="idProduto" value="${pedido.produto.idProduto}" readonly="readonly"/></div>
                <div id="ajustePlace1"><center>Tipo</center><input name="tipo" value="${pedido.produto.tipo}" placeholder="Tipo" readonly="readonly"></div>

                <div id="ajustePlace2"><center>Preço</center> <input name="preco" value="${pedido.produto.preco}" required  placeholder="Preço" readonly="readonly"></div>
                <div id="ajustePlace3Medida"><center>Quantidade</center><input name="quantidade" value="${pedido.produto.quantidade}" required  placeholder="Estoque" readonly="readonly"></div>
                <div id="ajustePlace4"><center>Produto Medida</center> <input name="medidaProduto" value="${pedido.produto.medida}" required  placeholder="Medida" readonly="readonly"> </div>
                <div id="ajustePlace5"><center>Medida Pedido</center><input name="medida" value="${pedido.medida}" required  placeholder="Quantidade Comprada"></div>
                <div id="ajustePlace6"><center>Data Pedido</center><input name="dataPedido" value="${pedido.dataPedido}" required  placeholder="Data do Pedido" readonly="readonly"></div><br>
                <div id="ajustePlace7"><center>Endereço Entrega</center><p><input name="enderecoEntrega" value="${pedido.enderecoEntrega}" required  placeholder="Endereço da Entrega"></div>
                <div id="ajustePlace8"><center>Valor Total</center><input name="valorTotal" value="${pedido.medida * pedido.produto.preco}" maxlength="7" required  placeholder="Valor Total" readonly="readonly"></div>
                <div id="ajustePlace9Endereco"><center>Unidade de Medida</center><input name="unidadeMedida" value="${pedido.produto.unidadeMedida}" required   placeholder="Unidade de Medida" readonly="readonly"></div>
                <input type="hidden" name="estado" value="${pedido.estado}">
                <!-- Botões do java script               -->
               <a href="MensagemCadastro"> <input type="submit" value="Atualizar"/></a>

                </form>
                </div>
        </div>

<div id="rodape">
            <div class="table">
           <h3> Menu</h3>
            <hr size="2" width="100%" ><br>
       &raquo <a href="#"><font color="white"> Início</font><br><br></a>
        &raquo <a href="mercado.jsp"><font color="white">Mercado</font><br><br></a>
       &raquo <a href="PedidoServlet?cmd=liquidos&categoria=${produto.categoria}"> <font color="white">Produtos</font><br><br></a>
       &raquo <a href="#"><font color="white">Promoções</font><br><br></a>
        &raquo <a href="registro.jsp"><font color="white">Registrar</font><br><br></a>
        &raquo <a href="entrar.jsp"><font color="white">Entrar | Cadastro</font></a>
            </div>
            <div class="table">
           <h3> Fale Conosco</h3>
            <hr size="2" width="130%"><br>

          <a href="#"><img src="imagem/facebook.png" border="1" width="50px" height="40px"></a> <font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo CentralMercado</font>
          <a href="#"><img src="imagem/skype.png" border="1" width="50px" height="40px"></a><font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo  MercadoCentralS</font>
          <a href="#"><img src="imagem/watsap.jpg" border="1" width="50px" height="40px"></a><font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo (61) 3356-7000</font>
          </div>
       <div class="table" style="margin-top: 15%; margin-left: 16%;">
          © 2015 Drogaria Pharmaluci. Todos direitos reservados.

       </div>
       </div>
    </body>
</html>
