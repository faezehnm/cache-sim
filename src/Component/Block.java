package Component;

import java.util.ArrayList;

public class Block {

    private int validBit ;
    private int dirtyBit ;
    private String tag ;

    public Block()
    {
        validBit = 0 ;
        dirtyBit = 0 ;
    }

    public int getValidBit() {
        return validBit;
    }

    public void setValidBit(int validBit) {
        this.validBit = validBit;
    }

    public int getDirtyBit() {
        return dirtyBit;
    }

    public void setDirtyBit(int dirtyBit) {
        this.dirtyBit = dirtyBit;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
