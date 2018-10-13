package Utils;

import org.junit.Test;

import java.math.BigInteger;

public class BigMathTest {

    @Test
    public void testBigInteger(){

        BigInteger smallestInt = BigMath.pow(BigInteger.valueOf(2), BigInteger.valueOf(16));

        byte[] testBytes = new byte[]{0, 0, 0, 0, -2};
        BigInteger bigInteger = new BigInteger(1, testBytes);

        System.out.println("Test Bytes: " + Hex.bytesToHex(testBytes));
        System.out.println("Big Integer: " + bigInteger);
        System.out.println("Smallest Int: " + smallestInt);
        System.out.println("Compare: " + bigInteger.compareTo(smallestInt));
        System.out.println("Big Integer in HEX: " + Hex.bytesToHex(bigInteger.toByteArray()));
    }

    @Test
    public void testBigIntegerHashes(){
        byte[] hash1 = Hex.hexToBytes("000010A5DA688C9BCA890B3F0F8A4A6AC1ACCD2DEECCDF1B4E9F6011280BF2D6");
        byte[] hash2 = Hex.hexToBytes("000065E3D19EE1FB71D5EACF4B37CADABF78960F085F39B60A765DA192F7C2C2");

        BigInteger big1 = new BigInteger(1, hash1);
        BigInteger big2 = new BigInteger(1, hash2);

        System.out.println(big1.compareTo(big2));

    }

}
