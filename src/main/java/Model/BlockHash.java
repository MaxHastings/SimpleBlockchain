package Model;

import Utils.Base58;

public class BlockHash {

    private byte[] blockHash;

    public BlockHash(byte[] blockHash) {
        this.blockHash = blockHash;
    }

    public byte[] getRaw() {
        return blockHash;
    }

    public void setRaw(byte[] blockHash) {
        this.blockHash = blockHash;
    }

    public String getBase58Hash(){
        return Base58.encode(blockHash);
    }

}
