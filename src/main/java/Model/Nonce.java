package Model;

public class Nonce {

    private int nonce;

    public Nonce(int nonce) {
        this.nonce = nonce;
    }

    public int getRaw() {
        return nonce;
    }

    public void setRaw(int nonce) {
        this.nonce = nonce;
    }

    public void incrementNonce(){
        nonce++;
    }

}
