package olcs

import java.io.FileReader
import static extension com.google.common.io.CharStreams.*

class suiteObject {
  public val suiteObjects = new FileReader("src/test/resources/test.suites").readLines.map [ line |
    val fields = line.split('  ').iterator
      return new suites (
        fields.next,
        fields.next, 
        fields.next,
        fields.next,
        fields.next,
        Integer.parseInt(fields.next),
        fields.next, 
        fields.next,
        Integer.parseInt(fields.next),
        fields.next,
        fields.next,
        fields.next
      )
  ]
}

@Data class suites {
  String testCases
  String browserList
  String hubList
  String mapSurface
  String recordVideo
  int videoPause
  String runSecurity
  String runAccessibility
  int accessibilityPause
  String groupRunIdentifier
  String reportDirectory
  String includeGroup
}