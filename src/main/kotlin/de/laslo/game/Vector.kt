package de.laslo.game

import kotlin.math.pow
import kotlin.math.sqrt

data class Vector(val x: Double, val y: Double) {
    fun setX(x: Double) = Vector(x, this.y)
    fun setY(y: Double) = Vector(this.x, y)

    fun getDistance(other: Vector) = sqrt((this.x - other.x).pow(2) + (this.y - other.y).pow(2))
    val length: Double
        get() = sqrt(this.x.pow(2) + this.y.pow(2))

    operator fun plus(other: Vector) = Vector(this.x + other.x, this.y + other.y)
    operator fun plus(other: Number) = Vector((this.x + other.toDouble()), (this.y + other.toDouble()))
    operator fun minus(other: Vector) = Vector(this.x - other.x, this.y - other.y)
    operator fun minus(other: Number) = Vector((this.x - other.toDouble()), (this.y - other.toDouble()))
    operator fun times(other: Vector) = Vector(this.x * other.x, this.y * other.y)
    operator fun times(other: Number) = Vector((this.x * other.toDouble()), (this.y * other.toDouble()))
    operator fun div(other: Vector) = Vector(this.x / other.x, this.y / other.y)
    operator fun div(other: Number) = Vector((this.x / other.toDouble()), (this.y / other.toDouble()))
    operator fun rem(other: Vector) = Vector(this.x % other.x, this.y % other.y)
    operator fun rem(other: Number) = Vector((this.x % other.toDouble()), (this.y % other.toDouble()))
    operator fun inc() = Vector(this.x + 1, this.y + 1)
    operator fun dec() = Vector(this.x - 1, this.y - 1)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = this * (-1)
}