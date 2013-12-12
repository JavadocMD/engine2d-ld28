package com.javadocmd.engine2d.component

import com.javadocmd.engine2d.util.Vector2f

trait Volume extends Component {
	val shape: Shape
	def isHit(testPoint: Vector2f, position: Vector2f): Boolean = shape.isHit(testPoint, position)
}

trait Shape {
	def isHit(testPoint: Vector2f, position: Vector2f): Boolean
	def getBoundingBox(): Vector2f
	def getSmallCorner(position: Vector2f): Vector2f
	def getLargeCorner(position: Vector2f): Vector2f
}

class Rectangle(val size: Vector2f) extends Shape {
	private val half: Vector2f = size / 2f
	
	def this(width: Float, height: Float) = this(new Vector2f(width, height))
	def this(widthAndHeight: Float) = this(new Vector2f(widthAndHeight, widthAndHeight))

	override def isHit(testPoint: Vector2f, position: Vector2f): Boolean = {
		val topLeft: Vector2f = getSmallCorner(position)
		val bottomRight: Vector2f = getLargeCorner(position)
		
		if (testPoint.x < topLeft.x || testPoint.x > bottomRight.x)
			return false
		if (testPoint.y < topLeft.y || testPoint.y > bottomRight.y)
			return false
		return true
	}

	override def getBoundingBox(): Vector2f = size
	override def getSmallCorner(position: Vector2f): Vector2f = position - half
	override def getLargeCorner(position: Vector2f): Vector2f = position + half

	def x(): Float = size.x
	def y(): Float = size.y
}

class Circle(val radius: Float) extends Shape {
	private val half: Vector2f = new Vector2f(radius, radius)

	override def isHit(testPoint: Vector2f, position: Vector2f): Boolean = position.distance(testPoint) <= radius
	
	override def getBoundingBox(): Vector2f = half * 2f
	override def getSmallCorner(position: Vector2f): Vector2f = position - half
	override def getLargeCorner(position: Vector2f): Vector2f = position + half
}