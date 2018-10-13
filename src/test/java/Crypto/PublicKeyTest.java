package Crypto;

import Utils.Hex;
import org.junit.Test;
import java.security.interfaces.ECPublicKey;

public class PublicKeyTest {

    @Test
    public void generateAndHashPublicKeys() throws Exception{
        for(int i = 0; i < 100; i++) {
            ECPublicKey publicKey = (ECPublicKey) KeyCreator.generateKeyPair().getPublic();
            System.out.println("Public Key Hash: " + Hex.bytesToHex(Sha256.hashData(publicKey.getEncoded())));
        }
    }

}
