import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class CommandParser {
    private static ArrayList<LoadStoreState> loadStoreStates;
    private static Cache cache ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        cache = new Cache();
        readFirstLine(scanner);
        readSecondLine(scanner);
        readLine(scanner);
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

//    private static void setHardWareType()
//    {
//        if( loadStoreStates.contains(LoadStoreState.instructionLoad))
//            cache.setArchitecture(ArchitectureType.harvard);
//        else
//            cache.setArchitecture(ArchitectureType.vonNeumann);
//
//    }

    private static void readFirstLine(Scanner scanner)
    {
       String line = scanner.nextLine();
       String[] arrOfStr = line.split(" - ");
       cache.setBlockSize(Integer.valueOf(arrOfStr[0]));
       if( arrOfStr[1].equals("0") )
           cache.setArchitecture(ArchitectureType.vonNeumann);
       else
           cache.setArchitecture(ArchitectureType.harvard);
       cache.setAssociativity(Integer.valueOf(arrOfStr[2]));
       if( arrOfStr[3].equals("wb"))
           cache.setWritePolicy(WritePolicy.writeBack);
       else
           cache.setWritePolicy(WritePolicy.writeTrough);
       if (arrOfStr[4].equals("wa"))
           cache.setWriteMissPolicy(WriteMissPolicy.allocate);
       else
           cache.setWriteMissPolicy(WriteMissPolicy.noAllocate);

    }

    private static void readSecondLine(Scanner scanner)
    {
        String line = scanner.nextLine();
        String[] arrOfStr = line.split(" - ");
        cache.setdCacheSize(Integer.valueOf(arrOfStr[0]));
        if( arrOfStr.length==2 ){
            ICache iCache = (ICache)cache ;
            iCache.setiCacheSize(Integer.valueOf(arrOfStr[1]));
            iCache.setArchitecture(ArchitectureType.harvard);
        }
        else
            cache.setArchitecture(ArchitectureType.vonNeumann);


    }
}
