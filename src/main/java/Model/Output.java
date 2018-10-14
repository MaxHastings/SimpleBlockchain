package Model;

import Utils.Base58;
import com.google.gson.JsonObject;

public class Output implements JSONNetworkObj, JSONSignedObj, JSONHashObj {

    private final static String JSON_AMOUNT = "amount";
    private final static String JSON_PUB_KEY_HASH = "pubKeyHash";

    private long amount;

    private byte[] pubKeyHash; //Crypto.Digest Hash of publicKey;

    public Output(long amount, byte[] pubKeyHash) {
        this.amount = amount;
        this.pubKeyHash = pubKeyHash;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public byte[] getPubKeyHash() {
        return pubKeyHash;
    }

    public void setPubKeyHash(byte[] pubKeyHash) {
        this.pubKeyHash = pubKeyHash;
    }

    public JsonObject toJson() {
        JsonObject outputObj = new JsonObject();
        outputObj.addProperty(JSON_AMOUNT, amount);
        outputObj.addProperty(JSON_PUB_KEY_HASH, Base58.encode(pubKeyHash));
        return outputObj;
    }

    public JsonObject toJSONForSigning() {
        return toJson();
    }

    public JsonObject toJSONForHashing() {
        return toJson();
    }
}
