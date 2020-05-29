import java.util.HashMap;

public class ICache extends Cache {
    private int iCacheSize ;
    private CacheStatistics instructionStatistics;
    private HashMap<Integer ,Set> dSets ;

    public void setiCacheSize(int iCacheSize) {
        this.iCacheSize = iCacheSize;
    }

    public int getiCacheSize() {
        return iCacheSize;
    }

    public CacheStatistics getInstructionStatistics() {
        return instructionStatistics;
    }

    //TODO : complete
    public void fetchInstruction(int address)
    {

    }

    @Override
    public void buildCache() {
        super.buildCache();
        for( int  i=0 ; i< iCacheSize/blockSize ; i++){
            dSets.put(i ,new Set(associativity,blockSize)) ;
        }
    }

}
