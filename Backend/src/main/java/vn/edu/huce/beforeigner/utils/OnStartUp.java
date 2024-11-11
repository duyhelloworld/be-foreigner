package vn.edu.huce.beforeigner.utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.core.Role;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.exam.Answer;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.domains.exam.Question;
import vn.edu.huce.beforeigner.domains.exam.QuestionType;
import vn.edu.huce.beforeigner.domains.exam.repo.LessonRepository;
import vn.edu.huce.beforeigner.domains.exam.repo.QuestionRepository;
import vn.edu.huce.beforeigner.domains.history.LessonHistory;
import vn.edu.huce.beforeigner.domains.history.LessonStatus;
import vn.edu.huce.beforeigner.domains.history.repo.LessonHistoryRepository;
import vn.edu.huce.beforeigner.domains.ranking.RankedUser;
import vn.edu.huce.beforeigner.domains.ranking.Ranking;
import vn.edu.huce.beforeigner.domains.ranking.repo.RankedUserRepository;
import vn.edu.huce.beforeigner.domains.ranking.repo.RankingRepository;
import vn.edu.huce.beforeigner.domains.storage.CloudFileType;
import vn.edu.huce.beforeigner.domains.system.Sysvar;
import vn.edu.huce.beforeigner.domains.system.SysvarKey;
import vn.edu.huce.beforeigner.domains.system.repo.SysvarRepository;
import vn.edu.huce.beforeigner.domains.vocab.Example;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.domains.vocab.WordType;
import vn.edu.huce.beforeigner.domains.vocab.repo.WordRepository;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserTokenService;
import vn.edu.huce.beforeigner.infrastructures.storagemodule.abstracts.ICloudFileService;

@Slf4j
@Component
@RequiredArgsConstructor
public class OnStartUp implements CommandLineRunner {

	private final UserRepository userRepo;

	private final SysvarRepository sysvarRepo;

	private final LessonRepository lessonRepo;

	private final QuestionRepository questionRepo;

	private final WordRepository wordRepo;

	private final LessonHistoryRepository lessonHistoryRepo;

	private final RankingRepository rankingRepo;

	private final RankedUserRepository rankedUserRepo;

	private final IUserTokenService userTokenService;

	private final ICloudFileService cloudFileService;

	private final PasswordEncoder passwordEncoder;

	private List<User> users = new ArrayList<>();

	@Override
	public void run(String... args) throws Exception {
		if (userRepo.count() < 1) {
			log.info("Initial data....");
			initializeUsers();
			audit(users.get(0));
			var lessons = initializeLessons();
			initializeLessonHistory(lessons);
			initializeWords();
			initializeSysvars();
			initRanking(UserLevel.BEGINNER);
			initRanking(UserLevel.MEDIUM);
		}
	}

	private void initializeUsers() {
		String adminUsername = "admin";
		String encodedPass = passwordEncoder.encode("1234");
		User adminUser = new User(adminUsername, "Chủ thớt", "admin@gmail.com", encodedPass);
		adminUser.setRole(Role.ADMIN);
		User user1 = new User("duyhelloworld", "Duy Pham", "duy0184466@huce.edu.vn", encodedPass);
		User user2 = new User("plus", "Khách VIP", "khachvip@huce.edu.vn", encodedPass);
		User user3 = new User("nguyenan", "Nguyễn An", "nguyenan@gmail.com", encodedPass);
		User user4 = new User("tranbinh", "Trần Bình", "tranbinh@yahoo.com", encodedPass);
		User user5 = new User("lechau", "Lê Châu", "lechau@outlook.com", encodedPass);
		User user6 = new User("phamduy", "Phạm Duy", "phamduy@huce.edu.vn", encodedPass);
		User user7 = new User("vuhoang", "Vũ Hoàng", "vuhoang@mail.com", encodedPass);
		User user8 = new User("tranlinh", "Trần Linh", "tranlinh@protonmail.com", encodedPass);
		User user9 = new User("nguyenminh", "Nguyễn Minh", "nguyenminh@icloud.com", encodedPass);
		User user10 = new User("phanngoc", "Phan Ngọc", "phanngoc@hotmail.com", encodedPass);
		User user11 = new User("nguyenhuong", "Nguyễn Hương", "nguyenhuong@zoho.com", encodedPass);
		User user12 = new User("truongtuan", "Trương Tuấn", "truongtuan@gmail.com", encodedPass);
		User user13 = new User("dangngoc", "Đặng Ngọc", "dangngoc@outlook.com", encodedPass);
		User user14 = new User("hoangyen", "Hoàng Yến", "hoangyen@icloud.com", encodedPass);
		User user15 = new User("dinhhung", "Đinh Hùng", "dinhhung@mail.com", encodedPass);
		User user16 = new User("kimthoa", "Kim Thoa", "kimthoa@yahoo.com", encodedPass);
		User user17 = new User("ngothang", "Ngô Thắng", "ngothang@aol.com", encodedPass);
		User user18 = new User("phamthuy", "Phạm Thủy", "phamthuy@protonmail.com", encodedPass);
		User user19 = new User("lelong", "Lê Long", "lelong@gmail.com", encodedPass);
		User user20 = new User("buiha", "Bùi Hà", "buiha@live.com", encodedPass);
		this.users.addAll(List.of(adminUser, user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11,
				user12, user13, user14, user15, user16, user17, user18, user19, user20));
		userRepo.saveAll(users);
		for (User user : users) {
			audit(user);
			user.setAvatar(cloudFileService.saveAndGet(CloudFileType.USER_AVATAR, null));
			userRepo.save(user);
			userTokenService.addNew(user, TokenType.REFRESH, null);
		}
		log.info("Saved admin account '{}'", adminUser.getUsername());
		log.info("Saved user : {}", users.stream().map(u -> u.getUsername()).toList());
	}

