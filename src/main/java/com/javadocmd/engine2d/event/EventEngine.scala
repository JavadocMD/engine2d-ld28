package com.javadocmd.engine2d
import scala.collection.mutable.Queue
import com.javadocmd.engine2d.event.Event
import com.javadocmd.engine2d.event.Handler
import scala.collection.mutable.HashMap

class EventEngine {
	
	private var events = new Queue[Event]
	private val handlers = new HashMap[Manifest[_], Handler[_ <: Event]]
	
	def enqueue(events: Event*) = { EventEngine.this.events ++= events }
	
	/** Register a handler to handle a specific event. */
	def register[E <: Event](handler: Handler[E])(implicit m: Manifest[E]) = {
		assert(handler != null)
		if (handlers.contains(m))
			throw new Exception("A handler has already been registered for that event.")
		handlers += (m -> handler)
	}
	
	/** Register a single handler to handle many subclasses of a specific event. */
	def register[E <: Event](handler: Handler[E], handledEvents: Manifest[_ <: E]*) = {
		assert(handler != null)
		for (m <- handledEvents) yield {
			if (handlers.contains(m))
				throw new Exception("A handler has already been registered for that event.")
			handlers += (m -> handler)
		}
	}
	
	def processEvents(): Unit = {
		if (events.length == 0)
			return
		
		val q = events // We will iterate through every event currently in the queue
		events = new Queue[Event] // But any new events should go in a fresh queue to be processed next round
		
		q.foreach { e =>
			val m = Manifest.classType(e.getClass())
			handlers.get(m).foreach { _.handleGeneric(e) }
		}
	}
}