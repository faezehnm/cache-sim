public class Output {
    private Cache cache ;

    public Output( Cache cache)
    {
        this.cache = cache ;
    }

    public void cacheInfo()
    {
        System.out.println("***CACHE SETTINGS***");
        printCacheSize();
        System.out.println("Associativity: " + cache.getAssociativity());
        System.out.println("Block size: " +cache.getBlockSize() );
        printWritePolicy();
        printAllocationPolicy();
    }

    private void printCacheSize()
    {
        if( cache instanceof Cache){
            ICache iCache =(ICache)cache;
            System.out.println("Split I- D-cache");
            System.out.println("I-cache size: " + iCache.getiCacheSize());
            System.out.println("D-cache size: " + iCache.getdCacheSize());
        }
        else if ( cache instanceof ICache ){
            System.out.println("Unified I- D-cache");
            System.out.println("Size : " + cache.getdCacheSize());
        }
    }


    private void printWritePolicy()
    {
        if( cache.getWritePolicy().equals("writeBack"))
            System.out.println("Write policy: WRITE BACK");
        else
            System.out.println("Write policy: WRITE THROUGH");
    }
    //TODO : check no allocate format
    private void printAllocationPolicy()
    {
        if( cache.getAllocationPolicy().equals("allocate"))
            System.out.println("Allocation policy: WRITE ALLOCATE");
        else
            System.out.println("Allocation policy: WRITE NO-ALLOCATE");
    }

}
