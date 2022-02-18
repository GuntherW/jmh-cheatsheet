package de.codecentric.wittig.jmh

import scala.util.Try

import org.openjdk.jmh.annotations.{Benchmark, Fork, Measurement, Threads, Warmup}

/** https://softwaremill.com/fast-number-parsing-in-scala/
  */
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(value = 1)
@Threads(value = 1)
class IntParsing {

  @Benchmark
  def usingTry: Int = Try("abc".toInt).getOrElse(0)

  @Benchmark
  def usingCatch: Int =
    try "abc".toInt
    catch {
      case _: Exception => 0
    }

  @Benchmark
  def usingOption: Int = "abc".toIntOption.getOrElse(0) // 500x faster
}
