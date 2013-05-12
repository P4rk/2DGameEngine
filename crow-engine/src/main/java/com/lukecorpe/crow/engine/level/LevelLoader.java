package com.lukecorpe.crow.engine.level;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.SlickException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukecorpe.crow.engine.Game;
import com.lukecorpe.crow.engine.objects.GameObject;

public class LevelLoader {
	public static void loadLevel(String filePath, Game game){
		ObjectMapper mapper = new ObjectMapper();
		try {
			String fileSrc = parseFile(filePath);
			Map<String, Object> levelMap = mapper.readValue(fileSrc, Map.class);
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
	
	private static void createLevel(Map<String, Object> levelMap, Game game) {
		Level level = new Level(game);
		
		level.setWidth((int) levelMap.get("width"));
		level.setHeight((int) levelMap.get("height"));
		
		createObjects((Map)levelMap.get("levelObjects"), level);
		
		level.finalise();
		game.getLevels().add(level);
	}

	private static void createObjects(Map<String, Object> objectsMap, Level level) {
		for(String s : objectsMap.keySet()){
			Map<String, Object> objectMap = (Map<String, Object>)objectsMap.get(s);
			try {
				GameObject obj = new GameObject(
						level, 
						BodyType.valueOf((String)objectMap.get("bodyType")), 
						new Vec2(
								Float.valueOf((String)objectMap.get("startPositionX")), 
								Float.valueOf((String)objectMap.get("startPositionY"))
							), 
						(String)objectMap.get("relitaveImagePath")
						);
				obj.setDensity(Float.valueOf((String)objectMap.get("density")));
				obj.setFriction(Float.valueOf((String)objectMap.get("friction")));
			} catch (SlickException e) {
				throw new RuntimeException(e);
			}
		}
	}
}


