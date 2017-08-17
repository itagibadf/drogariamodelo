
package controller;

/**
 *
 * @author Itagiba
 */
public class Usuario extends Object {
   private Integer idUsuario;
//Atributos ou campos do banco de dados
    private String nome;
    private String sobrenome;
    private String login;
    private String senha;
    private String email;
    private String dataNascimento;
    private String cpf;
     private String cidade;   
    private String endereco;
    private String cep;
    private int  tipo;



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
public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    //Construtor vazio para construir informações
    public Usuario(){
        super();
    }
//Esta é a ordem dos atributos a serem buscados nas consultas sql, ATENÇÂO na ordem
//Um atributo fora da ordem de acordo com o seu Mer, pode 
//fazer com que ele retorne durante a busca valores invertidos    
    public Usuario (Integer idUsuario, String nome, String sobrenome, String login, String senha, String email, String dataNascimento,
         String cpf,String cidade, String endereco, String cep, int tipo) {
        super();
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.senha = senha;
        this.email= email;
        this.dataNascimento= dataNascimento;
        this.cpf= cpf;
        this.cidade= cidade;        
        this.endereco= endereco;
        this.cep= cep;
        this.tipo=tipo;

}

    

  
}

