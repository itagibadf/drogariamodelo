<%-- 
    Document   : liquidos
    Created on : 28/08/2015, 10:46:56
    Author     : itagiba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="controller.Usuario"%>
<jsp:useBean id="catePedido" class="controller.PedidoDAO"/>


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
        <title>Medicamento- ${produto.nome}</title>
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

        <div id="topo">
            <a href="farmacia.jsp" title="Onde estamos"><div id class="iconeTopo" style="background-image: url('imagem/localizacao.png'); "></div><div id class="textoIcone"><font color="white">Localização</font></div></a>
                        <c:if test="${user == null && gerente == null}">
                <a href="entrar.jsp" title="Se cadastre aqui"><div id class="iconeTopo" style="background-image: url('imagem/cadastro.png'); margin-left: 0.7%; border-radius: 20px;"></div><div id class="textoIcone" style="margin-left: 0.3%;"><font color="white">Me cadastrar</font></div></a>
                        </c:if>  
            <a href="entrar.jsp" title="Acesse sua conta"><div id class="iconeTopo" style="background-image: url('imagem/usuario.png'); margin-left: 0.7%"></div><div id class="textoIcone" style="margin-left: 0.3%;"><font color="whitesmoke">Entrar</font></div></a>
                        <c:if test="${gerente != null}">
                <div id class="iconeTopo" style="background-image: url('imagem/usuario2.png'); margin-left: 0.7%"></div><div id class="textoIcone" style="margin-left: 0.3%; width: 30%;"><font color="white">Olá!</font> (<a href="UsuarioServlet?cmd=atuGerente&idUsuario=${gid}"><font color="white"><b>${gerente.nome}</b></font></a> |<a href="UsuarioServlet?cmd=sair"> <font color="white"><b>Sair</b></font></a>)</a></div>
                    </c:if>
                    <c:if test="${user != null}">
                <div id class="iconeTopo" style="background-image: url('imagem/usuario2.png'); margin-left: 0.7%"></div><div id class="textoIcone" style="margin-left: 0.3%; width: 30%;"><font color="white">Olá!</font> (<a href="UsuarioServlet?cmd=procuraUsuarioPerfil&idUsuario=${user.idUsuario}"><font color="white"><b>${user.nome}</b></font></a> |<a href="UsuarioServlet?cmd=sair"> <font color="white"><b>Sair</b></font></a>)</a></div>
                        <c:forEach var="pedido" items="${catePedido.pedidoUsuarioID(uid)}" varStatus="count">
                            <c:set var="contador" value="${count.count}"/>
                            <c:set var="idPedido" value="${pedido.idPedido}"/>
                            <c:set var="valorTotal" value="${pedido.valorTotal}"/>
                            <c:set var="total" value="${total+valorTotal}"/>
                        </c:forEach>
                <a href="PedidoServlet?cmd=listarPedidosUsuario&idUsuario=${user.idUsuario}" >   
                    <c:choose>
                        <c:when test="${contador != null}">
                            <div id class="imgCarrinho" title="Voce possui ${contador} item(s) em seu carrinho, Total:<fmt:formatNumber type="currency" value="${total}"/>. Clique e veja">
                                <font color="whitesmoke">${contador}</font>                                
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div id class="imgCarrinho" title="Seu carrinho está vazio.">
                                <font color="whitesmoke">0</font>                                
                            </div>
                        </c:otherwise>
                    </c:choose>
                </a>  
            </c:if>

            <div id="busca">
                <form action="ProdutoServlet?cmd=buscar" method="POST">
                    <div id class="textoBusca">
                        <input name="nomeBusca" placeholder=" Pesquise o Medicamento desejado" title="O que procura?" style=' width:60%; height: 42px'>    
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
                        <div class="cbp-hrsub">
                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=acessorios-aparelhos-e-testes">Acessórios</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=abaixador-de-lingua">Abaixador de Língua</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=coletor-de-fezes-e-urina">Coletor de Fezes e Urina</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=dilatador-nasal">Dilatador Nasal</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=protetor-auditivo">Protetor Auditivo</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=termometro">Termômetro</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=aparelhos">Aparelhos</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=balanca">Balança</a></li>
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
                        <a href="#">Bebê e Mãe</a>
                        <div class="cbp-hrsub">

                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=acessorios-de-bebe">Acessórios de Bebê</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=chupetas">Chupetas</a></li> 
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=mamadeiras">Mamadeiras</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=mordedores">Mordedores</a></li>

                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=alimentos-de-bebe">Alimentos de Bebê</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=complemento-alimentar-bebe">Complemento Alimentar</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=leites-para-bebe">Leites</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=papinhas">Papinhas</a></li>                               

                                    </ul>                                    
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=corpo-e-banho">Corpo e Banho</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=shampoo-de-bebe">Shampoo de Bebê</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=repelente-de-bebe">Repelente de Bebê</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=sabonete-liquido-de-bebe">Sabonete Líquido de Bebê</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=talco-de-bebe">Talco de Bebê</a></li>




                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=cuidado-da-mae">Cuidado da Mãe</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=absorventes-para-seios">Absorvente para Seios</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=amamentacao">Amamentação</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=meias-elasticas">Meias Elásticas</a></li>
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
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=para-troca-do-bebe">Para troca do Bebê</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=assaduras-do-bebe">Assaduras do Bebê</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fitas-adesivas">Fitas Adesivas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=lencos-umedecidos">Lenços Umedecidos</a></li>                                
                                    </ul>

                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=saude-bucal-do-bebe">Saúde Bucal do Bebê</a></h4>
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
                        <a href="#">Dermocosméticos</a>
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
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=colirios-olhos-dermocosmeticos">Colírios</a></li>

                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=rosto-dermocosmeticos">Rosto</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=agua-termal">Água Termal</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=anti-envelhecimento">Anti-envelhecimento</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antirrugas">Antirrugas</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=hidratante">Hidratante</a></li>                                         
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=protetor-solar-para-rosto">Protetor Solar</a></li> 

                                    </ul>
                                </div>

                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=nutricosmeticos">Nutricosméticos</a></h4>
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
                        <a href="#">Estética</a>
                        <div class="cbp-hrsub">
                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=acessorios-estetica">Acessórios</a></h4>
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
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=oleos-de-amendoas">Òleos de Amêndoas</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=depilacao">Depilação</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=aparelho-de-depilacao">Aparelho </a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cera-para-depilacao">Cera</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=creme-para-depilacao">Creme</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cortador-de-cabelos-depilacao">Cortador de Cabelos</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=folhas-para-depilacao">Folhas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=pos-depilacao">Pós Depilação</a></li>


                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=estetica-do-homem">Estética do Homem</a></h4>
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
                        <a href="#">Genéricos</a>
                        <div class="cbp-hrsub">

                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=alergias-e-infeccoes">Alergias e Infecções</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antialergicos">Antialérgicos</a></li>                    
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
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=analgesico-e-antitermico">Analgésico e Antitérmico</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=anti-inflamatorio-e-relaxante">Anti-inflamatórios e Relaxante Muscular</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=enxaqueca">Enxaqueca</a></li>

                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=pele-e-mucosa">Pele e Mucosa</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=anestesico">Anestésico</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antimicoticos-e-fungos">Antimicóticos e Fungos</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=despigmentador">Despigmentador</a></li>

                                    </ul>

                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=pressao-alta">Pressão Alta</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=antihipertensivo">Antihipertensivo</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=diureticos">Diuréticos</a></li>

                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=saude-masculina">Saúde Masculina</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=disfuncao-eretil">Disfunção Erétil</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=problemas-urinarios">Problemas Urinários</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=queda-de-cabelo">Queda de Cabelo</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=saude-feminina">Saúde Feminina</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=anticoncepcionais">Anticoncepcionais</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=colicas-menstruais">Cólicas Menstruais</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=creme-e-ovulos-vaginais">Creme e óvulos vaginais</a></li>

                                    </ul>

                                </div>

                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=respiratorio">Respiratório</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=bronco-respiratorio">Bronco Respiratório</a></li>
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
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=pos-quimica">Pós-quimica</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=geriatrico">Geriátrico</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=absorvente-geriatrico">Absorvente</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=fralda-geriatrico">Fralda</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=roupa-intima-geriatrico">Roupa íntima</a></li>
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
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=algodao-e-hastes">Algodão e Hastes</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=repelentes">Repelentes</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=aerosol">Aerosol</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=locao-higiene">Loção</a></li>
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
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=po-facial-rosto">Pó Facial</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=pre-maquiagem-rosto">Pré-maquiagem</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=labios-maquiagem">Lábios</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=batom-labios">Batom</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=batom-hidratante-labios">Batom Hidratante</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=brilho-labial">Brilho labial</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=lapis-para-labios">Lápis</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=olhos-maquiagem">Olhos</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cilios-posticos">Cílios Postíços</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=delineador-olhos">Delineador</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=demaquilante-olhos">Demaquilante</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=lapis-para-olhos">Lápis</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=lapis-de-sobrancelha">Lápis de Sobrancelha</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=mascara-de-cilios">Máscara de Cílios</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=pinca-para-olhos">Pinça</a></li>
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
                        <a href="#">Nutrição</a>
                        <div class="cbp-hrsub">
                            <div class="cbp-hrsub-inner"> 
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=alimentos-nutricao">Alimentos</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=adocante">Adoçante</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=balas">Balas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=barra-de-cereal">Barra de Cereal</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=biscoitos">Biscoitos</a></li>                              
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=chicletes">Chicletes</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=chocolates">Chocolates</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=complemento-alimentar-alimentos">Complemento Alimentar</a></li>                                        
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=frutas-secas">Frutas Secas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=geleias">Geléias</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=granola">Granola</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=sal">Sal</a></li>                                        



                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=bebidas">Bebidas</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=achocolatado">Achocolatado</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=agua">Água</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cha">Chá</a></li>   
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=energetico">Energético</a></li>  
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=isotonico">Isotônico</a></li>    
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=suco">Suco</a></li>                          
                                    </ul>

                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=emagrecedores">Emagrecedores</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=capsulas-e-tabletes">Cápsulas e Tabletes</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=shakes">Shakes</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=naturais">Naturais</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=cha">Chá</a></li>

                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=nutricao-esportiva">Nutrição Esportiva</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=energetico-nutricao-esportiva">Energéticos</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=massa-muscular">Massa Muscular</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=suplemento">Suplemento</a></li>
                                    </ul>

                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=nutricao-infantil">Nutrição Infantíl</a></h4>

                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=complemento-alimentar-nutricao-infantil">Complemento Alimentar</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=leites-e-formulas-infantis">Leites e Fórmulas Infantis</a></li>
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
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=acessorios-ortopedia">Acessórios</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=bola-de-fisioterapia">Bola de Fisioterapia</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=bolsa-termica">Bolsa Térmica</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=faixa-abdominal-e-toracica">Faixa abdominal e Torácica</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=massageador-ortopedia">Massageador</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=suspensorio-escrotal">Suspensório Escrotal</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=cabeca-ortopedia">Cabeça</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=mascara-cabeca">Máscara</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=protetor-de-ouvido">Protetor de Ouvido</a></li>
                                    </ul>
                                </div>
                                <div>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=maos-e-bracos-ortopedia">Mãos e Braços</a></h4>
                                    <ul>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=luvas">Luvas</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=munhequeiras">Munhequeiras</a></li>
                                        <li><a href="ProdutoServlet?cmd=listarProdutos&categoria=tala-para-tendinite">Tala para Tendinite</a></li>
                                    </ul>
                                    <h4><a href="ProdutoServlet?cmd=listarProdutos&categoria=pes-e-pernas-ortopedia">Pés e Pernas</a></h4>
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

        <div id="conteudo" style="overflow: auto;">
            <div id class="titulo" style="margin-top:3.2% ;left: 1%;  width: auto;">
                <a href="index.jsp">Drogaria Pharmaluci</a> - <a href="ProdutoServlet?cmd=listarProdutos&categoria=${produto.categoria}">${produto.categoria}</a> -<a href="#"> <b>${produto.nome}</b></a>
            </div>

            <!-- Importante o form deve ir dentro do c:forEach-->
            <c:if test="${user != null}">
                <form method="POST" action="PedidoServlet?cmd=adc">
                </c:if>
                <c:if test="${user == null}">
                    <form action="PedidoServlet?cmd=verificaUsuario" method="POST">                                                    
                    </c:if>
                    <!-- Buscando a imagem segundo a categoria setada e a extensão da imagem-->

                    <div id="imgProduto">
                        <a href="#imgProduto" onclick="window.open('imagem/Produtos/${produto.idRemedio}.jpg','janela','width=435px, height=310px, left= 515px, top= 85px ')" title="${produto.nome}">                       
                            <img src="imagem/Produtos/${produto.idRemedio}.jpg" width="100%" height="100%">
                        </a>
                    </div>
                    <div id="conteudoCompra">                        
                        <div id="quantidadeProduto">
                            <font color="#5d5858">Quantidade</font><br>
                            <input type="number"  name="quantidadePedido" placeholder="Qtd."  min="1" max="${produto.quantidadeEstoque}" value="${produto.quantidadeEstoque}" style="height:35px; left: 0%;">
                        </div>
                        <div id="preco">
                            <fmt:formatNumber type="currency" value="${produto.preco}"/><br><br>
                        </div>
                        <input type="hidden" name="idRemedio" value="${produto.idRemedio}">
                        <input type="hidden" name="preco" value="${produto.preco}"  readonly="readonly">     
                        <input type="hidden" name="nome" value="${produto.nome}">
                        <input type="hidden" name="idUsuario" value="${user.idUsuario}">
                        <input type="hidden" name="necessitaEntrega" value="">                    
                        <input type="hidden" name="enderecoEntrega" value="${user.endereco}">
                        <input type="hidden" name="estado" value="0">
                        <c:if test="${produto.quantidadeEstoque != 0}">
                            <input type="submit" value="Adicionar" style="margin-left:9.4%; margin-top: 0; width: 81.5%">
                        </c:if>
                        </form>
                        </form> 
                        <c:if test="${produto.quantidadeEstoque == 0}">
                            <a href="#" style="text-decoration:none;"><input type="button" onclick="alert('Produto fora de estoque, aguarde o periodo de reposição')" value="+ Detalhes" style="margin-top:10%; margin-left: 9.4%; width: 81.5%; height: 40px;">                                
                            </a>
                        </c:if>
                        <div id class="frete"><img src="imagem/frete2.png" width="13%" heigth="30px"><div id class="textoFrete">
                                <b> Frete e Prazo<br></b>
                            </div>
                            <font style="margin-left:14%; font-family: fantasy; "><b><p align="left">Frete Grátis:</b></p><p>
                                    <p>ADE, Aguas Cláras, Areal, ParkWay, Qs10 e Qs11<p>
                                    Riacho Fundo I, Sha(s).
                                <p align="left"><b>Prazo:</b></p><p>
                                   <p align="left">Até 6Horas</p>
                            </font>

                        </div><br>

                    </div>
                    <div id="nome" style="text-transform: uppercase;"><b>
                            ${produto.nome}</b><br><br> 
                        Medida - <fmt:formatNumber  maxFractionDigits="2">${produto.medida}</fmt:formatNumber>${produto.unidadeMedida}
                        </div><br>
                        <div id="descricao" >
                        <c:if test="${produto.quantidadeEstoque != 0}">
                            ${produto.descricao} 
                        </c:if>
                        <c:if test="${produto.quantidadeEstoque == 0}">
                            O estoque deste produto está esgotado, aguarde o periodo de reposição que tem duração de até 3 días, para mais informações ligue: 0800-123456

                        </c:if><p>                        
                        <div id="imgSeguro">                               
                        </div>                        
                    </div>
                    <a href="#sobreProduto" style="text-decoration:none;"><input type="button" value="+ Detalhes"></a>

                    <div id="sobreProduto"><h3>Sobre o Produto</h3><br>
                        ${produto.descricaoCompleta}</div><br>




                    </div>
                    <div id="rodape" style=" float:left; top: 270%">
                        <div class="table">
                            <h3> Menu</h3>
                            <hr size="2" width="100%" ><br>
                            &raquo <a href="index.jsp"> Início<br><br></a>
                            &raquo <a href="farmacia.jsp">Farmácia<br><br></a>
                            &raquo <a href="ProdutoServlet?cmd=listarProdutos&categoria=${''}"> Produtos<br><br></a>
                            &raquo <a href="ProdutoServlet?cmd=listarPromocoes#promocoes">Promoções<br><br></a>
                            &raquo <a href="entrar.jsp">Entrar | Cadastro</a>
                        </div>
                        <div class="table">
                            <h3> Fale Conosco</h3>
                            <hr size="2" width="100%"><br>

                            <a href="#"><img src="imagem/facebook.png"   width="50px" height="40px"></a> <font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo Drogaria</font>
                            <a href="#"><img src="imagem/skype.png"   width="50px" height="40px"></a><font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo  Drogaria</font>
                            <a href="#"><img src="imagem/watsap.jpg"   width="50px" height="40px"></a><font style=" font-family: helvetica; font-size: 12pt; display: table;"> &raquo (61) 3356-7000</font>
                        </div>
                        <div class="table">
                            © 2015 Drogaria Pharmaluci. Todos direitos reservados.

                        </div>
                    </div>


                    </body>
                    </html>
