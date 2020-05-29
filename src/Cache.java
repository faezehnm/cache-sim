import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.LinkedList;

public class Cache {

    private int blockSize ;
    private ArchitectureType architecture ;
    private int associativity;
    private WritePolicy writePolicy ;
    private AllocationPolicy allocationPolicy;
    private HashMap<Integer , LinkedList<Block>> sets ;
    private int dCacheSize ;
    private CacheStatistics dataStatistics ;


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

    public HashMap<Integer, LinkedList<Block>> getSets() {
        return sets;
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
        sets = new HashMap<>();
        for( int  i=0 ; i<blockSize/associativity ; i++){
            sets.put(i ,new LinkedList<Block>()) ;
        }
//        System.out.println(sets);
    }

    public void doOrder(LoadStoreState state ,int address)
    {
        switch (state.toString()){
            case "dataLoad" :
                loadData(address);
                break;
            case "dataStore" :
                storeData(address);
                break;
            case "instructionLoad" :
                fetchInstruction(address);
                break;
        }

    }

    private void loadData(int address)
    {

    }

    private void storeData(int address)
    {
        if( writePolicy.toString().equals("writeTrough") && allocationPolicy.toString().equals("allocate") )
            WTvsWA(address);
        else if(writePolicy.toString().equals("writeTrough") && allocationPolicy.toString().equals("noAllocate") )
            WTvsWN(address);
        else if( writePolicy.toString().equals("writeBack") && allocationPolicy.toString().equals("allocate") )
            WBvsWA(address);
        else if( writePolicy.toString().equals("writeBack") && allocationPolicy.toString().equals("noAllocate") )
            WBvsWN(address);

    }
    private void WTvsWA(int address)
    {
        dataStatistics.increaseCopiesBack(1);
        if( isInCache(address)){
            dataStatistics.increaseHit();
            writeWordInCache(address);
        }
        else{
            dataStatistics.increaseMiss();
            writeBlockInCache(address);
        }
    }

    private void WTvsWN(int address)
    {
        dataStatistics.increaseCopiesBack(1);
        if( isInCache(address)){
            dataStatistics.increaseHit();
            writeWordInCache(address);
        }
        else{
            dataStatistics.increaseMiss();
        }
    }

    private void WBvsWA(int address)
    {
        setDirtyBlock(address);
        if( isInCache(address)) {
            dataStatistics.increaseHit();
        }
        else{
            dataStatistics.increaseMiss();
            writeBlockInCache(address);
        }

    }

    private void WBvsWN(int address){
        if( isInCache(address)) {
            dataStatistics.increaseHit();
            setDirtyBlock(address);
        }
        else{
            dataStatistics.increaseMiss();
            dataStatistics.increaseCopiesBack(1);
        }
    }
    //TODO : complete
    private void setDirtyBlock(int address)
    {

    }

    //TODO : complete
    private boolean isInCache(int address)
    {
        return false;
    }
    //TODO : complete
    private void writeBlockInCache(int address)
    {

    }
    //TODO : complete
    private void writeWordInCache(int address)
    {

    }


    //TODO : complete
    private void expulsionBlock(int address)
    {
        dataStatistics.increaseCopiesBack(blockSize/4);
    }

    //TODO : complete
    public void cleanUpCache()
    {
        /*
        check all dirty bit & if that block was dirty expulsion from cache
        use method expulsionBlock
         */
    }

    private void fetchInstruction(int address)
    {

    }





}


