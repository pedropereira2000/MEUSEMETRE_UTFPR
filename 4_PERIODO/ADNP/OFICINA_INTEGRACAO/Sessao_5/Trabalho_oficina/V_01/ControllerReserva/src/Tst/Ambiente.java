package Tst;


import Tst.Reserva;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phili
 */
public class Ambiente {
    private String bloco;
    private String salaNum;
    private String tipoSala;
    private Reserva rese;/*Chamando a classe Reserva*/
    
    public Ambiente(){
        bloco = "";
        salaNum = "";
        tipoSala = "";
        rese = new Reserva();
    }
    
    public Ambiente(String bloco, String salaNum, String tipoSala, Reserva rese){
        this.bloco = bloco;
        this.salaNum = salaNum;
        this.tipoSala = tipoSala;
        this.rese = rese;
    }
    
    public String getBloco(){
        return bloco;
    }
    
    public void setBloco(String bloco){
        this.bloco = bloco;
    }
    
    public String getSalaNum(){
        return salaNum;
    }
    
    public void setSalaNum(String salaNum){
        this.salaNum = salaNum;
    }
    
    public String getTipoSala(){
        return tipoSala;
    }
    
    public void setTipoSala(String tipoSala){
        this.tipoSala = tipoSala;
    }
    
    public Reserva getRese(){
        return rese;
    }
    
    public void setRese(Reserva rese){
        this.rese = rese;
    }
}
