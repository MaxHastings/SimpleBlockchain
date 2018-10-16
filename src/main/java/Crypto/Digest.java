package Crypto;

import java.security.MessageDigest;

public class Digest {

    public static byte[] sha256Hash(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data);
    }

    public static byte[] sha1Hash(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        return digest.digest(data);
    }
}
