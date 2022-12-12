package org.rwtodd.agires.restypes;

import org.rwtodd.agires.AgiException;
import org.rwtodd.agires.AgiObject;
import org.rwtodd.agires.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the AGI Objects list. It does minimal interpretation and
 * validation of the resource, and works with a client-provided Handler to build
 * the representation of the object needed by the client.
 *
 * @author rwtodd
 */
public class ObjectsResource {

    private final byte[] data;
    private int maxAnimated;

    public ObjectsResource(final byte[] src) {
        data = src;
        maxAnimated = -1;
    }

    public int getMaxAnimated() { return maxAnimated; }

    public List<AgiObject> build() throws AgiException {
        final var result = new ArrayList<AgiObject>();
        try {
            final int wordsStart = 3 + ((data[0] & 0xff) | ((data[1] & 0xff) << 8));
            if ((wordsStart % 3) != 0) {
                throw new AgiException("Malformed game objects header!");
            }
            maxAnimated = (data[2] & 0xff);
            for (int i = 3; i < wordsStart; i += 3) {
                final var nameOffset = 3 + ((data[i] & 0xff) | ((data[i + 1] & 0xff) << 8));
                final var name = Util.asciizString(data, nameOffset);
                result.add(new AgiObject(name,data[i + 2] & 0xff));
            }
            return result;
        } catch (AgiException agie) {
            throw agie;
        } catch (Exception e) {
            throw new AgiException("Error parsing game Objects.", e);
        }
    }
}
