package olcs

import java.io.FileReader
import static extension com.google.common.io.CharStreams.*

class validationObject {
  public val validationObjects = new FileReader('src/test/resources/validation.inputs').readLines.map [ line |
    val fields = line.split('  ').iterator
      return new validation (
        fields.next,
        fields.next,
        Integer.parseInt(fields.next),
        Integer.parseInt(fields.next),
        fields.next,
        fields.next,
        fields.next
      )
  ]
}

@Data class validation {
  String validationIdentifier
  String mandatoryInput
  int minimumLength
  int maximumLength
  String inputType
  String dropdownWhitelist
  String validationComments
}