package com.javadocmd.engine2d.component

import com.javadocmd.engine2d.util.Vector2f

trait Movement extends Component {
	val maxSpeed: Float
	var velocity: Vector2f = Vector2f.ZERO
}