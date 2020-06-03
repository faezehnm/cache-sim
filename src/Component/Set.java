package Component;
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
//        System.out.print(" &&&& add block to set, " );
//        System.out.println();
        if( !contain(block) ){
//            System.out.print("doesn't contain before,  ");
            System.out.println("block size is : " + blocks.size());
            System.out.println(blocksNum);
            if(  blocks.size()== blocksNum ) {
                System.out.println("set is full so replace ");
                replaceBlock(block);
            }
            else {
                System.out.println("add successful");
                blocks.addFirst(block);
//                System.out.println(blocks.size());
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
//                Cache.dataStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize/4);
                break;
            case "instruction" :
                switch (Cache.BaseInfo.architecture.toString()) {
                    case "harvard" :
                        ICache.instructionStatistics.increaseReplaceNum();
//                        ICache.instructionStatistics.increaseCopiesBack(ICache.BaseInfo.blockSize/4);
                        break;
                    case "vonNeumann":
                        Cache.getiStatistics().increaseReplaceNum();
//                        Cache.getiStatistics().increaseCopiesBack(Cache.BaseInfo.blockSize/4);
                        break;

                }

        }
        block.setDirtyBit(1);
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
