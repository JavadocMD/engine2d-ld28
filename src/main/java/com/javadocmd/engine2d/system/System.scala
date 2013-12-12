package com.javadocmd.engine2d.system;

import com.javadocmd.engine2d.entity.Entity
import com.javadocmd.engine2d.EventEngine

/**
 * A system is any function that operates on entities with a certain set of
 * components. i.e., a Movement system operates on entities with Position and
 * Velocity.
 */
abstract class System {

	protected def isMatch(e: Entity): Boolean
	protected def process(delta: Float, eventEngine: EventEngine, e: Entity)

	def tryProcess(delta: Float, eventEngine: EventEngine, entities: Entity*): Unit = tryProcess(delta, eventEngine, entities.toList)
	def tryProcess(delta: Float, eventEngine: EventEngine, entities: List[Entity]): Unit = for (e <- entities if isMatch(e)) process(delta, eventEngine, e)
}
