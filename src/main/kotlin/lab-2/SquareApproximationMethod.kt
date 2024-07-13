package lab2

import lab1.methods.Method
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow

val squareApproximationMethod = Method("Квадратичная аппроксимация") {
    val ans: Pair<Double, Double>
    val dx = (b - a) * epsilon / 2
    var x1 = (b + a) / 2
    var f1 = function(x1)
    var x2 = x1 + dx
    var f2 = function(x2)
    var x3 = if (f1 > f2) x1 + 2 * dx else x1 - dx
    var f3 = function(x3)
    var fromSecondStep = false
    while(true) {
        if (fromSecondStep) {
            x2 = x1 + dx
            f1 = function(x1)
            f2 = function(x2)
            x3 = if (f1 > f2) x1 + 2 * dx else x1 - dx
            f3 = function(x3)
        } else {
            f1 = function(x1)
            f2 = function(x2)
            f3 = function(x3)
        }
        val (Fmin, Xmin) =
            if (f1 <= f2 && f1 <= f3) f1 to x1
            else if (f2 <= f1 && f2 <= f3) f2 to x2
            else f3 to x3
        var Xbar: Double
        var Fbar: Double
        try {
            Xbar = (
                    (x2.pow(2) - x3.pow(2)) * f1 +
                    (x3.pow(2) - x1.pow(2)) * f2 +
                    (x1.pow(2) - x2.pow(2)) * f3
            ) / (
                2 * (
                    (x2 - x3) * f1 +
                    (x3 - x1) * f2 +
                    (x1 - x2) * f3
                )
            )
            Fbar = function(Xbar)
        } catch (_: Exception) {
            x1 = Xmin
            fromSecondStep = true
            continue
        }
        val c = (if (abs((Fmin - Fbar) / Fbar) < epsilon) 1 else 0) +
                (if (abs((Xmin - Xbar) / Xbar) < epsilon) 1 else 0)
        if (c == 2) {
            ans = Xbar to Fbar
            break
        }
        else {
            if (Xbar in x1..x3) {
                x2 = min(Xmin, Xbar)
                fromSecondStep = false
            }
            else {
                x1 = Xbar
                fromSecondStep = true
            }
        }
        i++
    }
    ans
}.also { it.epsilon = 0.0001 }