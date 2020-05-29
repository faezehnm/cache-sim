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

    }

    private void fetchInstruction(int address)
    {

    }

}


