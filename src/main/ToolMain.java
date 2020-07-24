package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ToolMain {
	
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser=new JSONParser();
		JSONObject map=(JSONObject)parser.parse(new FileReader("out.json"));
		int width=((Long) map.get("width")).intValue();
		int height=((Long) map.get("height")).intValue();
		JSONArray objLayer=new JSONArray();
		for(int j=0;j<height;j++)
		{
			for(int i=0;i<width;i++) {
				if(i==2&&j==1)
					objLayer.add(16);
				else
					objLayer.add(-1);
			}
		}
		map.put("objLayer",objLayer);
		PrintWriter out=new PrintWriter("out.json");
		out.println(map);
		out.flush();
		out.close();
	}

}
