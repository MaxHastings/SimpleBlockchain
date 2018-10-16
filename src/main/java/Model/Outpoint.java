package Model;

import com.google.gson.JsonObject;

public class Outpoint implements JSONNetworkObj, JSONSignedObj, JSONHashObj {

    private final static String JSON_PREV_TX_HASH = "prevTxHash";
    private final static String JSON_INDEX = "index";

    private String prevTxHash;

    private int index;

    public Outpoint(String prevTxHash, int index) {
        this.prevTxHash = prevTxHash;
        this.index = index;
    }

    public String getPrevTxHash() {
        return prevTxHash;
    }

    public void setPrevTxHash(String prevTxHash) {
        this.prevTxHash = prevTxHash;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String toString() {
        return prevTxHash + index;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JSON_PREV_TX_HASH, prevTxHash);
        jsonObject.addProperty(JSON_INDEX, index);
        return jsonObject;
    }

    public JsonObject toJSONForSigning() {
        return toJson();
    }

    public JsonObject toJSONForHashing() {
        return toJson();
    }
}
