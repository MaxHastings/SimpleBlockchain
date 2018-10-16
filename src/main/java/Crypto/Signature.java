package Crypto;

public class Signature {

    private byte[] signature;

    public Signature(byte[] signature) {
        this.signature = signature;
    }

    public byte[] getRaw() {
        return signature;
    }

    public void setRaw(byte[] signature) {
        this.signature = signature;
    }

}
