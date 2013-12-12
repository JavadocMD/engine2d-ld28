package com.javadocmd.engine2d.event
import com.javadocmd.engine2d.util.Vector2f
import com.javadocmd.engine2d.entity.Entity
import scala.compat.Platform

/**
 * An event for the event processing system. Events may optionally refer
 * to an entity and/or a location, but they always have a time of creation.
 */
abstract class Event {
	val entity: Entity = null
	val location: Vector2f = null
	val time: Long = Platform.currentTime
}

/**
 * A handler of events which knows how to process a single type of event. 
 * You should probably implement these as singletons.
 */
abstract class Handler[-E <: Event] {
	def handleGeneric(event: Event) = handle(event.asInstanceOf[E])
	def handle(event: E)
}
object Handler {
	def apply[E <: Event](h: (E)=>Unit): Handler[E] = {
		return new Handler[E] { def handle(event: E) = h(event) }
	}
}

/* Below are some generic event implementations. */

/** An event that marks an entity for destruction. */
class DieEvent(entity: Entity) extends Event

/** An event that indicates the system should shut down. */
class ShutdownEvent extends Event