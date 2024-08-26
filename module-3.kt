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

    ////////////////////////////////////////////////////////////
    // Operations Quiz I
    data class Hero(
        val name: String,
        val age: Int,
        val gender: Gender?
    )

    val heroes = listOf(
        Hero("The Captain", 60, Gender.MALE),
        Hero("Frenchy", 42, Gender.MALE),
        Hero("The Kid", 9, null),
        Hero("Lady Lauren", 29, Gender.FEMALE),
        Hero("First Mate", 29, Gender.MALE),
        Hero("Sir Stephen", 37, Gender.MALE)
    )

    println(heroes.last().name) // If the list is empty, it throws an exception
    println(heroes.lastOrNull()?.name) // If the list is empty, it returns null
    println(heroes.firstOrNull { it.age == 30 }?.name) // If the list is empty, it returns null
    println(heroes.map { it.age }
        .distinct().size) // If the list is empty, it returns 0, returns the number of different ages
    println(heroes.filter { it.age < 30 }.size) // If the list is empty, it returns 0,
    val (youngest, oldest) = heroes.partition { it.age < 30 }
    println(oldest.size) // If the list is empty, it returns 0

    println(heroes.maxByOrNull { it.age }?.name) // If the list is empty, it returns null
    println(heroes.maxBy { it.age }.name) // If the list is empty, it throws an exception
    println(heroes.all { it.age < 50 }) // It returns true if all the elements match the condition, in case of an empty list, it returns true
    println(heroes.any { it.gender == Gender.FEMALE }) // It returns true if any element matches the condition, in case of an empty list, it returns false
    // Operations Quiz II
    val mapByAge =
        heroes.groupBy { it.age } // It returns a map with the age as key and the list of heroes with that age as value

    val (age, group) = mapByAge.maxByOrNull { (_, group) -> group.size }!!// It returns the age with the most heroes
    // El operador !! se usa para indicar que el valor no puede ser nulo, en caso de que sea nulo, se lanza una excepción

    println(age) // It prints the age with the most heroes

    val mapByName = heroes.associateBy { it.name } // It returns a map with the name as key and the hero as value
    // AssociateBy returns a map with the key and the value of the list, if there are repeated keys, the last one is taken
    mapByName["Frenchy"]?.let { println(it.age) } // It prints the age of the hero with the name Frenchy
    // Another way to access elements in a map
//    println(mapByName.getValue("unknown"))
//    It throws an exception if the key is not found

    println(
        mapByName.getOrDefault(
            "unknown",
            Hero("Unknown", 0, null)
        ).age
    ) // It prints the age of the hero with the name unknown

    val unknownHero = Hero("Unknown", 0, null)

    // Using get or else to provide a default value
    println(mapByName.getOrElse("unknown") { unknownHero }.age) // It prints the age of the hero with the name unknown

    val (first, second) = heroes
        .flatMap { heroes.map { hero -> it to hero } }
        .maxBy { it.first.age - it.second.age } // It returns the pair of heroes with the biggest age difference
    println(first.name)

    // Using a better approach and sintax
    val allPossiblePairs = heroes
        .flatMap { firstHero -> heroes.map { secondHero -> firstHero to secondHero } }


    val (oldestHero, youngestHero) = allPossiblePairs.maxBy { it.first.age - it.second.age }/// It returns the pair of heroes with the biggest age difference
    println(oldestHero.name)

    ////////////////////////////////////////////////////////////
    // Function Type
    val sumLambda: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    sumLambda(1, 2)
    val isEven = { i: Int -> i % 2 == 0 }
    val resultIsEven = isEven(2)
    println(resultIsEven)

    // We can pass a lambda as a parameter
    val listLambda = listOf(1, 2, 3, 4, 5)
    println(listLambda.any(isEven))
    println(listLambda.filter(isEven))

    // Calling a lambda immediately'
    val lambdaCallImmediately = {
        println("Hello I'm Carlos from immediate call")
    }()

    // Using run
    val runResult = run {
        println("Hello I'm Carlos from immediate run")
        42
    }

    // Nullable types in lambda functions
//    val f1: () -> Int? = null // Not compile time error
    val f2: () -> Int? = { null } // It means that the function can return null
    val f3: (() -> Int)? = null // It means that the function can be null
//    val f4: (() -> Int)? = { null } // It means that the function can be null but it can't return null, cannot compile
    // Working with nullable types in lambda functions
    if (f3 != null) {
        f3()
    }

    // Alternative is to use the safe accessor
    f3?.invoke()

    ////////////////////////////////////////////////////////////
    // Member references
    class Person(val name: String, val age: Int) {
        fun isOlder(ageLimit: Int): Boolean = age > ageLimit

    }

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    val result1 = people.maxByOrNull { it.age }
    val result2 = people.maxByOrNull(Person::age)
    // You can't assign a function to a variable only a lambda
    // val result3 = people.maxByOrNull(Person::isOlderThan) // It doesn't compile

    // Using function reference instead
    fun isEvenFun(i: Int) = i % 2 == 0
    val predicateIsEven = ::isEvenFun

    // Passing function reference as an argument
    val list3 = listOf(1, 2, 3, 4, 5)
    println(list3.any(::isEvenFun))
    println(list3.filter(::isEvenFun))

    // Bound and non-bound references
    val agePredicate: (Person, Int) -> Boolean = Person::isOlder

    // Not bound reference is a reference to a function that is not associated with an instance. Example:
//    fun isOlderThan(other: Person): Boolean {
//        return age > other.age
//    }


    val alice = Person("Alice", 29)
    agePredicate(alice, 21)

    // Bound Reference, it's a reference to a function that is associated with an instance. Example:
    val agePredicateAlice = alice::isOlder
    agePredicateAlice(21)
}

enum class Gender { MALE, FEMALE }

fun String?.isEmptyOrNull() = this.isNullOrEmpty() || this.isEmpty()

fun isEmptyOrNullParam(s: String?) = s.isNullOrEmpty() || s.isEmpty()
