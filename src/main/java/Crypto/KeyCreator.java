package Crypto;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.*;

public class KeyCreator {

    public static KeyPair generateKeyPair() throws Exception{
        ECGenParameterSpec kpgparams = new ECGenParameterSpec("secp256k1");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        //keyGen.initialize(256, random);
        keyGen.initialize(kpgparams, random);

        return keyGen.generateKeyPair();
    }

    public static ECPublicKey createPublicKey(byte[] encoding) throws Exception{
        X509EncodedKeySpec ks = new X509EncodedKeySpec(encoding);
        KeyFactory kf = KeyFactory.getInstance("EC");
        return (ECPublicKey) kf.generatePublic(ks);
    }

    public static ECPrivateKey createPrivateKey(byte[] encoding) throws Exception{
        PKCS8EncodedKeySpec ps = new PKCS8EncodedKeySpec(encoding);
        KeyFactory kf = KeyFactory.getInstance("EC");
        return  (ECPrivateKey) kf.generatePrivate(ps);
    }

    public static ECPublicKey createPublicKey(BigInteger x, BigInteger y) throws Exception{
        ECPoint pubPoint = new ECPoint(x,y);
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("EC", "SunEC");
        parameters.init(new ECGenParameterSpec("secp256k1"));
        ECParameterSpec ecParameters = parameters.getParameterSpec(ECParameterSpec.class);
        ECPublicKeySpec pubSpec = new ECPublicKeySpec(pubPoint, ecParameters);
        KeyFactory kf = KeyFactory.getInstance("EC");
        return (ECPublicKey)kf.generatePublic(pubSpec);
    }

    public static ECPrivateKey createPrivateKey(BigInteger x) throws Exception{
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("EC", "SunEC");
        parameters.init(new ECGenParameterSpec("secp256k1"));
        ECParameterSpec ecParameters = parameters.getParameterSpec(ECParameterSpec.class);
        ECPrivateKeySpec privSpec = new ECPrivateKeySpec(x, ecParameters);
        KeyFactory kf = KeyFactory.getInstance("EC");
        return (ECPrivateKey) kf.generatePrivate(privSpec);
    }
}
