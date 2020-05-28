import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class CommandParser {
    private ArrayList<LoadStoreState> loadStoreStates;
    private Cache cache ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    }

    private void readLine(Scanner scanner)
    {
        readState(scanner);
        readMemmoryAddress(scanner);
    }

    private void readState(Scanner scanner)
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

    private void readMemmoryAddress(Scanner scanner)
    {
        int memAddress = scanner.nextInt() ;

    }
    private void addLoadStoreState(LoadStoreState state)
    {
        if( !loadStoreStates.contains(state))
            loadStoreStates.add(state) ;
    }

    private void setHardWareType()
    {
        if( loadStoreStates.contains(LoadStoreState.instructionLoad))
            cache.setArchitecture(ArchitectureType.harvard);
        else
            cache.setArchitecture(ArchitectureType.vonNeumann);

    }
}
