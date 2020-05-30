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
import java.util.logging.LoggingPermission;

public class Cache {

    public static Statistics dataStatistics ;
    protected static HashMap<Integer ,Set> dSets ;
    public static int dCacheSize ;
    public static boolean isCurrentAddressIn ;

    public static class BaseInfo
    {
        public static int blockSize ;
        public static ArchitectureType architecture ;
        public static int associativity;
        public static WritePolicy writePolicy ;
        public static AllocationPolicy allocationPolicy;
    }

    public class Statistics
    {
        private long access ;
        private long misses ;
        private long hits ;
        private float missRate ;
        private float hitRate ;
        private long replaceNum ;
        private long demandFetch ;
        private long copiesBack ;

        public Statistics()
        {
            access = 0 ;
            misses = 0 ;
            hits = 0 ;
            missRate = (float) 0.0;
            hitRate = (float) 0.0 ;
            replaceNum =0 ;
        }

        public void increaseMiss()
        {
            access++ ;
            misses ++ ;
        }

        public void increaseHit()
        {
            access++ ;
            hits++ ;
        }

        public void increaseReplaceNum()
        {
            replaceNum++;
        }

        public void increaseDemandFetch()
        {
            demandFetch++;
        }

        public void increaseCopiesBack(int toIncrease)
        {
            copiesBack += toIncrease;
        }

        public void calculateMissRate()
        {
            missRate =(float) misses/access ;
        }

        public void calculateHitRate()
        {
            hitRate =(float) hits/access ;
        }

        public long getAccess() {
            return access;
        }

        public long getMisses() {
            return misses;
        }

        public long getHits() {
            return hits;
        }

        public double getMissRate() {
            calculateMissRate();
            double valueRounded = Math.round(missRate * 10000D) / 10000D;
            return valueRounded;
        }

        public double getHitRate() {
            calculateHitRate();
            double valueRounded = Math.round(missRate * 10000D) / 10000D;
            return valueRounded;
        }

        public long getReplaceNum() {
            return replaceNum;
        }

        public long getDemandFetch() {
            return demandFetch;
        }

        public long getCopiesBack() {
            return copiesBack;
        }
    }


    public void buildCache()
    {
        dSets = new HashMap<>();
        for( int  i=0 ; i< dCacheSize/BaseInfo.blockSize/BaseInfo.associativity ; i++){
            dSets.put(i ,new Set(BaseInfo.associativity)) ;
        }
//        System.out.println(dSets);
    }

    public void doOrder(LoadStoreState state , String address)
    {
        System.out.println(state.toString());
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


