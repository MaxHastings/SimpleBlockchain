package Model;

public class Outpoint {

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

    public String toString(){
        return prevTxHash + index;
    }
}
