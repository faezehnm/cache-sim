import java.util.HashMap;
import java.util.LinkedList;

public class Cache {

    private int totalCacheSize ;
    private int blockSize ;
    private ArchitectureType architecture ;
    private int associativity;
    private WritePolicy writePolicy ;
    private WriteMissPolicy writeMissPolicy;
    private HashMap<Integer , LinkedList<String>> sets ;
    private int dCacheSize ;

    public void setArchitecture(ArchitectureType architecture) {
        this.architecture = architecture;
    }

    public void setTotalCacheSize(int totalCacheSize) {
        this.totalCacheSize = totalCacheSize;
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

    public void setWriteMissPolicy(WriteMissPolicy writeMissPolicy) {
        this.writeMissPolicy = writeMissPolicy;
    }

    public void setdCacheSize(int dCacheSize) {
        this.dCacheSize = dCacheSize;
    }
}


