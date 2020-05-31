package Operations;

import Component.Block;
import Component.Cache;
import Component.ICache;

public class CacheManager {

    public static void writeBlockInCache(String address)
    {
        Address current = new Address(address);
        Block block = new Block();
        block.setTag(current.getTag());
        block.setValidBit(1);
        Cache.dSets.get(current.getSet()).addBlock(block);
    }

    public static void setDirtyBlock(String address)
    {
        Address current = new Address(address);
        Block block = new Block();
        block.setTag(current.getTag());
        block.setValidBit(1);
        Cache.dSets.get(current.getSet()).addBlock(block);
        Cache.dSets.get(current.getSet()).findBlock(current.getTag()).setDirtyBit(1) ;
    }

    public static boolean isDataInCache(String address)
    {
        Address current = new Address(address) ;

        if( Cache.dSets.get(current.getSet()).getBlocks().size()!=0) {
//            System.out.println(Cache.dSets.get(current.getSet()).getBlocks().get(0));
            for (Block block : Cache.dSets.get(current.getSet()).getBlocks()) {
//                System.out.println( block.getValidBit());
                if (block.getValidBit() == 1 && block.getTag().equals(current.getTag())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void expulsionDataBlock(String address)
    {
        Cache.dataStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize/4);
        Cache.dataStatistics.increaseReplaceNum();
        Address current = new Address(address);
        Block block = new Block();
        block.setTag(current.getTag());
        block.setValidBit(1);
        Cache.dSets.get(current.getSet()).replaceBlock(block);
        setDirtyBlock(address);
    }

//    public static void expulsionInstructionBlock(String address)
//    {
//        ICache.instructionStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize/4);
//        ICache.instructionStatistics.increaseReplaceNum();
//        Address current = new Address(address);
//        Block block = new Block();
//        block.setTag(current.getTag());
//        block.setValidBit(1);
//        Cache.dSets.get(current.getSet()).replaceBlock(block);
//        setDirtyBlock(address);
//    }

}
