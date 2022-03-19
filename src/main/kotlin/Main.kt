import core.ProcessorChain
import core.processors.ProcessorA
import core.processors.ProcessorB
import core.processors.ProcessorC
import core.processors.ProcessorFailing
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
  val processors = listOf(ProcessorFailing(), ProcessorA(), ProcessorB(), ProcessorC())
  val processorChain = ProcessorChain(processors)

  println("Starting to process")
  val time = measureTimeMillis {
    val result = processorChain.run("Test123")
    println("Results: $result")
  }

  println("Measured Time in millis: $time")
}
