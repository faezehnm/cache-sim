import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.LinkedList;

public class Cache {

    protected int blockSize ;
    protected ArchitectureType architecture ;
    protected int associativity;
    protected WritePolicy writePolicy ;
    protected AllocationPolicy allocationPolicy;
    protected HashMap<Integer ,Set> dSets ;
    protected int dCacheSize ;
    protected CacheStatistics dataStatistics ;
    protected DataStore dataStore ;
    protected DataLoad dataLoad ;


    public void setArchitecture(ArchitectureType architecture) {
        this.architecture = architecture;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public void setAssociativity(int associativity) {
        this.associativity = associativity;
    }

    public void setWritePolicy(WritePolicy writePolicy) {
        this.writePolicy = writePolicy;
    }

    public void setdCacheSize(int dCacheSize) {
        this.dCacheSize = dCacheSize;
    }

    public void setAllocationPolicy(AllocationPolicy allocationPolicy) {
        this.allocationPolicy = allocationPolicy;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public ArchitectureType getArchitecture() {
        return architecture;
    }

    public int getAssociativity() {
        return associativity;
    }

    public WritePolicy getWritePolicy() {
        return writePolicy;
    }

    public HashMap<Integer, Set> getdSets() {
        return dSets;
    }

    public int getdCacheSize() {
        return dCacheSize;
    }

    public AllocationPolicy getAllocationPolicy() {
        return allocationPolicy;
    }

    public CacheStatistics getDataStatistics() {
        return dataStatistics;
    }

    public void buildCache()
    {
        dSets = new HashMap<>();
        for( int  i=0 ; i< dCacheSize/blockSize ; i++){
            dSets.put(i ,new Set(associativity,blockSize)) ;
        }
//        System.out.println(dSets);
    }

    public void doOrder(LoadStoreState state ,int address)
    {
        switch (state.toString()){
            case "dataLoad" :
                dataLoad = new DataLoad(this) ;
                dataLoad.loadData(address);
                break;
            case "dataStore" :
                dataStore = new DataStore(this);
                dataStore.storeData(address);
                break;
        }

    }


    //TODO : complete
    public void cleanUpCache()
    {
        /*
        check all dirty bit & if that block was dirty expulsion from cache
        use method expulsionBlock
         */
    }





}


