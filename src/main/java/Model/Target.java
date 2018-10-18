package Model;

import java.math.BigInteger;

public class Target {

    private BigInteger targetThreshold;

    public Target(BigInteger targetThreshold) {
        this.targetThreshold = targetThreshold;
    }

    public BigInteger getRaw() {
        return targetThreshold;
    }

    public void setRaw(BigInteger targetThreshold) {
        this.targetThreshold = targetThreshold;
    }
}
