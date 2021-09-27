/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Pedro Pereira
 */
public class Calculadora {
    
    public static double soma(double n1, double n2){
        return n1+n2;
    }
    
    public static double subtracao(double n1, double n2){
        return n1-n2;
    }
    
    public static double multiplicacao(double n1, double n2){
        return n1*n2;
    }
    
    public static double divisao(double n1, double n2){
        return n1/n2;
    }
    
    public static String printNum(int num){
        String numT = " ";
        if(num==0)
            numT = "0";
        if(num==1)
            numT = "1";
        if(num==2)
            numT = "2";
        if(num==3)
            numT = "3";
        if(num==4)
            numT = "4";
        if(num==5)
            numT = "5";
        if(num==6)
            numT = "6";
        if(num==7)
            numT = "7";
        if(num==8)
            numT = "8";
        if(num==9)
            numT = "9";
        if(num==10)
            numT = ".";
        
        return numT;
    }
}
