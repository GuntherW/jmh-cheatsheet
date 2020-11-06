## How to microbenchmark

```
z.B.
sbt:jmh-cheatsheet> `jmh:run -i 2 -wi 2 -f1 -t1 .*ExampleBenchmark.*`
oder
sbt:jmh-cheatsheet> `jmh:run  .*ExampleWithSimpleStateBenchmark.*` // alle Parameter via Annotationen gesetzt
```

##### Erklärung:
     -i 3  => 3 Iterationen
     -wi 3 => 3 Warmup Iterationen
     -f1   => 1 Fork
     -t1   => 1 Thread

>For "real" results we recommend to at least warm up 10 to 20 iterations, and then measure 10 to 20 iterations again. Forking the JVM is required to avoid falling into specific optimisations (no JVM optimisation is really "completely" predictable)

##### Aufgepasst:
###### Dead Code Elemination
Damit die JVM nicht an der falschen Stelle optimiert, sollte die gebenchmarkte Methode einen Wert zurückgeben, oder via Blackhole den Wert konsumieren. Siehe [ExampleBenchmark.scala](src/main/scala/de/codecentric/wittig/jmh/ExampleBenchmark.scala)
###### Methodenbenamung
In den Ergebnissen werden von jmh alle Testmethode alphabetisch sortiert untereinander angezeigt.
Zur besseren Übersicht empfielt es sich also, daß man zwei Methoden, die man miteinander vergleichen möchte, lediglich im Suffix unterscheiden läßt.


## Referenzen:
* https://github.com/ktoso/sbt-jmh
* http://tutorials.jenkov.com/java-performance/jmh.html#running-your-jmh-benchmarks