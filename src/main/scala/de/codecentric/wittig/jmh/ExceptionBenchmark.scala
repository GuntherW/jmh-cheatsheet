package de.codecentric.wittig.jmh

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Fork, Measurement, Mode, Scope, State, Threads, Warmup}

import scala.collection.mutable
import scala.util.matching.Regex

/**
  *  Run via `jmh:run   .*ExampleWithSimpleStateBenchmark*`
  */
@BenchmarkMode(Array(Mode.AverageTime, Mode.Throughput))
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 1)
class ExceptionBenchmark {

  @Benchmark
  def WithException(): List[Option[Int]] = {
    val set  = new mutable.HashMap[Int, Int]()
    val list = List.tabulate(100)(identity)

    list.map { i =>
      try {
        Option(set(i))
      } catch {
        case e: Throwable => {
          set.put(i, i)
          None
        }
      }
    }
  }

  @Benchmark
  def WithOutException(): List[Option[Int]] = {
    val set  = new mutable.HashMap[Int, Int]()
    val list = List.tabulate(100)(identity)

    list.map { i =>
      if (set.toList.exists(_._1 == i))
        Some(i)
      else {
        set.put(1, 1)
        None
      }
    }
  }
}
