package dft.olcs;

import com.google.common.base.Charsets;
import com.google.common.base.Objects;
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
        Files.write(((((((("C:\\olcs_backup\\vlc-2.1.0\\vlc.exe screen:// --one-instance -I dummy --dummy-quiet --extraintf rc --rc-host localhost:1212 --rc-quiet --screen-follow-mouse --no-video :screen-fps=15 :screen-caching=300 --sout \"#transcode{vcodec=h264,vb=400,fps=4,scale=1,width=1024,height=768,acodec=none}:duplicate{dst=std{access=file,mux=mp4,dst=\'" + reportDirectory) + testRunIdentifier) + "//") + testRunIdentifier) + "_") + timeStamp) + ".mp4\'}}\""), _file, Charsets.UTF_8);
      }
      Thread.sleep(videoPause);
      Runtime _runtime = Runtime.getRuntime();
      _runtime.exec(((((((reportDirectory + testRunIdentifier) + "//") + testRunIdentifier) + "_") + timeStamp) + ".bat"));
      Thread.sleep(videoPause);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public Process stopVideo(final String reportDirectory, final String testRunIdentifier, final int videoPause) {
    try {
      Process _xblockexpression = null;
      {
        Thread.sleep(videoPause);
        Runtime _runtime = Runtime.getRuntime();
        _xblockexpression = (_runtime.exec("C:\\olcs_backup\\stopVideo.bat"));
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void accessibilityAuditFile(final String pluginIdentifier, final String testCaseUrl, final String testRunIdentifier, final String testRunIdentifierTimestamp, final String alertError, final String reportDirectory, final String accessibilityCaptureImage) {
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
      boolean _equals = Objects.equal(accessibilityCaptureImage, "Yes");
      if (_equals) {
        File _file = new File(((((((((reportDirectory + testRunIdentifier) + "//") + testRunIdentifier) + "_") + timeStamp) + "_") + pluginIdentifier) + ".png"));
        ImageIO.write(capture, "png", _file);
      }
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
}
