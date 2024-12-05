create table answer (id integer not null auto_increment, is_deleted bit, is_true bit not null, question_id integer, word_id integer, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), primary key (id)) engine=InnoDB;
create table example (id integer not null auto_increment, is_deleted bit, word_id integer, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), mean varchar(255) not null, sentense TEXT not null, primary key (id)) engine=InnoDB;
create table leader_board (id integer not null auto_increment, is_deleted bit, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), type enum ('ALL_TIME','MONTHLY','WEEKLY') not null, primary key (id)) engine=InnoDB;
create table leader_board_user (elo integer not null, id integer not null auto_increment, leader_board_id integer not null, user_id integer not null, user_rank integer not null, primary key (id)) engine=InnoDB;
create table lesson (id integer not null auto_increment, is_deleted bit, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), cover_image_public_id varchar(255), cover_image_url TEXT not null, name varchar(255) not null, level enum ('ADVANCED','BEGINNER','INTERMEDIATE','MEDIUM'), target enum ('LEARN_NEW','REVIEW','TEST'), type enum ('FREE_ACCESS','PLUS_ONLY'), primary key (id)) engine=InnoDB;
create table lesson_history (accuracy float(23), elo integer, id integer not null auto_increment, is_disabled bit not null, lesson_id integer, created_at datetime(6) not null, total_time bigint, updated_at datetime(6), owner varchar(100) not null, status enum ('COMPLETED','ONGOING','UNAVAILABLE'), primary key (id)) engine=InnoDB;
create table question (id integer not null auto_increment, index_in_lesson integer not null, is_deleted bit, lesson_id integer not null, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), sentense_audio varchar(255), sentense_meaning varchar(255), sentense_words varchar(255), unrelated_words varchar(255), level enum ('EASY','HARD'), type enum ('GIVE_AUDIO_CHOOSE_WORD','GIVE_AUDIO_ENTER_WORD','GIVE_AUDIO_REARRANGE_WORDS','GIVE_MEAN_CHOOSE_WORD','GIVE_MEAN_ENTER_WORD','GIVE_SENTENSE_REARRANGE_WORDS','LEARN_WORD') not null, primary key (id)) engine=InnoDB;
create table remind (id integer not null auto_increment, recipient_id integer, created_at datetime(6) not null, updated_at datetime(6), body varchar(255) not null, data varchar(255), title varchar(255) not null, type enum ('EMAIL','NOTIFICATION') not null, is_read BOOLEAN NOT NULL, primary key (id)) engine=InnoDB;
create table user (id integer not null auto_increment, is_allow_mail bit not null, is_allow_notification bit not null, is_first_try bit, is_verified bit not null, quota integer not null, streak_days integer not null, temp_code varchar(6), email varchar(100), password varchar(100), username varchar(100) not null, fullname varchar(200), avatar_filename varchar(255), avatar_public_id varchar(255), avatar_url TEXT not null, code_target enum ('RESET_PASSWORD','VERIFY_EMAIL'), level enum ('ADVANCED','BEGINNER','INTERMEDIATE','MEDIUM') not null, plan enum ('FREE','LIFETIME','PREMIUM_MONTH','PREMIUM_YEAR'), role enum ('ADMIN','USER') not null, primary key (id)) engine=InnoDB;
create table user_token (id integer not null auto_increment, is_disabled bit not null, last_modified_at datetime(6), last_modified_by varchar(255), token tinytext not null, type enum ('NOTIFICATION','REFRESH') not null, primary key (id)) engine=InnoDB;
create table word (id integer not null auto_increment, is_deleted bit, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), audio_filename varchar(255), audio_public_id varchar(255), audio_url TEXT not null, image_filename varchar(255), image_public_id varchar(255), image_url TEXT not null, mean varchar(255), phonetic varchar(255) not null, value varchar(255) not null, primary key (id)) engine=InnoDB;
alter table leader_board add constraint UKd5fai30dvfsvk0bjwypk1t57h unique (type);
alter table user add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user add constraint UKsb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table answer add constraint FK8frr4bcabmmeyyu60qt7iiblo foreign key (question_id) references question (id);
alter table answer add constraint FKiuakviv94kypsmxfhyb9j22yk foreign key (word_id) references word (id);
alter table example add constraint FKevt0wt02b4ev5loch1ec7wqy7 foreign key (word_id) references word (id);
alter table leader_board_user add constraint FKji2h0mxa7bpmmwk53kg0yb5f6 foreign key (leader_board_id) references leader_board (id);
alter table leader_board_user add constraint FKb6n7vugjhgf55yc1lf6vgh07a foreign key (user_id) references user (id);
alter table lesson_history add constraint FKdgqe6beumvugmtk7tbey13u41 foreign key (lesson_id) references lesson (id);
alter table question add constraint FK1sbknhfhhug49n0elkvgk38vs foreign key (lesson_id) references lesson (id);
alter table remind add constraint FKpudqkt8n183vhjbs6t12hhrh1 foreign key (recipient_id) references user (id);
create table answer (id integer not null auto_increment, is_deleted bit, is_true bit not null, question_id integer, word_id integer, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), primary key (id)) engine=InnoDB;
create table example (id integer not null auto_increment, is_deleted bit, word_id integer, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), mean varchar(255) not null, sentense TEXT not null, primary key (id)) engine=InnoDB;
create table leader_board (id integer not null auto_increment, is_deleted bit, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), type enum ('ALL_TIME','MONTHLY','WEEKLY') not null, primary key (id)) engine=InnoDB;
create table leader_board_user (elo integer not null, id integer not null auto_increment, leader_board_id integer not null, user_id integer not null, user_rank integer not null, primary key (id)) engine=InnoDB;
create table lesson (id integer not null auto_increment, is_deleted bit, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), cover_image_public_id varchar(255), cover_image_url TEXT not null, name varchar(255) not null, level enum ('ADVANCED','BEGINNER','INTERMEDIATE','MEDIUM'), target enum ('LEARN_NEW','REVIEW','TEST'), type enum ('FREE_ACCESS','PLUS_ONLY'), primary key (id)) engine=InnoDB;
create table lesson_history (accuracy float(23), elo integer, id integer not null auto_increment, is_disabled bit not null, lesson_id integer, created_at datetime(6) not null, total_time bigint, updated_at datetime(6), owner varchar(100) not null, status enum ('COMPLETED','ONGOING','UNAVAILABLE'), primary key (id)) engine=InnoDB;
create table question (id integer not null auto_increment, index_in_lesson integer not null, is_deleted bit, lesson_id integer not null, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), sentense_audio varchar(255), sentense_meaning varchar(255), sentense_words varchar(255), unrelated_words varchar(255), level enum ('EASY','HARD'), type enum ('GIVE_AUDIO_CHOOSE_WORD','GIVE_AUDIO_ENTER_WORD','GIVE_AUDIO_REARRANGE_WORDS','GIVE_MEAN_CHOOSE_WORD','GIVE_MEAN_ENTER_WORD','GIVE_SENTENSE_REARRANGE_WORDS','LEARN_WORD') not null, primary key (id)) engine=InnoDB;
create table remind (id integer not null auto_increment, recipient_id integer, created_at datetime(6) not null, updated_at datetime(6), body varchar(255) not null, data varchar(255), title varchar(255) not null, type enum ('EMAIL','NOTIFICATION') not null, primary key (id)) engine=InnoDB;
create table user (id integer not null auto_increment, is_allow_mail bit not null, is_allow_notification bit not null, is_first_try bit, is_verified bit not null, quota integer not null, streak_days integer not null, temp_code varchar(6), email varchar(100), password varchar(100), username varchar(100) not null, fullname varchar(200), avatar_filename varchar(255), avatar_public_id varchar(255), avatar_url TEXT not null, code_target enum ('RESET_PASSWORD','VERIFY_EMAIL'), level enum ('ADVANCED','BEGINNER','INTERMEDIATE','MEDIUM') not null, plan enum ('FREE','LIFETIME','PREMIUM_MONTH','PREMIUM_YEAR'), role enum ('ADMIN','USER') not null, primary key (id)) engine=InnoDB;
create table user_token (id integer not null auto_increment, is_disabled bit not null, last_modified_at datetime(6), last_modified_by varchar(255), token tinytext not null, type enum ('NOTIFICATION','REFRESH') not null, primary key (id)) engine=InnoDB;
create table word (id integer not null auto_increment, is_deleted bit, created_at datetime(6) not null, updated_at datetime(6), created_by varchar(100) not null, updated_by varchar(100), audio_filename varchar(255), audio_public_id varchar(255), audio_url TEXT not null, image_filename varchar(255), image_public_id varchar(255), image_url TEXT not null, mean varchar(255), phonetic varchar(255) not null, value varchar(255) not null, primary key (id)) engine=InnoDB;
alter table leader_board add constraint UKd5fai30dvfsvk0bjwypk1t57h unique (type);
alter table user add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user add constraint UKsb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table answer add constraint FK8frr4bcabmmeyyu60qt7iiblo foreign key (question_id) references question (id);
alter table answer add constraint FKiuakviv94kypsmxfhyb9j22yk foreign key (word_id) references word (id);
alter table example add constraint FKevt0wt02b4ev5loch1ec7wqy7 foreign key (word_id) references word (id);
alter table leader_board_user add constraint FKji2h0mxa7bpmmwk53kg0yb5f6 foreign key (leader_board_id) references leader_board (id);
alter table leader_board_user add constraint FKb6n7vugjhgf55yc1lf6vgh07a foreign key (user_id) references user (id);
alter table lesson_history add constraint FKdgqe6beumvugmtk7tbey13u41 foreign key (lesson_id) references lesson (id);
alter table question add constraint FK1sbknhfhhug49n0elkvgk38vs foreign key (lesson_id) references lesson (id);
alter table remind add constraint FKpudqkt8n183vhjbs6t12hhrh1 foreign key (recipient_id) references user (id);