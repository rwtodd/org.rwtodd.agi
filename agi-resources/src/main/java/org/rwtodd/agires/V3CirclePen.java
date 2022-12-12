package org.rwtodd.agires;

/**
 * AGI V3 draws slightly different circles of size 1
 *
 * @author rwtodd
 */
class V3CirclePen extends CirclePen {

    @Override
    protected int pixelstoPlot(int rownum) {
        if((size == 1)&&(rownum!=1)) return 0;
        return super.pixelstoPlot(rownum);
    }
}
