

public class TestingforExcel {
	
	static String empty[][]  = new String[12][20];
	public static void main(String args[]){
		String cookie = getGridText();
		System.out.println(cookie);
		
	}
	
	public static String getGridText()
	{String tableholder = "            ";
	
	
		for(int i= 0; i<12; i++){
			tableholder = tableholder + "\n";
			for(int j = 0; j<20; j++){
				tableholder = tableholder + empty[i][j]+"|";
		}
		}
		
		return tableholder;
}
}