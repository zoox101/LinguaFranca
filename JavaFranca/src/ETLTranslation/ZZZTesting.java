package ETLTranslation;
import java.io.IOException;
import java.util.ArrayList;
import LOGLAN.ParseMain;

@SuppressWarnings("unused")
public class ZZZTesting {
	
	public static void main(String[] args) throws IOException {
		
		ParseMain.fromFile("data/MusicAbstract.txt");
		ETLRules rules = ETLRules.fromFile("data/ETLTest/ETLRules.txt");
		rules.executeFile("data/ETLTest/MusicSimple.txt");
				
	}

}
