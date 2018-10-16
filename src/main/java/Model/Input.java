package Model;

import Crypto.Signature;
import Utils.Base58;
import com.google.gson.JsonObject;

import java.security.interfaces.ECPublicKey;

public class Input implements JSONNetworkObj, JSONSignedObj, JSONHashObj {

    private final static String JSON_OUTPOINT = "outpoint";
    private final static String JSON_SIGNATURE = "signature";
    private final static String JSON_PUBLIC_KEY_ENC = "publicKeyEncoded";

    private Outpoint outpoint;

    private Signature signature;

    private ECPublicKey publicKey;

    public Input(Outpoint outpoint, ECPublicKey publicKey) {
        this.outpoint = outpoint;
        this.publicKey = publicKey;
    }

    public Outpoint getOutpoint() {
        return outpoint;
    }

    public void setOutpoint(Outpoint outpoint) {
        this.outpoint = outpoint;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public ECPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(ECPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public JsonObject toJson() {
        JsonObject inputObj = new JsonObject();
        inputObj.add(JSON_OUTPOINT, outpoint.toJson());
        inputObj.addProperty(JSON_SIGNATURE, Base58.encode(signature.getRaw()));
        inputObj.addProperty(JSON_PUBLIC_KEY_ENC, Base58.encode(publicKey.getEncoded()));

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
        inputObj.addProperty(JSON_SIGNATURE, Base58.encode(signature.getRaw()));
        inputObj.addProperty(JSON_PUBLIC_KEY_ENC, Base58.encode(publicKey.getEncoded()));

        return inputObj;
    }
}
