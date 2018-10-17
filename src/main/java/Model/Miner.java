package Model;

import Utils.BigMath;

import java.math.BigInteger;
import java.util.Scanner;

public class Miner {

    private BigInteger smallestInt = BigMath.pow(BigInteger.valueOf(2), BigInteger.valueOf(255));

    private Block block;

    private int bestNonce = 0;

    private boolean doStop = false;

    public Miner(Block block) {
        this.block = block;
    }

    public void findSmallestHash(int threadCount) throws Exception{

        for(int i = 0; i < threadCount; i++){
            new MiningThread().start();
        }

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter user input to stop mining: ");
        reader.next();
        reader.close();

        doStop = true;

        System.out.println("Smallest Block Hash as Big Integer: " + smallestInt);

        block.setNonce(bestNonce);
        System.out.println("Smallest Block Hash in HEX: " + block.getHexHash());
    }

    private class MiningThread extends Thread {

        public void run() {
            while (!doStop) {
                try {
                    block.setNonce(block.getNonce() + 1);
                    BigInteger bigInteger = block.getBigIntegerHash();

                    if (bigInteger.compareTo(smallestInt) < 0) {
                        smallestInt = bigInteger;
                        bestNonce = block.getNonce();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
