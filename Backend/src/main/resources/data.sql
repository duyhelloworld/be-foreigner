-- Active: 1703168100323@@127.0.0.1@3306@be-foreigner-dev
USE `be-foreigner-dev`;

INSERT INTO account (id, username, fullname, avatar_url, email, password, streak_days, is_plus_streak, role, level, plan, is_verified) 
VALUES  
    (1, 'admin','Chủ thớt', 'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732243632/user_avatar/wqpodmvtdx2z0rthifkp.png', 'admin@gmail.com', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'ADMIN', 'BEGINNER', 'FREE', FALSE),  
    (2, 'duyhelloworld',  'Duy Pham', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png', 'duy0184466@huce.edu.vn', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'USER', 'BEGINNER', 'FREE', TRUE),  
    (3, 'plus', 'Khách VIP', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png','khachvip@huce.edu.vn', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'USER', 'BEGINNER', 'PLUS', FALSE),  
    (4, 'lechau',  'Lê Châu', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png','lechau@outlook.com', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'USER', 'BEGINNER', 'FREE', FALSE),  
    (5, 'phamduy',  'Phạm Duy', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png', 'phamduy@huce.edu.vn', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'USER', 'INTERMEDIATE', 'FREE', FALSE),  
    (6, 'buiha', 'Bùi Hà', 'https://picsum.photos/300/300', 'buiha@gmail.com','$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'USER', 'BEGINNER', 'FREE', FALSE),
    (7, 'hoanglong',  'Bùi Hà', 'https://picsum.photos/300/300', 'hoanglong@gmail.com', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'USER', 'MEDIUM', 'FREE', FALSE),
    (8, 'hoanglong123', 'Hoàng Long', 'https://picsum.photos/300/300', 'hoanglong123@gmail.com', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'USER', 'BEGINNER', 'FREE', FALSE),
    (9, 'thuychi',  'Thùy Chi', 'https://picsum.photos/300/300', 'thuychi123@gmail.com', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'USER', 'BEGINNER', 'FREE', FALSE),
    (10, 'kimbich',  'Kim Bích', 'https://picsum.photos/300/300', 'kimbich@mail.edu', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'USER', 'INTERMEDIATE', 'FREE', FALSE),
    (11, 'sumachai', 'Sumachai', 'https://picsum.photos/300/300', 'sumachai@mail.edu', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', null, false, 'USER', 'BEGINNER', 'FREE', FALSE);
    
INSERT INTO account_token (token, `type`, created_at, owner, expired_at) VALUES
    ('3e1c76e7-fc31-4dbf-81c5-8c9bfa4351d2', 'REFRESH', NOW(), 'admin', DATE_ADD(NOW(), INTERVAL 10 DAY)),
    ('7ad2f25c-3b9e-41c1-b20e-34c914ad923e', 'REFRESH', NOW(), 'duyhelloworld', DATE_ADD(NOW(), INTERVAL 10 DAY)),
    ('9d71c5de-bf1f-4f5e-908d-c54e1d4fbd94', 'REFRESH', NOW(), 'plus', DATE_ADD(NOW(), INTERVAL 10 DAY)),
    ('a8e2f91b-cf88-4f77-84bb-1f76c6714383', 'REFRESH', NOW(), 'lechau', DATE_ADD(NOW(), INTERVAL 10 DAY)),
    ('d6b22e91-75ea-4ab0-9603-9efec18461d6', 'REFRESH', NOW(), 'phamduy', DATE_ADD(NOW(), INTERVAL 10 DAY)),
    ('ec8c37d3-3e59-4c4c-b3ad-1cd4185c2cb1', 'REFRESH', NOW(), 'buiha', DATE_ADD(NOW(), INTERVAL 10 DAY)),
    ('ec8137d3-3e59-4c5c-b3ad-1cd4185c2cb1', 'REFRESH', NOW(), 'hoanglong', DATE_ADD(NOW(), INTERVAL 10 DAY)),
    ('ec8c37d3-3e59-4c2c-b3ad-1cd4185c2cb1', 'REFRESH', NOW(), 'hoanglong123', DATE_ADD(NOW(), INTERVAL 10 DAY)),
    ('ec8c37d3-3e59-4c4c-b3ad-1cd4145c2cb1', 'REFRESH', NOW(), 'thuychi', DATE_ADD(NOW(), INTERVAL 10 DAY)),
    ('ec8c37d3-3e59-4c4c-b3ad-1cd4185c0cb1', 'REFRESH', NOW(), 'kimbich', DATE_ADD(NOW(), INTERVAL 10 DAY)),
    ('ec8c37d3-3e59-4c4c-b3ad-1cd4185c0cb1', 'REFRESH', NOW(), 'sumachai', DATE_ADD(NOW(), INTERVAL 10 DAY));
    
INSERT INTO word (id, value, mean, phonetic, audio_url, image_url, created_at, created_by, is_deleted) VALUES 
-- Chủ đề: Gia đình
    (1, 'Family', 'Gia đình', '/ˈfæm.əl.i/', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1733582121/znqjx87mx5mx1zj6lvui.mp3', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Family_Portrait.jpg/800px-Family_Portrait.jpg', NOW(), 'admin', FALSE),
    (2, 'Father', 'Cha', '/ˈfɑː.ðər/', 'https://d1qx7pbj0dvboc.cloudfront.net/father.mp3', 'https://images.unsplash.com/photo-1657664058220-a1bfc04e2e14?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', NOW(), 'admin', FALSE),
    (3, 'Mother', 'Mẹ', '/ˈmʌð.ər/', 'https://d1qx7pbj0dvboc.cloudfront.net/mother.mp3', 'https://images.unsplash.com/photo-1542385151-efd9000785a0?q=80&w=1378&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', NOW(), 'admin', FALSE),
    (4, 'Parent', 'Cha mẹ', 'ˈper(ə)nt', 'https://d1qx7pbj0dvboc.cloudfront.net/parent.mp3', 'https://abc-ksa.com/wp-content/uploads/2021/08/parening.png', NOW(), 'admin', FALSE),
    (5, 'Child', 'Con cái', '/tʃaɪld/', 'https://d1qx7pbj0dvboc.cloudfront.net/child.mp3', 'https://images.unsplash.com/photo-1676286111583-9fec2694b26c?q=80&w=1438&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', NOW(), 'admin', FALSE),
    (6, 'Brother', 'Anh trai', '/ˈbrʌð.ər/', 'https://d1qx7pbj0dvboc.cloudfront.net/brother.mp3', 'https://images.unsplash.com/photo-1502143135356-dcdb8a9a3da6?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', NOW(), 'admin', FALSE),
    (7, 'Sister', 'Chị gái', '/ˈsɪs.tər/', 'https://d1qx7pbj0dvboc.cloudfront.net/sister.mp3', 'https://images.unsplash.com/photo-1476234251651-f353703a034d?q=80&w=1376&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', NOW(), 'admin', FALSE),
    (8, 'Grandparent', 'Ông bà', '/ˈɡræn.per.ənts/', 'https://d1qx7pbj0dvboc.cloudfront.net/grandparent.mp3', 'https://images.unsplash.com/photo-1581579439002-e29ac578f8d4?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fGdyYW5kcGFyZW50fGVufDB8fDB8fHwy', NOW(), 'admin', FALSE),
    -- Chủ đề: Trường học
    (9, 'School', 'Trường học', '/skuːl/', 'https://d1qx7pbj0dvboc.cloudfront.net/school.mp3', 'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
    (10, 'Student', 'Học sinh', '/ˈstuː.dənt/', 'https://d1qx7pbj0dvboc.cloudfront.net/student.mp3', 'https://www.everylearnereverywhere.org/wp-content/uploads/student-centered-learning.png', NOW(), 'admin', FALSE),
    (11, 'Teacher', 'Giáo viên', '/ˈtiː.tʃər/', 'https://d1qx7pbj0dvboc.cloudfront.net/teacher.mp3', 'https://guru.com/blog/wp-content/uploads/2023/10/private-teacher-cost.jpg', NOW(), 'admin', FALSE),
    (12, 'Classroom', 'Lớp học', '/ˈklæs.ruːm/', 'https://d1qx7pbj0dvboc.cloudfront.net/classroom.mp3', 'https://www.teachhub.com/wp-content/uploads/2020/05/Classroom-Management-for-an-Effective-Learning-Environment-768x512.jpg', NOW(), 'admin', FALSE),
    (13, 'Homework', 'Bài tập về nhà', '/ˈhoʊm.wɝːk/', 'https://d1qx7pbj0dvboc.cloudfront.net/homework.mp3', 'https://northpolkorbit.org/wp-content/uploads/2024/05/GettyImages-175452558-589a4cd83df78caebc7ebc63.jpg', NOW(), 'admin', FALSE),
    (14, 'Exam', 'Kỳ thi', '/ɪɡˈzæm/', 'https://d1qx7pbj0dvboc.cloudfront.net/exam.mp3', 'https://this.deakin.edu.au/wp-content/uploads/2016/06/female-asian-student-writing-exam-exercise-in-the-2023-11-27-05-18-09-utc-scaled.jpg', NOW(), 'admin', FALSE),
    (15, 'Subject', 'Môn học', '/ˈsʌb.dʒekt/', 'https://d1qx7pbj0dvboc.cloudfront.net/subject.mp3', 'https://vieclam123.vn/ckfinder/userfiles/images/subject-la-gi.jpg', NOW(), 'admin', FALSE),
    (16, 'Lesson', 'Bài học', '/ˈlesən/', 'https://d1qx7pbj0dvboc.cloudfront.net/lesson.mp3', 'https://blog.pango.education/hubfs/blog_mathslessonplanning-1.jpeg', NOW(), 'admin', FALSE),
    -- Chủ đề: Nghề nghiệp
    (17, 'Jobs', 'Nghề nghiệp', '/dʒɒb/', 'https://d1qx7pbj0dvboc.cloudfront.net/jobs.mp3', 'https://tailieutienganh.edu.vn/public/files/upload/default/images/phu-am-danh-tu-dem-duoc-so-it-so-nhieu-khong-dem-duoc-tu-vung-nghe-nghiep-jobs-3.jpg', NOW(), 'admin', FALSE),
    (18, 'Doctor', 'Bác sĩ', '/ˈdɒk.tər/', 'https://d1qx7pbj0dvboc.cloudfront.net/doctor.mp3', 'https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg?w=360', NOW(), 'admin', FALSE),
    (19, 'Engineer', 'Kỹ sư', '/ˌen.dʒɪˈnɪr/', 'https://d1qx7pbj0dvboc.cloudfront.net/engineer.mp3', 'https://i0.wp.com/www.engineeringandleadership.com/wp-content/uploads/2019/02/Engineer.png?fit=975%2C651&ssl=1', NOW(), 'admin', FALSE),
    (20, 'Nurse', 'Y tá', '/nɝːs/', 'https://d1qx7pbj0dvboc.cloudfront.net/nurse.mp3', 'https://cl-wpml.careerlink.vn/cam-nang-viec-lam/wp-content/uploads/2023/08/21085609/healthcare-workers-preventing-virus-quarantine-campaign-concept-cheerful-friendly-asian-female-physician-doctor-with-clipboard-during-daily-checkup-standing-white-background-1024x683.jpg', NOW(), 'admin', FALSE),
    (21, 'Police', 'Cảnh sát', '/pəˈliːs/', 'https://d1qx7pbj0dvboc.cloudfront.net/police.mp3', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Police.gun.1.london.arp.jpg/640px-Police.gun.1.london.arp.jpg', NOW(), 'admin', FALSE),
    (22, 'Pilot', 'Phi công', '/ˈpaɪ.lət/', 'https://d1qx7pbj0dvboc.cloudfront.net/pilot.mp3', 'https://i0.wp.com/aerocadet.com/blog/wp-content/uploads/2024/09/health-criteria-for-becoming-a-pilot.jpg?fit=1429%2C714&ssl=1', NOW(), 'admin', FALSE),
    (23, 'Chef', 'Đầu bếp', '/ʃef/', 'https://d1qx7pbj0dvboc.cloudfront.net/chef.mp3', 'https://tamlong.com.vn/wp-content/uploads/gordon-ramsay-646367718a5f4.jpg', NOW(), 'admin', FALSE),
    (24, 'Developer', 'Lập trình viên', '/dɪˈvel.ə.pɚ/', 'https://d1qx7pbj0dvboc.cloudfront.net/developer.mp3', 'https://images.careerviet.vn/content/images/developer-la-gi-CareerBuilder-1.jpg', NOW(), 'admin', FALSE),
    -- Chủ đề: Thời tiết
    (25, 'Sunny', 'Nắng', '/ˈsʌn.i/', 'https://d1qx7pbj0dvboc.cloudfront.net/sunny.mp3', 'https://images.photowall.com/products/44323/sunny-day.jpg?h=699&q=85', NOW(), 'admin', FALSE),
    (26, 'Rainy', 'Mưa', '/ˈreɪ.ni/', 'https://d1qx7pbj0dvboc.cloudfront.net/rainy.mp3', 'https://www.wellahealth.com/blog/wp-content/uploads/2021/09/6-ways-to-stay-healthy-during-the-rainy-season.jpg', NOW(), 'admin', FALSE),
    (27, 'Cloudy', 'Nhiều mây', '/ˈklaʊ.di/', 'https://d1qx7pbj0dvboc.cloudfront.net/cloudy.mp3', 'https://media.istockphoto.com/id/598222542/photo/sky-background.jpg?s=612x612&w=0&k=20&c=WBAiCExAztT4SzWh4hIgmQwTG7VMJ5o9oObXHszmm8A=', NOW(), 'admin', FALSE),
    (28, 'Windy', 'Có gió', '/ˈwɪn.di/', 'https://d1qx7pbj0dvboc.cloudfront.net/windy.mp3', 'https://img.freepik.com/premium-photo/cartoon-drawing-very-windy-day_1151123-32740.jpg?semt=ais_hybrid', NOW(), 'admin', FALSE),
    (29, 'Snowy', 'Có tuyết', '/ˈsnoʊ.i/', 'https://d1qx7pbj0dvboc.cloudfront.net/snowy.mp3', 'https://www.highcountryweather.com/wp-content/uploads/2016/11/2016-november-03-how-snowy.jpg', NOW(), 'admin', FALSE),
    (30, 'Stormy', 'Bão tố', '/ˈstɔːr.mi/', 'https://d1qx7pbj0dvboc.cloudfront.net/stormy.mp3', 'https://plus.unsplash.com/premium_photo-1726989863790-e31ee30770ce?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8c3Rvcm15JTIwd2VhdGhlcnxlbnwwfHwwfHx8MA%3D%3D', NOW(), 'admin', FALSE),
    (31, 'Foggy', 'Sương mù', '/ˈfɒɡ.i/', 'https://d1qx7pbj0dvboc.cloudfront.net/foggy.mp3', 'https://grammaticus.blog/wp-content/uploads/2022/11/foggy-morning-street.jpg?w=640', NOW(), 'admin', FALSE),
    (32, 'Hot', 'Nóng', '/hɒt/', 'https://d1qx7pbj0dvboc.cloudfront.net/hot.mp3', 'https://sa1s3optim.patientpop.com/assets/images/provider/photos/2744409.jpeg', NOW(), 'admin', FALSE),
    -- Chủ đề: Động vật
    (33, 'Dog', 'Chó', '/dɒɡ/', 'https://d1qx7pbj0dvboc.cloudfront.net/dog.mp3', 'https://cdn.britannica.com/79/232779-050-6B0411D7/German-Shepherd-dog-Alsatian.jpg', NOW(), 'admin', FALSE),
    (34, 'Cat', 'Mèo', '/kæt/', 'https://d1qx7pbj0dvboc.cloudfront.net/cat.mp3', 'https://media.4-paws.org/9/c/9/7/9c97c38666efa11b79d94619cc1db56e8c43d430/Molly_006-2829x1886-2726x1886-1920x1328.jpg', NOW(), 'admin', FALSE),
    (35, 'Bird', 'Chim', '/bɝːd/', 'https://d1qx7pbj0dvboc.cloudfront.net/bird.mp3', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Eopsaltria_australis_-_Mogo_Campground.jpg/640px-Eopsaltria_australis_-_Mogo_Campground.jpg', NOW(), 'admin', FALSE),
    (36, 'Fish', 'Cá', '/fɪʃ/', 'https://d1qx7pbj0dvboc.cloudfront.net/fish.mp3', 'https://www.aqueon.com/-/media/project/oneweb/aqueon/us/blog/ways-to-know-your-fish-are-happy/fish-are-happy-and-healthy-1.png', NOW(), 'admin', FALSE),
    (37, 'Rabbit', 'Thỏ', '/ˈræb.ɪt/', 'https://d1qx7pbj0dvboc.cloudfront.net/rabbit.mp3', 'https://www.vetcarepethospital.ca/wp-content/uploads/sites/247/2022/03/petrabbitcare-1-1280x650.jpg', NOW(), 'admin', FALSE),
    (38, 'Horse', 'Ngựa', '/hɔːrs/', 'https://d1qx7pbj0dvboc.cloudfront.net/horse.mp3', 'https://cdn.media.amplience.net/i/jpl/NY-blog-thumbnail-Horse-Breeds?qlt=80&w=1920&h=960&sm=c', NOW(), 'admin', FALSE),
    (39, 'Elephant', 'Voi', '/ˈel.ɪ.fənt/', 'https://d1qx7pbj0dvboc.cloudfront.net/elephant.mp3', 'https://i.natgeofe.com/n/b64060fa-343c-481b-a24d-7375fef34914/NationalGeographic_1425689_square.jpg', NOW(), 'admin', FALSE),
    (40, 'Duck', 'Vịt', '/dʌk/', 'https://d1qx7pbj0dvboc.cloudfront.net', 'https://vituyenuong.com/upload/news/istockphoto-695733700-612x612-5499.jpg', NOW(), 'admin', FALSE);

INSERT INTO sentense (id, value, mean, audio_url, created_at, created_by, is_deleted) 
VALUES
-- Chủ đề gia đình (Family) 
    (1, 'She is my mother.', 'Cô ấy là mẹ của tôi', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937422/audio/leiyjr3zfk3rhthfrsd6.mp3', NOW(), 'admin', FALSE),
    (2, 'He is my father', 'Cha của tôi đang ở đây', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937422/audio/leiyjr3zfk3rhthfrsd6.mp3', NOW(), 'admin', FALSE),
    (3, 'This is my family.', 'Đó là gia đình của tôi.', '', NOW(), 'admin', FALSE),
    (4, 'This is my father.', 'Đây là cha của tôi.', '', NOW(), 'admin', FALSE),
    (5, 'I have parent.', 'Tôi có cha mẹ.', '', NOW(), 'admin', FALSE),
    (6, 'My parents love me.', 'Cha mẹ tôi yêu tôi.', '', NOW(), 'admin', FALSE),
    (7, 'We are a family.', 'Chúng tôi là một gia đình.', '', NOW(), 'admin', FALSE),
    (8, 'My father is in my family.', 'Bố tôi ở trong gia đình tôi.', '', NOW(), 'admin', FALSE);
-- Chủ đề gia đình 4 từ sau
    (9, 'I live with my grandparents.', 'Tôi sống với ông bà của tôi.', '', NOW(), 'admin', FALSE),
    (10, 'I have a sister.', 'Ông ấy là bố của tôi.', '', NOW(), 'admin', FALSE),
    (11, 'He is my brother.', 'Bà ấy là cô của tôi.', '', NOW(), 'admin', FALSE),
    (12, 'Is they your grandparents?', 'Anh họ tôi rất hài hước.', '', NOW(), 'admin', FALSE),
    (13, 'This is my child.', 'Đây là con của tôi.', '', NOW(), 'admin', FALSE),
    (14, 'I\'m my parent\'s child', 'Tôi là đứa trẻ của bố mẹ tôi', '', NOW(), 'admin', FALSE),
    (15, 'I visit my grandparents every weekend.', 'Tôi thăm ông bà mỗi cuối tuần.', '', NOW(), 'admin', FALSE),
    (16, 'Where is your sister?', 'Chị của bạn ở đâu vậy?', ' ', NOW(), 'admin', FALSE),
-- -- Chủ đề: Trường học (School) school student teacher classroom
    (17, 'This is my school.', 'Đây là trường của tôi.', '', NOW(), 'admin', FALSE),
    (18, 'I am a student.', 'Tôi là một học sinh.', '', NOW(), 'admin', FALSE),
    (19, 'The teacher is kind.', 'Giáo viên rất tốt bụng.', '', NOW(), 'admin', FALSE),
    (20, 'We study in the classroom.', 'Chúng tôi học trong lớp học.', '', NOW(), 'admin', FALSE),
    (21, 'The classroom is big.', 'Lớp học rất rộng.', '', NOW(), 'admin', FALSE),
    (22, 'My classroom is in my school.', 'Lớp tôi ở trong trường tôi.', '', NOW(), 'admin', FALSE),
    (23, 'My teacher teaches English.', 'Cô giáo của tôi dạy tiếng Anh.', '', NOW(), 'admin', FALSE);

INSERT INTO lesson (id, name, type, user_level, access_level, elo, color, grammar_note, cover_image_url, created_at, created_by, is_deleted)
VALUES 
    (1, 'Cha mẹ', 'NEW_LEARNING', 'BEGINNER', 'FREE_ACCESS', 100, '#009229', 'Trong tiếng Anh, sự phân chia giới tính trong ngôi xưng rất rõ!', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Family_Portrait.jpg/800px-Family_Portrait.jpg', NOW(), 'admin', FALSE),
    (2, 'Anh em', 'NEW_LEARNING', 'BEGINNER', 'FREE_ACCESS', 100, '#993322', NULL, 'https://phuong3.tayninh.gov.vn/uploads/news/2024_07/doan-van-ke-gia-dinh.jpg', NOW(), 'admin', FALSE),
    (3, 'Gia đình', 'REVISION', 'BEGINNER', 'FREE_ACCESS', 150, '#005500', NULL,'https://file.hstatic.net/1000203256/article/ngay_gia_dinh_viet_nam_6529fc3d90224ff6a8896f5d4d164894.png', NOW(), 'admin', FALSE),
    (4, 'Công việc', 'NEW_LEARNING', 'BEGINNER', 'FREE_ACCESS', 100, '#110099', NULL,'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732931715/m7dz7yhxliv140btfdq7.png', NOW(), 'admin', FALSE),
    (5, 'Nghề nghiệp', 'NEW_LEARNING', 'BEGINNER', 'FREE_ACCESS', 100, '#200931', NULL,'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732931715/m7dz7yhxliv140btfdq7.png', NOW(), 'admin', FALSE),
    (6, 'Các công việc', 'REVISION', 'BEGINNER', 'FREE_ACCESS', 150, '#DD11AA', NULL,'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732931715/m7dz7yhxliv140btfdq7.png', NOW(), 'admin', FALSE),
    (7, 'Trường học', 'NEW_LEARNING', 'BEGINNER', 'FREE_ACCESS', 100, '#995577', NULL,'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
    (8, 'Trường học', 'NEW_LEARNING', 'BEGINNER', 'FREE_ACCESS', 100, '#008866', NULL,'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
    (9, 'Trường học', 'REVISION', 'BEGINNER', 'FREE_ACCESS', 150, '#009922', NULL,'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
    (10, 'Động vật', 'NEW_LEARNING', 'BEGINNER', 'FREE_ACCESS', 100, '#778800', NULL,'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Animals_png_set_by_mossi889-d4uye4q.png/1200px-Animals_png_set_by_mossi889-d4uye4q.png', NOW(), 'admin', FALSE),
    (11, 'Động vật', 'NEW_LEARNING', 'BEGINNER', 'FREE_ACCESS', 100, '#4488FF', NULL,'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Animals_png_set_by_mossi889-d4uye4q.png/1200px-Animals_png_set_by_mossi889-d4uye4q.png', NOW(), 'admin', FALSE),
    (12, 'Động vật', 'REVISION', 'BEGINNER', 'FREE_ACCESS', 100, '#FF99FF', NULL,'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Animals_png_set_by_mossi889-d4uye4q.png/1200px-Animals_png_set_by_mossi889-d4uye4q.png', NOW(), 'admin', FALSE),
    (13, 'Thời tiết', 'NEW_LEARNING', 'BEGINNER', 'FREE_ACCESS', 100, '#0088FF', NULL,'https://www.un.org/sites/un2.un.org/files/styles/large-article-image-style-16-9/public/field/image/2023/03/52196025795_06f077377a_c.jpg', NOW(), 'admin', FALSE),
    (14, 'Thời tiết', 'NEW_LEARNING', 'BEGINNER', 'FREE_ACCESS', 100, '#6677DD', NULL,'https://www.un.org/sites/un2.un.org/files/styles/large-article-image-style-16-9/public/field/image/2023/03/52196025795_06f077377a_c.jpg', NOW(), 'admin', FALSE),
    (15, 'Thời tiết', 'REVISION', 'BEGINNER', 'FREE_ACCESS', 100, '#BB0011', NULL,'https://www.un.org/sites/un2.un.org/files/styles/large-article-image-style-16-9/public/field/image/2023/03/52196025795_06f077377a_c.jpg', NOW(), 'admin', FALSE),
    (16, 'Kiểm tra', 'EXAM_EVENT', 'BEGINNER', 'FREE_ACCESS', 100,'#008811', NULL, 'https://images.squarespace-cdn.com/content/v1/5f57c8da5b4e905978984460/5bf55f3d-42ad-466b-b279-8b406d512cd9/Exams.jpeg', NOW(), 'admin', FALSE);

INSERT INTO question (id, index_in_lesson, lesson_id, level, type, word_id, sentense_id, unrelated_words, created_at, created_by, is_deleted) 
VALUES
-- Câu hỏi cho Lesson 1: Gia đình : word(1, 4) sentense(1, 18)
    (1, 1, 1, 'EASY', 'LEARN_WORD', 1, NULL, NULL, NOW(), 'admin', FALSE),
    (2, 2, 1, 'EASY', 'LEARN_WORD', 2, NULL, NULL, NOW(), 'admin', FALSE),
    (3, 3, 1, 'EASY', 'LEARN_WORD', 3, NULL, NULL, NOW(), 'admin', FALSE),
    (4, 4, 1, 'EASY', 'LEARN_WORD', 4, NULL, NULL, NOW(), 'admin', FALSE),
    (5, 5, 1, 'EASY', 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NOW(), 'admin', FALSE),
    (6, 6, 1, 'EASY', 'GIVE_AUDIO_REARRANGE_WORDS', NULL, 1, NULL, NOW(), 'admin', FALSE),
    (7, 7, 1, 'EASY', 'GIVE_AUDIO_ENTER_WORD', 2, NULL, NULL, NOW(), 'admin', FALSE),
    (8, 8, 1, 'EASY', 'GIVE_SENTENSE_REARRANGE_WORDS', NULL, 3, NULL, NOW(), 'admin', FALSE),
    (9, 9, 1, 'EASY', 'GIVE_SENTENSE_REARRANGE_WORDS', NULL, 2, NULL, NOW(), 'admin', FALSE),
    (10, 10, 1, 'EASY', 'GIVE_AUDIO_ENTER_SENTENSE', NULL, 4, NULL, NOW(), 'admin', FALSE),
    (11, 11, 1, 'EASY', 'GIVE_WORD_CHOOSE_SYNONYMOUS_WORDS', NULL, NULL, NULL, NOW(), 'admin', FALSE);

-- Chèn các đáp án vào bảng answer
INSERT INTO answer (question_id, word_id, is_true, created_at, created_by, is_deleted) VALUES
    -- Câu hỏi học mới lesson 1
    (1, 1, TRUE, NOW(), 'admin', FALSE),
    (2, 2, TRUE, NOW(), 'admin', FALSE),
    (3, 3, TRUE, NOW(), 'admin', FALSE),
    (4, 4, TRUE, NOW(), 'admin', FALSE),
    (5, 1, FALSE, NOW(), 'admin', FALSE),
    (5, 2, TRUE, NOW(), 'admin', FALSE),
    (5, 3, FALSE, NOW(), 'admin', FALSE),
    (5, 4, FALSE, NOW(), 'admin', FALSE),
    (10, 3, TRUE, NOW(), 'admin', FALSE);
    
INSERT INTO lesson_history (lesson_id, owner, status, total_time, accuracy, created_at, elo) 
VALUES
    (1, 'duyhelloworld', 'COMPLETED', 300, 95, NOW(), 100),
    (1, 'duyhelloworld', 'COMPLETED', 270, 100, NOW(), 150),
    (1, 'duyhelloworld', 'COMPLETED', 280, 97, NOW(), 100),
    (4, 'duyhelloworld', 'COMPLETED', 290, 85, NOW(), 100),
    (4, 'duyhelloworld', 'COMPLETED', 290, 90, NOW(), 100),
    (4, 'duyhelloworld', 'COMPLETED', 240, 100, NOW(), 150),
    (2, 'duyhelloworld', 'ONGOING', NULL, NULL, NOW(), NULL),
    (1, 'plus', 'COMPLETED', 350, 90, NOW(), 100),
    (3, 'plus', 'COMPLETED', 320, 85, NOW(), 100),
    (4, 'plus', 'ONGOING', NULL, NULL, NOW(), NULL),
    (2, 'lechau', 'COMPLETED', 300, 92, NOW(), 100),
    (3, 'lechau', 'ONGOING', NULL, NULL, NOW(), NULL),
    (1, 'phamduy', 'ONGOING', NULL, NULL, NOW(), NULL),
    (2, 'phamduy', 'COMPLETED', 340, 88, NOW(), 80),
    (1, 'buiha', 'COMPLETED', 290, 80, NOW(), 90),
    (4, 'buiha', 'ONGOING', NULL, NULL, NOW(), NULL);

INSERT INTO roadmap (id, name, description, word_count_target, created_by, created_at, is_deleted) 
VALUES
    (1, 'Lộ trình từ lớp 3-5', 'Lộ trình cho học sinh/người có kiến thức cấp tiểu học', 50, 'admin', NOW(), false),
    (2, 'Lộ trình từ lớp 5-9', 'Lộ trình cho học sinh/người có kiến thức cấp trung học cơ sở', 200, 'admin', NOW(), false),
    (3, 'Lộ trình từ lớp 9-12', 'Lộ trình cho học sinh/người có kiến thức cấp trung học phổ thông', 500, 'admin', NOW(), false),
    (4, 'Lộ trình từ đại học đổ lên', 'Lộ trình cho sinh viên/người có kiến thức cấp đại học', 1000, 'admin', NOW(), false);

INSERT INTO ranking_user (id, created_at, elo, user_rank, owner) 
VALUES 
    (1, NOW(), 1600, 1, 'duyhelloworld'), 
    (2, NOW(), 1500, 2, 'plus'), 
    (3, NOW(), 1400, 3, 'lechau'), 
    (4, NOW(), 1300, 4, 'phamduy'), 
    (5, NOW(), 1000, 5, 'buiha'), 
    (6, NOW(), 900, 6, 'hoanglong'),
    (7, NOW(), 800, 7, 'thuychi'),
    (8, NOW(), 700, 8, 'hoanglong123'),
    (9, NOW(), 600, 9, 'kimbich'),
    (11, NOW(), 2100, 10, 'sumachai');

    SELECT w.id, w.mean, w.`value`, w.phonetic 
    FROM Word w 
    JOIN answer a ON w.id = a.word_id 
    JOIN question q ON a.question_id = q.id 
    JOIN lesson_history lh ON q.lesson_id = lh.lesson_id 
            WHERE lh.owner = 'duyhelloworld' 
            AND q.type = 'LEARN_WORD' 
            AND a.is_true = true 
            AND lh.status = 'COMPLETED' 
            ORDER BY RAND()  
            LIMIT 1;
