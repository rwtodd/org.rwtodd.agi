package org.rwtodd.agi.resources;

/**
 * AGI V3 draws slightly different circles of size 1
 *
 * @author rwtodd
 */
public class V3CirclePen extends CirclePen {

    public V3CirclePen(int sz) {
        super(sz);
    }

    @Override
    protected int pixelstoPlot(int rownum) {
        if((size == 1)&&(rownum!=1)) return 0;
        return super.pixelstoPlot(rownum);
    }
}
