package de.codecentric.wittig.jmh

import java.time.LocalDate

import org.openjdk.jmh.annotations.{Scope, State, _}
@State(Scope.Thread)
class ListvsSetState {
  val now                   = LocalDate.now
  val count                 = 10000000
  val list: List[LocalDate] = (1 to count).toList.map(i => now.plusDays(i))
  val set: Set[LocalDate]   = (1 to count).toList.map(i => now.plusDays(i)).toSet
}

case class User(name: String, id: String)
@State(Scope.Thread)
class ListvsSetState2 {
  val searchName             = "vorname.name600"
  val searchId               = "id600"
  val list: List[User]       = List.tabulate(800)(i => User(s"vorname.name$i", s"id$i"))
  val set: Set[User]         = Set.tabulate(800)(i => User(s"vorname.name$i", s"id$i"))
  val map: Map[String, User] =
    (0 until 800).flatMap(i => Map(s"vorname.name$i" -> User(s"vorname.name$i", s"id$i"), s"id$i" -> User(s"vorname.name$i", s"id$i"))).toMap
}

/**  Run via `jmh:run   .*ExampleWithSimpleStateBenchmark*`
  */
@BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 1)
class ListvsSetBenchmark {
//
//  // Set is much faster with "contains"
//  @Benchmark
//  def testContainsList(state: ListvsSetState): Boolean =
//    state.list.contains(state.now.minusDays(1))
//
//  @Benchmark
//  def testContainsSet(state: ListvsSetState): Boolean =
//    state.set.contains(state.now.minusDays(1))
//
//  // List is faster with "filter"
//  @Benchmark
//  def testFilterList(state: ListvsSetState): List[LocalDate] =
//    state.list.filter(_.isBefore(state.now.plusDays(state.count / 2)))
//
//  @Benchmark
//  def testFilterSet(state: ListvsSetState): Set[LocalDate] =
//    state.set.filter(_.isBefore(state.now.plusDays(state.count / 2)))

  @Benchmark
  def testFindList(state: ListvsSetState2): Option[User] =
    state.list.find(u => u.name == state.searchName || u.id == state.searchId)

  @Benchmark
  def testFindSet(state: ListvsSetState2): Option[User] =
    state.set.find(u => u.name == state.searchName || u.id == state.searchId)

  @Benchmark
  def testFindMap(state: ListvsSetState2): Option[User] =
    state.map.get(state.searchName).orElse(state.map.get(state.searchId))
}
