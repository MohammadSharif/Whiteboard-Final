/**
 * Created by Abdellatif on 5/17/2016.
 */

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * This class deals with File Saving & Opening
 */
public class WhiteboardFile {

    /**
     * saves the current configuration of diagrams
     * @param fileName the file name without the file extension (i.e. without .xml)
     * @param diagrams an array containing all the diagrams
     * @throws Exception throws a bunch of exceptions
     */
    public static void save(String fileName, DShapeModel[] diagrams) throws Exception{
        XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(new FileOutputStream(fileName + ".xml"))
        );
        encoder.writeObject(diagrams);
        encoder.close();
    }

}
