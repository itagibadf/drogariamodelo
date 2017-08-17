/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrator;

import controller.Produto;
import controller.ProdutoDAO;
import controller.Pedido;
import controller.PedidoDAO;
import controller.Usuario;
import controller.UsuarioDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Itagiba
 */
public class ProdutoServlet extends HttpServlet {
//Classe Servlet responpsavel por direcionar as ações de persistencia do Banco de Dados

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        /* Esse Servlet criado tem que captar o comando (representado pelos caracteres cmd)
        para que seja decidida qual ação tomar de acordo com a chamada.
         */
        String cmd = request.getParameter("cmd");

        if (cmd == null) {
            cmd = "principal";
        }

        // A classe ProdutoDao criada é chamada.
        Produto produto = new Produto();
        ProdutoDAO dao;
        Usuario usuario = new Usuario();

        if (cmd != null || !cmd.equalsIgnoreCase("principal")) {
            try {
                String id = request.getParameter("idRemedio");
                if (id != null) //Request.getParameter só lê do tipo texto é necessário converter valores
                {
                    produto.setIdRemedio(Integer.parseInt(id));
                }
                produto.setNome(request.getParameter("nome"));
                produto.setDescricao(request.getParameter("descricao"));
                produto.setDescricaoCompleta(request.getParameter("descricaoCompleta"));
                produto.setPrecoAntigo(Double.parseDouble(request.getParameter("precoAntigo")));
                produto.setPreco(Double.parseDouble(request.getParameter("preco")));
                produto.setQuantidadeEstoque(Double.parseDouble(request.getParameter("quantidadeEstoque")));
                produto.setMedida(Double.parseDouble(request.getParameter("medida")));
                produto.setUnidadeMedida(request.getParameter("unidadeMedida"));
                produto.setValidade(request.getParameter("validade"));
                produto.setComprimento(Double.parseDouble(request.getParameter("comprimento")));
                produto.setLargura(Double.parseDouble(request.getParameter("largura")));
                produto.setAltura(Double.parseDouble(request.getParameter("altura")));
                produto.setPeso(Double.parseDouble(request.getParameter("peso")));
                produto.setCategoria(request.getParameter("categoria"));
                produto.setPromocao(request.getParameter("promocao"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        try {
            /*Dentro de um bloco try/catch, o dao é instanciado à classe ProdutoDao(),
              tornando-o pronto para uso de métodos.*/
            dao = new ProdutoDAO();
            RequestDispatcher rd = null;

            if (cmd.equals("listarProdutos")) {
                String cat = request.getParameter("categoria");

                ProdutoDAO produtoDAO = new ProdutoDAO();
                //Puxando a ação do banco de dados de inserir ou cadastrar usuários
                List produtoList = produtoDAO.leUmCategoria(cat);
                // Setando o valor de uma variável dentro de uma sessão do navegador
                request.setAttribute("produtoList", produtoList);
                //Setando o valor da categoria escolhido pelo usuário dentro do escopo do navegador
                request.setAttribute("cat", cat);

                rd = request.getRequestDispatcher("produtos.jsp");
            }
            //Mostra as informações do produto através do ID escolhido
            if (cmd.equals("mostraProduto")) {
                //produto é o nome utilizado para puxar os campos do construtor produto
                produto = dao.leUm(Integer.parseInt(request.getParameter("idRemedio")));
                /*Importante buscando a sessão verdadeira do produto e setando na sessão.
                Facilita na hora de chamar algum atributo ex: ${produto.nome}*/
                HttpSession session = request.getSession(true);
                session.setAttribute("produto", produto);

                rd = request.getRequestDispatcher("exibirProduto.jsp");

            }
            if (cmd.equalsIgnoreCase("listar")) {

                List produtoList = dao.todosProdutos();
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("/mostrarProdutos.jsp");

                /*O segundo comando listado é o de adicionar dados.
                 Nesse caso os dados serão transmitidos para serem inseridos no banco de dados.
                 Os setters criado anteriormente serão os responsáveis por capturar os dados vindos
                 do formulário. Ao transmitir o formulário, esse, além de chamar o Servlet,
                 deverá enviar a query string cmd=adc.

                 Ao verificar que é para adicionar os dados, o Servlet chamará o método
                 salvar(Produto  produto), que transmitirá os dados enviados por esse formulário.
                 E após a chamada desse método, uma página é redirecionada para mostrar os dados
                 pela inserção.
                 */
            } else if (cmd.equalsIgnoreCase("listarPromocoes")) {
                List produtoList = dao.listarPromocoes();
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("promocoes.jsp");
            } else if (cmd.equalsIgnoreCase("leCategoria")) {
                String cat = request.getParameter("categoria");
                //Puxando a ação do banco de dados de inserir ou adastrar usuários
                dao.leUmCategoria(cat);
                rd = request.getRequestDispatcher("produtos.jsp?categoria=" + cat);
            } else if (cmd.equalsIgnoreCase("buscar")) {
                String nomeBusca = request.getParameter("nomeBusca");
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.buscaProduto(nomeBusca);
                request.setAttribute("produtoList", produtoList);
                request.setAttribute("nomeBusca", nomeBusca);
                rd = request.getRequestDispatcher("buscarProduto.jsp");
            }else if(cmd.equalsIgnoreCase("procurarProdutoLista")){
                String nomeBusca = request.getParameter("nomeBusca");
                ProdutoDAO produtoDAO =  new ProdutoDAO();
                List produtoList = produtoDAO.buscaProdutoLista(nomeBusca);
                request.setAttribute("produtoList", produtoList);
                request.setAttribute("nomeBusca", nomeBusca);
                rd= request.getRequestDispatcher("mostrarProdutos.jsp");
            
                
            }
            
            /*Buscando no BD as categorias existentes dos produtos*/ else if (cmd.equalsIgnoreCase("listarCategorias")) {
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.listarCategorias();
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("buscaCategorias.jsp");
            } /*Filtrando os remedios pelo menor preço*/ else if (cmd.equalsIgnoreCase("menorPreco")) {
                String categoria = request.getParameter("categoria");
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.menorPreco(categoria);
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("produtos.jsp");
            } /*Filtrando pelo maior preço*/ else if (cmd.equalsIgnoreCase("maiorPreco")) {
                String categoria = request.getParameter("categoria");
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.maiorPreco(categoria);
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("produtos.jsp");
            } /*Filtrando em ordem alfabetica os remedios*/ else if (cmd.equalsIgnoreCase("ordemAlfabetica")) {
                String categoria = request.getParameter("categoria");
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.ordemAlfabetica(categoria);
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("produtos.jsp");
            } /*Filtrando em ordem decrescente os remedios*/ else if (cmd.equalsIgnoreCase("ordemDecrescente")) {
                String categoria = request.getParameter("categoria");
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.ordemDecrescente(categoria);
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("produtos.jsp");
            } /*Filtrando os remedios segundo a data mais recente de lançamento*/ else if (cmd.equalsIgnoreCase("dataLancamento")) {
                String categoria = request.getParameter("categoria");
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.dataLancamento(categoria);
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("produtos.jsp");
            } else if (cmd.equalsIgnoreCase("filtroPreco")) {
                String categoria = request.getParameter("categoria");
                Double precoInformado = Double.parseDouble(request.getParameter("precoInformado"));
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.filtroPreco(categoria, precoInformado);
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("produtos.jsp");

            } else if (cmd.equalsIgnoreCase("filtroPrecoMaior")) {
                String categoria = request.getParameter("categoria");
                Double precoInformado = Double.parseDouble(request.getParameter("precoInformado"));
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.filtroPrecoMaior(categoria, precoInformado);
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("produtos.jsp");

            } else if (cmd.equalsIgnoreCase("filtroPrecoBetween")) {
                String categoria = request.getParameter("categoria");
                Double preco1 = Double.parseDouble(request.getParameter("preco1"));
                Double preco2 = Double.parseDouble(request.getParameter("preco2"));
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.filtroPrecoBetween(categoria, preco1, preco2);
                request.setAttribute("produtoList", produtoList);
                rd = request.getRequestDispatcher("produtos.jsp");
            } else if (cmd.equalsIgnoreCase("adc")) {
                //Puxando a ação do banco de dados de inserir ou cadastrar usuários
                dao.salvar(produto);
                rd = request.getRequestDispatcher("ProdutoServlet?cmd=listar");

                /* O terceiro comando listado é o de excluir. Caso o Servlet receba a query
                 string cmd=exc,  o método excluir(Produto produto)
                 é chamado para eliminar uma linha no banco de dados.
                 Neste caso, o Id do Produto, que é chave primária, é enviado para a chamada exclusão.
                 */
            }  else if (cmd.equalsIgnoreCase("cadastraProduto")) {
                rd = request.getRequestDispatcher("formInserindoProduto.jsp");
            } else if (cmd.equalsIgnoreCase("exc")) {

                dao.excluir(produto);
                rd = request.getRequestDispatcher("ProdutoServlet?cmd=listar");

                /* O quarto comando é utilizado para atualização de dados.
                 Nesta ação a primeira coisa a  fazer é trazer os dados para um formulário,
                 preenchendo-o com os dados atuais e então, após sua submissão,
                 enviá-los para atualização. A situação ocorre da seguinte maneira:
                 A query string cmd=atu será enviada, que será retornada uma session
                 chamada produto,  contendo os dados do produto transmitido pelo método
                 procurarProduto(Integer id).
                 A página formUsproduto.jsp recuperará essa session e preencherá o formulário.
                 */
            } else if (cmd.equalsIgnoreCase("atu")) {
                produto = dao.procurarProduto(Integer.parseInt(request.getParameter("idRemedio")));
                HttpSession session = request.getSession(true);
                session.setAttribute("produto", produto);
                //Redirecionando para o formulário de atualização de campos
                rd = request.getRequestDispatcher("formAtuProduto.jsp");

                /* O formulário de atualização enviará a query string cmd = atualizar
                 transmitido  todos os dados do formulário e chamando o método
                 atualizar(Produto produto).
                 */
            } else if (cmd.equalsIgnoreCase("atualizar")) {
                dao.atualizar(produto);                        
                rd = request.getRequestDispatcher("ProdutoServlet?cmd=listar");

            } /*Atualizando a categoria, porque no select que busca as categoria via BD,
            não tem como digitar uma*/ else if (cmd.equalsIgnoreCase("atualizarCategoria")) {
                int idRemedio = Integer.parseInt(request.getParameter("idRemedio"));
                String categoria = request.getParameter("categoria");
                dao.atualizarCategoria(idRemedio, categoria);
                rd = request.getRequestDispatcher("ProdutoServlet?cmd=listar");
            } else if (cmd.equalsIgnoreCase("cadastraCategoria")) {
                dao.salvarCategoria(produto);
                request.setAttribute("mensagem", "<script>alert('Categoria cadastrada. Altere os dados cadastrados nessa nova categoria ');</script>");
                rd = request.getRequestDispatcher("ProdutoServlet?cmd=listar");
            } else if (cmd.equalsIgnoreCase("principal")) {

                rd = request.getRequestDispatcher("/index.jsp");

            }
            /* O método forward(HttpServeletRequest request, HttpServletResponse response)
             é utilizado para redirecionar a página sempre que solicitada
             */
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
