package nlp;

class WordCount
{
 private Word word;//形態素解析した語の結果を格納する
 public Word getWord() {
	return word;
}
public void setWord(Word word) {
	this.word = word;
}
public Integer getCount() {
	return count;
}
public void setCount(Integer count) {
	this.count = count;
}
private Integer count;//出現回数を計数するためのカウンタ
 WordCount(Word word,Integer count)
 {
 this.word = word;
 this.count = count;
 }
}
