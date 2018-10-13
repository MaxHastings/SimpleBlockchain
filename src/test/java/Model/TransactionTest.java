package Model;

import Crypto.ECSigner;
import Crypto.KeyCreator;
import Crypto.Sha256;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPublicKey;

public class TransactionTest {

    @Test
    public void showNetworkJSON() throws Exception{ //Network JSON should contain all the necessary data to reconstruct the Transaction object
        Transaction transaction = createTestTransaction();

        System.out.println(transaction.toJson().toString());
    }

    @Test
    public void showSignedJSON() throws Exception{ //We sign the entire transaction except for the signature itself and public key encoding.
        Transaction transaction = createTestTransaction();

        System.out.println(transaction.toJSONForSigning().toString());
    }

    public static Transaction createTestTransaction() throws Exception{
        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        byte[] pubKeyHash = Sha256.hashData(publicKey.getEncoded());

        Output output = new Output(100, pubKeyHash);
        Input input = new Input(new Outpoint("1234", 3));

        Transaction transaction = new Transaction();
        transaction.addInput(input);
        transaction.addOutput(output);

        byte[] data = transaction.toJSONForSigning().toString().getBytes();
        byte[] signature = ECSigner.signData(keyPair.getPrivate(), data);

        input.setSignature(signature);
        input.setPublicKeyEncoded(publicKey.getEncoded());

        output.setPubKeyHash(pubKeyHash);

        return transaction;
    }

    @Test
    public void testSigningVerifyingTransaction() throws Exception
    {
        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        byte[] pubKeyHash = Sha256.hashData(publicKey.getEncoded());

        Output output = new Output(100, pubKeyHash);
        Input input = new Input(new Outpoint("1234", 3));

        Transaction transaction = new Transaction();
        transaction.addInput(input);
        transaction.addOutput(output);

        byte[] data = transaction.toJSONForSigning().toString().getBytes();
        byte[] signature = ECSigner.signData(keyPair.getPrivate(), data);

        input.setSignature(signature);
        input.setPublicKeyEncoded(publicKey.getEncoded());

        output.setPubKeyHash(pubKeyHash);

        Assert.assertArrayEquals(output.getPubKeyHash(), Sha256.hashData(input.getPublicKeyEncoded()));

        ECPublicKey publicKeyFromInput = KeyCreator.createPublicKey(input.getPublicKeyEncoded());
        boolean verifySig = ECSigner.verifyData(publicKeyFromInput, data, input.getSignature());
        Assert.assertTrue(verifySig);
    }

    @Test
    public void tryToManipulateData() throws Exception
    {
        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        byte[] pubKeyHash = Sha256.hashData(publicKey.getEncoded());

        Output output = new Output(100, pubKeyHash);
        Input input = new Input(new Outpoint("1234", 3));

        Transaction transaction = new Transaction();
        transaction.addInput(input);
        transaction.addOutput(output);

        byte[] data = transaction.toJSONForSigning().toString().getBytes();
        byte[] signature = ECSigner.signData(keyPair.getPrivate(), data);

        input.setSignature(signature);
        input.setPublicKeyEncoded(publicKey.getEncoded());

        output.setPubKeyHash(pubKeyHash);

        Assert.assertArrayEquals(output.getPubKeyHash(), Sha256.hashData(input.getPublicKeyEncoded()));

        output.setAmount(0);
        data = transaction.toJSONForSigning().toString().getBytes();

        ECPublicKey publicKeyFromInput = KeyCreator.createPublicKey(input.getPublicKeyEncoded());
        boolean verifySig = ECSigner.verifyData(publicKeyFromInput, data, input.getSignature());
        Assert.assertFalse(verifySig);
    }
}
