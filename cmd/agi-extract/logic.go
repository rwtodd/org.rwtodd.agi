package main

import (
	"bufio"
	"bytes"
	"errors"
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"

	"github.com/rwtodd/agi-tools/agi"
)

var variableInfo = [...]string{
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
	"Graphics 0/CGA 2/Hercules 3/EGA",
}

var flagInfo = [...]string{
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
	"'print'/'print_at' modearg 0/close-on-<enter> 1/message-stays-up",
}

type argType uint8

const (
	argUnk argType = iota
	argNum
	argObj
	argCtl
	argVar
	argInv
	argTok
	argFlg
	argStr
	argMsg
	argWrd
)

var argTypePrefixes = []string{"", "", "%o", "%c", "%v", "%i", "%t", "%f", "%s", "%m", "%w"}

type agiByteCode struct {
	name     string
	argTypes [7]argType
	argCount uint8
}

var agiByteCodes = [...]agiByteCode{
	agiByteCode{"return", [7]argType{}, 0}, // 0
	agiByteCode{"increment", [7]argType{argVar}, 1},
	agiByteCode{"decrement", [7]argType{argVar}, 1},
	agiByteCode{"assignn", [7]argType{argVar, argNum}, 2},
	agiByteCode{"assignv", [7]argType{argVar, argVar}, 2},
	agiByteCode{"addn", [7]argType{argVar, argNum}, 2}, // 5
	agiByteCode{"addv", [7]argType{argVar, argVar}, 2},
	agiByteCode{"subn", [7]argType{argVar, argNum}, 2},
	agiByteCode{"subv", [7]argType{argVar, argVar}, 2},
	agiByteCode{"lindirectv", [7]argType{argVar, argVar}, 2},
	agiByteCode{"rindirect", [7]argType{argVar, argVar}, 2}, // 10
	agiByteCode{"lindirectn", [7]argType{argVar, argNum}, 2},
	agiByteCode{"set", [7]argType{argFlg}, 1},
	agiByteCode{"reset", [7]argType{argFlg}, 1},
	agiByteCode{"toggle", [7]argType{argFlg}, 1},
	agiByteCode{"set.v", [7]argType{argVar}, 1}, // 15
	agiByteCode{"reset.v", [7]argType{argVar}, 1},
	agiByteCode{"toggle.v", [7]argType{argVar}, 1},
	agiByteCode{"new.room", [7]argType{argNum}, 1},
	agiByteCode{"new.room.v", [7]argType{argVar}, 1},
	agiByteCode{"load.logics", [7]argType{argNum}, 1}, // 20
	agiByteCode{"load.logics.v", [7]argType{argVar}, 1},
	agiByteCode{"call", [7]argType{argNum}, 1},
	agiByteCode{"call.v", [7]argType{argVar}, 1},
	agiByteCode{"load.pic", [7]argType{argVar}, 1},
	agiByteCode{"draw.pic", [7]argType{argVar}, 1}, // 25
	agiByteCode{"show.pic", [7]argType{}, 0},
	agiByteCode{"discard.pic", [7]argType{argVar}, 1},
	agiByteCode{"overlay.pic", [7]argType{argVar}, 1},
	agiByteCode{"show.pri.screen", [7]argType{}, 0},
	agiByteCode{"load.view", [7]argType{argNum}, 1}, // 30
	agiByteCode{"load.view.v", [7]argType{argVar}, 1},
	agiByteCode{"discard.view", [7]argType{argNum}, 1},
	agiByteCode{"animate.obj", [7]argType{argObj}, 1},
	agiByteCode{"unanimate.all", [7]argType{}, 0},
	agiByteCode{"draw", [7]argType{argObj}, 1}, // 35
	agiByteCode{"erase", [7]argType{argObj}, 1},
	agiByteCode{"position", [7]argType{argObj, argNum, argNum}, 3},
	agiByteCode{"position.v", [7]argType{argObj, argVar, argVar}, 3},
	agiByteCode{"get.posn", [7]argType{argObj, argVar, argVar}, 3},
	agiByteCode{"reposition", [7]argType{argObj, argVar, argVar}, 3}, // 40
	agiByteCode{"set.view", [7]argType{argObj, argNum}, 2},
	agiByteCode{"set.view.v", [7]argType{argObj, argVar}, 2},
	agiByteCode{"set.loop", [7]argType{argObj, argNum}, 2},
	agiByteCode{"set.loop.v", [7]argType{argObj, argVar}, 2},
	agiByteCode{"fix.loop", [7]argType{argObj}, 1}, // 45
	agiByteCode{"release.loop", [7]argType{argObj}, 1},
	agiByteCode{"set.cel", [7]argType{argObj, argNum}, 2},
	agiByteCode{"set.cel.v", [7]argType{argObj, argVar}, 2},
	agiByteCode{"last.cel", [7]argType{argObj, argVar}, 2},
	agiByteCode{"current.cel", [7]argType{argObj, argVar}, 2}, // 50
	agiByteCode{"current.loop", [7]argType{argObj, argVar}, 2},
	agiByteCode{"current.view", [7]argType{argObj, argVar}, 2},
	agiByteCode{"number.of.loops", [7]argType{argObj, argVar}, 2},
	agiByteCode{"set.priority", [7]argType{argObj, argNum}, 2},
	agiByteCode{"set.priority.v", [7]argType{argObj, argVar}, 2}, // 55
	agiByteCode{"release.priority", [7]argType{argObj}, 1},
	agiByteCode{"get.priority", [7]argType{argObj, argVar}, 2},
	agiByteCode{"stop.update", [7]argType{argObj}, 1},
	agiByteCode{"start.update", [7]argType{argObj}, 1},
	agiByteCode{"force.update", [7]argType{argObj}, 1}, // 60
	agiByteCode{"ignore.horizon", [7]argType{argObj}, 1},
	agiByteCode{"observe.horizon", [7]argType{argObj}, 1},
	agiByteCode{"set.horizon", [7]argType{argNum}, 1},
	agiByteCode{"object.on.water", [7]argType{argObj}, 1},
	agiByteCode{"object.on.land", [7]argType{argObj}, 1}, // 65
	agiByteCode{"object.on.anything", [7]argType{argObj}, 1},
	agiByteCode{"ignore.objs", [7]argType{argObj}, 1},
	agiByteCode{"observe.objs", [7]argType{argObj}, 1},
	agiByteCode{"distance", [7]argType{argObj, argObj, argVar}, 3},
	agiByteCode{"stop.cycling", [7]argType{argObj}, 1}, // 70
	agiByteCode{"start.cycling", [7]argType{argObj}, 1},
	agiByteCode{"normal.cycle", [7]argType{argObj}, 1},
	agiByteCode{"end.of.loop", [7]argType{argObj, argFlg}, 2},
	agiByteCode{"reverse.cycle", [7]argType{argObj}, 1},
	agiByteCode{"reverse.loop", [7]argType{argObj, argFlg}, 2}, // 75
	agiByteCode{"cycle.time", [7]argType{argObj, argVar}, 2},
	agiByteCode{"stop.motion", [7]argType{argObj}, 1},
	agiByteCode{"start.motion", [7]argType{argObj}, 1},
	agiByteCode{"step.size", [7]argType{argObj, argVar}, 2},
	agiByteCode{"step.time", [7]argType{argObj, argVar}, 2}, // 80
	agiByteCode{"move.obj", [7]argType{argObj, argNum, argNum, argNum, argFlg}, 5},
	agiByteCode{"move.obj.v", [7]argType{argObj, argVar, argVar, argNum, argFlg}, 5},
	agiByteCode{"follow.ego", [7]argType{argObj, argNum, argFlg}, 3},
	agiByteCode{"wander", [7]argType{argObj}, 1},
	agiByteCode{"normal.motion", [7]argType{argObj}, 1}, // 85
	agiByteCode{"set.dir", [7]argType{argObj, argVar}, 2},
	agiByteCode{"get.dir", [7]argType{argObj, argVar}, 2},
	agiByteCode{"ignore.blocks", [7]argType{argObj}, 1},
	agiByteCode{"observe.blocks", [7]argType{argObj}, 1},
	agiByteCode{"block", [7]argType{argNum, argNum, argNum, argNum}, 4}, // 90
	agiByteCode{"unblock", [7]argType{}, 0},
	agiByteCode{"get", [7]argType{argInv}, 1},
	agiByteCode{"get.v", [7]argType{argVar}, 1},
	agiByteCode{"drop", [7]argType{argInv}, 1},
	agiByteCode{"put", [7]argType{argInv, argVar}, 2}, // 95
	agiByteCode{"put.v", [7]argType{argVar, argVar}, 2},
	agiByteCode{"get.room.v", [7]argType{argVar, argVar}, 2},
	agiByteCode{"load.sound", [7]argType{argNum}, 1},
	agiByteCode{"sound", [7]argType{argNum, argFlg}, 2},
	agiByteCode{"stop.sound", [7]argType{}, 0}, // 100
	agiByteCode{"print", [7]argType{argMsg}, 1},
	agiByteCode{"print.v", [7]argType{argVar}, 1},
	agiByteCode{"display", [7]argType{argNum, argNum, argMsg}, 3},
	agiByteCode{"display.v", [7]argType{argVar, argVar, argVar}, 3},
	agiByteCode{"clear.lines", [7]argType{argNum, argNum, argNum}, 3}, // 105
	agiByteCode{"text.screen", [7]argType{}, 0},
	agiByteCode{"graphics", [7]argType{}, 0},
	agiByteCode{"set.cursor.char", [7]argType{argMsg}, 1},
	agiByteCode{"set.text.attribute", [7]argType{argNum, argNum}, 2},
	agiByteCode{"shake.screen", [7]argType{argNum}, 1}, // 110
	agiByteCode{"configure.screen", [7]argType{argNum, argNum, argNum}, 3},
	agiByteCode{"status.line.on", [7]argType{}, 0},
	agiByteCode{"status.line.off", [7]argType{}, 0},
	agiByteCode{"set.string", [7]argType{argStr, argMsg}, 2},
	agiByteCode{"get.string", [7]argType{argStr, argMsg, argNum, argNum, argNum}, 5}, // 115
	agiByteCode{"word.to.string", [7]argType{argWrd, argStr}, 2},
	agiByteCode{"parse", [7]argType{argStr}, 1},
	agiByteCode{"get.num", [7]argType{argMsg, argVar}, 2},
	agiByteCode{"prevent.input", [7]argType{}, 0},
	agiByteCode{"accept.input", [7]argType{}, 0}, // 120
	agiByteCode{"set.key", [7]argType{argNum, argNum, argCtl}, 3},
	agiByteCode{"add.to.pic", [7]argType{argNum, argNum, argNum, argNum, argNum, argNum, argNum}, 7},
	agiByteCode{"add.to.pic.v", [7]argType{argVar, argVar, argVar, argVar, argVar, argVar, argVar}, 7},
	agiByteCode{"status", [7]argType{}, 0},
	agiByteCode{"save.game", [7]argType{}, 0}, // 125
	agiByteCode{"restore.game", [7]argType{}, 0},
	agiByteCode{"init.disk", [7]argType{}, 0},
	agiByteCode{"restart.game", [7]argType{}, 0},
	agiByteCode{"show.obj", [7]argType{argNum}, 1},
	agiByteCode{"random", [7]argType{argNum, argNum, argVar}, 3}, // 130
	agiByteCode{"program.control", [7]argType{}, 0},
	agiByteCode{"player.control", [7]argType{}, 0},
	agiByteCode{"obj.status.v", [7]argType{argVar}, 1},
	agiByteCode{"quit", [7]argType{argNum}, 255},
	agiByteCode{"show.mem", [7]argType{}, 0}, // 135
	agiByteCode{"pause", [7]argType{}, 0},
	agiByteCode{"echo.line", [7]argType{}, 0},
	agiByteCode{"cancel.line", [7]argType{}, 0},
	agiByteCode{"init.joy", [7]argType{}, 0},
	agiByteCode{"toggle.monitor", [7]argType{}, 0}, // 140
	agiByteCode{"version", [7]argType{}, 0},
	agiByteCode{"script.size", [7]argType{argNum}, 1},
	agiByteCode{"set.game.id", [7]argType{argMsg}, 1},
	agiByteCode{"log", [7]argType{argMsg}, 1},
	agiByteCode{"set.scan.start", [7]argType{}, 0}, // 145
	agiByteCode{"reset.scan.start", [7]argType{}, 0},
	agiByteCode{"reposition.to", [7]argType{argObj, argNum, argNum}, 3},
	agiByteCode{"reposition.to.v", [7]argType{argObj, argVar, argVar}, 3},
	agiByteCode{"trace.on", [7]argType{}, 0},
	agiByteCode{"trace.info", [7]argType{argNum, argNum, argNum}, 3}, // 150
	agiByteCode{"print.at", [7]argType{argMsg, argNum, argNum, argNum}, 255},
	agiByteCode{"print.at.v", [7]argType{argMsg, argVar, argVar, argVar}, 255},
	agiByteCode{"discard.view.v", [7]argType{argVar}, 1},
	agiByteCode{"clear.text.rect", [7]argType{argNum, argNum, argNum, argNum, argNum}, 5},
	agiByteCode{"set.upper.left", [7]argType{argUnk, argUnk}, 2}, // 155
	agiByteCode{"set.menu", [7]argType{argMsg}, 1},
	agiByteCode{"set.menu.item", [7]argType{argMsg, argCtl}, 2},
	agiByteCode{"submit.menu", [7]argType{}, 0},
	agiByteCode{"enable.item", [7]argType{argCtl}, 1},
	agiByteCode{"disable.item", [7]argType{argCtl}, 1}, // 160
	agiByteCode{"menu.input", [7]argType{}, 0},
	agiByteCode{"show.obj.v", [7]argType{argVar}, 1},
	agiByteCode{"open.dialogue", [7]argType{}, 0},
	agiByteCode{"close.dialogue", [7]argType{}, 0},
	agiByteCode{"mul.n", [7]argType{argVar, argNum}, 2}, // 165
	agiByteCode{"mul.v", [7]argType{argVar, argVar}, 2},
	agiByteCode{"div.n", [7]argType{argVar, argNum}, 2},
	agiByteCode{"div.v", [7]argType{argVar, argVar}, 2},
	agiByteCode{"close.window", [7]argType{}, 0},
	agiByteCode{"unknown170", [7]argType{argUnk}, 1}, // 170
	agiByteCode{"unknown171", [7]argType{}, 0},
	agiByteCode{"unknown172", [7]argType{}, 0},
	agiByteCode{"unknown173", [7]argType{}, 0},
	agiByteCode{"unknown174", [7]argType{argUnk}, 1},
	agiByteCode{"unknown175", [7]argType{argUnk}, 1},   // 175
	agiByteCode{"unknown176", [7]argType{argUnk}, 255}, // 176
	agiByteCode{"unknown177", [7]argType{argUnk}, 1},
	agiByteCode{"unknown178", [7]argType{}, 0},
	agiByteCode{"unknown179", [7]argType{argUnk, argUnk, argUnk, argUnk}, 4},
	agiByteCode{"unknown180", [7]argType{argUnk, argUnk}, 2}, // 180
	agiByteCode{"unknown181", [7]argType{}, 0},
}

