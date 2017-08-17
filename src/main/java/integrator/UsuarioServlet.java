/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrator;

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
public class UsuarioServlet extends HttpServlet {
//Classe Servlet responpsavel por direcionar as ações de persistencia do Banco de Dados

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        /* Esse Servlet criado tem que captar o comando (representado pelos caracteres cmd)
        para que seja decidida qual ação tomar de acordo com a chamada.
         */
        String cmd = request.getParameter("cmd");

        if (cmd==null) {
            cmd = "principal";
        }

        // A classe UsuarioDao criada é chamada.
        Usuario usuario = new Usuario( );
        UsuarioDAO dao;

        
        /* Isso sendo feito, pode-se utilizar o UsuarioDao para executar a ação desejada
        e  para modificar alguma informação existente no banco de dados
        ou transmitir alguma informação passada pelos formulários que você ainda irá criar.
        De que qualquer forma, os dados passados deverão ser recuperados utilizando
        o request.getParamater(String s). Na condição de não ser nulo o comando e ser diferente
        de principal, os setters são chamados do Usuário e atribuídos a eles valores transmitidos,
        seja via formulário ou não.
         */
        if (cmd!= null || !cmd.equalsIgnoreCase("principal")) {
            try {
                String id = request.getParameter("idUsuario");
                if (id!=null) 
                    usuario.setIdUsuario(Integer.parseInt(id));
               
                usuario.setNome(request.getParameter("nome"));
                usuario.setSobrenome(request.getParameter("sobrenome"));
                usuario.setLogin(request.getParameter("login"));
                usuario.setSenha(request.getParameter("senha"));
                usuario.setEmail(request.getParameter("email"));
                usuario.setDataNascimento(request.getParameter("dataNascimento"));
                usuario.setCidade(request.getParameter("cidade"));
                usuario.setCpf(request.getParameter("cpf"));
                usuario.setEndereco(request.getParameter("endereco"));
                 usuario.setCep(request.getParameter("cep"));
                   usuario.setTipo(Integer.parseInt(request.getParameter("tipo")));
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        try {
            /*Dentro de um bloco try/catch, o dao é instanciado à classe UsuarioDao(),
            tornando-o pronto para uso de métodos.*/
            dao = new UsuarioDAO();
            RequestDispatcher rd = null;

            /*O comando inicial do Servlet é o listar. Caso se transmita a variável cmd com o valor
            listar, dispara o bloco if existente em try/catch, chamando o método todosUsuarios(),
            responsável por retornar um List contendo todos os dados existentes na tabela usuario.
            Este List é armazenado em um atributo requerido e não uma session, no qual se chamará
            usuarioList.

            Quando um comando é executado com sucesso, uma página é redirecionada.
            No primeiro caso a página mostrarUsuario.jsp é chamada pelo Servlet para
            exibir os  dados pedidos.*/

            if (cmd.equalsIgnoreCase("listar")) {

                List usuarioList = dao.todosUsuarios();
                request.setAttribute("usuarioList", usuarioList);
                rd = request.getRequestDispatcher("/mostrarUsuario.jsp");

           


                /*O segundo comando listado é o de adicionar dados.
                Nesse caso os dados serão transmitidos para serem inseridos no banco de dados.
                Os setters criado anteriormente serão os responsáveis por capturar os dados vindos
                do formulário. Ao transmitir o formulário, esse, além de chamar o Servlet,
                deverá enviar a query string cmd=adc.

                Ao verificar que é para adicionar os dados, o Servlet chamará o método
                salvar(Usuario  usuario), que transmitirá os dados enviados por esse formulário.
                E após a chamada desse método, uma página é redirecionada para mostrar os dados
                pela inserção.
                 */
            }
            
          
            else if (cmd.equalsIgnoreCase("adc")) {
                //Puxando a ação do banco de dados de inserir ou cadastrar usuários
                dao.salvar(usuario);
                rd = request.getRequestDispatcher("UsuarioServlet?cmd=listar");
              }

            //Adicionando o usuário ao banco de dados e fazendo o redirecionamento para a página principal,
            //sem que ele veja a tabela de sua alteração.
            else if (cmd.equalsIgnoreCase("adc2")) {
                //Puxando a ação do banco de dados de inserir ou cadastrar usuários
                dao.salvar(usuario);
                request.setAttribute("mensagem", "<script>alert('Você foi cadastrado com sucesso, use o nome de login e senha para acessar!');</script>");
                rd = request.getRequestDispatcher("entrar.jsp");

                /* O terceiro comando listado é o de excluir. Caso o Servlet receba a query
                string cmd=exc,  o método excluir(Usuario usuario)
                é chamado para eliminar uma linha no banco de dados.
                Neste caso, o Id do Usuario, que é chave primária, é enviado para a chamada exclusão.
                 */
            } 
            
            //Excluindo o Usuario do Banco
               else if (cmd.equalsIgnoreCase("exc")) {
                    HttpSession session = request.getSession(true);
//                    Buscando o id do Gerente da sessao para verificar se as senhas sao compativeis
                if (dao.verificaSenha(((Integer) session.getAttribute("gid")), request.getParameter("senhaAdmin"))) {
                    dao.excluir(usuario);
                    rd = request.getRequestDispatcher("UsuarioServlet?cmd=listar");
                    request.setAttribute("mensagem", "O usuário foi excluído!");
                } else {
                    rd = request.getRequestDispatcher("UsuarioServlet?cmd=listar");
                    request.setAttribute("mensagem", "<script>alert('Senha incorreta!');</script>");
                }

                //Comando dao.procurarUsuario faz uma busca no banco para encontrar os dados do usuário
                //Para poder atualizar os campos dos usuários
            }

               
            else if (cmd.equalsIgnoreCase("atu")) {

                usuario = dao.procurarUsuario(usuario.getIdUsuario());
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario);
                //Redirecionando para o formulário de atualização de campos
                rd = request.getRequestDispatcher("/formAtuUsuario.jsp");

                //Realiza as alterações feitas nos campos
            }

            else if (cmd.equalsIgnoreCase("atualizar")) {

                dao.atualizar(usuario);
                rd = request.getRequestDispatcher("UsuarioServlet?cmd=listar");

             //Página de redirecionamento, após as condições serem satisfeitas.
            }
            else if(cmd.equalsIgnoreCase("atualizarCep")){
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                String cep = request.getParameter("cep");
                dao.atualizarCep(idUsuario, cep);
                rd= request.getRequestDispatcher("PedidoServlet?cmd=listarPedidosUsuario&idUsuario="+idUsuario);
                
            }
            else if (cmd.equalsIgnoreCase("procuraUsuarioPerfil")) {

                usuario = dao.procurarUsuario(usuario.getIdUsuario());
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario);
                //Redirecionando para o formulário de atualização de campos
                rd = request.getRequestDispatcher("/formAtuUsuario.jsp");

                //Realiza as alterações feitas nos campos
            }
            else if (cmd.equalsIgnoreCase("atualizarPerfilUsuario")) {
                dao.atualizar(usuario);
                request.setAttribute("usuario",usuario);
                request.setAttribute("mensagem","<script>alert('Perfil atualizado');</script>");
                rd = request.getRequestDispatcher("UsuarioServlet?cmd=atu");

             //Página de redirecionamento, após as condições serem satisfeitas.
            }

            else if (cmd.equalsIgnoreCase("principal")) {

                rd = request.getRequestDispatcher("/logadoUsuario.jsp");

                  }

                
            //Fazendo a listagem de Gerentes
            else if(cmd.equalsIgnoreCase("listarGerente")) {

                List usuarioList = dao.todosGerentes();
                request.setAttribute("usuarioList", usuarioList);
                rd = request.getRequestDispatcher("/mostrarGerentes.jsp");

            }
            //Adicionando o Gerente ao Banco
            else if (cmd.equalsIgnoreCase("adcGerente")) {
                //Puxando a ação do banco de dados de inserir ou cadastrar usuários
                dao.salvarGerente(usuario);
                rd = request.getRequestDispatcher("UsuarioServlet?cmd=listarGerente");
             }

            //Comando que faz uma busca no banco nos campos do UsuarioGerente
            else if (cmd.equalsIgnoreCase("atuGerente")) {

                usuario = dao.procurarGerente(usuario.getIdUsuario());
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario);
                //Redirecionando para o formulário de atualização de campos
                rd = request.getRequestDispatcher("/formAtuGerente.jsp");

                /* O formulário de atualização enviará a query string cmd = atualizar
                transmitido  todos os dados do formulário e chamando o método
                atualizar(Usuario usuario).
                 */
            }
            //Comando que atualiza os campos que foram modificados

            else if (cmd.equalsIgnoreCase("atualizarGerente")) {

                dao.atualizarGerente(usuario);
                rd = request.getRequestDispatcher("UsuarioServlet?cmd=listarGerente");
            }

            //Redirecionamento, após o atu e o atualizar forem satisfeitos corretamente.
            else if (cmd.equalsIgnoreCase("principal")) {

                rd = request.getRequestDispatcher("/logadoGerente.jsp");

                  }

            //Excluindo um  gerente cadastrado no Banco
            else if (cmd.equalsIgnoreCase("excGerente")) {

                dao.excluirGerente(usuario);
                rd = request.getRequestDispatcher("UsuarioServlet?cmd=listarGerente");

          //Comando dao.procurarUsuario faz uma busca no banco para encontrar os dados do usuário
          //Para poder atualizar os campos dos usuários
            }
            else if(cmd.equalsIgnoreCase("sair")){
                HttpSession session=  request.getSession(true);
                session.setAttribute("idUsuario", usuario);
                session.invalidate();
                rd = request.getRequestDispatcher("/entrar.jsp");

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
