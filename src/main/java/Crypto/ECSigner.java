package Crypto;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class ECSigner {


    public static Crypto.Signature signData(PrivateKey privateKey, byte[] data) throws Exception {
        Signature dsa = Signature.getInstance("SHA256withECDSA");

        dsa.initSign(privateKey);
        dsa.update(data);

        /*
         * Now that all the data to be signed has been read in, generate a
         * signature for it
         */

        byte[] realSig = dsa.sign();

        return new Crypto.Signature(realSig);
    }

    public static boolean verifyData(PublicKey publicKey, byte[] data, Crypto.Signature signature) throws Exception {
        Signature dsa = Signature.getInstance("SHA256withECDSA");
        dsa.initVerify(publicKey);
        dsa.update(data);
        return dsa.verify(signature.getRaw());
    }
}
