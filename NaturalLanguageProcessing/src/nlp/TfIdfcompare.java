package nlp;

import java.util.Comparator;

public class TfIdfcompare implements Comparator<TfIdfCount>{
	@Override
	 public int compare(TfIdfCount wc1, TfIdfCount wc2)
	 {
	 if(wc1.getTfidf() < wc2.getTfidf())
	 return 1;
	 if(wc1.getTfidf() == wc2.getTfidf())
	 return 0;
	 if(wc1.getTfidf() > wc2.getTfidf())
	 return -1;
	 return 0;
	 }
}
