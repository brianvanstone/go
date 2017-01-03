package tech.notpaper.go.test;

import tech.notpaper.go.controller.Controller;
import tech.notpaper.go.controller.impl.DefaultController;
import tech.notpaper.go.engine.impl.DefaultEngine;
import tech.notpaper.go.messaging.entities.simple.Color;
import tech.notpaper.go.engine.Engine;

public class TestHarness {
	
	public static void main(String[] args) {
		Engine engine = new DefaultEngine(9, 5.5f, true);
		Controller controller = new DefaultController(engine);
		
		System.out.print(controller.boardsize(9));
		System.out.print(controller.clearBoard());
		
		System.out.print(controller.name());
		System.out.print(controller.protocolVersion());
		System.out.print(controller.version());
		System.out.print(controller.listCommands());
		System.out.print(controller.showBoard());
		System.out.print(controller.genMove(Color.BLACK));
		System.out.print(controller.showBoard());
	}
}
