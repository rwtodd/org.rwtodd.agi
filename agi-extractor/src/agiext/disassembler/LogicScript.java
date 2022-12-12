package agiext.disassembler;

import org.rwtodd.agires.AgiObject;

/**
 *
 * @author rwtodd
 */
public interface LogicScript {

    /**
     * Get the unsigned byte from the logic source at the given location.
     * @param location the location of the requested byte
     * @return the byte (unsigned, as an integer)
     */
    int getRawByte(int location);

    int getRawLength();

    String getScriptMessage(int num);

    Iterable<String> getWordGroup(int group);

    AgiObject getObject(int num);

    default String getFlagDescription(int flag) {
        return (flag < defaultFlags.length) ? defaultFlags[flag] : null;
    }

    default String getVariableDescription(int variable) {
        return (variable < defaultVars.length) ? defaultVars[variable] : null;
    }
    
    double getAGIVersion();

    default InstructionDecoder getInstructionDecoder() {
        return new InstructionDecoder(getAGIVersion());
    }

    /** flag descriptions common to most (all?) AGI games */
    static String[] defaultFlags = new String[]{
        "EGO base line is on pri-3 pixels (water surface)",
        "EGO is invisible (completely obscured)",
        "The player has issued a command line",
        "EGO base line has touched a pri-2 pixel (signal)",
        "'said' command has accepted user input",
        "The new room is executed for the first time",
        "'restart_game' command has been executed",
        "when 1, writing to script-buffer is blocked",
        "when 1, v15 determines joystick sensitivity",
        "sound on/off",
        "when 1, built-in debugger is on",
        "set when logic-0 is run for the first time",
        "'restore_game' command has been executed",
        "when 1, allows the 'status' command to select items",
        "when 1, allows the menu to work",
        "'print'/'print_at' modearg 0/close-on-<enter> 1/message-stays-up"
    };

    /** Variable descriptions common to most (all?) AGI games */
    static String[] defaultVars = new String[]{
        "Current room number",
        "Previous room number",
        "Border touched by EGO 0/none 1/top 2/right 3/bottom 4/left",
        "Current Score",
        "Number of object (other than EGO) touching the boarder",
        "The code of border touched by the object in v4",
        "Direction of EGO's motion 1/N 3/E 5/S 7/W 0/none",
        "Maximum score",
        "Free pages in memory",
        "if non-zero, number of the word which wasn't found",
        "Time delay in interpreter cycles in 1/20s",
        "Clock seconds",
        "Clock minutes",
        "Clock hours",
        "Clock days",
        "Joystick sensitivity",
        "ID of the view associated with EGO",
        "Interpreter error code",
        "Additional error info",
        "Key pressed on keyboard",
        "Computer type. IBM=0",
        "If f15=0 and v21 != 0, close window in 0.5*v21 secs",
        "Sound generator type 1/PC 3/Tandy",
        "Sound volume",
        "29h",
        "ID number of item selected using status cmd",
        "Graphics 0/CGA 2/Hercules 3/EGA"
    };
}
