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
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.core.Role;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.domains.exam.repo.LessonRepository;
import vn.edu.huce.beforeigner.domains.vocab.Example;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.domains.vocab.WordType;
import vn.edu.huce.beforeigner.domains.vocab.repo.WordRepository;
import vn.edu.huce.beforeigner.infrastructures.storagemodule.abstracts.IImageService;
import vn.edu.huce.beforeigner.infrastructures.storagemodule.abstracts.ISoundService;

@Slf4j
@Component
@RequiredArgsConstructor
public class OnStartUp implements CommandLineRunner {

	private final UserRepository userRepo;

	private final PasswordEncoder passwordEncoder;

	private final LessonRepository lessonRepo;

	private final WordRepository wordRepo;

	private final ISoundService soundService;

	private final IImageService imageService;

	@Override
	public void run(String... args) throws Exception {
		User adminUser = extractAdmin();

		var token = new UsernamePasswordAuthenticationToken(adminUser, null, adminUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(token);

		if (wordRepo.count() < 1) {
			Word w1 = new Word("animal", "động vật, loài vật, thú vật",
				"['anəm(ə)l]", WordType.NOUN);
			Example e1_1 = new Example("Our world need animal",
			"Thế giới này cần động vật");
			Example e1_2 = new Example("Animals are important to the ecosystem",
			"Động vật quan trọng với hệ sinh thái");
			w1.setExamples(Set.of(e1_1, e1_2));

			Word w2 = new Word("fast", "nhanh, mau, lâu phai", 
			"[fæst]", WordType.ADJECTIVE);
			Example e2_1 = new Example("He runs fast", "Anh ấy chạy nhanh");
			Example e2_2 = new Example("The car is fast", "Chiếc xe chạy nhanh");
			w2.setExamples(Set.of(e2_1, e2_2));

			Word w3 = new Word("slow", "chậm, chậm chạp, buồn chán",
				"[sloʊ]", WordType.ADJECTIVE);
			Example e3_1 = new Example("The snail is slow", "Con ốc sên chậm");
			Example e3_2 = new Example("The computer is slow", "Máy tính chậm");
			w3.setExamples(Set.of(e3_1, e3_2));
			Word w4 = new Word("big", "to, lớn, rộng, bự", 
				"[bɪɡ]",  WordType.ADJECTIVE);
			Example e4_1 = new Example("The elephant is big", "Con voi to lớn");
			Example e4_2 = new Example("The house is big", "Ngôi nhà to lớn");
			w4.setExamples(Set.of(e4_1, e4_2));
			
			Word w5 = new Word("small", "nhỏ, bé, vụn vặt",
			 "[smɔːl]", WordType.ADJECTIVE);
			Example e5_1 = new Example("The mouse is small", "Con chuột nhỏ");
			Example e5_2 = new Example("The book is small", "Cuốn sách nhỏ");
			w5.setExamples(Set.of(e5_1, e5_2));

			Word w6 = new Word("cute", "dễ thương, đáng yêu, có duyên",
				"[kjuːt]",WordType.ADJECTIVE);
			Example e6_1 = new Example("The kitten is cute", "Con mèo con đáng yêu");
			Example e6_2 = new Example("The puppy is cute", "Con chó con đáng yêu");
			w6.setExamples(Set.of(e6_1, e6_2));

			Word w7 = new Word("scary", "đáng sợ, ghê sợ, kinh khủng", 
			"[ˈskɛri]", WordType.ADJECTIVE);
			Example e7_1 = new Example("The tiger is scary", "Con hổ đáng sợ");
			Example e7_2 = new Example("The movie is scary", "Bộ phim đáng sợ");
			w7.setExamples(Set.of(e7_1, e7_2));

			Word w8 = new Word("friendly", "thân thiện, thân mật",
			"[ˈfrɛndli]", WordType.ADJECTIVE);
			Example e8_1 = new Example("My dog is friendly",
			"Chó của tôi thân thiện");
			Example e8_2 = new Example("The people here are friendly",
			"Những người ở đây thân thiện");
			w8.setExamples(Set.of(e8_1, e8_2));

			Word w9 = new Word("wild", "hoang dã", "[waɪld]", WordType.ADJECTIVE);
			Example e9_1 = new Example("The lion is wild", "Con sư tử hoang dã");
			Example e9_2 = new Example("The forest is wild", "Rừng hoang dã");
			w9.setExamples(Set.of(e9_1, e9_2));

			Word w10 = new Word("children", "bọn trẻ, trẻ em", "[children]",			WordType.NOUN);
			Example e10_1 = new Example("The children are playing", "Những đứa trẻ đang chơi");
			Example e10_2 = new Example("The children's toys","Đồ chơi của trẻ con");
			w10.setExamples(Set.of(e10_1, e10_2));

			Word w11 = new Word("monkey", "con khỉ", "[ˈmʌŋki]",
			WordType.NOUN);
			Example e11_1 = new Example("The monkey is climbing a tree", "Con khỉ đang leo cây");
			Example e11_2 = new Example("The monkey is eating a banana", "Con khỉ đang ăn chuối");
			w11.setExamples(Set.of(e11_1, e11_2));

			Word w12 = new Word("is", "là", "[ɪz]",					 WordType.VERB);
			Example e12_1 = new Example("The cat is black", "Con mèo màu đen");
			Example e12_2 = new Example("He is a student", "Anh ấy là một sinh viên");
			w12.setExamples(Set.of(e12_1, e12_2));

			Word w13 = new Word("and", "và", "[ænd]",
					 WordType.CONJUNCTION);
			Example e13_1 = new Example("I like apples and oranges", "Tôi thích táo và cam");
			Example e13_2 = new Example("He is tall and handsome", "Anh ấy cao và đẹp trai");
			w13.setExamples(Set.of(e13_1, e13_2));

			Word w14 = new Word("like", "thích", "[laɪk]",
					WordType.VERB);
			Example e14_1 = new Example("I like dogs", "Tôi thích chó");
			Example e14_2 = new Example("She likes to dance", "Cô ấy thích nhảy");
			w14.setExamples(Set.of(e14_1, e14_2));

			Word w15 = new Word("banana", "chuối", "[bəˈnɑːnə]",
					 WordType.NOUN);
			Example e15_1 = new Example("The monkey is eating a banana", "Con khỉ đang ăn chuối");
			Example e15_2 = new Example("I like bananas", "Tôi thích chuối");
			w15.setExamples(Set.of(e15_1, e15_2));

			Word w16 = new Word("run", "chạy", "[rʌn]",
					 WordType.VERB);
			Example e16_1 = new Example("The dog is running", "Con chó đang chạy");
			Example e16_2 = new Example("Let's run together", "Hãy cùng nhau chạy");
			w16.setExamples(Set.of(e16_1, e16_2));

			Word w17 = new Word("look", "nhìn", "[lʊk]",
					WordType.VERB);
			Example e17_1 = new Example("Look at the bird", "Nhìn con chim");
			Example e17_2 = new Example("She looks beautiful", "Cô ấy trông đẹp");
			w17.setExamples(Set.of(e17_1, e17_2));

			Word w18 = new Word("so", "rất, vì vậy", "[soʊ]",
					 WordType.CONJUNCTION);
			Example e18_1 = new Example("He is so tall", "Anh ấy rất cao");
			Example e18_2 = new Example("I am so tired", "Tôi rất mệt");
			w18.setExamples(Set.of(e18_1, e18_2));

			Word w19 = new Word("I", "tôi", "[aɪ]",
			WordType.PRONOUN);
			Example e19_1 = new Example("I am a student", "Tôi là một sinh viên");
			Example e19_2 = new Example("I like to eat pizza", "Tôi thích ăn pizza");
			w19.setExamples(Set.of(e19_1, e19_2));
			
			Word w20 = new Word("you", "bạn", "[juː]",
					 WordType.PRONOUN);
			Example e20_1 = new Example("You are very kind", "Bạn rất tốt bụng");
			Example e20_2 = new Example("Can you help me?", "Bạn có thể giúp tôi không?");
			w20.setExamples(Set.of(e20_1, e20_2));
			
			Word w21 = new Word("we", "chúng tôi", "[wi]",
					WordType.PRONOUN);
			Example e21_1 = new Example("We are friends", "Chúng tôi là bạn bè");
			Example e21_2 = new Example("We will go to the beach tomorrow", "Chúng tôi sẽ đi biển ngày mai");
			w21.setExamples(Set.of(e21_1, e21_2));
			
			Word w22 = new Word("they", "họ", "[ðeɪ]",
					 WordType.PRONOUN);
			Example e22_1 = new Example("They are students", "Họ là sinh viên");
			Example e22_2 = new Example("They like to play football", "Họ thích chơi bóng đá");
			w22.setExamples(Set.of(e22_1, e22_2));
			
			Word w23 = new Word("he", "anh ấy, ông ấy", "[hiː]",
					 WordType.PRONOUN);
			Example e23_1 = new Example("He is a doctor", "Anh ấy là một bác sĩ");
			Example e23_2 = new Example("He likes to read books", "Anh ấy thích đọc sách");
			w23.setExamples(Set.of(e23_1, e23_2));
			
			Word w24 = new Word("she", "cô ấy, bà ấy", "[ʃiː]",
					 WordType.PRONOUN);
			Example e24_1 = new Example("She is a teacher", "Cô ấy là một giáo viên");
			Example e24_2 = new Example("She likes to cook", "Cô ấy thích nấu ăn");
			w24.setExamples(Set.of(e24_1, e24_2));
			
			Word w25 = new Word("it", "nó", "[ɪt]",
					 WordType.PRONOUN);
			Example e25_1 = new Example("It is a book", "Đó là một cuốn sách");
			Example e25_2 = new Example("It is raining", "Trời đang mưa");
			w25.setExamples(Set.of(e25_1, e25_2));
			
			Word w26 = new Word("can", "có thể", "[kæn]",
					 WordType.VERB);
			Example e26_1 = new Example("Can you swim?", "Bạn có biết bơi không?");
			Example e26_2 = new Example("I can speak English", "Tôi có thể nói tiếng Anh");
			w26.setExamples(Set.of(e26_1, e26_2));
			
			Word w27 = new Word("dog", "con chó", "[dɒɡ]",
					 WordType.NOUN);
			Example e27_1 = new Example("The dog is barking", "Con chó đang sủa");
			Example e27_2 = new Example("I have a dog", "Tôi có một con chó");
			w27.setExamples(Set.of(e27_1, e27_2));
			
			Word w28 = new Word("cat", "con mèo", "[kæt]",
					 WordType.NOUN);
			Example e28_1 = new Example("The cat is sleeping", "Con mèo đang ngủ");
			Example e28_2 = new Example("I like cats", "Tôi thích mèo");
			w28.setExamples(Set.of(e28_1, e28_2));

			Word w29 = new Word("a", "một", "[ə]",
			WordType.INDEFINITE_ARTICLE);
			Word w30 = new Word("an", "một", "[æn]",
			 WordType.INDEFINITE_ARTICLE);
			Word w31 = new Word("the", "cái, những", "[ðə]",
			 WordType.DEFINITE_ARTICLE);

			var words = List.of(w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, w13, w14, w15, w16, w17, w18, w19, w20, w21, w22, w23, w24, w25, w26, w27, w28, w29, w30, w31);
			for (Word word : words) {
				word.setAudio(soundService.getAndSaveWordAudio(word.getValue()));
				word.setImage(imageService.getAndSaveWordImage(word.getValue()));
			}
			wordRepo.saveAll(words);
			log.info("Saved 10 word");

			Lesson l1 = new Lesson("Bài học các từ vựng cơ bản", 20, 5, UserLevel.BEGINNER);		
			Lesson l2 = new Lesson("Bài học các đại từ nhân xưng", 20, 8, UserLevel.BEGINNER);
			lessonRepo.saveAll(List.of(l1, l2));
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
