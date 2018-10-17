package Model;

import Crypto.BlockHasher;
import org.junit.Test;

public class BlockTest {

    public static Block createTestBlock() throws Exception {
        Transaction tx = TransactionTest.createTestTransaction();

        Block block = new Block(tx);
        block.setPrevBlockHash("Bitcoin Opinion: This is the Quiet Before The Storm");

        return block;
    }

    @Test
    public void showBlockDataToHash() throws Exception {
        Block block = createTestBlock();

        System.out.println(block.toJSONForHashing().toString());
    }

    @Test
    public void hashTestBlock() throws Exception {
        Block block = createTestBlock();

        System.out.println("Block Hash as Big Integer: " + BlockHasher.getBigIntegerHash(block));
        System.out.println("Block Hash in HEX: " + BlockHasher.getHexHash(block));
    }

}
