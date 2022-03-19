package core.processors

import kotlinx.coroutines.delay
import ports.AsyncProcessor
import ports.ProcessorResult

class ProcessorB : AsyncProcessor {
  override val processorName: String = "B"

  override fun isRelevant(someDto: String): Boolean =
    someDto.contains("Bla")

  override suspend fun process(someDto: String): ProcessorResult {
    delay(3000)
    return ProcessorResult(processorName, false)
  }
}
