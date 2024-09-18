import { WordType } from "../types/enum";

export function randomTopicId(): number {
  const Topics = sampleTopics();
  return Math.floor(Math.random() * Topics.length + 1);
}
export function sampleTopic(): Topic {
  return {
    id: 1,
    name: "Động vật",
    cover:
      "https://uberhumor.com/wp-content/uploads/2011/03/these_funny_animals_652_640_30.jpg",
  };
}

export function sampleTopics(): Topic[] {
  return [
    {
      id: 1,
      name: "Động vật",
      cover:
        "https://uberhumor.com/wp-content/uploads/2011/03/these_funny_animals_652_640_30.jpg",
    },
    {
      id: 2,
      name: "Linh trưởng",
      cover:
        "https://cdnphoto.dantri.com.vn/okMdRVrewuFMQmRQrZJ8BYSWVDg=/2024/07/22/z5642619190636c4477f95f3d45e7da4f0f994392f7545-1721664636331.jpg",
    },
    {
      id: 3,
      name: "Ước mơ",
      cover: "https://m.media-amazon.com/images/I/51gLBzPWoLL._AC_SL1354_.jpg",
    },
    {
      id: 4,
      name: "Phong cảnh",
      cover:
        "https://uberhumor.com/wp-content/uploads/2011/03/these_funny_animals_652_640_30.jpg",
    },
  ];
}

export function sampleWord(): Word {
  return {
    id: 1,
    value: "Valiu 1",
    audio: "audio1",
    mean: "Giá trị 1",
    examples: [
      { sentense: "1 shot value1", mean: "Ví dụ 1 shot" },
      { sentense: "1 shot value1", mean: "Ví dụ 1 shot" },
      { sentense: "Continue value1", mean: "Ví dụ phát nữa" },
    ],
    phonetic: "value1",
    hint: "va líu 1",
    type: WordType.N,
    image: "https://m.yodycdn.com/blog/duong-tang-meme-yody-vn-2.jpg",
  };
}

export function sampleWords(): Word[] {
  return [
    sampleWord(),
    {
      id: 2,
      value: "Duy dep trai",
      mean: "Duy đẹp trai",
      image:
        "https://cdn2.fptshop.com.vn/unsafe/Uploads/images/tin-tuc/174965/Originals/meme-la-gi-3.jpg",
      examples: [
        { sentense: "Duy dep trai so much", mean: "Duy đẹp trai vờ cờ lờ" },
        { sentense: "", mean: "" },
      ],
    },
    {
      id: 3,
      value: "Test phát 3",
      mean: "Nghĩa test 3",
      image:
        "https://cdn2.fptshop.com.vn/unsafe/Uploads/images/tin-tuc/174965/Originals/meme-la-gi-3.jpg",
      examples: [
        { sentense: "Test câu 3", mean: "Nghĩa câu 3" },
        { sentense: "Test câu 3.2", mean: "Nghĩa câu 3.2" },
      ],
    },
    {
      id: 4,
      value: "Test phát 4",
      mean: "Nghĩa test 4",
      image:
        "https://media3.coolmate.me/cdn-cgi/image/quality=80,format=auto/uploads/March2023/anh-che-meme1_63.jpg",

      examples: [
        { sentense: "Test câu 4", mean: "Nghĩa câu 4" },
        { sentense: "Test câu 4", mean: "Nghĩa câu 4" },
      ],
    },
    {
      id: 5,
      value: "Test phát 5",
      mean: "Nghĩa test 5",
      image:
        "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/03/anh-meme-hai-6.jpg",
      examples: [
        { sentense: "Test câu 3", mean: "Nghĩa câu 5" },
        { sentense: "Test câu 5", mean: "Nghĩa câu 5" },
      ],
    },
    {
      id: 6,
      value: "Test phát 6",
      mean: "Nghĩa test 6",
      examples: [
        { sentense: "Test câu 3", mean: "Nghĩa câu 6" },
        { sentense: "Test câu 6", mean: "Nghĩa câu 6" },
      ],
    },
  ];
}
