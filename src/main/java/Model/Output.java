package Model;

public class Output implements DataObject {

    private long amount;

    private byte[] pubKeyHash; //Crypto.Sha256 Hash of publicKey;

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

    public String getObjData(){
        return amount + pubKeyHash.toString();
    }

}
