import Component.Cache;
import Component.ICache;
import Enums.AllocationPolicy;
import Enums.ArchitectureType;
import Enums.LoadStoreState;
import Enums.WritePolicy;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandParser {
    private static ArrayList<LoadStoreState> loadStoreStates;
    private static Cache cache ;
    private static ICache iCache;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        cache = new Cache();
        readFirstLine(scanner);
        readSecondLine(scanner);
        Cache.BaseInfo.setbitDOffset();
        Output output = new Output(cache) ;
        cache.buildCache();
        readOrders(scanner);
        output.printOutput();
//        cache.cleanUpCache();
    }

    private static void readOrders(Scanner scanner)
    {
        String line = null ;
        while( !(line =scanner.nextLine()).isEmpty()){
            String[] arrOfStr = line.split("\\s+");
            if(arrOfStr[0].equals("2")){
                iCache.fetchInstruction(arrOfStr[1]);
            }
            else
                cache.doOrder(readState(Integer.valueOf(arrOfStr[0])), arrOfStr[1]);
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

    private static void readFirstLine(Scanner scanner)
    {
       String line = scanner.nextLine();
       String[] arrOfStr = line.split(" - ");

       setArchitecture(arrOfStr[1]);
       Cache.BaseInfo.blockSize =(Integer.valueOf(arrOfStr[0]));
       Cache.BaseInfo.associativity =(Integer.valueOf(arrOfStr[2]));
       setWritePolicy(arrOfStr[3]);
       setAllocationPolicy(arrOfStr[4]);
    }

    private static void setArchitecture(String architecture)
    {
        if( architecture.equals("0") )
            Cache.BaseInfo.architecture = (ArchitectureType.vonNeumann);
        else {
            Cache.BaseInfo.architecture = (ArchitectureType.harvard);
            cache = new ICache();
        }
    }

    private static void setWritePolicy(String writePolicy)
    {
        if( writePolicy.equals("wb"))
            Cache.BaseInfo.writePolicy = (WritePolicy.writeBack);
        else
            Cache.BaseInfo.writePolicy =(WritePolicy.writeTrough);
    }

    private static void setAllocationPolicy(String allocationPolicy)
    {
        if (allocationPolicy.equals("wa"))
            Cache.BaseInfo.allocationPolicy =(AllocationPolicy.allocate);
        else
            Cache.BaseInfo.allocationPolicy =(AllocationPolicy.noAllocate);
    }

    private static void readSecondLine(Scanner scanner)
    {
        String line = scanner.nextLine();
        String[] arrOfStr = line.split(" - ");
        cache.dCacheSize = (Integer.valueOf(arrOfStr[0]));
        if( arrOfStr.length==2 ){
            iCache = (ICache)cache ;
            iCache.iCacheSize = (Integer.valueOf(arrOfStr[1]));
        }

    }
}
