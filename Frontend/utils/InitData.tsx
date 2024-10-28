import { Lesson, LessonDetail, Ranking, Topic, Word } from "../types/apimodels";
import { LessonStatus, QuestionType, WordType } from "../types/enum";

export function randomTopicId(): number {
  const Topics = sampleTopics();
  return Math.floor(Math.random() * Topics.length + 1);
}

export function sampleTopic(): Topic {
  return {
    id: 1,
    name: "Động vật",
    learnCount: 0,
    coverImage:
      "https://uberhumor.com/wp-content/uploads/2011/03/these_funny_animals_652_640_30.jpg",
  };
}

export function sampleTopics(): Topic[] {
  return [
    {
      id: 1,
      name: "Động vật",
      learnCount: 0,
      coverImage:
        "https://uberhumor.com/wp-content/uploads/2011/03/these_funny_animals_652_640_30.jpg",
    },
    {
      id: 2,
      name: "Linh trưởng",
      learnCount: 0,
      coverImage:
        "https://cdnphoto.dantri.com.vn/okMdRVrewuFMQmRQrZJ8BYSWVDg=/2024/07/22/z5642619190636c4477f95f3d45e7da4f0f994392f7545-1721664636331.jpg",
    },
    {
      id: 3,
      name: "Ước mơ",
      learnCount: 0,
      coverImage:
        "https://m.media-amazon.com/images/I/51gLBzPWoLL._AC_SL1354_.jpg",
    },
    {
      id: 4,
      name: "Phong cảnh",
      learnCount: 0,
      coverImage:
        "https://uberhumor.com/wp-content/uploads/2011/03/these_funny_animals_652_640_30.jpg",
    },
  ];
}

export function sampleWord(): Word {
  return {
    id: 1,
    value: "Animal",
    audio: "https://d1qx7pbj0dvboc.cloudfront.net/animal.mp3",
    mean: "Động vật",
    examples: [
      { sentense: "Animal is important", mean: "Động vật rất quan trọng" },
      { sentense: "Dog is an animal", mean: "Chó là một loài động vật" },
    ],
    phonetic: "['anəm(ə)l]",
    type: WordType.NOUN,
    image: "https://m.yodycdn.com/blog/duong-tang-meme-yody-vn-2.jpg",
  };
}

