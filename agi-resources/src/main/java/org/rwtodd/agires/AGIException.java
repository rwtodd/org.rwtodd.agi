package org.rwtodd.agires;

/**
 * An exception when trying to load or parse AGI Resources
 * @author rwtodd
 */
public class AGIException extends Exception {
    public AGIException(String desc, Throwable cause) {
        super(desc,cause);
    }
    
    public AGIException(String desc) {
        super(desc);
    }
}
