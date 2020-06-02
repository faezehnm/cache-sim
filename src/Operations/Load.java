package Operations;
import Component.Cache;
import Enums.ArchitectureType;
import Enums.DataOrInstruction;

public class Load {
    private Cache cache ;

    public Load(Cache cache)
    {
        this.cache = cache ;
    }

    public static void loadData(String address)
    {
        if( CacheManager.isInCache(address , Cache.dSets)) {
            Cache.dataStatistics.increaseHit();
        }
        else {
            Cache.dataStatistics.increaseMiss();
            CacheManager.writeBlockInCache(address,Cache.dSets, DataOrInstruction.data);
            Cache.dataStatistics.increaseDemandFetch(Cache.BaseInfo.blockSize/4);
        }
    }

    public static void loadInstruction(String address)
    {
        if( CacheManager.isInCache(address , Cache.dSets)) {
            Cache.getiStatistics().increaseHit();
        }
        else {
            Cache.getiStatistics().increaseMiss();
            CacheManager.writeBlockInCache(address,Cache.dSets, DataOrInstruction.instruction);
            Cache.getiStatistics().increaseDemandFetch(Cache.BaseInfo.blockSize/4);
        }
    }

}