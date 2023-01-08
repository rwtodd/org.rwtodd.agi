package agiext.evaluator;

import org.rwtodd.agires.AgiException;
import org.rwtodd.agires.AgiResourceLoader;
import org.rwtodd.agires.AgiView;

/**
 * Animated Objects have certain state that the VM maintains.
 */
record AnimatedObject(int view, int loop, int cell, int baseX, int baseY, int priority) {

    AnimatedObject() {
        this(-1,0,0,0,0,0);
    }

    // adjust an existing AnimatedObject
    AnimatedObject setView(int view) {
        return new AnimatedObject(view, loop, cell, baseX, baseY, priority);
    }

    AnimatedObject setLoop(int loop) {
        return new AnimatedObject(view, loop, cell, baseX, baseY, priority);
    }

    AnimatedObject setCell(int cell) {
        return new AnimatedObject(view, loop, cell, baseX, baseY, priority);
    }

    AnimatedObject setPosition(int x, int y) { return new AnimatedObject(view, loop, cell, x, y, priority); }

    AnimatedObject setPriority(int priority) {
        return new AnimatedObject(view, loop, cell, baseX, baseY, priority);
    }

    void drawInto(VMState vms, AgiResourceLoader resLoader) throws AgiException {
        System.err.printf(" - Asked to draw view %s\n", this.toString());
        if(baseX == 0 && baseY == 0) return; // don't draw at 0,0 ... probably an error

        AgiView v = resLoader.loadView(view);
        if(loop >= v.loops().size()) {
            throw new AgiException("Bad loop number to load!");
        }
        final var l = v.loops().get(loop);
        if(cell >= l.cells().size()) {
            throw new AgiException("Bad cell number to load!");
        }
        final var c = l.cells().get(cell);
        vms.drawInto(c, baseX, baseY, priority);
    }
}