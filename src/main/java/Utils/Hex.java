package Utils;

import com.sun.istack.internal.NotNull;

public class Hex {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    @NotNull
    public static byte[] hexToBytes(@NotNull String hex)
    {
        return hexToBytes(hex.toCharArray());
    }

    @NotNull
    public static byte[] hexToBytes(@NotNull char[] hex)
    {
        if (hex.length % 2 != 0)
            throw new IllegalArgumentException("Must pass an even number of characters.");

        int length = hex.length >> 1;
        byte[] raw = new byte[length];
        for (int o = 0, i = 0; o < length; o++) {
            raw[o] = (byte) ((getHexCharValue(hex[i++]) << 4)
                    | getHexCharValue(hex[i++]));
        }
        return raw;
    }

    public static byte getHexCharValue(char c)
    {
        if (c >= '0' && c <= '9')
            return (byte) (c - '0');
        if (c >= 'A' && c <= 'F')
            return (byte) (10 + c - 'A');
        if (c >= 'a' && c <= 'f')
            return (byte) (10 + c - 'a');
        throw new IllegalArgumentException("Invalid hex character");
    }
}
