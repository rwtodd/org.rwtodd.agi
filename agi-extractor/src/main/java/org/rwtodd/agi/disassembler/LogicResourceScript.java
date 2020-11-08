package org.rwtodd.agi.disassembler;

import java.util.List;
import org.rwtodd.agi.extractor.ObjectDictionary;
import org.rwtodd.agi.extractor.WordDictionary;
import org.rwtodd.agi.resources.LogicResource;

/**
 *
 * @author rwtodd
 */
public class LogicResourceScript implements LogicScript {
    private final InstructionFactory ifactory;
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
        ifactory = new InstructionFactory(agiVersion);
        resource = lr;
    }
    
    public void setResource(final LogicResource lr) {
        resource = lr;
    }
    
    @Override
    public int getRawByte(int location) {
        return resource.getByteCodes()[location];
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
    public InstructionFactory getInstructionFactory() {
        return ifactory;
    }
    
}
