package com.javadocmd.engine2d.component

trait Lifespan extends Component {
	var lifespan: Float = 0
	private var lifeLeft: Float = 0
	
	def setLifespan(l: Float) = { lifespan = l; lifeLeft = l }
	def age(delta: Float): Float = { lifeLeft -= delta; lifeLeft }
}