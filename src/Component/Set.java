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

            if(  blocks.size()== blocksNum ) {
                System.out.println("set is full so replace ");
                replaceBlock(block);
            }
            else {
//                System.out.println("add successful");
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
        block.setDirtyBit(1);

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


        blocks.removeLast();
        addBlock(block);
    }

    public LinkedList<Block> getBlocks()
    {
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

    private boolean contain(Block block)
    {
//        System.out.println();
//        System.out.println("shiiiiiiiiiiiiiiiiiiiiiit");

        for( int i=0 ; i<blocks.size() ; i++){
//            System.out.println(blocks.get(i).getTag().equals(block.getTag()));
            if( blocks.get(i).getTag().equals(block.getTag()))
                return true;
        }
//        System.out.println("shiiiiiiiiiiiiiiiiiiiiiit");
        return false;
    }
}
