package Component;

import java.util.HashMap;

public class ICache extends Cache {
    public static int iCacheSize ;
    public static Statistics instructionStatistics;
    protected static HashMap<Integer ,Set> dSets ;

    @Override
    public void buildCache()
    {
        super.buildCache();
        dSets = new HashMap<>();
        for( int  i=0 ; i< iCacheSize/BaseInfo.blockSize ; i++){
            dSets.put(i ,new Set(BaseInfo.associativity)) ;
        }
    }


    //TODO : complete
    public void fetchInstruction(int address)
    {
        System.out.println("instructionLoad");
    }


}
