package Component;
import Enums.AllocationPolicy;
import Enums.ArchitectureType;
import Enums.LoadStoreState;
import Enums.WritePolicy;
import Operations.Load;
import Operations.Store;
import java.util.HashMap;
import java.util.Map;

public class Cache {

    public static Statistics dataStatistics  ;
    private static Statistics iStatistics;
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

        private String getInFormat(Double valueRounded)
        {
            String result = String.valueOf(valueRounded);
            String[] arr = result.split("\\.");
            if(arr[1].length()<4){
                for( int i=0 ; i< 4-arr[1].length() ; i++){
                    result +="0" ;
                }
            }
            return result;
        }

        public String getMissRate()
        {
            calculateMissRate();
            double valueRounded = Math.round(missRate * 10000D) / 10000D;
            return getInFormat(valueRounded);
        }

        public String getHitRate()
        {
            calculateHitRate();
            double valueRounded = Math.round(hitRate * 10000D) / 10000D;
            return getInFormat(valueRounded);
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
        for( long  i=0 ; i< BaseInfo.setNum ; i++){
            dSets.put(i ,new Set()) ;
        }
        dataStatistics = new Statistics();
        dataStatistics.initial();
        iStatistics = new Statistics();
        iStatistics.initial();
    }

    public void doOrder(LoadStoreState state , String address)
    {
        switch (state.toString()){
            case "dataLoad" :
                Load.loadData(address);
                break;
            case "dataStore" :
                Store.storeData(address);
                break;
            case "instructionLoad" :
                Load.loadInstruction(address);
                break;
        }
    }

    public void cleanUpCache()
    {
        for (Map.Entry<Long, Set> entry : dSets.entrySet()) {
            for( Block block : entry.getValue().getBlocks() ){
                if( block.getDirtyBit()==1 ){
                    Cache.dataStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize / 4);
                }
            }
        }
    }

    public static Statistics getiStatistics()
    {
        return iStatistics;
    }
}


