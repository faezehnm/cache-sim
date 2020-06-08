package Component;
import Enums.AllocationPolicy;
import Enums.WritePolicy;
import Operations.CacheManager;
import java.util.LinkedList;

public class Set {

    private int blocksNum ;
    private LinkedList<Block> blocks ;

    public Set()
    {
        this.blocksNum = Cache.BaseInfo.associativity ;
        blocks = new LinkedList<>();
    }

    public void addBlock(Block block )
    {
        if( !contain(block) ){
            if(  blocks.size()== blocksNum ) {
                replaceBlock(block);
            }
            else {
                blocks.addFirst(block);
            }
        }
    }

    public void updateBlockSituation(Block block)
    {
        blocks.remove(findBlock(block.getTag()));
        addBlock(block);
    }

    public void replaceBlock(Block block)
    {
        switch (block.getType())
        {
            case "data" :
                Cache.dataStatistics.increaseReplaceNum();
                break;
            case "instruction" :
                switch (Cache.BaseInfo.architecture.toString()) {
                    case "harvard" :
                        ICache.instructionStatistics.increaseReplaceNum();
                        break;
                    case "vonNeumann":
                        Cache.getiStatistics().increaseReplaceNum();
                        break;
                }

        }
        if(Cache.BaseInfo.writePolicy.toString().equals("writeBack")){
            if(blocks.getLast().getDirtyBit()==1) {
//                System.out.println("lala");
                Cache.dataStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize / 4);
            }
        }
        blocks.removeLast();
        addBlock(block);
    }

    public LinkedList<Block> getBlocks()
    {
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

    private boolean contain(Block block)
    {
        for( int i=0 ; i<blocks.size() ; i++){
            if( blocks.get(i).getTag().equals(block.getTag()))
                return true;
        }
        return false;
    }
}
