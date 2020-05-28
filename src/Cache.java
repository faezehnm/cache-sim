import java.util.HashMap;
import java.util.LinkedList;

public class Cache {

    private int totalCacheSize ;
    private int blockSize ;
    private ArchitectureType architecture ;
    private int associativity;
    private WritePolicy writePolicy ;
    private HashMap<Integer , LinkedList<String>> sets ;

    public void setArchitecture(ArchitectureType architecture) {
        this.architecture = architecture;
    }
}
