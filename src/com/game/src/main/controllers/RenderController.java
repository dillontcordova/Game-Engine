package com.game.src.main.controllers;
import com.game.src.main.views.GameView;
import java.awt.*;
import java.util.LinkedList;

public class RenderController {

	static private LinkedList<GameView> views = new LinkedList<>();

	public void render(Graphics graphic) {
		for (int i = views.size() - 1; i >= 0; i--) {
			GameView view = views.get(i);
			if(!view.isToBeRemoved()) {
				view.render(graphic);
			} else {
				removeView(views.get(i));
			}
		}
	}

	public static void addActorView(GameView newView) {
		views.add(newView);
	}

	public void removeView(GameView view) {
		synchronized (GameView.class) {
			views.remove(view);
		}
	}
}