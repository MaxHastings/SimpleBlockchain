package Crypto;

import Utils.Base58;

import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;

public class PrivateKeyUtil {

    public static String privateKeyToBase58(ECPrivateKey privateKey){

        byte[] key = new byte[32];

        byte[] s = privateKey.getS().toByteArray();

        for(int i = 0; i < key.length && i < s.length; i++){
            key[31 - i] = s[s.length - 1 - i];
        }

        return Base58.encode(key);
    }

    public static ECPrivateKey base58KeyToPrivateKey(String keyString) throws Exception{

        byte[] bytes = Base58.decode(keyString);

        return KeyCreator.createPrivateKey(new BigInteger(1, bytes));

    }

}
