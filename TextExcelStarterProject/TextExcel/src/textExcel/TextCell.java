package textExcel;

public class TextCell implements Cell {
	
	private String cellText;
	
	public TextCell (String text){
		this.cellText=text.substring(1, text.length()-1); 
	}
	@Override
	public String abbreviatedCellText() {
		String returnStr = cellText;
		int cellValueLength = cellText.length();
		if (cellValueLength < 10){
			while (returnStr.length() < 10){
				returnStr += " ";
			}
			return returnStr;
		} else if (cellValueLength > 10){
			returnStr = returnStr.substring(0, 10);
			return returnStr;
		}else{
			return cellText;
		}
	}

	@Override
	public String fullCellText() {
		return "\"" + cellText + "\"";
	}
}