	private void audit(User user) {
		var token = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(token);
	}

	private void initializeLessonHistory(Lesson[] lessons) {
		lessonHistoryRepo.saveAll(
				Stream.concat(
						users.subList(0, users.size() / 2 - 1).stream()
								.map(u -> createLessonHistory(lessons[0], LessonStatus.ONGOING, u)),
						users.subList(users.size() / 2, users.size() - 1).stream()
								.map(u -> createLessonHistory(lessons[1], LessonStatus.COMPLETED, u)))
						.collect(Collectors.toList()));
	}

	private LessonHistory createLessonHistory(Lesson lesson, LessonStatus status, User user) {
		LessonHistory lessonHistory = new LessonHistory();
		lessonHistory.setOwner(user.getId());
		lessonHistory.setLesson(lesson);
		lessonHistory.setStatus(status);
		return lessonHistory;
	}

	private void initRanking(UserLevel level) {
		Ranking ranking = new Ranking();
		ranking.setLevel(level);
		ranking = rankingRepo.save(ranking);
		for (int i = 1; i < users.size(); i++) {
			User currentUser = users.get(i);
			if (currentUser.getLevel() == level) {
				var rankedUser = new RankedUser(i, (long) 2100 - i * 100);
				rankedUser.setRanking(ranking);
				audit(currentUser);
				rankedUserRepo.save(rankedUser);
			}
		}
		log.info("Saved ranking for level {}", level);
	}

