package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ToolMain {
	
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		PrintWriter out=new PrintWriter("out.json");
		JSONParser parser=new JSONParser();
		JSONObject glue=(JSONObject)parser.parse(new FileReader("tileset.json"));
		JSONObject map=(JSONObject)parser.parse(new FileReader("map.json"));
		glue.forEach((key,value)->{
			map.put(key, value);
		});
		map.put("spawnX", 1);
		map.put("spawnY",1);
		
		out.println(map);
		out.flush();
		out.close();
	}

}
