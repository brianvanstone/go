package tech.notpaper.go.test;

import java.util.LinkedList;
import java.util.List;

import tech.notpaper.go.controller.Controller;
import tech.notpaper.go.controller.impl.DefaultController;
import tech.notpaper.go.engine.impl.DefaultEngine;
import tech.notpaper.go.messaging.entities.GoResponse;
import tech.notpaper.go.messaging.entities.simple.Color;
import tech.notpaper.go.messaging.entities.simple.GoMove;
import tech.notpaper.go.engine.Engine;

public class TestHarness {
	
	public static void main(String[] args) {
		List<Controller> players = new LinkedList<>();
		
		Engine engine1 = new DefaultEngine(9, 5.5f, true);
		Controller playerBlack = new DefaultController(engine1);
		
		Engine engine2 = new DefaultEngine(9, 5.5f, false);
		Controller playerWhite = new DefaultController(engine2);
		
		players.add(playerBlack);
		players.add(playerWhite);
		
		for(Controller player : players) {
			player.boardsize(9);
			player.clearBoard();
		}
		
		int i = 0;
		while(i < 100) {
			//generate a move for black
			GoResponse response = playerBlack.genMove(Color.BLACK);
			String move = response.toString().trim();
			System.out.println(response);
			//System.out.println("Black prints board");
			//System.out.println(playerBlack.showBoard());
			
			//make that black move on the white side
			int index = move.indexOf(" ");
			move = response.toString().trim().substring(index >= 0 ? index : 0).trim();
			response = playerWhite.play(new GoMove(move));
			System.out.println(response);
			System.out.println("White prints board");
			System.out.println(playerWhite.showBoard());
			
			//generate a move for white
			response = playerWhite.genMove(Color.WHITE);
			System.out.println(response);
			move = response.toString();
			index = move.indexOf(" ");
			move = response.toString().trim().substring(index >= 0 ? index : 0).trim();
			//System.out.println("White prints board");
			//System.out.println(playerWhite.showBoard());
			
			//make that white move on the black side
			response = playerBlack.play(new GoMove(move));
			System.out.println(response);
			System.out.println("Black prints board");
			System.out.println(playerBlack.showBoard());
			
			i++;
		}
	}
}
