unit AGIcommands;

// This is the command unit from AGI Studio. It is written in Delphi. It
// will probably compile under other versions of Pascal. It is based on
// AGICODES.H.

interface

const
      atNum = 0;
      atVar = 1;
      atFlag = 2;
      atMsg = 3;
      atSObj = 4;
      atIObj = 5;
      atStr = 6;
      atWord = 7;
      atCtrl = 8;

      ArgTypePrefix : array[0..8] of string[2] = ('','v','f','m','o','i','s','w','c');
      ArgTypeName : array[0..8] of string = ('number','var','flag','message','object',
                                             'inventory item','string','word','controller');

type CommandStruct = record
                       Name : string;
                       numArgs : byte;
                       argTypes : array[1..7] of byte;
                      end;

const NumTestCommands : byte = 18;
      NumAGICommands  : byte = 181;  // not counting return()

const TestCommand : array[1..18] of CommandStruct =
  ((Name:'equaln'; numArgs: 2; argTypes:(atVar,atNum,0,0,0,0,0)),
   (Name:'equalv'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
   (Name:'lessn'; numArgs: 2; argTypes:(atVar,atNum,0,0,0,0,0)),
   (Name:'lessv'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
   (Name:'greatern'; numArgs: 2; argTypes:(atVar,atNum,0,0,0,0,0)),
   (Name:'greaterv'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
   (Name:'isset'; numArgs: 1; argTypes:(atFlag,0,0,0,0,0,0)),
   (Name:'issetv'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
   (Name:'has'; numArgs: 1; argTypes:(atIObj,0,0,0,0,0,0)),
   (Name:'obj.in.room'; numArgs: 2; argTypes:(atIObj,atVar,0,0,0,0,0)),
   (Name:'posn'; numArgs: 5; argTypes:(atSObj,atNum,atNum,atNum,atNum,0,0)),
   (Name:'controller'; numArgs: 1; argTypes:(atCtrl,0,0,0,0,0,0)),
   (Name:'have.key'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
   (Name:'said'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),  // special command so we don't need to set the right argument types for it
   (Name:'compare.strings'; numArgs: 2; argTypes:(atStr,atStr,0,0,0,0,0)),
   (Name:'obj.in.box'; numArgs: 5; argTypes:(atSObj,atNum,atNum,atNum,atNum,0,0)),
   (Name:'center.posn'; numArgs: 5; argTypes:(atSObj,atNum,atNum,atNum,atNum,0,0)),
   (Name:'right.posn'; numArgs: 5; argTypes:(atSObj,atNum,atNum,atNum,atNum,0,0)));

const AGICommand : array[0..181] of CommandStruct =
((Name:'return'; numArgs: 0),
 (Name:'increment'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'decrement'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'assignn'; numArgs: 2; argTypes:(atVar,atNum,0,0,0,0,0)),
 (Name:'assignv'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
 (Name:'addn'; numArgs: 2; argTypes:(atVar,atNum,0,0,0,0,0)),
 (Name:'addv'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
 (Name:'subn'; numArgs: 2; argTypes:(atVar,atNum,0,0,0,0,0)),
 (Name:'subv'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
 (Name:'lindirectv'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
 (Name:'rindirect'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
 (Name:'lindirectn'; numArgs: 2; argTypes:(atVar,atNum,0,0,0,0,0)),
 (Name:'set'; numArgs: 1; argTypes:(atFlag,0,0,0,0,0,0)),
 (Name:'reset'; numArgs: 1; argTypes:(atFlag,0,0,0,0,0,0)),
 (Name:'toggle'; numArgs: 1; argTypes:(atFlag,0,0,0,0,0,0)),
 (Name:'set.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'reset.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'toggle.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'new.room'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),
 (Name:'new.room.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'load.logics'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),
 (Name:'load.logics.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'call'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),
 (Name:'call.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'load.pic'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'draw.pic'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'show.pic'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'discard.pic'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'overlay.pic'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'show.pri.screen'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'load.view'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),
 (Name:'load.view.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'discard.view'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),
 (Name:'animate.obj'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'unanimate.all'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'draw'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'erase'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'position'; numArgs: 3; argTypes:(atSObj,atNum,atNum,0,0,0,0)),
 (Name:'position.v'; numArgs: 3; argTypes:(atSObj,atVar,atVar,0,0,0,0)),
 (Name:'get.posn'; numArgs: 3; argTypes:(atSObj,atVar,atVar,0,0,0,0)),
 (Name:'reposition'; numArgs: 3; argTypes:(atSObj,atVar,atVar,0,0,0,0)),
 (Name:'set.view'; numArgs: 2; argTypes:(atSObj,atNum,0,0,0,0,0)),
 (Name:'set.view.v'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'set.loop'; numArgs: 2; argTypes:(atSObj,atNum,0,0,0,0,0)),
 (Name:'set.loop.v'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'fix.loop'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'release.loop'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'set.cel'; numArgs: 2; argTypes:(atSObj,atNum,0,0,0,0,0)),
 (Name:'set.cel.v'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'last.cel'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'current.cel'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'current.loop'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'current.view'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'number.of.loops'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'set.priority'; numArgs: 2; argTypes:(atSObj,atNum,0,0,0,0,0)),
 (Name:'set.priority.v'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'release.priority'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'get.priority'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'stop.update'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'start.update'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'force.update'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'ignore.horizon'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'observe.horizon'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'set.horizon'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),
 (Name:'object.on.water'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'object.on.land'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'object.on.anything'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'ignore.objs'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'observe.objs'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'distance'; numArgs: 3; argTypes:(atSObj,atSObj,atVar,0,0,0,0)),
 (Name:'stop.cycling'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'start.cycling'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'normal.cycle'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'end.of.loop'; numArgs: 2; argTypes:(atSObj,atFlag,0,0,0,0,0)),
 (Name:'reverse.cycle'; numArgs:1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'reverse.loop'; numArgs: 2; argTypes:(atSObj,atFlag,0,0,0,0,0)),
 (Name:'cycle.time'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'stop.motion'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'start.motion'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'step.size'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'step.time'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'move.obj'; numArgs: 5; argTypes:(atSObj,atNum,atNum,atNum,atFlag,0,0)),
 (Name:'move.obj.v'; numArgs: 5; argTypes:(atSObj,atVar,atVar,atNum,atFlag,0,0)),
 (Name:'follow.ego'; numArgs: 3; argTypes:(atSObj,atNum,atFlag,0,0,0,0)),
 (Name:'wander'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'normal.motion'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'set.dir'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'get.dir'; numArgs: 2; argTypes:(atSObj,atVar,0,0,0,0,0)),
 (Name:'ignore.blocks'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'observe.blocks'; numArgs: 1; argTypes:(atSObj,0,0,0,0,0,0)),
 (Name:'block'; numArgs: 4; argTypes:(atNum,atNum,atNum,atNum,0,0,0)),
 (Name:'unblock'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'get'; numArgs: 1; argTypes:(atIObj,0,0,0,0,0,0)),
 (Name:'get.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'drop'; numArgs: 1; argTypes:(atIObj,0,0,0,0,0,0)),
 (Name:'put'; numArgs: 2; argTypes:(atIObj,atVar,0,0,0,0,0)),
 (Name:'put.v'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
 (Name:'get.room.v'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
 (Name:'load.sound'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),
 (Name:'sound'; numArgs: 2; argTypes:(atNum,atFlag,0,0,0,0,0)),
 (Name:'stop.sound'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'print'; numArgs: 1; argTypes:(atMsg,0,0,0,0,0,0)),
 (Name:'print.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'display'; numArgs: 3; argTypes:(atNum,atNum,atMsg,0,0,0,0)),
 (Name:'display.v'; numArgs: 3; argTypes:(atVar,atVar,atVar,0,0,0,0)),
 (Name:'clear.lines'; numArgs: 3; argTypes:(atNum,atNum,atNum,0,0,0,0)),
 (Name:'text.screen'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'graphics'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'set.cursor.char'; numArgs: 1; argTypes:(atMsg,0,0,0,0,0,0)),
 (Name:'set.text.attribute'; numArgs: 2; argTypes:(atNum,atNum,0,0,0,0,0)),
 (Name:'shake.screen'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),
 (Name:'configure.screen'; numArgs: 3; argTypes:(atNum,atNum,atNum,0,0,0,0)),
 (Name:'status.line.on'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'status.line.off'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'set.string'; numArgs: 2; argTypes:(atStr,atMsg,0,0,0,0,0)),
 (Name:'get.string'; numArgs: 5; argTypes:(atStr,atMsg,atNum,atNum,atNum,0,0)),
 (Name:'word.to.string'; numArgs: 2; argTypes:(atWord,atStr,0,0,0,0,0)),
 (Name:'parse'; numArgs: 1; argTypes:(atStr,0,0,0,0,0,0)),
 (Name:'get.num'; numArgs: 2; argTypes:(atMsg,atVar,0,0,0,0,0)),
 (Name:'prevent.input'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'accept.input'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'set.key'; numArgs: 3; argTypes:(atNum,atNum,atCtrl,0,0,0,0)),
 (Name:'add.to.pic'; numArgs: 7; argTypes:(atNum,atNum,atNum,atNum,atNum,atNum,atNum)),
 (Name:'add.to.pic.v'; numArgs: 7; argTypes:(atVar,atVar,atVar,atVar,atVar,atVar,atVar)),
 (Name:'status'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'save.game'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'restore.game'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'init.disk'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'restart.game'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'show.obj'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),
 (Name:'random'; numArgs: 3; argTypes:(atNum,atNum,atVar,0,0,0,0)),
 (Name:'program.control'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'player.control'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'obj.status.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'quit'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),  // 0 args for 2.089
 (Name:'show.mem'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'pause'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'echo.line'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'cancel.line'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'init.joy'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'toggle.monitor'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'version'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'script.size'; numArgs: 1; argTypes:(atNum,0,0,0,0,0,0)),
 (Name:'set.game.id'; numArgs: 1; argTypes:(atMsg,0,0,0,0,0,0)),
 (Name:'log'; numArgs: 1; argTypes:(atMsg,0,0,0,0,0,0)),
 (Name:'set.scan.start'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'reset.scan.start'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'reposition.to'; numArgs: 3; argTypes:(atSObj,atNum,atNum,0,0,0,0)),
 (Name:'reposition.to.v'; numArgs: 3; argTypes:(atSObj,atVar,atVar,0,0,0,0)),
 (Name:'trace.on'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'trace.info'; numArgs: 3; argTypes:(atNum,atNum,atNum,0,0,0,0)),
 (Name:'print.at'; numArgs: 4; argTypes:(atMsg,atNum,atNum,atNum,0,0,0)),    // 3 args before 2.440
 (Name:'print.at.v'; numArgs: 4; argTypes:(atMsg,atVar,atVar,atVar,0,0,0)),  // 3 args before 2.440
 (Name:'discard.view.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'clear.text.rect'; numArgs: 5; argTypes:(atNum,atNum,atNum,atNum,atNum,0,0)),
 (Name:'set.upper.left'; numArgs: 2; argTypes:(0,0,0,0,0,0,0)),    // ?????
 (Name:'set.menu'; numArgs: 1; argTypes:(atMsg,0,0,0,0,0,0)),
 (Name:'set.menu.item'; numArgs: 2; argTypes:(atMsg,atCtrl,0,0,0,0,0)),
 (Name:'submit.menu'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'enable.item'; numArgs: 1; argTypes:(atCtrl,0,0,0,0,0,0)),
 (Name:'disable.item'; numArgs: 1; argTypes:(atCtrl,0,0,0,0,0,0)),
 (Name:'menu.input'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'show.obj.v'; numArgs: 1; argTypes:(atVar,0,0,0,0,0,0)),
 (Name:'open.dialogue'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'close.dialogue'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'mul.n'; numArgs: 2; argTypes:(atVar,atNum,0,0,0,0,0)),
 (Name:'mul.v'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
 (Name:'div.n'; numArgs: 2; argTypes:(atVar,atNum,0,0,0,0,0)),
 (Name:'div.v'; numArgs: 2; argTypes:(atVar,atVar,0,0,0,0,0)),
 (Name:'close.window'; numArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown170'; NumArgs: 1; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown171'; NumArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown172'; NumArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown173'; NumArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown174'; NumArgs: 1; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown175'; NumArgs: 1; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown176'; NumArgs: 0; argTypes:(0,0,0,0,0,0,0)),   // 1 arg for 3.002.086
 (Name:'unknown177'; NumArgs: 1; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown178'; NumArgs: 0; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown179'; NumArgs: 4; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown180'; NumArgs: 2; argTypes:(0,0,0,0,0,0,0)),
 (Name:'unknown181'; NumArgs: 0; argTypes:(0,0,0,0,0,0,0)));

procedure CorrectCommands(VerNum:double);

implementation

procedure CorrectCommands(VerNum:double);
// This procedure adjusts the above data for a given int. version
begin
  if VerNum <= 2.089 then
    AGICommand[134].NumArgs := 0;  // quit
  if VerNum <= 2.400 then
  begin
    AGICommand[151].NumArgs := 3;  // print.at
    AGICommand[152].NumArgs := 3;  // print.at.v
  end;
  if VerNum <= 3.002086 then
    AGICommand[176].NumArgs := 1;
  if VerNum <= 2.089 then NumAGICommands := 155
  else if VerNum <= 2.272 then NumAGICommands := 161
  else if VerNum <= 2.440 then NumAGICommands := 169
  else if VerNum <= 2.917 then NumAGICommands := 173
  else if VerNum <= 2.936 then NumAGICommands := 175
  else if VerNum <= 3.002086 then NumAGICommands := 177
  else NumAGICommands := 181
end;

end.