export function sampleWords(): Word[] {
  return [
    {
      id: 1,
      value: "Animal",
      audio: "https://d1qx7pbj0dvboc.cloudfront.net/animal.mp3",
      mean: "Động vật",
      image: "https://i.ytimg.com/vi/m7Yr9iY8gLY/maxresdefault.jpg",
      examples: [
        { sentense: "Animal is important", mean: "Động vật rất quan trọng" },
        { sentense: "Dog is an animal", mean: "Chó là một loài động vật" },
      ],
      phonetic: "['anəm(ə)l]",
      type: WordType.NOUN,
    },
    {
      id: 2,
      value: "Cat",
      mean: "Mều",
      audio: "https://d1qx7pbj0dvboc.cloudfront.net/cat.mp3",
      image:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_wtjwch7hAvEU5YMynFOwUOcSeQ3YTi7vzg&s",
      examples: [
        { sentense: "Duy dep trai so much", mean: "Duy đẹp trai vờ cờ lờ" },
        { sentense: "", mean: "" },
      ],
    },
    {
      id: 3,
      value: "Dog",
      mean: "Con chó",
      audio: "https://d1qx7pbj0dvboc.cloudfront.net/dog.mp3",
      image:
        "https://fagopet.vn/storage/v7/ch/v7che47zyux8lz9vxk918t4ok0nn_phoi-giong-cho-phoc-soc.webp",
      examples: [
        { sentense: "Test câu 3", mean: "Nghĩa câu 3" },
        { sentense: "Test câu 3.2", mean: "Nghĩa câu 3.2" },
      ],
    },
    {
      id: 4,
      value: "Human",
      mean: "Con người",
      audio: "https://d1qx7pbj0dvboc.cloudfront.net/human.mp3",
      image:
        "https://t4.ftcdn.net/jpg/00/96/48/11/360_F_96481143_EDJRxhplkTUrdgXE4R45XAX0cHFr8QTC.jpg",
      examples: [
        { sentense: "Test câu 4", mean: "Nghĩa câu 4" },
        { sentense: "Test câu 4", mean: "Nghĩa câu 4" },
      ],
    },
    {
      id: 5,
      value: "Trousers",
      mean: "Quần",
      audio: "https://d1qx7pbj0dvboc.cloudfront.net/trousers.mp3",
      image:
        "https://bizweb.dktcdn.net/100/287/440/products/quan-jean-nam-ong-rong-local-brand-dep-13.jpg?v=1665054928380",
      examples: [
        { sentense: "Test câu 3", mean: "Nghĩa câu 5" },
        { sentense: "Test câu 5", mean: "Nghĩa câu 5" },
      ],
    },
    {
      id: 6,
      value: "Car",
      mean: "Xe ô tô",
      audio: "https://d1qx7pbj0dvboc.cloudfront.net/trousers.mp3",
      image:
        "https://imagev3.vietnamplus.vn/w1000/Uploaded/2024/qrndqxjwp/2022_01_15/5c13378d057c480a8e98c64a413e20613682161636517973.jpg.webp",
      examples: [
        { sentense: "Test câu 3", mean: "Nghĩa câu 6" },
        { sentense: "Test câu 6", mean: "Nghĩa câu 6" },
      ],
    },
  ];
}

export const sampleLesson = (): Lesson => {
  return {
    id: 1,
    name: "Bài học đầu đời",
    cover:
      "https://baogiaothong.mediacdn.vn/upload/images/2020-1/article_img/2020-01-20/den-1579510145-width800height450.jpg",
    lastLearnQuestion: 1, 
    status: LessonStatus.ONGOING,
    totalQuestion: 3
  };
};

export function sampleLessons() : Lesson[] {
return [
  {
    id: 1,
    name: "Rap",
    cover:
      "https://baogiaothong.mediacdn.vn/upload/images/2020-1/article_img/2020-01-20/den-1579510145-width800height450.jpg",
    lastLearnQuestion: 1,
    status: LessonStatus.ONGOING,
    totalQuestion: 3,
  },
  {
    id: 2,
    name: "Animal",
    cover:
      "https://baogiaothong.mediacdn.vn/upload/images/2020-1/article_img/2020-01-20/den-1579510145-width800height450.jpg",
    lastLearnQuestion: 3,
    status: LessonStatus.COMPLETED,
    totalQuestion: 3,
  },
];
}

export function sampleTasks() {
  return [
    {
      id: 1,
      name: "Học tổng cộng 10 phút",
      current: 1,
      total: 5,
      award: { diamonds: 10 },
    },
    {
      id: 1,
      name: "Chia sẻ bài học cho bạn bè",
      current: 1,
      total: 10,
      award: { diamonds: 100 },
    },
    {
      id: 1,
      name: "Hoàn thành 3 bài học Tuyệt đối điện ảnh",
      current: 10,
      total: 10,
      award: { experiences: 100 },
    },
  ];
}

