package my;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CSVManaging {
	private static final char DEFAULT_OPERATOR = ',';
	private static final char DEFAULT_QUOTE = '\'';
	
	private static String followCSVFormat(String value){
		String result = value;
		if(result.contains("\"")){
			result = result.replace("\"","\"\"");
		}
		
		return result;
	}
	public static void writeLine(Writer w, List<Animal> animals) throws IOException{
		
		StringBuilder sb = new StringBuilder();
		for(Animal a : animals){
			sb.append(DEFAULT_QUOTE).append(a.getID()).append(DEFAULT_QUOTE).append(DEFAULT_OPERATOR);
			sb.append(DEFAULT_QUOTE).append(a.getName().split(" : ",2)[0]).append(DEFAULT_QUOTE).append(DEFAULT_OPERATOR);
			sb.append(DEFAULT_QUOTE).append(a.getName().split(" : ",2)[1]).append(DEFAULT_QUOTE);
			sb.append("\n");
		}
		w.append(sb.toString());
	}
}
