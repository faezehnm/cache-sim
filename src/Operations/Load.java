package Operations;
import Component.Block;
import Component.Cache;
import Enums.ArchitectureType;
import Enums.DataOrInstruction;

public class Load {

    public static void loadData(String address)
    {
        if( CacheManager.isInCache(address , Cache.dSets)) {
            Cache.dataStatistics.increaseHit();
            CacheManager.updateBlockState(address,Cache.dSets);
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
            CacheManager.updateBlockState(address,Cache.dSets);
        }
        else {
            Cache.getiStatistics().increaseMiss();
            CacheManager.writeBlockInCache(address,Cache.dSets, DataOrInstruction.instruction);
            Cache.getiStatistics().increaseDemandFetch(Cache.BaseInfo.blockSize/4);
        }
    }

}