<%-- 
    Document   : liquidos
    Created on : 28/08/2015, 10:46:56
    Author     : itagiba
--%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="produto" scope="session" class="controller.Produto" />
<jsp:useBean id="cate" class="controller.ProdutoDAO"/>
<%@page import="controller.Usuario"%>
<jsp:useBean id="catePedido" class="controller.PedidoDAO"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="Ajuste.css">
        <script type="text/javascript" src="slideQuery.js"></script>
        <script type="text/javascript" src="slide.js"></script>

        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <title>Produto - ${nomeBusca}</title>
        <meta name="description" content="Blueprint: Horizontal Drop-Down Menu" />
        <meta name="keywords" content="horizontal menu" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="../favicon.ico">
        <link rel="stylesheet" type="text/css" href="css/default.css" />
        <link rel="stylesheet" type="text/css" href="css/component.css" />
        <script src="js/modernizr.custom.js"></script>


    </head>
    <body>
        <jsp:useBean id="Usuario" scope="session"
                     class="controller.Usuario" />
        <c:forEach var="produto" items="${requestScope.produtoList}">
            <c:set var="categoria" value="${produto.categoria}"/>
            <c:set var="preco" value="${produto.preco}"/>
        </c:forEach>

        <div id="topo">
            <a href="farmacia.jsp" title="Onde estamos"><div id class="iconeTopo" style="background-image: url('imagem/localizacao.png'); "></div><div id class="textoIcone"><font color="white">Localiza��o</font></div></a>
                        <c:if test="${user == null && gerente == null}">
                <a href="entrar.jsp" title="Se cadastre aqui"><div id class="iconeTopo" style="background-image: url('imagem/cadastro.png'); margin-left: 0.7%; border-radius: 20px;"></div><div id class="textoIcone" style="margin-left: 0.3%;"><font color="white">Me cadastrar</font></div></a>
                        </c:if>  
            <a href="entrar.jsp" title="Acesse sua conta"><div id class="iconeTopo" style="background-image: url('imagem/usuario.png'); margin-left: 0.7%"></div><div id class="textoIcone" style="margin-left: 0.3%;"><font color="whitesmoke">Entrar</font></div></a>
                        <c:if test="${gerente != null}">
                <div id class="iconeTopo" style="background-image: url('imagem/usuario2.png'); margin-left: 0.7%"></div><div id class="textoIcone" style="margin-left: 0.3%; width: 30%;"><font color="white">Ol�!</font> (<a href="UsuarioServlet?cmd=atuGerente&idUsuario=${gid}"><font color="white"><b>${gerente.nome}</b></font></a> |<a href="UsuarioServlet?cmd=sair"> <font color="white"><b>Sair</b></font></a>)</a></div>
                    </c:if>
                    <c:if test="${user != null}">
                <div id class="iconeTopo" style="background-image: url('imagem/usuario2.png'); margin-left: 0.7%"></div><div id class="textoIcone" style="margin-left: 0.3%; width: 30%;"><font color="white">Ol�!</font> (<a href="UsuarioServlet?cmd=procuraUsuarioPerfil&idUsuario=${user.idUsuario}"><font color="white"><b>${user.nome}</b></font></a> |<a href="UsuarioServlet?cmd=sair"> <font color="white"><b>Sair</b></font></a>)</a></div>
                        <c:forEach var="pedido" items="${catePedido.pedidoUsuarioID(uid)}" varStatus="count">
                            <c:set var="contador" value="${count.count}"/>
                            <c:set var="idPedido" value="${pedido.idPedido}"/>
                            <c:set var="valorTotal" value="${pedido.valorTotal}"/>
                            <c:set var="total" value="${total+valorTotal}"/>
                        </c:forEach>
                <a href="PedidoServlet?cmd=listarPedidosUsuario&idUsuario=${user.idUsuario}" >    
                    <c:choose>
                        <c:when test="${contador != null}">
                            <div id class="imgCarrinho" title="Voc� possui ${contador} item(s) em seu carrinho valor Total:<fmt:formatNumber type="currency" value="${total}"/>. Clique e veja.">
                                <font color="whitesmoke">${contador}</font>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div id class="imgCarrinho" title="Seu carrinho est� vazio.">
                                <font color="whitesmoke">0</font>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </a>              </c:if>

                <div id="busca">
                    <form action="ProdutoServlet?cmd=buscar" method="POST">
                        <div id class="textoBusca">
                            <input name="nomeBusca" placeholder=" Pesquise o Medicamento desejado" value="${nomeBusca}" title="O que procura?" style=' width:60%; height: 42px'>    
                    </div>
                    <input type="submit" value="Buscar">
                </form>
            </div>
        </div>
        <div class="container">
            <nav id="cbp-hrmenu" class="cbp-hrmenu">
                <ul>
                    <li>
                        <a href="#"> Aparelhos e Testes</a>
                        <div class="cbp-hrsub" style="width: 100%">
                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=acessorios-aparelhos-e-testes">Acess�rios</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=abaixador-de-lingua">Abaixador de L�ngua</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=coletor-de-fezes-e-urina">Coletor de Fezes e Urina</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=dilatador-nasal">Dilatador Nasal</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=protetor-auditivo">Protetor Auditivo</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=termometro">Term�metro</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=aparelhos">Aparelhos</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=balanca">Balan�a</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=massageador">Massageador</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=inalador-e-purificador">Inalador e Purificador</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=diabetes">Diabetes</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=diabete-agulhas-e-seringas">Agulhas e Seringas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=diabete-aparelhos-e-medidores">Aparelhos e Medidores</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=diabete-lancetas-e-tiras">Lancetas e Tiras</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=testes">Testes</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=teste-fertilidade">Fertilidade</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=teste-gravidez">Gravidez</a></li>
                                    </ul>
                                </div>
                                <a href="ProdutoServlet?cmd=listarProdutos&categoria=aparelhos-e-testes">
                                    <div>
                                        <img src="imagem/Produtos/imgSubmenu/aparelho.jpg" width="100%" style="position: absolute;width: 50%;top: 0%; left: 50%;height: 100%; background-size: 100%; border:none;">

                                    </div>
                                </a>
                            </div><!-- /cbp-hrsub-inner -->
                        </div><!-- /cbp-hrsub -->
                    </li>
                    <li>
                        <a href="#">Beb� e M�e</a>
                        <div class="cbp-hrsub">

                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=acessorios-de-bebe">Acess�rios de Beb�</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=chupetas">Chupetas</a></li> 
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=mamadeiras">Mamadeiras</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=mordedores">Mordedores</a></li>

                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=alimentos-de-bebe">Alimentos de Beb�</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=complemento-alimentar-bebe">Complemento Alimentar</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=leites-para-bebe">Leites</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=papinhas">Papinhas</a></li>                               

                                    </ul>                                    
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=corpo-e-banho">Corpo e Banho</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=shampoo-de-bebe">Shampoo de Beb�</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=repelente-de-bebe">Repelente de Beb�</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=sabonete-liquido-de-bebe">Sabonete L�quido de Beb�</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=talco-de-bebe">Talco de Beb�</a></li>




                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=cuidado-da-mae">Cuidado da M�e</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=absorventes-para-seios">Absorvente para Seios</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=amamentacao">Amamenta��o</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=meias-elasticas">Meias El�sticas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=tratamento-do-corpo">Tratamento do Corpo</a></li>                                
                                    </ul>
                                </div>

                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=fraldas">Fraldas</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fraldas-tamanho-m">Tamanho M</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fraldas-tamanho-g">Tamanho G</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fraldas-para-piscina">Para piscina</a></li>



                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=para-troca-do-bebe">Para troca do Beb�</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=assaduras-do-bebe">Assaduras do Beb�</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fitas-adesivas">Fitas Adesivas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=lencos-umedecidos">Len�os Umedecidos</a></li>                                
                                    </ul>

                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=saude-bucal-do-bebe">Sa�de Bucal do Beb�</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=creme-dental-do-bebe">Creme Dental</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=escova-de-dente-bebe">Escova de Dente</a></li>

                                    </ul>
                                </div>
                            </div><!-- /cbp-hrsub-inner -->
                            <a href="ProdutoServlet?cmd=listarProdutos&categoria=bebe-mae">
                                <div>
                                    <img src="imagem/Produtos/imgSubmenu/bebeMae.jpg" width="100%" style="position: absolute;width: 24%;top: 0%; left: 76%;height: 100%;background-size: 100%">

                                </div>
                            </a>
                        </div><!-- /cbp-hrsub -->
                    </li>

                    <li>
                        <a href="#">Dermocosm�ticos</a>
                        <div class="cbp-hrsub">

                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=cabelos-dermocosmeticos">Cabelos</a></h4>
                                    <ul>                   
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=condicionador-dermocosmeticos">Condicionador</a></li>                                         
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=shampoo-cabelo-dermocosmeticos">Shampoo</a></li> 
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=tratamento-do-corpo-dermocosmeticos">Tratamento</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=corpo-dermocosmeticos">Corpo</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antiestrias-e-firmador">Antiestrias e Firmador</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=desodorante-creme">Desodorante Creme</a></li>                                         
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=shampoo-para-corpo">Shampoo</a></li>                                       
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=hidratante-corporal">Hidratante Corporal</a></li>                                         
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=protetor-solar-para-corpo">Protetor Solar</a></li> 

                                    </ul>                                    
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=olhos-dermocosmeticos">Olhos</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=colirios-olhos-dermocosmeticos">Col�rios</a></li>

                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=rosto-dermocosmeticos">Rosto</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=agua-termal">�gua Termal</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=anti-envelhecimento">Anti-envelhecimento</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antirrugas">Antirrugas</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=hidratante">Hidratante</a></li>                                         
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=protetor-solar-para-rosto">Protetor Solar</a></li> 

                                    </ul>
                                </div>

                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=nutricosmeticos">Nutricosm�ticos</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=anticelulite">Anticelulite</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fortalecedor-de-unhas-e-cabelos">Fortalecedor de Unhas e Cabelos</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=redutor-de-medidas">Redutor de Medidas</a></li> 

                                    </ul>

                                </div>
                                <a href="ProdutoServlet?cmd=listarProdutos&categoria=dermocosmeticos">
                                    <div>
                                        <img src="imagem/Produtos/imgSubmenu/dermocosmeticos.jpg" width="100%" style="position: absolute;width: 37%;top: 0%; left: 63%;height: 100%; background-size: 100%; border:none;">

                                    </div>
                                </a>
                            </div><!-- /cbp-hrsub-inner -->
                        </div><!-- /cbp-hrsub -->
                    </li>

                    <li>
                        <a href="#">Est�tica</a>
                        <div class="cbp-hrsub">
                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=acessorios-estetica">Acess�rios</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=chapinha">Chapinha</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cortador-de-cabelos">Cortador de Cabelos</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=escovas-e-pentes">Escovas e Pentes</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=secador-de-cabelos">Secador de Cabelos</a></li>                                
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=cuidado-da-pele">Cuidado da Pele</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antiacne">Antiacne</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=creme-antirrugas">Creme Antirrugas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=creme-facial">Creme Facial</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=hidratante-facial">Hidratante Facial</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=oleos-de-amendoas">�leos de Am�ndoas</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=depilacao">Depila��o</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=aparelho-de-depilacao">Aparelho </a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cera-para-depilacao">Cera</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=creme-para-depilacao">Creme</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cortador-de-cabelos-depilacao">Cortador de Cabelos</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=folhas-para-depilacao">Folhas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=pos-depilacao">P�s Depila��o</a></li>


                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=estetica-do-homem">Est�tica do Homem</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antiqueda">Antiqueda</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=barba">Barba</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=base">Base</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=tinturas">Tinturas</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=protetor-solar-estetica">Protetor Solar</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fator-30">Fator 30</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fator-60">Fator 60</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=tintura-e-tratamento">Tintura e Tratamento</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=alisantes-e-relaxamentos">Alisantes e Relaxamentos </a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=descolorantes">Descolorantes</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=unhas-estetica">Unhas</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=alicate-e-cortador">Alicate e Cortador</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=esmaltes">Esmaltes</a></li>
                                    </ul>

                                </div>
                                <a href="ProdutoServlet?cmd=listarProdutos&categoria=estetica">
                                    <div>
                                        <img src="imagem/Produtos/imgSubmenu/estetica.jpg" width="100%" style="position: absolute;width: 22%;top: 0%; left: 78%;height: 100%; background-size: 100%; border:none;">

                                    </div>
                                </a>

                            </div><!-- /cbp-hrsub-inner -->
                        </div><!-- /cbp-hrsub -->
                    </li>
                    <li>
                        <a href="#">Gen�ricos</a>
                        <div class="cbp-hrsub">

                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=alergias-e-infeccoes">Alergias e Infec��es</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antialergicos">Antial�rgicos</a></li>                    
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=asma">Asma</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=diabetes">Diabetes</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=hipoglicemiante">Hipoglicemiante</a></li>

                                    </ul>    
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=gripes-e-resfriados">Gripes e Resfriados</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=atigripais">Antigripais</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=descongestionante-nasal">Descongestionante Nasal</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=tosse">Tosse</a></li>

                                    </ul>

                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=regulador-instestinal">Regulador Intestinal</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antigases">Antigases</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=digestivo">Digestivo</a></li>

                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=contusao-e-dores">Contusao e Dores</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=analgesico-e-antitermico">Analg�sico e Antit�rmico</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=anti-inflamatorio-e-relaxante">Anti-inflamat�rios e Relaxante Muscular</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=enxaqueca">Enxaqueca</a></li>

                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=pele-e-mucosa">Pele e Mucosa</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=anestesico">Anest�sico</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antimicoticos-e-fungos">Antimic�ticos e Fungos</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=despigmentador">Despigmentador</a></li>

                                    </ul>

                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=pressao-alta">Press�o Alta</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antihipertensivo">Antihipertensivo</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=diureticos">Diur�ticos</a></li>

                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=saude-masculina">Sa�de Masculina</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=disfuncao-eretil">Disfun��o Er�til</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=problemas-urinarios">Problemas Urin�rios</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=queda-de-cabelo">Queda de Cabelo</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=saude-feminina">Sa�de Feminina</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=anticoncepcionais">Anticoncepcionais</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=colicas-menstruais">C�licas Menstruais</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=creme-e-ovulos-vaginais">Creme e �vulos vaginais</a></li>

                                    </ul>

                                </div>

                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=respiratorio">Respirat�rio</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=bronco-respiratorio">Bronco Respirat�rio</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=nebulizador">Nebulizador</a></li>

                                    </ul>
                                    <a href="ProdutoServlet?cmd=listarProdutos&categoria=genericos">
                                        <div>
                                            <img src="imagem/Produtos/imgSubmenu/genericos.jpg" width="100%" style="position: absolute;width: 22%;top: 0%; left: 78%;height: 100%; background-size: 100%; border:none;">

                                        </div>
                                    </a>
                                </div>

                            </div><!-- /cbp-hrsub-inner -->
                        </div><!-- /cbp-hrsub -->
                    </li>
                    <li>
                        <a href="#">Higiene</a>
                        <div class="cbp-hrsub">
                            <div class="cbp-hrsub-inner">
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=absorventes-higiene">Absorventes</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=ProdutoServlet?cmd=listarProdutos&categoria=absorventes-higiene">Mais</a></li>                    
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=barbas-higiene">Barbas</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=barbeador">Barbeador</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=espuma-para-barbear">Espuma</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=corporal-higiene">Corporal</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=sabonetes-higiene">Sabonetes</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=condicionador-higiene">Condicionador</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cabelos-oleosos">Cabelos Oleosos</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=danificado-e-seco">Danificado e Seco</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=pos-quimica">P�s-quimica</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=geriatrico">Geri�trico</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=absorvente-geriatrico">Absorvente</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fralda-geriatrico">Fralda</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=roupa-intima-geriatrico">Roupa �ntima</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=oral-higiene">Oral</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=creme-dental-higiene">Creme Dental</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fio-dental-higiene">Fio Dental</a></li>
                                    </ul>
                                </div>

                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=pessoal-higiene">Pessoal</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=algodao-e-hastes">Algod�o e Hastes</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=repelentes">Repelentes</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=aerosol">Aerosol</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=locao-higiene">Lo��o</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=spray-higiene">Spray</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=vida-sexual">Vida Sexual</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=lubrificantes-higiene">Lubrificantes</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=preservativos">Preservativos</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=preservativos-masculino">Preservativos Masculino</a></li>
                                    </ul>
                                </div>


                                <a href="ProdutoServlet?cmd=listarProdutos&categoria=higiene">
                                    <div>
                                        <img src="imagem/Produtos/imgSubmenu/higiene.jpeg" width="100%" style="position: absolute;width: 22%;top: 0%; left: 78%;height: 100%; background-size: 100%; border:none;">

                                    </div>
                                </a>
                            </div><!-- /cbp-hrsub-inner -->
                        </div><!-- /cbp-hrsub -->
                    </li>
                    <li>
                        <a href="#">Maquiagem</a>
                        <div class="cbp-hrsub">
                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=rosto-maquiagem">Rosto</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=base-rosto">Base</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=blush-rosto">Blush</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=corretivo-rosto">Corretivo</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=demaquilante-rosto">Demaquilante</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fixador-rosto">Fixador</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=iluminador-rosto">Iluminador</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=po-facial-rosto">P� Facial</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=pre-maquiagem-rosto">Pr�-maquiagem</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=labios-maquiagem">L�bios</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=batom-labios">Batom</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=batom-hidratante-labios">Batom Hidratante</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=brilho-labial">Brilho labial</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=lapis-para-labios">L�pis</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=olhos-maquiagem">Olhos</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cilios-posticos">C�lios Post��os</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=delineador-olhos">Delineador</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=demaquilante-olhos">Demaquilante</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=lapis-para-olhos">L�pis</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=lapis-de-sobrancelha">L�pis de Sobrancelha</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=mascara-de-cilios">M�scara de C�lios</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=pinca-para-olhos">Pin�a</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=sombras-para-olhos">Sombras</a></li>
                                    </ul>
                                </div>
                                <a href="ProdutoServlet?cmd=listarProdutos&categoria=maquiagem">
                                    <div>
                                        <img src="imagem/Produtos/imgSubmenu/maquiagem.jpg" width="100%" style="position: absolute;width: 35%;top: 0%; left: 65%;height: 100%; background-size: 100%; border:none;">

                                    </div>
                                </a>
                            </div><!-- /cbp-hrsub-inner -->
                        </div><!-- /cbp-hrsub -->
                    </li>
                    <li>
                        <a href="#">Nutri��o</a>
                        <div class="cbp-hrsub">
                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=alimentos-nutricao">Alimentos</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=adocante">Ado�ante</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=balas">Balas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=barra-de-cereal">Barra de Cereal</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=biscoitos">Biscoitos</a></li>                              
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=chicletes">Chicletes</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=chocolates">Chocolates</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=complemento-alimentar-alimentos">Complemento Alimentar</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=frutas-secas">Frutas Secas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=geleias">Gel�ias</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=granola">Granola</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=sal">Sal</a></li>                                        



                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=bebidas">Bebidas</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=achocolatado">Achocolatado</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=agua">�gua</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cha">Ch�</a></li>   
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=energetico">Energ�tico</a></li>  
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=isotonico">Isot�nico</a></li>    
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=suco">Suco</a></li>                          
                                    </ul>

                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=emagrecedores">Emagrecedores</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=capsulas-e-tabletes">C�psulas e Tabletes</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=shakes">Shakes</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=naturais">Naturais</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cha">Ch�</a></li>

                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=nutricao-esportiva">Nutri��o Esportiva</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=energetico-nutricao-esportiva">Energ�ticos</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=massa-muscular">Massa Muscular</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=suplemento">Suplemento</a></li>
                                    </ul>

                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=nutricao-infantil">Nutri��o Infant�l</a></h4>

                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=complemento-alimentar-nutricao-infantil">Complemento Alimentar</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=leites-e-formulas-infantis">Leites e F�rmulas Infantis</a></li>
                                    </ul>
                                </div>
                                <a href="ProdutoServlet?cmd=listarProdutos&categoria=nutricao">
                                    <div>
                                        <img src="imagem/Produtos/imgSubmenu/nutricaoEsportiva.png" width="100%" style="position: absolute;width: 40%;top: 0%; left: 60%;height: 100%; background-size: 100%; border:none;">

                                    </div>
                                </a>
                            </div><!-- /cbp-hrsub-inner -->
                        </div><!-- /cbp-hrsub -->
                    </li>
                    <li>
                        <a href="#">Ortopedia</a>
                        <div class="cbp-hrsub">
                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=acessorios-ortopedia">Acess�rios</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=bola-de-fisioterapia">Bola de Fisioterapia</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=bolsa-termica">Bolsa T�rmica</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=faixa-abdominal-e-toracica">Faixa abdominal e Tor�cica</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=massageador-ortopedia">Massageador</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=suspensorio-escrotal">Suspens�rio Escrotal</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=cabeca-ortopedia">Cabe�a</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=mascara-cabeca">M�scara</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=protetor-de-ouvido">Protetor de Ouvido</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=maos-e-bracos-ortopedia">M�os e Bra�os</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=luvas">Luvas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=munhequeiras">Munhequeiras</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=tala-para-tendinite">Tala para Tendinite</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=pes-e-pernas-ortopedia">P�s e Pernas</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=calcanheira-ortopedia">Calcanheira</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=meias-pes-e-pernas">Meias</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=palmilha">Palmilha</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=protetor-adesivo-pes-e-pernas">Protetor Adesivo</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=tornozeleira-e-joelheira">Tornozeleira e Joelheira</a></li>
                                    </ul>
                                </div>
                                <a href="ProdutoServlet?cmd=listarProdutos&categoria=ortopedia">
                                    <div>
                                        <img src="imagem/Produtos/imgSubmenu/ortopedia.jpg" width="100%" style="position: absolute;width: 40%;top: 0%; left: 60%;height: 100%; background-size: 100%; border:none;">

                                    </div>
                                </a>
                            </div><!-- /cbp-hrsub-inner -->
                        </div><!-- /cbp-hrsub -->
                    </li>



                </ul>
            </nav>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/cbpHorizontalMenu.min.js"></script>
        <script>
            $(function () {
                cbpHorizontalMenu.init();
            });
        </script>
        <div id="conteudo">

            <div id class="titulo" style="margin-top: 4%;"><a href="index.jsp">Drogaria Pharmaluci</a> - <a href="ProdutoServlet?cmd=listarProdutos&categoria=${categoria}">${categoria}</a> - <a href="#"><b>${nomeBusca}</b></a> </div>


            <div id="conteudoProdutos">
                <c:if test="${nomeBusca == ''}">
                    <script>
                        alert('Informe o nome do produto desejado');

                    </script>
                    <h3 style="margin-left: 22%">Informe um Medicamento v�lido para ser buscado!.</h3>
                </c:if>
                <c:if test="${nomeBusca != ''}">
                    <c:forEach var="produto" items="${ requestScope.produtoList }" varStatus="count">
                        <!-- Importante o form deve ir dentro do c:forEach-->
                        <c:set var="contador" value="${count.count}"/>
                        <c:if test="${user != null}">
                            <form method="POST" action="PedidoServlet?cmd=adc">
                            </c:if>
                            <c:if test="${user == null}">
                                <form action="PedidoServlet?cmd=verificaUsuario" method="POST">
                                </c:if>
                                <input type="hidden" name="idRemedio" value="${produto.idRemedio}"  readonly="readonly">
                                <div class="produto" style="margin: 1%; width: 23%; margin-top: 4%;" >
                                    <!-- Mandando o idRemedio para o servlet-->

                                    <a href="ProdutoServlet?cmd=mostraProduto&idRemedio=${produto.idRemedio}" title="${produto.nome}">


                                        <img src="imagem/Produtos/${produto.idRemedio}.jpg" width="100%" height="216px"></a><br>
                                    <div class="nome"><br>
                                        ${produto.nome} <fmt:formatNumber maxFractionDigits="2">${produto.medida}</fmt:formatNumber>${produto.unidadeMedida} 
                                        </div>
                                        <div class="preco">
                                            <hr size="1" width="100%" color="white">
                                            <div class="precoAntigo">De <fmt:formatNumber type="currency">${produto.precoAntigo}</fmt:formatNumber></div>
                                        Por <fmt:formatNumber type="currency" value="${produto.preco}"/>
                                        <input type="hidden" name="estado" value="0">
                                    </div> 
                                    <input type="hidden" name="idUsuario" value="${user.idUsuario}">
                                    <input type="hidden" name="necessitaEntrega" value="">
                                    <input type="hidden" name="preco" value="${produto.preco}"  readonly="readonly">
                                    <div class="quantidadeProduto"><input type="number"  name="quantidadePedido"  min="1" max="${produto.quantidadeEstoque}" value="${produto.quantidadeEstoque}"></div>
                                        <c:if test="${produto.quantidadeEstoque != 0}">
                                        <input type="submit" value="Adicionar">
                                    </c:if>
                                    <c:if test="${produto.quantidadeEstoque == 0}">
                                        <a href="ProdutoServlet?cmd=mostraProduto&idRemedio=${produto.idRemedio}" style="text-decoration: none"><input type="button" value="+ Detalhes" style="width: 100%; margin-left: 0;height: 38px;"></a>
                                        </c:if>
                                        <c:set var="idRemedio" value="${produto.idRemedio}"/>
                                </div>
                            </form>
                        </c:forEach>                                                                          

                        <br>
                        <c:if test="${idRemedio == null}">
                            <script>alert('Produto n�o encontrado, verifique se o nome do medicamento foi digitado corretamente');</script>
                            <h3 style="margin-top: 1%; margin-left: 18%;">Nenhum Medicamento encontrado com esse nome!.</h3>
                        </c:if><br>
                    </c:if>                   
                    </div>

                    <div id="menuCategorias">
                        <c:forEach var="produtoBanco" items="${cate.listarCategorias()}" varStatus="count">
                            <c:set var="contadorProdutos" value="${count.count}"/>
                            <a href="ProdutoServlet?cmd=listarProdutos&categoria=${produtoBanco.categoria}"><h3><font color="#47a3da">${produtoBanco.categoria}</font></h3></a><p>
                                <a href="ProdutoServlet?cmd=buscar&nomeBusca=${produtoBanco.nome}"><font color="#5d5858" style="font-size: 11pt;">${produtoBanco.nome}</font></a><br>
                                <a href="ProdutoServlet?cmd=listarProdutos&categoria=${produtoBanco.categoria}"><font style="color:#999;">Mais</font></a><br><br>        
                                    </c:forEach>
                                <font color="#999">Produtos encontrados: ${contadorProdutos}</font>
                    </div>
                    <c:if test="${contador >1 and nomeBusca != ''}">
                        <div id class="filtros">
                            <h3>Filtros</h3>
                            <a href="ProdutoServlet?cmd=ordemAlfabetica&categoria=${categoria}" title="Ordem alfab�tica" style="color:#3d3f41;">A-z</a><br>
                            <a href="ProdutoServlet?cmd=ordemDecrescente&categoria=${categoria}" title="Ordem decrescente" style="color:#3d3f41;">Z-a</a><br>
                            <a href="ProdutoServlet?cmd=dataLancamento&categoria=${categoria}" style="color:#3d3f41;">Data Lan�amento</a><br><br>

                            <h3>Pre�os</h3><p>
                                <a href="ProdutoServlet?cmd=menorPreco&categoria=${categoria}" title="Menor pre�o" style="color:#3d3f41;">Menor pre�o</a><br>
                                <a href="ProdutoServlet?cmd=maiorPreco&categoria=${categoria}" style="color:#3d3f41;"> Maior Pre�o</a><br>                    
                                <a href="ProdutoServlet?cmd=filtroPreco&categoria=${categoria}&precoInformado=10" style="color:#3d3f41;">At� R$10</a><br>                                                
                                <a href="ProdutoServlet?cmd=filtroPrecoBetween&categoria=${categoria}&preco1=11&preco2=20" style="color:#3d3f41;">At� R$ 11-20</a><br>                   
                                <a href="ProdutoServlet?cmd=filtroPrecoBetween&categoria=${categoria}&preco1=21&preco2=30" style="color:#3d3f41;"> De R$21-30</a> <br>            
                                <a href="ProdutoServlet?cmd=filtroPrecoBetween&categoria=${categoria}&preco1=30&preco2=50" style="color:#3d3f41;">De R$ 30-50</a><br>                      
                                <a href="ProdutoServlet?cmd=filtroPrecoBetween&categoria=${categoria}&preco1=50&preco2=100" style="color:#3d3f41;">De R$ 50-100</a><br>                    
                                <a href="ProdutoServlet?cmd=filtroPrecoBetween&categoria=${categoria}&preco1=100&preco2=200" style="color:#3d3f41;">De R$ 100-200</a><br>                     
                                <a href="ProdutoServlet?cmd=filtroPrecoMaior&categoria=${categoria}&precoInformado=200" style="color:#3d3f41;">Acima de R$ 200</a>           


                        </div>
                    </c:if>
                    <font style="float: left; margin-left:1%; margin-top:1% ; color:#999;">Produtos encontrados: ${contador}</font><br><br>

                    </div>
                    <div id="rodape">
                        <div class="table">
                            <h3> Menu</h3>
                            <hr size="2" width="100%" ><br>
                            &raquo <a href="index.jsp"> In�cio<br><br></a>
                            &raquo <a href="farmacia.jsp">Farm�cia<br><br></a>
                            &raquo <a href="ProdutoServlet?cmd=listarProdutos&categoria=${''}"> Produtos<br><br></a>
                            &raquo <a href="ProdutoServlet?cmd=listarPromocoes#promocoes">Promo��es<br><br></a>
                            &raquo <a href="entrar.jsp">Entrar | Cadastro</a>
                        </div>
                        <div class="table">
                            <h3> Fale Conosco</h3>
                            <hr size="2" width="100%"><br>

                            <a href="#"><img src="imagem/facebook.png"   width="50px" height="40px"></a> <font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo CentralMercado</font>
                            <a href="#"><img src="imagem/skype.png"   width="50px" height="40px"></a><font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo  MercadoCentralS</font>
                            <a href="#"><img src="imagem/watsap.jpg"   width="50px" height="40px"></a><font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo (61) 3356-7000</font>
                        </div>
                        <div class="table">
                            � 2015 Drogaria Pharmaluci. Todos direitos reservados.

                        </div>
                    </div>


                    </body>
                    </html>
