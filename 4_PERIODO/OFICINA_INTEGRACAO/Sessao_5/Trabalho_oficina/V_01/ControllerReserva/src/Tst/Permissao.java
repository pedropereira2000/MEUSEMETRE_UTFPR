package Tst;

public class Permissao {
    private Integer tipo;
    
    public Permissao(){
        tipo = 0;
    }
    
    public Permissao(Integer tipo){
        this.tipo = tipo;
    }
    
    public Integer getTipo(){
        return tipo;
    }
    
    public void setTipo(Integer tipo){
        this.tipo = tipo;
    }
    
    public void cadastrarPermissao(Integer tipo){
        //Acessa SQL passa o tipo
        //Salva
        this.tipo = tipo;
    }
    
    public void editarPermissao(Integer tipo){
        //Acessa Sql
        //Salva no SQL
        this.tipo = tipo;
    }
    
    public Integer consultarPermissao(){
        //Comando SQL
        return this.tipo;
    }
    
    public void excluirPermissao(){
        //Acessa o SQL
        //Apaga a Permissao do servidor
        this.tipo = 0;
    }
}

