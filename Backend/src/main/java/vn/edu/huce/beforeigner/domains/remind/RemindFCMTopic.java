package vn.edu.huce.beforeigner.domains.remind;

/**
 * Mã topic bên firebase
 */
public enum RemindFCMTopic {
    FIRST_OPEN,
    LAST_OPEN_IN_3_DAY,
    LAST_OPEN_IN_7_DAY,
    LAST_OPEN_IN_15_DAY,
    LAST_OPEN_IN_30_DAY;

    public String getStandardName() {
        return this.name().replace("_", "").toLowerCase();
    }
}