const numByteCodes = 182

// numArgs returns the number of arguments to a bytecode, which
// is sometimes dependent on the game version.
func numArgs(g *agi.Game, byteCode uint8) (args int) {
	args = int(agiByteCodes[byteCode].argCount)
	if args < 255 {
		return
	}

	// if args was 255, we need to calculate the arg count based on
	// the version number
	switch byteCode {
	case 134:
		if g.Version.Numeric < 2.090 {
			args = 0
		} else {
			args = 1
		}
	case 151, 152:
		if g.Version.Numeric < 2.401 {
			args = 3
		} else {
			args = 4
		}
	case 176:
		if g.Version.Numeric == 3.002086 {
			args = 1
		} else {
			args = 0
		}
	default:
		// unknown!
		args = 0
	}
	return
}

// The generic interface for the different types of AGI
// commands.  agiParsedLogic, agiParsedIf, and agiParsedGoto
// are the three implementations at present.
type agiParsedCommand interface {
	size() int
	format(g *agi.Game, msgs []string, w io.Writer, indent string) error
}

// Logic commands are parsed into agiParsedLogic's, which are simply
// the bytecodes involved
type agiParsedLogic []byte

// *agiParsedLogic's are agiParsedCommands
func (pl agiParsedLogic) size() int {
	return len(pl)
}

