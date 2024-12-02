USE `be-foreigner`;

INSERT INTO `user` (id, username, fullname, avatar_url, email, password, streak_days, temp_code, is_first_try, is_allow_mail, is_allow_notification, role, level, learn_count_availaible, plan, provider) 
VALUES  
    (1, 'admin', 'Chủ thớt', 'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732243632/user_avatar/wqpodmvtdx2z0rthifkp.png', 'admin@gmail.com', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 10, null, true, true, true, 'ADMIN', 'BEGINNER', 5, 'FREE', 'LOCAL'),  
    (2, 'duyhelloworld', 'Duy Pham', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png', 'duy0184466@huce.edu.vn', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 0, null, true, true, true, 'USER', 'BEGINNER', 5, 'FREE', 'LOCAL'),  
    (3, 'plus', 'Khách VIP', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png','khachvip@huce.edu.vn', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 5, null, true, true, true, 'USER', 'BEGINNER', 5, 'PREMIUM_MONTH', 'LOCAL'),  
    (4, 'lechau', 'Lê Châu', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png','lechau@outlook.com', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 1, null, true, true, true, 'USER', 'BEGINNER', 5, 'FREE', 'LOCAL'),  
    (5, 'phamduy', 'Phạm Duy', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png', 'phamduy@huce.edu.vn', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 0, null, true, true, true, 'USER', 'INTERMEDIATE', 5, 'FREE', 'LOCAL'),  
    (6, 'buiha', 'Bùi Hà', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png', 'buiha@live.com', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 1, null, true, true, true, 'USER', 'BEGINNER', 5, 'FREE', 'LOCAL');

INSERT INTO user_token (token, `type`, last_modified_at, last_modified_by, is_disabled) VALUES
    ('3e1c76e7-fc31-4dbf-81c5-8c9bfa4351d2', 'REFRESH', NOW(), 'admin', FALSE),
    ('7ad2f25c-3b9e-41c1-b20e-34c914ad923e', 'REFRESH', NOW(), 'duyhelloworld', FALSE),
    ('9d71c5de-bf1f-4f5e-908d-c54e1d4fbd94', 'REFRESH', NOW(), 'plus', FALSE),
    ('a8e2f91b-cf88-4f77-84bb-1f76c6714383', 'REFRESH', NOW(), 'lechau', FALSE),
    ('d6b22e91-75ea-4ab0-9603-9efec18461d6', 'REFRESH', NOW(), 'phamduy', FALSE),
    ('ec8c37d3-3e59-4c4c-b3ad-1cd4185c2cb1', 'REFRESH', NOW(), 'buiha', FALSE),
    ('cgkY0O2aQIqKpH7ftIe2G-:APA91bEqstwjNGiWIVnoD41hVQPJ5yv2HOEB6iNhfJ2mTcz7bzKLnqjaElini2eeB5K3M3XC5qg5pMu0t-EnXeS1S8WLET_reA04C8xG6fmI56OGx6TAKew', 'NOTIFICATION', NOW(), 'duyhelloworld', FALSE),
    ('cgkY0O2aQIqKpH7ftIe2G-:APA91bEqstwjNGiWIVnoD41hVQPJ5yv2HOEB6iNhfJ2mTcz7bzKLnqjaElini2eeB5K3M3XC5qg5pMu0t-EnXeS1S8WLET_reA04C8xG6fmI56OGx6TAKew', 'NOTIFICATION', NOW(), 'phamduy', FALSE);
    
INSERT INTO word (id, value, mean, phonetic, audio_url, image_url, created_at, created_by, is_deleted) VALUES 
-- Chủ đề: Gia đình
(1, 'Family', 'Gia đình', '/ˈfæm.əl.i/', 'https://d1qx7pbj0dvboc.cloudfront.net/family.mp3', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Family_Portrait.jpg/800px-Family_Portrait.jpg', NOW(), 'admin', FALSE),
(2, 'Father', 'Cha', '/ˈfɑː.ðər/', 'https://d1qx7pbj0dvboc.cloudfront.net/father.mp3', 'https://images.unsplash.com/photo-1657664058220-a1bfc04e2e14?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', NOW(), 'admin', FALSE),
(3, 'Mother', 'Mẹ', '/ˈmʌð.ər/', 'https://d1qx7pbj0dvboc.cloudfront.net/mother.mp3', 'https://images.unsplash.com/photo-1542385151-efd9000785a0?q=80&w=1378&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', NOW(), 'admin', FALSE),
(4, 'Grandparent', 'Ông bà', '/ˈɡræn.per.ənts/', 'https://d1qx7pbj0dvboc.cloudfront.net/grandparent.mp3', 'https://images.unsplash.com/photo-1581579439002-e29ac578f8d4?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fGdyYW5kcGFyZW50fGVufDB8fDB8fHwy', NOW(), 'admin', FALSE),
(5, 'Child', 'Con cái', '/tʃaɪld/', 'https://d1qx7pbj0dvboc.cloudfront.net/child.mp3', 'https://images.unsplash.com/photo-1676286111583-9fec2694b26c?q=80&w=1438&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', NOW(), 'admin', FALSE),
(6, 'Brother', 'Anh trai', '/ˈbrʌð.ər/', 'https://d1qx7pbj0dvboc.cloudfront.net/brother.mp3', 'https://images.unsplash.com/photo-1502143135356-dcdb8a9a3da6?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', NOW(), 'admin', FALSE),
(7, 'Sister', 'Chị gái', '/ˈsɪs.tər/', 'https://d1qx7pbj0dvboc.cloudfront.net/sister.mp3', 'https://images.unsplash.com/photo-1476234251651-f353703a034d?q=80&w=1376&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', NOW(), 'admin', FALSE),
(8, 'Parent', 'Cha mẹ', 'ˈper(ə)nt', 'https://d1qx7pbj0dvboc.cloudfront.net/parent.mp3', 'https://abc-ksa.com/wp-content/uploads/2021/08/parening.png', NOW(), 'admin', FALSE),
-- Chủ đề: Trường học
(9, 'School', 'Trường học', '/skuːl/', 'https://d1qx7pbj0dvboc.cloudfront.net/school.mp3', 'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
(10, 'Student', 'Học sinh', '/ˈstuː.dənt/', 'https://d1qx7pbj0dvboc.cloudfront.net/student.mp3', 'https://www.everylearnereverywhere.org/wp-content/uploads/student-centered-learning.png', NOW(), 'admin', FALSE),
(11, 'Teacher', 'Giáo viên', '/ˈtiː.tʃər/', 'https://d1qx7pbj0dvboc.cloudfront.net/teacher.mp3', 'https://guru.com/blog/wp-content/uploads/2023/10/private-teacher-cost.jpg', NOW(), 'admin', FALSE),
(12, 'Classroom', 'Lớp học', '/ˈklæs.ruːm/', 'https://d1qx7pbj0dvboc.cloudfront.net/classroom.mp3', 'https://www.teachhub.com/wp-content/uploads/2020/05/Classroom-Management-for-an-Effective-Learning-Environment-768x512.jpg', NOW(), 'admin', FALSE),
(13, 'Homework', 'Bài tập về nhà', '/ˈhoʊm.wɝːk/', 'https://d1qx7pbj0dvboc.cloudfront.net/homework.mp3', 'https://northpolkorbit.org/wp-content/uploads/2024/05/GettyImages-175452558-589a4cd83df78caebc7ebc63.jpg', NOW(), 'admin', FALSE),
(14, 'Exam', 'Kỳ thi', '/ɪɡˈzæm/', 'https://d1qx7pbj0dvboc.cloudfront.net/exam.mp3', 'https://this.deakin.edu.au/wp-content/uploads/2016/06/female-asian-student-writing-exam-exercise-in-the-2023-11-27-05-18-09-utc-scaled.jpg', NOW(), 'admin', FALSE),
(15, 'Subject', 'Môn học', '/ˈsʌb.dʒekt/', 'https://d1qx7pbj0dvboc.cloudfront.net/subject.mp3', 'https://vieclam123.vn/ckfinder/userfiles/images/subject-la-gi.jpg', NOW(), 'admin', FALSE),
-- Chủ đề: Nghề nghiệp
(16, 'Jobs', 'Nghề nghiệp', '/dʒɒb/', 'https://d1qx7pbj0dvboc.cloudfront.net/jobs.mp3', 'https://tailieutienganh.edu.vn/public/files/upload/default/images/phu-am-danh-tu-dem-duoc-so-it-so-nhieu-khong-dem-duoc-tu-vung-nghe-nghiep-jobs-3.jpg', NOW(), 'admin', FALSE),
(17, 'Doctor', 'Bác sĩ', '/ˈdɒk.tər/', 'https://d1qx7pbj0dvboc.cloudfront.net/doctor.mp3', 'https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg?w=360', NOW(), 'admin', FALSE),
(18, 'Engineer', 'Kỹ sư', '/ˌen.dʒɪˈnɪr/', 'https://d1qx7pbj0dvboc.cloudfront.net/engineer.mp3', 'https://i0.wp.com/www.engineeringandleadership.com/wp-content/uploads/2019/02/Engineer.png?fit=975%2C651&ssl=1', NOW(), 'admin', FALSE),
(19, 'Nurse', 'Y tá', '/nɝːs/', 'https://d1qx7pbj0dvboc.cloudfront.net/nurse.mp3', 'https://cl-wpml.careerlink.vn/cam-nang-viec-lam/wp-content/uploads/2023/08/21085609/healthcare-workers-preventing-virus-quarantine-campaign-concept-cheerful-friendly-asian-female-physician-doctor-with-clipboard-during-daily-checkup-standing-white-background-1024x683.jpg', NOW(), 'admin', FALSE),
(20, 'Police', 'Cảnh sát', '/pəˈliːs/', 'https://d1qx7pbj0dvboc.cloudfront.net/police.mp3', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Police.gun.1.london.arp.jpg/640px-Police.gun.1.london.arp.jpg', NOW(), 'admin', FALSE),
(21, 'Pilot', 'Phi công', '/ˈpaɪ.lət/', 'https://d1qx7pbj0dvboc.cloudfront.net/pilot.mp3', 'https://i0.wp.com/aerocadet.com/blog/wp-content/uploads/2024/09/health-criteria-for-becoming-a-pilot.jpg?fit=1429%2C714&ssl=1', NOW(), 'admin', FALSE),
(22, 'Chef', 'Đầu bếp', '/ʃef/', 'https://d1qx7pbj0dvboc.cloudfront.net/chef.mp3', 'https://tamlong.com.vn/wp-content/uploads/gordon-ramsay-646367718a5f4.jpg', NOW(), 'admin', FALSE),
-- Chủ đề: Thời tiết
(23, 'Sunny', 'Nắng', '/ˈsʌn.i/', 'https://d1qx7pbj0dvboc.cloudfront.net/sunny.mp3', 'https://images.photowall.com/products/44323/sunny-day.jpg?h=699&q=85', NOW(), 'admin', FALSE),
(24, 'Rainy', 'Mưa', '/ˈreɪ.ni/', 'https://d1qx7pbj0dvboc.cloudfront.net/rainy.mp3', 'https://www.wellahealth.com/blog/wp-content/uploads/2021/09/6-ways-to-stay-healthy-during-the-rainy-season.jpg', NOW(), 'admin', FALSE),
(25, 'Cloudy', 'Nhiều mây', '/ˈklaʊ.di/', 'https://d1qx7pbj0dvboc.cloudfront.net/cloudy.mp3', 'https://media.istockphoto.com/id/598222542/photo/sky-background.jpg?s=612x612&w=0&k=20&c=WBAiCExAztT4SzWh4hIgmQwTG7VMJ5o9oObXHszmm8A=', NOW(), 'admin', FALSE),
(26, 'Windy', 'Có gió', '/ˈwɪn.di/', 'https://d1qx7pbj0dvboc.cloudfront.net/windy.mp3', 'https://img.freepik.com/premium-photo/cartoon-drawing-very-windy-day_1151123-32740.jpg?semt=ais_hybrid', NOW(), 'admin', FALSE),
(27, 'Snowy', 'Có tuyết', '/ˈsnoʊ.i/', 'https://d1qx7pbj0dvboc.cloudfront.net/snowy.mp3', 'https://www.highcountryweather.com/wp-content/uploads/2016/11/2016-november-03-how-snowy.jpg', NOW(), 'admin', FALSE),
(28, 'Stormy', 'Bão tố', '/ˈstɔːr.mi/', 'https://d1qx7pbj0dvboc.cloudfront.net/stormy.mp3', 'https://plus.unsplash.com/premium_photo-1726989863790-e31ee30770ce?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8c3Rvcm15JTIwd2VhdGhlcnxlbnwwfHwwfHx8MA%3D%3D', NOW(), 'admin', FALSE),
(29, 'Foggy', 'Sương mù', '/ˈfɒɡ.i/', 'https://d1qx7pbj0dvboc.cloudfront.net/foggy.mp3', 'https://grammaticus.blog/wp-content/uploads/2022/11/foggy-morning-street.jpg?w=640', NOW(), 'admin', FALSE),
-- Chủ đề: Động vật
(30, 'Dog', 'Chó', '/dɒɡ/', 'https://d1qx7pbj0dvboc.cloudfront.net/dog.mp3', 'https://cdn.britannica.com/79/232779-050-6B0411D7/German-Shepherd-dog-Alsatian.jpg', NOW(), 'admin', FALSE),
(31, 'Cat', 'Mèo', '/kæt/', 'https://d1qx7pbj0dvboc.cloudfront.net/cat.mp3', 'https://media.4-paws.org/9/c/9/7/9c97c38666efa11b79d94619cc1db56e8c43d430/Molly_006-2829x1886-2726x1886-1920x1328.jpg', NOW(), 'admin', FALSE),
(32, 'Bird', 'Chim', '/bɝːd/', 'https://d1qx7pbj0dvboc.cloudfront.net/bird.mp3', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Eopsaltria_australis_-_Mogo_Campground.jpg/640px-Eopsaltria_australis_-_Mogo_Campground.jpg', NOW(), 'admin', FALSE),
(33, 'Fish', 'Cá', '/fɪʃ/', 'https://d1qx7pbj0dvboc.cloudfront.net/fish.mp3', 'https://www.aqueon.com/-/media/project/oneweb/aqueon/us/blog/ways-to-know-your-fish-are-happy/fish-are-happy-and-healthy-1.png', NOW(), 'admin', FALSE),
(34, 'Rabbit', 'Thỏ', '/ˈræb.ɪt/', 'https://d1qx7pbj0dvboc.cloudfront.net/rabbit.mp3', 'https://www.vetcarepethospital.ca/wp-content/uploads/sites/247/2022/03/petrabbitcare-1-1280x650.jpg', NOW(), 'admin', FALSE),
(35, 'Horse', 'Ngựa', '/hɔːrs/', 'https://d1qx7pbj0dvboc.cloudfront.net/horse.mp3', 'https://cdn.media.amplience.net/i/jpl/NY-blog-thumbnail-Horse-Breeds?qlt=80&w=1920&h=960&sm=c', NOW(), 'admin', FALSE),
(36, 'Elephant', 'Voi', '/ˈel.ɪ.fənt/', 'https://d1qx7pbj0dvboc.cloudfront.net/elephant.mp3', 'https://i.natgeofe.com/n/b64060fa-343c-481b-a24d-7375fef34914/NationalGeographic_1425689_square.jpg', NOW(), 'admin', FALSE);

INSERT INTO lesson (id, name, target, level, type, cover_image_url, created_at, created_by, is_deleted)
VALUES 
    (1, 'Gia đình p1', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Family_Portrait.jpg/800px-Family_Portrait.jpg', NOW(), 'admin', FALSE),
    (2, 'Gia đình p2', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Family_Portrait.jpg/800px-Family_Portrait.jpg', NOW(), 'admin', FALSE),
    (3, 'Gia đình (Ôn)', 'REVIEW', 'BEGINNER', 'FREE_ACCESS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Family_Portrait.jpg/800px-Family_Portrait.jpg', NOW(), 'admin', FALSE),
    (4, 'Nghề nghiệp p1', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732931715/m7dz7yhxliv140btfdq7.png', NOW(), 'admin', FALSE),
    (5, 'Nghề nghiệp p2', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732931715/m7dz7yhxliv140btfdq7.png', NOW(), 'admin', FALSE),
    (6, 'Nghề nghiệp (Ôn)', 'REVIEW', 'BEGINNER', 'FREE_ACCESS', 'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732931715/m7dz7yhxliv140btfdq7.png', NOW(), 'admin', FALSE),
    (7, 'Trường học p1', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
    (8, 'Trường học p2', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
    (9, 'Trường học (Ôn)', 'REVIEW', 'BEGINNER', 'FREE_ACCESS', 'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
    (10, 'Động vật p1', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Animals_png_set_by_mossi889-d4uye4q.png/1200px-Animals_png_set_by_mossi889-d4uye4q.png', NOW(), 'admin', FALSE),
    (11, 'Động vật p2', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Animals_png_set_by_mossi889-d4uye4q.png/1200px-Animals_png_set_by_mossi889-d4uye4q.png', NOW(), 'admin', FALSE),
    (12, 'Động vật (Ôn)', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Animals_png_set_by_mossi889-d4uye4q.png/1200px-Animals_png_set_by_mossi889-d4uye4q.png', NOW(), 'admin', FALSE),
    (13, 'Thời tiết p1', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://www.un.org/sites/un2.un.org/files/styles/large-article-image-style-16-9/public/field/image/2023/03/52196025795_06f077377a_c.jpg', NOW(), 'admin', FALSE),
    (14, 'Thời tiết p2', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://www.un.org/sites/un2.un.org/files/styles/large-article-image-style-16-9/public/field/image/2023/03/52196025795_06f077377a_c.jpg', NOW(), 'admin', FALSE),
    (15, 'Thời tiết (ôn)', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://www.un.org/sites/un2.un.org/files/styles/large-article-image-style-16-9/public/field/image/2023/03/52196025795_06f077377a_c.jpg', NOW(), 'admin', FALSE),
    (16, 'Kiểm tra 5 tiết', 'TEST', 'BEGINNER', 'PLUS_ONLY', 'https://images.squarespace-cdn.com/content/v1/5f57c8da5b4e905978984460/5bf55f3d-42ad-466b-b279-8b406d512cd9/Exams.jpeg', NOW(), 'admin', FALSE);

INSERT INTO question (id, lesson_id, type, sentense_meaning, sentense_words, sentense_audio, unrelated_words, level, created_at, created_by, is_deleted) VALUES
-- Câu hỏi cho Lesson 1: Gia đình - Family - Father - Mother - Grandparent
    (1, 1, 'LEARN_BY_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (2, 1, 'LEARN_BY_AUDIO', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (3, 1, 'LEARN_BY_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (4, 1, 'LEARN_BY_AUDIO', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (5, 1, 'LEARN_BY_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (6, 1, 'LEARN_BY_AUDIO', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (7, 1, 'LEARN_BY_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (8, 1, 'LEARN_BY_AUDIO', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (9, 1, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Chúng tôi là gia đình', 'We are family', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (10, 1, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Đó là mẹ tôi', 'That is my mother', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (11, 1, 'GIVE_AUDIO_REARRANGE_WORDS', 'Đây là bố tôi', 'This is my father', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937422/audio/leiyjr3zfk3rhthfrsd6.mp3', NULL, 'EASY', NOW(), 'admin', FALSE), 
    (12, 1, 'GIVE_AUDIO_REARRANGE_WORDS', 'Cô ấy là mẹ của tôi', 'She is my mother', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937397/audio/ttcklx5wgjswnlqzqken.mp3', 'my your their', 'HARD', NOW(), 'admin', FALSE), 
    (13, 1, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Ông ấy là bố tôi', 'He is my father', NULL, 'gold like', 'EASY', NOW(), 'admin', FALSE),
    (14, 1, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'HARD', NOW(), 'admin', FALSE),
    (15, 1, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Ông ấy là bố tôi', 'He is my father', NULL, 'him mine am his', 'HARD', NOW(), 'admin', FALSE),
    (16, 1, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Đây là ông bà tôi', ' This is my grandparent', NULL, 'parent ', 'HARD', NOW(), 'admin', FALSE),
    (17, 1, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'HARD', NOW(), 'admin', FALSE),
-- Câu hỏi cho Lesson 2: Gia đình - Child - Parent - Brother - Sister
    (18, 2, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (19, 2, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (20, 2, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (21, 2, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (22, 2, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (23, 2, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (24, 2, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (25, 2, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (26, 2, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Đó là con của chúng tôi', 'That is our child', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (27, 2, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Tôi có một anh trai', 'I have a brother', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (28, 2, 'GIVE_AUDIO_REARRANGE_WORDS', 'Cô ấy là chị tôi', 'She is my sister', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937422/audio/leiyjr3zfk3rhthfrsd6.mp3', 'Her', 'EASY', NOW(), 'admin', FALSE), -- 'They are happy.'
    (29, 2, 'GIVE_AUDIO_REARRANGE_WORDS', 'Cha mẹ tôi ở đằng kia', 'My parent is there', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937397/audio/ttcklx5wgjswnlqzqken.mp3', 'my your their', 'HARD', NOW(), 'admin', FALSE), -- 'She is my teacher.'
    (30, 2, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Anh ấy là anh bạn à', 'Is he your brother ?', NULL, 'family father', 'EASY', NOW(), 'admin', FALSE),
    (31, 2, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Ông ấy là bố tôi', 'He is my father', NULL, 'him mine am his', 'HARD', NOW(), 'admin', FALSE),
    (32, 2, 'GIVE_AUDIO_REARRANGE_WORDS', 'Tôi có một chị gái và hai anh trai', 'I have a sister and two brother', '', 'parent three an', 'HARD', NOW(), 'admin', FALSE),
    (33, 2, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),    
-- Câu hỏi cho Lesson 3: Gia đình ôn lại
    (34, 3, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (35, 3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Anh ấy là anh bạn à', 'Is he your brother ?', NULL, 'family father', 'EASY', NOW(), 'admin', FALSE),
    (36, 3, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (37, 3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Tôi có một anh trai', 'I have a brother', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (38, 3, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (39, 3, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (40, 3, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (41, 3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Đó là con của chúng tôi', 'That is our child', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (42, 3, 'GIVE_AUDIO_REARRANGE_WORDS', 'Cô ấy là chị tôi', 'She is my sister', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937422/audio/leiyjr3zfk3rhthfrsd6.mp3', NULL, 'EASY', NOW(), 'admin', FALSE),
    (43, 3, 'GIVE_AUDIO_REARRANGE_WORDS', 'Anh trai của bạn là ai', 'Who is your brother ?', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732965290/audio/uilamzoggbnwage3ckch.mp3', 'my your their', 'HARD', NOW(), 'admin', FALSE),
    (44, 3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Anh ấy là anh bạn à', 'Is he your brother ?', NULL, 'family father', 'HARD', NOW(), 'admin', FALSE),
    (45, 3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Ông ấy là bố tôi', 'He is my father', NULL, 'him mine am his', 'HARD', NOW(), 'admin', FALSE),
    (46, 3, 'GIVE_AUDIO_REARRANGE_WORDS', 'Tôi có một chị gái và hai anh trai', 'I have a sister and two brother', '', 'parent three an', 'HARD', NOW(), 'admin', FALSE),
    (47, 3, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'HARD', NOW(), 'admin', FALSE), 
-- Câu hỏi bài học vip
    (48, 3, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (49, 3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Anh ấy là anh bạn à', 'Is he your brother ?', NULL, 'family father', 'EASY', NOW(), 'admin', FALSE),
    (50, 3, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (51, 3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Đó là con của chúng tôi', 'That is our child', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (52, 3, 'GIVE_AUDIO_REARRANGE_WORDS', 'Cô ấy là chị tôi', 'She is my sister', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937422/audio/leiyjr3zfk3rhthfrsd6.mp3', NULL, 'EASY', NOW(), 'admin', FALSE),
    (53, 3, 'GIVE_AUDIO_REARRANGE_WORDS', 'Anh trai của bạn là ai', 'Who is your brother ?', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732965290/audio/uilamzoggbnwage3ckch.mp3', 'my your their', 'EASY', NOW(), 'admin', FALSE),
    (54, 3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Anh ấy là anh bạn à', 'Is he your brother ?', NULL, 'family father', 'EASY', NOW(), 'admin', FALSE),
    (55, 3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Ông ấy là bố tôi', 'He is my father', NULL, 'him mine am his', 'EASY', NOW(), 'admin', FALSE),
    (56, 3, 'GIVE_AUDIO_REARRANGE_WORDS', 'Tôi có một chị gái và hai anh trai', 'I have a sister and two brother', '', 'parent three an', 'EASY', NOW(), 'admin', FALSE),
    (57, 3, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'HARD', NOW(), 'admin', FALSE);

-- Chèn các đáp án vào bảng answer
INSERT INTO answer (question_id, word_id, is_true, created_at, created_by, is_deleted) VALUES
    -- Câu hỏi học mới lesson 1
    (1, 1, TRUE, NOW(), 'admin', FALSE),
    (2, 1, TRUE, NOW(), 'admin', FALSE),
    (3, 2, TRUE, NOW(), 'admin', FALSE),
    (4, 2, TRUE, NOW(), 'admin', FALSE),
    (5, 3, TRUE, NOW(), 'admin', FALSE),
    (6, 3, TRUE, NOW(), 'admin', FALSE),
    (7, 4, TRUE, NOW(), 'admin', FALSE),
    (8, 4, TRUE, NOW(), 'admin', FALSE),
    (14, 4, TRUE, NOW(), 'admin', FALSE),
    (14, 3, FALSE, NOW(), 'admin', FALSE),
    (14, 2, FALSE, NOW(), 'admin', FALSE),
    (14, 1, FALSE, NOW(), 'admin', FALSE),
    (17, 2, TRUE, NOW(), 'admin', FALSE),
    (17, 1, FALSE, NOW(), 'admin', FALSE),
    (17, 4, FALSE, NOW(), 'admin', FALSE),
    (17, 5, FALSE, NOW(), 'admin', FALSE),
    -- Câu hỏi học mới lesson 2
    (18, 5, TRUE, NOW(), 'admin', FALSE),
    (19, 5, TRUE, NOW(), 'admin', FALSE),
    (20, 6, TRUE, NOW(), 'admin', FALSE),
    (21, 6, TRUE, NOW(), 'admin', FALSE),
    (22, 7, TRUE, NOW(), 'admin', FALSE),
    (23, 7, TRUE, NOW(), 'admin', FALSE),
    (24, 8, TRUE, NOW(), 'admin', FALSE),
    (25, 8, TRUE, NOW(), 'admin', FALSE),
    (33, 6, FALSE, NOW(), 'admin', FALSE),
    (33, 1, FALSE, NOW(), 'admin', FALSE),
    (33, 8, TRUE, NOW(), 'admin', FALSE),
    (33, 5, FALSE, NOW(), 'admin', FALSE),
    -- Câu hỏi học lại lesson 1 2
    (34, 3, FALSE, NOW(), 'admin', FALSE),
    (34, 1, FALSE, NOW(), 'admin', FALSE),
    (34, 5, FALSE, NOW(), 'admin', FALSE),
    (34, 4, TRUE, NOW(), 'admin', FALSE),
    (36, 6, FALSE, NOW(), 'admin', FALSE),
    (36, 1, TRUE, NOW(), 'admin', FALSE),
    (36, 2, FALSE, NOW(), 'admin', FALSE),
    (36, 3, FALSE, NOW(), 'admin', FALSE);


INSERT INTO lesson_history (lesson_id, owner, status, total_time, accuracy, created_at, is_disabled) 
VALUES
    (1, 'duyhelloworld', 'COMPLETED', 300, 95, NOW(), FALSE),
    (2, 'duyhelloworld', 'ONGOING', NULL, NULL, NOW(), FALSE),
    (1, 'plus', 'COMPLETED', 350, 90, NOW(), FALSE),
    (3, 'plus', 'COMPLETED', 320, 85, NOW(), FALSE),
    (4, 'plus', 'ONGOING', NULL, NULL, NOW(), FALSE),
    (2, 'lechau', 'COMPLETED', 300, 92, NOW(), FALSE),
    (3, 'lechau', 'ONGOING', NULL, NULL, NOW(), FALSE),
    (1, 'phamduy', 'ONGOING', NULL, NULL, NOW(), FALSE),
    (2, 'phamduy', 'COMPLETED', 340, 88, NOW(), FALSE),
    (1, 'buiha', 'COMPLETED', 290, 80, NOW(), FALSE),
    (4, 'buiha', 'ONGOING', NULL, NULL, NOW(), FALSE);

INSERT INTO ranking (level, created_at, created_by) 
VALUES 
    ('BEGINNER', NOW(), 'admin'),
    ('INTERMEDIATE', NOW(), 'admin'),
    ('ADVANCED', NOW(), 'admin');

INSERT INTO ranked_user (user_rank, elo, ranking_id, created_at, owner, updated_at, is_disabled) 
VALUES 
    (1, 1200, 1, NOW(), 'phamduy', NULL, FALSE), 
    (2, 1100, 1, NOW(), 'duyhelloworld', NULL, FALSE), 
    (3, 1050, 1, NOW(), 'lechau', NULL, FALSE), 
    (1, 1400, 2, NOW(), 'plus', NULL, FALSE), 
    (1, 1600, 3, NOW(), 'buiha', NULL, FALSE);
