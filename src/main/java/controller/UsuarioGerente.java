
package controller;

/**
 *
 * @author itagiba
 */
public class UsuarioGerente {
private Integer idUsuario;
//Atributos ou campos do banco de dados
    private String nome;
    private String sobrenome;
    private String login;
    private String senha;
    private String email;
    private String dataNascimento;
    private String cidade;
    private String cpf;
    private String endereco;
    private String cep;
    private String  tipo;



    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
      public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    //Construtor vazio para construir informações
    public UsuarioGerente(){
            }
public UsuarioGerente(Integer idUsuario,String nome,String sobrenome, String login, String senha, String email, String dataNascimento,
        String cidade, String cpf, String endereco,String cep,String tipo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.senha = senha;
        this.email= email;
        this.dataNascimento= dataNascimento;
        this.cidade= cidade;
        this.cpf= cpf;
        this.endereco= endereco;
        this.cep= cep;
        this.tipo=tipo;

}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}

