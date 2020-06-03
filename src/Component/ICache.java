package Component;

import Enums.ArchitectureType;
import Enums.DataOrInstruction;
import Operations.Address;
import Operations.CacheManager;

import java.util.HashMap;
import java.util.Map;

public class ICache extends Cache {
    public static int iCacheSize ;
    public static Statistics instructionStatistics;
    protected static HashMap<Long ,Set> iSets ;

    @Override
    public void buildCache()
    {
        super.buildCache();
        iSets = new HashMap<>();
        for( long  i=0 ; i< iCacheSize/BaseInfo.blockSize ; i++){
            iSets.put(i ,new Set()) ;
        }
        instructionStatistics = new Statistics();
        instructionStatistics.initial();
    }

    public void fetchInstruction(String address)
    {
        System.out.println("instructionLoad");
        if( CacheManager.isInCache(address , iSets))
            instructionStatistics.increaseHit();
        else {
            instructionStatistics.increaseMiss();
            CacheManager.writeBlockInCache(address,iSets, DataOrInstruction.instruction);
            instructionStatistics.increaseDemandFetch(ICache.BaseInfo.blockSize/4);
        }
    }

    @Override
    public void cleanUpCache()
    {
        super.cleanUpCache();
        for (Map.Entry<Long, Set> entry : iSets.entrySet()) {
            for( Block block : entry.getValue().getBlocks() ){
                if( block.getDirtyBit()==1 ){
                    ICache.instructionStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize / 4);
                }
            }
        }
    }
}
