package Operations;
import Component.Cache;

public class DataLoad {
    private Cache cache ;

    public DataLoad(Cache cache)
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
            CacheManager.writeBlockInCache(address,Cache.dSets);
            Cache.dataStatistics.increaseDemandFetch(Cache.BaseInfo.blockSize/4);
        }
    }

}