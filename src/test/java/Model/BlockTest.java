package Model;

import Crypto.BlockHasher;
import org.junit.Test;

import java.math.BigInteger;

public class BlockTest {

    public static Block createTestBlock() throws Exception {
        Transaction tx = TransactionTest.createTestTransaction();

        BlockHeader blockHeader = new BlockHeader(new BlockHash("Bitcoin Opinion: This is the Quiet Before The Storm".getBytes()),
                new BlockHash("".getBytes()),
                new MerkleTree(tx).generateMerkleRootHash(),
                new Target(new BigInteger("1000")));
        Block block = new Block(tx, blockHeader);

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
