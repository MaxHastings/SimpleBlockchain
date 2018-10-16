package Model;

import Crypto.Digest;
import Utils.Base58;
import Utils.Hex;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.util.ArrayList;

public class Block implements JSONNetworkObj, JSONHashObj {

    private final static String JSON_BLOCK_HASH = "blockHash";
    private final static String JSON_PREV_BLOCK_HASH = "prevBlockHash";
    private final static String JSON_TRANSACTIONS = "transactions";
    private final static String JSON_NONCE = "nonce";
    private final static String JSON_MERKLE_ROOT = "merkleRoot";

    private String blockHash;

    private String prevBlockHash;

    private String hashMerkleRoot;

    private int nonce = 0;

    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public Block(Transaction coinbaseTransaction) {

        //Coinbase Transaction: This first transaction is where the miner adds the payout to his address. Fees + Model.Block reward.

        transactions.add(coinbaseTransaction);
    }

    public String getHashMerkleRoot() {
        return hashMerkleRoot;
    }

    public void setHashMerkleRoot(String hashMerkleRoot) {
        this.hashMerkleRoot = hashMerkleRoot;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }

    public JsonObject toJson() {
        JsonObject blockObj = new JsonObject();
        blockObj.addProperty(JSON_BLOCK_HASH, blockHash);
        blockObj.addProperty(JSON_PREV_BLOCK_HASH, prevBlockHash);
        blockObj.addProperty(JSON_NONCE, nonce);
        blockObj.addProperty(JSON_MERKLE_ROOT, hashMerkleRoot);

        JsonArray txArray = new JsonArray();
        for (int i = 0; i < transactions.size(); i++) {
            txArray.add(transactions.get(i).toJson());
        }

        blockObj.add(JSON_TRANSACTIONS, txArray);
        return blockObj;
    }

    public JsonObject toJSONForHashing() throws Exception {

        hashMerkleRoot = Base58.encode(new MerkleTree(transactions).generateMerkleRootHash());

        JsonObject blockObj = new JsonObject();
        blockObj.addProperty(JSON_PREV_BLOCK_HASH, prevBlockHash);
        blockObj.addProperty(JSON_NONCE, nonce);
        blockObj.addProperty(JSON_MERKLE_ROOT, hashMerkleRoot);
        return blockObj;
    }

    public byte[] getRawHash() throws Exception {
        return Digest.sha256Hash(toJSONForHashing().toString().getBytes());
    }

    public BigInteger getBigIntegerHash() throws Exception {
        return new BigInteger(1, getRawHash());
    }

    public String getHexHash() throws Exception {
        return Hex.bytesToHex(getRawHash());
    }
}
