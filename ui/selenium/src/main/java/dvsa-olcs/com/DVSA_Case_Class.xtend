package olcs

import java.io.FileReader
import static extension com.google.common.io.CharStreams.*

class caseObject {
  public val caseObjects = new FileReader("src/test/resources/test.cases").readLines.map [ line |
    val fields = line.split('  ').iterator
      return new cases (
        fields.next,
        fields.next,
  	    fields.next,
        fields.next,
        fields.next,
        fields.next,
        fields.next,
        fields.next
      )
  ]
}

@Data class cases {
  String bddIdentifier
  String bddScenario
  String bddSprint
  String bddType
  String bddTitle
  String bddStory
  String startPoint
  String bddSequence
}