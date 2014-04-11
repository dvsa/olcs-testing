package olcs;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class auditObject {
  public void startVideo(final String reportDirectory, final String testRunIdentifier, final int videoPause) {
    try {
      final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss");
      Date _date = new Date();
      final String timeStamp = simpleDateFormat.format(_date);
      final File directory = new File((reportDirectory + testRunIdentifier));
      boolean _exists = directory.exists();
      boolean _not = (!_exists);
      if (_not) {
        directory.mkdirs();
      }
      final File file = new File(((((((reportDirectory + testRunIdentifier) + "//") + testRunIdentifier) + "_") + timeStamp) + ".bat"));
      boolean _exists_1 = file.exists();
      boolean _not_1 = (!_exists_1);
      if (_not_1) {
        File _file = new File(((((((reportDirectory + testRunIdentifier) + "//") + testRunIdentifier) + "_") + timeStamp) + ".bat"));
        Files.write(((((((("C:\\ARC\\base\\vlc-2.1.0\\vlc.exe screen:// --one-instance -I dummy --dummy-quiet --extraintf rc --rc-host localhost:1212 --rc-quiet --screen-follow-mouse --no-video :screen-fps=15 :screen-caching=300 --sout \"#transcode{vcodec=h264,vb=400,fps=4,scale=1,width=1024,height=768,acodec=none}:duplicate{dst=std{access=file,mux=mp4,dst=\'" + reportDirectory) + testRunIdentifier) + "//") + testRunIdentifier) + "_") + timeStamp) + ".mp4\'}}\""), _file, Charsets.UTF_8);
      }
      Thread.sleep(videoPause);
      Runtime _runtime = Runtime.getRuntime();
      _runtime.exec(((((((reportDirectory + testRunIdentifier) + "//") + testRunIdentifier) + "_") + timeStamp) + ".bat"));
      Thread.sleep(videoPause);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public Process stopVideo(final int videoPause) {
    try {
      Process _xblockexpression = null;
      {
        Thread.sleep(videoPause);
        Runtime _runtime = Runtime.getRuntime();
        _xblockexpression = _runtime.exec("C:\\ARC\\base\\stopVideo.bat");
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void accessibilityAuditFile(final String pluginIdentifier, final String testCaseUrl, final String testRunIdentifier, final String testRunIdentifierTimestamp, final String alertError, final String reportDirectory) {
    try {
      final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss");
      Date _date = new Date();
      final String timeStamp = simpleDateFormat.format(_date);
      Toolkit _defaultToolkit = Toolkit.getDefaultToolkit();
      Dimension _screenSize = _defaultToolkit.getScreenSize();
      final Rectangle screenRectangle = new Rectangle(_screenSize);
      Robot _robot = new Robot();
      final BufferedImage capture = _robot.createScreenCapture(screenRectangle);
      final File directory = new File((reportDirectory + testRunIdentifier));
      boolean _exists = directory.exists();
      boolean _not = (!_exists);
      if (_not) {
        directory.mkdirs();
      }
      File _file = new File(((((((((reportDirectory + testRunIdentifier) + "//") + testRunIdentifier) + "_") + timeStamp) + "_") + pluginIdentifier) + ".png"));
      ImageIO.write(capture, "png", _file);
      final File file = new File(((((((reportDirectory + testRunIdentifier) + "//") + testRunIdentifierTimestamp) + "_") + pluginIdentifier) + ".txt"));
      boolean _exists_1 = file.exists();
      if (_exists_1) {
        File _file_1 = new File(((((((reportDirectory + testRunIdentifier) + "//") + testRunIdentifierTimestamp) + "_") + pluginIdentifier) + ".txt"));
        Files.append((((((((((testCaseUrl + " | ") + alertError) + " | ") + testRunIdentifier) + "_") + timeStamp) + "_") + pluginIdentifier) + ".png\n"), _file_1, Charsets.UTF_8);
      }
      boolean _exists_2 = file.exists();
      boolean _not_1 = (!_exists_2);
      if (_not_1) {
        File _file_2 = new File(((((((reportDirectory + testRunIdentifier) + "//") + testRunIdentifierTimestamp) + "_") + pluginIdentifier) + ".txt"));
        Files.write((((((((((testCaseUrl + " | ") + alertError) + " | ") + testRunIdentifier) + "_") + timeStamp) + "_") + pluginIdentifier) + ".png\n"), _file_2, Charsets.UTF_8);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void surfaceAuditBaseline(final String testCaseUrl, final String testCaseIdentifier, final String currentInput, final String testIdentifier) {
    try {
      final File directory = new File(("C:\\ARC\\surface\\" + testIdentifier));
      boolean _exists = directory.exists();
      boolean _not = (!_exists);
      if (_not) {
        directory.mkdirs();
      }
      String _plus = (directory + "//");
      String _plus_1 = (_plus + testCaseIdentifier);
      String _plus_2 = (_plus_1 + ".txt");
      final File file = new File(_plus_2);
      boolean _exists_1 = file.exists();
      if (_exists_1) {
        String _plus_3 = (directory + "//");
        String _plus_4 = (_plus_3 + testCaseIdentifier);
        String _plus_5 = (_plus_4 + ".txt");
        File _file = new File(_plus_5);
        Files.append(("\n" + currentInput), _file, Charsets.UTF_8);
      }
      boolean _exists_2 = file.exists();
      boolean _not_1 = (!_exists_2);
      if (_not_1) {
        String _plus_6 = (directory + "//");
        String _plus_7 = (_plus_6 + testCaseIdentifier);
        String _plus_8 = (_plus_7 + ".txt");
        File _file_1 = new File(_plus_8);
        Files.write(((testCaseUrl + "\n") + currentInput), _file_1, Charsets.UTF_8);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void surfaceAuditCurrent(final String testCaseUrl, final String testCaseIdentifier, final String currentInput, final String reportDirectory, final String testRunIdentifier) {
    try {
      final File directory = new File((reportDirectory + testRunIdentifier));
      boolean _exists = directory.exists();
      boolean _not = (!_exists);
      if (_not) {
        directory.mkdirs();
      }
      String _plus = (directory + "//");
      String _plus_1 = (_plus + testCaseIdentifier);
      String _plus_2 = (_plus_1 + ".txt");
      final File file = new File(_plus_2);
      boolean _exists_1 = file.exists();
      if (_exists_1) {
        String _plus_3 = (directory + "//");
        String _plus_4 = (_plus_3 + testCaseIdentifier);
        String _plus_5 = (_plus_4 + ".txt");
        File _file = new File(_plus_5);
        Files.append(("\n" + currentInput), _file, Charsets.UTF_8);
      }
      boolean _exists_2 = file.exists();
      boolean _not_1 = (!_exists_2);
      if (_not_1) {
        String _plus_6 = (directory + "//");
        String _plus_7 = (_plus_6 + testCaseIdentifier);
        String _plus_8 = (_plus_7 + ".txt");
        File _file_1 = new File(_plus_8);
        Files.write(((testCaseUrl + "\n") + currentInput), _file_1, Charsets.UTF_8);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public String surfaceAuditCompare(final String testCaseIdentifier, final String reportDirectory, final String testRunIdentifier, final String testIdentifier) {
    try {
      String _xblockexpression = null;
      {
        final String baselineDirectory = ("C:\\ARC\\surface\\" + testIdentifier);
        final File baselineFile = new File((((baselineDirectory + "//") + testCaseIdentifier) + ".txt"));
        String _xifexpression = null;
        boolean _exists = baselineFile.exists();
        if (_exists) {
          _xifexpression = Files.toString(baselineFile, Charsets.UTF_8);
        } else {
          _xifexpression = "Nothing to compare";
        }
        final String baselineFileString = _xifexpression;
        final String compareDirectory = (reportDirectory + testRunIdentifier);
        final File compareFile = new File((((compareDirectory + "//") + testCaseIdentifier) + ".txt"));
        String _xifexpression_1 = null;
        boolean _exists_1 = compareFile.exists();
        if (_exists_1) {
          _xifexpression_1 = Files.toString(compareFile, Charsets.UTF_8);
        } else {
          _xifexpression_1 = "Nothing to compare";
        }
        final String compareFileString = _xifexpression_1;
        String _xifexpression_2 = null;
        boolean _equals = baselineFileString.equals(compareFileString);
        if (_equals) {
          _xifexpression_2 = InputOutput.<String>print("Pass\n");
        } else {
          _xifexpression_2 = InputOutput.<String>print("Fail\n");
        }
        _xblockexpression = _xifexpression_2;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
