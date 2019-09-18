package de.codecentric.wittig.jmh

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Mode}
import org.openjdk.jmh.infra.Blackhole

class ExampleBenchmark {

  @Benchmark
  def view(): Int =
    1.to(100000)
      .view
      .filter(_ % 2 == 0)
      .count(_.toString.length == 4)

  @Benchmark @BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
  def range(): Int =
    1.to(100000)
      .filter(_ % 2 == 0)
      .count(_.toString.length == 4)

  @Benchmark @BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
  def iterator(): Int =
    Iterator
      .from(1)
      .takeWhile(_ < 100000)
      .filter(_ % 2 == 0)
      .count(_.toString.length == 4)

  @Benchmark
  def avoidDeadCodeElemination(blackhole: Blackhole): Unit = {
    val c = 1 + 2
    blackhole.consume(c)
  }
}
