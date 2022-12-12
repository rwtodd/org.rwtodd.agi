package agiext.disassembler;

import java.util.List;
import org.rwtodd.agires.AgiDictionary;
import org.rwtodd.agires.AgiObject;
import org.rwtodd.agires.LogicResource;

/**
 *
 * @author rwtodd
 */
public class LogicResourceScript implements LogicScript {
    private final InstructionDecoder idecoder;
    private final double agiVersion;
    private final AgiDictionary words;
    private final List<AgiObject> objects;
    private LogicResource resource;
    
    public LogicResourceScript(double agiVersion, AgiDictionary wd, List<AgiObject> od) {
        this(agiVersion, wd, od, null);
    }
    
    public LogicResourceScript(double agiVersion, AgiDictionary wd, List<AgiObject> od, LogicResource lr) {
        this.agiVersion = agiVersion;
        words = wd;
        objects = od;
        idecoder = new InstructionDecoder(agiVersion);
        resource = lr;
    }
    
    public void setResource(final LogicResource lr) {
        resource = lr;
    }
    
    @Override
    public int getRawByte(int location) {
        return (resource.getByteCodes()[location] & 0xff);
    }

    @Override
    public int getRawLength() {
        return resource.getByteCodes().length;
    }

    @Override
    public String getScriptMessage(int num) {
        return resource.getMessage(num);
    }

    @Override
    public Iterable<String> getWordGroup(int group) {
        return words.idToWords(group);
    }

    @Override
    public AgiObject getObject(int num) {
        AgiObject result = objects.get(num);
        return (result != null) ? result : new AgiObject("Non-existent Object!", -1);
    }

    @Override
    public double getAGIVersion() {
        return agiVersion;
    }

    @Override
    public InstructionDecoder getInstructionDecoder() {
        return idecoder;
    }
    
}
