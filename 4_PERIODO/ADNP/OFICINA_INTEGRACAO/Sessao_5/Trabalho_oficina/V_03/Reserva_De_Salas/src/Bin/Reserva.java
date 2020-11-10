
package Bin;


public class Reserva {
    private Boolean contribuicaoReserva;
    private String dataInicio;
    private String periodoReservado;
    private String observacoes;   
    private Servidor serv;/*Chamando a classe Servidor*/
    private String nomeCliente;/*Chamando a classe Cliente*/
    private String docCliente;
    private Ambiente ambi;/*Chamando a classe Ambiente*/
    
    public Reserva(){
        contribuicaoReserva = false;
        dataInicio = "";
        periodoReservado = "";
        observacoes = "";
        serv = new Servidor() {};
        nomeCliente = "";
        docCliente = "";
        ambi = new Ambiente();
    }
    
    public Reserva(Boolean contribuicaoReserva, String dataInicio, String periodoReservado, String observacoes, Servidor serv, String nomeCliente, String docCliente, Ambiente ambi){
        this.contribuicaoReserva = contribuicaoReserva;
        this.dataInicio = dataInicio;
        this.periodoReservado = periodoReservado;
        this.observacoes = observacoes;
        this.serv = serv;
        this.nomeCliente = nomeCliente;
        this.docCliente = docCliente;
        this.ambi = ambi;
    }
    
    public Boolean getcontribuicaoReserva(){
        return contribuicaoReserva;
    }
    
    public void setContribuicaoReserva(Boolean contribuicaoReserva){
        this.contribuicaoReserva = contribuicaoReserva;
    }
    
    public String getDataInicio(){
        return dataInicio;
    }
    
    public void setDataInicio(String dataInicio){
        this.dataInicio = dataInicio;
    }
    
    public String getPeriodoReservado(){
        return periodoReservado;
    }
    
    public void setPeriodoReservado(String periodoReservado){
        this.periodoReservado = periodoReservado;
    }
    
    public String getObservacoes(){
        return observacoes;
    }
    
    public void setObservacoes(String observacoes){
        this.observacoes = observacoes;
    }
    
    public Servidor getServ(){
        return serv;
    }
    
    public void setServ(Servidor serv){
        this.serv = serv;
    }
    
    public String getCliNome(){
        return nomeCliente;
    }
    
    public void setCliNome(String nomeCliente){
        this.nomeCliente = nomeCliente;
    }
    
    public String getCliDoc(){
        return docCliente;
    }
    
    public void setCliDoc(String docCliente){
        this.docCliente = docCliente;
    }
    
    public Ambiente getAmbi(){
        return ambi;
    }
    
    public void setAmbi(Ambiente ambi){
        this.ambi = ambi;
    }
}