package Operations;

import Component.Cache;

public class DataStore {

    static class WriteState
    {
        protected static void WTvsWA(String address)
        {
            Cache.dataStatistics.increaseCopiesBack(1);
            if( isDataInCache(address)){
                Cache.dataStatistics.increaseHit();
                MangeCache.writeWordInCache(address);
            }
            else{
                Cache.dataStatistics.increaseMiss();
                MangeCache.writeBlockInCache(address);
            }
        }

        protected static void WTvsWN(String address)
        {
            Cache.dataStatistics.increaseCopiesBack(1);
            if( isDataInCache(address)){
                Cache.dataStatistics.increaseHit();
                MangeCache.writeWordInCache(address);
            }
            else{
                Cache.dataStatistics.increaseMiss();
            }
        }

        protected static void WBvsWA(String address)
        {
            MangeCache.setDirtyBlock(address);
            if( isDataInCache(address)) {
                Cache.dataStatistics.increaseHit();
            }
            else{
                Cache.dataStatistics.increaseMiss();
                MangeCache.writeBlockInCache(address);
            }

        }

        protected static void WBvsWN(String address)
        {
            if( isDataInCache(address)) {
                Cache.dataStatistics.increaseHit();
                MangeCache.setDirtyBlock(address);
            }
            else{
                Cache.dataStatistics.increaseMiss();
                Cache.dataStatistics.increaseCopiesBack(1);
            }
        }
    }

    static class MangeCache
    {
        //TODO : complete
        private static void writeBlockInCache(String address)
        {

        }


        //TODO : complete
        private static void writeWordInCache(String address)
        {

        }

        //TODO : complete
        private void expulsionBlock(String address)
        {
            Cache.dataStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize/4);
        }

        //TODO : complete
        private static void setDirtyBlock(String address)
        {

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

    private static boolean isDataInCache(String address)
    {
        long n = (long) Long.parseLong(address, 16);
//        long setIndex = n%dSets.size() ;
        return false;
    }

}
