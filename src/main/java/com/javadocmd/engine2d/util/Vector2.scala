package com.javadocmd.engine2d.util

import java.lang.Math.abs
import java.lang.Math.hypot

import scala.language.implicitConversions

import com.google.common.base.Objects

trait Vector2

object Vector2f {
	val ZERO = new Vector2f(0,0)
	def apply(x: Float, y: Float): Vector2f = new Vector2f(x, y)
	def apply[T](x: T, y: T)(implicit t:Numeric[T]): Vector2f = new Vector2f(t.toFloat(x), t.toFloat(y))
}
class Vector2f(val x: Float, val y: Float) extends Vector2 {
	def +(that: Vector2f) = new Vector2f(x + that.x, y + that.y)
	def -(that: Vector2f) = new Vector2f(x - that.x, y - that.y)
	def *(that: Vector2f) = new Vector2f(x * that.x, y * that.y)
	def /(that: Vector2f) = new Vector2f(x / that.x, y / that.y)
	
	def *(scale: Float) = new Vector2f(x * scale, y * scale)
	def /(scale: Float) = new Vector2f(x / scale, y / scale)
	
	def normalize(): Vector2f = length() match {
		case 0 => this
		case len => new Vector2f(x / len, y / len)
	}
	def clip(maxLength: Float): Vector2f = length() match {
		case length if (length <= maxLength) => this
		case length => this * (maxLength / length)
	}
	
	def length(): Float = distance(Vector2f.ZERO)
	def distance(that: Vector2f): Float = hypot(that.x - x, that.y - y).toFloat
	
	override def equals(that: Any): Boolean =  
		that.isInstanceOf[Vector2f] && {
			val o = that.asInstanceOf[Vector2f]
			o.x == x && o.y == y
		}
	def epsilonEquals(that: Vector2f, epsilon: Float): Boolean = {
		if (abs(that.x - x) > epsilon) return false
		if (abs(that.y - y) > epsilon) return false
		return true
	}
	override def toString(): String = "(%f, %f)".format(x, y)
	override def hashCode(): Int = Objects.hashCode(new java.lang.Float(x), new java.lang.Float(y))
}

object Vector2i {
	val ZERO = new Vector2i(0, 0)
	def apply(x: Int, y: Int): Vector2i = new Vector2i(x, y)
	def apply[T](x: T, y: T)(implicit t:Numeric[T]): Vector2i = new Vector2i(t.toInt(x), t.toInt(y))
}
class Vector2i(val x: Int, val y: Int) extends Vector2 {
	def +(that: Vector2i) = new Vector2i(x + that.x, y + that.y)
	def -(that: Vector2i) = new Vector2i(x - that.x, y - that.y)
	def *(that: Vector2i) = new Vector2i(x * that.x, y * that.y)
	def /(that: Vector2i) = new Vector2i(x / that.x, y / that.y)
	
	def *(scale: Int) = new Vector2i(x * scale, y * scale)
	def /(scale: Int) = new Vector2i(x / scale, y / scale)
	
	def normalize(): Vector2f = { new Vector2f(x,y).normalize() }
	
	def length(): Float = distance(Vector2i.ZERO)
	def distance(that: Vector2i): Float = hypot(that.x - x, that.y - y).toFloat
	
	override def equals(that: Any): Boolean =  
		that.isInstanceOf[Vector2i] && {
			val o = that.asInstanceOf[Vector2i]
			o.x == x && o.y == y
		}
	override def toString(): String = "(%d, %d)".format(x, y)
	override def hashCode(): Int = Objects.hashCode(new java.lang.Integer(x), new java.lang.Integer(y))
}