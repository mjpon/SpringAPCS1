
package textExcel;
 
// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private Cell[][] emptycell = new Cell[20][12];
	public Spreadsheet(){
		for( int i = 0; i < emptycell.length; i++){
			for (int j = 0; j<emptycell[i].length; j++){
				emptycell[i][j] = new EmptyCell();
			}
		}
	}
	@Override
	public String processCommand(String command)
	{	int row = 0;
		int col = 0;
		if(command.charAt(0) == 'c'){
			return getGridText();
			}
		String[] inputcommand = command.split(" ");
		if (inputcommand[1].equals("=")){
			String location = inputcommand[0];
			SpreadsheetLocation cellLocation = new SpreadsheetLocation(location);
			row = cellLocation.getRow();
			col = cellLocation.getCol();
		}
		if(inputcommand[2].equals("\"")){
				emptycell[row][col] = new TextCell(inputcommand[2]);
				return emptycell[row][col].abbreviatedCellText();
		}
		return getGridText();
	}

	@Override
	public int getRows()
	{
		int rowCount = 20;
		return rowCount;
	}

	@Override
	public int getCols()
	{
		int colCount = 12;
		
		return colCount;
	}

	@Override
	public Cell getCell(Location loc)
	{
		
		return null;
	}

	@Override
	public String getGridText()
	{
		String tableholder = "";
		tableholder = tableholder + "   |";
		char col = 'A';
		for(int i = 0; i < 12; i++){
			tableholder = tableholder + ((char)(col)) + "         |";
			col = (char) (col +  1);
		}
		for (int i = 0; i < 20; i++){
			tableholder = tableholder +  "\n";
			if(i<9){
				tableholder = tableholder +  (i+1) + "  |";
			}else{
				tableholder = tableholder +  (i+1) + " |"; // remember there is an extra character
			}
			for(int j = 0; j<12 ; j++){
				tableholder = tableholder + emptycell[i][j].abbreviatedCellText() + "|";
			//
			}
		}
		return tableholder + "\n";
	}
}