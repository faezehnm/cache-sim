package Component;

import java.util.LinkedList;

public class Set {

    private int blocksNum ;
    private LinkedList<Block> blocks ;

    public Set()
    {
        this.blocksNum = Cache.BaseInfo.associativity ;
        blocks = new LinkedList<>();
    }

    public void addBlock(Block block)
    {
//        System.out.print(" &&&& add block to set, " );
//        System.out.println();
        if( !contain(block) ){
//            System.out.print("doesn't contain before,  ");

            if(  blocks.size()== blocksNum ) {
//                System.out.println("set is full so replace ");
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
        blocks.remove(block);
        addBlock(block);
    }

    public void replaceBlock(Block block)
    {
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
