package lab3.methods

import kotlin.math.pow
import kotlin.math.sqrt


class Method(
    val name: String,
    val evalH: Method.(ans: DoubleArray) -> Double
) {
    var i = 1
    var epsilon = 0.001
    var function = {
        vector: DoubleArray ->
        val x1 = vector[0]
        val x2 = vector[1]
        val x3 = vector[2]
        2 * x1.pow(2) + x2.pow(3) + x3.pow(2) - x1 * x2 + 2 * x3 * x1 - x2
    }
    var derivatives = arrayListOf(
        {
            vector: DoubleArray ->
            val x1 = vector[0]
            val x2 = vector[1]
            val x3 = vector[2]
            4 * x1 - x2 + 2 * x3
        },
        {
            vector: DoubleArray ->
            val x1 = vector[0]
            val x2 = vector[1]
            3 * x2.pow(2) - x1 - 1
        },
        {
            vector: DoubleArray ->
            val x1 = vector[0]
            val x3 = vector[2]
            2 * x3 + 2 * x1
        }
    )
    fun evalMin() = execution.invoke(this, -1.0)
    fun evalMax() = execution.invoke(this, 1.0)

    private var execution: Method.(vector: Double) -> DoubleArray = {
        vector: Double ->
        var ans = DoubleArray(derivatives.size) { 0.0 }
        i = 1
        var canDo = true
        while (canDo) {
            val newAns  = ans
            var gradientLength = 0.0
            for (i in 0..2) {
                val derivative = derivatives[i](ans)
                newAns[i] = ans[i] + vector * evalH(ans) * derivative
                gradientLength += derivative.pow(2)
            }
            i++
            ans = newAns
            if (sqrt(gradientLength) <= epsilon) break
            for (x in ans) if (x.isInfinite()) canDo = false
        }
        ans
    }
}