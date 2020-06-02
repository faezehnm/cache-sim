package Operations;

import Component.Block;
import Component.Cache;

import java.util.Map;

public class DataStore {

    static class WriteState
    {
        protected static void WTvsWA(String address)
        {
            Cache.dataStatistics.increaseCopiesBack(1);
            if( CacheManager.isInCache(address ,Cache.dSets)){
                Cache.dataStatistics.increaseHit();
                CacheManager.writeBlockInCache(address,Cache.dSets);
            }
            else{
                Cache.dataStatistics.increaseMiss();
                CacheManager.writeBlockInCache(address ,Cache.dSets);
            }
        }

        protected static void WTvsWN(String address)
        {
            Cache.dataStatistics.increaseCopiesBack(1);
            if( CacheManager.isInCache(address , Cache.dSets)){
                Cache.dataStatistics.increaseHit();
                CacheManager.writeBlockInCache(address , Cache.dSets);
            }
            else{
                Cache.dataStatistics.increaseMiss();
            }
        }

        protected static void WBvsWA(String address)
        {

            if( CacheManager.isInCache(address , Cache.dSets)) {
//                System.out.println("**");
                Cache.dataStatistics.increaseHit();
            }
            else{
                Cache.dataStatistics.increaseMiss();
                CacheManager.writeBlockInCache(address , Cache.dSets);
            }
            CacheManager.setDirtyBlock(address , Cache.dSets);

        }

        protected static void WBvsWN(String address)
        {
            if( CacheManager.isInCache(address , Cache.dSets)) {
                Cache.dataStatistics.increaseHit();
                CacheManager.setDirtyBlock(address , Cache.dSets);
            }
            else{
                Cache.dataStatistics.increaseMiss();
                Cache.dataStatistics.increaseCopiesBack(1);
            }
        }
    }

    public static void storeData(String address)
    {
        switch (Cache.BaseInfo.writePolicy.toString()){
            case "writeTrough" :
                switch (Cache.BaseInfo.allocationPolicy.toString()){
                    case "allocate" :
                        WriteState.WTvsWA(address);
                        break;
                    case "noAllocate" :
                        WriteState.WTvsWN(address);
                        break;
                }
                break;

            case "writeBack" :
                switch (Cache.BaseInfo.allocationPolicy.toString()){
                    case "allocate" :
                        WriteState.WBvsWA(address);
                        break;
                    case "noAllocate" :
                        WriteState.WBvsWN(address);
                        break;
                }
                break;
        }

    }

}
