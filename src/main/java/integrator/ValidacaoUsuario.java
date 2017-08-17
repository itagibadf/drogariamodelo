/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package integrator;

import Model.Conexao;
import controller.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;




/**
 *
 * @author Itagiba
 */
public class ValidacaoUsuario{

      private Connection conn;
    public ValidacaoUsuario() throws Exception{
        try{
            this.conn = Conexao.getConnection();
        }catch(Exception e){
            throw new Exception("Erro: " + ":\n" + e.getMessage());
        }
    }

    public Usuario getUsuario(String login, String senha)throws Exception{
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Informações solicitadas para verificar se o usuário o usuario está cadastrado no banco
         try {
            c = this.conn;
            ps = c.prepareStatement("select idUsuario, nome, sobrenome, login, senha, email, dataNascimento, cidade, cpf, endereco, cep, tipo " +
                                                 " from Farmacia.Usuario " +
                                                 "where login = ? and senha = ? and tipo='1' ");
            ps.setString(1, login);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            if (rs.next()) {
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNome(rs.getString("nome"));
                user.setSobrenome(rs.getString("sobrenome"));
                user.setTipo(rs.getInt("tipo"));
                user.setEmail(rs.getString("email"));
                user.setDataNascimento(rs.getString("dataNascimento"));
                user.setCidade(rs.getString("cidade"));
                user.setCpf(rs.getString("cpf"));
                user.setEndereco(rs.getString("endereco"));
                user.setCep(rs.getString("cep"));

                user.setLogin(login);
                user.setSenha(senha);
                 return user;
        }
            //Fechamento de conexões
         } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {;
                }
                rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {;
                }
                ps = null;
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {;
                }
                c = null;
            }
        }
        return null;

    }
}

    