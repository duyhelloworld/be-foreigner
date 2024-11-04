package vn.edu.huce.beforeigner.infrastructures.storagemodule.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnflashImageDto {
    private int total;
    private int totalPages;
    private List<UnflashImageResultDto> results;

    @Getter
    @Setter
    @NoArgsConstructor
    public class UnflashImageResultDto {
        private String id;
        private String slug;
        private String createdAt;
        private String updatedAt;
        private String promotedAt;
        private int width;
        private int height;
        private String color;
        private String blurHash;
        private String altDescription;
        private UnflashImageResultUrlDto urls;
        private String assetType;

        @Getter
        @Setter
        @NoArgsConstructor
        public class UnflashImageResultUrlDto {
            private String raw;
            private String full;
            private String regular;
            private String small;
            private String thumb;
            private String smallS3;
        }
    }
}
