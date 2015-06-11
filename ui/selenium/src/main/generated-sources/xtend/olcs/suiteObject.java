package olcs;

import com.google.common.io.CharStreams;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import olcs.suites;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class suiteObject {
  public final List<suites> suiteObjects = new Function0<List<suites>>() {
    public List<suites> apply() {
      try {
        FileReader _fileReader = new FileReader("src/test/resources/test.suites");
        List<String> _readLines = CharStreams.readLines(_fileReader);
        final Function1<String,suites> _function = new Function1<String,suites>() {
          public suites apply(final String line) {
            String[] _split = line.split("  ");
            final Iterator<String> fields = ((List<String>)Conversions.doWrapArray(_split)).iterator();
            String _next = fields.next();
            String _next_1 = fields.next();
            String _next_2 = fields.next();
            String _next_3 = fields.next();
            int _parseInt = Integer.parseInt(_next_3);
            String _next_4 = fields.next();
            String _next_5 = fields.next();
            String _next_6 = fields.next();
            int _parseInt_1 = Integer.parseInt(_next_6);
            String _next_7 = fields.next();
            String _next_8 = fields.next();
            String _next_9 = fields.next();
            return new suites(_next, _next_1, _next_2, _parseInt, _next_4, _next_5, _parseInt_1, _next_7, _next_8, _next_9);
          }
        };
        List<suites> _map = ListExtensions.<String, suites>map(_readLines, _function);
        return _map;
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
  }.apply();
}
