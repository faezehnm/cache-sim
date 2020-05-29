package Component;

import java.util.LinkedList;

public class Set {

    private int blocksNum ;
    private int blockSize ;
    private LinkedList<Block> blocks ;

    public Set(int blocksNum , int blockSize)
    {
        this.blocksNum = blocksNum ;
        this.blockSize = blockSize ;
        blocks = new LinkedList<>();
    }

    public void addBlock(Block block)
    {
        if( !blocks.contains(block) ){
            if(  blocks.size()==blocksNum )
                replaceBlock(block);
            else
                blocks.addFirst(block);
        }

    }

    public void updateBlockSituation(Block block)
    {
        blocks.remove(block);
        addBlock(block);
    }

    public void replaceBlock(Block block)
    {
        blocks.removeLast();
        addBlock(block);
    }

}
