package Crypto;

import Utils.Hex;
import org.junit.Test;
import java.security.interfaces.ECPrivateKey;

public class PrivateKeyTest {

    @Test
    public void generateAndHashPrivateKeys() throws Exception{
        for(int i = 0; i < 100; i++) {
            ECPrivateKey privateKey = (ECPrivateKey) KeyCreator.generateKeyPair().getPrivate();
            System.out.println("Private Key Hash: " + Hex.bytesToHex(Sha256.hashData(privateKey.getEncoded())));
        }
    }

}