	private void initializeWords() {
		List<Word> words = List.of(
				createWord("animal", "động vật", "['anəm(ə)l]", WordType.NOUN, "Our world needs animals",
						"Thế giới này cần động vật", "Animals are important to the ecosystem",
						"Động vật quan trọng với hệ sinh thái"),
				createWord("fast", "nhanh", "[fæst]", WordType.ADJECTIVE, "He runs fast", "Anh ấy chạy nhanh",
						"The car is fast", "Chiếc xe chạy nhanh"),
				createWord("slow", "chậm", "[sloʊ]", WordType.ADJECTIVE, "The snail is slow", "Con ốc sên chậm",
						"The computer is slow", "Máy tính chậm"),
				createWord("big", "lớn", "[bɪɡ]", WordType.ADJECTIVE, "The elephant is big", "Con voi to lớn",
						"The house is big", "Ngôi nhà to lớn"),
				createWord("small", "nhỏ", "[smɔːl]", WordType.ADJECTIVE, "The mouse is small", "Con chuột nhỏ",
						"The book is small", "Cuốn sách nhỏ"),
				createWord("cute", "dễ thương", "[kjuːt]", WordType.ADJECTIVE, "The kitten is cute",
						"Con mèo con đáng yêu", "The puppy is cute", "Con chó con đáng yêu"),
				createWord("scary", "đáng sợ", "[ˈskɛri]", WordType.ADJECTIVE, "The tiger is scary", "Con hổ đáng sợ",
						"The movie is scary", "Bộ phim đáng sợ"),
				createWord("friendly", "thân thiện", "[ˈfrɛndli]", WordType.ADJECTIVE, "My dog is friendly",
						"Chó của tôi thân thiện", "The people here are friendly", "Những người ở đây thân thiện"),
				createWord("wild", "hoang dã", "[waɪld]", WordType.ADJECTIVE, "The lion is wild", "Con sư tử hoang dã",
						"The forest is wild", "Rừng hoang dã"),
				createWord("children", "bọn trẻ", "[children]", WordType.NOUN, "The children are playing",
						"Những đứa trẻ đang chơi", "The children's toys", "Đồ chơi của trẻ con"),
				createWord("monkey", "con khỉ", "[ˈmʌŋki]", WordType.NOUN, "The monkey is climbing a tree",
						"Con khỉ đang leo cây", "The monkey is eating a banana", "Con khỉ đang ăn chuối"),
				createWord("is", "là", "[ɪz]", WordType.VERB, "The cat is black", "Con mèo màu đen", "He is a student",
						"Anh ấy là một sinh viên"),
				createWord("and", "và", "[ænd]", WordType.CONJUNCTION, "I like apples and oranges",
						"Tôi thích táo và cam", "He is tall and handsome", "Anh ấy cao và đẹp trai"),
				createWord("like", "thích", "[laɪk]", WordType.VERB, "I like dogs", "Tôi thích chó",
						"She likes to dance", "Cô ấy thích nhảy"),
				createWord("banana", "chuối", "[bəˈnɑːnə]", WordType.NOUN, "The monkey is eating a banana",
						"Con khỉ đang ăn chuối", "I like bananas", "Tôi thích chuối"),
				createWord("run", "chạy", "[rʌn]", WordType.VERB, "The dog is running", "Con chó đang chạy",
						"Let's run together", "Hãy cùng nhau chạy"),
				createWord("look", "nhìn", "[lʊk]", WordType.VERB, "Look at the bird", "Nhìn con chim",
						"She looks beautiful", "Cô ấy trông đẹp"),
				createWord("so", "rất, vì vậy", "[soʊ]", WordType.CONJUNCTION, "He is so tall", "Anh ấy rất cao",
						"I am so tired", "Tôi rất mệt"),
				createWord("I", "tôi", "[aɪ]", WordType.PRONOUN, "I am a student", "Tôi là một sinh viên",
						"I like to eat pizza", "Tôi thích ăn pizza"),
				createWord("you", "bạn", "[juː]", WordType.PRONOUN, "You are very kind", "Bạn rất tốt bụng",
						"Can you help me?", "Bạn có thể giúp tôi không?"));

		for (Word word : words) {
			word.setAudio(cloudFileService.saveAndGet(CloudFileType.WORD_AUDIO, word.getValue()));
			word.setImage(cloudFileService.saveAndGet(CloudFileType.WORD_IMAGE, word.getValue()));
		}
		wordRepo.saveAll(words);
		log.info("Saved {} words", words.size());
	}

	private Word createWord(String value, String meaning, String pronunciation, WordType type, String example1,
			String translation1, String example2, String translation2) {
		Word word = new Word(value, meaning, pronunciation, type);
		word.setExamples(Set.of(new Example(example1, translation1), new Example(example2, translation2)));
		return word;
	}

	private void initializeSysvars() {
		List<Sysvar> sysvars = List.of(
				new Sysvar(SysvarKey.DIAMONDS_PER_RETRY, "100"),
				new Sysvar(SysvarKey.DIAMOND_PER_VND, "1"),
				new Sysvar(SysvarKey.RETRY_PER_DAY, "5"));
		sysvarRepo.saveAll(sysvars);
		log.info("Saved 3 sysvars");
	}

