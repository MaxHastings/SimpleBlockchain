package Crypto;

import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;

public class PrivateKeyTest {

    @Test
    public void createPrivateKeyFromRaw() throws Exception {

        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();

        ECPrivateKey newPrivateKey = KeyCreator.createPrivateKey(privateKey.getS());

        Assert.assertEquals(privateKey.getS(), newPrivateKey.getS());

    }

}
