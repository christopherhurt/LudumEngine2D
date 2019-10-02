package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a file that can save data in key-value pairs.
 *
 * @author Chris Hurt
 * @version 10.01.19
 */
public class SaveFile {

    /** The character used to separate keys from values */
    private static final char SEPARATOR = ':';

    private File mFile;

    /**
     * Constructor.
     *
     * @param pRelativePath the path of the save file relative to the executable
     */
    public SaveFile(String pRelativePath) {
        mFile = new File(System.getProperty("user.dir") + "/" + pRelativePath);
    }

    /**
     * Reads in data as a map of key-value pairs.
     *
     * @return the map of key-value pairs in the file.
     */
    public Map<String, String> read() {
        Map<String, String> data = new HashMap<>();

        try {
            Scanner reader = new Scanner(mFile);

            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] pair = line.split(Character.toString(SEPARATOR));

                if (pair.length != 2) {
                    throw new IllegalStateException("Invalid line while reading file " + mFile.getName());
                }

                data.put(pair[0], pair[1]);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Writes a map of key-value pairs to disk.
     *
     * @param pData the key-value pairs
     */
    public void write(Map<String, String> pData) {
        // Separator character can only be present between the keys and values
        pData.forEach((pKey, pValue) -> {
            if (pKey.indexOf(SEPARATOR) > -1 || pValue.indexOf(SEPARATOR) > -1) {
                throw new IllegalArgumentException("Tried to write data containing separator character " + SEPARATOR);
            }
        });

        try {
            Writer writer = new BufferedWriter(new FileWriter(mFile));

            pData.forEach((pKey, pValue) -> {
                try {
                    writer.write(pKey + SEPARATOR + pValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
