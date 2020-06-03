package Operations;
import Component.Cache;

import java.nio.ByteBuffer;

public class Address {

    long block ;
    String tag ="";
    long set ;
    int decimal;
    String address;
    String bytes ;

    public Address(String str)
    {
        this.address = str;
        this.decimal = Integer.parseInt(str,16) ;
        this.block = decimal/(Cache.BaseInfo.blockSize) ;
        this.set =  block%Cache.BaseInfo.setNum;


        bytes = Integer.toBinaryString(decimal);


        for (int i = 0; i < bytes.length() - Cache.BaseInfo.bitSet - Cache.BaseInfo.bitDOffset; i++)
                tag += bytes.charAt(i);

    }

    public String getTag()
    {
        return tag;
    }

    public long getSet()
    {
        return set;
    }

    public void print()
    {
        System.out.println("address : " + address);
        System.out.println("set: " + set);
        System.out.println("tag: " + tag);

        System.out.println("{");
        System.out.println("  decimal : "+ decimal +" ,to dived " +Cache.BaseInfo.blockSize +", block is " +decimal/(Cache.BaseInfo.blockSize) + " ,set num is :" +Cache.BaseInfo.setNum+" , set is :" + set);
        System.out.println("}");
    }
}
