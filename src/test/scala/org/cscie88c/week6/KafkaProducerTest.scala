package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class KafkaProducerTest extends StandardTest {
  "KafkaClient" should {
    "send a message to the default topic" in {
      // add your unit tests for KafkaClient.send here
      val status = "status-update"
      val result = KafkaClient.sendStatusEvent(status)
      result should include("default-topic")
      result should include("status-update")
    }
  }
}
 