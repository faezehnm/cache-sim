package Component;
import Enums.AllocationPolicy;
import Enums.ArchitectureType;
import Enums.LoadStoreState;
import Enums.WritePolicy;
import Operations.DataLoad;
import Operations.DataStore;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Cache {

    public static CacheStatistics dataStatistics ;
    protected static HashMap<Integer ,Set> dSets ;
    protected static int dCacheSize ;

    public static class BaseInfo
    {
        public static int blockSize ;
        public static ArchitectureType architecture ;
        public static int associativity;
        public static WritePolicy writePolicy ;
        public static AllocationPolicy allocationPolicy;
    }

    public void buildCache()
    {
        dSets = new HashMap<>();
        for( int  i=0 ; i< dCacheSize/BaseInfo.blockSize ; i++){
            dSets.put(i ,new Set(BaseInfo.associativity,BaseInfo.blockSize)) ;
        }
//        System.out.println(dSets);
    }

    public void doOrder(LoadStoreState state , int address)
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


