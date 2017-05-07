package textExcel;

public class FormulaCell extends RealCell{
	private String formanswer;
	//private String[] fractionEquationHolder;
	private Spreadsheet sprdsheet;	//saves array from spreadsheet to access information from Spreadsheet
	
	// Constructor for the FormulaCell class
	public FormulaCell (String enteredForm, Spreadsheet emptycell){
		super(enteredForm);							//constructor to fill super's String field (accesses by fullCellText)
		this.formanswer = enteredForm;		
		sprdsheet = emptycell;
	}

	public double sum(String total) { // adds the cells in the when SUM is requested
		String[] cellParts = total.split("-");
		double sum = 0;
		int rowStart = Integer.parseInt(cellParts[0].substring(1));
		int rowEnd = Integer.parseInt(cellParts[1].substring(1));
		char colStart = cellParts[0].charAt(0);
		char colEnd = cellParts[1].charAt(0);
		for (char i = colStart; i < colEnd; i++) {
			for (int j = rowStart; j < rowEnd; j++) {
				SpreadsheetLocation gridLoc = new SpreadsheetLocation(i + j + "");
				sum += ((RealCell) sprdsheet.getCell(gridLoc)).getDoubleValue();
			}
		}
		return sum;
	}
	
	public double avg(String total) { // figures out the AVG of the lot
		String[] cellParts = total.split("-");
		int next = 0;
		double avgTotal = 0;
		int rowStart = Integer.parseInt(cellParts[0].substring(1));
		int rowEnd = Integer.parseInt(cellParts[1].substring(1));
		char colStart = cellParts[0].charAt(0);
		char colEnd = cellParts[1].charAt(0);
		for (char i = colStart; i < colEnd; i++) {
			for (int j = rowStart; j < rowEnd; j++) {
				SpreadsheetLocation gridLoc = new SpreadsheetLocation(i + j + "");
				avgTotal += ((RealCell) sprdsheet.getCell(gridLoc)).getDoubleValue();
				next++;
			}
		}
		return avgTotal/next;
	}

	@Override
public String abbreviatedCellText() {

		String removepart = this.formanswer.substring(2,this.formanswer.length()-2);
		 String[] fracEquationHolder = removepart.split(" ",removepart.length()); // holds the fractions and operator
		  double operand;
	      double operand2;
	      double answer;
		if (removepart.toUpperCase().indexOf("SUM")>=0){ //filters out SUM
//			String removepart1 = this.formanswer.substring(2,this.formanswer.length()-2);
//			return sum(removepart)+"";
			return sum(fracEquationHolder[1])+"";
		}else if (removepart.toUpperCase().indexOf("AVG")>=0) { //filters out AVG
//			String removepart1 = this.formanswer.substring(2,this.formanswer.length()-2);
//			return sum(removepart)+"";
			return avg(fracEquationHolder[1])+"";
		}else if (fracEquationHolder[0].substring(0,1).matches("[a-zA-Z]+")) { // um, using the famous Stackoverflow method
			// handles anything that has letter in one request
			SpreadsheetLocation gridLoc = new SpreadsheetLocation(fracEquationHolder[0]);
			answer = ((RealCell) sprdsheet.getCell(gridLoc)).getDoubleValue();
		}else{
			answer = Double.parseDouble(fracEquationHolder[0]);
		}
		for (int i = 2; i < fracEquationHolder.length; i+=2) {
			if (fracEquationHolder[i].length() < 4 && fracEquationHolder[i].substring(0,1).matches("[a-zA-Z]+")) {
				SpreadsheetLocation gridLoc = new SpreadsheetLocation(fracEquationHolder[i]);
				if (fracEquationHolder[i-1].equals("+")) {
					answer += ((RealCell) sprdsheet.getCell(gridLoc)).getDoubleValue();
				}else if(fracEquationHolder[i-1].equals("-")) {
					answer -= ((RealCell) sprdsheet.getCell(gridLoc)).getDoubleValue();
				}else if(fracEquationHolder[i-1].equals("/")) {
					answer /= ((RealCell) sprdsheet.getCell(gridLoc)).getDoubleValue();
				}else if(fracEquationHolder[i-1].equals("*")) {
					answer *= ((RealCell) sprdsheet.getCell(gridLoc)).getDoubleValue();
				}else{
					throw new IllegalArgumentException("Error, operator does not exist" );
				}
			}else{
				if (fracEquationHolder[i-1].equals("+")) {
					answer  += Double.parseDouble(fracEquationHolder[i]);
				}else if(fracEquationHolder[i-1].equals("-")) {
					answer  -= Double.parseDouble(fracEquationHolder[i]);
				}else if(fracEquationHolder[i-1].equals("/")) {
					answer  /= Double.parseDouble(fracEquationHolder[i]);
				}else if (fracEquationHolder[i-1].equals("*")) {
					answer  *= Double.parseDouble(fracEquationHolder[i]);
				}else {
					throw new IllegalArgumentException("ERro, operator does not exist");
				}
			}
		}// for the needed spaces
		String answer1 = answer  +"";
        int cellValueLength = answer1.length();
		if (cellValueLength < 10){
			while (answer1.length() < 10){
				answer1 += " ";
			}
			return answer1;
		}else if (cellValueLength > 10){
			answer1 = answer1.substring(0, 10);
			return answer1;
		}else{
			return answer1;
		}
	}
	@Override
	public String fullCellText() {
		return super.fullCellText();
	}
	@Override
	public double getDoubleValue() {
		return Double.parseDouble(this.formanswer.substring(0, this.formanswer.length()-1))/100;

	}
	
	}
	