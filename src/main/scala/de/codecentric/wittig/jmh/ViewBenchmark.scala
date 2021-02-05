package de.codecentric.wittig.jmh

import java.time.LocalDate

import org.openjdk.jmh.annotations.{Scope, State, _}

@State(Scope.Thread)
class ViewState {
  val now                   = LocalDate.now
  val count                 = 10000000
  val list: List[LocalDate] = (1 to count).toList.map(i => now.plusDays(i))
}

@BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 1)
class ViewBenchmark {

//  // list.find is faster
//  @Benchmark
//  def testFindList(state: ViewState): Option[LocalDate] =
//    state.list.find(_ == state.now.minusDays(state.count / 2))
//  @Benchmark
//  def testFindView(state: ViewState): Option[LocalDate] =
//    state.list.view.find(_ == state.now.minusDays(state.count / 2))
//
//  @Benchmark
//  def testMappedFindList(state: ViewState): Option[LocalDate] =
//    state.list.map(_.plusDays(1)).find(_ == state.now.minusDays(state.count / 2))
//  // map.view is faster
//  @Benchmark
//  def testMappedFindView(state: ViewState): Option[LocalDate] =
//    state.list.view.map(_.plusDays(1)).find(_ == state.now.minusDays(state.count / 2))

  @Benchmark
  def testToMap(state: ViewState): Map[Int, LocalDate]     =
    state.list.map(ld => (ld.getDayOfYear, ld)).toMap
  // faster
  @Benchmark
  def testToMapView(state: ViewState): Map[Int, LocalDate] =
    state.list.view.map(ld => (ld.getDayOfYear, ld)).toMap
}
