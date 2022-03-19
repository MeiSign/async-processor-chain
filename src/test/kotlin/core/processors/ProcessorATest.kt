package core.processors

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import ports.ProcessorResult

internal class ProcessorATest {

  private val processor = ProcessorA()

  @ParameterizedTest
  @CsvSource(
    value = [
      "123,true",
      "abc,false",
      "abc123,true",
      "a1b2c3,false",
    ]
  )
  fun `should be relevant for strings containing 123`(
    someDto: String,
    relevant: Boolean
  ) {
    assertThat(processor.isRelevant(someDto)).isEqualTo(relevant)
  }

  @Test
  fun `should process some dto`() = runBlocking {
    assertThat(processor.process("someDto123"))
      .isEqualTo(ProcessorResult("A", true))
  }
}
