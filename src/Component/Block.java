package Component;

import java.util.ArrayList;

public class Block {
    private int address;
    private int validBit ;
    private int size ;
    private int wordsNum ;
    private int dirtyBit ;
    private ArrayList<Word> words ;

    public Block(int address , int size)
    {
        this.address = address ;
        this.size = size ;
        validBit = 0 ;
        words = new ArrayList<>();
        wordsNum = size/4 ;
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
}
