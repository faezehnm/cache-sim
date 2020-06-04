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
        set.get(current.getSet()).addBlock(block);
    }

    public static void setDirtyBlock(String address ,HashMap<Long , Set> set,DataOrInstruction type )
    {
        Address current = new Address(address);
        set.get(current.getSet()).findBlock(current.getTag()).setDirtyBit(1);
    }

    public static boolean isInCache(String address ,HashMap<Long , Set> set)
    {
        Address current = new Address(address) ;

        if( set.get(current.getSet()).getBlocks().size()!=0) {
            for (Block block : set.get(current.getSet()).getBlocks()) {
                if (block.getValidBit() == 1 && block.getTag().equals(current.getTag())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void updateBlockState(String address , HashMap<Long , Set> set )
    {
        Block toUpdate =  set.get(new Address(address).getSet()).findBlock(new Address(address).getTag());
        set.get(new Address(address).getSet()).updateBlockSituation(toUpdate);

    }

}
