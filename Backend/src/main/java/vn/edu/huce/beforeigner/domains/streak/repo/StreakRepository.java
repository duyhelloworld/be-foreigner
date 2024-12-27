package vn.edu.huce.beforeigner.domains.streak.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.edu.huce.beforeigner.domains.streak.Streak;
import java.util.Optional;

@Repository
public interface StreakRepository extends JpaRepository<Streak, Integer> {
    
    Optional<Streak> findByOwner(String owner);

    @Transactional
    @Modifying
    @Query("UPDATE Streak s SET s.isPlusStreak = false")
    int resetShowedStreak();
}
