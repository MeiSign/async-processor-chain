package core

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ports.AsyncProcessor
import ports.ProcessorResult

internal class ProcessorChainTest {

  private val relevantProcessor: AsyncProcessor = mock {
    on { isRelevant(any()) } doReturn true
  }
  private val irrelevantProcessor: AsyncProcessor = mock {
    on { isRelevant(any()) } doReturn false
  }
  private val processorChain =
    ProcessorChain(listOf(irrelevantProcessor, relevantProcessor))

  @Test
  fun `should call process on relevant processors`(): Unit = runBlocking {
    ProcessorResult("relevant", true).let { expectedResult ->
      whenever(relevantProcessor.process(any())).thenReturn(expectedResult)

      assertThat(processorChain.run("abc")).isEqualTo(listOf(expectedResult))

      verify(relevantProcessor).process("abc")
      verify(irrelevantProcessor, times(0)).process("abc")
    }
  }
}
