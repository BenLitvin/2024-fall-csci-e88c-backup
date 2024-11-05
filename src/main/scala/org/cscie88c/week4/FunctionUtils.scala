package org.cscie88c.week4

object FunctionUtils {

  // complete the implementation of the higher order functions below

  // This function applies given function f to an input x, repeating the process n times, 
  // essentially chaining the function's effect multiple times because of iteration
  def applyNtimes(n: Int)(x: Int)(f: Int => Int): Int = {
    (1 to n).foldLeft(x)((acc, _) => f(acc))
  }

  // This function computes x raised to the power of n by using applyNtimes to multiply x by itself n times 
  // and leveraging function repetition instead of the built-in power function
  def myPositivePower(x: Int, n: Int): Int = applyNtimes(n)(1)(_ * x)

  // This function creates wrapper around another function f and logging a message with the executor's name 
  // and input value before executing f, which allows us to track the function's execution context and input
  def deferredExecutor(name: String)(f: Int => Int): Int => Int = { input =>
    println(s"running on deferred executor $name with value $input")
    f(input)
  }
}

