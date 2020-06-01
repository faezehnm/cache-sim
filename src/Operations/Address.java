package Operations;
import Component.Cache;

import java.nio.ByteBuffer;

public class Address {

    long block ;
    long tagBitNum ;
    String tag ="";
    long set ;
    int decimal;
    String address;
    String bytes ;

    Address( String str )
    {
        this.address = str;
        this.decimal = Integer.parseInt(str,16) ;
        this.block = decimal/(Cache.BaseInfo.blockSize/4) ;
        this.set =  block%Cache.BaseInfo.setNum;

//        System.out.println("{");
//        System.out.print(str );
//        System.out.println("  decimal : "+ decimal +" ,to dived " +Cache.BaseInfo.bitDOffset +", block is " +decimal/(Cache.BaseInfo.bitDOffset) + " ,set num is :" +Cache.BaseInfo.setNum+" , set is :" + set);
//        System.out.println("}");


         bytes = Integer.toBinaryString(decimal);

//        for( int i=0 ; i<bytes.length ; i++){
//            System.out.print(bytes[i]);
//        }
//        System.out.println();
//        System.out.println("{");
//        System.out.println(bytes.length);
//        System.out.println(Cache.BaseInfo.bitSet);
//        System.out.println(Cache.BaseInfo.bitDOffset);
//        System.out.println("}");
//
        int toAdd = 8 - str.length();
        System.out.println("fun" +toAdd);
        for (int i =0 ; i<toAdd ; i++){
            tag+="00000";
        }


        for (int i = 0; i < bytes.length() - Cache.BaseInfo.bitSet - Cache.BaseInfo.bitDOffset; i++) {
//        for ( int i=0 ; i<bytes.length ; i++ ){
//            System.out.println(i);
            tag += bytes.charAt(i);
        }
    }

    public String getTag() {
        return tag;
    }

    public long getSet() {
        return set;
    }

    public void print()
    {
        System.out.println("address : " + address);
        System.out.println("set: " + set);
        System.out.println("tag: " + tag);

        System.out.println("{");
        System.out.println("  decimal : "+ decimal +" ,to dived " +Cache.BaseInfo.bitDOffset +", block is " +decimal/(Cache.BaseInfo.bitDOffset) + " ,set num is :" +Cache.BaseInfo.setNum+" , set is :" + set);
        System.out.println("}");

        System.out.println("bites : " +bytes );
    }
}
