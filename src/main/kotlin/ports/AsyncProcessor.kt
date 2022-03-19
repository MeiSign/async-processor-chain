package ports

interface AsyncProcessor {
  val processorName: String
  fun isRelevant(someDto: String): Boolean
  suspend fun process(someDto: String):  ProcessorResult
}
