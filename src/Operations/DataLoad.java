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
        if( CacheManager.isDataInCache(address))
            Cache.dataStatistics.increaseHit();
        else
            Cache.dataStatistics.increaseMiss();
    }

}