package vn.edu.huce.beforeigner.utils;

import vn.edu.huce.beforeigner.domains.core.User;

public class RankUtils {
    
    public static Integer getElo(User user) {
        return user.getExperiences();
    }
    
}
