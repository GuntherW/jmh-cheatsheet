package de.codecentric.wittig.jmh

import org.openjdk.jmh.annotations.{Scope, State, _}
import scala.collection.parallel.CollectionConverters._

case class Category(eins: Option[Int], zwei: String)

@State(Scope.Thread)
class ListState {
  val list: List[Category] = List.tabulate(100000) { i =>
    val modulo = i % 10
    val eins   = if (modulo == 0) None else Some(modulo)
    Category(eins, s"hallo $i")
  }
}

@BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 2)
class View2Benchmark {

  @Benchmark
  def test1(state: ListState): Seq[(Int, Int)] =
    state.list
      .filter(_.eins.isDefined)
      .groupBy(_.eins.get)
      .map { case (key, value) => key -> value.size }
      .toList

  @Benchmark
  def test1Parallel(state: ListState): Seq[(Int, Int)] =
    state.list.par
      .filter(_.eins.isDefined)
      .groupBy(_.eins.get)
      .map { case (key, value) => key -> value.size }
      .toList

  @Benchmark
  def test2(state: ListState): Seq[(Int, Int)] =
    state.list.view
      .filter(_.eins.isDefined)
      .groupBy(_.eins.get)
      .view
      .mapValues(_.toList)
      .toMap
      .map { case (key, value) => key -> value.size }
      .toList

  @Benchmark
  def test3(state: ListState): Seq[(Int, Int)] =
    state.list
      .groupBy(_.eins)
      .collect { case (Some(key), value) => key -> value.size }
      .toList

}
