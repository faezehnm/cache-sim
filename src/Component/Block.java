package Component;

import java.util.ArrayList;

public class Block {

    private int validBit ;
    private int dirtyBit ;
    private String tag ;

    public Block(String tag)
    {
        this.tag = tag ;
        validBit = 1 ;
        dirtyBit = 0 ;
    }
    public void setDirtyBit(int dirtyBit) {
        this.dirtyBit = dirtyBit;
    }

    public int getValidBit() {
        return validBit;
    }

    public int getDirtyBit() {
        return dirtyBit;
    }

    public String getTag() {
        return tag;
    }

}
