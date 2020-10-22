package org.rwtodd.agi.resources;

/**
 * Represents the AGI Objects list. It does minimal interpretation and
 * validation of the resource, and works with a client-provided Handler to build
 * the representation of the sound needed by the client.
 *
 * @author rwtodd
 */
public class ObjectsResource {

    public static interface Handler {

        void startObjects(int maximumAnimated);

        void object(int startingRoom, String name);

        void endObjects();
    }

    private final byte[] data;

    public ObjectsResource(final byte[] src) {
        data = src;
    }

    public void streamToHandler(final Handler h) throws AGIException {
        try {
            final int wordsStart = 3 + ((data[0] & 0xff) | ((data[1] & 0xff) << 8));
            if((wordsStart%3)!=0) {
                throw new AGIException("Malformed game objects header!");
            }
            h.startObjects(data[2] & 0xff);
            for(int i = 3; i < wordsStart; i += 3) {
                final var nameOffset = 3+((data[i]&0xff) | ((data[i+1]&0xff) << 8));
		final var name = Util.asciizString(data, nameOffset);
                h.object(data[i+2]&0xff, name);
            }
            h.endObjects();
        } catch(AGIException agie) {
            throw agie;
        } catch (Exception e) {
            throw new AGIException("Error parsing game Objects.", e);
        }
    }
}
