package Model;

import Utils.Base58;
import com.google.gson.JsonObject;

public class Input implements JSONNetworkObj, JSONSignedObj, JSONHashObj {

    private final static String JSON_OUTPOINT = "outpoint";
    private final static String JSON_SIGNATURE = "signature";
    private final static String JSON_PUBLIC_KEY_ENC = "publicKeyEncoded";

    private Outpoint outpoint;

    private byte[] signature;

    private byte[] publicKeyEncoded;

    public Input(Outpoint outpoint) {
        this.outpoint = outpoint;
    }

    public Outpoint getOutpoint() {
        return outpoint;
    }

    public void setOutpoint(Outpoint outpoint) {
        this.outpoint = outpoint;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public byte[] getPublicKeyEncoded() {
        return publicKeyEncoded;
    }

    public void setPublicKeyEncoded(byte[] publicKeyEncoded) {
        this.publicKeyEncoded = publicKeyEncoded;
    }

    public JsonObject toJson() {
        JsonObject inputObj = new JsonObject();
        inputObj.add(JSON_OUTPOINT, outpoint.toJson());
        inputObj.addProperty(JSON_SIGNATURE, Base58.encode(signature));
        inputObj.addProperty(JSON_PUBLIC_KEY_ENC, Base58.encode(publicKeyEncoded));

        return inputObj;
    }

    public JsonObject toJSONForSigning() {
        JsonObject inputObj = new JsonObject();
        inputObj.add(JSON_OUTPOINT, outpoint.toJSONForSigning());

        return inputObj;
    }

    public JsonObject toJSONForHashing() {
        JsonObject inputObj = new JsonObject();
        inputObj.add(JSON_OUTPOINT, outpoint.toJSONForHashing());
        inputObj.addProperty(JSON_SIGNATURE, Base58.encode(signature));
        inputObj.addProperty(JSON_PUBLIC_KEY_ENC, Base58.encode(publicKeyEncoded));

        return inputObj;
    }
}
