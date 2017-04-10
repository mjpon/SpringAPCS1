package textExcel;

public class FormulaCell extends RealCell{
	private String formanswer;
	// Constructor for the FormulaCell class
	public FormulaCell (String enteredForm){
		super(enteredForm);
		this.formanswer = enteredForm;
	}

	@Override
	public String abbreviatedCellText() {
		
		
		if(this.formanswer.indexOf("( SUM")>= 0){ //for SUM
			return"";
		} else if(this.formanswer.indexOf("(")>= 0){ //for adding regular numbers
//			SpreadsheetLocation location1 = new SpreadsheetLocation(inputcommand[0]);
//			int row = location1.getRow();
//			int col = location1.getCol();
			String removepart = this.formanswer.substring(2,this.formanswer.length()-2);
		 String[] fracEquationHolder = removepart.split(" ",removepart.length()); // holds the fractions and operator
	        String operators = "";
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