package nlp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DocumentFrequency
{
 //DF をカウントするためのデータ格納領域の定義
 ArrayList<DfCount> list = new ArrayList<DfCount>();
 //DF の元になる TF を受ける
 TermFrequency tf[];
 DocumentFrequency(TermFrequency[] tf)
 {
 this.tf = tf;
 }
 public void df(String outputFilename)
 {
 //（ここに処理を定義する）
 //TF100 ファイル分について繰り返す
 for(int i=1;i<=100;i++) {
	 System.out.println("df calculate : "+String.format("%03d", i));
	//TF1 件に含まれる語の分だけ繰り返し
	 for(int j=0;j<tf[i-1].list.size();j++) {
		 //DF のリストの中にエントリがあるか調べる
		//System.out.rintln(tf[i-1].list.get(j).getWord().getHyousoukei());
		 int k;
		 for(k=0;k<list.size();k++) {
		 if(list.get(k).getWord().equals(tf[i-1].list.get(j).getWord())) {
			 list.get(k).setCount(list.get(k).getCount() +1);
			 break;
		 }
		 }
		 //リストにエントリが無かったときは，新しい語としてリストに追加する
		 if (k == list.size()) {
			 list.add(new DfCount(tf[i-1].list.get(j).getWord(), Integer.valueOf(1)));
		 }
	 }
	 }
 //ソートする
 list.sort(new WordCompare());

 //今のリストのエントリをすべて表示する
 for (int i = 0; i < list.size(); i++){
	 //System.out.println(list.get(i).getWord().getHyousoukei()+ ":" + list.get(i).getCount());
	 //if (list.get(i).getCount()>=90) {
	 //System.out.println(list.get(i).getWord().getHyousoukei());
	 //}
 }

 //df,idf の結果をファイルに保存する
 try {
	 FileWriter fw = new FileWriter(outputFilename);
	 //System.out.println(outputFilename);
	 for (int i = 0; i < list.size(); i++){
		 //System.out.println(fileal.get(i).word.getHyousoukei() + ":"+ fileal.get(i).getCount());
		 list.get(i).setIdf(Math.log10((double) 100 / (double) list.get(i).getCount())+1);

		 fw.write(list.get(i).getWord().getHyousoukei() + "\t"
				 + list.get(i).getWord().getHinshi() + "\t"
				 + list.get(i).getCount() + "\t"
				 + String.format("%.10f", list.get(i).getIdf()) + "\n");
	 }
	 fw.close();
 }
 catch (IOException ex) {
	ex.printStackTrace();
 }
 }
}
