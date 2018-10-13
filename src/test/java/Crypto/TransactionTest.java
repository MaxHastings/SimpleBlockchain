package Crypto;

import Model.Input;
import Model.Outpoint;
import Model.Output;
import Model.Transaction;
import Utils.Base58;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPublicKey;

public class TransactionTest {

    @Test
    public void testSigningVerifyingTransaction() throws Exception
    {
        KeyPair keyPair = KeyCreator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        byte[] addrSha256 = Sha256.hashData(publicKey.getEncoded());
        String addr = Base58.encode(addrSha256);

        Output output = new Output(100, addrSha256);
        Input input = new Input(new Outpoint("1234", 3), 50);

        Transaction transaction = new Transaction();
        transaction.addInput(input);
        transaction.addOutput(output);

        byte[] data = transaction.getObjData().getBytes();
        byte[] signature = ECSigner.signData(keyPair.getPrivate(), data);

        input.setSignature(signature);
        input.setPublicKeyEncoded(publicKey.getEncoded());

        output.setPubKeyHash(addrSha256);

        Assert.assertArrayEquals(output.getPubKeyHash(), Sha256.hashData(input.getPublicKeyEncoded()));

        ECPublicKey publicKeyFromInput = KeyCreator.createPublicKey(input.getPublicKeyEncoded());
        boolean verifySig = ECSigner.verifyData(publicKeyFromInput, data, input.getSignature());
        Assert.assertTrue(verifySig);
    }
}
