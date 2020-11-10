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
public class Funcionario extends Servidor {
   private Reserva rese;
   
   public Funcionario(){
       rese = new Reserva();
   }
   
   public Funcionario(Reserva rese){
       this.rese = rese;
   }
   
   public Reserva getRese(){
       return rese;
   }
   
   public void setRese(Reserva rese){
       this.rese = rese;
   }
}
