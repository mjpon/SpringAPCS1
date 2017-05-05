package textExcel;

public class FormulaCell extends RealCell{
	private String formanswer;
	private String[] fractionEquationHolder;
	private Cell[][] sprdsheet;	//saves array from spreadsheet to access information
	// Constructor for the FormulaCell class
	public FormulaCell (String enteredForm, Cell[][] emptycell){
		super(enteredForm);							//constructor to fill super's String field (accesses by fullCellText)
		this.formanswer = enteredForm;		
		sprdsheet = emptycell;
	}
	@Override
public String abbreviatedCellText() {
		
		String removepart = this.formanswer.substring(2,this.formanswer.length()-2);
		 String[] fracEquationHolder = removepart.split(" ",removepart.length()); // holds the fractions and operator
	        String operators = "";
		if(this.formanswer.length() == 0){
			return"";
		} else if(this.formanswer.indexOf("( SUM")>= 0){
			return"";
		} else if(this.formanswer.indexOf("( AVG")>= 0){
			int next = 0;
			String firstNum = fracEquationHolder[1].toUpperCase().substring(0, fracEquationHolder[1].indexOf('-'));
			String endingNum = fracEquationHolder[1].toUpperCase().substring(fracEquationHolder[1].indexOf('-') + 1);
			String placeHolder = firstNum;
			while(!(placeHolder.equals(endingNum))){
				SpreadsheetLocation loc = new SpreadsheetLocation(placeHolder);
				//change the column when its the same row
				if(placeHolder.substring(1).equals(endingNum.substring(1))){
					//changes the letter by one if the rows are the same
					placeHolder = ((char)(placeHolder.charAt(0) + 1)) + endingNum.substring(1);
					if(sprdsheet[loc.getRow()][loc.getCol()] instanceof RealCell){//checks to see if realcell object/ cell exists, aka is an number
						next++;
					}
				}else{
					//Move to next row if the row isnt equal
					placeHolder = placeHolder.charAt(0) + "" + (Integer.parseInt(placeHolder.substring(1)) + 1);
					if(sprdsheet[loc.getRow()][loc.getCol()] instanceof RealCell){
						next++;
					}
				}
			}
			return placeHolder;
		}else if (this.formanswer.indexOf("(")>=0){
			
	        double operand;
	        double operand2;
	        double answer = Double.parseDouble(fracEquationHolder[0]);

	        for(int i =0; i <= (fracEquationHolder.length-2); i+=2){
	            operators = fracEquationHolder[i+1];
	            operand = Double.parseDouble(fracEquationHolder[i]);
	            operand2 = Double.parseDouble(fracEquationHolder[i+2]);
	            if(operators.indexOf("+")>=0){
	                answer += operand2;
	            }else if(operators.indexOf("-")>=0){
	                answer -= operand2;
	            }else if(operators.indexOf("*")>=0){
	                answer *=  operand2;
	            }else if(operators.indexOf("/")>=0){
	                answer /=   operand2;
	            }else
	                return(answer+"      "); 
	        }
	        String answer1 = answer +"";
	        int cellValueLength = answer1.length();
			if (cellValueLength < 10){
				while (answer1.length() < 10){
					answer1 += " ";
				}
				return answer1;
			} else if (cellValueLength > 10){
				answer1 = answer1.substring(0, 10);
				return answer1;
			}else{
				return answer1;
			}
			
		}// OUT OF THE MAIN IF
		return"";
	}

	@Override
	public String fullCellText() {
		return super.fullCellText();
	}
	public double getDoubleValue (){ // truncates it
		return Double.parseDouble(this.formanswer.substring(0, this.formanswer.length()-1))/100;
		
	}
	
}