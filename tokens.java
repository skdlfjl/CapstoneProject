package capstone;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.Random;


public class tokens {
	
	//token 100000개 생성해서 csv로 저장하기~
	public static void main(String[] args) throws IOException {
		
		String creatfile = "C:\\Users\\user_\\Desktop\\tokens\\tokens.txt"; 
		FileWriter fw = new FileWriter(creatfile);
		
		String creatfile2 = "C:\\Users\\user_\\Desktop\\tokens\\search.txt"; 
		FileWriter fw2 = new FileWriter(creatfile2);
		//------------------------------------------------------
		String[] arr = new String[100000];  //1차원배열 방 n개를 만듭니다.
		   String numStr = "";    //랜덤값을 받을 변수를 만듭니다
        boolean cheak;    // 비교하기 위해 boolean형 변수를 만듭니다.
        for (int i=0; i<arr.length; i++) {    // 배열의 크기만큼 for문을 돌립니다.
            numStr = numberGen(12) ;
     	   cheak = true;    // i문이 돌 때마다 cheak를 true로 만듭니다.

            for (int j=0; j<i; j++) {
                if(arr[j].equals(numStr)) {
             	   // for문을 돌리면서  방금 받은 random값과 배열에 들어있는 값들을 비교하여 같은게 있으면
             	   i--;    // i의 값을 하나 줄여 한 번 더 돌게 합니다.
             	   cheak=false;    // 목적과는 다르게 같은 값이 나왔으므로 cheak를 false로 만듭니다.
                }
            }
            if(cheak) {    // 위의 if문의 조건을 만족하지 않았으면 자동으로 cheak는 true므로 실행이 됩니다.
         	   arr[i] = numStr; // numStr에 받은 값을 arr[i]방에 넣습니다.
                numStr = "";
            }
        }
        
        //-------------------------------------------------------
        
        //System.out.println(Arrays.toString(arr));
        
        //for(int i=0; i<arr.length; i++)    //출력하기 위한 for문
     	  // System.out.println(arr[i]);    // arr배열을 출력합니다.
        for (int i=0; i<arr.length; i++) {
        	if (i<50000) {
        		fw.append(arr[i]);
            	fw.append(',');
            	//fw.append('\n');        		
        	} else {
        		fw2.append(arr[i]);
            	fw2.append(',');
            	//fw2.append('\n');
        	}
        }
        fw.flush();
        fw.close();
        fw2.flush();
        fw2.close();
        //--------------------------------------------------------
 
	}
	
	public static String numberGen(int len) {
        
        Random rand = new Random();
        String numStr = ""; //난수가 저장될 변수
        
        for(int i=0; i<len-11; i++) {
        	char ch = (char) ((Math.random() * 26) + 97);
        	numStr += ch;
        }
        for(int i=0;i<len-5;i++) {
            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        for(int i=0; i<len-6; i++) {
        	char ch = (char) ((Math.random() * 26) + 97);
        	numStr += ch;
        }
        return numStr;
        
   }




}