func (pl agiParsedLogic) format(g *agi.Game, msgs []string, w io.Writer, indent string) error {
	var extra_info, argstr []string
	var code = &agiByteCodes[pl[0]]
	argstr = make([]string, 0, len(pl)-1)

	// traverse the arguments...
	for idx, arg := range pl[1:] {
		at := code.argTypes[idx]
		argstr = append(argstr, fmt.Sprintf("%s%d", argTypePrefixes[at], arg))
		switch at {
		case argMsg:
			extra_info = append(extra_info, fmt.Sprintf("%s;; msg %d = <%s>", indent, arg, msgs[arg-1]))
		case argFlg:
			if int(arg) < len(flagInfo) {
				extra_info = append(extra_info, fmt.Sprintf("%s;; flg %d = <%s>", indent, arg, flagInfo[arg]))
			}
		case argVar:
			if int(arg) < len(variableInfo) {
				extra_info = append(extra_info, fmt.Sprintf("%s;; var %d = <%s>", indent, arg, variableInfo[arg]))
			}
		}
	}

	// TODO: format the arguments
	_, err := fmt.Fprintf(w,
		"%s%s(%s) ;; %X\n",
		indent,
		code.name,
		strings.Join(argstr, ", "),
		pl)
	if err != nil {
		return err
	}

	for _, ei := range extra_info {
		_, err = fmt.Fprintln(w, ei)
		if err != nil {
			return err
		}
	}

	return nil
}

