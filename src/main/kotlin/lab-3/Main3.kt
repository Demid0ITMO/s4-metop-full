package lab3

import lab3.methods.fastestDescentMethod
import lab3.methods.gradientDescentMethod

fun main() {
    val methods = arrayListOf(
        gradientDescentMethod,
        fastestDescentMethod
    )
    for (method in methods) {
        val ans = method.evalMin()
        println(method.name)
        print("Точка минимума: (")
        printAns(ans)
        println(")\nШагов итерации: ${method.i - 1}")
        printF(method.function(ans))
    }
}

fun printAns(ans: DoubleArray) {
    for (i in ans)
        if (i.isInfinite()) {
            print("-, -, -")
            return
        }
    for (i in ans) print("$i" + if (ans.last() == i) "" else ", ")
}

fun printF(f: Double) { println("F = " + if (f.isNaN()) "-" else f.toString()) }