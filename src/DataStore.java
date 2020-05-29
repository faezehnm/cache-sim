public class DataStore {
    private Cache cache ;

    public DataStore(Cache cache)
    {
      this.cache = cache ;
    }

    public void storeData(int address)
    {
        switch (cache.getWritePolicy().toString()){
            case "writeTrough" :
                switch (cache.getAllocationPolicy().toString()){
                    case "allocate" :
                        WTvsWA(address);
                        break;
                    case "noAllocate" :
                        WTvsWN(address);
                        break;
                }
                break;

            case "writeBack" :
                switch (cache.getAllocationPolicy().toString()){
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

    private void WTvsWA(int address)
    {
        cache.getDataStatistics().increaseCopiesBack(1);
        if( isInCache(address)){
            cache.getDataStatistics().increaseHit();
            writeWordInCache(address);
        }
        else{
            cache.getDataStatistics().increaseMiss();
            writeBlockInCache(address);
        }
    }

    private void WTvsWN(int address)
    {
        cache.getDataStatistics().increaseCopiesBack(1);
        if( isInCache(address)){
            cache.getDataStatistics().increaseHit();
            writeWordInCache(address);
        }
        else{
            cache.getDataStatistics().increaseMiss();
        }
    }

    private void WBvsWA(int address)
    {
        setDirtyBlock(address);
        if( isInCache(address)) {
            cache.getDataStatistics().increaseHit();
        }
        else{
            cache.getDataStatistics().increaseMiss();
            writeBlockInCache(address);
        }

    }

    private void WBvsWN(int address)
    {
        if( isInCache(address)) {
            cache.getDataStatistics().increaseHit();
            setDirtyBlock(address);
        }
        else{
            cache.getDataStatistics().increaseMiss();
            cache.getDataStatistics().increaseCopiesBack(1);
        }
    }

    //TODO : complete
    private void setDirtyBlock(int address)
    {

    }

    //TODO : complete
    private boolean isInCache(int address)
    {
        return false;
    }


    //TODO : complete
    private void writeBlockInCache(int address)
    {

    }


    //TODO : complete
    private void writeWordInCache(int address)
    {

    }

    //TODO : complete
    private void expulsionBlock(int address)
    {
        cache.getDataStatistics().increaseCopiesBack(cache.getBlockSize()/4);
    }

}
