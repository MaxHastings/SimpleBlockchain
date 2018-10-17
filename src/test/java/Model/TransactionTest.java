package Model;

import Crypto.ECSigner;
import Crypto.KeyCreator;
import Crypto.Signature;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPublicKey;

public class TransactionTest {

    public static Transaction createTestTransaction() throws Exception {
        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        Address address = Address.createAddressFromPublicKey(publicKey);

        Output output = new Output(new Amount(100), address);
        Input input = new Input(new Outpoint("1234", 3), publicKey);

        Transaction transaction = new Transaction();
        transaction.addInput(input);
        transaction.addOutput(output);

        Signature signature = ECSigner.signData(keyPair.getPrivate(), transaction);

        input.setSignature(signature);

        return transaction;
    }

    @Test
    public void showNetworkJSON() throws Exception { //Network JSON should contain all the necessary data to reconstruct the Transaction object
        Transaction transaction = createTestTransaction();

        System.out.println(transaction.toJson().toString());
    }

    @Test
    public void showSignedJSON() throws Exception { //We sign the entire transaction except for the signature itself and public key encoding.
        Transaction transaction = createTestTransaction();

        System.out.println(transaction.toJSONForSigning().toString());
    }

    @Test
    public void testSigningVerifyingTransaction() throws Exception {
        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        Address address = Address.createAddressFromPublicKey(publicKey);

        Output output = new Output(new Amount(100), address);
        Input input = new Input(new Outpoint("1234", 3), publicKey);

        Transaction transaction = new Transaction();
        transaction.addInput(input);
        transaction.addOutput(output);

        Signature signature = ECSigner.signData(keyPair.getPrivate(), transaction);

        input.setSignature(signature);

        Assert.assertEquals(output.getAddress().getAddress(), Address.createAddressFromPublicKey(input.getPublicKey()).getAddress());

        boolean verifySig = ECSigner.verifyData(input.getPublicKey(), transaction, input.getSignature());

        Assert.assertTrue(verifySig);
    }

    @Test
    public void tryToManipulateAmount() throws Exception {
        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        Address address = Address.createAddressFromPublicKey(publicKey);

        Output output = new Output(new Amount(100), address);
        Input input = new Input(new Outpoint("1234", 3), publicKey);

        Transaction transaction = new Transaction();
        transaction.addInput(input);
        transaction.addOutput(output);

        Signature signature = ECSigner.signData(keyPair.getPrivate(), transaction);

        input.setSignature(signature);

        Assert.assertEquals(output.getAddress().getAddress(), Address.createAddressFromPublicKey(input.getPublicKey()).getAddress());

        output.setAmount(new Amount(0));

        boolean verifySig = ECSigner.verifyData(input.getPublicKey(), transaction, input.getSignature());
        Assert.assertFalse(verifySig);
    }

    @Test
    public void tryToManipulateAddress() throws Exception {
        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        Address address = Address.createAddressFromPublicKey(publicKey);

        Output output = new Output(new Amount(100), address);
        Input input = new Input(new Outpoint("1234", 3), publicKey);

        Transaction transaction = new Transaction();
        transaction.addInput(input);
        transaction.addOutput(output);

        Signature signature = ECSigner.signData(keyPair.getPrivate(), transaction);

        input.setSignature(signature);

        Assert.assertEquals(output.getAddress().getAddress(), Address.createAddressFromPublicKey(input.getPublicKey()).getAddress());

        output.setAddress(Address.createAddressFromPublicKey((ECPublicKey) KeyCreator.generateKeyPair().getPublic()));

        boolean verifySig = ECSigner.verifyData(input.getPublicKey(), transaction, input.getSignature());
        Assert.assertFalse(verifySig);
    }
}
