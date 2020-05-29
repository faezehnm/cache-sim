package Component;

import java.util.HashMap;

public class ICache extends Cache {
    protected static int iCacheSize ;
    protected static CacheStatistics instructionStatistics;
    protected static HashMap<Integer ,Set> dSets ;

    @Override
    public void buildCache() {
        super.buildCache();
        for( int  i=0 ; i< iCacheSize/BaseInfo.blockSize ; i++){
            dSets.put(i ,new Set(BaseInfo.associativity,BaseInfo.blockSize)) ;
        }
    }


    //TODO : complete
    public void fetchInstruction(int address)
    {

    }


}
