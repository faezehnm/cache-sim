package Component;

public class Word {
    private int blockNum ;
    private int index ;
    private int tag ;

    public Word(int blockNum , int cacheSIze){
        this.blockNum = blockNum ;
        this.index = blockNum%cacheSIze ;
        this.tag = blockNum/cacheSIze ;
    }

    public int getIndex() {
        return index;
    }

    public int getTag() {
        return tag;
    }
}
