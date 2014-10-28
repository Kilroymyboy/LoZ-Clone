package launcher;

public class NameEnterArray 
{
	private String[][] nameArr;
	private Menu menu;
	
	
	public NameEnterArray(Menu menu) {
		this.menu = menu;
	}



	public void enterChar(int x, int y)
	{
		menu.setStringToPrint(menu.getStringToPrint() + nameArr[x][y]);
	}

}
