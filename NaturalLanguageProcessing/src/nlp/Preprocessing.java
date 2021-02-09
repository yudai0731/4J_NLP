package nlp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Preprocessing{

	ArrayList<String> list = new ArrayList<String>();
	private File inputFileName;
	private File outputFileName;
	Preprocessing(File inputFileName,File outputFileName){
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
	}

    public void preprocess() {
    	try{

    	      if (checkBeforeReadinputFileName(inputFileName)){
    	        BufferedReader br = new BufferedReader(new FileReader(inputFileName));

    	        String str;
    	        while((str = br.readLine()) != null){
    	          //System.out.println(str);
    	        	str = str.toLowerCase();
    	        	if (!str.contains("「http"))
      	          {
    	        		list.add(str);
      	          }

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

    	try {
			FileWriter fw = new FileWriter(outputFileName);
			for(int i=0;i<list.size();i++) {
			fw.write(list.get(i)+"\n");
			}
			fw.close();
			list.clear();
			} catch (IOException ex) {
				ex.printStackTrace();
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