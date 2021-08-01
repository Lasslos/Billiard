package de.laslo.util

import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.absoluteValue

class SortedList<T : Comparable<T>> : CopyOnWriteArrayList<T>() {
    override fun set(index: Int, element: T): T {
        throw UnsupportedOperationException("Set element at specified position is not supported since list is sorted.")
    }

    override fun add(element: T): Boolean {
        val index = Collections.binarySearch(this, element)

        when {
            index > 0 -> super.add(index, element) //if matching pos was found
            index.absoluteValue < this.size -> super.add(
                index.absoluteValue,
                element
            ) //if there is a position where element can be inserted
            else -> super.add(element) //else append element to list
        }
        return true
    }

    override fun add(index: Int, element: T) {
        throw UnsupportedOperationException("Add element at specified position is not supported since list is sorted.")
    }

    override fun addAll(elements: Collection<T>): Boolean {
        return if (super.addAll(elements)) {
            Collections.sort(this)
            true
        } else false
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        throw UnsupportedOperationException("Add elements at specified position is not supported since list is sorted.")
    }
}