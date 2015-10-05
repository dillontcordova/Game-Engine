package com.game.src.main;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Physics {

	public static boolean Collision(GameObject mainActor, LinkedList<GameObject> actorList) {
		Rectangle mainActorBounds = mainActor.getBounds();
		Rectangle otherActorBounds;
		for(int i = 0; i < actorList.size(); i++) {
			GameObject otherActor = actorList.get(i);
			if(mainActor != otherActor) {
				otherActorBounds = otherActor.getBounds();
				if( mainActorBounds.intersects(otherActorBounds)) {
					actorList.get(i).CollidedWithObject();
					mainActor.CollidedWithObject();
					return true;
				}
			}
		}
		return false;
	}
}
