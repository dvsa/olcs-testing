package dft.olcs

import java.io.FileReader
import static extension com.google.common.io.CharStreams.*

class journeyObject {

public val journeyObjects = new FileReader('journeys.csv').readLines.map [ line |
    val fields = line.split('  ').iterator
      return new journey (
  	  fields.next,
      fields.next, 
      fields.next,
      fields.next,
      fields.next,
      fields.next,
      fields.next,
      Integer.parseInt(fields.next)
    )
]

}

@Data class journey {
  String jtcBddIdentifier
  String jtcTitle
  String jtcAction
  String jtcNameElement
  String jtcByElement
  String jtcInputCheck
  String jtcInclude
  int jtcPause
}