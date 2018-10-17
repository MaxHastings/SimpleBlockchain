package Model;

public class MinerTest {

    public static void main(String[] args) throws Exception{

        Block block = BlockTest.createTestBlock();

        Miner miner = new Miner(block);

        miner.findSmallestHash(3);

    }

}
