package core.processors

import kotlinx.coroutines.delay
import ports.AsyncProcessor
import ports.ProcessorResult

class ProcessorA : AsyncProcessor {
  override val processorName: String = "A"

  override fun isRelevant(someDto: String): Boolean =
    someDto.contains("123")

  override suspend fun process(someDto: String): ProcessorResult {
    delay(3000)
    return ProcessorResult(processorName, true)
  }
}
