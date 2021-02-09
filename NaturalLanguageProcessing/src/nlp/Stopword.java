package nlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Stopword {
	ArrayList<String> stopword = new ArrayList<String>();
    public void readStopword(File inputFileName) {
    	try{

  	      if (checkBeforeReadinputFileName(inputFileName)){
  	        BufferedReader br = new BufferedReader(new FileReader(inputFileName));

  	        String str;
  	        while((str = br.readLine()) != null){
  	          //System.out.println(str);
  	        	str = str.toLowerCase();
  	        		stopword.add(str);
  	        }

  	        br.close();
  	      }else{
  	        System.out.println("file open error");
  	      }
  	    }catch(FileNotFoundException e){
  	      System.out.println(e);
  	    }catch(IOException e){
  	      System.out.println(e);
  	    }
    }
    private static boolean checkBeforeReadinputFileName(File inputFileName){
        if (inputFileName.exists()){
          if (inputFileName.isFile() && inputFileName.canRead()){
            return true;
          }
        }

        return false;
      }
}
