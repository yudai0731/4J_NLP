package nlp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TermFrequency {

	ArrayList<TfCount> list = new ArrayList<TfCount>();

	public void tf(String inputFilename, String outputFilename,Stopword stopwordlist){

		//String inputFilename = "data-utf8\\001.txt";
		String[] command = {"cmd.exe", "/C", "mecab", inputFilename };

		 try {
			 Process ps = Runtime.getRuntime().exec(command);
			 BufferedReader bReader_i = new BufferedReader(new InputStreamReader(ps.getInputStream(), "UTF-8"));
			 String targetLine;

			 while (true) {
				 targetLine = bReader_i.readLine();

				 if (targetLine == null) {
					 break;
				 }
				 else if (targetLine.equals("EOS")) {
					 continue;
				 }
				 else {
					 Word wo = new Word();
					 String targetArray[] = targetLine.split("[\t|,]");

					 if (targetArray.length >= 1)
						 wo.setHyousoukei(targetArray[0]);
					 if (targetArray.length >= 2)
						 wo.setHinshi(targetArray[1]);
					 if (targetArray.length >= 3)
						 wo.setHinshi1(targetArray[2]);
					 if (targetArray.length >= 4)
						 wo.setHinshi2(targetArray[3]);
					 if (targetArray.length >= 5)
						 wo.setHinshi3(targetArray[4]);
					 if (targetArray.length >= 6)
						 wo.setKatsuyoKata(targetArray[5]);
					 if (targetArray.length >= 7)
						 wo.setKatsuyoKei(targetArray[6]);
					 if (targetArray.length >= 8)
						 wo.setGenkei(targetArray[7]);
					 if (targetArray.length >= 9)
						 wo.setYomi(targetArray[8]);
					 if (targetArray.length >= 10)
						 wo.setHatsuon(targetArray[9]);

					//リストの中に同じ語のエントリがあるか調べて，既にエントリがあれば，カウントアップする
					 int i;
					 for (i = 0; i < list.size(); i++) {
						 if (list.get(i).getWord().equals(wo))     {
							 list.get(i).setCount(list.get(i).getCount() +1);
							 break;
						 }
					 }
					 //リストにエントリが無かったときは，新しい語としてリストに追加する
					 if (i == list.size()) {
						 list.add(new TfCount(wo, Integer.valueOf(1)));
					 }

					 //System.out.println(targetLine);
				 }
			 }

		 }
		 catch (IOException e) {
			 e.printStackTrace();
		 }

		 // 指定した品詞の語のみを抽出する

		 for (int i = 0; i < list.size(); i++){
			 if(list.get(i).getWord().getHinshi().equals("記号")) {
				 //System.out.println(list.get(i).getWord().getHyousoukei() + "\t");
				 list.remove(i);
				 i--;
			 }else if(list.get(i).getWord().getHinshi().equals("助動詞")) {
				 list.remove(i);
				 i--;
			 }else if(list.get(i).getWord().getHinshi().equals("動詞")) {
				 list.remove(i);
				 i--;
			 }else if(list.get(i).getWord().getHinshi().equals("接続詞")) {
				 list.remove(i);
				 i--;
			 }else if(list.get(i).getWord().getHinshi().equals("助詞")){
				 //System.out.println(list.get(i).getWord().getHyousoukei() + "\t");
				 list.remove(i);
				 i--;
			 }else if(list.get(i).getWord().getHinshi().equals("連体詞")){
				 list.remove(i);
				 i--;
			 }else if(list.get(i).getWord().getHinshi1().equals("数")){
				 list.remove(i);
				 i--;
			 }else if(list.get(i).getWord().getHinshi().length() == 0){
				 list.remove(i);
				 i--;
			 }
		 }


		 //stopwordの除外
		 for (int i = 0; i < list.size(); i++){
			 for(int j = 0; j<stopwordlist.stopword.size();j++) {
				 if(list.get(i).getWord().getHyousoukei().equals(stopwordlist.stopword.get(j))) {
					 list.remove(i);
					 i--;
					 break;
				 }
			 }
		 }

		//今のリストのエントリをすべて表示する
		 int sum = 0;
		 for (int i = 0; i < list.size(); i++){
			 //System.out.println(list.get(i).getWord().getHyousoukei()+ ":" + list.get(i).getCount());
			 sum += list.get(i).getCount();
		 }

		 //System.out.println(outputFilename+"has : "+ list.size() + " data");
		 //System.out.print(list.size() + ",");
		 list.sort(new WordCompare());

		 try {
			 //String outputFilename = "data-utf8\\001tf.txt";
			 FileWriter fw = new FileWriter(outputFilename);
			 //System.out.println(outputFilename);
			 for (int i = 0; i < list.size(); i++){
				 //System.out.println(fileal.get(i).word.getHyousoukei() + ":"+ fileal.get(i).getCount());
				 list.get(i).setTf((double) list.get(i).getCount() / (double) sum);

				 fw.write(list.get(i).getWord().getHyousoukei() + "\t"
						 + list.get(i).getWord().getHinshi() + "\t"
						 + list.get(i).getCount() + "\t"
						 + String.format("%.10f", list.get(i).getTf()) + "\n");
			 }
			 fw.close();
		 }
		 catch (IOException ex) {
			ex.printStackTrace();
		 }

	}
}
