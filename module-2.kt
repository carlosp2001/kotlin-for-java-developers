import java.awt.Color
import java.awt.Color.*

// Example of class in Java
/*
public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
*/

// Modifier data adds the following methods to the class:
// - equals(Object)
// - hashCode()
// - toString()
data class Person(val name: String, val age: Int)

// To create an instance of the class, use the following syntax:
// Java:
// Person person = new Person("Alice", 20);
// System.out.println(person.getName());

// Kotlin:
val person = Person("Alice", 27)

// Función en Java
/*
public void updateWeather(int degrees) {
    String description;
    Color color;
    if (degrees < 5) {
        description = "cold";
        color = BLUE;
    } else if (degrees < 25) {
        description = "mild";
        color = YELLOW;
    } else {
        description = "hot";
        color = RED;
    }
}
*/

// Función en Kotlin
fun updateWeather(degrees: Int) {
//    val description: String
//    val color: Color
//    if (degrees < 10) {
//        description = "cold"
//        color = BLUE
//    } else if (degrees < 25) {
//        description = "mild"
//        color = YELLOW
//    } else {
//        description = "hot"
//        color = RED
//    }

    // We also can initialize the variables in the declaration
    val (description: String, color: Color) =
        if (degrees < 10) {
            Pair("cold", BLUE)
        } else if (degrees < 25) {
            Pair("mild", YELLOW)
        } else {
            Pair("hot", RED)
        }

    // Using when instead of if-else
    /*
    when {
        degrees < 5 -> {
            description = "cold"
            color = BLUE
        }
        degrees < 25 -> {
            description = "mild"
            color = YELLOW
        }
        else -> {
            description = "hot"
            color = RED
        }
    }
    */

//    val (description2, color2) = when {
//        degrees < 5 -> Pair("cold", BLUE)
//        degrees < 25 -> Pair("mild", YELLOW)
//        else -> Pair("hot", RED)
//    }

    // We can even replace the constructing of Pair
    val (description2, color2) = when {
        degrees < 5 -> "cold" to BLUE
        degrees < 25 -> "mild" to YELLOW
        else -> "hot" to RED
    }

    println(description)
    println(color)
}

fun main() {
    println(person.name)
    println(person.age)
    updateWeather(10)

    // Variables in Kotlin
    val name = "Alice" // Val is immutable (final in Java)
    var age = 20 // Var is mutable

    // Using mutableList and immutableList
    val immutableList = listOf(1, 2, 3)
//    immutableList.add(4) // Error: Unresolved reference: add
    val mutableList = mutableListOf(1, 2, 3)
    mutableList.add(4)

    // Functions in Kotlin

    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    // Function with expression body
    fun max2(a: Int, b: Int) = if (a > b) a else b

    // Function returning Unit (void in Java)
    fun printHello(name: String): Unit {
        println("Hello, $name!")
    }

    // Functions everywhere
    /*
    fun topLevel() = 1

    class A {
        fun member() = 2
    }

    fun other() {
        fun local() = 3
    }
     */

    /*
    In Java we can use top level functions as a static function of the class,
    which name corresponds to the file name.

    public class UsingFoo {
        public static void main(String[] args) {
            MyFileKt.foo();
        }
    }

     */

    // Name in default arguments
    println(listOf('a', 'b', 'c').joinToString(separator = "", prefix = "(", postfix = ")")) // prints (abc)

    // Function with default arguments
    fun displaySeparator(character: Char = '*', size: Int = 10) {
        repeat(size) {
            print(character)
        }
        println()
    }

    displaySeparator(character = '*', size = 5) // prints *****

    // In Java we need to create an overloaded method to achieve the same result

    // Conditionals structures in Kotlin
    val a = 5
    val b = 10
    val max = if (a > b) a else b

    // Using when with enum
    fun getDescription(color: Color) =
        when (color) {
            RED -> "hot"
            BLUE -> "cold"
            YELLOW -> "mild"
            else -> "neutral"
        }

    print(getDescription(BLUE)) // prints cold

    fun respondToInput(input: String) = when(input) {
        "y", "yes" -> "I'm glad you agree"
        "n", "no" -> "Sorry to hear that"
        else -> "I don't understand you"
    }

    println(respondToInput("y")) // prints I'm glad you agree

    // Any expression can be used as a branch condition
    fun mix(c1: Color, c2: Color) =
        when(setOf(c1, c2)) {
            setOf(RED, YELLOW) -> ORANGE
            setOf(YELLOW, BLUE) -> GREEN
            setOf(BLUE) -> ORANGE
            else -> throw Exception("Dirty color")
        }

    /*
    Checking types
     */
    open class Pet {
        fun feed() = "Feed the pet"
    }

    class Cat : Pet() {
        fun cleanLitter() =
            "Clean the litter"
    }

    class Dog: Pet() {
        fun walk() =
            "Walk the dog"
    }

    val pet = Cat()

//    when (pet) {
//        is Cat -> pet.cleanLitter()
//        is Dog -> pet.walk() // is works as instanceof in Java
//    }

    // Capturing when subject in a variable
//    fun getSound(): String =
//        when (val pet2 = Dog()) {
//            is Cat -> pet2.cleanLitter()
//            is Dog -> pet2.walk()
//            else -> "No sound"
//        }

    // Checking conditions: when without an argument
    fun updateWeather2(degrees: Int) {
        val (description, colour) = when {
            degrees < 10 -> "cold" to BLUE
            degrees < 25 -> "mild" to YELLOW
            else -> "hot" to RED
        }
    }
}
