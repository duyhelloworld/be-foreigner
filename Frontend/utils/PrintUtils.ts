export function pretty(o : object) {
  console.log(JSON.stringify(o, null, 2));
}

export function prettyWithMsg(m: string, o: object) {
  console.log(m, JSON.stringify(o, null, 2));
}
