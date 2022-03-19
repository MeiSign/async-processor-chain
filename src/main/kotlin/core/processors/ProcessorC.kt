package core.processors

import kotlinx.coroutines.delay
import ports.AsyncProcessor
import ports.ProcessorResult

class ProcessorC : AsyncProcessor {
  override val processorName: String = "C"

  override fun isRelevant(someDto: String): Boolean =
    someDto.contains("Test")

  override suspend fun process(someDto: String): ProcessorResult {
    delay(3000)
    return ProcessorResult(processorName, true)
  }
}
