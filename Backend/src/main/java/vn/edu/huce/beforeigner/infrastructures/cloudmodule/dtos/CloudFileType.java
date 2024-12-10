package vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CloudFileType {
    USER_AVATAR("https://static.vecteezy.com/system/resources/thumbnails/000/439/863/small/Basic_Ui__28186_29.jpg"),
    WORD_IMAGE(""),
    WORD_AUDIO(""),
    LESSON_COVER(""),
    VOICE_AUDIO("");
    private String defaultUrl;

    public static String getFolderName(CloudFileType fileType) {
        return fileType.name().toLowerCase();
    }
}
