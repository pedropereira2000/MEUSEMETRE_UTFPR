package Bean;

import Bean.Permissao;


public abstract class Servidor {
    private String nome;
    private int siape;
    private String email;
    private int ramal;
    private String senha;
    private String foto;
    private int permissao;
    private String emailAl;
    private String conPessoal;
    private String conPessoal2;
  
    //Permissao perm;
    
    public Servidor(){
        nome = "";
        siape = 0;
        email = "";
        ramal = 0;
        senha = "";
        foto = "";
        permissao = 0;
        emailAl = "";
        conPessoal = "";
        conPessoal2 = "";
        
       //perm = new Permissao();
    }
    
    public Servidor(String nome, int siape, String email, int ramal, String senha, String foto,int permissao, String emailAl,String conPessoal, String conPessoal2 ){
        this.nome = nome;
        this.siape = siape;
        this.email = email;
        this.ramal = ramal;
        this.senha = senha;
        this.foto = foto;
        this.permissao = permissao;
        this.emailAl = emailAl;
        this.conPessoal = conPessoal;
        this.conPessoal2 = conPessoal2;
       // this.perm = perm;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public int getSiape(){
        return siape;
    }
    
    public void setSiape(int siape){
        this.siape = siape;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public int getRamal(){
        return ramal;
    }
    
    public void setRamal(int Ramal){
        this.ramal = ramal;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
     public String getFoto(){
        return foto;
    }
    
    public void setFoto(String foto){
        this.foto = foto;
    }
    
     public int getPermissao(){
        return permissao;
    }
    
    public void setPermissao(int permissao){
        this.permissao = permissao;
    }
    
    public String getEmailAl(){
        return emailAl;
    }
    
    public void setEmailAl(String emailAl){
        this.emailAl = emailAl;
        
    }
    
    public String getConPessoal(){
        return conPessoal;
    }
    
    public void setConPessoal(String conPessoal){
        this.conPessoal = conPessoal;
    }
    
    public String getConPessoal2(){
        return conPessoal2;
    }
    
    public void setConPessoal2(String conPessoal2){
        this.conPessoal2 = conPessoal2;
    }
    
   /* public Permissao getPerm(){
        return perm;
    }
    
    public void setPerm(Permissao perm){
        this.perm = perm;
    }*/
}