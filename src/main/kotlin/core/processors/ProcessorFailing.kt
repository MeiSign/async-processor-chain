package core.processors

import kotlinx.coroutines.delay
import ports.AsyncProcessor
import ports.ProcessorResult

class ProcessorFailing: AsyncProcessor {
  override val processorName: String = "Failing Processor"

  override fun isRelevant(someDto: String): Boolean =
    someDto.contains("123")

  override suspend fun process(someDto: String): ProcessorResult {
    delay(1500)
    throw IllegalStateException("This one is not working")
  }
}
