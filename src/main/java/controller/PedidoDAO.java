/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Itagiba
 */
public class PedidoDAO {

    private Connection conn;

    public PedidoDAO() throws Exception {
        try {
            this.conn = Conexao.getConnection();

        } catch (Exception e) {
            throw new Exception("Erro: "
                    + "\n" + e.getMessage());
        }

    }

    public void salvar(Pedido pedido) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        Produto produto =  new Produto();
        if (pedido == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            String SQL = " INSERT INTO Farmacia.Pedido (quantidadePedido, necessitaEntrega, "
                    + " valorTotal, dataPedido, enderecoEntrega, "
                    + " estado, Remedios_idRemedio, Usuario_idUsuario) "
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setDouble(1, pedido.getQuantidadePedido());
            ps.setString(2, pedido.getNecessitaEntrega());
            ps.setDouble(3, pedido.getValorTotal());
            ps.setString(4, pedido.getDataPedido());
            ps.setString(5, pedido.getEnderecoEntrega());
            ps.setInt(6, pedido.getEstado());
            //Classes associativas
            ps.setInt(7, pedido.getProduto().getIdRemedio());
            ps.setInt(8, pedido.getUsuario().getIdUsuario());
            ps.executeUpdate();
      
        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);

        
    }
    }
//    DAO responsável por evitar que o usuário adicione o mesmo pedido varias vezes

    public boolean PorId(Pedido pedido) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = this.conn;
            ps = conn.prepareStatement("SELECT idPedido, Usuario_idUsuario, Remedios_idRemedio, estado FROM Pedido WHERE Remedios_idRemedio = ? AND Usuario_idUsuario = ? AND estado IN(0,1)");
            ps.setInt(1, pedido.getProduto().getIdRemedio());
            ps.setInt(2, pedido.getUsuario().getIdUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                Produto produto = new Produto();
                produto.setIdRemedio(rs.getInt("Remedios_idRemedio"));

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("Usuario_idUsuario"));

                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setUsuario(usuario);
                pedido.setProduto(produto);
                return true;
            } else {
                return false;
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
    }

    
   
    public List<Pedido> todosPedidosUsuarios() throws Exception {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement("SELECT p.idRemedio as idRemedio, p.nome as nome, p.preco as preco,  p.medida as medidaP, "
                    + "pd.valorTotal as valorTotal, pd.estado as estado, pd.idPedido as idPedido, "
                    + "pd.quantidadePedido as quantidadePedido, pd.dataPedido as dataPedido, pd.Remedios_idRemedio as Remedios_idRemedio,"
                    + "pd.Usuario_idUsuario as Usuario_idUsuario, u.idUsuario as idUsuario, u.nome as nome"
                    + "FROM Pedido pd "
                    + "INNER JOIN Remedios p"
                    + "ON p.idRemedio = pd.Remedios_idRemedio"
                    + "INNER JOIN Usuario u"
                    + "ON u.idUsuario = pd.Usuario_idUsuario"
                    + "WHERE u.tipo= 1");

            rs = ps.executeQuery();
            List<Pedido> list = new ArrayList<Pedido>();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setDataPedido(rs.getString("dataPedido"));
                pedido.setEnderecoEntrega(rs.getString("enderecoEntrega"));
                pedido.setQuantidadePedido(rs.getDouble("quantidadePedido"));
                pedido.setValorTotal(rs.getDouble("valorTotal"));
                pedido.setEstado(rs.getInt("estado"));
                //Classes associativas para realizar a busca dos campos das tabelas
                //Usuario e Produto
                pedido.setUsuario(UsuarioDAO.leChaveP(rs.getInt("Usuario_idUsuario")));
                pedido.setProduto(ProdutoDAO.leChaveP(rs.getInt("Produto_idProduto")));

                //Adicionando os objetos encontrados na Lista
                pedidos.add(pedido);

            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL:" + e.getMessage());
        }
        //Retornando a lista de clientes
        return pedidos;
    }

    
    
    
    public List<Pedido> todosPedidosUsuariosCarrinhoCount(int idUsuario) throws Exception {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement(" Select count(*) from Pedido where Usuario_idUsuario=? ");
            ps.setInt(1,idUsuario);
            rs = ps.executeQuery();
            List<Pedido> list = new ArrayList<Pedido>();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setDataPedido(rs.getString("dataPedido"));
                pedido.setEnderecoEntrega(rs.getString("enderecoEntrega"));
                pedido.setQuantidadePedido(rs.getDouble("quantidadePedido"));
                pedido.setValorTotal(rs.getDouble("valorTotal"));
                pedido.setEstado(rs.getInt("estado"));
                //Classes associativas para realizar a busca dos campos das tabelas
                //Usuario e Produto
                pedido.setUsuario(UsuarioDAO.leChaveP(rs.getInt("Usuario_idUsuario")));
                pedido.setProduto(ProdutoDAO.leChaveP(rs.getInt("Produto_idProduto")));

                //Adicionando os objetos encontrados na Lista
                pedidos.add(pedido);

            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL:" + e.getMessage());
        }
        //Retornando a lista de clientes
        return pedidos;
    }

    
    public List todosPedidosLiberados() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select pd.idPedido as idPedido, pd.dataPedido as dataPedido, pd.necessitaEntrega as necessitaEntrega, pd.enderecoEntrega as enderecoEntrega, pd.quantidadePedido as quantidadePedido, pd.valorTotal as valorTotal, pd.estado as estado, "
                    + "          u.idUsuario as idUsuario, u.nome as nome, u.sobrenome as sobrenome, u.tipo as tipo, "
                    + "          p.idRemedio as idRemedio, p.nome as nome, p.preco as preco, p.quantidadeEstoque as quantidadeEstoque, p.medida as medidaProduto, p.unidadeMedida as unidadeMedida, p.validade as validade, p.categoria as categoria "
                    + "  from Pedido pd "
                    + " inner join Usuario u "
                    + "     on u.idUsuario = pd.Usuario_idUsuario "
                    + " inner join Remedios p "
                    + "     on p.idRemedio = pd.Remedios_idRemedio "
                    + " where pd.estado=3 GROUP BY idUsuario ORDER BY dataPedido ASC  ");

            rs = ps.executeQuery();
            List<Pedido> list = new ArrayList<Pedido>();

            while (rs.next()) {
                Produto produto = new Produto(rs.getInt("idRemedio"),
                        rs.getString("nome"),
                        null,
                        null,
                        null,
                        rs.getDouble("preco"),
                        rs.getDouble("quantidadeEstoque"),
                        rs.getDouble("medidaProduto"),
                        rs.getString("unidadeMedida"),
                        rs.getString("validade"),
                        null,
                        null,
                        null,
                        null,
                        rs.getString("categoria"),
                        null);

                Usuario usuario = new Usuario(rs.getInt("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        rs.getInt("tipo"));

                Pedido pedido = new Pedido(rs.getInt("idPedido"),
                        rs.getDouble("quantidadePedido"),
                        rs.getString("necessitaEntrega"),
                        rs.getDouble("valorTotal"),
                        rs.getString("dataPedido"),
                        rs.getString("enderecoEntrega"),
                        rs.getInt("estado"),
                        produto,
                        usuario);

                list.add(pedido);
            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    
    
    public List listarPedidosPendentes() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement(" SELECT u.idUsuario as idUsuario, u.nome as nome, u.sobrenome as sobrenome, u.tipo as tipo,"
                    + " p.idPedido as idPedido, p.dataPedido as dataPedido, p.necessitaEntrega as necessitaEntrega, p.enderecoEntrega as enderecoEntrega,"
                    + " p.valorTotal as valorTotal, p.estado as estado"
                    + " FROM Pedido p"
                    + " INNER JOIN Usuario u "
                    + " ON u.idUsuario = p.Usuario_idUsuario"
                    + " WHERE p.estado= 1 or p.estado= 2 GROUP by u.idUsuario ORDER by p.dataPedido ASC ");

            rs = ps.executeQuery();
            List<Pedido> list = new ArrayList<Pedido>();

            while (rs.next()) {
                Produto produto = new Produto();

                Usuario usuario = new Usuario(rs.getInt("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        rs.getInt("tipo"));

                Pedido pedido = new Pedido(rs.getInt("idPedido"),
                        null,
                        rs.getString("necessitaEntrega"),
                        rs.getDouble("valorTotal"),
                        rs.getString("dataPedido"),
                        rs.getString("enderecoEntrega"),
                        rs.getInt("estado"),
                        produto,
                        usuario);

                list.add(pedido);
            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    
    
     
     public List listarPedidosNegados() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement(" SELECT u.idUsuario as idUsuario, u.nome as nome, u.sobrenome as sobrenome, u.tipo as tipo,"
                    + " p.idPedido as idPedido, p.dataPedido as dataPedido, p.necessitaEntrega as necessitaEntrega, p.enderecoEntrega as enderecoEntrega,"
                    + " p.valorTotal as valorTotal, p.estado as estado"
                    + " FROM Pedido p"
                    + " INNER JOIN Usuario u "
                    + " ON u.idUsuario = p.Usuario_idUsuario"
                    + " WHERE p.estado= 4 GROUP BY u.idUsuario ORDER BY p.dataPedido ASC");

            rs = ps.executeQuery();
            List<Pedido> list = new ArrayList<Pedido>();

            while (rs.next()) {
                Produto produto = new Produto();

                Usuario usuario = new Usuario(rs.getInt("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        rs.getInt("tipo"));

                Pedido pedido = new Pedido(rs.getInt("idPedido"),
                        null,
                        rs.getString("necessitaEntrega"),
                        rs.getDouble("valorTotal"),
                        rs.getString("dataPedido"),
                        rs.getString("enderecoEntrega"),
                        rs.getInt("estado"),
                        produto,
                        usuario);

                list.add(pedido);
            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

 
    
    public void atualizar(Pedido pedido) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        if (pedido == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            String SQL = " UPDATE Pedido SET   quantidadePedido=?, necessitaEntrega= ?, "
                    + " valorTotal= ?, enderecoEntrega= ?, estado= ?, Remedios_idRemedio= ?,"
                    + "   Usuario_idUsuario = ? where idPedido= ? ";

            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setDouble(1, pedido.getQuantidadePedido());
            ps.setString(2, pedido.getNecessitaEntrega());
            ps.setDouble(3, pedido.getValorTotal());
            ps.setString(4, pedido.getEnderecoEntrega());
            ps.setInt(5, pedido.getEstado());
            ps.setInt(6, pedido.getProduto().getIdRemedio());
            ps.setInt(7, pedido.getUsuario().getIdUsuario());
            ps.setInt(8, pedido.getIdPedido());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);

        }
    }

    public void atualizarTodosEstados(int estado, String necessitaEntrega, String enderecoEntrega, int idUsuario) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
      
        try {
            conn = this.conn;
           ps= conn.prepareStatement("UPDATE Farmacia.Pedido SET estado = ?, necessitaEntrega= ?, enderecoEntrega= ?"
                    + " WHERE Usuario_idUsuario = ?");
                        
            ps.setInt(1, estado);
            ps.setString(2, necessitaEntrega);
            ps.setString(3, enderecoEntrega);
            ps.setInt(4, idUsuario);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

     public void liberarPedidos(int estado, int idUsuario) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
      
        try {
            conn = this.conn;
           ps= conn.prepareStatement("UPDATE Farmacia.Pedido SET estado = ?  WHERE Usuario_idUsuario = ?");
                        
            ps.setInt(1, estado);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }
    
    public void excluir(Pedido pedido) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement(" delete from Pedido where idPedido=? ");
            ps.setInt(1, pedido.getIdPedido());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);

        } finally {
            Conexao.closeConnection(conn, ps);
        }

    }

    public void excluirTodosPedidosID(int idUsuario) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement(" delete from Pedido where Usuario_idUsuario=? ");
            ps.setInt(1, idUsuario);
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);

        } finally {
            Conexao.closeConnection(conn, ps);
        }

    }

//Lista dos produtos  pedidos pelos usuários
    public List pedidoUsuarioID(Integer idUsuario) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement(" SELECT pd.idPedido as idPedido, "
                    + " pd.quantidadePedido as quantidadePedido, "
                    + " pd.necessitaEntrega as necessitaEntrega, "
                    + " pd.valorTotal as valorTotal, pd.dataPedido as dataPedido, "
                    + " pd.enderecoEntrega as enderecoEntrega,  pd.estado as estado, "
                    
                    + " p.idRemedio as idRemedio, p.nome as nomeRemedio, p.descricao as descricao,"
                    + " p.preco as preco, p.quantidadeEstoque as quantidadeEstoque, "
                    + " p.medida as medida, p.unidadeMedida as unidadeMedida, "
                    + " p.validade as validade, p.comprimento as 'comprimento', p.largura as 'largura', "
                    + "p.altura as 'altura', p.peso as 'peso', p.categoria as categoria, "
                    
                    + " u.idUsuario as idUsuario, u.nome as nome, u.email as email, u.sobrenome as sobrenome, "
                    + " u.cidade as cidade, u.endereco as endereco, u.cep as 'cep', u.tipo as tipo "
                    + " FROM Pedido pd "
                    + " INNER JOIN Usuario u "
                    + " ON pd.Usuario_idUsuario = u.idUsuario "
                    + " INNER JOIN Remedios p "
                    + " ON pd.Remedios_idRemedio = p.idRemedio "
                    + " WHERE idUsuario = ?  ");

            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            List<Pedido> list = new ArrayList<Pedido>();

            while (rs.next()) {
                Produto produto = new Produto(rs.getInt("idRemedio"),
                        rs.getString("nomeRemedio"),
                        null,
                        null,
                        null,                        
                        rs.getDouble("preco"),
                        rs.getDouble("quantidadeEstoque"),
                        rs.getDouble("medida"),
                        rs.getString("unidadeMedida"),
                        rs.getString("validade"),
                        rs.getDouble("comprimento"),                        
                        rs.getDouble("largura"),                        
                        rs.getDouble("altura"),                        
                        rs.getDouble("peso"),
                        rs.getString("categoria"),
                        null);

                Usuario usuario = new Usuario(rs.getInt("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        null,
                        null,
                        rs.getString("email"),
                        null,
                        null,
                        rs.getString("cidade"),
                        rs.getString("endereco"),
                        rs.getString("cep"),
                        rs.getInt("tipo"));

                Pedido pedido = new Pedido(rs.getInt("idPedido"),
                        rs.getDouble("quantidadePedido"),
                        rs.getString("necessitaEntrega"),
                        rs.getDouble("valorTotal"),
                        rs.getString("dataPedido"),
                        rs.getString("enderecoEntrega"),
                        rs.getInt("estado"),
                        produto,
                        usuario);

                list.add(pedido);
            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    
    
    
    
   
    
    
    
//DAO responsável por Procurar o Pedido pelo Id para Atualização
    public Pedido procurarPedidoID(Integer idPedido) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Pedido where idPedido = ? ");

            ps.setInt(1, idPedido);
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum"
                        + " registro com o ID: " + idPedido);
            }
            Double quantidadePedido = rs.getDouble(2);
            String necessitaEntrega = rs.getString(3);
            Double valorTotal = rs.getDouble(4);
            String dataPedido = rs.getString(5);
            String enderecoEntrega = rs.getString(6);
            Integer estado = rs.getInt(7);
            Integer idUsuario = rs.getInt(8);
            Integer idRemedio = rs.getInt(9);

            Produto produto = new Produto();
            Usuario usuario = new Usuario();

            ProdutoDAO produtoDAO = new ProdutoDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            produto = produtoDAO.procurarProduto(idRemedio);
            usuario = usuarioDAO.procurarUsuario(idUsuario);

            Pedido pedidos;
            pedidos = new Pedido(idPedido, quantidadePedido, necessitaEntrega, valorTotal, dataPedido, enderecoEntrega, estado, produto, usuario);
            pedidos.setQuantidadePedido(quantidadePedido);
            pedidos.setEnderecoEntrega(enderecoEntrega);

            return pedidos;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
//            Conexao.closeConnection(conn, ps, rs);

        }
    }

    public List<Pedido> procurarPedido(String buscar) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try {
            conn = this.conn;
            ps = conn.prepareStatement("SELECT p.idRemedio as idRemedio, p.preco as preco, p.tipo as tipo, p.marca as marca, p.medida as medidaP, "
                    + "pd.valorTotal as valorTotal, pd.idPedido as idPedido, pd.medida as medidaPD, pd.dataPedido as dataPedido, pd.Produto_idProduto as Produto_idProduto, pd.Usuario_idUsuario as uId"
                    + "FROM produto p "
                    + "INNER JOIN pedido pd"
                    + "ON idProduto = Produto_idProduto"
                    + "WHERE tipo like '%?%' OR marca like '%?%'");

            ps.setString(1, buscar);
            ps.setString(2, buscar);
            rs = ps.executeQuery();
            if (rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + buscar);
            }
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setDataPedido(rs.getString("dataPedido"));
                pedido.setEnderecoEntrega(rs.getString("enderecoEntrega"));
                pedido.setQuantidadePedido(rs.getDouble("quantidadePedido"));
                pedido.setValorTotal(rs.getDouble("valorTotal"));
                //Classes Associativas
                pedido.setUsuario(UsuarioDAO.leChaveP(rs.getInt("Usuario_idUsuario")));
                pedido.setProduto(ProdutoDAO.leChaveP(rs.getInt("Remedios_idRemedio")));

            }

            return pedidos;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);

        }
    }

    public List<Pedido> procurarPedidoPreco(String buscar)
            throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try {
            conn = this.conn;
            ps
                    = conn.prepareStatement("SELECT p.idRemedio as idRemedio, p.preco as preco,"
                            + " p.nome as nome, p.medida as medida, pd.valorTotal as valorTotal, "
                            + "pd.idPedido as idPedido, pd.quantidadePedido as quantidadePedido, pd.dataPedido as dataPedido,"
                            + " pd.Remedios_idRemedio as Remedios_idRemedio, pd.Usuario_idUsuario as uId"
                            + "FROM Remedios p "
                            + "INNER JOIN pedido pd"
                            + "ON idRemedio = Remedios_idRemedio"
                            + "WHERE preco like '%?%'");

            ps.setString(1, buscar);
            ps.setString(2, buscar);
            rs = ps.executeQuery();
            if (rs.next()) {
                throw new Exception("Não foi encontrado nenhum registro com o ID: " + buscar);
            }
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setDataPedido(rs.getString("dataPedido"));
                pedido.setEnderecoEntrega(rs.getString("enderecoEntrega"));
                pedido.setQuantidadePedido(rs.getDouble("quantidadePedido"));
                pedido.setValorTotal(rs.getDouble("valorTotal"));
                //Classes Associativas
                pedido.setUsuario(UsuarioDAO.leChaveP(rs.getInt("Usuario_idUsuario")));
                pedido.setProduto(ProdutoDAO.leChaveP(rs.getInt("Remedios_idRemedio")));

            }

            return pedidos;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);

        }
    }
}
