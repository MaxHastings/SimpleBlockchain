package Model;

import Crypto.BlockHasher;

import java.util.Scanner;

public class MinerTest {

    public static void main(String[] args) throws Exception{

        Block block = BlockTest.createTestBlock();

        Miner miner = new Miner(block);

        miner.findSmallestHash(4);

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter user input to stop mining: ");
        reader.next();
        reader.close();

        miner.setDoStop(true);

        miner.compareThreads();

        System.out.println("Smallest Block Hash as Big Integer: " + miner.getSmallestInt());
        System.out.println("Total Hashes: " + miner.getHashCount().intValue());

        block.getBlockHeader().setNonce(miner.getBestNonce());
        System.out.println("Smallest Block Hash in HEX: " + BlockHasher.getHexHash(block));

    }

}
