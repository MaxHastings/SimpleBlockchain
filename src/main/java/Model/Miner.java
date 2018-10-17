package Model;

import Crypto.BlockHasher;
import Crypto.Digest;
import Utils.BigMath;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

public class Miner {

    private BigInteger smallestInt = BigMath.pow(BigInteger.valueOf(2), BigInteger.valueOf(255));

    private Block block;

    private int bestNonce = 0;

    private boolean doStop = false;

    public Miner(Block block) {
        this.block = block;
    }

    public BigInteger getSmallestInt() {
        return smallestInt;
    }

    public void setSmallestInt(BigInteger smallestInt) {
        this.smallestInt = smallestInt;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public int getBestNonce() {
        return bestNonce;
    }

    public void setBestNonce(int bestNonce) {
        this.bestNonce = bestNonce;
    }

    public boolean isDoStop() {
        return doStop;
    }

    public void setDoStop(boolean doStop) {
        this.doStop = doStop;
    }

    public void findSmallestHash(int threadCount){

        for(int i = 0; i < threadCount; i++){
            new MiningThread().start();
        }

    }

    private class MiningThread extends Thread {

        public void run() {
            while (!doStop) {
                try {
                    String jsonBlock = grabNextJSONBlock();

                    BigInteger bigIntegerBlockHash = BlockHasher.getBigIntegerHash(Digest.sha256Hash(jsonBlock.getBytes()));

                    compareHashes(bigIntegerBlockHash);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private synchronized void compareHashes(BigInteger bigInteger){
            if (bigInteger.compareTo(smallestInt) < 0) {
                smallestInt = bigInteger;
                bestNonce = block.getNonce();
            }
        }

        private synchronized String grabNextJSONBlock() throws Exception{
            block.setNonce(block.getNonce() + 1);
            return block.toJSONForHashing().toString();
        }
    }

}
