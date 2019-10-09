package de.codecentric.wittig.jmh

import java.util
import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations.{Level, Scope, Setup, State, _}

@State(Scope.Thread)
class ArrayListState {
  @Param(Array("1", "100", "1000", "10000", "100000"))
  var size: Int = _

  @Setup(Level.Invocation)
  def doSetup(): Unit = {
    arrayList = new util.ArrayList[Int](size)
    var i: Int = 0
    while (i < size) {
      arrayList.add(i)
      i += 1
    }
  }

  var arrayList: util.ArrayList[Int] = _
}

@State(Scope.Thread)
class LinkedListState {
  @Param(Array("1", "100", "1000", "10000", "100000"))
  var size: Int = _

  @Setup(Level.Invocation)
  def doSetup(): Unit = {
    linkedList = new util.LinkedList[Int]
    var i: Int = 0
    while (i < size) {
      linkedList.add(i)
      i += 1
    }
  }

  var linkedList: util.LinkedList[Int] = _
}

/**
  *  Run via `jmh:run -i 2 -wi 3 -f1 -t1 .*ExampleWithStateBenchmark*`
  */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2)
@Measurement(iterations = 2)
@Fork(value = 1)
@Threads(value = 1)
class ExampleWithStateBenchmark {

  @Benchmark
  def insertToTheMiddle_ArrayList(state: ArrayListState): Unit = {
    val size   = state.arrayList.size()
    val offset = size / 2
    state.arrayList.add(offset, 42)
  }

  @Benchmark
  def unsertToTheMiddle_LinkedList(state: LinkedListState): Unit = {
    val size   = state.linkedList.size()
    val offset = size / 2
    state.linkedList.add(offset, 42)
  }

  @Benchmark
  def prepend_ArrayList(state: ArrayListState): Unit =
    state.arrayList.add(0, 42)

  @Benchmark
  def prepend_LinkedList(state: LinkedListState): Unit =
    state.linkedList.add(0, 42)
}
