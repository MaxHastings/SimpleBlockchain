package Crypto;

import Model.Address;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public class KeyPairTest {

    @Test
    public void generateKeyPairs() throws Exception {

        for (int i = 0; i < 100; i++) {
            KeyPair keyPair = KeyCreator.generateKeyPair();
            ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
            ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();

            System.out.println("Private Key : " + PrivateKeyUtil.privateKeyToBase58(privateKey));
            System.out.println("Public Key  : " + Address.createAddressFromPublicKey(publicKey).getAddress());
            System.out.println("-----------------------------------------------------------");
        }
    }

}
