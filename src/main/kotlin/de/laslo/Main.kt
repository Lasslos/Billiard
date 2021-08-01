package de.laslo

import de.laslo.game.Ball
import de.laslo.game.MovementHandler
import de.laslo.game.Vector
import de.laslo.gui.Frame

fun main() {
    Frame
    Ball
    MovementHandler
}

const val FRAME_WIDTH = 1920.0
const val FRAME_HEIGHT = 1080.0

const val PLAYING_FIELD_WIDTH = 1600.0
const val PLAYING_FIELD_HEIGHT = 800.0

val PLAYING_FIELD_ORIGIN = Vector((FRAME_WIDTH - PLAYING_FIELD_WIDTH) / 2, (FRAME_HEIGHT - PLAYING_FIELD_HEIGHT) / 2)

const val LEFT_WALL_RELATIVE = 0.0
val LEFT_WALL = PLAYING_FIELD_ORIGIN.x + LEFT_WALL_RELATIVE
const val RIGHT_WALL_RELATIVE = PLAYING_FIELD_WIDTH
val RIGHT_WALL = PLAYING_FIELD_ORIGIN.x + RIGHT_WALL_RELATIVE
const val UPPER_WALL_RELATIVE = 0.0
val UPPER_WALL = PLAYING_FIELD_ORIGIN.y + UPPER_WALL_RELATIVE
const val LOWER_WALL_RELATIVE = PLAYING_FIELD_HEIGHT
val LOWER_WALL = PLAYING_FIELD_ORIGIN.y + LOWER_WALL_RELATIVE

const val PUFFER_ZONE_SIZE = 15
const val HOLE_SIZE = 72
const val BALL_HEIGHT_WIDTH = 41

val holes: Array<Vector> = arrayOf(
    Vector(LEFT_WALL_RELATIVE - PUFFER_ZONE_SIZE, UPPER_WALL_RELATIVE - PUFFER_ZONE_SIZE), //left-up
    Vector(FRAME_WIDTH / 2, UPPER_WALL_RELATIVE - PUFFER_ZONE_SIZE), //middle-up
    Vector(RIGHT_WALL_RELATIVE + PUFFER_ZONE_SIZE, UPPER_WALL_RELATIVE - PUFFER_ZONE_SIZE), //right-up
    Vector(LEFT_WALL_RELATIVE - PUFFER_ZONE_SIZE, LOWER_WALL_RELATIVE + PUFFER_ZONE_SIZE), //left-down
    Vector(FRAME_WIDTH / 2, LOWER_WALL_RELATIVE + PUFFER_ZONE_SIZE), //middle-down
    Vector(RIGHT_WALL_RELATIVE + PUFFER_ZONE_SIZE, LOWER_WALL_RELATIVE + PUFFER_ZONE_SIZE), //right-down
)

val WHITE_BALL_START_LOCATION = Vector(RIGHT_WALL + PLAYING_FIELD_WIDTH / 4, FRAME_HEIGHT / 2)

const val RES_DIRECTORY = "./src/main/resources"