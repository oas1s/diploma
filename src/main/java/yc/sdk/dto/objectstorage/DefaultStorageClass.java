package yc.sdk.dto.objectstorage;

public enum DefaultStorageClass {
    STANDARD("STANDARD"),
    COLD("COLD");

    public final String name;

    private DefaultStorageClass(String name) {
        this.name = name;
    }
}
