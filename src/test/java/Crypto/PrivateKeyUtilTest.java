package Crypto;

import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;

public class PrivateKeyUtilTest {

    @Test
    public void testBase58Encoder() throws Exception{

        for(int i = 0; i < 100; i++) {

            KeyPair keyPair = KeyCreator.generateKeyPair();

            ECPrivateKey originalKey = (ECPrivateKey) keyPair.getPrivate();

            String encodedKey = PrivateKeyUtil.privateKeyToBase58(originalKey);

            ECPrivateKey decodedKey = PrivateKeyUtil.base58KeyToPrivateKey(encodedKey);

            Assert.assertArrayEquals(originalKey.getEncoded(), decodedKey.getEncoded());

        }
    }

    @Test
    public void showBase58PrivateKey() throws Exception{
        KeyPair keyPair = KeyCreator.generateKeyPair();

        ECPrivateKey originalKey = (ECPrivateKey) keyPair.getPrivate();

        String encodedKey = PrivateKeyUtil.privateKeyToBase58(originalKey);

        System.out.println(encodedKey);
        System.out.println(encodedKey.length());
    }

}
