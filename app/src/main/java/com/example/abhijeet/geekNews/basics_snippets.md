```fun main() {
val name: String = "Kotlin" // Immutable variable
var age: Int = 5            // Mutable variable
age += 1
println("Language: $name, Age: $age")
}```
#
```fun main() {
val score = 85
if (score >= 90) {
println("Grade: A")
} else if (score >= 75) {
println("Grade: B")
} else {
println("Grade: C")
}
}```
#
'''fun main() {
val day = 5
val dayName = when (day) {
1 -> "Monday"
2 -> "Tuesday"
3 -> "Wednesday"
4 -> "Thursday"
5 -> "Friday"
6 -> "Saturday"
7 -> "Sunday"
else -> "Invalid day"
}
println("Day $day is $dayName")
}'''
#
```fun greetUser(name: String): String {
return "Hello, $name!"
}

fun main() {
println(greetUser("Abhijeet"))
}```

#
```class Person(val name: String, var age: Int) {
    fun introduce() {
        println("Hi, I'm $name, and I'm $age years old.")
    }
}
fun main() {
    val person = Person("Abhijeet", 25)
    person.introduce()
}```


#
```fun main() {
    // For loop
    for (i in 1..5) {
        println("GeekLoo0p Iteration: $i")
    }

    // While loop
    var count = 5
    while (count >= 0) {
        println("While loop countdown: $counter")
        count--
    }
}```
