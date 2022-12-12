package agiext.disassembler;

import java.util.List;
import agiext.extractor.ObjectDictionary;
import agiext.extractor.WordDictionary;
import org.rwtodd.agires.LogicResource;

/**
 *
 * @author rwtodd
 */
public class LogicResourceScript implements LogicScript {
    private final InstructionDecoder idecoder;
    private final double agiVersion;
    private final WordDictionary words;
    private final ObjectDictionary objects;
    private LogicResource resource;
    
    public LogicResourceScript(double agiVersion, WordDictionary wd, ObjectDictionary od) {
        this(agiVersion, wd, od, null);
    }
    
    public LogicResourceScript(double agiVersion, WordDictionary wd, ObjectDictionary od, LogicResource lr) {
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
    public List<String> getWordGroup(int group) {
        return words.lookupGroup(group);
    }

    @Override
    public ObjectDictionary.Entry getObject(int num) {
        return objects.get(num);
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
