package dft.olcs

import java.awt.Rectangle
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.awt.Robot
import javax.imageio.ImageIO
import java.io.File
import java.util.Date
import java.text.SimpleDateFormat
import com.google.common.io.Files
import com.google.common.base.Charsets

class auditObject {

def startVideo(String reportDirectory, String testRunIdentifier, int videoPause) {
  val simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss")
  val timeStamp = simpleDateFormat.format(new Date())
  val directory = new File(reportDirectory+testRunIdentifier)
  if (!directory.exists) directory.mkdirs
  val file = new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifier+"_"+timeStamp+".bat")
  if (!file.exists) Files.write("C:\\olcs_backup\\vlc-2.1.0\\vlc.exe screen:// --one-instance -I dummy --dummy-quiet --extraintf rc --rc-host localhost:1212 --rc-quiet --screen-follow-mouse --no-video :screen-fps=15 :screen-caching=300 --sout \"#transcode{vcodec=h264,vb=400,fps=4,scale=1,width=1024,height=768,acodec=none}:duplicate{dst=std{access=file,mux=mp4,dst=\'"+reportDirectory+testRunIdentifier+"//"+testRunIdentifier+"_"+timeStamp+".mp4\'}}\"", new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifier+"_"+timeStamp+".bat"), Charsets.UTF_8)
  Thread.sleep(videoPause)
  Runtime.getRuntime().exec(reportDirectory+testRunIdentifier+"//"+testRunIdentifier+"_"+timeStamp+".bat")
  Thread.sleep(videoPause)
}

def stopVideo(String reportDirectory, String testRunIdentifier, int videoPause) {
  Thread.sleep(videoPause)
  Runtime.getRuntime().exec("C:\\olcs_backup\\stopVideo.bat")
}

def accessibilityAuditFile(String pluginIdentifier, String testCaseUrl, String testRunIdentifier, String testRunIdentifierTimestamp, String alertError, String reportDirectory, String accessibilityCaptureImage) {
  val simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss")
  val timeStamp = simpleDateFormat.format(new Date())
  val screenRectangle = new Rectangle(Toolkit.getDefaultToolkit.getScreenSize)
  val BufferedImage capture = new Robot().createScreenCapture(screenRectangle)
  val directory = new File(reportDirectory+testRunIdentifier)
  if (!directory.exists) directory.mkdirs
  if (accessibilityCaptureImage == "Yes") {
    ImageIO.write(capture, "png", new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifier+"_"+timeStamp+"_"+pluginIdentifier+".png"))
  }
  val file = new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifierTimestamp+"_"+pluginIdentifier+".txt")
  if (file.exists) Files.append(testCaseUrl+" | "+alertError+" | "+testRunIdentifier+"_"+timeStamp+"_"+pluginIdentifier+".png\n", new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifierTimestamp+"_"+pluginIdentifier+".txt"), Charsets.UTF_8)
  if (!file.exists) Files.write(testCaseUrl+" | "+alertError+" | "+testRunIdentifier+"_"+timeStamp+"_"+pluginIdentifier+".png\n", new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifierTimestamp+"_"+pluginIdentifier+".txt"), Charsets.UTF_8)
}

}

