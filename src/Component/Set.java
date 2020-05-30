package Component;

import java.util.LinkedList;

public class Set {

    private int blocksNum ;
    private LinkedList<Block> blocks ;

    public Set()
    {
        this.blocksNum = Cache.BaseInfo.associativity ;

        blocks = new LinkedList<>();
        for( int i=0 ; i<blocksNum ; i++) {
            addBlock(new Block());
        }
    }

    public void addBlock(Block block)
    {
        if( !blocks.contains(block) ){
            if(  blocks.size()== blocksNum )
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

    public LinkedList<Block> getBlocks() {
//        System.out.println(blocks);
        return blocks;
    }

    public Block findBlock(String tag)
    {
        for(int i=0 ; i<blocks.size() ; i++){
            if( blocks.get(i).getTag().equals(tag))
                return blocks.get(i);
        }
        return null;
    }
}