	private Lesson[] initializeLessons() {
		Lesson beginnerLesson1 = new Lesson("Kiểm tra level", 20, 5, UserLevel.BEGINNER);
		Question question1ForLesson1 = new Question(QuestionType.GIVE_MEAN_CHOOSE_WORD);

		Answer answerCorrect1ForQ1L1 = new Answer("run", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "run"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "run"), true);
		Answer answerIncorrect1ForQ1L1 = new Answer("like", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "like"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "like"), false);
		Answer answerIncorrect2ForQ1L1 = new Answer("wild", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "wild"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "wild"), false);
		Answer answerIncorrect3ForQ1L1 = new Answer("scary", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "scary"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "scary"), false);

		answerCorrect1ForQ1L1.setQuestion(question1ForLesson1);
		answerIncorrect1ForQ1L1.setQuestion(question1ForLesson1);
		answerIncorrect2ForQ1L1.setQuestion(question1ForLesson1);
		answerIncorrect3ForQ1L1.setQuestion(question1ForLesson1);
		question1ForLesson1.setAnswers(Set.of(answerCorrect1ForQ1L1, answerIncorrect1ForQ1L1, answerIncorrect2ForQ1L1,
				answerIncorrect3ForQ1L1));

		Question question2ForLesson1 = new Question(QuestionType.GIVE_AUDIO_CHOOSE_WORD);
		Answer answerCorrect1ForQ2L1 = new Answer("run", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "run"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "run"), true);
		Answer answerIncorrect1ForQ2L1 = new Answer("like", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "run"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "like"), false);
		Answer answerIncorrect2ForQ2L1 = new Answer("wild", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "run"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "wild"), false);
		Answer answerIncorrect3ForQ2L1 = new Answer("scary", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "run"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "scary"), false);

		answerCorrect1ForQ2L1.setQuestion(question2ForLesson1);
		answerIncorrect1ForQ2L1.setQuestion(question2ForLesson1);
		answerIncorrect2ForQ2L1.setQuestion(question2ForLesson1);
		answerIncorrect3ForQ2L1.setQuestion(question2ForLesson1);
		question2ForLesson1.setAnswers(Set.of(answerCorrect1ForQ2L1, answerIncorrect1ForQ2L1, answerIncorrect2ForQ2L1,
				answerIncorrect3ForQ2L1));

		Question question3ForLesson1 = new Question(QuestionType.MATCHING);
		Map<String, String> matchingPairsForQ3L1 = new HashMap<>();
		matchingPairsForQ3L1.put("I", "tôi");
		matchingPairsForQ3L1.put("like", "thích");
		matchingPairsForQ3L1.put("friendly", "thân thiện");
		matchingPairsForQ3L1.put("animal", "động vật");
		question3ForLesson1.setMatching(LessonUtil.encode(matchingPairsForQ3L1));

		Question question4ForLesson1 = new Question(QuestionType.GIVE_SENTENSE_REARRANGE_WORDS);
		question4ForLesson1.setSentenseMeaning("Tôi thích động vật hoang dã");
		question4ForLesson1.setSentenseWords("I like wild animal");

		Question question5ForLesson1 = new Question(QuestionType.GIVE_SENTENSE_REARRANGE_WORDS);
		question5ForLesson1.setSentenseMeaning("Tôi thích động vật hoang dã");
		question5ForLesson1.setSentenseWords("I like wild animal");
		question5ForLesson1.setUnrelatedWords("friendly run that");

		Question question6ForLesson1 = new Question(QuestionType.GIVE_AUDIO_REARRANGE_WORDS);
		question6ForLesson1.setSentenseAudio("https://tatoeba.org/en/audio/download/1110206");
		question6ForLesson1.setSentenseWords("I like animals");

		Question question7ForLesson1 = new Question(QuestionType.GIVE_AUDIO_REARRANGE_WORDS);
		question7ForLesson1.setSentenseAudio("https://tatoeba.org/en/audio/download/1110206");
		question7ForLesson1.setSentenseWords("I like animals");
		question7ForLesson1.setUnrelatedWords("friendly run that");

		var questionsForLesson1 = Set.of(question1ForLesson1, question2ForLesson1, question3ForLesson1,
				question4ForLesson1, question5ForLesson1, question6ForLesson1, question7ForLesson1);
		questionRepo.saveAll(questionsForLesson1);
		beginnerLesson1.setQuestions(questionsForLesson1);
		// Lesson 1

		Lesson beginnerLesson2 = new Lesson("Các đại từ nhân xưng", 20, 8, UserLevel.BEGINNER);

		Question question1ForLesson2 = new Question(QuestionType.GIVE_MEAN_CHOOSE_WORD);
		Answer answerCorrect1ForQ1L2 = new Answer("I", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "I"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "I"), true);
		Answer answerIncorrect1ForQ1L2 = new Answer("you", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "you"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "you"), false);
		Answer answerIncorrect2ForQ1L2 = new Answer("he", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "he"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "he"), false);
		Answer answerIncorrect3ForQ1L2 = new Answer("she", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "she"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "she"), false);

		answerCorrect1ForQ1L2.setQuestion(question1ForLesson2);
		answerIncorrect1ForQ1L2.setQuestion(question1ForLesson2);
		answerIncorrect2ForQ1L2.setQuestion(question1ForLesson2);
		answerIncorrect3ForQ1L2.setQuestion(question1ForLesson2);
		question1ForLesson2.setAnswers(Set.of(answerCorrect1ForQ1L2, answerIncorrect1ForQ1L2, answerIncorrect2ForQ1L2,
				answerIncorrect3ForQ1L2));

		Question question2ForLesson2 = new Question(QuestionType.GIVE_AUDIO_CHOOSE_WORD);
		Answer answerCorrect1ForQ2L2 = new Answer("I", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "I"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "I"), true);
		Answer answerIncorrect1ForQ2L2 = new Answer("you", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "you"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "you"), false);
		Answer answerIncorrect2ForQ2L2 = new Answer("he", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "he"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "he"), false);
		Answer answerIncorrect3ForQ2L2 = new Answer("she", cloudFileService.getUrl(CloudFileType.WORD_AUDIO, "she"),
				cloudFileService.getUrl(CloudFileType.WORD_IMAGE, "she"), false);

		answerCorrect1ForQ2L2.setQuestion(question2ForLesson2);
		answerIncorrect1ForQ2L2.setQuestion(question2ForLesson2);
		answerIncorrect2ForQ2L2.setQuestion(question2ForLesson2);
		answerIncorrect3ForQ2L2.setQuestion(question2ForLesson2);
		question2ForLesson2.setAnswers(Set.of(answerCorrect1ForQ2L2, answerIncorrect1ForQ2L2, answerIncorrect2ForQ2L2,
				answerIncorrect3ForQ2L2));

		Question question3ForLesson2 = new Question(QuestionType.MATCHING);
		Map<String, String> matchingPairsForQ3L2 = new HashMap<>();
		matchingPairsForQ3L2.put("I", "tôi");
		matchingPairsForQ3L2.put("you", "bạn");
		matchingPairsForQ3L2.put("he", "anh ấy");
		matchingPairsForQ3L2.put("she", "cô ấy");
		question3ForLesson2.setMatching(LessonUtil.encode(matchingPairsForQ3L2));

		Question question4ForLesson2 = new Question(QuestionType.GIVE_SENTENSE_REARRANGE_WORDS);
		question4ForLesson2.setSentenseMeaning("Anh ấy rất thân thiện");
		question4ForLesson2.setSentenseWords("He is very friendly");

		Question question5ForLesson2 = new Question(QuestionType.GIVE_SENTENSE_REARRANGE_WORDS);
		question5ForLesson2.setSentenseMeaning("Cô ấy thích bạn");
		question5ForLesson2.setSentenseWords("She likes you");
		question5ForLesson2.setUnrelatedWords("run eat big");

		Question question6ForLesson2 = new Question(QuestionType.GIVE_AUDIO_REARRANGE_WORDS);
		question6ForLesson2.setSentenseAudio("https://tatoeba.org/en/audio/download/1000202");
		question6ForLesson2.setSentenseWords("They are students");

		Question question7ForLesson2 = new Question(QuestionType.GIVE_AUDIO_REARRANGE_WORDS);
		question7ForLesson2.setSentenseAudio("https://tatoeba.org/en/audio/download/1000202");
		question7ForLesson2.setSentenseWords("They are students");
		question7ForLesson2.setUnrelatedWords("fast slow big");

		var questionsForLesson2 = Set.of(question1ForLesson2, question2ForLesson2, question3ForLesson2,
				question4ForLesson2, question5ForLesson2, question6ForLesson2, question7ForLesson2);
		questionRepo.saveAll(questionsForLesson2);
		beginnerLesson2.setQuestions(questionsForLesson2);
		var result = new Lesson[] { beginnerLesson1, beginnerLesson2 };
		lessonRepo.saveAll(Arrays.asList(result));
		log.info("Saved lessons : {}", Arrays.stream(result).map(r -> r.getName()).toList());
		return result;
	}
}
