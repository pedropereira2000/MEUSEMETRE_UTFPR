
package Bean;


public class Reserva {
    private boolean repeteReserva;
    private String semanaDia;
    private String motivoReserva;
    private double contribuicaoReserva;
    private Periodo peri;/*Chamando a classe Periodo */
    private Ambiente ambi;/*Chamando a classe Ambiente*/
    
    public Reserva(){
        repeteReserva = true;
        semanaDia = "";
        motivoReserva = "";
        contribuicaoReserva = 0.0;
        peri = new Periodo();
        ambi = new Ambiente();
    }
    
    public Reserva(boolean repeteReserva, String semanaDia, String motivoReserva, double contribuicaoReserva, Periodo peri, Ambiente ambi){
        this.repeteReserva = repeteReserva;
        this.semanaDia = semanaDia;
        this.motivoReserva = motivoReserva;
        this.contribuicaoReserva = contribuicaoReserva;
        this.peri = peri;
        this.ambi = ambi;
    }
    
    public boolean getRepeteReserva(){
        return repeteReserva;
    }
    
    public void setRepeteReserva(boolean repeteReserva){
        this.repeteReserva = repeteReserva;
    }
    
    public String getSemanaDia(){
        return semanaDia;
    }
    
    public void setSemanaDia(String semanaDia){
        this.semanaDia = semanaDia;
    }
    
    public String getMotivoReserva(){
        return motivoReserva;
    }
    
    public void setMotivoReserva(String motivoReserva){
        this.motivoReserva = motivoReserva;
    }
    
    public double getContribuicaoReserva(){
        return contribuicaoReserva;
    }
    
    public void setContribuicaoReserva(double contribuicaoReserva){
        this.contribuicaoReserva = contribuicaoReserva;
    }
    
    public Periodo getPeri(){
        return peri;
    }
    
    public void setPeri(Periodo peri){
        this.peri = peri;
    }
    
    public Ambiente getAmbi(){
        return ambi;
    }
    
    public void setAmbi(Ambiente ambi){
        this.ambi = ambi;
    }
}