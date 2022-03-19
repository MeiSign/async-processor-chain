package core

import kotlinx.coroutines.*
import ports.AsyncProcessor
import ports.ProcessorResult

class ProcessorChain(private val processors: List<AsyncProcessor>) {
  fun run(someDto: String): List<ProcessorResult> {
    return runBlocking(Dispatchers.IO) {
      processors
        .filter { processor -> processor.isRelevant(someDto) }
        .map { processor ->
          async {
            runCatching {
              processor.process(someDto)
            }.getOrElse { failure ->
              println("Processor [${processor.processorName}] failed [${failure.message}]")
              ProcessorResult(processorName = processor.processorName, false)
            }
          }
        }
        .awaitAll()
    }
  }
}
