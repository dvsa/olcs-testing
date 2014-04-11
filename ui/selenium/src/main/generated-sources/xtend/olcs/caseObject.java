package olcs;

import com.google.common.io.CharStreams;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import olcs.cases;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class caseObject {
  public final List<cases> caseObjects = new Function0<List<cases>>() {
    public List<cases> apply() {
      try {
        FileReader _fileReader = new FileReader("src/test/resources/test.cases");
        List<String> _readLines = CharStreams.readLines(_fileReader);
        final Function1<String,cases> _function = new Function1<String,cases>() {
          public cases apply(final String line) {
            String[] _split = line.split("  ");
            final Iterator<String> fields = ((List<String>)Conversions.doWrapArray(_split)).iterator();
            String _next = fields.next();
            String _next_1 = fields.next();
            String _next_2 = fields.next();
            String _next_3 = fields.next();
            String _next_4 = fields.next();
            String _next_5 = fields.next();
            return new cases(_next, _next_1, _next_2, _next_3, _next_4, _next_5);
          }
        };
        List<cases> _map = ListExtensions.<String, cases>map(_readLines, _function);
        return _map;
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
  }.apply();
}
