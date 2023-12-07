import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions

interface TestRunner {
    fun <T> runTest(steps: T, test: () -> Unit)
}

class SomeTestSteps {

    fun beforeAllTest() {
        println("This print before all tests")
    }

    fun BeforeUpperCaseTest() {
        println("This print before UpperCase named tests")
    }

    fun afterFirstTest() {
        println("This print after 1st test")
    }

    fun beforeLastTest() {
        println("This print before last test")
    }

    fun afterAllTest() {
        println("This print after all tests")
    }

    fun AfterUpperCaseTest() {
        println("This print after UpperCase named tests")
    }


}

class Tests {

    fun tests() {
        println("///// This print while tests is running")
    }
}

class myTestRun : TestRunner {

    override fun <T> runTest(steps: T, test: () -> Unit) {
        val funsbefore = Regex("before\\w*")
        val funsBefore = Regex("Before\\w*")
        val funsafter = Regex("after\\w*")
        val funsAfter = Regex("After\\w*")

        val functions: Collection<KFunction<*>> = SomeTestSteps::class.declaredFunctions
        for (function in functions) {
            if (function.name.contains(funsbefore)) {
               println("Before tests ${function.name}")
            }
            if (function.name.contains(funsBefore)) {
                println("Before tests ${function.name}")
            }
        }

        val runTests = Tests()
        runTests.tests()

        for (function in functions) {
            if (function.name.contains(funsafter)) {
                println("After tests ${function.name}")
            }
            if (function.name.contains(funsAfter)) {
                println("After tests ${function.name}")
            }
        }
    }
}

fun main() {
    val myTestRunner = myTestRun()
    val testFun = SomeTestSteps()

    myTestRunner.runTest(steps = testFun) {

    }

}