USE `be-foreigner`;

INSERT INTO `user` (username, fullname, avatar_url, email, password, streak_days, temp_code, is_first_try, is_allow_mail, is_allow_notification, role, level, learn_count, plan, provider) 
VALUES  
    ('admin', 'Chủ thớt', 'https://res.cloudinary.com/dqzwh7zvo/image/upload/v1732243632/user_avatar/wqpodmvtdx2z0rthifkp.png', 'admin@gmail.com', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 10, null, true, true, true, 'ADMIN', 'BEGINNER', 5, 'FREE', 'LOCAL'),  
    ('duyhelloworld', 'Duy Pham', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png', 'duy0184466@huce.edu.vn', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 0, null, true, true, true, 'USER', 'BEGINNER', 5, 'FREE', 'LOCAL'),  
    ('plus', 'Khách VIP', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png','khachvip@huce.edu.vn', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 5, null, true, true, true, 'USER', 'BEGINNER', 5, 'PREMIUM_MONTH', 'LOCAL'),  
    ('lechau', 'Lê Châu', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png','lechau@outlook.com', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 1, null, true, true, true, 'USER', 'BEGINNER', 5, 'FREE', 'LOCAL'),  
    ('phamduy', 'Phạm Duy', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png', 'phamduy@huce.edu.vn', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 0, null, true, true, true, 'USER', 'BEGINNER', 5, 'FREE', 'LOCAL'),  
    ('buiha', 'Bùi Hà', 'https://icons.veryicon.com/png/o/miscellaneous/rookie-official-icon-gallery/225-default-avatar.png', 'buiha@live.com', '$2a$10$jVmFLvWWNRJZixLn0KwamuamsP23grqOMxXzQO2MqW5rNaUSe2Ql.', 1, null, true, true, true, 'USER', 'BEGINNER', 5, 'FREE', 'LOCAL');

INSERT INTO user_token (token, `type`, last_modified_at, last_modified_by, is_disabled) VALUES
    ('3e1c76e7-fc31-4dbf-81c5-8c9bfa4351d2', 'REFRESH', NOW(), 'admin', FALSE),
    ('7ad2f25c-3b9e-41c1-b20e-34c914ad923e', 'REFRESH', NOW(), 'duyhelloworld', FALSE),
    ('9d71c5de-bf1f-4f5e-908d-c54e1d4fbd94', 'REFRESH', NOW(), 'plus', FALSE),
    ('a8e2f91b-cf88-4f77-84bb-1f76c6714383', 'REFRESH', NOW(), 'lechau', FALSE),
    ('d6b22e91-75ea-4ab0-9603-9efec18461d6', 'REFRESH', NOW(), 'phamduy', FALSE),
    ('ec8c37d3-3e59-4c4c-b3ad-1cd4185c2cb1', 'REFRESH', NOW(), 'buiha', FALSE),
    ('cgkY0O2aQIqKpH7ftIe2G-:APA91bEqstwjNGiWIVnoD41hVQPJ5yv2HOEB6iNhfJ2mTcz7bzKLnqjaElini2eeB5K3M3XC5qg5pMu0t-EnXeS1S8WLET_reA04C8xG6fmI56OGx6TAKew', 'NOTIFICATION', NOW(), 'duyhelloworld', FALSE);
    
INSERT INTO word (value, mean, phonetic, audio_url, image_url, created_at, created_by, is_deleted) VALUES 
('I', 'Tôi', '/aɪ/', 'https://d1qx7pbj0dvboc.cloudfront.net/I.mp3', 'https://cdn-icons-png.flaticon.com/512/6858/6858504.png', NOW(), 'admin', FALSE),
('You', 'Bạn', '/juː/', 'https://d1qx7pbj0dvboc.cloudfront.net/You.mp3', 'https://www.w3schools.com/howto/img_avatar.png', NOW(), 'admin', FALSE),
('He', 'Anh ấy', '/hiː/', 'https://d1qx7pbj0dvboc.cloudfront.net/He.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJXFY1Qq1gCP4ZeDIITDKhIdicPFrEbGX1zQ&s', NOW(), 'admin', FALSE),
('She', 'Cô ấy', '/ʃiː/', 'https://d1qx7pbj0dvboc.cloudfront.net/She.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSs8Pp4mFafyknDfuYB9oORBZHWMCoM8dr1yA&s', NOW(), 'admin', FALSE),
('It', 'Nó', '/ɪt/', 'https://d1qx7pbj0dvboc.cloudfront.net/It.mp3', 'https://img.freepik.com/premium-vector/ninja-with-tablet-pc-cute-cartoon-style-vector-illustration_1142-77341.jpg', NOW(), 'admin', FALSE),
('We', 'Chúng tôi', '/wiː/', 'https://d1qx7pbj0dvboc.cloudfront.net/We.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1iUZ5if2kDU9BNe5Qh8jB1dbEH_whpXrFWw&s', NOW(), 'admin', FALSE),
('They', 'Họ', '/ðeɪ/', 'https://d1qx7pbj0dvboc.cloudfront.net/They.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqUevNko-A8x7OpyXwnccDBnnYERMcxMissw&s', NOW(), 'admin', FALSE),
('Elephant', 'Voi', '/ˈɛlɪfənt/', 'https://d1qx7pbj0dvboc.cloudfront.net/Elephant.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSamvTpkKyot8rCC2ZtWxYyokyQfr-1o9Pqnw&s', NOW(), 'admin', FALSE),
('Tiger', 'Hổ', '/ˈtaɪɡər/', 'https://d1qx7pbj0dvboc.cloudfront.net/Tiger.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxuvLdD0LJLsqyDSLWpl6VWNbB7e0TxzeTkQ&s', NOW(), 'admin', FALSE),
('Monkey', 'Khỉ', '/ˈmʌŋki/', 'https://d1qx7pbj0dvboc.cloudfront.net/Monkey.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMkKHeEvzBVLjXYte6Nb8qCzBhE9zRc_T7yQ&s', NOW(), 'admin', FALSE),
('Rabbit', 'Thỏ', '/ˈræbɪt/', 'https://d1qx7pbj0dvboc.cloudfront.net/Rabbit.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFlJAqyLYLsZIvy1Gm_BP4D_f83EPdfOfZYw&s', NOW(), 'admin', FALSE),
('Horse', 'Ngựa', '/hɔːrs/', 'https://d1qx7pbj0dvboc.cloudfront.net/Horse.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhrOxVVkaJz5pycqOudu9bfNrN4EAcSeRhcw&s', NOW(), 'admin', FALSE),
('Bear', 'Gấu', '/bɛər/', 'https://d1qx7pbj0dvboc.cloudfront.net/Bear.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZ-eo0-05BNn4XPA23qXwLCsvoypHEAORGyg&s', NOW(), 'admin', FALSE),
('Lion', 'Sư tử', '/ˈlaɪən/', 'https://d1qx7pbj0dvboc.cloudfront.net/Lion.mp3', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/020_The_lion_king_Snyggve_in_the_Serengeti_National_Park_Photo_by_Giles_Laurent.jpg/1200px-020_The_lion_king_Snyggve_in_the_Serengeti_National_Park_Photo_by_Giles_Laurent.jpg', NOW(), 'admin', FALSE),
('Deer', 'Nai', '/dɪər/', 'https://d1qx7pbj0dvboc.cloudfront.net/Deer.mp3', 'https://tracuuduoclieu.vn/wp-content/uploads/2018/04/huou-nai-dltl3.jpg', NOW(), 'admin', FALSE),
('Am', 'Là (dùng với I)', '/æm/', 'https://d1qx7pbj0dvboc.cloudfront.net/Am.mp3', '', NOW(), 'admin', FALSE),
('Is', 'Là (dùng với He, She, It)', '/ɪz/', 'https://d1qx7pbj0dvboc.cloudfront.net/Is.mp3', '', NOW(), 'admin', FALSE),
('Are', 'Là (dùng với You, We, They)', '/ɑːr/', 'https://d1qx7pbj0dvboc.cloudfront.net/Are.mp3', '', NOW(), 'admin', FALSE),
('Like', 'Thích', '/laɪk/', 'https://d1qx7pbj0dvboc.cloudfront.net/Like.mp3', '', NOW(), 'admin', FALSE),
('Love', 'Yêu, rất thích', '/lʌv/', 'https://d1qx7pbj0dvboc.cloudfront.net/Love.mp3', 'https://images.theconversation.com/files/122137/original/image-20160511-18171-kulas4.jpg?ixlib=rb-4.1.0&q=45&auto=format&w=926&fit=clip', NOW(), 'admin', FALSE),
('Animal', 'Động vật', '/ˈænɪməl/', 'https://d1qx7pbj0dvboc.cloudfront.net/Animal.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzbZ-vvffinVbf9rk3G-qvPyndA2VTI0b22Q&s', NOW(), 'admin', FALSE),
('Wild', 'Hoang dã', '/waɪld/', 'https://d1qx7pbj0dvboc.cloudfront.net/Wild.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6x09vWZvlabltgB7nzMmMH8_TGPgSkDeV8Q&s', NOW(), 'admin', FALSE),
('Nice', 'Dễ thương, đẹp', '/naɪs/', 'https://d1qx7pbj0dvboc.cloudfront.net/Nice.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTi1oaz8S6aQY_NKQHx37aRbwvUxaLnBtocAA&s', NOW(), 'admin', FALSE),
('Friendly', 'Thân thiện', '/ˈfrɛndli/', 'https://d1qx7pbj0dvboc.cloudfront.net/Friendly.mp3', 'https://www.wikihow.com/images/thumb/e/ee/Be-Friendly-Step-21-Version-2.jpg/aid112343-v4-1200px-Be-Friendly-Step-21-Version-2.jpg', NOW(), 'admin', FALSE),
('Dangerous', 'Nguy hiểm', '/ˈdeɪndʒərəs/', 'https://d1qx7pbj0dvboc.cloudfront.net/Dangerous.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGRdstdfuoDr_YKgZjKYgbzwZRQJ6JBU42eQ&s', NOW(), 'admin', FALSE),
('Cute', 'Đáng yêu', '/kjuːt/', 'https://d1qx7pbj0dvboc.cloudfront.net/Cute.mp3', 'https://www.ismartkids.vn/uploads/images/cute-la-gi-cach-de-tro-thanh-mot-co-gai-cute-2.jpeg', NOW(), 'admin', FALSE),
('Domestic', 'Thuần hóa', '/dəˈmɛstɪk/', 'https://d1qx7pbj0dvboc.cloudfront.net/Domestic.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYDtNtnfEyvrUBi3n-_aEMFM1Mz_aZNUNcOw&s', NOW(), 'admin', FALSE);

INSERT INTO lesson (name, description, elo, level, type, cover_image_url, created_at, created_by, is_deleted)
VALUES 
    ('Kiểm tra trình độ', 'Test Level', 0, 'BEGINNER', 'FREE_ACCESS', 'https://picsum.photos/536/354', NOW(), 'admin', FALSE),
    ('Các đại từ nhân xưng', NULL, 5, 'BEGINNER', 'FREE_ACCESS', 'https://quizizz.com/media/resource/gs/quizizz-media/quizzes/ee15d05a-5a23-482d-aef0-b7fd00fe12ed?w=400&h=400', NOW(), 'admin', FALSE),
    ('Các loài động vật phổ biến 1', NULL, 5, 'INTERMEDIATE', 'FREE_ACCESS', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgTY4OLimzPvu3tihBGecs8r5-B8bhYkFZ0w&s', NOW(), 'admin', FALSE),
    ('Các loài động vật phổ biến 2', NULL, 5, 'BEGINNER', 'PLUS_ONLY', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQL8brmRxersT0sU68I8Q9YPefFNT-yzM7BrdPPyO8_SqxEwur_qBNdFJr0gwCIXSxVUsw&usqp=CAU', NOW(), 'admin', FALSE),
    ('Bài học trống', NULL, 5, 'BEGINNER', 'FREE_ACCESS', 'https://picsum.photos/536/536', NOW(), 'admin', FALSE),
    ('Bài học trống', NULL, 5, 'BEGINNER', 'FREE_ACCESS', 'https://picsum.photos/536/536', NOW(), 'admin', FALSE),
    ('Bài học trống', NULL, 5, 'BEGINNER', 'FREE_ACCESS', 'https://picsum.photos/536/536', NOW(), 'admin', FALSE),
    ('Bài học trống', NULL, 5, 'BEGINNER', 'FREE_ACCESS', 'https://picsum.photos/536/536', NOW(), 'admin', FALSE);

INSERT INTO lesson_words (lessons_id, words_id) VALUES 
    (2, 1), -- I
    (2, 2), -- You
    (2, 3), -- He
    (2, 4), -- She
    (2, 5), -- It
    (2, 6), -- We
    (2, 7), -- They
    (3, 8), -- Elephant
    (3, 9), -- Tiger
    (3, 10), -- Monkey
    (3, 11), -- Rabbit
    (3, 12), -- Horse
    (4, 13), -- Bear
    (4, 14), -- Lion
    (4, 15), -- Deer
    (4, 21), -- Animal
    (4, 22), -- Wild
    (4, 23), -- Nice
    (4, 24), -- Friendly
    (4, 25), -- Dangerous
    (4, 26), -- Cute
    (4, 27), -- Domestic
    (1, 16), -- Am
    (1, 17), -- Is
    (1, 18), -- Are
    (1, 19), -- Like
    (1, 20); -- Love


-- Câu hỏi cho Lesson 2: Các đại từ nhân xưng
INSERT INTO question (type, sentense_meaning, sentense_words, sentense_audio, unrelated_words, level, created_at, created_by, is_deleted) VALUES
    -- Câu dạng GIVE_MEAN_CHOOSE_WORD
    ('GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE), -- Nghĩa: 'Tôi', chọn từ 'I'
    ('GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE), -- Nghĩa: 'Bạn', chọn từ 'You'
    -- Câu dạng GIVE_SENTENSE_REARRANGE_WORDS
    ('GIVE_SENTENSE_REARRANGE_WORDS', 'Anh ấy thích cô ấy', 'He likes her', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    ('GIVE_SENTENSE_REARRANGE_WORDS', 'Chúng tôi là bạn', 'We are friends', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    -- Câu dạng GIVE_AUDIO_REARRANGE_WORDS
    ('GIVE_AUDIO_REARRANGE_WORDS', NULL, 'They are happy', '', NULL, 'EASY', NOW(), 'admin', FALSE), -- 'They are happy.'
    ('GIVE_AUDIO_REARRANGE_WORDS', NULL, 'She is my teacher', '', 'my your their', 'HARD', NOW(), 'admin', FALSE), -- 'She is my teacher.'
    -- Câu dạng GIVE_AUDIO_CHOOSE_WORD
    ('GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, 'https://d1qx7pbj0dvboc.cloudfront.net/I.mp3', NULL, 'EASY', NOW(), 'admin', FALSE),
    ('GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, 'https://d1qx7pbj0dvboc.cloudfront.net/We.mp3', NULL, 'EASY', NOW(), 'admin', FALSE),
-- Câu hỏi cho Lesson 3: Các loài động vật phổ biến 1
    -- Câu dạng GIVE_MEAN_CHOOSE_WORD
    ('GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE), -- Nghĩa: 'Voi', chọn từ 'Elephant'
    ('GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE), -- Nghĩa: 'Hổ', chọn từ 'Tiger'
    -- Câu dạng GIVE_SENTENSE_REARRANGE_WORDS
    ('GIVE_SENTENSE_REARRANGE_WORDS', 'Con khỉ ở trên cây', 'The monkey is on the tree', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    ('GIVE_SENTENSE_REARRANGE_WORDS', 'Con thỏ dễ thương', 'The rabbit is cute', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    -- Câu dạng GIVE_AUDIO_REARRANGE_WORDS
    ('GIVE_AUDIO_REARRANGE_WORDS', NULL, 'The horse is running', 'running.mp3', NULL, 'EASY', NOW(), 'admin', FALSE), -- 'The horse is running.'
    ('GIVE_AUDIO_REARRANGE_WORDS', NULL, 'The tiger is dangerous', '', 'wild safe big', 'HARD', NOW(), 'admin', FALSE), -- 'The tiger is dangerous.'
    -- Câu dạng GIVE_AUDIO_CHOOSE_WORD
    ('GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, 'https://d1qx7pbj0dvboc.cloudfront.net/Elephant.mp3', NULL, 'EASY', NOW(), 'admin', FALSE),
    ('GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, 'https://d1qx7pbj0dvboc.cloudfront.net/Monkey.mp3', NULL, 'EASY', NOW(), 'admin', FALSE),
-- Câu hỏi cho Lesson 4: Các loài động vật phổ biến 2
    -- Câu dạng GIVE_MEAN_CHOOSE_WORD
    ('GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE), -- Nghĩa: 'Gấu', chọn từ 'Bear'
    ('GIVE_MEAN_CHOOSE_WORD', NULL, NULL, NULL, NULL, 'EASY', NOW(), 'admin', FALSE), -- Nghĩa: 'Sư tử', chọn từ 'Lion'
    -- Câu dạng GIVE_SENTENSE_REARRANGE_WORDS
    ('GIVE_SENTENSE_REARRANGE_WORDS', 'Con nai đang ăn cỏ', 'The deer is eating grass', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    ('GIVE_SENTENSE_REARRANGE_WORDS', 'Động vật hoang dã thật đẹp', 'Wild animals are beautiful', NULL, NULL, 'EASY', NOW(), 'admin', FALSE),
    -- Câu dạng GIVE_AUDIO_REARRANGE_WORDS
    ('GIVE_AUDIO_REARRANGE_WORDS', NULL, 'The bear is climbing', 'climbing.mp3', NULL, 'EASY', NOW(), 'admin', FALSE), -- 'The bear is climbing.'
    ('GIVE_AUDIO_REARRANGE_WORDS', NULL, 'The lion is the king', 'king.mp3', 'queen cub roar', 'HARD', NOW(), 'admin', FALSE), -- 'The lion is the king.'
    -- Câu dạng GIVE_AUDIO_CHOOSE_WORD
    ('GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, 'Bear.mp3', NULL, 'EASY', NOW(), 'admin', FALSE),
    ('GIVE_AUDIO_CHOOSE_WORD', NULL, NULL, 'Deer.mp3', NULL, 'EASY', NOW(), 'admin', FALSE);

-- Chèn các đáp án vào bảng answer
-- Chèn các đáp án vào bảng answer (bao gồm cột image)
INSERT INTO answer (txt, audio, image, is_true, question_id, created_at, created_by, is_deleted) VALUES
    -- Đại từ nhân xưng
    ('I', 'https://d1qx7pbj0dvboc.cloudfront.net/I.mp3', 'https://cdn-icons-png.flaticon.com/512/6858/6858504.png', TRUE, 1, NOW(), 'admin', FALSE),
    ('You', 'https://d1qx7pbj0dvboc.cloudfront.net/You.mp3', 'https://www.w3schools.com/howto/img_avatar.png', FALSE, 1, NOW(), 'admin', FALSE),
    ('He', 'https://d1qx7pbj0dvboc.cloudfront.net/He.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJXFY1Qq1gCP4ZeDIITDKhIdicPFrEbGX1zQ&s', FALSE, 1, NOW(), 'admin', FALSE),
    ('She', 'https://d1qx7pbj0dvboc.cloudfront.net/She.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSs8Pp4mFafyknDfuYB9oORBZHWMCoM8dr1yA&s', FALSE, 1, NOW(), 'admin', FALSE),
    ('You', 'https://d1qx7pbj0dvboc.cloudfront.net/You.mp3', 'https://www.w3schools.com/howto/img_avatar.png', TRUE, 2, NOW(), 'admin', FALSE),
    ('I', 'https://d1qx7pbj0dvboc.cloudfront.net/I.mp3', 'https://cdn-icons-png.flaticon.com/512/6858/6858504.png', FALSE, 2, NOW(), 'admin', FALSE),
    ('We', 'https://d1qx7pbj0dvboc.cloudfront.net/We.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1iUZ5if2kDU9BNe5Qh8jB1dbEH_whpXrFWw&s', FALSE, 2, NOW(), 'admin', FALSE),
    ('They', 'https://d1qx7pbj0dvboc.cloudfront.net/They.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqUevNko-A8x7OpyXwnccDBnnYERMcxMissw&s', FALSE, 2, NOW(), 'admin', FALSE),
    ('Elephant', 'https://d1qx7pbj0dvboc.cloudfront.net/Elephant.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSamvTpkKyot8rCC2ZtWxYyokyQfr-1o9Pqnw&s', TRUE, 9, NOW(), 'admin', FALSE),
    ('Tiger', 'https://d1qx7pbj0dvboc.cloudfront.net/Tiger.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxuvLdD0LJLsqyDSLWpl6VWNbB7e0TxzeTkQ&s', FALSE, 9, NOW(), 'admin', FALSE),
    ('Rabbit', 'https://d1qx7pbj0dvboc.cloudfront.net/Rabbit.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFlJAqyLYLsZIvy1Gm_BP4D_f83EPdfOfZYw&s', FALSE, 9, NOW(), 'admin', FALSE),
    ('Monkey', 'https://d1qx7pbj0dvboc.cloudfront.net/Monkey.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMkKHeEvzBVLjXYte6Nb8qCzBhE9zRc_T7yQ&s', FALSE, 9, NOW(), 'admin', FALSE),
    ('Tiger', 'https://d1qx7pbj0dvboc.cloudfront.net/Tiger.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxuvLdD0LJLsqyDSLWpl6VWNbB7e0TxzeTkQ&s', TRUE, 10, NOW(), 'admin', FALSE),
    ('Elephant', 'https://d1qx7pbj0dvboc.cloudfront.net/Elephant.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSamvTpkKyot8rCC2ZtWxYyokyQfr-1o9Pqnw&s', FALSE, 10, NOW(), 'admin', FALSE),
    ('Monkey', 'https://d1qx7pbj0dvboc.cloudfront.net/Monkey.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMkKHeEvzBVLjXYte6Nb8qCzBhE9zRc_T7yQ&s', FALSE, 10, NOW(), 'admin', FALSE),
    ('Rabbit', 'https://d1qx7pbj0dvboc.cloudfront.net/Rabbit.mp3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFlJAqyLYLsZIvy1Gm_BP4D_f83EPdfOfZYw&s', FALSE, 10, NOW(), 'admin', FALSE);

INSERT INTO lesson_history () VALUES ();


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

INSERT INTO lesson_history (lesson_id, status, total_time, accuracy, created_at, owner, is_disabled)
VALUES
    (1, 'COMPLETED', 610, 85, NOW(), 'duyhelloworld', FALSE),
    (2, 'COMPLETED', 222, 90, NOW(), 'duyhelloworld',  FALSE),
    (3, 'ONGOING', NULL, 75, NOW(), 'duyhelloworld', FALSE),
    (1, 'COMPLETED', 540, 95, NOW(), 'plus', FALSE),
    (2, 'COMPLETED', 450, 70, NOW(), 'plus',  FALSE),
    (3, 'COMPLETED', 400, 98, NOW(), 'plus', FALSE),
    (1, 'ONGOING', NULL, 50, NOW(), 'lechau', FALSE),
    (2, 'COMPLETED', 1200, 80, NOW(), 'lechau', FALSE),
    (3, 'COMPLETED', 890, 85, NOW(), 'lechau', FALSE),
    (1, 'COMPLETED', 440, 60, NOW(), 'buiha', FALSE),
    (2, 'COMPLETED', 500, 88, NOW(), 'buiha',  FALSE),
    (3, 'ONGOING', NULL, 75, NOW(), 'buiha',  FALSE);
