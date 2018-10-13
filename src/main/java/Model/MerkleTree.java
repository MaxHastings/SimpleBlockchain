package Model;

import Crypto.Hash;
import Crypto.Sha256;

import java.util.ArrayList;

public class MerkleTree {

    private ArrayList<Transaction> transactions;

    public MerkleTree(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public byte[] generateMerkleRootHash() throws Exception{

        ArrayList<Hash> hashChain = new ArrayList<Hash>();

        if(transactions.size() % 2 != 0){ //Odd number of transactions so we add the last one twice.
            transactions.add(transactions.get(transactions.size() - 1));
        }

        for(int i = 0; i < transactions.size(); i++){
            byte[] sha256Hash = Sha256.hashData(transactions.get(i).toJSONForSigning().toString().getBytes());
            hashChain.add(new Hash(sha256Hash));
        }

        return hashTheChain(hashChain);
    }

    private byte[] hashTheChain(ArrayList<Hash> hashChain) throws Exception{

        if(hashChain.size() == 1){
            return hashChain.get(0).hashData(); //Break out of recursion when we have only one hash remaining. This is our merkle root.
        }

        ArrayList<Hash> nextHashChain = new ArrayList<Hash>();

        for(int i = 0; i < hashChain.size(); i =+ 2){

            byte[] hash1 = hashChain.get(i).hashData();
            byte[] hash2 = hashChain.get(i + 1).hashData();

            byte[] c = new byte[hash1.length + hash2.length];
            System.arraycopy(hash1, 0, c, 0, hash1.length);
            System.arraycopy(hash2, 0, c, hash1.length, hash2.length);

            byte[] sha256Hash = Sha256.hashData(c);
            Hash hash = new Hash(sha256Hash);

            nextHashChain.add(hash);
        }

        return hashTheChain(nextHashChain);
    }
}
