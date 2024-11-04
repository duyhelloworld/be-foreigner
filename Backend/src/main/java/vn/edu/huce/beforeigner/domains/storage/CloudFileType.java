package vn.edu.huce.beforeigner.domains.storage;

public enum CloudFileType {
    USER_AVATAR,
    WORD_IMAGE,
    WORD_AUDIO,
    LESSON_COVER,
    VOICE_AUDIO;

    public static String getFolderName(CloudFileType fileType) {
        return fileType.name().toLowerCase();
    }
}