package Model;

import Utils.BigMath;
import Utils.Hex;
import org.junit.Test;

import java.math.BigInteger;

public class BlockTest {

    public static Block createTestBlock() throws Exception{
        Transaction tx = TransactionTest.createTestTransaction();

        Block block = new Block(tx);
        block.setPrevBlockHash("Bitcoin Opinion: This is the Quiet Before The Storm");

        return block;
    }

    @Test
    public void showBlockDataToHash() throws Exception{
        Block block = createTestBlock();

        System.out.println(block.toJSONForHashing().toString());
    }

    @Test
    public void hashTestBlock() throws Exception{
        Block block = createTestBlock();

        System.out.println("Block Hash as Big Integer: " + block.getBigIntegerHash());
        System.out.println("Block Hash in HEX: " + block.getHexHash());
    }

    @Test
    public void testFindingSmallestHash() throws Exception{
        Block block = createTestBlock();

        BigInteger smallestInt = BigMath.pow(BigInteger.valueOf(2), BigInteger.valueOf(256));
        byte[] smallsha256 = new byte[]{0};

        long endTime = System.currentTimeMillis() + 60 * 1000 * 1;
        for(int i = 0; System.currentTimeMillis() < endTime; i++) {

            block.setNonce(i);
            BigInteger bigInteger = block.getBigIntegerHash();

            if (bigInteger.compareTo(smallestInt) < 0) {
                smallestInt = bigInteger;
                smallsha256 = block.getRawHash();
            }
        }

        System.out.println("Smallest Block Hash as Big Integer: " + smallestInt);
        System.out.println("Smallest Block Hash in HEX: " + Hex.bytesToHex(smallsha256));
    }

}