// create an agiParsedLogic from `src`
func newParsedLogic(g *agi.Game, src []byte) (agiParsedLogic, error) {
	code := src[0]
	args := numArgs(g, code)
	if len(src) < args+1 {
		return nil, fmt.Errorf("ByteCode %d [%X]: Not enough room for %d args!", code, src, args)
	}
	return agiParsedLogic(src[0 : args+1]), nil
}

// a ParsedIf contains all the portions of an if/then/else series of
// statements
type agiParsedIf struct {
	condition          []byte             // the conditional tests
	thenPart, elsePart []agiParsedCommand // the two branches for then/else
}

// *agiParsedIf's are agiParsedCommands
func (pi *agiParsedIf) size() int {
	// DEBUG
	fmt.Fprintf(os.Stderr, "IF SIZE: [%X] + thenlen %d + elselen %d\n", pi.condition, len(pi.thenPart), len(pi.elsePart))
	// END DEBUG
	var total int = len(pi.condition) + 4 // 2 for the 0xff's, 2 for if's sizing
	for _, pc := range pi.thenPart {
		total += pc.size()
	}
	if len(pi.elsePart) > 0 {
		total += 3 // the 0xfe xx xx ELSE/GOTO
	}
	for _, pc := range pi.elsePart {
		total += pc.size()
	}
	return total
}

