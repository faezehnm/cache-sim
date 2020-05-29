public class DataStore {

    public static void storeData(int address)
    {
        switch (Cache.BaseInfo.writePolicy.toString()){
            case "writeTrough" :
                switch (Cache.BaseInfo.allocationPolicy.toString()){
                    case "allocate" :
                        WTvsWA(address);
                        break;
                    case "noAllocate" :
                        WTvsWN(address);
                        break;
                }
                break;

            case "writeBack" :
                switch (Cache.BaseInfo.allocationPolicy.toString()){
                    case "allocate" :
                        WBvsWA(address);
                        break;
                    case "noAllocate" :
                        WBvsWN(address);
                        break;
                }
                break;
        }

    }

    private static void WTvsWA(int address)
    {
        Cache.dataStatistics.increaseCopiesBack(1);
        if( isInCache(address)){
            Cache.dataStatistics.increaseHit();
            writeWordInCache(address);
        }
        else{
            Cache.dataStatistics.increaseMiss();
            writeBlockInCache(address);
        }
    }

    private static void WTvsWN(int address)
    {
        Cache.dataStatistics.increaseCopiesBack(1);
        if( isInCache(address)){
            Cache.dataStatistics.increaseHit();
            writeWordInCache(address);
        }
        else{
            Cache.dataStatistics.increaseMiss();
        }
    }

    private static void WBvsWA(int address)
    {
        setDirtyBlock(address);
        if( isInCache(address)) {
            Cache.dataStatistics.increaseHit();
        }
        else{
            Cache.dataStatistics.increaseMiss();
            writeBlockInCache(address);
        }

    }

    private static void WBvsWN(int address)
    {
        if( isInCache(address)) {
            Cache.dataStatistics.increaseHit();
            setDirtyBlock(address);
        }
        else{
            Cache.dataStatistics.increaseMiss();
            Cache.dataStatistics.increaseCopiesBack(1);
        }
    }

    //TODO : complete
    private static void setDirtyBlock(int address)
    {

    }

    //TODO : complete
    private static boolean isInCache(int address)
    {
        return false;
    }


    //TODO : complete
    private static void writeBlockInCache(int address)
    {

    }


    //TODO : complete
    private static void writeWordInCache(int address)
    {

    }

    //TODO : complete
    private void expulsionBlock(int address)
    {
        Cache.dataStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize/4);
    }

}
