
package org.rwtodd.agi.resources;

/**
 * An exception when asked to load a resource that does not exist.
 * @author rwtodd
 */
public class ResourceNotPresentException extends AGIException {
    public ResourceNotPresentException(String desc, Throwable cause) {
        super(desc,cause);
    }
    
    public ResourceNotPresentException(String desc) {
        super(desc);
    }
}
