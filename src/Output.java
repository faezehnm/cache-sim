public class Output {
    private Cache cache ;

    public Output( Cache cache)
    {
        this.cache = cache ;
    }

//    public void printOutput()
//    {
//        printCacheInfo();
//        printCacheStatistics();
//    }


    public void printCacheInfo()
    {
        System.out.println("***CACHE SETTINGS***");
        printCacheSize();
        System.out.println("Associativity: " + cache.getAssociativity());
        System.out.println("Block size: " +cache.getBlockSize() );
        printWritePolicy();
        printAllocationPolicy();
        System.out.println();
    }

    private void printCacheSize()
    {
        if( cache instanceof ICache){
            ICache iCache =(ICache)cache;
            System.out.println("Split I- D-cache");
            System.out.println("I-cache size: " + iCache.getiCacheSize());
            System.out.println("D-cache size: " + iCache.getdCacheSize());
        }
        else if ( cache instanceof Cache ){
            System.out.println("Unified I- D-cache");
            System.out.println("Size : " + cache.getdCacheSize());
        }
    }

    private void printWritePolicy()
    {
        if( cache.getWritePolicy().toString().equals("writeBack"))
            System.out.println("Write policy: WRITE BACK");
        else
            System.out.println("Write policy: WRITE THROUGH");
    }

    private void printAllocationPolicy()
    {
        if( cache.getAllocationPolicy().toString().equals("allocate"))
            System.out.println("Allocation policy: WRITE ALLOCATE");
        else
            System.out.println("Allocation policy: WRITE NO ALLOCATE");
    }

    private void printCacheStatistics()
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
            System.out.println("accesses: " + iCache.getInstructionStatistics().getAccess());
            System.out.println("misses: " + iCache.getInstructionStatistics().getMisses());
            System.out.println("miss rate: " + iCache.getInstructionStatistics().getMissRate() + "(hit rate " +iCache.getInstructionStatistics().getHitRate() + ")");
            System.out.println("replace: " + iCache.getInstructionStatistics().getReplaceNum());
        }
        else {
            System.out.println("DATA");
            System.out.println("accesses: " + 0);
            System.out.println("misses: " + 0);
            System.out.println("miss rate: " + "0.0000 " + "(hit rate 0.0000)");
            System.out.println("replace: " + 0);
        }
    }

    private void printData()
    {
        System.out.println("DATA");
        System.out.println("accesses: " + cache.getDataStatistics().getAccess());
        System.out.println("misses: " + cache.getDataStatistics().getMisses());
        System.out.println("miss rate: " + cache.getDataStatistics().getHitRate());
        System.out.println("replace: " + cache.getDataStatistics().getReplaceNum());

    }

    private void printTraffic()
    {
        System.out.println("TRAFFIC (in worlds)");
        System.out.println("demand fetch: " + cache.getDataStatistics().getDemandFetch() );
        System.out.println("copies back: " +cache.getDataStatistics().getCopiesBack() );
    }

}
