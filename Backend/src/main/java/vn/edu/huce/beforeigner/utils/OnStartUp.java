package vn.edu.huce.beforeigner.utils;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.Role;
import vn.edu.huce.beforeigner.domains.core.TokenProvider;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.exam.Answer;
import vn.edu.huce.beforeigner.domains.exam.Question;
import vn.edu.huce.beforeigner.domains.exam.QuestionType;
import vn.edu.huce.beforeigner.domains.exam.QuestionWord;
import vn.edu.huce.beforeigner.domains.exam.repo.AnswerRepository;
import vn.edu.huce.beforeigner.domains.exam.repo.LessonRepository;
import vn.edu.huce.beforeigner.domains.exam.repo.QuestionRepository;
import vn.edu.huce.beforeigner.domains.vocab.Example;
import vn.edu.huce.beforeigner.domains.vocab.Topic;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.domains.vocab.WordType;
import vn.edu.huce.beforeigner.domains.vocab.repo.ExampleRepository;
import vn.edu.huce.beforeigner.domains.vocab.repo.TopicRepository;
import vn.edu.huce.beforeigner.domains.vocab.repo.WordRepository;
import vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts.IImageService;
import vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts.ISoundService;
import vn.edu.huce.beforeigner.infrastructures.filemodule.dtos.ImageType;

@Slf4j
@Component
@RequiredArgsConstructor
public class OnStartUp implements CommandLineRunner {

	private final UserRepository userRepo;

	private final PasswordEncoder passwordEncoder;

	private final TopicRepository topicRepo;

	private final AnswerRepository answerRepo;

	private final LessonRepository lessonRepo;

	private final QuestionRepository questionRepo;

	private final ExampleRepository exampleRepo;

	private final WordRepository wordRepo;

	private final ISoundService soundService;

	private final IImageService imageService;

