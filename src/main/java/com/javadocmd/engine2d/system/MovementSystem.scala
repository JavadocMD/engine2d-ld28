package com.javadocmd.engine2d.system

import com.javadocmd.engine2d.component.Movement
import com.javadocmd.engine2d.component.Position
import com.javadocmd.engine2d.entity.Entity
import com.javadocmd.engine2d.event.EventEngine

class MovementSystem extends System {

	def isMatch(e: Entity): Boolean = { e.isInstanceOf[Position with Movement] }
	
	def process(delta: Float, eventEngine: EventEngine, e: Entity): Unit = {
		val p = e.asInstanceOf[Position with Movement]
		p.position = p.velocity * delta + p.position
	}
}