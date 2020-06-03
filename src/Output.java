import Component.Cache;
import Component.ICache;

public class Output {
    private Cache cache ;

    public Output( Cache cache)
    {
        this.cache = cache ;
    }


    public void printOutput()
    {
        printCacheInfo();
        printStatistics();
    }

    public void printCacheInfo()
    {
        System.out.println("***CACHE SETTINGS***");
        printCacheSize();
        System.out.println("Associativity: " + Cache.BaseInfo.associativity );
        System.out.println("Block size: " + Cache.BaseInfo.blockSize );
        printWritePolicy();
        printAllocationPolicy();
        System.out.println();
    }

    private void printCacheSize()
    {
        if( cache instanceof ICache){
            ICache iCache =(ICache)cache;
            System.out.println("Split I- D-cache");
            System.out.println("I-cache size: " + iCache.iCacheSize);
            System.out.println("D-cache size: " + iCache.dCacheSize);
        }
        else if ( cache instanceof Cache ){
            System.out.println("Unified I- D-cache");
            System.out.println("Size: " + cache.dCacheSize);
        }
    }

    private void printWritePolicy()
    {
        if( Cache.BaseInfo.writePolicy.toString().equals("writeBack"))
            System.out.println("Write policy: WRITE BACK");
        else
            System.out.println("Write policy: WRITE THROUGH");
    }

    private void printAllocationPolicy()
    {
        if( Cache.BaseInfo.allocationPolicy.toString().equals("allocate"))
            System.out.println("Allocation policy: WRITE ALLOCATE");
        else
            System.out.println("Allocation policy: WRITE NO ALLOCATE");
    }

    private void printStatistics()
    {
        System.out.println("***CACHE STATISTICS***");
        printInstructions();
        printData();
        printTraffic();
    }

    private void printInstructions()
    {
        if( cache instanceof ICache ) {
            ICache iCache = (ICache) cache;
            System.out.println("INSTRUCTIONS");
            System.out.println("accesses: " + iCache.instructionStatistics.getAccess());
            System.out.println("misses: " + iCache.instructionStatistics.getMisses());
            System.out.println("miss rate: " + iCache.instructionStatistics.getMissRate() + " (hit rate " +iCache.instructionStatistics.getHitRate() + ")");
            System.out.println("replace: " + iCache.instructionStatistics.getReplaceNum());
        }
        else {

            System.out.println("INSTRUCTIONS");
            System.out.println("accesses: " + Cache.getiStatistics().getAccess());
            System.out.println("misses: " + Cache.getiStatistics().getMisses());
            System.out.println("miss rate: " + Cache.getiStatistics().getMissRate() +" (hit rate "  + Cache.getiStatistics().getHitRate() +  ")");
            System.out.println("replace: " + Cache.getiStatistics().getReplaceNum() );
        }
    }

    private void printData()
    {
        System.out.println("DATA");
        System.out.println("accesses: " + cache.dataStatistics.getAccess());
        System.out.println("misses: " + cache.dataStatistics.getMisses());
        System.out.println("miss rate: " + cache.dataStatistics.getMissRate() + " (hit rate " +Cache.dataStatistics.getHitRate() + ")");
        System.out.println("replace: " + cache.dataStatistics.getReplaceNum());
    }

    private void printTraffic()
    {
        System.out.println("TRAFFIC (in words)");
        if( Cache.BaseInfo.architecture.toString().equals("harvard")) {
            long fetch = cache.dataStatistics.getDemandFetch() + ICache.instructionStatistics.getDemandFetch();
            long copiesBack = cache.dataStatistics.getCopiesBack() + ICache.instructionStatistics.getCopiesBack();
            System.out.println("demand fetch: " + fetch);
            System.out.println("copies back: " + copiesBack);
        }
        else {
            long fetch = cache.dataStatistics.getDemandFetch() + Cache.getiStatistics().getDemandFetch();
            long copiesBack = cache.dataStatistics.getCopiesBack() + Cache.getiStatistics().getCopiesBack();
            System.out.println("demand fetch: " + fetch);
            System.out.println("copies back: " + copiesBack);
        }
    }

}
