import java.util.HashMap;

public class ICache extends Cache {
    protected static int iCacheSize ;
    private CacheStatistics instructionStatistics;
    protected static HashMap<Integer ,Set> dSets ;

    public void setiCacheSize(int iCacheSize) {
        this.iCacheSize = iCacheSize;
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
        for( int  i=0 ; i< iCacheSize/BaseInfo.blockSize ; i++){
            dSets.put(i ,new Set(BaseInfo.associativity,BaseInfo.blockSize)) ;
        }
    }

}
