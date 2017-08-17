/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package integrator;

import Model.Conexao;
import controller.UsuarioGerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;




/**
 *
 * @author Itagiba
 */
public class ValidacaoUsuarioGerente{

      private Connection conn;
    public ValidacaoUsuarioGerente() throws Exception{
        try{
            this.conn = Conexao.getConnection();
        }catch(Exception e){
            throw new Exception("Erro: " + ":\n" + e.getMessage());
        }
    }

    public UsuarioGerente getUsuarioGerente(String login, String senha)throws Exception{
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Informações solicitadas para verificar se o usuário o gerente está cadastrado no banco
         try {
            c = this.conn;
            //Select para fazer uma busca no usuário Administrador que é do tipo='2'
            ps = c.prepareStatement("select idUsuario, nome,sobrenome,login,senha, email, dataNascimento, cidade,cpf,endereco,cep,tipo from Farmacia.Usuario where login = ? and senha = ? and tipo='2' ");
            ps.setString(1,login);
            ps.setString(2, senha);
            rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioGerente gerente = new UsuarioGerente();
                gerente.setIdUsuario(rs.getInt("idUsuario"));
                gerente.setNome(rs.getString("nome"));
                gerente.setSobrenome(rs.getString("sobrenome"));
                gerente.setLogin(login);
                gerente.setSenha(senha);
                gerente.setNome(rs.getString("nome"));
                return gerente;
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
