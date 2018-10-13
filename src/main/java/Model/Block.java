package Model;

import java.util.ArrayList;

public class Block {

    private String blockHash;

    private String prevBlockHash;

    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public Block(Transaction coinbaseTransaction) {

        //Coinbase Model.Transaction: This first transaction is where the miner adds the payout to his address. Fees + Model.Block reward.

        transactions.add(coinbaseTransaction);
    }

    public void addTransaction(Transaction transaction){

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
}
