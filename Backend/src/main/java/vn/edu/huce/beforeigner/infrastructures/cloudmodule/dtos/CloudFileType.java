package vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CloudFileType {
    USER_AVATAR(""),
    WORD_IMAGE(""),
    WORD_AUDIO(""),
    LESSON_COVER(""),
    VOICE_AUDIO("");
    private String defaultUrl;

    public static String getFolderName(CloudFileType fileType) {
        return fileType.name().toLowerCase();
    }
}
