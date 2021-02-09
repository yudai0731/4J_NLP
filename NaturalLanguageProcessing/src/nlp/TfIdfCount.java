package nlp;

public class TfIdfCount extends TfCount{
	double tfidf;
	double idf;

	TfIdfCount(Word word, Integer count, Double tf)
	{
		super(word, count, tf);
	}

	public double getTfidf() {
		return tfidf;
	}

	public void setTfidf(double tfidf) {
		this.tfidf = tfidf;
	}

	public double getIdf() {
		return idf;
	}

	public void setIdf(double idf) {
		this.idf = idf;
	}
}