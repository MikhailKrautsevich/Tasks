package com.example.hometask_03_kotlinclasses

import kotlin.math.sqrt

class Triangle (side1: Int, side2: Int, side3: Int) : Figure() {
    fun getSquare(side1: Int, side2: Int, side3: Int): Double {
        val halfperimeter : Double = (side1 + side2 + side3)/2.0
        val square : Double = sqrt(halfperimeter*(halfperimeter-side1)*(halfperimeter-side2)*(halfperimeter-side3))
        return square
    }
}