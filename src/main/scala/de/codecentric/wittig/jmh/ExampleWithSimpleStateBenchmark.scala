package de.codecentric.wittig.jmh

import org.openjdk.jmh.annotations.{Scope, State, _}

import scala.util.matching.Regex

@State(Scope.Thread)
class MyState {
  val regex: Regex = ".*gunther".r
}

/**
  *  Run via `jmh:run   .*ExampleWithSimpleStateBenchmark*`
  */
@BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 1)
class ExampleWithSimpleStateBenchmark {

  @Benchmark
  def WithStateRegex(state: MyState): Int =
    if (state.regex.pattern.matcher("hallo gunther").matches())
      1
    else
      2

  @Benchmark
  def WithRegex(state: MyState): Int =
    if (".*gunther".r.pattern.matcher("hallo gunther").matches())
      1
    else
      2

  @Benchmark
  def WithStringMatches(): Int =
    if ("hallo gunther".matches(".*gunther"))
      1
    else
      2

  @Benchmark
  def endsWith(): Int =
    if ("hallo gunther".endsWith("gunther"))
      1
    else
      2
}
