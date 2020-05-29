package Component;

public class CacheStatistics {
    private long access ;
    private long misses ;
    private long hits ;
    private float missRate ;
    private float hitRate ;
    private long replaceNum ;
    private long demandFetch ;
    private long copiesBack ;

    public CacheStatistics()
    {
        access = 0 ;
        misses = 0 ;
        hits = 0 ;
        missRate = (float) 0.0;
        hitRate = (float) 0.0 ;
        replaceNum =0 ;
    }

    public void increaseMiss()
    {
        access++ ;
        misses ++ ;
    }

    public void increaseHit()
    {
        access++ ;
        hits++ ;
    }

    public void increaseReplaceNum()
    {
        replaceNum++;
    }

    public void increaseDemandFetch()
    {
        demandFetch++;
    }

    public void increaseCopiesBack(int toIncrease)
    {
        copiesBack += toIncrease;
    }

    public void calculateMissRate()
    {
        missRate =(float) misses/access ;
    }

    public void calculateHitRate()
    {
        hitRate =(float) hits/access ;
    }

    public long getAccess() {
        return access;
    }

    public long getMisses() {
        return misses;
    }

    public long getHits() {
        return hits;
    }

    public double getMissRate() {
        calculateMissRate();
        double valueRounded = Math.round(missRate * 10000D) / 10000D;
        return valueRounded;
    }

    public double getHitRate() {
        calculateHitRate();
        double valueRounded = Math.round(missRate * 10000D) / 10000D;
        return valueRounded;
    }

    public long getReplaceNum() {
        return replaceNum;
    }

    public long getDemandFetch() {
        return demandFetch;
    }

    public long getCopiesBack() {
        return copiesBack;
    }
}
