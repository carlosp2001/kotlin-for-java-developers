fun main (args: Array<String>) { // Since Kotlin 1.3, the main function can have no arguments
    val name = if (args.isNotEmpty()) args[0] else "World"
    println("Hello, $name!")

    // Using getOrNull
    println("Argument is ${args.getOrNull(1)}")
}
