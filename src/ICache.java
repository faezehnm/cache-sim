public class ICache extends Cache {
    private int iCacheSize ;
    private CacheStatistics instructionStatistics;

    public void setiCacheSize(int iCacheSize) {
        this.iCacheSize = iCacheSize;
    }

    public int getiCacheSize() {
        return iCacheSize;
    }

    public CacheStatistics getInstructionStatistics() {
        return instructionStatistics;
    }
}
