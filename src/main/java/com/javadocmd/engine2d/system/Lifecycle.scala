package com.javadocmd.engine2d.system;

import com.javadocmd.engine2d.component.Lifespan
import com.javadocmd.engine2d.entity.Entity
import com.javadocmd.engine2d.event.Event
import com.javadocmd.engine2d.event.DieEvent
import com.javadocmd.engine2d.event.EventEngine

class Lifecycle extends System {

	override def isMatch(e: Entity) = e.isInstanceOf[Lifespan]
	
	override def process(delta: Float, eventEngine: EventEngine, e: Entity) = {
		val l = e.asInstanceOf[Lifespan]
		if (l.age(delta) <= 0) {
			e.dead = true
			eventEngine.enqueue(new DieEvent(e))
		}
	}
}
