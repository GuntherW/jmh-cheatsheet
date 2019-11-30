package de.codecentric.wittig.jmh

import org.openjdk.jmh.annotations.{Scope, State, _}

case class Person(name: String, alter: Int)

@State(Scope.Thread)
class MeinBenchmarkState {
  val list: List[Person] = List.tabulate(100000)(i => Person(s"Name $i", i))
}

@BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 1)
class MeinBenchmark {

  @Benchmark
  def testMap(state: MeinBenchmarkState): Map[Int, Person] =
    state.list.map(p => p.alter -> p).toMap
  @Benchmark
  def testZip(state: MeinBenchmarkState): Map[Int, Person] =
    state.list.map(_.alter).zip(state.list).toMap

}
