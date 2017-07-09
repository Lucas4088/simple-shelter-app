package my;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManaging {
	private Path file;
	private List<String> data;
	public FileManaging(){
		file  = Paths.get("Shelter data");
		data = new ArrayList<>();
	}
	
	public void writeToFile(){
		try {
			Files.write(file, data, Charset.forName("UTF-8"));
		} catch (IOException e) {
			new ErrorDialog("Unable to write to file");
		}
		
	}
	
	public void addToList(String a){
		data.add(a);
	}
	
	public List<String> readFile(){
		try {
			data = Files.readAllLines(file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			new ErrorDialog("Unable to read file");
		}
		
		return data;
	}
	
}
