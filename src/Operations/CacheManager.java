package Operations;

import Component.Block;
import Component.Cache;
import Component.ICache;
import Component.Set;
import Enums.ArchitectureType;
import Enums.DataOrInstruction;

import java.util.HashMap;

public class CacheManager {

    public static void writeBlockInCache(String address , HashMap<Long , Set> set , DataOrInstruction type)
    {
        Address current = new Address(address);
        Block block = new Block(current.getTag(),type.toString());
//        System.out.println("in set "+ current.getSet());
        set.get(current.getSet()).addBlock(block);
    }

    public static void setDirtyBlock(String address ,HashMap<Long , Set> set,DataOrInstruction type )
    {
        Address current = new Address(address);
        Block block = new Block(current.toString() ,type.toString());
        block.setDirtyBit(1);
        set.get(current.getSet()).updateBlockSituation(block);
    }

    public static boolean isInCache(String address ,HashMap<Long , Set> set)
    {
        Address current = new Address(address) ;
//        current.print();
//        System.out.print("in " +address + " with tag: " + current.getTag());
        if( set.get(current.getSet()).getBlocks().size()!=0) {
//            System.out.println(" ::::: set size" + Cache.dSets.get(current.getSet()).getBlocks().size());
//            System.out.println(Cache.dSets.get(current.getSet()).getBlocks().get(0));
            for (Block block : set.get(current.getSet()).getBlocks()) {
//                System.out.println( block.getValidBit());
                if (block.getValidBit() == 1 && block.getTag().equals(current.getTag())) {
                    return true;
                }
            }
        }

        return false;
    }

//    public static void expulsionDataBlock(String address , HashMap<Long , Set> set , DataOrInstruction type)
//    {
//        if(type.toString().equals("vonNeumann")) {
//            Cache.dataStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize / 4);
//            Cache.dataStatistics.increaseReplaceNum();
//        }
//        else{
//            ICache.instructionStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize / 4);
//            ICache.instructionStatistics.increaseReplaceNum();
//        }
//        Address current = new Address(address);
//        Block block = new Block(current.getTag(),type.toString());
//        set.get(current.getSet()).addBlock(block);
////        setDirtyBlock(address , set, type);
//    }



}
