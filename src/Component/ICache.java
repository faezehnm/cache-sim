package Component;

import Enums.ArchitectureType;
import Enums.DataOrInstruction;
import Operations.Address;
import Operations.CacheManager;

import java.util.HashMap;

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



}
