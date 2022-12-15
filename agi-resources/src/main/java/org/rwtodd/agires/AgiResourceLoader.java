package org.rwtodd.agires;

import org.rwtodd.agires.restypes.*;
import org.rwtodd.agires.util.Util;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

/**
 * A resource loader for all AGI games.  Unlike many classes in this package, small
 * differences in AGI versions are ironed out in the code, rather than via independent
 * V2/V3 classes.
 *
 * @author Richard Todd
 */
public class AgiResourceLoader implements java.io.Closeable {
    private final GameMetaData meta;
    private final VolumeManager vmgr;
    private final ResourceDirectory rdir;
    private final AgiDictionary dictionary;
    private final List<AgiObject> initialObjects;
    private final int maxAnimated;

    public AgiResourceLoader(
            GameMetaData meta,
            final ResourceDirectory rd,
            final VolumeManager vm) throws AgiException {
        this.meta = meta;
        rdir = rd;
        vmgr = vm;
        try {
            dictionary = WordsResource.build(vmgr.getWordsData());
        } catch(IOException ioe) {
            throw new AgiException("Error loading WORDS.TOK!",ioe);
        }
        try {
            final var objData = vmgr.getObjectsData();
            if (meta.getVersion() >= 2.411) {
                Util.decodeInPlace(meta.getDecryptionKey(), objData);
            }
            final var ores = new ObjectsResource(objData);
            initialObjects = ores.build();
            maxAnimated = ores.getMaxAnimated();
        } catch(IOException ioe) {
            throw new AgiException("Error loading OBJECT file!", ioe);
        }
    }

    /**
     * Create a resource loader with the default Resource Directory and Volume Managers.
     * @param meta the game metadata
     * @throws AgiException if there is a problem initializing the object.
     */
    public AgiResourceLoader(GameMetaData meta) throws AgiException {
        this(meta, ResourceDirectory.createDefault(meta), VolumeManager.createDefault(meta));
    }

    @Override
    public void close() throws IOException {
        vmgr.close();
    }

    public int getSoundCount() {
        return rdir.getSoundCount();
    }

    public int getPicCount() {
        return rdir.getPicCount();
    }

    public int getViewCount() {
        return rdir.getViewCount();
    }

    public int getLogicCount() {
        return rdir.getLogicCount();
    }

    public AgiSound loadSound(int number) throws AgiException, ResourceNotPresentException {
        final var dirEntry = rdir.findSound(number);
        if (!dirEntry.isPresent()) {
            throw new ResourceNotPresentException("Sound resource " + number + " isn't present!");
        }
        return loadSound(number, dirEntry);
    }

    protected AgiSound loadSound(int number, DirEntry de) throws AgiException, ResourceNotPresentException {
        try {
            final var resbytes = vmgr.getResource(de);
            return SoundResource.build(resbytes);
        } catch(AgiException agie) {
            throw agie;
        } catch (Exception e) {
            throw new AgiException("Error while building Sound Resource",e);
        }
    }

    public AgiPic loadPic(int number, Consumer<AgiPic.Image> observer) throws AgiException, ResourceNotPresentException {
        final var dirEntry = rdir.findPic(number);
        if (!dirEntry.isPresent()) {
            throw new ResourceNotPresentException("PIC resource " + number + " isn't present!");
        }
        return loadPic(number, dirEntry, observer);
    }

    protected AgiPic loadPic(int number, DirEntry de, Consumer<AgiPic.Image> observer) throws AgiException, ResourceNotPresentException {
        final var resbytes = vmgr.getResource(de);
        return new PicResource(meta, observer).build(resbytes);
    }

    public AgiLogicScript loadLogic(int number) throws AgiException, ResourceNotPresentException {
        final var dirEntry = rdir.findLogic(number);
        if (!dirEntry.isPresent()) {
            throw new ResourceNotPresentException("LOGIC resource " + number + " isn't present!");
        }
        return loadLogic(number, dirEntry);
    }

    protected AgiLogicScript loadLogic(int number, DirEntry de) throws AgiException, ResourceNotPresentException {
        final var resbytes = vmgr.getResource(de);
        if (meta.isBeforeV3()) {
            // we have to decode the text section
            final var textArea = ((resbytes[0] & 0xff) | ((resbytes[1] & 0xff) << 8)) + 2;
            final var msgIndexLen = 2 * (resbytes[textArea] & 0xff);
            Util.decodeInPlace(meta.getDecryptionKey(), resbytes, textArea + 3 + msgIndexLen, resbytes.length);
        }
        return LogicResource.build(this, resbytes);
    }

    public AgiView loadView(int number) throws AgiException, ResourceNotPresentException {
        final var dirEntry = rdir.findView(number);
        if (!dirEntry.isPresent()) {
            throw new ResourceNotPresentException("VIEW resource " + number + " isn't present!");
        }
        return loadView(number, dirEntry);
    }

    protected AgiView loadView(int number, DirEntry de) throws AgiException, ResourceNotPresentException {
        final var resbytes = vmgr.getResource(de);
        return ViewResource.build(resbytes);
    }

    public AgiDictionary getDictionary() { return dictionary; }
    public List<AgiObject> getInitialGameObjects() { return initialObjects; }
    public int getMaxAnimatedObjects() { return maxAnimated; }
    public GameMetaData getMetaData() { return meta; }

    public int[] getPalette() {
        // assume standard EGA palette for now. Might expand to the strange palettes of
        // non-PC versions of the games in the future.
        return Util.egaPalette;
    }
}
