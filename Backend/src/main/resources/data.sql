USE `be-foreigner`;

INSERT INTO `user` (id, username, fullname, avatar_url, email, password, streak_days, temp_code, is_showed_streak, is_allow_mail, is_allow_notification, is_allow_word_notification, role, level, quota, plan, is_verified) 
VALUES  
    (1, 'admin','Chủ thớt', 'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732243632/user_avatar/wqpodmvtdx2z0rthifkp.png', 'admin@gmail.com', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', 0, null, false, false, false, false, 'ADMIN', 'BEGINNER', 5, 'FREE', FALSE),  
    (2, 'duyhelloworld',  'Duy Pham', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png', 'duy0184466@huce.edu.vn', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', 1, null, false, false, false, false, 'USER', 'BEGINNER', 5, 'FREE', FALSE),  
    (3, 'plus', 'Khách VIP', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png','khachvip@huce.edu.vn', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', 5, null, false, false, false, false, 'USER', 'BEGINNER', 5, 'PLUS', FALSE),  
    (4, 'lechau',  'Lê Châu', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png','lechau@outlook.com', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', 1, null, false, false, false, false, 'USER', 'BEGINNER', 5, 'FREE', FALSE),  
    (5, 'phamduy',  'Phạm Duy', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png', 'phamduy@huce.edu.vn', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', 1, null, false, false, false, false, 'USER', 'INTERMEDIATE', 5, 'FREE', FALSE),  
    (6, 'buiha', 'Bùi Hà', 'https://picsum.photos/300/300', 'buiha@gmail.com','$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', 1, null, false, false, false, false, 'USER', 'BEGINNER', 5, 'FREE', FALSE),
    (7, 'hoanglong',  'Bùi Hà', 'https://picsum.photos/300/300', 'hoanglong@gmail.com', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', 1, null, false, false, false, false, 'USER', 'MEDIUM', 5, 'FREE', FALSE),
    (8, 'hoanglong123', 'Hoàng Long', 'https://picsum.photos/300/300', 'hoanglong123@gmail.com', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', 1, null, false, false, false, false, 'USER', 'BEGINNER', 5, 'FREE', FALSE),
    (9, 'thuychi',  'Thùy Chi', 'https://picsum.photos/300/300', 'thuychi123@gmail.com', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa', 1, null, false, false, false, false, 'USER', 'BEGINNER', 5, 'FREE', FALSE),
    (10, 'kimbich',  'Kim Bích', 'https://picsum.photos/300/300', 'kimbich@mail.edu', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa',61, null, false, false, false, false, 'USER', 'INTERMEDIATE', 5, 'FREE', FALSE),
    (11, 'sumachai', 'Sumachai', 'https://picsum.photos/300/300', 'sumachai@mail.edu', '$2a$12$Fnk7pPcDWVGq9SylQUB5qOfkj4IeNjWo/n0zSlNTSDpDWkuB08xVa',20, null, false, false, false, false, 'USER', 'BEGINNER', 5, 'FREE', FALSE);
    
INSERT INTO user_token (token, `type`, last_modified_at, last_modified_by, is_disabled) VALUES
    ('3e1c76e7-fc31-4dbf-81c5-8c9bfa4351d2', 'REFRESH', NOW(), 'admin', FALSE),
    ('7ad2f25c-3b9e-41c1-b20e-34c914ad923e', 'REFRESH', NOW(), 'duyhelloworld', FALSE),
    ('9d71c5de-bf1f-4f5e-908d-c54e1d4fbd94', 'REFRESH', NOW(), 'plus', FALSE),
    ('a8e2f91b-cf88-4f77-84bb-1f76c6714383', 'REFRESH', NOW(), 'lechau', FALSE),
    ('d6b22e91-75ea-4ab0-9603-9efec18461d6', 'REFRESH', NOW(), 'phamduy', FALSE),
    ('ec8c37d3-3e59-4c4c-b3ad-1cd4185c2cb1', 'REFRESH', NOW(), 'buiha', FALSE),
    ('ec8137d3-3e59-4c5c-b3ad-1cd4185c2cb1', 'REFRESH', NOW(), 'hoanglong', FALSE),
    ('ec8c37d3-3e59-4c2c-b3ad-1cd4185c2cb1', 'REFRESH', NOW(), 'hoanglong123', FALSE),
    ('ec8c37d3-3e59-4c4c-b3ad-1cd4145c2cb1', 'REFRESH', NOW(), 'thuychi', FALSE),
    ('ec8c37d3-3e59-4c4c-b3ad-1cd4185c0cb1', 'REFRESH', NOW(), 'kimbich', FALSE),
    ('ec8c37d3-3e59-4c4c-b3ad-1cd4185c0cb1', 'REFRESH', NOW(), 'sumachai', FALSE);
    
INSERT INTO word (id, value, mean, phonetic, audio_url, image_url, created_at, created_by, is_deleted) VALUES 
-- Chủ đề: Gia đình
(1, 'Family', 'Gia đình', '/ˈfæm.əl.i/', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1733582121/znqjx87mx5mx1zj6lvui.mp3', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Family_Portrait.jpg/800px-Family_Portrait.jpg', NOW(), 'admin', FALSE),
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


INSERT INTO lesson (id,   name, target, level, type, cover_image_url, created_at, created_by, is_deleted)
VALUES 
    (1, 'Cha mẹ', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Family_Portrait.jpg/800px-Family_Portrait.jpg', NOW(), 'admin', FALSE),
    (2, 'Anh em', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://phuong3.tayninh.gov.vn/uploads/news/2024_07/doan-van-ke-gia-dinh.jpg', NOW(), 'admin', FALSE),
    (3, 'Gia đình', 'REVIEW', 'BEGINNER', 'FREE_ACCESS', 'https://file.hstatic.net/1000203256/article/ngay_gia_dinh_viet_nam_6529fc3d90224ff6a8896f5d4d164894.png', NOW(), 'admin', FALSE),
    (4, 'Công việc', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732931715/m7dz7yhxliv140btfdq7.png', NOW(), 'admin', FALSE),
    (5, 'Nghề nghiệp', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732931715/m7dz7yhxliv140btfdq7.png', NOW(), 'admin', FALSE),
    (6, 'Các công việc', 'REVIEW', 'BEGINNER', 'FREE_ACCESS', 'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732931715/m7dz7yhxliv140btfdq7.png', NOW(), 'admin', FALSE),
    (7, 'Trường học', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
    (8, 'Trường học', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
    (9, 'Trường học', 'REVIEW', 'BEGINNER', 'FREE_ACCESS', 'https://dictionary.cambridge.org/vi/images/thumb/school_noun_002_32354.jpg', NOW(), 'admin', FALSE),
    (10, 'Các động vật', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Animals_png_set_by_mossi889-d4uye4q.png/1200px-Animals_png_set_by_mossi889-d4uye4q.png', NOW(), 'admin', FALSE),
    (11, 'Các loài động vật', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Animals_png_set_by_mossi889-d4uye4q.png/1200px-Animals_png_set_by_mossi889-d4uye4q.png', NOW(), 'admin', FALSE),
    (12, 'Động vật', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Animals_png_set_by_mossi889-d4uye4q.png/1200px-Animals_png_set_by_mossi889-d4uye4q.png', NOW(), 'admin', FALSE),
    (13, 'Thời tiết', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://www.un.org/sites/un2.un.org/files/styles/large-article-image-style-16-9/public/field/image/2023/03/52196025795_06f077377a_c.jpg', NOW(), 'admin', FALSE),
    (14, 'Không khí', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://www.un.org/sites/un2.un.org/files/styles/large-article-image-style-16-9/public/field/image/2023/03/52196025795_06f077377a_c.jpg', NOW(), 'admin', FALSE),
    (15, 'Thời tiết', 'LEARN_NEW', 'BEGINNER', 'FREE_ACCESS', 'https://www.un.org/sites/un2.un.org/files/styles/large-article-image-style-16-9/public/field/image/2023/03/52196025795_06f077377a_c.jpg', NOW(), 'admin', FALSE),
    (16, 'Kiểm tra', 'TEST', 'BEGINNER', 'FREE_ACCESS', 'https://images.squarespace-cdn.com/content/v1/5f57c8da5b4e905978984460/5bf55f3d-42ad-466b-b279-8b406d512cd9/Exams.jpeg', NOW(), 'admin', FALSE);

INSERT INTO question (id, index_in_lesson, lesson_id, type, sentense_meaning, sentense_words, sentense_audio, unrelated_words, level, created_at, created_by, is_deleted) VALUES
-- Câu hỏi cho Lesson 1: Gia đình - Family - Father - Mother - Grandparent
    (1, 1, 1, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (2, 2, 1, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (3, 3, 1, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (4, 4, 1, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (5, 5, 1, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (9, 6, 1, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Chúng tôi là gia đình', 'We are family', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (10, 7, 1, 'GIVE_MEAN_ENTER_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (11, 8, 1, 'GIVE_AUDIO_REARRANGE_WORDS', 'Đây là bố tôi', 'This is my father', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937422/audio/leiyjr3zfk3rhthfrsd6.mp3', NULL, 'EASY', NOW(), 'admin', FALSE), 
    (12, 9, 1, 'GIVE_AUDIO_REARRANGE_WORDS', 'Cô ấy là mẹ của tôi', 'She is my mother', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937397/audio/ttcklx5wgjswnlqzqken.mp3', 'my your their', 'HARD', NOW(), 'admin', FALSE), 
    (13, 10, 1, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Ông ấy là bố tôi', 'He is my father', NULL, 'gold like', 'EASY', NOW(), 'admin', FALSE),
    (14, 11, 1, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (15, 12, 1, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Ông ấy là bố tôi', 'He is my father', NULL, 'him mine am his', 'HARD', NOW(), 'admin', FALSE),
    (16, 13, 1, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Đây là ông bà tôi', 'This is my grandparent', NULL, 'parent ', 'EASY', NOW(), 'admin', FALSE),
    (17, 14, 1, 'GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'HARD', NOW(), 'admin', FALSE),
-- Câu hỏi cho Lesson 2: Gia đình - Child - Parent - Brother - Sister
    (18, 1, 2, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (19, 2, 2, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (20, 3, 2, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (21, 4, 2, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (26, 5, 2, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Đó là con của chúng tôi', 'That is our child', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (27, 6, 2, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Tôi có một anh trai', 'I have a brother', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (28, 7, 2, 'GIVE_AUDIO_REARRANGE_WORDS', 'Cô ấy là chị tôi', 'She is my sister', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937422/audio/leiyjr3zfk3rhthfrsd6.mp3', 'Her', 'EASY', NOW(), 'admin', FALSE), -- 'They are happy.'
    (29, 8, 2, 'GIVE_AUDIO_REARRANGE_WORDS', 'Cha mẹ tôi ở đằng kia', 'My parent is there', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937397/audio/ttcklx5wgjswnlqzqken.mp3', 'my your their', 'HARD', NOW(), 'admin', FALSE), -- 'She is my teacher.'
    (30, 9, 2, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Anh ấy là anh bạn à', 'Is he your brother ?', NULL, 'family father', 'EASY', NOW(), 'admin', FALSE),
    (31, 10, 2, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Ông ấy là bố tôi', 'He is my father', NULL, 'him mine am his', 'HARD', NOW(), 'admin', FALSE),
    (32, 11, 2, 'GIVE_AUDIO_REARRANGE_WORDS', 'Tôi có một chị gái và hai anh trai', 'I have a sister and two brother', '', 'parent three an', 'HARD', NOW(), 'admin', FALSE),
    (33, 12, 2, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),    
-- Câu hỏi cho Lesson 3: Gia đình 
    (34, 1,  3, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (35, 2,  3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Anh ấy là anh bạn à', 'Is he your brother ?', NULL, 'family father', 'EASY', NOW(), 'admin', FALSE),
    (36, 3,  3, 'GIVE_AUDIO_CHOOSE_WORD', NULL, 'This is my father', '', NULL, 'EASY', NOW(), 'admin', FALSE),
    (37, 4,  3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Tôi có một anh trai', 'I have a brother', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (38, 5,  3, 'GIVE_AUDIO_REARRANGE_WORDS', NULL, 'This is my father', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937422/audio/leiyjr3zfk3rhthfrsd6.mp3', 'mother brother sister', 'EASY', NOW(), 'admin', FALSE),
    (39, 6,  3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Chúng tôi là gia đình', 'We are family', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (40, 7,  3, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (41, 8,  3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Đó là con của chúng tôi', 'That is our child', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (42, 9,  3, 'GIVE_AUDIO_REARRANGE_WORDS', 'Cô ấy là chị tôi', 'She is my sister', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732937422/audio/leiyjr3zfk3rhthfrsd6.mp3', NULL, 'EASY', NOW(), 'admin', FALSE),
    (43, 10,  3, 'GIVE_AUDIO_REARRANGE_WORDS', 'Anh trai của bạn là ai', 'Who is your brother ?', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1732965290/audio/uilamzoggbnwage3ckch.mp3', 'my your their', 'HARD', NOW(), 'admin', FALSE),
    (44, 11,  3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Anh ấy là anh bạn à', 'Is he your brother ?', NULL, 'family father', 'HARD', NOW(), 'admin', FALSE),
    (45, 12,  3, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Ông ấy là bố tôi', 'He is my father', NULL, 'him mine am his', 'HARD', NOW(), 'admin', FALSE),
    (46, 13,  3, 'GIVE_AUDIO_REARRANGE_WORDS', 'Tôi có một chị gái và hai anh trai', 'I have a sister and two brother', '', 'parent three an', 'HARD', NOW(), 'admin', FALSE),
    (47, 14,  3, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'HARD', NOW(), 'admin', FALSE), 
-- Câu hỏi bài học job
    (48, 1, 4, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (49, 2, 4, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (50, 3, 4, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (51, 4, 4, 'LEARN_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (52, 5, 4, 'GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (53, 6, 4, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Họ là bác sĩ', 'They are doctors', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (54, 7, 4, 'GIVE_MEAN_ENTER_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    (55, 8, 4, 'GIVE_AUDIO_REARRANGE_WORDS', 'Một cô y tá và một bác sĩ', 'A nurse and a doctor', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1733930479/lu1nwi6ehos2igtd2wl1.mp3', NULL, 'EASY', NOW(), 'admin', FALSE), 
    (56, 9, 4, 'GIVE_AUDIO_REARRANGE_WORDS', 'Cậu ấy là một kĩ sư IT', 'He is an IT engineer', 'https://res.cloudinary.com/dqzwh7zvo/video/upload/v1733930631/kugwqszmfzj4jw4zosyr.mp3', 'Him your doctor', 'HARD', NOW(), 'admin', FALSE), 
    (57, 10, 4, 'GIVE_SENTENSE_REARRANGE_WORDS', 'Nghề nghiệp của bạn là gì?', 'What is your job?', NULL, 'When you', 'EASY', NOW(), 'admin', FALSE),
    (58, 11, 4, 'GIVE_MEAN_ENTER_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE);

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
    (10, 3, TRUE, NOW(), 'admin', FALSE),
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
    (19, 6, TRUE, NOW(), 'admin', FALSE),
    (20, 7, TRUE, NOW(), 'admin', FALSE),
    (21, 8, TRUE, NOW(), 'admin', FALSE),
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

INSERT INTO lesson_history (lesson_id, owner, status, total_time, accuracy, created_at, is_disabled, elo) 
VALUES
    (1, 'duyhelloworld', 'COMPLETED', 300, 95, NOW(), FALSE, 100),
    (1, 'duyhelloworld', 'COMPLETED', 270, 100, NOW(), FALSE, 150),
    (1, 'duyhelloworld', 'COMPLETED', 280, 97, NOW(), FALSE, 100),
    (4, 'duyhelloworld', 'COMPLETED', 290, 85, NOW(), FALSE, 100),
    (4, 'duyhelloworld', 'COMPLETED', 290, 90, NOW(), FALSE, 100),
    (4, 'duyhelloworld', 'COMPLETED', 240, 100, NOW(), FALSE, 150),
    (2, 'duyhelloworld', 'ONGOING', NULL, NULL, NOW(), FALSE, NULL),
    (1, 'plus', 'COMPLETED', 350, 90, NOW(), FALSE, 100),
    (3, 'plus', 'COMPLETED', 320, 85, NOW(), FALSE, 100),
    (4, 'plus', 'ONGOING', NULL, NULL, NOW(), FALSE, NULL),
    (2, 'lechau', 'COMPLETED', 300, 92, NOW(), FALSE, 100),
    (3, 'lechau', 'ONGOING', NULL, NULL, NOW(), FALSE, NULL),
    (1, 'phamduy', 'ONGOING', NULL, NULL, NOW(), FALSE, NULL),
    (2, 'phamduy', 'COMPLETED', 340, 88, NOW(), FALSE, 80),
    (1, 'buiha', 'COMPLETED', 290, 80, NOW(), FALSE, 90),
    (4, 'buiha', 'ONGOING', NULL, NULL, NOW(), FALSE, NULL);

INSERT INTO leader_board (`type`, created_by, created_at, is_deleted)
VALUES 
    ('WEEKLY', 'admin', NOW(), FALSE),
    ('MONTHLY', 'admin', NOW(), FALSE),
    ('ALL_TIME', 'admin', NOW(), FALSE);

INSERT INTO leader_board_user (leader_board_id, user_id, elo, user_rank) 
VALUES 
    (1, 2, 1600, 2), 
    (1, 3, 1500, 3), 
    (1, 4, 1400, 6), 
    (1, 5, 1300, 4), 
    (1, 6, 1000, 5), 
    (1, 7, 900, 10),
    (1, 8, 800, 7),
    (1, 9, 700, 8),
    (1, 10, 600, 9),
    (1, 11, 500, 11),
    (2, 1, 2100, 2),
    (2, 2, 1900, 3),
    (2, 3, 2000, 7),
    (2, 4, 1800, 4),
    (2, 5, 1400, 5),
    (2, 6, 1300, 6),
    (2, 7, 1200, 8),
    (2, 8, 1100, 9),
    (2, 9, 1000, 11),
    (2, 10, 900, 10),
    (3, 1, 3000, 2),
    (3, 2, 2900, 3),
    (3, 3, 2500, 5),
    (3, 4, 2400, 6),
    (3, 5, 2300, 4),
    (3, 6, 2200, 7),
    (3, 7, 2100, 8),
    (3, 8, 2000, 9),
    (3, 9, 1900, 10),
    (3, 10, 1800, 11);