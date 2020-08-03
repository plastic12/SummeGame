package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ToolMain {
	
	public static void main(String[] args) throws Exception {
		modify();
	}
	public static void test() throws URISyntaxException {
		
		ClassLoader loader = ToolMain.class.getClassLoader();
		System.out.println(loader.getResource("data/out.json"));
		//File file=new File(loader.getResource("data/out.json").toURI());
		/*
		Path path=Paths.get("data/out.json");
		System.out.format("toString: %s%n", path.toString());
		System.out.format("getFileName: %s%n", path.getFileName());
		System.out.format("getName(0): %s%n", path.getName(0));
		System.out.format("getNameCount: %d%n", path.getNameCount());
		System.out.format("subpath(0,2): %s%n", path.subpath(0,2));
		System.out.format("getParent: %s%n", path.getParent());
		System.out.format("getRoot: %s%n", path.getRoot());
		System.out.format("%s%n", path.toUri());
		*/
		
	}
	public static void moduleChecker() {
		String moduleName=ModuleFinder.of(Paths.get("json-simple-1.1.1.jar"))
				.findAll().stream().findFirst()
				.map(ModuleReference::descriptor)
				.map(ModuleDescriptor::name)
				.orElse(null);
		System.out.println(moduleName);
	}
	
	public static void modify() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser=new JSONParser();
		JSONObject map=(JSONObject)parser.parse(new FileReader("out.json"));
		
		int width=((Long) map.get("width")).intValue();
		int height=((Long) map.get("height")).intValue();
		JSONArray objLayer=new JSONArray();
		for(int j=0;j<height;j++)
		{
			for(int i=0;i<width;i++) {
				if(i==2&&j==1)
					objLayer.add(25);
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
