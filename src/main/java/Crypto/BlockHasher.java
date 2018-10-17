package Crypto;

import Model.Block;
import Utils.Hex;

import java.math.BigInteger;

public class BlockHasher {

    public static byte[] getRawHash(Block block) throws Exception {
        return Digest.sha256Hash(block.toJSONForHashing().toString().getBytes());
    }

    public static BigInteger getBigIntegerHash(Block block) throws Exception{
        return new BigInteger(1, getRawHash(block));
    }

    public static String getHexHash(Block block) throws Exception{
        return Hex.bytesToHex(getRawHash(block));
    }

    public static BigInteger getBigIntegerHash(byte[] hash) {
        return new BigInteger(1, hash);
    }

    public static String getHexHash(byte[] hash) {
        return Hex.bytesToHex(hash);
    }

}