func (pi *agiParsedIf) format(g *agi.Game, msgs []string, w io.Writer, indent string) error {
	// TODO: format the conditions
	var err error
	var moreIndent = indent + "    "

	if _, err = fmt.Fprintf(w, "%sIF(...) { ;; %X\n", indent, pi.condition); err != nil {
		return err
	}
	for _, pc := range pi.thenPart {
		if err = pc.format(g, msgs, w, moreIndent); err != nil {
			return err
		}
	}

	if len(pi.elsePart) > 0 {
		if _, err = fmt.Fprintf(w, "%s} else {\n", indent); err != nil {
			return err
		}
		for _, pc := range pi.elsePart {
			if err = pc.format(g, msgs, w, moreIndent); err != nil {
				return err
			}
		}
	}

	if _, err = fmt.Fprintf(w, "%s}\n", indent); err != nil {
		return err
	}
	return nil
}

func signedShort(bs []byte) int16 {
	return int16(bs[0]) | (int16(bs[1]) << 8)
}

func newParsedIf(g *agi.Game, src []byte) (*agiParsedIf, error) {
	// find the end of the if statement
	end := bytes.IndexByte(src[1:], 0xff)
	if end == -1 {
		return nil, errors.New("no end to IF statement!")
	}
	if len(src) < end+4 {
		return nil, fmt.Errorf("Malformed IF, too short (%X)", src)
	}

	condition, thenLen := src[1:1+end], signedShort(src[2+end:4+end])
	fmt.Fprintf(os.Stderr, "condition [%X] thenLen %d\n", src[:4+end], thenLen) // DEBUG
	src = src[4+end:]
	if len(src) < int(thenLen) {
		return nil, fmt.Errorf("Malformed then, too short (%d vs %d)",
			len(src), thenLen)
	}

	// now check for an ELSE at the end of the then part...
	var thenSrc, elseSrc []byte = src[:thenLen], nil
	var hasElse = thenLen >= 3 && thenSrc[thenLen-3] == 0xfe
	if hasElse {
		elseLen := signedShort(thenSrc[thenLen-2:])
		if elseLen >= 0 {
			thenSrc = thenSrc[:thenLen-3]
			if len(src) < int(thenLen+elseLen) {
				return nil, fmt.Errorf("Malformed then, too short (%d vs %d)",
					len(src), thenLen+elseLen)

			}
			elseSrc = src[thenLen : thenLen+elseLen]
		} else {
			// no else section after all--backwards jump!
			hasElse = false
		}
	}

	thenStatements, err := parseManyLogic(g, thenSrc)
	if err != nil {
		return nil, err
	}
	var result = &agiParsedIf{condition, thenStatements, nil}
	if hasElse {
		result.elsePart, err = parseManyLogic(g, elseSrc)
		if err != nil {
			return nil, err
		}
	}
	return result, nil
}

