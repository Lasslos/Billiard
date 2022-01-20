package eu.lh0.game

import kotlin.math.pow
import kotlin.math.sqrt

data class Vector(var x: Double, var y: Double) {
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    val length: Double
    get() = sqrt(x.pow(2) + y.pow(2))

    fun getDistanceTo(other: Vector) = sqrt((other.x - this.x).pow(2) + (other.y - this.y).pow(2))

    operator fun plus(other: Vector) = Vector(this.x + other.x, this.y + other.y)
    operator fun plus(other: Number) =
        Vector((this.x + other.toDouble()), (this.y + other.toDouble()))
    operator fun minus(other: Vector) = Vector(this.x - other.x, this.y - other.y)
    operator fun minus(other: Number) =
        Vector((this.x - other.toDouble()), (this.y - other.toDouble()))
    operator fun times(other: Vector) = Vector(this.x * other.x, this.y * other.y)
    operator fun times(other: Number) =
        Vector((this.x * other.toDouble()), (this.y * other.toDouble()))
    operator fun div(other: Vector) = Vector(this.x / other.x, this.y / other.y)
    operator fun div(other: Number) = Vector((this.x / other.toDouble()), (this.y / other.toDouble()))
    operator fun rem(other: Vector) = Vector(this.x % other.x, this.y % other.y)
    operator fun rem(other: Number) = Vector((this.x % other.toDouble()), (this.y % other.toDouble()))
    operator fun inc() = Vector(this.x + 1, this.y + 1)
    operator fun dec() = Vector(this.x - 1, this.y - 1)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = this * (-1)

    fun copyWith(x: Double = this.x, y: Double = this.y) = Vector(x, y)
}