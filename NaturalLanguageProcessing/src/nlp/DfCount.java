package nlp;

class DfCount extends WordCount
{
 public Double getIdf() {
		return idf;
	}
	public void setIdf(Double idf) {
		this.idf = idf;
	}

private Double idf;
 DfCount(Word word,Integer count){
	 super(word,count);
 }
}