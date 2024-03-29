package textExcel;

public class FormulaCell extends RealCell{
	private String formanswer;
	//private String[] fractionEquationHolder;
	private Spreadsheet sprdsheet;	//saves array from spreadsheet to access information from Spreadsheet
	private Cell[][] breadsheet; // previously used, but did not work

	// Constructor for the FormulaCell class
	public FormulaCell (String enteredForm, Spreadsheet emptycell, Cell[][] breadsheet){
		super(enteredForm);        //constructor to fill super's String field (accesses by fullCellText)
		formanswer = enteredForm;		
		sprdsheet = emptycell;
		this.breadsheet = breadsheet;
	}

	public double sum(String total) { // adds the cells in the when SUM is requested
		String[] cellParts = total.split("-");
		double sum = 0;
		int rowBeg = Integer.parseInt(cellParts[0].substring(1));
		int rowEnd = Integer.parseInt(cellParts[1].substring(1)); //numbers
		char colBeg = (cellParts[0].toUpperCase()).charAt(0); //letters
		char colEnd = (cellParts[1].toUpperCase()).charAt(0);
		for (char i = colBeg; i <= colEnd; i++) {
			for (int j = rowBeg; j <= rowEnd; j++) {
				SpreadsheetLocation gridLoc = new SpreadsheetLocation(i+""+j);
				sum += ((RealCell) sprdsheet.getCell(gridLoc)).getDoubleValue();
				//sum += ((RealCell) breadsheet[i][j]).getDoubleValue();
		}		
	} 
		return sum;
	}
	public double avg(String total) { // figures out the average of the lot
		String[] cellParts = total.split("-");
		double avgTotal = 0;
		double next = 0;
		int rowBeg = Integer.parseInt(cellParts[0].substring(1)); //numbers
		int rowEnd = Integer.parseInt(cellParts[1].substring(1));
		char colBeg = (cellParts[0].toUpperCase()).charAt(0); //letters
		char colEnd = (cellParts[1].toUpperCase()).charAt(0);
		for (char i = colBeg; i <= colEnd; i++) {
			for (int j = rowBeg; j <= rowEnd; j++) {
				SpreadsheetLocation gridLoc = new SpreadsheetLocation(i+""+j);
				avgTotal += ((RealCell) sprdsheet.getCell(gridLoc)).getDoubleValue();
				next++;//next increments the amount of cells in the range
			}
		}
		return avgTotal/next; 
	}

	@Override
	public double getDoubleValue() {
		String removepart = formanswer.substring(2,formanswer.length()-2);
		 String[] fracEquationHolder = removepart.split(" ",removepart.length()); // holds the fractions and operator
		  double operand;
	      double operand2;
	      double answer;
		if (fracEquationHolder[0].toUpperCase().indexOf("SUM")>=0){ //filters out SUM
			return sum(fracEquationHolder[1]);
		}else if (fracEquationHolder[0].toUpperCase().indexOf("AVG")>=0) { //filters out AVG
			return avg(fracEquationHolder[1]);
		}else{ //anything that doesn't have SUM or AVG
			for (int j = 0; j < fracEquationHolder.length; j+=2) {
				if (fracEquationHolder[j].substring(0,1).matches("[a-zA-Z]+")) {
					fracEquationHolder[j] = getCellValue(fracEquationHolder[j]) + "";
				}
			} // this where the math starts
			answer = Double.parseDouble(fracEquationHolder[0]);
			for (int i = 1; i < fracEquationHolder.length; i+=2) {
				if (fracEquationHolder[i].equals("+")) {
					answer += Double.parseDouble(fracEquationHolder[i+1]);
				}else if(fracEquationHolder[i].equals("-")) {
					answer -= Double.parseDouble(fracEquationHolder[i+1]);
				}else if(fracEquationHolder[i].equals("/")) {
					answer /= Double.parseDouble(fracEquationHolder[i+1]);
				}else if (fracEquationHolder[i].equals("*")) {
					answer *= Double.parseDouble(fracEquationHolder[i+1]);
				}else {
					throw new IllegalArgumentException("ERro, operator does not exist");
				}
			}
		}
		return answer;
	}
	public double getCellValue(String cellPlace) { //finds the location and gets the double value out of that location
		SpreadsheetLocation gridLoc = new SpreadsheetLocation(cellPlace);
		double cellvalue =  ((RealCell) sprdsheet.getCell(gridLoc)).getDoubleValue();
		return cellvalue;
	}
	@Override
	public String abbreviatedCellText() { // for the needed spaces
		String answer1 = getDoubleValue() +"";
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
	public String fullCellText() { // returns the full cell only and nothing else
		return super.fullCellText();
	}
}
	