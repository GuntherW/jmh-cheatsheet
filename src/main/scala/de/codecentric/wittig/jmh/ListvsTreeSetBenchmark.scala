package de.codecentric.wittig.jmh

import java.time.LocalDate

import org.openjdk.jmh.annotations.{Scope, State, _}

import scala.collection.immutable.TreeSet

@State(Scope.Thread)
class ListvsTreeSetState {
  val now: LocalDate              = LocalDate.now
  val count: Int                  = 10000000
  val list: List[LocalDate]       = (1 to count).view.map(i => now.plusDays(i)).toList
  val treeSet: TreeSet[LocalDate] = (1 to count).view.map(i => now.plusDays(i)).to(TreeSet)
  val dateFrom: LocalDate         = now.plusDays(count / 4)
  val dateTo: LocalDate           = dateFrom.plusDays(count / 4)
}

@BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 1)
class ListvsTreeSetBenchmark {

  @Benchmark
  def testFilterList(state: ListvsTreeSetState): List[LocalDate] =
    state.list.filter(ld => !ld.isBefore(state.dateFrom) && !ld.isAfter(state.dateTo))
  @Benchmark
  def testFilterTreeSet(state: ListvsTreeSetState): List[LocalDate] =
    state.treeSet.view.filter(ld => !ld.isBefore(state.dateFrom) && !ld.isAfter(state.dateTo)).toList
  @Benchmark
  def testFilterTreeSetWithRange(state: ListvsTreeSetState): List[LocalDate] =
    state.treeSet.range(state.dateFrom, state.dateTo).toList
}
