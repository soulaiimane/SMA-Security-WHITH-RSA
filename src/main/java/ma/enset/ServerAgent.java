package ma.enset;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        String encodedk=(String) getArguments()[0];


        addBehaviour(new CyclicBehaviour() {
        @Override
        public void action() {
            ACLMessage receive=receive();
            if (receive!=null){
                    String cryptedEncodedMsg=receive.getContent();

                    byte[] cryptedMessage= Base64.getDecoder().decode(cryptedEncodedMsg);
                try {
                    byte[] decodedPk=Base64.getDecoder().decode(encodedk);
                    KeyFactory keyFactory=KeyFactory.getInstance("RSA");
                    PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedPk));
                    Cipher cipher=Cipher.getInstance("RSA");
                    cipher.init(Cipher.DECRYPT_MODE,privateKey);
                    byte[] decriptMessage = cipher.doFinal(cryptedMessage);
                    System.out.println("le message décripter est :" + new String(decriptMessage));


                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchPaddingException e) {
                    throw new RuntimeException(e);
                } catch (InvalidKeyException e) {
                    throw new RuntimeException(e);
                } catch (IllegalBlockSizeException e) {
                    throw new RuntimeException(e);
                } catch (BadPaddingException e) {
                    throw new RuntimeException(e);
                } catch (InvalidKeySpecException e) {
                    throw new RuntimeException(e);
                }

            }else {block();}
        }
    });
    }
}
