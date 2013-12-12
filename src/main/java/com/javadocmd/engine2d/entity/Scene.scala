package com.javadocmd.engine2d.entity

import scala.collection.mutable.LinkedHashMap

import com.javadocmd.engine2d.component.Clickable

class Scene {
	private var entityId: Long = -1

	private var entities = new LinkedHashMap[Long, Entity]()
	private var clickables = new LinkedHashMap[Long, Clickable]()

	def add(entities: Entity*): Unit = synchronized { for (e <- entities) this.add(e) }

	def add(e: Entity): Unit = synchronized {
		if (e == null || entities.contains(e.id))
			return ;
		entityId += 1;
		e.id = entityId

		entities += (e.id -> e)
		if (e.isInstanceOf[Clickable])
			clickables += (e.id -> e.asInstanceOf[Clickable])
	}

	//synchronized
	def remove(e: Entity): Unit = synchronized {
		if (e == null)
			return
		entities -= e.id
		clickables -= e.id
	}

	def getEntities() = { entities.values.toList }
	def getClickables() = { clickables.values.toList }
}