// a ParsedGoto is a stray GOTO statement, which I think they
// use to implement while/do loops.
type agiParsedGoto struct {
	target int16
}

func (pg *agiParsedGoto) size() int {
	return 3
}

func (pg *agiParsedGoto) format(g *agi.Game, msgs []string, w io.Writer, indent string) error {
	if _, err := fmt.Fprintf(w, "%sGOTO %d\n", indent, pg.target); err != nil {
		return err
	}
	return nil
}

func newParsedGoto(src []byte) (*agiParsedGoto, error) {
	if len(src) < 3 {
		return nil, fmt.Errorf("GOTO, src isn't long enough! <%X>", src)
	}
	return &agiParsedGoto{signedShort(src[1:3])}, nil
}

func parseOneLogic(g *agi.Game, src []byte) (agiParsedCommand, error) {
	code := src[0]
	switch {
	case code < numByteCodes:
		return newParsedLogic(g, src)
	case code == 0xff:
		return newParsedIf(g, src)
	case code == 0xfe:
		return newParsedGoto(src)
	}
	return nil, fmt.Errorf("Unknown bytecode %X!", code)
}

func parseManyLogic(g *agi.Game, src []byte) ([]agiParsedCommand, error) {
	var result []agiParsedCommand = nil
	for len(src) > 0 {
		// DEBUG OUTPUT
		var dbgSlice = src
		if len(src) > 40 {
			dbgSlice = src[:40]
		}
		fmt.Fprintf(os.Stderr, "pm [%X]\n", dbgSlice)
		// END DEBUG
		nxt, err := parseOneLogic(g, src)
		if err != nil {
			return nil, err
		}
		result = append(result, nxt)
		// DEBUG
		fmt.Fprintf(os.Stderr, "last statment (%x) of size %d\n", src[0], nxt.size())
		// END DEBUG
		src = src[nxt.size():]
	}
	return result, nil
}

func outputLogic(game *agi.Game, odir string, i int) error {
	if i < 0 || i >= len(game.LogicDir) {
		return fmt.Errorf("Logic %d is out of range!", i)
	}

	entry := game.LogicDir[i]
	if entry.IsPresent() {
		logic, err := game.LoadLogic(i)
		if err != nil {
			return fmt.Errorf("Logic %d ERROR: %v", i, err)
		}
		path := filepath.Join(odir, fmt.Sprintf("logic_%03d.txt", i))
		logFile, err := os.Create(path)
		if err != nil {
			return err
		}
		logBuf := bufio.NewWriter(logFile)
		cmds, err := parseManyLogic(game, logic.ByteCode)
		if err != nil {
			return err
		}
		for _, pc := range cmds {
			if err = pc.format(game, logic.Messages, logBuf, ""); err != nil {
				logBuf.Flush()
				logFile.Close()
				return err
			}
		}

		fmt.Fprintln(logBuf, ";; ~~~~~~~~~~~~~~~~~~~~~~~~~")
		for mnum, msg := range logic.Messages {
			fmt.Fprintf(logBuf, "Logic %d Msg %d: <%s>\n", i, mnum+1, msg)
		}
		if err = logBuf.Flush(); err != nil {
			return err
		}
		if err = logFile.Close(); err != nil {
			return err
		}
	}
	return nil
}
