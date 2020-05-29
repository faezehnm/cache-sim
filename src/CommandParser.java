import java.util.ArrayList;
import java.util.Scanner;

public class CommandParser {
    private static ArrayList<LoadStoreState> loadStoreStates;
    private static Cache cache ;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        cache = new Cache();
        readFirstLine(scanner);
        readSecondLine(scanner);
//        Output output = new Output(cache) ;
//        output.printCacheInfo();
        cache.buildCache();
        readOrders(scanner);
//        cache.cleanUpCache();
    }

    private static void readOrders(Scanner scanner)
    {
        String line = null ;
        while( !(line =scanner.nextLine()).isEmpty()){
            String[] arrOfStr = line.split("\\s+");
            cache.doOrder(readState(Integer.valueOf(arrOfStr[0])), Integer.valueOf(arrOfStr[1]));
        }

    }

    private static LoadStoreState readState(int state)
    {
        LoadStoreState res = null;
        switch (state) {
            case 0:
                res = LoadStoreState.dataLoad;
                break;
            case 1:
                res = LoadStoreState.dataStore;
                break;
            case 2:
                res = LoadStoreState.instructionLoad;
                break;
        }
        return res;
    }

    private static void addLoadStoreState(LoadStoreState state)
    {
        if( !loadStoreStates.contains(state))
            loadStoreStates.add(state) ;
    }

    private static void readFirstLine(Scanner scanner)
    {
       String line = scanner.nextLine();
       String[] arrOfStr = line.split(" - ");

       setArchitecture(arrOfStr[1]);
       cache.setBlockSize(Integer.valueOf(arrOfStr[0]));
       cache.setAssociativity(Integer.valueOf(arrOfStr[2]));
       setWritePolicy(arrOfStr[3]);
       setAllocationPolicy(arrOfStr[4]);
    }

    private static void setArchitecture(String architecture)
    {
        if( architecture.equals("0") )
            cache.setArchitecture(ArchitectureType.vonNeumann);
        else {
            cache.setArchitecture(ArchitectureType.harvard);
            cache = new ICache();
        }
    }

    private static void setWritePolicy(String writePolicy)
    {
        if( writePolicy.equals("wb"))
            cache.setWritePolicy(WritePolicy.writeBack);
        else
            cache.setWritePolicy(WritePolicy.writeTrough);
    }

    private static void setAllocationPolicy(String allocationPolicy)
    {
        if (allocationPolicy.equals("wa"))
            cache.setAllocationPolicy(AllocationPolicy.allocate);
        else
            cache.setAllocationPolicy(AllocationPolicy.noAllocate);
    }

    private static void readSecondLine(Scanner scanner)
    {
        String line = scanner.nextLine();
        String[] arrOfStr = line.split(" - ");
        cache.setdCacheSize(Integer.valueOf(arrOfStr[0]));
        if( arrOfStr.length==2 ){
            ICache iCache = (ICache)cache ;
            iCache.setiCacheSize(Integer.valueOf(arrOfStr[1]));
        }

    }
}
