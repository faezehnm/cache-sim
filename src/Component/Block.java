package Component;
import java.util.ArrayList;

public class Block {

    private int validBit ;
    private int dirtyBit ;
    private String tag ;
    private String type;

    public Block(String tag , String type )
    {
        this.tag = tag ;
        this.type = type;
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

    public String getType() {
        return type;
    }
}
