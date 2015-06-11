package olcs;

import com.google.common.io.CharStreams;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import olcs.journey;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class journeyObject {
  public final List<journey> journeyObjects = new Function0<List<journey>>() {
    public List<journey> apply() {
      try {
        FileReader _fileReader = new FileReader("src/test/resources/journey.model");
        List<String> _readLines = CharStreams.readLines(_fileReader);
        final Function1<String,journey> _function = new Function1<String,journey>() {
          public journey apply(final String line) {
            String[] _split = line.split("  ");
            final Iterator<String> fields = ((List<String>)Conversions.doWrapArray(_split)).iterator();
            String _next = fields.next();
            String _next_1 = fields.next();
            String _next_2 = fields.next();
            String _next_3 = fields.next();
            String _next_4 = fields.next();
            String _next_5 = fields.next();
            String _next_6 = fields.next();
            int _parseInt = Integer.parseInt(_next_6);
            String _next_7 = fields.next();
            return new journey(_next, _next_1, _next_2, _next_3, _next_4, _next_5, _parseInt, _next_7);
          }
        };
        List<journey> _map = ListExtensions.<String, journey>map(_readLines, _function);
        return _map;
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
  }.apply();
}
