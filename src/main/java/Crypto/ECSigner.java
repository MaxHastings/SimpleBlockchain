package Crypto;

import Model.Transaction;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class ECSigner {


    public static Crypto.Signature signData(PrivateKey privateKey, Transaction transaction) throws Exception {
        Signature dsa = Signature.getInstance("SHA256withECDSA");

        dsa.initSign(privateKey);
        dsa.update(transaction.toJSONForSigning().toString().getBytes());

        /*
         * Now that all the data to be signed has been read in, generate a
         * signature for it
         */

        byte[] realSig = dsa.sign();

        return new Crypto.Signature(realSig);
    }

    public static boolean verifyData(PublicKey publicKey, Transaction transaction, Crypto.Signature signature) throws Exception {
        Signature dsa = Signature.getInstance("SHA256withECDSA");
        dsa.initVerify(publicKey);
        dsa.update(transaction.toJSONForSigning().toString().getBytes());
        return dsa.verify(signature.getRaw());
    }
}
