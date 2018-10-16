package Model;

import com.google.gson.JsonObject;

public class Output implements JSONNetworkObj, JSONSignedObj, JSONHashObj {

    private final static String JSON_AMOUNT = "amount";
    private final static String JSON_PUB_KEY_HASH = "pubKeyHash";

    private Amount amount;

    private Address address; //Crypto.Digest Hash of publicKey;

    public Output(Amount amount, Address address) {
        this.amount = amount;
        this.address = address;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public JsonObject toJson() {
        JsonObject outputObj = new JsonObject();
        outputObj.addProperty(JSON_AMOUNT, amount.getSize());
        outputObj.addProperty(JSON_PUB_KEY_HASH, address.getAddress());
        return outputObj;
    }

    public JsonObject toJSONForSigning() {
        return toJson();
    }

    public JsonObject toJSONForHashing() {
        return toJson();
    }
}
