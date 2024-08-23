import kotlin.math.pow

fun main() {
    // Nullable types
    // Kotlin accepts nullable types and non-nullable types
    // The nullable types are declared with a question mark
    var name: String? = null
    var name1: String = "Carlos"
    // The non-nullable types are declared without a question mark
    // Check if the variable is not null
    if (name != null) {
        println(name.length)
    }
    // Check with null safety operator
    println(name?.length)

    // Same as using the null safety operator
    val s: String? = null
    val length = if (s != null) s.length else null

    // If we want to have a default value in case of null, we can use the Elvis operator
    val length1 = s?.length ?: -1

    // We can throw an exception if the value is null using the !! operator
    val length2 = try {
        s!!.length
    } catch (e: NullPointerException) {
        -1
    }

    println(length1)
    println(length2)

    // Nullability under the hood
    /*
    @NotNull
    public static final String foo() {
        return "foo";
    }
     */
    fun foo(): String = "foo"

    /*
    @Nullable
    public static final String bar() {
        return null;
    }
     */
    fun bar(): String? = null

    // Nullable lists
    val nullableList: List<Int?> = listOf(1, 2, null, 4) // Elements can be null
    val intList: List<Int>? = null // The list itself can be null


    val s1: String? = null
    val s2: String? = ""
    println(s1.isEmptyOrNull())
    println(s2.isEmptyOrNull())

    val s3 = "   "
    println(s3.isEmptyOrNull())

    val s4 = "Carlos"
    println(s4.isEmptyOrNull())

    // Type casts
    if (s1 is String) { // s1 instanceof String
        var s = s1 as String
        s.uppercase()
    }

    // Using safe cast
    val result = s1 as? Int // If the cast is not possible, the result is null
    val s5: String? = s1 as? String


    ///////////////////////////////////////////////////////////
    // Functional Programming

    // Lambdas vs Anonymous functions
    /*
    // Action Listener es una interfaz funcional
    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button clicked");
        }
     */

    // Lambda
    /*
    Java:
    button.addActionListener(e -> System.out.println("Button clicked"));
     */

    // Lambda Syntax
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 2))

    // If you pass a lambda as an argument, we can use the full syntax
    fun performOperation(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
        return operation(x, y)
    }

    // When lambda is the last parameter, we can use the trailing lambda syntax
    fun performOperation1(x: Int, y: Int) {
        println(x + y)
    }

    val sum1 = performOperation(1, 2, sum)
    println(sum1)

    // Basic example function receiving a lambda as only parameter
    fun executeOperation(operation: () -> Unit) {
        println("Before calling operation")
        operation()
        println("After calling operation")
    }

    executeOperation { println("This is the operation") }

    // Example with parameter
    fun executeOperationWithParameter(operation: (Int) -> Int) {
        println("Before calling operation")
        println("After calling operation " + operation(5))
    }

    executeOperationWithParameter {
        println("This is the operation with parameter $it")
        it * 2 // You can return the value of the last expression
    }

    ////////////////////////////////////////////////////////////
    // Common Operations on collections
    // Extensions on collections
    // filter, map, reduce, count, find, any, flatMap, groupBy

    // Using filter
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println(evenNumbers)
    val powerOfTwo = numbers.map { it.toDouble().pow(2).toInt() }
    println(powerOfTwo)
    val findEleven = numbers.find { it == 11 } // It's an alias for firstOrNull
    println(findEleven)
    val findElevenFirst = try {
        numbers.first { it == 11 }
    } // Throws an exception if not found }
    catch (e: NoSuchElementException) {
        null
    }
    println(findElevenFirst)
    val evenAndOdds = numbers.partition { it % 2 == 0 }
    println(evenAndOdds)
    val groupNumbers = numbers.groupBy { if (it % 2 == 0) "even" else "odd" }
    println(groupNumbers)
    val associateNumbers = numbers.associateBy { it % 2 == 0 } // The difference between groupBy and
    // associateBy is that the first one returns a map with a list of elements and the second one returns a map with a single element
    println(associateNumbers)

    val countNumbers = numbers.count { it == 1 || it == 2 }
    println(countNumbers)

    // using zip
    val numbersDescending = { 9 downTo 1 }
    val numbersAscending = { 1..9 }
    val zipped = numbersDescending().zip(numbersAscending()) // Using zip to combine two lists if there's
    // a different number of elements, the result will be the size of the smallest list
    println(zipped)

    // Defining infix functions
    infix fun Int.add(x: Int): Int = this + x
    println(1 add 2)

    // Zip with next
    val numbersWithNext = numbers.zipWithNext { a, b ->
        a + 1 to b + 1
    }

    println(numbersWithNext)

    val listOfLists = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))
    val flatMap = listOfLists.flatten()
    println(flatMap)

    val listOfMaps = listOf(mapOf("a" to 1, "b" to 2), mapOf("c" to 3, "d" to 4))
    val flatMapMaps = listOfMaps.flatMap { it.values }
    println(flatMapMaps)
}

fun String?.isEmptyOrNull() = this.isNullOrEmpty() || this.isEmpty()

fun isEmptyOrNullParam(s: String?) = s.isNullOrEmpty() || s.isEmpty()
