package com.javadocmd.engine2d.component

import com.javadocmd.engine2d.util.Vector2f

trait Position extends Component {
	var position:Vector2f
	
	def isNear(location: Vector2f): Boolean = { position.epsilonEquals(location, 0.1f) }
}