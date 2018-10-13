package Model;

public class Input implements DataObject {

    private Outpoint outpoint;

    private long amount;

    private byte[] signature;

    private byte[] publicKeyEncoded;

    public Input(Outpoint outpoint, long amount) {
        this.outpoint = outpoint;
        this.amount = amount;
    }

    public Outpoint getOutpoint() {
        return outpoint;
    }

    public void setOutpoint(Outpoint outpoint) {
        this.outpoint = outpoint;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
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

    public String getObjData(){
        return outpoint.toString() + amount;
    }

}
