package Crypto;

import Model.Address;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPublicKey;

public class PublicKeyTest {

    @Test
    public void createPublicKeyFromRaw() throws Exception{

        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();

        ECPublicKey newPublicKey = KeyCreator.createPublicKey(publicKey.getW().getAffineX(), publicKey.getW().getAffineY());

        Assert.assertEquals(publicKey.getW().getAffineX(), newPublicKey.getW().getAffineX());
        Assert.assertEquals(publicKey.getW().getAffineY(), newPublicKey.getW().getAffineY());

    }

    @Test
    public void createCoinAddress() throws Exception{
        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();

        Address address = Address.createAddressFromPublicKey(publicKey);

        System.out.println(address.getAddress());
    }

}
