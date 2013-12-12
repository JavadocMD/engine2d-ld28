package com.javadocmd.engine2d.component

import com.javadocmd.engine2d.event.Event;
import com.javadocmd.engine2d.util.Vector2f

trait Clickable extends Component with Position with Volume {
	def isHit(point: Vector2f): Boolean = { isHit(point, position) }
	def clicked(button: Int, point: Vector2f)
}