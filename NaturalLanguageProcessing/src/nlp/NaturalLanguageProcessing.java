package nlp;

import java.io.File;

public class NaturalLanguageProcessing
{
 static public void main(String args[])
 {

 System.out.println("Preprocessing");
 System.out.println("deleteURL");
 for(int i=1; i<=100;i++) {
	 File inputFileName = new File("data-utf8\\" + String.format("%03d", i) + ".txt");
	 File outputFileName = new File("data-utf8\\" + String.format("%03d", i) + "changed.txt");
	 System.out.println("data-utf8\\" + String.format("%03d", i) + "changed.txt");
	 Preprocessing pre = new Preprocessing(inputFileName,outputFileName);
	 pre.preprocess();
 }
 System.out.println("readstopword");
 File stopwordName = new File("data-utf8\\stopword.txt");
 Stopword stopwordlist = new Stopword();
 stopwordlist.readStopword(stopwordName);

 System.out.println("TF calculate");
 TermFrequency[] tf= new TermFrequency[100];
 for(int i=1; i <= 100;i++){
	 tf[i-1] = new TermFrequency();
	 String inputFileName = "data-utf8\\" + String.format("%03d", i) + "changed.txt";
	 String outputFileName = "data-utf8\\" + String.format("%03d", i) + "tfchanged.txt";
	 //System.out.println(inputFileName);
	 System.out.println(outputFileName);
	 tf[i-1].tf(inputFileName,outputFileName,stopwordlist);
	 //tf[i-1].tf(inputFileName,outputFileName);
 }


 System.out.println("DF calculate");
 DocumentFrequency df = new DocumentFrequency(tf);
 String outputFilename = "data-utf8\\dfchanged.txt";
 df.df(outputFilename);

 System.out.println("Tf-Idf calculate");
 TfIdf tfidf = new TfIdf(tf,df);
 tfidf.tfidf();
 System.out.println("complete");
 }
}
