/**
 * Created by Abdellatif on 5/17/2016.
 */

import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * This class deals with File Saving & Opening
 * Helpful guide: http://www.rgagnon.com/javadetails/java-0470.html
 */
public class WhiteboardFile {

    /**
     * saves the current configuration of diagrams
     * @param fileName the file name with the file extension
     * @param diagrams an array containing all the diagrams
     * @throws Exception throws a bunch of exceptions
     */
    public static void save(String fileName, DShapeModel[] diagrams) throws Exception{
        XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(new FileOutputStream(fileName))
        );
        encoder.writeObject(diagrams);
        encoder.close();
    }

    public static void saveImage(String fileName, JFrame frame) throws Exception{
// TODO: save Image 
    }
    /**
     * reads a saved file
     * @param fileName the file name with the extension
     * @return an array of diagrams
     * @throws Exception
     */
    public static DShapeModel[] read (String fileName) throws Exception{
        // TODO: check whiteboard state (should only read in normal state)
        XMLDecoder decoder =
                new XMLDecoder(new BufferedInputStream(
                        new FileInputStream(fileName)));
        DShapeModel[] diagrams = (DShapeModel[])decoder.readObject();
        decoder.close();
        return diagrams;
    }

}
