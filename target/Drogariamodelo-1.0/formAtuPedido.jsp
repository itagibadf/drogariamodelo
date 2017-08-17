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
                 
                <form name="form1"  action="PedidoServlet?cmd=atualizarPedido" method="POST">
                    
                <div id="textoCadastro">Edição de Pedido<br></div>
                <!--Puxando o Id_usuario para que ele não possa digitar, facilitando o processo. -->
                  <div id="ajusteId"><input type="hidden" name="idPedido" value="${pedido.idPedido}" readonly="readonly" /></div>
                  <div id="ajusteId"><input type="hidden" name="idUsuario" value="${pedido.usuario.idUsuario}" readonly="readonly" /></div>
                  <div id="ajusteId"><input type="hidden" name="idProduto" value="${pedido.produto.idProduto}" readonly="readonly"/></div>
                  <div id="ajustePlace1"> <font color="blue"><center>Nome</center></font><input  name="nome" value="${pedido.usuario.nome}" required  placeholder="Nome" readonly="readonly"></div>

                <div id="ajustePlace2"> <font color="blue"><center>Sobrenome</center></font> <input  name="sobrenome" value="${pedido.usuario.sobrenome}" placeholder="Sobrenome" readonly="readonly" ></div>

                <div id="ajustePlace3Medida"><font color="blue"><center>Tipo</center></font><input name="tipo" value="${pedido.produto.tipo}" placeholder="Tipo" readonly="readonly"></div>
                
                <div id="ajustePlace4"><font color="blue"><center>Preço</center></font> <input name="preco" value="${pedido.produto.preco}" required  placeholder="Preço" readonly="readonly"></div>
                <div id="ajustePlace5"><font color="blue"><center>Quantidade</center></font><input name="quantidade" value="${pedido.produto.quantidade}" required  placeholder="Estoque" readonly="readonly"></div>
                <div id="ajustePlace6"><font color="blue"><center>Produto Medida</center></font> <input name="medidaProduto" value="${pedido.produto.medida}" required  placeholder="Medida" readonly="readonly"> </div>
                <div id="ajustePlace7"><font color="blue"><center>Medida Pedido</center></font><input name="medida" value="${pedido.medida}" required  placeholder="Quantidade Comprada"></div>
                <div id="ajustePlace8"><font color="blue"><center>Data Pedido</center></font><input name="dataPedido" value="${pedido.dataPedido}" required maxlength="10" placeholder="Data do Pedido"></div><br>
                <div id="ajustePlace9Endereco"><font color="blue"><center>Endereço Entrega</center></font><p><input name="enderecoEntrega" value="${pedido.enderecoEntrega}" required  placeholder="Endereço da Entrega"></div>
                <div id="ajustePlace10Valor"><font color="blue"><center>Valor Total</center></font><input name="valorTotal" value="${pedido.medida * pedido.produto.preco}" maxlength="7" required  placeholder="Valor Total"></div>
                <div id="ajustePlace11UnidadeMedida"><font color="blue"><center>Estado Pedido</center></font><input name="estado" value="${pedido.estado}" placeholder="Estado Pedido"></div>
                <div id="ajustePlace12"><font color="blue"><center>Unidade de Medida</center></font><input name="unidadeMedida" value="${pedido.produto.unidadeMedida}" required   placeholder="Unidade de Medida" readonly="readonly"></div>
               
                <!-- Botões do java script               -->
               <a href="MensagemCadastro"> <input type="submit" value="Atualizar"/></a>

                </form>
                </div>
        </div>
<div id="rodape" style="margin-top: 15%; margin-left: 16%;">
    </div>
    </body>
</html>
