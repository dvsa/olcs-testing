package olcs

import com.google.common.base.Charsets
import com.google.common.io.Files
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import javax.imageio.ImageIO

class auditObject {

def startVideo(String reportDirectory, String testRunIdentifier, int videoPause) {
  val simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss")
  val timeStamp = simpleDateFormat.format(new Date())
  val directory = new File(reportDirectory+testRunIdentifier)
  if (!directory.exists) directory.mkdirs
  val file = new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifier+"_"+timeStamp+".bat")
  if (!file.exists) Files.write("C:\\ARC\\base\\vlc-2.1.0\\vlc.exe screen:// --one-instance -I dummy --dummy-quiet --extraintf rc --rc-host localhost:1212 --rc-quiet --screen-follow-mouse --no-video :screen-fps=15 :screen-caching=300 --sout \"#transcode{vcodec=h264,vb=400,fps=4,scale=1,width=1024,height=768,acodec=none}:duplicate{dst=std{access=file,mux=mp4,dst=\'"+reportDirectory+testRunIdentifier+"//"+testRunIdentifier+"_"+timeStamp+".mp4\'}}\"", new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifier+"_"+timeStamp+".bat"), Charsets.UTF_8)
  Thread.sleep(videoPause)
  Runtime.getRuntime().exec(reportDirectory+testRunIdentifier+"//"+testRunIdentifier+"_"+timeStamp+".bat")
  Thread.sleep(videoPause)
}

def stopVideo(int videoPause) {
  Thread.sleep(videoPause)
  Runtime.getRuntime().exec("C:\\ARC\\base\\stopVideo.bat")
}

def accessibilityAuditFile(String pluginIdentifier, String testCaseUrl, String testRunIdentifier, String testRunIdentifierTimestamp, String alertError, String reportDirectory) {
  val simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss")
  val timeStamp = simpleDateFormat.format(new Date())
  val screenRectangle = new Rectangle(Toolkit.getDefaultToolkit.getScreenSize)
  val BufferedImage capture = new Robot().createScreenCapture(screenRectangle)
  val directory = new File(reportDirectory+testRunIdentifier)
  if (!directory.exists) directory.mkdirs
  ImageIO.write(capture, "png", new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifier+"_"+timeStamp+"_"+pluginIdentifier+".png"))
  val file = new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifierTimestamp+"_"+pluginIdentifier+".txt")
  if (file.exists) Files.append(testCaseUrl+" | "+alertError+" | "+testRunIdentifier+"_"+timeStamp+"_"+pluginIdentifier+".png\n", new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifierTimestamp+"_"+pluginIdentifier+".txt"), Charsets.UTF_8)
  if (!file.exists) Files.write(testCaseUrl+" | "+alertError+" | "+testRunIdentifier+"_"+timeStamp+"_"+pluginIdentifier+".png\n", new File(reportDirectory+testRunIdentifier+"//"+testRunIdentifierTimestamp+"_"+pluginIdentifier+".txt"), Charsets.UTF_8)
}

def surfaceAuditBaseline(String testCaseUrl, String testCaseIdentifier, String currentInput, String testIdentifier) {
  val directory = new File("C:\\ARC\\surface\\"+testIdentifier)
  if (!directory.exists) directory.mkdirs
  val file = new File(directory+"//"+testCaseIdentifier+".txt")
  if (file.exists) Files.append("\n"+currentInput, new File(directory+"//"+testCaseIdentifier+".txt"), Charsets.UTF_8)
  if (!file.exists) Files.write(testCaseUrl+"\n"+currentInput, new File(directory+"//"+testCaseIdentifier+".txt"), Charsets.UTF_8)
}

def surfaceAuditCurrent(String testCaseUrl, String testCaseIdentifier, String currentInput, String reportDirectory, String testRunIdentifier) {
  val directory = new File(reportDirectory+testRunIdentifier)
  if (!directory.exists) directory.mkdirs
  val file = new File(directory+"//"+testCaseIdentifier+".txt")
  if (file.exists) Files.append("\n"+currentInput, new File(directory+"//"+testCaseIdentifier+".txt"), Charsets.UTF_8)
  if (!file.exists) Files.write(testCaseUrl+"\n"+currentInput, new File(directory+"//"+testCaseIdentifier+".txt"), Charsets.UTF_8)
}

def surfaceAuditCompare(String testCaseIdentifier, String reportDirectory, String testRunIdentifier, String testIdentifier) {
  val baselineDirectory = "C:\\ARC\\surface\\"+testIdentifier
  val baselineFile = new File(baselineDirectory+"//"+testCaseIdentifier+".txt")
  val baselineFileString = if (baselineFile.exists) Files.toString(baselineFile, Charsets.UTF_8) else "Nothing to compare"
  val compareDirectory = reportDirectory+testRunIdentifier
  val compareFile = new File(compareDirectory+"//"+testCaseIdentifier+".txt")
  val compareFileString = if (compareFile.exists) Files.toString(compareFile, Charsets.UTF_8) else "Nothing to compare"
  if (baselineFileString.equals(compareFileString)) print("Pass\n") else print("Fail\n")
}

}