package ETLTranslation;
import java.io.IOException;
import java.util.ArrayList;

import GraphStructure.GraphObject;
import LOGLAN.LOGLAN;
import ResponseModel.User;

@SuppressWarnings("unused")
public class ZZZTesting {
	
	public static void main(String[] args) throws IOException {
				
		/* */
		LOGLAN.fromFile("data/ETLTest/Dictionary.txt");
		LOGLAN.fromFile("data/ETLTest/MusicAbstract.txt");
		ETLRules rules = ETLRules.fromFile("data/ETLTest/ETLRules.txt");
		rules.readFile("data/ETLTest/MusicSimple.txt");
		/* */
		
		while(true) System.out.println(LOGLAN.parse(User.getString("Query: ")));
				
	}

}
