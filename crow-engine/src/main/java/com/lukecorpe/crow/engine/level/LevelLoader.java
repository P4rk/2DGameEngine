package com.lukecorpe.crow.engine.level;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukecorpe.crow.engine.Game;

public class LevelLoader {
	public static void loadLevel(String filePath, Game game){
		ObjectMapper mapper = new ObjectMapper();
		try {
			String fileSrc = parseFile(filePath);
			Map levelMap = mapper.readValue(fileSrc, Map.class);
			createLevel(levelMap, game);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected static String parseFile(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String currentLine, totalString ="";
		while((currentLine = br.readLine())!=null){
			totalString += currentLine;
		}
		return totalString;
	}
	
	private static void createLevel(Map levelMap, Game game) {
		Level level = new Level(game);
		
		level.setWidth((int) levelMap.get("width"));
		level.setHeight((int) levelMap.get("height"));
		
		level.finalise();
		game.getLevels().add(level);
	}
}


