<%-- 
    Document   : pagSeguro
    Created on : 13/03/2017, 14:21:03
    Author     : itagiba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


    </head>
    <body>
        <table>
            <tr>
                <th>Img</th>
                <th>Nome</th>
                <th>Pedido</th>
                <th>Estoque</th>
                <th>Medida</th>
                <th>Quantidade</th>
                <th>Preço </th>
                <th>Total</th>
                <th>Alterar</th>
                <th>Excluir</th>
            </tr>

            <c:forEach var="pedido" items="${ requestScope.pedidoList}" >

                <form name="form1"  method="post" target="_blank"  
                      action="https://pagseguro.uol.com.br/v2/checkout/payment.html">

                    <tr>
                        <td><a href="ProdutoServlet?cmd=mostraProduto&idRemedio=${pedido.produto.idRemedio}"><img src="imagem/Produtos/${pedido.produto.idRemedio}.jpg" width="100%" height="90px" title="${pedido.produto.nome}"></a></td>
                        <td><b><c:out default="tipo"  value="${pedido.produto.nome} "/></b></td>
                        <td><fmt:parseDate value="${pedido.dataPedido}" pattern="yyyy-MM-dd H:m:s" var="formatar"/>
                            <fmt:formatDate value="${formatar}" var="dataFormatada" pattern="dd-MM-yyyy HH:mm"/>
                            <c:out  default="dataPedido" value="${dataFormatada}"/></td>
                        <td><fmt:formatNumber maxFractionDigits="0"><c:out default="estoque" value="${pedido.produto.quantidadeEstoque - pedido.quantidadePedido}"/></fmt:formatNumber></td>
                        <td><fmt:formatNumber maxFractionDigits="0"><c:out default="medida" value="${pedido.produto.medida}"/></fmt:formatNumber>${pedido.produto.unidadeMedida}</td>
                        <td><input type="number"  min="1" max="${pedido.produto.quantidadeEstoque}"  name="quantidadePedido" value="${pedido.quantidadePedido}" style="width: 100px;"> ${pedido.produto.unidadeMedida}</td>
                        <td><fmt:formatNumber type="currency" value="${pedido.produto.preco}" /></td>
                        <td><c:set var="valorTotal" value="${pedido.produto.preco* pedido.quantidadePedido}"/><fmt:formatNumber type="currency" value="${valorTotal}"/></td>

                    <input type='hidden' name='necessitaEntrega' value="${pedido.necessitaEntrega}">
                    <input type="hidden" name="idPedido" value="${pedido.idPedido}"/>
                    <input type="hidden" name="idRemedio" value="${pedido.produto.idRemedio}">                           
                    <input type="hidden" name="idUsuario" value="${pedido.usuario.idUsuario}">
                    <input type="hidden" name="enderecoEntrega" value="${pedido.enderecoEntrega}">
                    <input type="hidden" name="valorTotal" value="${pedido.valorTotal}">
                    <input type="hidden" name="estado" value="${pedido.estado}">

                    <c:set var="idUsuario" value="${pedido.usuario.idUsuario}"></c:set>
                    <c:set var="necessitaEntrega" value="${pedido.necessitaEntrega}"></c:set>
                    <c:set var="estado" value="${pedido.estado}"></c:set>
                    <c:set var="enderecoEntrega" value="${pedido.usuario.endereco}"> </c:set>
                    <c:set var="idRemedio" value="${pedido.produto.idRemedio}"></c:set>
                    <c:set var="idPedido" value="${pedido.idPedido}"/>
                    <!--Descobrindo o resultado do peso final sobre a quantidade pedida-->
                    <c:set var="peso2" value="${(pedido.produto.peso*pedido.quantidadePedido)}"/>                                  
                    <!--Somando a quantidade pedida mais o novo peso informado-->
                    <c:set var="peso" value="${peso2+peso}"/> 
                    <c:set var="preco" value="${pedido.produto.preco}"/>                                      
                    <c:set var="valor" value="${valorTotal+valor}"/>
                    <c:set var="cep" value="${pedido.usuario.cep}"/>
                    <c:set var="descricao" value="${pedido.produto.descricao}"/>
                    <c:set var="quantidadePedido" value="${pedido.quantidadePedido}"/>                        
                    <c:set var="cidade" value="${pedido.usuario.cidade}"/>


                    <td><!--                                                Usuário Servlet que puxa os campos dos usuários para serem atualizados-->

                        <input type="submit" value="Edit" title="Editar">
                    </td></form>
                    <c:set var="total" value="${total+valorTotal}"/>

                <td>
                    <a href="PedidoServlet?cmd=excPedidosUsuario&idPedido=${pedido.idPedido}"><input type="submit" value="Del" title="Remover">
                    </a>
                </td>  

                <script type="text/javascript" src="https://stc.pagseguro.uol.com.br/pagseguro/api/v2/checkout/pagseguro.lightbox.js">

                    PagSeguroLightbox({
                    code: ‘D0707F35C87C431BACE8A9AEF78718A7’
                            }, {
                    success : function(transactionCode) {
                    alert("success - " + transactionCode);
                    },
                            abort : function() {
                            alert("abort");
                            }


                    });
                    var code = 'D0707F35C87C431BACE8A9AEF78718A7';
                    var isOpenLightbox = PagSeguroLightbox({
                    code: 'code'
                            }, {
                    success : function(transactionCode) {
                    alert("success - " + transactionCode);
                    },
                            abort : function() {
                            alert("abort");
                            }
                    });
                // Redirecionando o cliente caso o navegador não tenha suporte ao Lightbox
                    if (!isOpenLightbox){
                    location.href = "https://pagseguro.uol.com.br/v2/checkout/payment.html?code=" + code;
                    }
                </script>

                <button onclick="PagSeguroLightbox('D0707F35C87C431BACE8A9AEF78718A7')">Pagar com lightbox</button>
                <script type="text/javascript"
                src="https://stc.pagseguro.uol.com.br/pagseguro/api/v2/checkout/pagseguro.lightbox.js"></script>

            </c:forEach>









            <c:if test="${idUsuario !=null and idPedido != null}">
                <form action="PedidoServlet?cmd=excTodosPedidosUsuario" method="POST">
                    <input type="hidden" name="idUsuario" value="${user.idUsuario}"/>
                    <input type="submit" value="Excluir Lista" >
                </form> 
                <id class="font3" style="position: absolute; margin-top: 8.6%; "><a href="#rodape"><font color="black">&raquoComo está minha lista?</font></a></id>

            </c:if>

        </table>
        
    
    
    <input type="submit" id="botao" value='mostrar' style="float: left; width:100px; height: 30px;"  onclick=" document.getElementById('agrupa').style.display = 'block';"> 
   <div id="agrupa" style="float:left; margin-left: 2%; width:100%; height: 100px; border: 1px solid black; display: none; ">

</div>
</body>
</html>
