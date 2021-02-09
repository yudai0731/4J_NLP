package nlp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TfIdf {

	ArrayList<TfIdfCount> list = new ArrayList<TfIdfCount>();
	TermFrequency[] tf;
	DocumentFrequency df;
	TfIdf(TermFrequency[] tf,DocumentFrequency df){
		this.tf = tf;
		this.df = df;
	}

	public void tfidf() {
		for(int i=1; i<=100;i++) {
			System.out.println("tf-idf calculate : "+String.format("%03d", i));
			for(int j=0;j<tf[i-1].list.size();j++) {
				for(int k=0; k<df.list.size();k++) {
					if(tf[i-1].list.get(j).getWord().equals(df.list.get(k).getWord())) {
						TfIdfCount tfidfcount = new TfIdfCount(tf[i-1].list.get(j).getWord(),tf[i-1].list.get(j).getCount(),tf[i-1].list.get(j).getTf());
						list.add(j, tfidfcount);
						tfidfcount.setWord(tf[i-1].list.get(j).getWord());
						tfidfcount.setTf(tf[i-1].list.get(j).getTf());
						tfidfcount.setIdf(df.list.get(k).getIdf());
						//System.out.println(df.list.get(j).getIdf());
						tfidfcount.setTfidf(tf[i-1].list.get(j).getTf() * df.list.get(k).getIdf());
					}
				}
			}
			list.sort(new TfIdfcompare());
		try {
			String outputFileName = "data-utf8\\" + String.format("%03d", i) + "tfidfchanged.txt";
			FileWriter fw = new FileWriter(outputFileName);
			for(int j=0;j<list.size();j++) {
			fw.write(list.get(j).getWord().getHyousoukei() + "\t"
					 + list.get(j).getWord().getHinshi() + "\t"
					 + list.get(j).getCount() + "\t"
					 + list.get(j).getTf() + "\t"
					 + list.get(j).getIdf() + "\t"
					 + String.format("%.10f", list.get(j).getTfidf()) + "\n");
			}
			fw.close();
			list.clear();
			} catch (IOException ex) {
				ex.printStackTrace();
			 }
		}
	}
}