export function sampleRanking() : Ranking {
  return {
    users: [
      {
        avatar:
          "https://i.pinimg.com/1200x/21/00/a6/2100a6013207d5ce52aafc2d61304ea9.jpg",
        rank: 1,
        username: "Boss 1",
        experience: 789,
      },
      {
        avatar:
          "https://cellphones.com.vn/sforum/wp-content/uploads/2024/01/anh-meme-98.jpg",
        rank: 2,
        username: "Superman",
        experience: 690,
      },
      {
        avatar:
          "https://hapotravel.com/wp-content/uploads/2023/03/suu-tam-25-meme-avatar-hai-huoc-va-de-thuong_5.jpg",
        rank: 3,
        username: "Dzuynen",
        experience: 500,
      },
      {
        avatar:
          "https://hapotravel.com/wp-content/uploads/2023/03/suu-tam-25-meme-avatar-hai-huoc-va-de-thuong_5.jpg",
        rank: 4,
        username: "Dzuynen 2",
        experience: 460,
      },
      {
        avatar:
          "https://hapotravel.com/wp-content/uploads/2023/03/suu-tam-25-meme-avatar-hai-huoc-va-de-thuong_5.jpg",
        rank: 5,
        username: "Dzuynen 3",
        experience: 400,
      },
    ],
  };
}

export function sampleLessonDetail() : LessonDetail {
  return {
    id: 1,
    name: "Rap",
    diamonds: 150,
    experiences: 100,
    questions: [
      {
        type: QuestionType.GIVE_MEAN_CHOOSE_WORD,
        correctOption: {
          text: "Animal",
          image: "https://i.ytimg.com/vi/m7Yr9iY8gLY/maxresdefault.jpg",
          audio: "https://d1qx7pbj0dvboc.cloudfront.net/animal.mp3",
        },
        incorrectOptions: [
          {
            text: "Car",
            image:
              "https://imagev3.vietnamplus.vn/w1000/Uploaded/2024/qrndqxjwp/2022_01_15/5c13378d057c480a8e98c64a413e20613682161636517973.jpg.webp",
            audio: "https://d1qx7pbj0dvboc.cloudfront.net/car.mp3",
          },
          {
            text: "Trouser",
            image:
              "https://bizweb.dktcdn.net/100/287/440/products/quan-jean-nam-ong-rong-local-brand-dep-13.jpg?v=1665054928380",
            audio: "https://d1qx7pbj0dvboc.cloudfront.net/trouser.mp3",
          },
          {
            text: "Dog",
            image:
              "https://fagopet.vn/storage/v7/ch/v7che47zyux8lz9vxk918t4ok0nn_phoi-giong-cho-phoc-soc.webp",
            audio: "https://d1qx7pbj0dvboc.cloudfront.net/dog.mp3",
          },
        ],
      },
      {
        type: QuestionType.GIVE_WORDS_REARRANGE_MEANS,
        mainSentense: ["Animal", "is", "important"],
        mainSentenseAudio: require("../temp/animal_is_important.mp3"),
        unrelatedWords: ["Stupid", "Nam", "and", "Golden"],
      },
      {
        type: QuestionType.GIVE_AUDIO_CHOOSE_WORD,
        correctOption: {
          text: "Animal",
          image: "https://i.ytimg.com/vi/m7Yr9iY8gLY/maxresdefault.jpg",
          audio: "https://d1qx7pbj0dvboc.cloudfront.net/animal.mp3",
        },
        incorrectOptions: [
          {
            text: "Car",
            image:
              "https://imagev3.vietnamplus.vn/w1000/Uploaded/2024/qrndqxjwp/2022_01_15/5c13378d057c480a8e98c64a413e20613682161636517973.jpg.webp",
            audio: "https://d1qx7pbj0dvboc.cloudfront.net/car.mp3",
          },
          {
            text: "Trouser",
            image:
              "https://bizweb.dktcdn.net/100/287/440/products/quan-jean-nam-ong-rong-local-brand-dep-13.jpg?v=1665054928380",
            audio: "https://d1qx7pbj0dvboc.cloudfront.net/trouser.mp3",
          },
          {
            text: "Dog",
            image:
              "https://fagopet.vn/storage/v7/ch/v7che47zyux8lz9vxk918t4ok0nn_phoi-giong-cho-phoc-soc.webp",
            audio: "https://d1qx7pbj0dvboc.cloudfront.net/dog.mp3",
          },
        ],
      },
    ],
  };
}