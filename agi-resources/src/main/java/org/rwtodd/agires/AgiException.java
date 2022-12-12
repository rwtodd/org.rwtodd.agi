package org.rwtodd.agires;

/**
 * An exception when trying to load or parse AGI Resources
 * @author rwtodd
 */
public class AgiException extends Exception {
    public AgiException(String desc, Throwable cause) {
        super(desc,cause);
    }
    
    public AgiException(String desc) {
        super(desc);
    }
}
