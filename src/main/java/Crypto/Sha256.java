package Crypto;

import java.security.MessageDigest;

public class Sha256 {

    public static byte[] hashData(byte[] data) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data);
    }
}
