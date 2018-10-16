package Crypto;

import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public class ECSignerTest {

    @Test
    public void testKeySigningAndVerifying() throws Exception
    {
        for(int i = 0; i < 100; i++) {
            String str = "This is string to sign";
            byte[] strByte = str.getBytes("UTF-8");

            KeyPair keyPair = KeyCreator.generateKeyPair();
            byte[] signature = ECSigner.signData(keyPair.getPrivate(), strByte);
            boolean verifies = ECSigner.verifyData(keyPair.getPublic(), strByte, signature);

            Assert.assertTrue(verifies);
        }
    }

    @Test
    public void testCreatePublicKeyFromEncoded() throws Exception{
        KeyPair keyPair = KeyCreator.generateKeyPair();

        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();

        byte[] pubEncoded = publicKey.getEncoded();
        ECPublicKey publicKeyCreated = KeyCreator.createPublicKey(pubEncoded);

        Assert.assertArrayEquals(pubEncoded, publicKeyCreated.getEncoded());
    }

    @Test
    public void testCreatePrivateKeyFromEncoded() throws Exception{
        KeyPair keyPair = KeyCreator.generateKeyPair();

        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();

        byte[] privEncoded = privateKey.getEncoded();
        ECPrivateKey privateKeyCreated = KeyCreator.createPrivateKey(privEncoded);

        Assert.assertArrayEquals(privEncoded, privateKeyCreated.getEncoded());
    }
}
