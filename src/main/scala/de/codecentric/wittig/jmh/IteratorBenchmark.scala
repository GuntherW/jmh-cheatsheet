package de.codecentric.wittig.jmh

import java.time.LocalDate

import org.openjdk.jmh.annotations._
import scala.jdk.CollectionConverters._

@State(Scope.Thread)
class IteratorState {
  val start: LocalDate = LocalDate.now().minusYears(1)
  val end: LocalDate   = LocalDate.now()
}

/**  Run via jmh:run .*IteratorBenchmark.*
  */
@BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 1)
class IteratorBenchmark {

  @Benchmark
  def iterator1(state: IteratorState): Set[LocalDate] =
    Iterator
      .iterate(state.start)(_.plusDays(1))
      .takeWhile(!_.isAfter(state.end))
      .toSet

  @Benchmark
  def iterator2(state: IteratorState): Set[LocalDate] =
    state.start.datesUntil(state.end).iterator.asScala.toSet

}
