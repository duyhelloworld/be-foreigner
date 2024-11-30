package vn.edu.huce.beforeigner.domains.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LessonTarget {
    LEARN_NEW(5),
    REVIEW(10),
    TEST(15);
    private Integer elo;
}
