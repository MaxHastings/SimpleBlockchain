package Model;

import Utils.Base58;

public class MerkleRoot {

    private byte[] merkleRoot;

    public MerkleRoot(byte[] merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public byte[] getRaw() {
        return merkleRoot;
    }

    public void setRaw(byte[] merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public String getBase58Hash(){
        return Base58.encode(merkleRoot);
    }

}
