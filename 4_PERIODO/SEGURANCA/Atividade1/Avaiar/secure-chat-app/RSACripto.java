import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

public class RSACripto {

    Key publicKey;
    Key privateKey;
    
//==============================================================================
    public static void main(String[] args) throws NoSuchAlgorithmException, GeneralSecurityException, IOException {
        RSACripto rsa = new RSACripto();
        rsa.createRSA();
    }
//==============================================================================
    void createRSA() throws NoSuchAlgorithmException, GeneralSecurityException, IOException {

        KeyPairGenerator kPairGen = KeyPairGenerator.getInstance("RSA");
        kPairGen.initialize(1024);
        KeyPair kPair = kPairGen.genKeyPair();
        publicKey = kPair.getPublic();
        System.out.println(publicKey);
        privateKey = kPair.getPrivate();

        KeyFactory fact = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec pub = fact.getKeySpec(kPair.getPublic(), RSAPublicKeySpec.class);
        RSAPrivateKeySpec priv = fact.getKeySpec(kPair.getPrivate(), RSAPrivateKeySpec.class);
        serializeToFile("public.key", pub.getModulus(), pub.getPublicExponent());
        serializeToFile("private.key", priv.getModulus(), priv.getPrivateExponent());

    }
//==============================================================================
    void serializeToFile(String fileName, BigInteger mod, BigInteger exp) throws IOException {
        ObjectOutputStream ObjOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));

        try {
            ObjOut.writeObject(mod);
            ObjOut.writeObject(exp);
            System.out.println("Key File Created: " + fileName);
        } catch (Exception e) {
            throw new IOException("Erro ao escrever o objeto chave ", e);
        } finally {
            ObjOut.close();
        }
    }
//==============================================================================
}
