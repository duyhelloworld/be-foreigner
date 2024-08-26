package vn.edu.huce.beforeigner.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.entities.core.Role;
import vn.edu.huce.beforeigner.entities.core.TokenProvider;
import vn.edu.huce.beforeigner.entities.core.User;
import vn.edu.huce.beforeigner.entities.learn.Card;
import vn.edu.huce.beforeigner.entities.learn.Category;
import vn.edu.huce.beforeigner.entities.learn.Word;
import vn.edu.huce.beforeigner.entities.learn.WordType;
import vn.edu.huce.beforeigner.repositories.CardRepo;
import vn.edu.huce.beforeigner.repositories.CategoryRepo;
import vn.edu.huce.beforeigner.repositories.DeckRepo;
import vn.edu.huce.beforeigner.repositories.UserRepo;
import vn.edu.huce.beforeigner.repositories.WordRepo;

@Slf4j
@Component
@RequiredArgsConstructor
public class OnStartUp implements CommandLineRunner {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final WordRepo wordRepo;

    private final CategoryRepo categoryRepo;

    private final DeckRepo deckRepo;

    private final CardRepo cardRepo;

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
        if (userRepo.count() < 1) {
            String encodedPass = passwordEncoder.encode(testPassword);
            User admin = new User(testUsername, testFullname, testEmail, encodedPass, TokenProvider.LOCAL, Role.ADMIN);
            User user = new User("duyhelloworld", "Duy Pham", "duy0184466@huce.edu.vn", encodedPass, TokenProvider.LOCAL, Role.USER);
            userRepo.save(admin);
            log.info("Saved admin account!");
            userRepo.save(user);
            log.info("Saved user 'duyhelloworld'!");
        }

        if (categoryRepo.count() < 1) {
            Category c1 = new Category("Animal", "Animal (Động vật) là chủ đề vô cùng thú vị và dễ tiếp cận");
            Category c2 = new Category("Anime", "Chủ đề về các truyện tranh của nhật được chuyển thể");
            Category c3 = new Category("Classroom", "Chủ đề về lớp học");
            Category c4 = new Category("IT", "Chủ đề về ngành công nghệ thông tin");
            Category c5 = new Category("Weather", "Chủ đề về thời tiết");
            Category c6 = new Category("Clother", "Chủ đề về trang phục");
            categoryRepo.saveAll(List.of(c1, c2, c3, c4, c5, c6));
        }

        if (wordRepo.count() < 1 || cardRepo.count() < 1) {
            Word w1 = new Word("animal", "['anəm(ə)l]", "", WordType.NOUN);
            Word w2 = new Word("fast", "[fæst]", "moving or able to move quickly", WordType.ADJECTIVE);
            Word w3 = new Word("slow", "[sloʊ]", "moving or happening at a low speed", WordType.ADJECTIVE);
            Word w4 = new Word("big", "[bɪɡ]", "large in size or extent", WordType.ADJECTIVE);
            Word w5 = new Word("small", "[smɔːl]", "little in size or amount", WordType.ADJECTIVE);
            Word w6 = new Word("cute", "[kjuːt]", "attractive and appealing", WordType.ADJECTIVE);
            Word w7 = new Word("scary", "[ˈskɛri]", "causing fear or alarm", WordType.ADJECTIVE);
            Word w8 = new Word("friendly", "[ˈfrɛndli]", "having or displaying a friendly or pleasant manner", WordType.ADJECTIVE);
            Word w9 = new Word("wild", "[waɪld]", "living or growing in a natural state; not domesticated", WordType.ADJECTIVE); 
            Word w10 = new Word("children", "[]", "", WordType.NOUN);
            
            wordRepo.saveAll(List.of(w1, w2, w3, w4, w5, w6, w7, w8, w9, w10));
            
            Card c1 = new Card();
        }
    }
}
