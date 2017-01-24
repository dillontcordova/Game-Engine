package com.game.src.main;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Physics {

	public static boolean Collision(GameActor mainActor, LinkedList<GameActor> actorList) {
		Rectangle mainActorBounds = mainActor.getBounds();
		Rectangle otherActorBounds;
		for (GameActor otherActor : actorList) {
			if (mainActor != otherActor) {
				otherActorBounds = otherActor.getBounds();
				if (mainActorBounds.intersects(otherActorBounds)) {
					otherActor.CollidedWithObject();
					mainActor.CollidedWithObject();
					return true;
				}
			}
		}
		return false;
	}
}
