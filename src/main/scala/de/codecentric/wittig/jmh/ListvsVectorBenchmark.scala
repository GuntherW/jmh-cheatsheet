package de.codecentric.wittig.jmh

import org.openjdk.jmh.annotations.{Scope, State, _}

@State(Scope.Thread)
class ListvsVectorState2 {
  val list: List[User]     = List.tabulate(800)(i => User(s"vorname.name$i", s"id$i"))
  val vector: Vector[User] = Vector.tabulate(800)(i => User(s"vorname.name$i", s"id$i"))
}

/**  Run via `jmh:run   .*ListvsVectorBenchmark*`
  */
@BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 1)
class ListvsVectorBenchmark {

  @Benchmark
  def testFindList(state: ListvsVectorState2): Seq[User] =
    state.list.map(u => u.copy(name = u.name + "_suffix"))

  @Benchmark
  def testFindVector(state: ListvsVectorState2): Seq[User] =
    state.vector.map(u => u.copy(name = u.name + "_suffix"))

}
