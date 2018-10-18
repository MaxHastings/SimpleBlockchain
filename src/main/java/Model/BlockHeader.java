package Model;

import com.google.gson.JsonObject;

public class BlockHeader {

    public final static String JSON_BLOCK_HASH = "blockHash";
    public final static String JSON_PREV_BLOCK_HASH = "prevBlockHash";
    public final static String JSON_NONCE = "nonce";
    public final static String JSON_MERKLE_ROOT = "merkleRoot";

    private BlockHash blockHash;

    private BlockHash prevBlockHash;

    private MerkleRoot hashMerkleRoot;

    private Nonce nonce = new Nonce(0);

    private Target hashTarget;

    public BlockHeader(BlockHash blockHash, BlockHash prevBlockHash, MerkleRoot hashMerkleRoot, Target hashTarget) {
        this.blockHash = blockHash;
        this.prevBlockHash = prevBlockHash;
        this.hashMerkleRoot = hashMerkleRoot;
        this.hashTarget = hashTarget;
    }

    public BlockHash getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(BlockHash blockHash) {
        this.blockHash = blockHash;
    }

    public BlockHash getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(BlockHash prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }

    public MerkleRoot getHashMerkleRoot() {
        return hashMerkleRoot;
    }

    public void setHashMerkleRoot(MerkleRoot hashMerkleRoot) {
        this.hashMerkleRoot = hashMerkleRoot;
    }

    public Nonce getNonce() {
        return nonce;
    }

    public void setNonce(Nonce nonce) {
        this.nonce = nonce;
    }

    public Target getHashTarget() {
        return hashTarget;
    }

    public void setHashTarget(Target hashTarget) {
        this.hashTarget = hashTarget;
    }

    public JsonObject toJson() {
        JsonObject blockObj = new JsonObject();
        blockObj.addProperty(JSON_BLOCK_HASH, blockHash.getBase58Hash());
        blockObj.addProperty(JSON_PREV_BLOCK_HASH, prevBlockHash.getBase58Hash());
        blockObj.addProperty(JSON_NONCE, nonce.getRaw());
        blockObj.addProperty(JSON_MERKLE_ROOT, hashMerkleRoot.getBase58Hash());
        return blockObj;
    }

    public JsonObject toJSONForHashing() {
        JsonObject blockObj = new JsonObject();
        blockObj.addProperty(JSON_PREV_BLOCK_HASH, prevBlockHash.getBase58Hash());
        blockObj.addProperty(JSON_NONCE, nonce.getRaw());
        blockObj.addProperty(JSON_MERKLE_ROOT, hashMerkleRoot.getBase58Hash());
        return blockObj;
    }
}
