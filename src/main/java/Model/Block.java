package Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Block implements JSONNetworkObj, JSONHashObj, Cloneable {

    public final static String JSON_TRANSACTIONS = "transactions";

    public final static String JSON_BLOCK_HEADER = "blockHeader";

    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    private BlockHeader blockHeader;

    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }

    public Block(Transaction coinbaseTransaction, BlockHeader blockHeader) {

        //Coinbase Transaction: This first transaction is where the miner adds the payout to his address. Fees + Model.Block reward.

        transactions.add(coinbaseTransaction);
        this.blockHeader = blockHeader;
    }

    public JsonObject toJson() {
        JsonObject blockObj = new JsonObject();
        blockObj.add(JSON_BLOCK_HEADER, blockHeader.toJson());

        JsonArray txArray = new JsonArray();
        for (int i = 0; i < transactions.size(); i++) {
            txArray.add(transactions.get(i).toJson());
        }

        blockObj.add(JSON_TRANSACTIONS, txArray);
        return blockObj;
    }

    public JsonObject toJSONForHashing() {
        JsonObject blockObj = new JsonObject();
        blockObj.add(JSON_BLOCK_HEADER, blockHeader.toJSONForHashing());

        JsonArray txArray = new JsonArray();
        for (int i = 0; i < transactions.size(); i++) {
            txArray.add(transactions.get(i).toJSONForHashing());
        }

        blockObj.add(JSON_TRANSACTIONS, txArray);
        return blockObj;
    }

}
