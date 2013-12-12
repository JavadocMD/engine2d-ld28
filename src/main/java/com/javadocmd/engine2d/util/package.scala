package com.javadocmd.engine2d

package object util {
	implicit def tupleToVector2f[T,U](xs: Tuple2[T,U])(implicit t:Numeric[T], u:Numeric[U]): Vector2f = {
		new Vector2f(t.toFloat(xs._1), u.toFloat(xs._2))
	}
	
	implicit def tupleToVector2i[T,U](xs: Tuple2[T,U])(implicit t:Numeric[T], u:Numeric[U]): Vector2i = {
		new Vector2i(t.toInt(xs._1), u.toInt(xs._2))
	}
	
	implicit def vector2fTo2i(xs: Vector2f): Vector2i = new Vector2i(xs.x.toInt, xs.y.toInt)
	
	implicit def vector2iTo2f(xs: Vector2i): Vector2f = new Vector2f(xs.x.toFloat, xs.y.toFloat)
}