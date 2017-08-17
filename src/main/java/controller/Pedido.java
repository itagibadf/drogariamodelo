
package controller;



/**
 *
 * @author itagiba
 */


public class Pedido extends Object {
    
    private int idPedido;
    private Double quantidadePedido;
    private String necessitaEntrega;
    private Double valorTotal;    
    private  String dataPedido;  
    private String enderecoEntrega;  
    private int estado;

     private Produto produto =  new Produto();
    private Usuario usuario =  new Usuario();

    Pedido(int idPedido, Double quantidadePedido, String necessitaEntrega,
            double valorTotal, String dataPedido, String enderecoEntrega,  int estado, 
            Produto produto, Usuario usuario  ) {
       this.idPedido = idPedido;
       this.quantidadePedido=  quantidadePedido;
       this.necessitaEntrega= necessitaEntrega;
       this.valorTotal= valorTotal;
       this.dataPedido = dataPedido;
       this.enderecoEntrega= enderecoEntrega;
       this.estado = estado;
     
      this.produto = produto;
     this.usuario = usuario;
   }   
    public int getIdPedido() {
        return idPedido;
    }
   
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;

    }
    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }


    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
   
      
      public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    //construtor vazio para persistência de informações
    public Pedido(){
        super();
    }

    public void setUsuario(int idUsuario) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Double getQuantidadePedido() {
        return quantidadePedido;
    }

    public void setQuantidadePedido(Double quantidadePedido) {
        this.quantidadePedido = quantidadePedido;
    }

    public String getNecessitaEntrega() {
        return necessitaEntrega;
    }

    public void setNecessitaEntrega(String necessitaEntrega) {
        this.necessitaEntrega = necessitaEntrega;
    }


  

  

    
}
