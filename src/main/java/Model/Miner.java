package Model;

import Crypto.BlockHasher;
import Crypto.Digest;
import Utils.BigMath;
import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Miner {

    private Block block;

    private Nonce bestNonce = new Nonce(0);

    private BigInteger smallestInt = BigMath.pow(BigInteger.valueOf(2), BigInteger.valueOf(255));

    private boolean doStop = false;

    private AtomicInteger hashCount = new AtomicInteger(0);

    private ArrayList<MiningThread> miningThreads = new ArrayList<MiningThread>();

    public Miner(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public AtomicInteger getHashCount() {
        return hashCount;
    }

    public BigInteger getSmallestInt() {
        return smallestInt;
    }

    public void setSmallestInt(BigInteger smallestInt) {
        this.smallestInt = smallestInt;
    }

    public Nonce getBestNonce() {
        return bestNonce;
    }

    public void setBestNonce(Nonce bestNonce) {
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
            int startNonce = Math.round(((float)Integer.MAX_VALUE) * i * (1 / (float) threadCount));
            System.out.println(startNonce);
            block.getBlockHeader().setNonce(new Nonce(startNonce));
            MiningThread miningThread = new MiningThread(block.toJSONForHashing());
            miningThread.start();
            miningThreads.add(miningThread);
        }

    }

    public void compareThreads(){

        bestNonce = new Nonce(0);
        smallestInt = BigMath.pow(BigInteger.valueOf(2), BigInteger.valueOf(255));

        for(int i = 0; i < miningThreads.size(); i++){
            BigInteger smallestThreadInt = miningThreads.get(i).getSmallestThreadInt();

            if (smallestThreadInt.compareTo(smallestInt) < 0) {
                smallestInt = smallestThreadInt;
                bestNonce = miningThreads.get(i).getBestNonce();
            }
        }

    }

    private class MiningThread extends Thread {

        private BigInteger smallestThreadInt = BigMath.pow(BigInteger.valueOf(2), BigInteger.valueOf(255));

        private Nonce bestNonce = new Nonce(0);

        private JsonObject jsonBlock;

        public MiningThread(JsonObject jsonBlock) {
            this.jsonBlock = jsonBlock;
        }

        public Nonce getBestNonce() {
            return bestNonce;
        }

        public void setBestNonce(Nonce bestNonce) {
            this.bestNonce = bestNonce;
        }

        public BigInteger getSmallestThreadInt() {
            return smallestThreadInt;
        }

        public void setSmallestThreadInt(BigInteger smallestThreadInt) {
            this.smallestThreadInt = smallestThreadInt;
        }

        public void run() {
            while (!doStop) {
                try {
                    int nonce = jsonBlock.getAsJsonObject(Block.JSON_BLOCK_HEADER).get(BlockHeader.JSON_NONCE).getAsInt() + 1;
                    jsonBlock.getAsJsonObject(Block.JSON_BLOCK_HEADER).addProperty(BlockHeader.JSON_NONCE, nonce);

                    byte[] hashedBytes = Digest.sha256Hash(jsonBlock.toString().getBytes());

                    BigInteger bigIntegerBlockHash = BlockHasher.getBigIntegerHash(hashedBytes);

                    if (bigIntegerBlockHash.compareTo(smallestThreadInt) < 0) {
                        smallestThreadInt = bigIntegerBlockHash;
                        bestNonce = new Nonce(nonce);
                    }
                    hashCount.incrementAndGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
