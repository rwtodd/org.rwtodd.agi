package rwt;

import org.rwtodd.agires.GameMetaData;

import java.nio.file.Path;

class DummyMetaData implements GameMetaData {
    private final double version;
    private final String prefix;

    private final byte[] decryptionString;

    DummyMetaData(double version, String prefix) {
        this.version = version;
        this.prefix = prefix;
        this.decryptionString = new byte[] {0};  // no encryption... XOR by 0!
    }
    DummyMetaData(double version) {
        this(version, null);
    }

    @Override
    public String getVersionString() {
        return null; // not needed for testing
    }

    @Override
    public double getVersion() {
        return version;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public Path getGamePath() {
        return null; // not needed for testing
    }


    @Override
    public byte[] getDecryptionKey() {
        // our dummy metadata means we don't have to encrypt/decrypt strings... we're just
        // XOR-ing with zeros!
        return decryptionString;
    }
}
