package Model;

import java.util.ArrayList;

public class Chain { //TODO: Implement LevelDB to handle chain

    private ArrayList<Block> blocks = new ArrayList<Block>();

    public Chain(Block generationBlock) {

        //The initial generation block
        blocks.add(generationBlock);

    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public Block getBlock(int index) {
        return blocks.get(index);
    }

    public Block getBlock(String blockHash) {
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            if (block.getBlockHeader().getBlockHash().equals(blockHash)) {
                return block;
            }
        }
        return null;
    }
}
