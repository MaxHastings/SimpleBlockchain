package Model;

import Crypto.Digest;
import Utils.Base58;

import java.security.interfaces.ECPublicKey;

public class Address {

    private String address;

    public Address(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Creates a public key address using the provided ECDSA public key.
     *
     * We create a byte array made up of the concatenation of Xcoord and YCoord from the public key.
     * We hash the byte array using SHA-256, and then hashing the result with SHA-1. Creating a 28 character coin address.
     *
     * @return The coin public address.
     */

    public static Address createAddressFromPublicKey(ECPublicKey publicKey) throws Exception{

        byte[] xCoord = new byte[32];
        byte[] yCoord = new byte[32];

        byte[] xKeyCoord = publicKey.getW().getAffineX().toByteArray();
        byte[] yKeyCoord = publicKey.getW().getAffineY().toByteArray();

        for(int i = 0; i < xKeyCoord.length && i < xCoord.length; i++){
            xCoord[31 - i] = xKeyCoord[xKeyCoord.length - 1 - i];
        }

        for(int i = 0; i < yKeyCoord.length && i < yCoord.length; i++){
            yCoord[31 - i] = yKeyCoord[yKeyCoord.length - 1 - i];
        }

        byte[] c = new byte[xCoord.length + yCoord.length];
        System.arraycopy(xCoord, 0, c, 0, xCoord.length);
        System.arraycopy(yCoord, 0, c, xCoord.length, yCoord.length);

        byte[] sha256Hash = Digest.sha256Hash(c);
        byte[] sha1Hash = Digest.sha1Hash(sha256Hash);

        String address = Base58.encode(sha1Hash);
        return new Address(address);
    }

}
