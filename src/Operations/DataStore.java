package Operations;

import Component.Block;
import Component.Cache;

import java.util.Map;

public class DataStore {

    static class WriteState
    {
        protected static void WTvsWA(String address)
        {
            Cache.dataStatistics.increaseCopiesBack(1);
            if( isDataInCache(address)){
                Cache.dataStatistics.increaseHit();
                MangeCache.writeBlockInCache(address);
            }
            else{
                Cache.dataStatistics.increaseMiss();
                MangeCache.writeBlockInCache(address);
            }
        }

        protected static void WTvsWN(String address)
        {
            Cache.dataStatistics.increaseCopiesBack(1);
            if( isDataInCache(address)){
                Cache.dataStatistics.increaseHit();
                MangeCache.writeBlockInCache(address);
            }
            else{
                Cache.dataStatistics.increaseMiss();
            }
        }

        protected static void WBvsWA(String address)
        {
            MangeCache.setDirtyBlock(address);
            if( isDataInCache(address)) {
                Cache.dataStatistics.increaseHit();
            }
            else{
                Cache.dataStatistics.increaseMiss();
                MangeCache.writeBlockInCache(address);
            }

        }

        protected static void WBvsWN(String address)
        {
            if( isDataInCache(address)) {
                Cache.dataStatistics.increaseHit();
                MangeCache.setDirtyBlock(address);
            }
            else{
                Cache.dataStatistics.increaseMiss();
                Cache.dataStatistics.increaseCopiesBack(1);
            }
        }
    }

    static class MangeCache
    {
        private static void writeBlockInCache(String address)
        {
            Address current = new Address(address);
            Block block = new Block();
            block.setTag(current.getTag());
            block.setValidBit(1);
            Cache.dSets.get(current.getSet()).addBlock(block);
        }

        private static void setDirtyBlock(String address)
        {
            Address current = new Address(address);
            Cache.dSets.get(current.getSet()).findBlock(current.getTag()).setDirtyBit(1) ;
        }

        private void expulsionBlock(String address)
        {
            Cache.dataStatistics.increaseCopiesBack(Cache.BaseInfo.blockSize/4);
            Address current = new Address(address);
            Block block = new Block();
            block.setTag(current.getTag());
            block.setValidBit(1);
            Cache.dSets.get(current.getSet()).replaceBlock(block);
            setDirtyBlock(address);
        }
    }

    static class Address
    {
        long block ;
        long tagBitNum ;
        String tag;
        int set ;

        Address( String str )
        {
            int decimal = Integer.parseInt(str,16) ;
            this.block = decimal%(Cache.BaseInfo.blockSize/4) ;
            this.set = (int) block%Cache.dSets.size();
            this.tagBitNum = Integer.toBinaryString(decimal).length() - Integer.toBinaryString(this.set).length() - Cache.BaseInfo.dOffset ;
            for( int i=0 ; i< tagBitNum ; i++ ){
                tag += Integer.toBinaryString(decimal).charAt(i);
            }
        }

        public String getTag() {
            return tag;
        }

        public long getSet() {
            return set;
        }

    }

    public static void storeData(String address)
    {
        switch (Cache.BaseInfo.writePolicy.toString()){
            case "writeTrough" :
                switch (Cache.BaseInfo.allocationPolicy.toString()){
                    case "allocate" :
                        WriteState.WTvsWA(address);
                        break;
                    case "noAllocate" :
                        WriteState.WTvsWN(address);
                        break;
                }
                break;

            case "writeBack" :
                switch (Cache.BaseInfo.allocationPolicy.toString()){
                    case "allocate" :
                        WriteState.WBvsWA(address);
                        break;
                    case "noAllocate" :
                        WriteState.WBvsWN(address);
                        break;
                }
                break;
        }

    }

    private static boolean isDataInCache(String address)
    {
        Address current = new Address(address) ;

        for (Block block : Cache.dSets.get(current.getSet()).getBlocks() ){
            if( block.getValidBit()== 1 && block.getTag().equals(current.getTag()))
                return true;
        }

        return false;
    }

}
