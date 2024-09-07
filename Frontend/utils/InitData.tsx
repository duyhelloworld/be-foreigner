import { DiffLevel } from "../types/enum";

export function randomDeckId() : number {
  const decks = sampleDecks();
  return Math.floor(Math.random() * decks.length + 1);
}
export function sampleDeck(): Deck {
  return {
    id: 1,
    name: "Động vật",
    cover:
      "https://uberhumor.com/wp-content/uploads/2011/03/these_funny_animals_652_640_30.jpg",
  };
}

export function sampleDecks(): Deck[] {
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

export function sampleCard(): Card {
  return {
    id: 1,
    word: sampleWord(),
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
      example: ["Duy đẹp trai vờ cờ lờ", "Không Duy thì ai"],
    },
    {
      id: 3,
      value: "Test phát 3",
      mean: "Nghĩa test 3",
      example: ["Ví dụ test 3", "Ví dụ khác test 3"],
    },
    {
      id: 4,
      value: "Test phát 4",
      mean: "Nghĩa test 4",
      example: ["Ví dụ test 4", "Ví dụ khác test 4"],
    },
    {
      id: 5,
      value: "Test phát 5",
      mean: "Nghĩa test 5",
      example: ["Ví dụ test 5", "Ví dụ khác test 5"],
    },
    {
      id: 6,
      value: "Test phát 6",
      mean: "Nghĩa test 6",
      example: ["Ví dụ test 6", "Ví dụ khác test 6"],
    },
  ];
}

export function sampleCards(): Card[] {
  let words = sampleWords();
  return [
    sampleCard(),
    {
      id: 2,
      word: words[1],
      image:
        "https://cdn2.fptshop.com.vn/unsafe/Uploads/images/tin-tuc/174965/Originals/meme-la-gi-3.jpg",
    },
    {
      id: 3,
      word: words[2],
      image:
        "https://media3.coolmate.me/cdn-cgi/image/quality=80,format=auto/uploads/March2023/anh-che-meme1_63.jpg",
    },
    {
      id: 4,
      word: words[3],
      image:
        "https://genk.mediacdn.vn/2018/10/19/photo-1-15399266837281100315834-15399271585711710441111.png",
    },
    {
      id: 5,
      word: words[4],
      image:
        "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/03/anh-meme-hai-6.jpg",
    },
  ];
}

export function sampleWord(): Word {
  return {
    id: 1,
    value: "Valiu 1",
    audio: "audio1",
    mean: "Giá trị 1",
    example: ["Ví dụ 1 shot", "Ví dụ phát nữa"],
    phonetic: "value1",
  };
}
