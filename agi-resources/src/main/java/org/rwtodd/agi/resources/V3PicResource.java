package org.rwtodd.agi.resources;

/**
 * V3 Pics are just like normal PicResources, except they use slightly different
 * CirclePens.
 * 
 * @author rwtodd
 */
public class V3PicResource extends PicResource {

    public V3PicResource(final byte[] src) {
        super(src);
    }

    @Override
    protected PicPen createCirclePen(int size) {
        return new V3CirclePen(size);
    }
}
