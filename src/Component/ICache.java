package Component;

import Operations.CacheManager;

import java.util.HashMap;

public class ICache extends Cache {
    public static int iCacheSize ;
    public static Statistics instructionStatistics;
    protected static HashMap<Integer ,Set> iSets ;

    @Override
    public void buildCache()
    {
        super.buildCache();
        iSets = new HashMap<>();
        for( int  i=0 ; i< iCacheSize/BaseInfo.blockSize ; i++){
            iSets.put(i ,new Set()) ;
        }
        instructionStatistics = new Statistics();
        instructionStatistics.initial();
    }

    public void fetchInstruction(String address)
    {
        System.out.println("instructionLoad");
        if( CacheManager.isDataInCache(address))
            instructionStatistics.increaseHit();
        else
            instructionStatistics.increaseMiss();
    }


}
