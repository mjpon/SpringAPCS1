package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
	private String name;
    @Override
    public int getRow()
    { // FIXME fix variables
    	int j = 0;
    	if(name.charAt(0)>=0){
    		String i = name.toLowerCase();
    		char row = i.charAt(0);
    		j  = (char)row;  		
    	}
        return j;
    }

    @Override
    public int getCol()
    { // FIXME fix variables
    	
    	int placeholder = 0;
    	char placeholder2 = '';
    	if(name.charAt(1)>=0){
    		placeholder = name.charAt(1);
    		placeholder2= name.charAt(2);
    	}
        return placeholder;

    }
    
    public SpreadsheetLocation(String cellName)
    {	
     this.name = cellName;
//    	Location loc = new SpreadsheetLocation(cellName);
//    	loc.getCol();
//    	loc.getRow();
    }

}