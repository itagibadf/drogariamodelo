/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrator;

import controller.Pedido;
import controller.PedidoDAO;
import controller.Produto;
import controller.ProdutoDAO;
import controller.Usuario;
import controller.UsuarioDAO;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Itagiba
 */
public class PedidoServlet extends HttpServlet {

//Classe Servlet responpsavel por direcionar as ações de persistencia do Banco de Dados
    private Date dataFormatada(final String dataSt) throws Exception {
        //data  recebida : 26/07/2010 06:05:20

        Date data = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //transformar em 2010/07/26 06:05:20 como no banco de dados

            String dataTemp = dataSt.substring(6, 10) + dataSt.substring(2, 6) + dataSt.substring(0, 2) + dataSt.substring(10, 19);
            
            data = dateFormat.parse(dataTemp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getSession(true);
        request.getAttribute("user");
        /* Esse Servlet criado tem que captar o comando (representado pelos caracteres cmd)
        para que seja decidida qual ação tomar de acordo com a chamada.
         */
        String cmd = request.getParameter("cmd");
        
        HttpSession session = request.getSession(true);
        request.getSession(true);
        Usuario user = (Usuario) (session.getAttribute("user"));
        
        if (cmd == null) {
            cmd = "principal";
        }

        // A classe PedidoDao criada é chamada.
        Pedido pedido = new Pedido();
        PedidoDAO dao;
        Produto produto = new Produto();
        Usuario usuario = new Usuario();
        UsuarioDAO daoU;

        /* Isso sendo feito, pode-se utilizar o PedidoDao para executar a ação desejada
        e  para modificar alguma informação existente no banco de dados
        ou transmitir alguma informação passada pelos formulários que você ainda irá criar.
        De que qualquer forma, os dados passados deverão ser recuperados utilizando
        o request.getParamater(String s). Na condição de não ser nulo o comando e ser diferente
        de principal, os setters são chamados do Usuário e atribuídos a eles valores transmitidos,
        seja via formulário ou não.
         */
        if (cmd != null || !cmd.equalsIgnoreCase("principal")) {
            try {
                String id = request.getParameter("idPedido");
                if (id != null) {
                    pedido.setIdPedido(Integer.parseInt(id));
                }
                
                pedido.setQuantidadePedido(Double.parseDouble(request.getParameter("quantidadePedido")));
                pedido.setNecessitaEntrega(request.getParameter("necessitaEntrega"));
                pedido.setValorTotal(Double.parseDouble(request.getParameter("valorTotal")));
                pedido.setDataPedido(request.getParameter("dataPedido"));
                pedido.setEnderecoEntrega(request.getParameter(("enderecoEntrega")));
                pedido.setEstado(Integer.parseInt(request.getParameter("estado")));
                pedido.setProduto(ProdutoDAO.leChaveP(Integer.parseInt(request.getParameter("idRemedio"))));
                pedido.setUsuario(UsuarioDAO.leChaveP(Integer.parseInt(request.getParameter("idUsuario"))));
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        
        try {
            /*Dentro de um bloco try/catch, o dao é instanciado à classe PedidoDao(),
            tornando-o pronto para uso de métodos.*/
            dao = new PedidoDAO();
            RequestDispatcher rd = null;

            /*O comando inicial do Servlet é o listar. Caso se transmita a variável cmd com o valor
            listar, dispara o bloco if existente em try/catch, chamando o método todosPedidos(),
            responsável por retornar um List contendo todos os dados existentes na tabela pedido.
            Este List é armazenado em um atributo requerido e não uma session, no qual se chamará
            pedidoList.

            Quando um comando é executado com sucesso, uma página é redirecionada.
            No primeiro caso a página mostrarPedido.jsp é chamada pelo Servlet para
            exibir os  dados pedidos.*/
            if (cmd.equalsIgnoreCase("listar")) {
                
                List pedidoList = dao.todosPedidosUsuarios();
                request.setAttribute("pedidoList", pedidoList);
                rd = request.getRequestDispatcher("/mostrarPedidos.jsp");

                //Servlet Responsável por buscar todos Pedidos
            }else if (cmd.equalsIgnoreCase("todosPedidosLiberados")) {
                List pedidoList = dao.todosPedidosLiberados();
                request.setAttribute("pedidoList", pedidoList);
                rd = request.getRequestDispatcher("/mostrarPedidos.jsp");
                
            } else if (cmd.equalsIgnoreCase("adc")) {
                //Instânciando e setando o Calendar para buscar a data e a hora do Servidor
                Calendar c = Calendar.getInstance();
                Date data = c.getTime();
                DateFormat f = DateFormat.getDateInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
                SimpleDateFormat h = new SimpleDateFormat(" HH:mm:s");
                pedido.setDataPedido(df.format(data) + h.format(data));
                //Pegando o Usuário da Session com seus dados cadastrados
                usuario = (Usuario) request.getSession().getAttribute("user");
                pedido.setUsuario(usuario);
                pedido.setEnderecoEntrega(usuario.getEndereco());

                //Buscando as variaveis específicas da classe Produto
                produto.setIdRemedio(Integer.parseInt(request.getParameter("idRemedio")));
                produto.setPreco(Double.parseDouble(request.getParameter("preco")));
                produto.setNome(request.getParameter("nome"));
                pedido.setProduto(produto);
                Double quantidadePedido = Double.parseDouble(request.getParameter("quantidadePedido"));
                
                pedido.setValorTotal(produto.getPreco() * quantidadePedido);
                pedido.setEstado(Integer.parseInt(request.getParameter("estado")));
                //dao.PorId verifica se o produto é verdadeiro ou falso, se existe na lista ou nao 
                if (!dao.PorId(pedido)) {
                    dao.salvar(pedido);
                    rd = request.getRequestDispatcher("PedidoServlet?cmd=listarPedidosUsuario&idUsuario=" + user.getIdUsuario());
                    
                } //Caso a dao.PorId seja um valor falso o produto já existe na lista de Pedidos
                else {
                    request.setAttribute("mensagem", "<script>alert('Este produto já está em sua lista, altere a Quantidade Pedida');</script>");
                    rd = request.getRequestDispatcher("PedidoServlet?cmd=listarPedidosUsuario&idUsuario=" + user.getIdUsuario());
                    
                }
            }
            else if (cmd.equalsIgnoreCase("calculaFrete")) {
                int idRemedio = Integer.parseInt(request.getParameter("idRemedio"));
                Double preco = Double.parseDouble(request.getParameter("preco"));
                int cep = Integer.parseInt(request.getParameter("cep"));
                Double quantidadePedido = Double.parseDouble(request.getParameter("quantidadePedido"));
                Double peso = Double.parseDouble(request.getParameter("peso"));
  
                Double valor = (preco * quantidadePedido);
                /*Setando as variaveis informadas na sessão para que o cálculo seja realizado 
                ao voltar para a página do produto*/
                request.setAttribute("quantidadePedido", quantidadePedido);
                request.setAttribute("cep", cep);
                request.setAttribute("valor", valor);
                request.setAttribute("peso", peso);
//                /*CEP de São Paulo*/
//                if (peso <= 0.300 && cep >= 01000000 && cep <= 19999999) {
//                    /*Se o peso for <= 10 o percentual é: 13.5%*/
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 15.8;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SP 0.10- 10<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 15.8;
//                        /*Preço fixo para objeto de 0-300g */
//                        Double valorFinal = ((valor * 0.5) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SP 0.10- 10 qtd >1<p>");
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 20.70;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SP 11-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 20.70;
//                        Double valorFinal = (valor * 0.3) + frete;
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SP 11-50 Qtd> 1<p>");
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 20.72;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SP >51 <200<p>");
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        double frete = 22.95;
//                        double valorFinal = ((valor * 0.03) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SP >=200 qntd > 1<p>");
//                    }
//                } /*CEP do Rio de Janeiro*/ else if (peso <= 0.300 && cep >= 20000000 && cep <= 28999999) {
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 15.8;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RJ 0.10- 16<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 15.8;
//                        Double valorFinal = ((valor * 0.5) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RJ 0.10- 16 qtd >1<p>");
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 20.70;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RJ 17-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 20.70;
//                        Double valorFinal = ((valor * 0.3) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RJ 18-50<p>");
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 20.72;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RJ >51 <200<p>");
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        Double frete = 22.95;
//                        Double valorFinal = ((valor * 0.04) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RJ >=200 qntd > 1<p>");
//                    }
//
//                } /*CEP Espirito Santo*/ else if (peso <= 0.300 && cep >= 29000000 && cep <= 29999999) {
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 17.2;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)ES 0.10- 16<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 17.2;
//                        Double valorFinal = ((valor * 0.05) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)ES 0.10- 16 qtd >1<p>");
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 22.60;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)ES 17-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 22.60;
//                        Double valorFinal = ((valor * 0.03) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)ES 18-50 qtd >1<p>");
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 22.62;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)ES >51 <200<p>");
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        Double frete = 24.85;
//                        Double valorFinal = ((valor * 0.06) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)ES >=200 qntd > 1<p>");
//                    }
//                } /*CEP Minas Gerais*/ else if (peso <= 0.300 && cep >= 30000000 && cep <= 39999999) {
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 14.8;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)MG 0.10- 16<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 14.8;
//                        Double valorFinal = ((valor * 0.05) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)MG 0.10- 16 qtd >1<p>");
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 19.40;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)MG 17-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 19.40;
//                        Double valorFinal = ((valor * 0.02) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)MG 18-50 qtd >1<p>");
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 19.42;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)MG >51 <200<p>");
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        Double frete = 21.65;
//                        Double valorFinal = ((valor * 0.06) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)MG >=200 qntd > 1<p>");
//                    }
//                } /*CEP Bahia*/ else if (peso <= 0.300 && cep >= 40000000 && cep <= 48999999) {
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 15.8;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)BA 0.10- 16<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 15.8;
//                        Double valorFinal = ((valor * 0.05) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)BA 0.10- 16 qtd >1<p>");
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 20.70;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)BA 17-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 20.70;
//                        Double valorFinal = ((valor * 0.02) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)BA 18-50 qtd >1<p>");
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 20.72;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)BA >51 <200<p>");
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        Double frete = 22.95;
//                        Double valorFinal = ((valor * 0.06) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)BA >=200 qntd > 1<p>");
//                    }
//                } /*CEP Sergipe*/ else if (peso <= 0.300 && cep >= 49000000 && cep <= 49999999) {
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 18.7;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SE 0.10- 16<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 18.7;
//                        Double valorFinal = ((valor * 0.05) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SE 0.10- 16 qtd >1<p>");
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 24.50;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SE 17-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 24.50;
//                        Double valorFinal = ((valor * 0.02) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SE 18-50 qtd >1<p>");
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 24.52;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SE >51 <200<p>");
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        Double frete = 26.75;
//                        Double valorFinal = ((valor * 0.06) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)SE >=200 qntd > 1<p>");
//                    }
//                } /*CEP Pernambuco PE*/ else if (peso <= 0.300 && cep >= 50000000 && cep <= 56999999) {
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 19.4;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PE 0.10- 16<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 19.4;
//                        Double valorFinal = ((valor * 0.05) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PE 0.10- 16 qtd >1<p>");
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 26.70;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PE 17-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 26.70;
//                        Double valorFinal = ((valor * 0.02) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PE 18-50 qtd >1<p>");
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 26.72;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PE >51 <200<p>");
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        Double frete = 28.95;
//                        Double valorFinal = ((valor * 0.06) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PE >=200 qntd > 1<p>");
//                    }
//                } /*CEP Alagoas AL*/ else if (peso <= 0.300 && cep >= 57000000 && cep <= 57999999) {
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 20.4;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)AL 0.10- 16<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 20.4;
//                        Double valorFinal = ((valor * 0.05) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)AL 0.10- 16 qtd >1<p>");
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 26.70;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)AL 17-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 26.70;
//                        Double valorFinal = ((valor * 0.02) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)AL 18-50 qtd >1<p>");
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 26.72;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)AL >51 <200<p>");
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        Double frete = 28.95;
//                        Double valorFinal = ((valor * 0.06) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)AL >=200 qntd > 1<p>");
//                    }
//                } /*CEP Paraíba PB*/ else if (peso <= 0.300 && cep >= 58000000 && cep <= 58999999) {
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 19.4;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PB 0.10- 16<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 19.4;
//                        Double valorFinal = ((valor * 0.05) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PB 0.10- 16 qtd >1<p>");
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 25.40;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PB 17-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 25.40;
//                        Double valorFinal = ((valor * 0.02) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PB 18-50 qtd >1<p>");
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 25.42;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PB >51 <200<p>");
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        Double frete = 27.65;
//                        Double valorFinal = ((valor * 0.06) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)PB >=200 qntd > 1<p>");
//                        session.setAttribute("frete", valorFinal);
//
//                    }
//                } /*CEP  Rio Grande do Norte RN */ else if (peso <= 0.300 && cep >= 59000000 && cep <= 59999999) {
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 20.4;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RN 0.10- 16<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 20.4;
//                        Double valorFinal = ((valor * 0.05) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RN 0.10- 16 qtd >1<p>");
//                        session.setAttribute("frete", valorFinal);
//
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 26.70;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RN 17-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 26.70;
//                        Double valorFinal = ((valor * 0.02) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RN 18-50 qtd >1<p>");
//                        session.setAttribute("frete", valorFinal);
//
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 26.72;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RN >51 <200<p>");
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        Double frete = 28.95;
//                        Double valorFinal = ((valor * 0.06) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)RN >=200 qntd > 1<p>");
//                        session.setAttribute("frete", valorFinal);
//
//                    }
//                } 
//                /*CEP do Distrito Federal*/ 
//                else if (peso <= 0.300 && cep >= 70000000 && cep <= 73699999) {
//                    if (valor >= 0.10 && valor <= 16) {
//                        double frete = 12.3;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)DF 0.10- 16<p>");
//                    }
//                    if (valor >= 0.10 && valor <= 16 && quantidadePedido > 1) {
//                        double frete = 12.3;
//                        Double valorFinal = ((valor * 0.05) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)DF 0.10- 16 qtd >1<p>");
//                        session.setAttribute("frete", valorFinal);
//
//                    }
//                    if (valor >= 17 && valor <= 50) {
//                        double frete = 16.10;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)DF 17-50<p>");
//                    }
//                    if (valor >= 17 && valor <= 50 && quantidadePedido > 1) {
//                        double frete = 16.10;
//                        Double valorFinal = ((valor * 0.02) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)DF 18-50 qtd >1<p>");
//                        session.setAttribute("frete", valorFinal);
//
//                    }
//                    if (valor >= 51 && valor < 200) {
//                        Double frete = 16.12;
//                        request.setAttribute("mensagem", "" + frete);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)DF >51 <200<p>");
//
//                    }
//                    if (valor >= 200 && quantidadePedido > 1) {
//                        Double frete = 16.13;
//                        Double valorFinal = ((valor * 0.4) + frete);
//                        request.setAttribute("mensagem", "" + valorFinal);
//                        request.setAttribute("mensagemPrazo", "(5 Días úteis)DF >=200 qntd > 1<p>");
//                        session.setAttribute("frete", valorFinal);
//
//                    }
//
//                }

                rd = request.getRequestDispatcher("ProdutoServlet?cmd=mostraProduto&idRemedio=" + idRemedio);

            }
            //Verficando se o usuário está nulo
            else if (cmd.equalsIgnoreCase("verificaUsuario")) {
                if(user == null){
                request.setAttribute("mensagemErro", "<script>alert('Efetue login para continuar a operação');</script>");
                rd=  request.getRequestDispatcher("entrar.jsp");
                }
            } else if (cmd.equalsIgnoreCase("exc")) {
                
                dao.excluir(pedido);
                rd = request.getRequestDispatcher("PedidoServlet?cmd=todosPedidos");
                
            } else if (cmd.equalsIgnoreCase("ProcurarTipo")) {
                List pedidoList = dao.procurarPedido(request.getParameter("buscar"));
                request.setAttribute("pedidoList", pedidoList);
//           HttpSession session = request.getSession(true);
                session.setAttribute("pedido", pedido);
                //Redirecionando para o formulário de atualização de campos
                rd = request.getRequestDispatcher("/mostrarPedidos.jsp");
                /* O formulário de atualização enviará a query string cmd = atualizar
                transmitido  todos os dados do formulário e chamando o método
                atualizar(Pedido pedido).
                 */
            } else if (cmd.equalsIgnoreCase("atualizarTipo")) {
                
                dao.atualizar(pedido);
                rd = request.getRequestDispatcher("PedidoServlet?cmd=listar");

                //Página de redirecionamento, após as condições serem satisfeitas.
            } else if (cmd.equalsIgnoreCase("ProcurarPreco")) {
                List pedidoList = dao.procurarPedidoPreco(request.getParameter("buscar"));
                request.setAttribute("pedidoList", pedidoList);
                // HttpSession session = request.getSession(true);
                session.setAttribute("pedido", pedido);
                //Redirecionando para o formulário de atualização de campos
                rd = request.getRequestDispatcher("/mostrarPedidos.jsp");
                
            } else if (cmd.equalsIgnoreCase("atualizarPreco")) {
                
                dao.atualizar(pedido);
                rd = request.getRequestDispatcher("PedidoServlet?cmd=listar");
                
            } else if (cmd.equalsIgnoreCase("ProcurarPedidoID")) {
                //Listando os Usuários para utilizar os campos de sua tabela
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                List usuarioList = usuarioDAO.todosUsuarios();
                request.setAttribute("usuarioList", usuarioList);

                // Lista os Produtos para utilização dos Campos
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.todosProdutos();
                request.setAttribute("produtoList", produtoList);

                //Procurando o Pedido pelo Id para Alteração
                pedido = dao.procurarPedidoID(pedido.getIdPedido());
                pedido.setIdPedido(Integer.parseInt(request.getParameter("idPedido")));
                // HttpSession session = request.getSession(true);
                session.setAttribute("pedido", pedido);
                //Redirecionando para o formulário de atualização de campos
                rd = request.getRequestDispatcher("/formAtuPedido.jsp");
                
            } else if (cmd.equalsIgnoreCase("atualizarPedido")) {
                dao.atualizar(pedido);
//                rd = request.getRequestDispatcher("PedidoServlet?cmd=listar");
                rd = request.getRequestDispatcher("PedidoServlet?cmd=todosPedidos");

                //Página de redirecionamento, após as condições serem satisfeitas.
            } else if (cmd.equalsIgnoreCase("listarPedidosUsuario")) {

                //Criando a Lista para armazenar os campos Buscados pelo DAO
                // Para que o parâmetro dao.pedidoUsuarioID busque os dadosé necessário o idUsuario
                //Valor inteiro que deve ser convertido ao passar para o request que busca valores em String
//              
                List pedidoList = dao.pedidoUsuarioID(Integer.parseInt(request.getParameter("idUsuario")));
                request.setAttribute("pedidoList", pedidoList);
//               HttpSession session = request.getSession();
                session.setAttribute("pedido", pedido);
                rd = request.getRequestDispatcher("mostrarPedidosUsuarios.jsp");
                
            } 
            /*Testando PagSeguro*/
            else if(cmd.equalsIgnoreCase("listarPedidosPag")){
                List pedidoList = dao.pedidoUsuarioID(Integer.parseInt(request.getParameter("idUsuario")));
                request.setAttribute("pedidoList", pedidoList);
//               HttpSession session = request.getSession();
                session.setAttribute("pedido", pedido);
                rd = request.getRequestDispatcher("pagSeguro.jsp");
            }
            else if (cmd.equalsIgnoreCase("listarPedidosPendentes")) {
                List pedidoList = dao.listarPedidosPendentes();
                request.setAttribute("pedidoList", pedidoList);
                
                rd = request.getRequestDispatcher("mostrarPedidosPendentes.jsp");
            } else if (cmd.equalsIgnoreCase("listarPedidosNegados")) {
                List pedidoList = dao.listarPedidosNegados();
                request.setAttribute("pedidoList", pedidoList);
                rd = request.getRequestDispatcher("mostrarPedidosNegados.jsp");
                
            } else if (cmd.equalsIgnoreCase("ProcurarPedidoUsuarioID")) {
                //Listando os Usuários para utilizar os campos de sua tabela
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                List usuarioList = usuarioDAO.todosUsuarios();
                request.setAttribute("usuarioList", usuarioList);

                // Lista os Produtos para utilização dos Campos
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List produtoList = produtoDAO.todosProdutos();
                request.setAttribute("produtoList", produtoList);

                //Procurando o Pedido pelo Id para Alteração
                pedido = dao.procurarPedidoID(pedido.getIdPedido());
                pedido.setIdPedido(Integer.parseInt(request.getParameter("idPedido")));
                //        HttpSession session = request.getSession(true);
                session.setAttribute("pedido", pedido);
                //Redirecionando para o formulário de atualização de campos
                rd = request.getRequestDispatcher("/formAtuPedidoUsuario.jsp");
                
            } else if (cmd.equalsIgnoreCase("atualizarPedidoUsuario")) {
                pedido.setIdPedido(Integer.parseInt(request.getParameter("idPedido")));
                pedido.getDataPedido();
                dao.atualizar(pedido);
                rd = request.getRequestDispatcher("PedidoServlet?cmd=listarPedidosUsuario");
            } else if (cmd.equalsIgnoreCase("atualizarTodosEstados")) {
                //Buscando as variaveis com os valores setados no form
                int estado = (Integer.parseInt(request.getParameter("estado")));
                String necessitaEntrega = (request.getParameter("necessitaEntrega"));
                String enderecoEntrega = (request.getParameter("enderecoEntrega"));
                int idUsuario = (Integer.parseInt(request.getParameter("idUsuario")));
                dao.atualizarTodosEstados(estado, necessitaEntrega, enderecoEntrega, idUsuario);
                rd = request.getRequestDispatcher("PedidoServlet?cmd=ListarPedidosUsuario");
                
            } else if (cmd.equalsIgnoreCase("liberarPedidos")) {
                int estado = Integer.parseInt(request.getParameter("estado"));
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                
                dao.liberarPedidos(estado, idUsuario);
                rd = request.getRequestDispatcher("PedidoServlet?cmd=ListarPedidosUsuario");
            } //Servlet para exclusão do Pedido do Usuário
            else if (cmd.equalsIgnoreCase("excPedidosUsuario")) {
                dao.excluir(pedido);
                rd = request.getRequestDispatcher("MensagemDeletarPedidoUsuario.jsp");
                
            } else if (cmd.equalsIgnoreCase("excTodosPedidosUsuario")) {
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                dao.excluirTodosPedidosID(idUsuario);
                rd = request.getRequestDispatcher("MensagemDeletarPedidoUsuario.jsp");
            } else if (cmd.equalsIgnoreCase("principal")) {

//                rd = request.getRequestDispatcher("/mostrarPedidosUsuario.jsp");
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
