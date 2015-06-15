package olcs

import java.io.FileReader
import static extension com.google.common.io.CharStreams.*

class journeyObject {
    public val journeyObjects = new FileReader('src/test/resources/journey.model').readLines.map [ line |
        val fields = line.split('  ').iterator
        return new journey(
            fields.next,
            fields.next,
            fields.next,
            fields.next,
            fields.next,
            fields.next,
            Integer.parseInt(fields.next),
            fields.next
        )
    ]
}

@Data class journey {
    String jtcBddIdentifier
    String jtcAction
    String jtcNameElement
    String jtcByElement
    String jtcInputCheck
    String jtcTitle
    int jtcPause
    String jtcComments
}