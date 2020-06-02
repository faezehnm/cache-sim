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

    public static Statistics dataStatistics  ;
    public static HashMap<Long ,Set> dSets ;
    public static int dCacheSize ;
    public static boolean isCurrentAddressIn ;

    public static class BaseInfo
    {
        public static int blockSize ;
        public static ArchitectureType architecture ;
        public static int associativity;
        public static WritePolicy writePolicy ;
        public static AllocationPolicy allocationPolicy;
        public static int bitDOffset;
        public static int bitSet ;
        public static int setNum ;

        public static void setbitDOffset()
        {
            bitDOffset =(int)(Math.log(blockSize) / Math.log(2)) ;
            setNum = dCacheSize/BaseInfo.blockSize/BaseInfo.associativity;
            bitSet = (int)(Math.log(setNum) / Math.log(2)) ;
//            System.out.println(setNum);
//            System.out.println(Cache.BaseInfo.blockSize/4);
//            System.out.println(bitDOffset);
        }
    }

    public static class Statistics
    {
        public long access ;
        public long misses ;
        public long hits ;
        public float missRate ;
        public float hitRate ;
        public long replaceNum ;
        public long demandFetch ;
        public long copiesBack ;

        public void initial()
        {
            this.access = 0 ;
            this.misses = 0 ;
            this.hits = 0 ;
            this.missRate = (float) 0.0;
            this.hitRate = (float) 0.0 ;
            this.replaceNum =0 ;
        }

        public void increaseMiss()
        {
            System.out.println("miss");
            access++ ;
            misses ++ ;
        }

        public void increaseHit()
        {
            System.out.println("hit");
            access++ ;
            hits++ ;
        }

        public void increaseReplaceNum()
        {
            replaceNum++;
        }

        public void increaseDemandFetch(int toAdd)
        {
            demandFetch += toAdd;
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

        public long getAccess()
        {
            return access;
        }

        public long getMisses()
        {
            return misses;
        }

        public long getHits()
        {
            return hits;
        }

        public double getMissRate()
        {
            calculateMissRate();
            double valueRounded = Math.round(missRate * 10000D) / 10000D;
            return valueRounded;
        }

        public double getHitRate()
        {
            calculateHitRate();
            double valueRounded = Math.round(hitRate * 10000D) / 10000D;
            return valueRounded;
        }

        public long getReplaceNum()
        {
            return replaceNum;
        }

        public long getDemandFetch()
        {
            return demandFetch;
        }

        public long getCopiesBack()
        {
            return copiesBack;
        }
    }


    public void buildCache()
    {
        dSets = new HashMap<>();
//        System.out.println(dCacheSize/BaseInfo.blockSize/BaseInfo.associativity);
        for( long  i=0 ; i< BaseInfo.setNum ; i++){
            dSets.put(i ,new Set()) ;
        }
        dataStatistics = new Statistics();
        dataStatistics.initial();

//        System.out.println(dSets.size());
//        for (int i=0 ; i<dSets.size() ; i++){
//            System.out.println(dSets.get(i).getBlocks().size());
//        }

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
        use method expulsionDataBlock
         */
    }





}


