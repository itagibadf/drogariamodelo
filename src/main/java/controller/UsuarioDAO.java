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
public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() throws Exception {
        try {
            this.conn = Conexao.getConnection();

        } catch (Exception e) {
            throw new Exception("Erro: "
                    + "\n" + e.getMessage());
        }
    }

    //Como fazer para inserir um usuário do tipo= ''1' pelo comando sql, para que a cada cadastro que o usuário realizar
    //ele se cadastre como tipo='1'.
    public void salvar(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        if (usuario == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            String SQL = "INSERT INTO Usuario (nome, sobrenome, login, senha, email, dataNascimento, cidade, cpf,  endereco, cep, tipo) "
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setString(3, usuario.getLogin());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getDataNascimento());
            ps.setString(7, usuario.getCidade());
            ps.setString(8, usuario.getCpf());
            ps.setString(9, usuario.getEndereco());
            ps.setString(10, usuario.getCep());
            ps.setInt(11, usuario.getTipo());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);

        }
    }

    public void atualizar(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        if (usuario == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            String SQL = " UPDATE Usuario SET nome=?, sobrenome=?, login=?, senha=?, email=?, dataNascimento=?, cidade=?, cpf=?, "
                    + " endereco=?, cep=? "
                    + " where idUsuario=? and tipo='1' ";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setString(3, usuario.getLogin());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getDataNascimento());
            ps.setString(7, usuario.getCidade());
            ps.setString(8, usuario.getCpf());
            ps.setString(9, usuario.getEndereco());
            ps.setString(10, usuario.getCep());
            ps.setInt(11, usuario.getIdUsuario());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);

        }
    }

    public void atualizarCep(int idUsuario, String cep) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        if (cep == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            String SQL = " UPDATE Usuario SET  cep=? where idUsuario=? and tipo='1' ";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, cep);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);

        }
    }

    public void excluir(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        if (usuario == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            conn = this.conn;
            ps
                    = conn.prepareStatement("delete from Usuario where idUsuario=? and tipo='1' ");
            ps.setInt(1, usuario.getIdUsuario());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);

        } finally {
            Conexao.closeConnection(conn, ps);
        }

    }

    public Usuario leUm(int us) throws Exception {
        Usuario usuario = new Usuario();
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT idUsuario, nome ,sobrenome ,"
                    + "login , senha ,email , dataNascimento , cidade ,cpf , endereco, cep, tipo  FROM Usuario where tipo =1");
            pstm.setInt(1, us);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();

                int idUsuario = rs.getInt("idUsuario");
                usuario.setIdUsuario(idUsuario);
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDataNascimento(rs.getString("dataNascimento"));
                usuario.setCidade(rs.getString("cidade"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setCep(rs.getString("cep"));
                usuario.setTipo(rs.getInt("tipo"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return usuario;
    }
//DAO para ler somente a chave primária da tabela Usuário

    public static Usuario leChaveP(int id) throws Exception {
        Usuario usuario = null;
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pstm = conn.prepareStatement(" SELECT idUsuario, nome, sobrenome, endereco, tipo from Usuario where idUsuario = ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                int idUsuario = rs.getInt("idUsuario");
                usuario.setIdUsuario(idUsuario);
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setTipo(rs.getInt("tipo"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return usuario;
    }
//Verifica os dados do usuário Gerente da sessão no banco para deletar o usuário

    public boolean verificaSenha(int idUsuario, String senha) throws Exception {
        ResultSet rs = null;
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT idUsuario, senha FROM Usuario WHERE idUsuario = ? AND senha = ?");
            pstm.setInt(1, idUsuario);
            pstm.setString(2, senha);
            rs = pstm.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
    }

    public List todosUsuarios() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement("select * from Usuario where tipo='1' ");
            rs = ps.executeQuery();
            List<Usuario> list = new ArrayList<Usuario>();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String nome = rs.getString(2);
                String sobrenome = rs.getString(3);
                String login = rs.getString(4);
                String senha = rs.getString(5);
                String email = rs.getString(6);
                String dataNascimento = rs.getString(7);
                String cidade = rs.getString(8);
                String cpf = rs.getString(9);
                String endereco = rs.getString(10);
                String cep = rs.getString(11);
                Integer tipo = rs.getInt(12);
                list.add(new Usuario(id, nome, sobrenome, login, senha, email, dataNascimento, cidade, cpf, endereco, cep, tipo));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public Usuario procurarUsuario(Integer id)
            throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps
                    = conn.prepareStatement(" select idUsuario, nome, sobrenome, login, senha, email, dataNascimento,  cpf,cidade, endereco, cep, tipo "
                            + " from Usuario where idUsuario=? and tipo='1' ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum"
                        + " registro com o ID: " + id);
            }

            String nome = rs.getString(2);
            String sobrenome = rs.getString(3);
            String login = rs.getString(4);
            String senha = rs.getString(5);
            String email = rs.getString(6);
            String dataNascimento = rs.getString(7);
            String cpf = rs.getString(8);
            String cidade = rs.getString(9);
            String endereco = rs.getString(10);
            String cep = rs.getString(11);
            Integer tipo = rs.getInt(12);
            return new Usuario(id, nome, sobrenome, login, senha, email, dataNascimento, cpf, cidade, endereco, cep, tipo);

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);

        }
    }

//Criando o SQL que faz a inserção na tabela Usuario
    public void salvarGerente(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        if (usuario == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            String SQL = "INSERT INTO Farmacia.Usuario (nome, sobrenome, login, senha, email,"
                    + " dataNascimento, cidade, cpf,  endereco,cep, tipo) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setString(3, usuario.getLogin());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getDataNascimento());
            ps.setString(7, usuario.getCidade());
            ps.setString(8, usuario.getCpf());
            ps.setString(9, usuario.getEndereco());
            ps.setString(10, usuario.getCep());
            ps.setInt(11, usuario.getTipo());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);

        }
    }

    public List todosGerentes() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = this.conn;
            ps = conn.prepareStatement("select * from Usuario where  tipo='2' ");
            rs = ps.executeQuery();
            List<Usuario> list = new ArrayList<Usuario>();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String nome = rs.getString(2);
                String sobrenome = rs.getString(3);
                String login = rs.getString(4);
                String senha = rs.getString(5);
                String email = rs.getString(6);
                String dataNascimento = rs.getString(7);
                String cidade = rs.getString(8);
                String cpf = rs.getString(9);
                String endereco = rs.getString(10);
                String cep = rs.getString(11);
                Integer tipo = rs.getInt(12);
                list.add(new Usuario(id, nome, sobrenome, login, senha, email, dataNascimento, cidade, cpf, endereco, cep, tipo));

            }
            return list;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
    }

    public static Usuario leUmGerente(int us) throws Exception {
        Usuario usuario = new Usuario();
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT c.idUsuario as 'Codigo' , c.nome ,c.sobrenome , "
                    + "c.login , c.senha , c.email , c.dataNascimento , c.cidade ,c.cpf , c.endereco, c.cep, c.tipo  as 'Nome'P FROM Usuario ORDER BY c.nome ASC'");
            pstm.setInt(1, us);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();

                int codigo = rs.getInt("Codigo");
                usuario.setIdUsuario(codigo);
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDataNascimento(rs.getString("dataNascimento"));
                usuario.setCidade(rs.getString("cidade"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setCep(rs.getString("cep"));
                usuario.setTipo(rs.getInt("tipo"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return usuario;
    }

    public void excluirGerente(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        if (usuario == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            conn = this.conn;
            ps
                    = conn.prepareStatement("delete from Usuario where idUsuario=? and tipo='2' ");
            ps.setInt(1, usuario.getIdUsuario());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados:" + sqle);

        } finally {
            Conexao.closeConnection(conn, ps);
        }

    }

    public void atualizarGerente(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;

        if (usuario == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }

        try {
            String SQL = " UPDATE Usuario SET nome=?, sobrenome=?, login=?, senha=?, email=?, dataNascimento=?, cidade=?, cpf=?,"
                    + " endereco=?, cep=? "
                    + "where idUsuario=? and  tipo='2' ";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setString(3, usuario.getLogin());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getDataNascimento());
            ps.setString(7, usuario.getCidade());
            ps.setString(8, usuario.getCpf());
            ps.setString(9, usuario.getEndereco());
            ps.setString(10, usuario.getCep());
            ps.setInt(11, usuario.getIdUsuario());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            Conexao.closeConnection(conn, ps);

        }
    }

    public Usuario procurarGerente(int id)
            throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = this.conn;
            ps
                    = conn.prepareStatement(" select idUsuario, nome,sobrenome, login, senha,"
                            + "email, dataNascimento, cpf, cidade,endereco, cep, tipo"
                            + " from Usuario where idUsuario=? and tipo='2' ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new Exception("Não foi encontrado nenhum"
                        + " registro com o ID: " + id);
            }

            String nome = rs.getString(2);
            String sobrenome = rs.getString(3);
            String login = rs.getString(4);
            String senha = rs.getString(5);
            String email = rs.getString(6);
            String dataNascimento = rs.getString(7);
            String cpf = rs.getString(8);
            String cidade = rs.getString(9);
            String endereco = rs.getString(10);
            String cep = rs.getString(11);
            Integer tipo = rs.getInt(12);
            return new Usuario(id, nome, sobrenome, login, senha, email, dataNascimento, cpf, cidade, endereco, cep, tipo);

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            Conexao.closeConnection(conn, ps, rs);

        }
    }

}
