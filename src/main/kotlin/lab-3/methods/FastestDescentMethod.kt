package lab3.methods

import kotlin.math.pow

val fastestDescentMethod = Method("Наискорейший спуск") {
    ans ->
    val a = -3 * derivatives[1](ans)
    val b = 2 * (
            2 * derivatives[0](ans).pow(2) +
                    3 * ans[1] * derivatives[1](ans).pow(2) +
                    derivatives[2](ans).pow(2) +
                    derivatives[0](ans) * derivatives[1](ans) +
                    2 * derivatives[0](ans) * derivatives[2](ans)
            )
    val c = (
            -4 * derivatives[0](ans) * ans[0] -
                    3 * derivatives[1](ans) * ans[1].pow(2) -
                    2 * derivatives[2](ans) * ans[2] +
                    ans[1] * derivatives[0](ans) +
                    ans[0] * derivatives[1](ans) -
                    2 * derivatives[0](ans) * ans[2] -
                    2 * derivatives[2](ans) * ans[0] +
                    derivatives[1](ans)
            )
    var h = (-b + Math.sqrt(b.pow(2) + 4 * a * c)) / (2 * a)
    if (h.isNaN()) h = (-b - Math.sqrt(b.pow(2) + 4 * a * c)) / (2 * a)
    if (h.isNaN()) h = 0.05
    h
}
