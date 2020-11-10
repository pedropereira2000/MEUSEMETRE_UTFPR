
package Bean;


public class Periodo {
    private String dataInicio;
    private String dataFim;
    private String horaInicial;
    private String horaFinal;
    
    public Periodo(){
        dataInicio = "";
        dataFim = "";
        horaInicial = "";
        horaFinal = "";
    }
     
    public Periodo(String dataInicio,String dataFim, String horaInicial, String horaFinal){
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
    }
    
    public String getDataInicio(){
        return dataInicio;
    }
    
    public void setDataInicio(String dataInicio){
        this.dataInicio = dataInicio;
    }
    
    public String getDataFim(){
        return dataFim;
    }
    
    public void setDataFim(String dataFim){
        this.dataFim = dataFim;
    }
    
    public String getHoraInicial(){
        return horaInicial;
    }
    
    public void setHoraInicial(String horaInicial){
        this.horaInicial = horaInicial;
    }
    
    public String getHoraFinal(){
        return horaFinal;
    }
    
    public void setHoraFinal(String horaFinal){
        this.horaFinal = horaFinal;
    }
    
    
}

