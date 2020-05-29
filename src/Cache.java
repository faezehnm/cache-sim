import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.LinkedList;

public class Cache {

    protected static CacheStatistics dataStatistics ;
    protected HashMap<Integer ,Set> dSets ;
    protected static int dCacheSize ;

    protected static class BaseInfo
    {
        protected static int blockSize ;
        protected static ArchitectureType architecture ;
        protected static int associativity;
        protected static WritePolicy writePolicy ;
        protected static AllocationPolicy allocationPolicy;

    }

    public static void setdCacheSize(int dCacheSize) {
        Cache.dCacheSize = dCacheSize;
    }

    public HashMap<Integer, Set> getdSets() {
        return dSets;
    }

    public void buildCache()
    {
        dSets = new HashMap<>();
        for( int  i=0 ; i< dCacheSize/BaseInfo.blockSize ; i++){
            dSets.put(i ,new Set(BaseInfo.associativity,BaseInfo.blockSize)) ;
        }
//        System.out.println(dSets);
    }

    public void doOrder(LoadStoreState state ,int address)
    {
        switch (state.toString()){
            case "dataLoad" :
                DataLoad.loadData(address);
                break;
            case "dataStore" :
                DataStore.storeData(address);
                break;
        }

    }


    //TODO : complete
    public void cleanUpCache()
    {
        /*
        check all dirty bit & if that block was dirty expulsion from cache
        use method expulsionBlock
         */
    }





}


