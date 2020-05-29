import java.util.HashMap;
import java.util.LinkedList;

public class Cache {

    private int blockSize ;
    private ArchitectureType architecture ;
    private int associativity;
    private WritePolicy writePolicy ;
    private AllocationPolicy allocationPolicy;
    private HashMap<Integer , LinkedList<String>> sets ;
    private int dCacheSize ;

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

    public void setSets(HashMap<Integer, LinkedList<String>> sets) {
        this.sets = sets;
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

    public HashMap<Integer, LinkedList<String>> getSets() {
        return sets;
    }

    public int getdCacheSize() {
        return dCacheSize;
    }

    public AllocationPolicy getAllocationPolicy() {
        return allocationPolicy;
    }

    public void checkAddress(int state ,int address)
    {

    }

}


