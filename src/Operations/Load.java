package Operations;
import Component.Block;
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
            Block toUpdate =  Cache.dSets.get(new Address(address).getSet()).findBlock(new Address(address).getTag());
            Cache.dSets.get(new Address(address).getSet()).updateBlockSituation(toUpdate);
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
            Block toUpdate =  Cache.dSets.get(new Address(address).getSet()).findBlock(new Address(address).getTag());
            Cache.dSets.get(new Address(address).getSet()).updateBlockSituation(toUpdate);
        }
        else {
            Cache.getiStatistics().increaseMiss();
            CacheManager.writeBlockInCache(address,Cache.dSets, DataOrInstruction.instruction);
            Cache.getiStatistics().increaseDemandFetch(Cache.BaseInfo.blockSize/4);
        }
    }

}