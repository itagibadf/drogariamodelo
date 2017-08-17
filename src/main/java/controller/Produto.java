package controller;

/**
 *
 * @author itagiba
 */
public class Produto extends Object {

    private int idRemedio;
    private String nome;
    private String descricao;
    private String descricaoCompleta;
    private Double precoAntigo;
    private Double preco;
    private Double quantidadeEstoque;
    private Double medida;
    private String unidadeMedida;
    private String validade;
    private Double comprimento;
    private Double largura;
    private Double altura;
    private Double peso;
    private String categoria;
    private String promocao;

    public int getIdRemedio() {
        return idRemedio;
    }

    public void setIdRemedio(int idRemedio) {
        this.idRemedio = idRemedio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Double getMedida() {
        return medida;
    }

    public void setMedida(Double medida) {
        this.medida = medida;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    /**
     * @return the comprimento
     */
    public Double getComprimento() {
        return comprimento;
    }

    /**
     * @return the largura
     */
    public Double getLargura() {
        return largura;
    }

    /**
     * @return the altura
     */
    public Double getAltura() {
        return altura;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param comprimento the comprimento to set
     */
    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    /**
     * @param largura the largura to set
     */
    public void setLargura(Double largura) {
        this.largura = largura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(Double altura) {
        this.altura = altura;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPromocao() {
        return promocao;
    }

    /**
     * @param promocao the promocao to set
     */
    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }
        /**
     * @return the descricaoCompleta
     */
    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    /**
     * @param descricaoCompleta the descricaoCompleta to set
     */
    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    /**
     * @return the precoAntigo
     */
    public Double getPrecoAntigo() {
        return precoAntigo;
    }

    /**
     * @param precoAntigo the precoAntigo to set
     */
    public void setPrecoAntigo(Double precoAntigo) {
        this.precoAntigo = precoAntigo;
    }

    public Produto() {
    }

    public Produto(int idRemedio, String nome, String descricao, String descricaoCompleta, Double precoAntigo, Double preco, Double quantidadeEstoque, Double medida,
            String unidadeMedida, String validade, Double comprimento, Double largura, Double altura, Double peso, String categoria, String promocao) {
        this.idRemedio = idRemedio;
        this.nome = nome;
        this.descricao = descricao;
        this.descricaoCompleta = descricaoCompleta;
        this.precoAntigo = precoAntigo;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.medida = medida;
        this.unidadeMedida = unidadeMedida;
        this.validade = validade;
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
        this.peso = peso;
        this.categoria = categoria;
        this.promocao = promocao;
    }



}
