package Operations;
import Component.Cache;

import java.nio.ByteBuffer;

public class Address {

    long block ;
    long tagBitNum ;
    String tag ="";
    int set ;

    Address( String str )
    {
        int decimal = Integer.parseInt(str,16) ;
        this.block = decimal%(Cache.BaseInfo.blockSize/4) ;
        this.set = (int) block%Cache.dSets.size();
        byte[] bytes = ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(decimal).array();

        for( int i=0 ; i< bytes.length- Cache.BaseInfo.bitSet - Cache.BaseInfo.bitDOffset ; i++ )
            tag += bytes[i];
    }

    public String getTag() {
        return tag;
    }

    public int getSet() {
        return set;
    }

    public void print()
    {
        System.out.println("address :");
        System.out.println("set: " + set);
        System.out.println("tag: " + tag);
    }
}