	@Override
	public void run(String... args) throws Exception {
		User adminUser = extractAdmin();
		
		
		var token = new UsernamePasswordAuthenticationToken(adminUser, null, adminUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(token);

		if (topicRepo.count() < 1) {
			Topic t1 = new Topic("Basic", "Các từ vựng nền tảng cơ bản", "");
			Topic t2 = new Topic("Anime", "Chủ đề về các truyện tranh của nhật được chuyển thể", "");
			Topic t3 = new Topic("Classroom", "Chủ đề về lớp học", "");
			Topic t4 = new Topic("IT", "Chủ đề về ngành công nghệ thông tin", "");
			Topic t5 = new Topic("Weather", "Chủ đề về thời tiết", "");
			Topic t6 = new Topic("Clother", "Chủ đề về trang phục", "");
			Topic t7 = new Topic("Science", "Chủ đề về khoa học", "");
			Topic t8 = new Topic("Animal", "Animal (Động vật) là chủ đề vô cùng thú vị và dễ tiếp cận", "");
			topicRepo.saveAll(List.of(t1, t2, t3, t4, t5, t6, t7, t8));
			log.info("Saved 8 topic!");

			Word w1 = new Word("animal", "động vật",  "['anəm(ə)l]", soundService.getWordAudio("animal"), imageService.getImage(ImageType.WORD_IMAGE, "animal"), WordType.NOUN);
			Example e1_1 = new Example("Our world need animal",
					"Thế giới này cần động vật");
			Example e1_2 = new Example("Animals are important to the ecosystem",
					"Động vật quan trọng với hệ sinh thái");
			w1.setTopics(Set.of(t1, t8));
			w1.setExamples(Set.of(e1_1, e1_2));
			wordRepo.save(w1);
			// Word w2 = new Word("fast", "[fæst]", "moving or able to move quickly", "",
			// 		WordType.ADJECTIVE);
			// Example e2_1 = new Example("He runs fast", "Anh ấy chạy nhanh");
			// Example e2_2 = new Example("The car is fast", "Chiếc xe nhanh");
			// w2.setExampleList(e2_1, e2_2);
			// w2.setTopicList(t1);

			// Word w3 = new Word("slow", "[sloʊ]", "moving or happening at a low speed", "",
			// 		WordType.ADJECTIVE);
			// Example e3_1 = new Example("The snail is slow", "Con ốc sên chậm");
			// Example e3_2 = new Example("The computer is slow", "Máy tính chậm");
			// w3.setExampleList(e3_1, e3_2);
			// w3.setTopicList(t1);

			// Word w4 = new Word("big", "[bɪɡ]", "large in size or extent", "", WordType.ADJECTIVE);
			// Example e4_1 = new Example("The elephant is big", "Con voi to lớn");
			// Example e4_2 = new Example("The house is big", "Ngôi nhà to lớn");
			// w4.setExampleList(e4_1, e4_2);
			// w4.setTopicList(t1);

			// Word w5 = new Word("small", "[smɔːl]", "little in size or amount", "",
			// 		WordType.ADJECTIVE);
			// Example e5_1 = new Example("The mouse is small", "Con chuột nhỏ");
			// Example e5_2 = new Example("The book is small", "Cuốn sách nhỏ");
			// w5.setExampleList(e5_1, e5_2);
			// w5.setTopicList(t1);

			// Word w6 = new Word("cute", "[kjuːt]", "attractive and appealing", "",
			// 		WordType.ADJECTIVE);
			// Example e6_1 = new Example("The kitten is cute", "Con mèo con đáng yêu");
			// Example e6_2 = new Example("The puppy is cute", "Con chó con đáng yêu");
			// w6.setExampleList(e6_1, e6_2);
			// w6.setTopicList(t1);

			// Word w7 = new Word("scary", "[ˈskɛri]", "causing fear or alarm", "",
			// 		WordType.ADJECTIVE);
			// Example e7_1 = new Example("The tiger is scary", "Con hổ đáng sợ");
			// Example e7_2 = new Example("The movie is scary", "Bộ phim đáng sợ");
			// w7.setExampleList(e7_1, e7_2);
			// w7.setTopicList(t1);

			// Word w8 = new Word("friendly", "[ˈfrɛndli]",
			// 		"having or displaying a friendly or pleasant manner", "", WordType.ADJECTIVE);
			// Example e8_1 = new Example("My dog is friendly",
			// 		"Chó của tôi thân thiện");
			// Example e8_2 = new Example("The people here are friendly",
			// 		"Những người ở đây thân thiện");
			// w8.setExampleList(e8_1, e8_2);
			// w8.setTopicList(t1);

			// Word w9 = new Word("wild", "[waɪld]",
			// 		"living or growing in a natural state; not domesticated",
			// 		"", WordType.ADJECTIVE);
			// Example e9_1 = new Example("The lion is wild", "Con sư tử hoang dã");
			// Example e9_2 = new Example("The forest is wild", "Rừng hoang dã");
			// w9.setExampleList(e9_1, e9_2);
			// w9.setTopicList(t1);

			// Word w10 = new Word("children", "[children]", "", "", WordType.NOUN);
			// Example e10_1 = new Example("The children are playing", "Những đứa trẻ đang chơi");
			// Example e10_2 = new Example("The children's toys",
			// 		"Đồ chơi của trẻ con");
			// w10.setExampleList(e10_1, e10_2);
			// w10.setTopicList(t1);

			// wordRepo.saveAll(List.of(w1, w2, w3, w4, w5, w6, w7, w8, w9, w10));
			// exampleRepo.saveAll(List.of(e1_1, e1_2, e2_1, e2_2, e3_1, e3_2, e4_1, e4_2, e5_1, e5_2, e6_1,
			// 		e6_2, e7_1,
			// 		e7_2, e8_1, e8_2, e9_1, e9_2, e10_1, e10_2));
			// log.info("Saved 10 word");

			// // w4: 'big'
			// Question q1 = new Question(QuestionType.CHOOSE_CORRECT_WORD);
			// QuestionWord qw1 = new QuestionWord();
			// qw1.setWord(w4);
			// q1.setQuestionWords(qw1);

			// // w2 : "fast"
			// Answer a1_1 = new Answer(q1, w4);
			// a1_1.setIsTrue(true);

			// Answer a1_2 = new Answer(q1, w3);
			// a1_2.setIsTrue(false);

			// Answer a1_3 = new Answer(q1, w2);
			// a1_3.setIsTrue(false);

			// Answer a1_4 = new Answer(q1, w1);
			// a1_4.setIsTrue(false);

			// q1.setAnswers(a1_1, a1_2, a1_3, a1_4);

			// // w8 : "friendly"
			// // w9 : "wild"
			// Question q2 = new Question(QuestionType.CHOOSE_CORRECT_WORD);
			// QuestionWord qw2_1 = new QuestionWord();
			
			// Answer a2_1 = new Answer(q2);
			// a2_1.setWords(w8, w9);

			// Answer a2_2 = new Answer(q2);
			// a2_1.setWords(w8, w9);

			// Answer a2_3 = new Answer(q2);
			// a2_1.setWords(w8, w9);

			// Answer a2_4 = new Answer(q2);
			// a2_1.setWords(w8, w9);

			// q2.setAnswers(a2_1, a2_2, a2_3, a2_4);

			// Question q3 = new Question(w1, QuestionType.MATCHING, null,  10);

			// Answer a3_1 = new Answer(q3);
			// a3_1.setWords(w1); // animal
			// Answer a3_2 = new Answer(q3);
			// a3_2.setWords(w2); // fast

			// Answer a3_3 = new Answer(q3); // match answer for animal
			// a3_3.setMatchAnswer(a3_1);
			// a3_1.getMatchedAnswers().add(a3_3);

			// Answer a3_4 = new Answer(q3, a3_2); // match answer for fast
			// a3_4.setMatchAnswer(a3_2);
			// a3_2.getMatchedAnswers().add(a3_4);

			// q3.setAnswers(a3_1, a3_2, a3_3, a3_4);

			// Question 4 (Choose correct meaning)
			// Question q4 = new Question(w1, QuestionType.CHOOSE_CORRECT_MEAN, "What does
			// the word 'slow' mean?", 10);
			// q4.setQuestionString("");

			// Answer a4_1 = new Answer(q4);
			// a4_1.setWords(w1);
			// a4_1.setIsTrue(true);

			// Answer a4_2 = new Answer(q4);
			// a4_2.setWord(w4); // big
			// a4_2.setIsTrue(false);

			// Answer a4_3 = new Answer(q4);
			// a4_3.setWord(w5); // small
			// a4_3.setIsTrue(false);

			// Question 5 (Fill in the blank)
			// Question q5 = new Question(w1, QuestionType.FILL_IN_BLANK, "I like to eat
			// ...", 10);
			// q5.setQuestionString("");

			// Answer a5_1 = new Answer(q5);
			// a5_1.setCorrectString("apples");

			// Answer a5_2 = new Answer(q5);
			// a5_2.setCorrectString("bananas");

			// q5.setAnswers(a5_1, a5_2);
		}
	}

	private User extractAdmin() {
		String adminUsername = "admin";
		if (userRepo.count() < 1) {
			String encodedPass = passwordEncoder.encode("1234");
			User adminUser = new User(adminUsername, "Chủ thớt", "admin@gmail.com", encodedPass);
			adminUser.setRole(Role.ADMIN);
			User user = new User("duyhelloworld", "Duy Pham", "duy0184466@huce.edu.vn", adminUser.getPassword());	
			userRepo.saveAll(List.of(adminUser, user));
			log.info("Saved user : {}", user.getUsername());
			log.info("Saved admin account '{}' success", adminUser.getUsername());
			return adminUser;
		} else {
			return userRepo.findByUsername(adminUsername).get();
		}
	}

	
}
