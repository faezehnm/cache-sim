package Analysis;

import Component.Cache;
import Component.ICache;
import Enums.AllocationPolicy;
import Enums.ArchitectureType;
import Enums.LoadStoreState;
import Enums.WritePolicy;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Analysis {

    public static void main(String[] args) throws IOException {
        File dataIn = new File("dataIn.txt");
        File instructionIn = new File("instructionIn.txt");
        File dataOut = new File("dataOut.txt");
        File instructionOut = new File("instructionOut.txt");


        ICache iCache = new ICache() ;
        Cache.BaseInfo.architecture = (ArchitectureType.harvard);
        ICache.BaseInfo.writePolicy = (WritePolicy.writeBack);
        ICache.BaseInfo.allocationPolicy =(AllocationPolicy.allocate);
        ICache.BaseInfo.blockSize =(4);

        double last = 0.0;
        ICache.dCacheSize = 8*1024;
        ICache.iCacheSize = 8*1024;
        ICache.BaseInfo.associativity =(2);
        ICache.BaseInfo.setbitDOffset();
        iCache.buildCache();
        readOrders(iCache);
//        System.out.println(ICache.dataStatistics.getMissRateD());
//        saveData(ICache.dCacheSize , ICache.dataStatistics.getHitRateD() ,dataIn,dataOut );

        saveData(ICache.BaseInfo.blockSize,ICache.instructionStatistics.getHitRateD(),dataIn,dataOut);


//        while ( ICache.instructionStatistics.getHitRateD() - last > 0.01) {
        for( int i=0 ; i<3 ; i++){
            System.out.println("hey");
            last = ICache.instructionStatistics.getHitRateD() ;
            ICache.iCacheSize*=2 ;
            ICache.dCacheSize*=2 ;
            ICache.BaseInfo.associativity =(ICache.iCacheSize/4);
            ICache.BaseInfo.setbitDOffset();
            iCache.buildCache();
            readOrders(iCache);
            saveData(ICache.iCacheSize,ICache.instructionStatistics.getHitRateD(),dataIn,dataOut);
        }

//        while (  ICache.dataStatistics.getHitRateD() - last > 0.01) {
//            System.out.println("hey");
//            last = ICache.dataStatistics.getHitRateD() ;
//            ICache.dCacheSize *=2 ;
//            ICache.BaseInfo.associativity =(ICache.dCacheSize/4);
//            ICache.BaseInfo.setbitDOffset();
//            iCache.buildCache();
//            readOrders(iCache);
//            saveData(ICache.dCacheSize , ICache.dataStatistics.getHitRateD() ,dataIn,dataOut );
//        }
//
//        last = 0.0;

//
//        while ( ICache.instructionStatistics.getHitRateD() - last > 0.01) {
//            last = ICache.instructionStatistics.getHitRateD() ;
//            ICache.iCacheSize*=2 ;
//            ICache.BaseInfo.associativity =(ICache.iCacheSize/4);
//            readOrders(iCache);
//            saveInstruction(ICache.iCacheSize,ICache.instructionStatistics.getHitRateD(),instructionIn,instructionOut);
//        }



    }

    public static void saveData(int dataIn , double dataOut ,  File fdataIn  ,File fdataOut  ) throws IOException {
        System.out.println("in saving ...");
        System.out.println(dataIn);
        System.out.println(dataOut);
        FileWriter myWriter = new FileWriter(fdataIn ,true);
        FileWriter myWriter2 = new FileWriter(fdataOut,true);
        myWriter.write(dataIn +"\n");
        myWriter2.write(dataOut+"\n");
        myWriter.close();
        myWriter2.close();
    }


    public static void readOrders( ICache iCache) throws IOException
    {
        File file=new File("src\\spise.txt");
        FileReader fr=new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
        StringBuffer sb=new StringBuffer();
        String line;
        while((line=br.readLine())!=null)
        {
            String[] arrOfStr = line.split("\\s+");
            if(arrOfStr[0].equals("2") && Cache.BaseInfo.architecture.toString().equals("harvard")){
                iCache.fetchInstruction(arrOfStr[1]);
            }
            else
                iCache.doOrder(readState(Integer.valueOf(arrOfStr[0])), arrOfStr[1]);
        }
        fr.close();

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
}
