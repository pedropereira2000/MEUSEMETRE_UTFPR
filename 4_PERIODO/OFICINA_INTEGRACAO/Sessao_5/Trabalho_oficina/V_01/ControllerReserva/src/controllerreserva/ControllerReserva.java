
        
package controllerreserva;


public class ControllerReserva {

    public static void main(String[] args) {
        
     /* Tst.Pacote pacote = new Tst.Pacote();
      
      pacote.p();*/
     Tst.Leitura leit = new Tst.Leitura();
     Tst.Permissao permi = new Tst.Permissao();
     Integer op = 2;
     //Primeira implementação para definir o tipo de permissão do servidor
     //op=leit("fasfasf");
     if(op == 1){
         permi.cadastrarPermissao(1);
     }else if(op == 2){
         permi.cadastrarPermissao(2);
     }else {
         permi.cadastrarPermissao(0);
     }
     System.out.println("Permissão cadastrarda: "+permi.consultarPermissao());
    }
    
}
