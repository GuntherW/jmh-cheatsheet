package de.codecentric.wittig.jmh

import java.time.LocalDate

import org.openjdk.jmh.annotations.{Scope, State, _}

import scala.collection.immutable.TreeSet
import cats.implicits._

@State(Scope.Thread)
class FmapVsMapValuesState {
  val now: LocalDate           = LocalDate.now
  val count: Int               = 10000000
  val map: Map[Int, LocalDate] = (1 to count).view.map(i => (1, now.plusDays(i))).toMap
  val list: List[LocalDate]    = (1 to count).view.map(i => now.plusDays(i)).toList
}

@BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 1)
class FmapVsMapValuesBenchmark {

  @Benchmark
  def testFmap(state: FmapVsMapValuesState): Map[Int, LocalDate]      =
    state.map.fmap(_.plusDays(1))
  @Benchmark
  def testMapValues(state: FmapVsMapValuesState): Map[Int, LocalDate] =
    state.map.view.mapValues(_.plusDays(1)).toMap

  @Benchmark
  def test1(state: FmapVsMapValuesState): Int = {
    state.list
      .groupByNel(_.getMonth.getValue)
      .foldMap(_.map(_.getDayOfYear).maximum)
  }
  @Benchmark
  def test2(state: FmapVsMapValuesState): Int = {
    state.list.map(_.getMonth.getValue).max
  }
}
