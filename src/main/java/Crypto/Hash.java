package Crypto;

public class Hash {

    private byte[] data;

    public Hash(byte[] data) {
        this.data = data;
    }

    public byte[] hashData() throws Exception{
        return Digest.sha256Hash(data);
    }

}
