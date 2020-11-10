import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.security.*;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

public class Usuario {

    private ObjectOutputStream sOutput;
    private ObjectInputStream sInput;

    private Socket socket;
    private String server;
    private int port;
    private Cipher cipher1;
    private Cipher cipher2;
    int i = 0;
    message m;
    SecretKey AESkey;
    message toSend;
    static String IV = "AAAAAAAAAAAAAAAA";
//==============================================================================
    Usuario(String server, int port) {
        this.server = server;
        this.port = port;
    }
//==============================================================================
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        String serverAddress;

        int portNumber = 8002;
        if (args.length < 1) {
            serverAddress = "localhost";
        } else {
            serverAddress = args[0];
        }
        Usuario usuario = new Usuario(serverAddress, portNumber);
        usuario.generateAESkey();
        usuario.start();
    }
//==============================================================================
    void start() throws IOException {
        socket = new Socket(server, port);
        System.out.println("COnexao aceitada " + socket.getInetAddress() + " :" + socket.getPort());

        sInput = new ObjectInputStream(socket.getInputStream());
        sOutput = new ObjectOutputStream(socket.getOutputStream());

        new sendToServer().start();
        new listenFromServer().start();
    }
//==============================================================================
    class listenFromServer extends Thread {

        public void run() {
            while (true) {
                try {
                    m = (message) sInput.readObject();
                    decryptMessage(m.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Conexao fechada");
                }
            }
        }
    }
//==============================================================================
    class sendToServer extends Thread {

        public void run() {
            while (true) {
                try {

                    if (i == 0) {
                        toSend = null;

                        toSend = new message(encryptAESKey());
                        sOutput.writeObject(toSend);
                        i = 1;
                    } else {

                        System.out.println("Usuario: Digite sua mensagem e pressione enter para enviar >");
                        Scanner sc = new Scanner(System.in);
                        String s = sc.nextLine();
                        toSend = new message(encryptMessage(s));
                        sOutput.writeObject(toSend);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Nenhuma mensagem enviada ao servidor.");
                    break;
                }
            }
        }
    }
//==============================================================================
    void generateAESkey() throws NoSuchAlgorithmException {
        AESkey = null;
        KeyGenerator Gen = KeyGenerator.getInstance("AES");
        Gen.init(128);
        AESkey = Gen.generateKey();
        System.out.println("Gerado a chave AES: " + AESkey);
    }
//==============================================================================
    private byte[] encryptAESKey() {
        cipher1 = null;
        byte[] key = null;
        try {
            PublicKey pK = readPublicKeyFromFile("public.key");
            System.out.println("Encriptando chave AES usando chave publica RSA" + pK);
            cipher1 = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            cipher1.init(Cipher.ENCRYPT_MODE, pK);
            long time1 = System.nanoTime();
            key = cipher1.doFinal(AESkey.getEncoded());
            long time2 = System.nanoTime();
            long totalRSA = time2 - time1;
            i = 1;
        } catch (Exception e) {
            System.out.println("Erro na chave de codificacao: " + e.getMessage());
            e.printStackTrace();
        }
        return key;
    }
//==============================================================================
    private byte[] encryptMessage(String s) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException {
        cipher2 = null;
        byte[] cipherText = null;
        cipher2 = Cipher.getInstance("AES/CBC/PKCS5PADDING");

        cipher2.init(Cipher.ENCRYPT_MODE, AESkey, new IvParameterSpec(IV.getBytes()));
        long time3 = System.nanoTime();
        cipherText = cipher2.doFinal(s.getBytes());
        long time4 = System.nanoTime();
        long totalAES = time4 - time3;
        return cipherText;
    }
//==============================================================================
    private void decryptMessage(byte[] encryptedMessage) {
        cipher2 = null;
        try {
            cipher2 = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher2.init(Cipher.DECRYPT_MODE, AESkey, new IvParameterSpec(IV.getBytes()));
            byte[] msg = cipher2.doFinal(encryptedMessage);
            System.out.println("Usuario: Mensagem enviada do servidor >>" + new String(msg));
            System.out.println("Usuario: Digite a mensagem e depois pressione enter para enviar >");
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
            System.out.println("Excecao gerada no metodo decryptData: " + e.getMessage());
        }
    }
//==============================================================================
    public void closeSocket() {
        try {
            if (sInput != null) {
                sInput.close();
            }
            if (sOutput != null) {
                sOutput.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ioe) {
            System.out.println("Erro em desconectar o metodo.");
        }
    }
//==============================================================================
    PublicKey readPublicKeyFromFile(String fileName) throws IOException {

        FileInputStream in = new FileInputStream(fileName);
        ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in));

        try {
            BigInteger m = (BigInteger) oin.readObject();
            BigInteger e = (BigInteger) oin.readObject();
            RSAPublicKeySpec keySpecifications = new RSAPublicKeySpec(m, e);

            KeyFactory kF = KeyFactory.getInstance("RSA");
            PublicKey pubK = kF.generatePublic(keySpecifications);
            return pubK;
        } catch (Exception e) {
            throw new RuntimeException("Some error in reading public key", e);
        } finally {
            oin.close();
        }
    }
//==============================================================================
}


