/**
   
   Referências:
   http://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec.html
   http://www.javamex.com/tutorials/cryptography/rsa_encryption.shtml
*/


import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Scanner;
import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


 //=============================================================================

  // A classe servidor irá abrir um soquete na porta TCP 8002
  // o programa irá estabelecer conexão através do socket e enviar mensagens 
  // criptografadas para o servidor

public class Server {

    private ObjectOutputStream sOutput;
    private ObjectInputStream sInput;
    private Cipher keyDecipher;
    private Cipher ServerDecryptCipher;
    private Cipher ServerEncryptCipher;
    SecretKey AESKey;
    int i;
    byte[] input;
    private message m;
    int port;
    static String IV = "AAAAAAAAAAAAAAAA";
    message toSend;
//==============================================================================
    public Server(int port) {
        this.port = port;
    }
//============================================================================== 
    // O metodo main irá instanciar o objeto RSA para criar um RSA poublico
    // Par de chaves privadas e salve-o como arquivos "public.key" e "private.key" no mesmo diretório.
    // O main irá criar um objeto do tipo server e chamará o metodo start()
     
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        int port = 8002;
        Server server = new Server(port);
        server.start();
    }
//==============================================================================
    
    // O metodo start ira criar um ssocket de servidor "ouvindo" conexoes na porta tcp 8002. 
    // Depois que a conexao for estabelecida, a conexao individual sera executada como
    // uma thread independente.
 
    void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.print("Porta " + port + ".");
        Socket socket = serverSocket.accept();  // accepting the connection.
        clientThread t = new clientThread(socket);
        t.run();
        serverSocket.close();
    }
//==============================================================================

    // A classe irá operar no soquete estabelecido a partir do método de início.
    // O thread irá acessar os fluxos de entrada e saída do soquete para receber 
    // e enviar mensagens do cliente
     
    class clientThread extends Thread {

        Socket socket;

        clientThread(Socket socket) throws IOException {
            this.socket = socket;
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            sInput = new ObjectInputStream(socket.getInputStream());
            new listenFromClient().start();
            new sendToClient().start();
        }
    }
//==============================================================================
   
    // "Escuta" continuamente as mensagens recebidas do servidor.
    // Uma vez recebido, decifra-o e escreve no servidor consoladamente as mensagens
    // recebidas do servidor. 
     
    class listenFromClient extends Thread {

        public void run() {

            while (true) {
                try {
                    m = (message) sInput.readObject();

                } catch (ClassNotFoundException e) {
                    System.out.println("Classe nao encontrada ao ler o objeto ");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (i == 0) {
                    if (m.getData() != null) {
                        decryptAESKey(m.getData());
                        System.out.println();
                        i++;
                    } else {
                        System.out.println("Erro em descriptografar a chave AES no metodo clientThread.run()");
                        System.exit(1);
                    }
                } else {
                    if (m.getData() != null) {
                        decryptMessage(m.getData());
                    }
                }
            }
        }
    }
//==============================================================================
 
    // Pega o input de entrada system.in, chama a criptografia na mensagem e a envia ao cliente.		 * 
     
    class sendToClient extends Thread {

        public void run() {
            while (true) {
                try {
                    System.out.println("Servidor: Digite a mensagem e pressione enter para enviar >");
                    Scanner sc = new Scanner(System.in);
                    String s = sc.nextLine();
                    toSend = null;
                    toSend = new message(encryptMessage(s));
                    //		System.out.println("new message: " + toSend);

                    //	sOutput.writeObject(toSend);
                    write();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Nenhuma mensagem enviada para o servidor.");
                    break;
                }
            }
        }

        public synchronized void write() throws IOException {
            sOutput.writeObject(toSend);
            sOutput.reset();
        }
    }
//==============================================================================
   							
    // Recebe a chave AES criptografada do servidor e decifre-a
    // usará a chave privada RSA do par de chaves pública - privada para decifrar 
    // a chave AES criptografada usando a chave pública e enviada pelo cliente.
    // A chave criptografada como um array de bytes
     
    private void decryptAESKey(byte[] encryptedKey) {
        SecretKey key = null;
        PrivateKey privKey = null;
        keyDecipher = null;
        try {
            privKey = readPrivateKeyFromFile("private.key"); //  Chave privada
            keyDecipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // Inicializando a cifragem...
            keyDecipher.init(Cipher.DECRYPT_MODE, privKey);
            key = new SecretKeySpec(keyDecipher.doFinal(encryptedKey), "AES");
            System.out.println();
            System.out.println("Chave AES depois da descriptografia: " + key);
            i = 1;
            AESKey = key;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Excecao em descriptografar usando chave AES: " + e.getMessage());
        }
    }
//==============================================================================
	  
    // Essa classe Decifra e descriptografa a mensagem criptografada usando a chave AES
    // Decifra a mensagem criptografada recebida do cliente
    // Pega o array de bytes da mensagem criptografada como entrada
     
    private void decryptMessage(byte[] encryptedMessage) {
        ServerDecryptCipher = null;
        try {
            ServerDecryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            ServerDecryptCipher.init(Cipher.DECRYPT_MODE, AESKey, new IvParameterSpec(IV.getBytes()));
            byte[] msg = ServerDecryptCipher.doFinal(encryptedMessage);
            System.out.println("Servidor: Mensagem eviada pelo Usuario >> " + new String(msg));
            System.out.println("Sevidor: Digite a mensagem e depois pressione enter para enviar > ");
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
            System.out.println("Excecao gerada no metodo decrypttData" + e.getMessage());
        }
    }
//==============================================================================
   
    // Mensagem criptografada usando a chave AES
    // Recebe a string da mensagem como entrada e a criptografa.
     
    private byte[] encryptMessage(String s) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException {
        ServerEncryptCipher = null;
        byte[] cipherText = null;
        ServerEncryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        ServerEncryptCipher.init(Cipher.ENCRYPT_MODE, AESKey, new IvParameterSpec(IV.getBytes()));
        cipherText = ServerEncryptCipher.doFinal(s.getBytes());

        return cipherText;
    }
//==============================================================================
   
    // Leia a chave privada do arquivo
    // Lê a chave privada RSA do arquivo private.key salvo no mesmo diretório
    // A chave privada é usada para descriptografar e decifrar a chave AES enviada pelo Cliente
     
    PrivateKey readPrivateKeyFromFile(String fileName) throws IOException {

        FileInputStream in = new FileInputStream(fileName);
        ObjectInputStream readObj = new ObjectInputStream(new BufferedInputStream(in));

        try {
            BigInteger m = (BigInteger) readObj.readObject();
            BigInteger d = (BigInteger) readObj.readObject();
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, d);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PrivateKey priKey = fact.generatePrivate(keySpec);
            return priKey;
        } catch (Exception e) {
            throw new RuntimeException("Some error in reading private key", e);
        } finally {
            readObj.close();
        }
    }
}
