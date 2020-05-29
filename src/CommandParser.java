import java.util.ArrayList;
import java.util.Scanner;

public class CommandParser {
    private static ArrayList<LoadStoreState> loadStoreStates;
    private static Cache cache ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        cache = new Cache();
        readFirstLine(scanner);
        readSecondLine(scanner);
        Output output = new Output(cache) ;
        output.printCacheInfo();
//        readLine(scanner);
    }

    private static void readLine(Scanner scanner)
    {
        readState(scanner);
        readMemmoryAddress(scanner);
    }

    private static void readState(Scanner scanner)
    {
        int state = scanner.nextInt();
        switch (state) {
            case 0:
                addLoadStoreState(LoadStoreState.dataLoad);
                break;
            case 1:
                addLoadStoreState(LoadStoreState.dataStore);
                break;
            case 2:
                addLoadStoreState(LoadStoreState.instructionLoad);
                break;
        }
    }

    private static void readMemmoryAddress(Scanner scanner)
    {
        int memAddress = scanner.nextInt() ;

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

       if( arrOfStr[1].equals("0") )
           cache.setArchitecture(ArchitectureType.vonNeumann);
       else {
           cache.setArchitecture(ArchitectureType.harvard);
           cache = new ICache();
       }
       cache.setBlockSize(Integer.valueOf(arrOfStr[0]));
       cache.setAssociativity(Integer.valueOf(arrOfStr[2]));
       setWritePolicy(arrOfStr[3]);
       setAllocationPolicy(arrOfStr[4]);

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
