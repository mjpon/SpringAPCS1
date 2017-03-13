package textExcel;

public class TextCell implements Cell {
	
	private String cellText;
	
	public TextCell (String text){
		cellText = text.substring(1, text.length()-1); 
	}
	@Override
	public String abbreviatedCellText() {
		String characterCount = "";
		if (cellText.length() < 10){
			while (cellText.length() + characterCount.length() != 10){
				characterCount = characterCount + " ";
			} 
		}else if (cellText.length() > 10){
			characterCount = cellText.substring(0, 11); //remember that length is always "one before and one away"
			return characterCount;
		}
		return cellText; // if its 10 characters
	}

	@Override
	public String fullCellText() {
		return cellText;
	}
}