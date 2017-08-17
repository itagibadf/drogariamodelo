/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package integrator;

import controller.Usuario;
import controller.UsuarioGerente;
import controller.Pedido;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;




/**
 *
 * @author Itagiba
 */
public class ValidaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(); //obtem a sessao do usuario, caso exista

        Usuario user = null;
        String login_form = request.getParameter("login"); // Pega o Login vindo do formulario
        String senha_form = request.getParameter("senha"); //Pega a senha vinda do formulario

       UsuarioGerente usuarioGerente =  null;
        String login_formGerente = request.getParameter("login"); // Pega o Login vindo do formulario
        String senha_formGerente = request.getParameter("senha"); //Pega a senha vinda do formulario
        Pedido pedido = null;
        try {
            ValidacaoUsuarioGerente dao = new ValidacaoUsuarioGerente(); //cria uma instancia do DAO usuario
            usuarioGerente = dao.getUsuarioGerente(login_formGerente, senha_formGerente);


        } catch (Exception e) {

        }
        //Solução de erro do Usuario
        try {
            ValidacaoUsuario dao = new ValidacaoUsuario(); //cria uma instancia do DAO usuario
            user = dao.getUsuario(login_form, senha_form);


        } catch (Exception e) {
        //Criando atributo para que ele puxe as informações dos erros
            session.setAttribute("e",e);
            //Redirecionando o usuário para a página caso haja erro
            request.getRequestDispatcher("/erroBanco.jsp").forward(request, response);
        }


        //se nao encontrou usuario no banco, redireciona para a pagina de erro!
        if ((user  == null)&&(usuarioGerente == null)) {
            session.invalidate(); //invalidando a sessão por erro
            request.getRequestDispatcher("/erroLogin.jsp").forward(request, response);//
        } else if((user != null)&&(usuarioGerente == null)) {
            //se o dao retornar um usuario, coloca o mesmo na sessao
            session.setAttribute("user", user);
            /*Muito importante esse idUsuario  setado na sessão*/
            session.setAttribute("uid",user.getIdUsuario());
            //Redirecionando para a página desejada
           response.sendRedirect("UsuarioServlet");
                    
                    }
            

        else{
            session.setAttribute("gerente",usuarioGerente);
            session.setAttribute("gid", usuarioGerente.getIdUsuario());
            //Redirecionando para a página desejada         
            response.sendRedirect("GerenteServlet");
        }
    }
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}