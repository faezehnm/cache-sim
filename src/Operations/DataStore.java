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
            if( CacheManager.isDataInCache(address)){
                Cache.dataStatistics.increaseHit();
                CacheManager.writeBlockInCache(address);
            }
            else{
                Cache.dataStatistics.increaseMiss();
                CacheManager.writeBlockInCache(address);
            }
        }

        protected static void WTvsWN(String address)
        {
            Cache.dataStatistics.increaseCopiesBack(1);
            if( CacheManager.isDataInCache(address)){
                Cache.dataStatistics.increaseHit();
                CacheManager.writeBlockInCache(address);
            }
            else{
                Cache.dataStatistics.increaseMiss();
            }
        }

        protected static void WBvsWA(String address)
        {

            if( CacheManager.isDataInCache(address)) {
                System.out.println("**");
                Cache.dataStatistics.increaseHit();
            }
            else{
                Cache.dataStatistics.increaseMiss();
                CacheManager.writeBlockInCache(address);
            }
            CacheManager.setDirtyBlock(address);

        }

        protected static void WBvsWN(String address)
        {
            if( CacheManager.isDataInCache(address)) {
                Cache.dataStatistics.increaseHit();
                CacheManager.setDirtyBlock(address);
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
