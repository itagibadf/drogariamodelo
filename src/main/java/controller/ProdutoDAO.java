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
public class ProdutoDAO {

    private Connection conn;

    public ProdutoDAO() throws Exception {
        try {
            this.conn = Conexao.getConnection();

        } catch (Exception e) {
            throw new Exception("Erro: "
                    + "\n" + e.getMessage());
        }
    }

    public void salvar(Produto produto) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        if (produto == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            String SQL = "INSERT INTO Farmacia.Remedios (nome, descricao, descricaoCompleta, precoAntigo,"
                    + " preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura,"
                    + " altura, peso, categoria, promocao) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setString(3, produto.getDescricaoCompleta());
            ps.setDouble(4, produto.getPrecoAntigo());
            ps.setDouble(5, produto.getPreco());
            ps.setDouble(6, produto.getQuantidadeEstoque());
            ps.setDouble(7, produto.getMedida());
            ps.setString(8, produto.getUnidadeMedida());
            ps.setString(9, produto.getValidade());
            ps.setDouble(10, produto.getComprimento());
            ps.setDouble(11, produto.getLargura());
            ps.setDouble(12, produto.getAltura());
            ps.setDouble(13, produto.getPeso());
            ps.setString(14, produto.getCategoria());
            ps.setString(15, produto.getPromocao());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);

        }
    }

    public void salvarCategoria(Produto produto) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        if (produto == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            String SQL = "INSERT INTO Farmacia.Remedios (nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setString(3, produto.getDescricaoCompleta());
            ps.setDouble(4, produto.getPrecoAntigo());
            ps.setDouble(5, produto.getPreco());
            ps.setDouble(6, produto.getQuantidadeEstoque());
            ps.setDouble(7, produto.getMedida());
            ps.setString(8, produto.getUnidadeMedida());
            ps.setString(9, produto.getValidade());
            ps.setDouble(10, produto.getComprimento());
            ps.setDouble(11, produto.getLargura());
            ps.setDouble(12, produto.getAltura());
            ps.setDouble(13, produto.getPeso());
            ps.setString(14, produto.getCategoria());
            ps.setString(15, produto.getPromocao());

            ps.executeUpdate();

        } catch (SQLException sqle) {

        } finally {
            Conexao.closeConnection(conn, ps);

        }
    }

    public void atualizar(Produto produto) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        if (produto == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            String SQL = "UPDATE Farmacia.Remedios SET nome=?, descricao=?, descricaoCompleta=?, precoAntigo=?, preco=?,  quantidadeEstoque=?, medida=?, unidadeMedida=?, validade= ?,"
                    + "comprimento=?, largura=?, altura=?, peso=?, categoria = ?, promocao = ?"
                    + "where idRemedio=?";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setString(3, produto.getDescricaoCompleta());
            ps.setDouble(4, produto.getPrecoAntigo());
            ps.setDouble(5, produto.getPreco());
            ps.setDouble(6, produto.getQuantidadeEstoque());
            ps.setDouble(7, produto.getMedida());
            ps.setString(8, produto.getUnidadeMedida());
            ps.setString(9, produto.getValidade());
            ps.setDouble(10, produto.getComprimento());
            ps.setDouble(11, produto.getLargura());
            ps.setDouble(12, produto.getAltura());
            ps.setDouble(13, produto.getPeso());
            ps.setString(14, produto.getCategoria());
            ps.setString(15, produto.getPromocao());
            ps.setInt(16, produto.getIdRemedio());

            ps.executeUpdate();

        } catch (SQLException sqle) {

        } finally {
            Conexao.closeConnection(conn, ps);

        }
    }

    public void atualizarCategoria(int idRemedio, String categoria) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String SQL = "UPDATE Remedios SET categoria = ?"
                    + " where idRemedio=? ";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, categoria);
            ps.setInt(2, idRemedio);

            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);

        }
    }

    public void excluir(Produto produto) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        if (produto == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            conn = this.conn;
            ps
                    = conn.prepareStatement(" delete from Remedios where idRemedio=? ");
            ps.setInt(1, produto.getIdRemedio());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);

        } finally {
            Conexao.closeConnection(conn, ps);
        }

    }

    public Produto leUm(Integer id) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement(" Select * from Remedios where idRemedio= ? ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            //Não se esqueça dessa exclamação antes do método rs.next
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum"
                        + " registro com o ID: " + id);
            }

            String nome = rs.getString(2);
            String descricao = rs.getString(3);
            String descricaoCompleta = rs.getString(4);
            Double precoAntigo = rs.getDouble(5);
            Double preco = rs.getDouble(6);
            Double quantidadeEstoque = rs.getDouble(7);
            Double medida = rs.getDouble(8);
            String unidadeMedida = rs.getString(9);
            String validade = rs.getString(10);
            Double comprimento = rs.getDouble(11);
            Double largura = rs.getDouble(12);
            Double altura = rs.getDouble(13);
            Double peso = rs.getDouble(14);
            String categoria = rs.getString(15);
            String promocao = rs.getString(16);
            return new Produto(id, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao);

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);

        }
    }

    public List leUmCategoria(String categoria) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where categoria like ? ");
            ps.setString(1, '%' + categoria + '%');
            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String promocao = rs.getString(15);
                list.add(new Produto(idRemedio, nome, descricao,descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List buscaProduto(String nomeBusca) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where nome like ? ");
            ps.setString(1, "%" + nomeBusca + "%");
            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String categoria = rs.getString(15);
                String promocao = rs.getString(16);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    
    public List buscaProdutoLista(String nomeBusca) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where nome like ? ");
            ps.setString(1, "%" + nomeBusca + "%");
            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String categoria = rs.getString(15);
                String promocao = rs.getString(16);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    
    
//DAO responsvel por puxar os campos dos Produtos e ser instânciada no Servlet Pedido
    public static Produto leChaveP(int idRemedio) throws Exception {
        Produto produto = null;
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT idRemedio, nome, preco, quantidadeEstoque, medida, unidadeMedida, validade,comprimento, largura, altura, peso, categoria, promocao"
                    + " from Remedios where idRemedio = ?");
            pstm.setInt(1, idRemedio);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                produto = new Produto();

                produto.setIdRemedio(rs.getInt("idRemedio"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidadeEstoque(rs.getDouble("quantidadeEstoque"));
                produto.setMedida(rs.getDouble("medida"));
                produto.setUnidadeMedida(rs.getString("unidadeMedida"));
                produto.setValidade(rs.getString("validade"));
                produto.setComprimento(rs.getDouble("comprimento"));
                produto.setLargura(rs.getDouble("largura"));
                produto.setAltura(rs.getDouble("altura"));
                produto.setPeso(rs.getDouble("peso"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setPromocao(rs.getString("promocao"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return produto;
    }

    public List menorPreco(String categoria) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where categoria =? order by preco ASC ");
            ps.setString(1, categoria);
            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String promocao = rs.getString(15);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List maiorPreco(String categoria) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where categoria=? order by preco DESC ");
            ps.setString(1, categoria);
            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String promocao = rs.getString(15);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List ordemAlfabetica(String categoria) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where categoria= ? order by nome ASC ");
            ps.setString(1, categoria);
            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
               Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String promocao = rs.getString(15);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List ordemDecrescente(String categoria) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where categoria= ? order by nome DESC");
            ps.setString(1, categoria);
            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
              Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String promocao = rs.getString(15);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List dataLancamento(String categoria) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where categoria=? order by validade DESC");
            ps.setString(1, categoria);
            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String promocao = rs.getString(15);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List filtroPreco(String categoria, Double precoInformado) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where categoria=? and preco <= ? ");
            ps.setString(1, categoria);
            ps.setDouble(2, precoInformado);
            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String promocao = rs.getString(15);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List filtroPrecoMaior(String categoria, Double precoInformado) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where categoria=? and preco > ? ");
            ps.setString(1, categoria);
            ps.setDouble(2, precoInformado);
            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String promocao = rs.getString(15);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List filtroPrecoBetween(String categoria, Double preco1, Double preco2) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> list;
        try {
            conn = this.conn;
            ps = conn.prepareStatement(" select * from Remedios where categoria=? and preco between ? and ? ");
            ps.setString(1, categoria);
            ps.setDouble(2, preco1);
            ps.setDouble(3, preco2);

            rs = ps.executeQuery();
            list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String promocao = rs.getString(15);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List todosProdutos() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement("select * from Remedios order by nome ASC");
            rs = ps.executeQuery();
            List<Produto> list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String categoria = rs.getString(15);
                String promocao = rs.getString(16);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List listarPromocoes() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement("select * from Remedios where promocao= 'Sim' and categoria not in('ofertas-semanais') ");
            rs = ps.executeQuery();
            List<Produto> list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String categoria = rs.getString(15);
                String promocao = rs.getString(16);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public List listarOfertasSemanais() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement("select * from Remedios where categoria='ofertas-semanais'");
            rs = ps.executeQuery();
            List<Produto> list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                String descricaoCompleta = rs.getString(4);
                Double precoAntigo = rs.getDouble(5);
                Double preco = rs.getDouble(6);
                Double quantidadeEstoque = rs.getDouble(7);
                Double medida = rs.getDouble(8);
                String unidadeMedida = rs.getString(9);
                String validade = rs.getString(10);
                Double comprimento = rs.getDouble(11);
                Double largura = rs.getDouble(12);
                Double altura = rs.getDouble(13);
                Double peso = rs.getDouble(14);
                String categoria = rs.getString(15);
                String promocao = rs.getString(16);
                list.add(new Produto(idRemedio, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade, comprimento, largura, altura, peso, categoria, promocao));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    /*Listando as categorias existentes no BD*/
    public List listarCategorias() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement("select idRemedio,nome,categoria from Remedios order by nome  ASC");
            rs = ps.executeQuery();
            List<Produto> list = new ArrayList<Produto>();
            while (rs.next()) {
                Integer idRemedio = rs.getInt(1);
                String nome = rs.getString(2);
                String categoria = rs.getString(3);
                list.add(new Produto(idRemedio, nome, null, null, null, null,null, null, null, null, null, null, null, null, categoria, null));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    /*Listando as categorias existentes no BD*/
    public List listarCategoriasForm() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement("select idRemedio, categoria from Remedios group by categoria order by categoria ASC ");
            rs = ps.executeQuery();
            List<Produto> list = new ArrayList<Produto>();
            while (rs.next()) {
                int idRemedio = rs.getInt(1);
                String categoria = rs.getString(2);
                list.add(new Produto(idRemedio, null, null, null,null, null, null, null, null, null, null, null, null, null, categoria, null));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public Produto procurarProduto(Integer id)
            throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps
                    = conn.prepareStatement("select * from Remedios where idRemedio=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum"
                        + " registro com o ID: " + id);
            }

            String nome = rs.getString(2);
            String descricao = rs.getString(3);
            String descricaoCompleta =rs.getString(4);
            Double precoAntigo = rs.getDouble(5);
            Double preco = rs.getDouble(6);
            Double quantidadeEstoque = rs.getDouble(7);
            Double medida = rs.getDouble(8);
            String unidadeMedida = rs.getString(9);
            String validade = rs.getString(10);
            Double comprimento = rs.getDouble(11);
            Double largura = rs.getDouble(12);
            Double altura = rs.getDouble(13);
            Double peso = rs.getDouble(14);
            String categoria = rs.getString(15);
            String promocao = rs.getString(16);
            return new Produto(id, nome, descricao, descricaoCompleta, precoAntigo, preco, quantidadeEstoque, medida, unidadeMedida, validade,comprimento, largura, altura, peso, categoria, promocao);

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);

        }
    }

}
