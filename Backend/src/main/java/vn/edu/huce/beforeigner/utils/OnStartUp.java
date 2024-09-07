package vn.edu.huce.beforeigner.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.entities.core.Role;
import vn.edu.huce.beforeigner.entities.core.TokenProvider;
import vn.edu.huce.beforeigner.entities.core.User;
import vn.edu.huce.beforeigner.entities.learn.Deck;
import vn.edu.huce.beforeigner.entities.learn.DifficultyLevel;
import vn.edu.huce.beforeigner.entities.learn.Word;
import vn.edu.huce.beforeigner.entities.learn.WordType;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthenticatedUser;
import vn.edu.huce.beforeigner.repositories.CardRepository;
import vn.edu.huce.beforeigner.repositories.DeckRepository;
import vn.edu.huce.beforeigner.repositories.UserRepository;
import vn.edu.huce.beforeigner.repositories.WordRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class OnStartUp implements CommandLineRunner {

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    private final WordRepository wordRepo;

    private final DeckRepository deckRepo;

    private final CardRepository cardRepo;

    @Value("${application.auth.test-user.username}")
    private String testUsername;

    @Value("${application.auth.test-user.password}")
    private String testPassword;

    @Value("${application.auth.test-user.email}")
    private String testEmail;

    @Value("${application.auth.test-user.fullname}")
    private String testFullname;

    @Override
    public void run(String... args) throws Exception {
        User adminUser;
        if (userRepo.count() < 1) {
            String encodedPass = passwordEncoder.encode(testPassword);
            adminUser = new User(testUsername, testFullname, testEmail, encodedPass, TokenProvider.LOCAL, Role.ADMIN);
            User user = new User("duyhelloworld", "Duy Pham", "duy0184466@huce.edu.vn", encodedPass, TokenProvider.LOCAL, Role.USER);
            userRepo.save(adminUser);
            log.info("Saved admin account!");
            userRepo.save(user);
            log.info("Saved user 'duyhelloworld'!");
        } else {
            adminUser = userRepo.findByUsername(testUsername).get();
        }
        UserDetails admin = AuthenticatedUser.instance(adminUser);
        var token = new UsernamePasswordAuthenticationToken(admin,null, admin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        if (deckRepo.count() < 1) {
            Deck c1 = new Deck("Animal", "Animal (Động vật) là chủ đề vô cùng thú vị và dễ tiếp cận", DifficultyLevel.BEGINNER);
            Deck c2 = new Deck("Anime", "Chủ đề về các truyện tranh của nhật được chuyển thể", DifficultyLevel.MEDIUM);
            Deck c3 = new Deck("Classroom", "Chủ đề về lớp học", DifficultyLevel.BEGINNER);
            Deck c4 = new Deck("IT", "Chủ đề về ngành công nghệ thông tin", DifficultyLevel.HARD);
            Deck c5 = new Deck("Weather", "Chủ đề về thời tiết", DifficultyLevel.BEGINNER);
            Deck c6 = new Deck("Clother", "Chủ đề về trang phục", DifficultyLevel.MEDIUM);
            deckRepo.saveAll(List.of(c1, c2, c3, c4, c5, c6));
        }

        if (wordRepo.count() < 1 && cardRepo.count() < 1) {
            Word w1 = new Word("animal", "động vật", "['anəm(ə)l]", "", WordType.NOUN);
            Word w2 = new Word("fast", "nhanh", "[fæst]", "moving or able to move quickly", WordType.ADJECTIVE);
            Word w3 = new Word("slow", "chậm", "[sloʊ]", "moving or happening at a low speed", WordType.ADJECTIVE);
            Word w4 = new Word("big", "to lớn", "[bɪɡ]", "large in size or extent", WordType.ADJECTIVE);
            Word w5 = new Word("small", "nhỏ bé", "[smɔːl]", "little in size or amount", WordType.ADJECTIVE);
            Word w6 = new Word("cute", "đáng yêu", "[kjuːt]", "attractive and appealing", WordType.ADJECTIVE);
            Word w7 = new Word("scary","đáng sợ", "[ˈskɛri]", "causing fear or alarm", WordType.ADJECTIVE);
            Word w8 = new Word("friendly","thân thiện","[ˈfrɛndli]", "having or displaying a friendly or pleasant manner", WordType.ADJECTIVE);
            Word w9 = new Word("wild", "hoang dã","[waɪld]", "living or growing in a natural state; not domesticated", WordType.ADJECTIVE); 
            Word w10 = new Word("children","trẻ con", "[children]", "", WordType.NOUN);
            wordRepo.saveAll(List.of(w1, w2, w3, w4, w5, w6, w7, w8, w9, w10));
        }
    }
}
