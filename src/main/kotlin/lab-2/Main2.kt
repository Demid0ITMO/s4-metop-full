package lab2

fun main() {
    val methods = arrayListOf(
        squareApproximationMethod
    )
    for (method in methods) {
        val (x, y) = method.eval()
        println("${method.name}, шагов итерации: ${method.i - 1}, x: $x, y: $y")
    }
}
