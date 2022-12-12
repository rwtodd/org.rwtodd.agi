package org.rwtodd.agires;

/**
 * An exception when asked to load a resource that does not exist.
 * @author rwtodd
 */
public class ResourceNotPresentException extends AgiException {
    public ResourceNotPresentException(String desc, Throwable cause) {
        super(desc,cause);
    }
    
    public ResourceNotPresentException(String desc) {
        super(desc);
    }
}
