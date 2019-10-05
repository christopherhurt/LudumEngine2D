package main;

import java.io.*;
import java.util.*;

/**
 * Represents a file that can read and write data from a file in various formats.
 *
 * @author Chris Hurt
 * @version 10.01.19
 */
public final class SaveFile {

    /** The character used to separate keys from values when reading key-value pairs from a file */
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
     * Reads in the entire file as a single string.
     *
     * @return the data in the file as a String
     */
    public String readString() {
        StringBuilder builder = new StringBuilder();
        readLineByLine(pLine -> {
            builder.append(pLine);
            builder.append('\n');
        });
        return builder.toString();
    }

    /**
     * Reads in data as a list where each entry is from a line in the file.
     *
     * @return the list of lines in the file
     */
    public List<String> readLines() {
        List<String> data = new LinkedList<>();
        readLineByLine(data::add);
        return data;
    }

    /**
     * Reads in data as a map of key-value pairs where each pair is from a line separated by the SEPARATOR character.
     *
     * @return the map of key-value pairs in the file.
     */
    public Map<String, String> readPairs() {
        Map<String, String> data = new HashMap<>();
        readLineByLine(pLine -> {
            String[] pair = pLine.split(Character.toString(SEPARATOR));
            if (pair.length != 2) {
                throw new IllegalStateException("Invalid line while reading key-value pairs from file "
                        + mFile.getName());
            }
            data.put(pair[0], pair[1]);
        });
        return data;
    }

    /**
     * Writes the String representation of an object to the file using its toString() method.
     *
     * @param pObj the object to write to file
     */
    public void writeObject(Object pObj) {
        writeLines(List.of(pObj));
    }

    /**
     * Writes a list of objects to file where each line is the toString() representation of an entry in the list.
     *
     * @param pLines the list of objects to write
     */
    public void writeLines(List<?> pLines) {
        writeLineByLine(pLines, Object::toString);
    }

    /**
     * Writes a map of pairs to file where each line is the toString() of the pair key concatenated with the SEPARATOR
     * character concatenated with the toString() of the pair value.
     *
     * @param pPairs the map of pairs to write
     */
    public void writePairs(Map<?, ?> pPairs) {
        writeLineByLine(pPairs.entrySet(), pEntry -> {
            String keyStr = pEntry.getKey().toString();
            String valueStr = pEntry.getValue().toString();
            if (keyStr.indexOf(SEPARATOR) > -1 || valueStr.indexOf(SEPARATOR) > -1) {
                throw new IllegalArgumentException("Tried to write pair data containing separator character "
                        + SEPARATOR);
            }
            return keyStr + SEPARATOR + valueStr;
        });
    }

    /**
     * Reads and processes the file line-by-line.
     *
     * @param pInputHandler the handler describing what to do which each line of the file
     */
    private void readLineByLine(ILineInputHandler pInputHandler) {
        Scanner reader;
        try {
            reader = new Scanner(mFile);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                pInputHandler.handleLine(line);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a collection of data to the file.
     *
     * @param pLines the collection of data to write where each entry is representative of a line in the file
     * @param pOutputHandler the handler that translates each entry in the collection a writeable line string
     * @param <T> the object type of the collection entries
     */
    private <T> void writeLineByLine(Collection<T> pLines, ILineOutputHandler<T> pOutputHandler) {
        try {
            Writer writer = new BufferedWriter(new FileWriter(mFile));

            pLines.forEach(pEntry -> {
                try {
                    writer.write(pOutputHandler.convertToLine(pEntry));
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

    /**
     * Helper interface with handler describing how each line should be processed as the file is being read from.
     */
    private interface ILineInputHandler {

        /**
         * Handler describing how each line from the file should be processed as it's read.
         *
         * @param pLine the line read from the file
         */
        void handleLine(String pLine);

    }

    /**
     * Helper interface with handler describing how an object should be translated to a String when being written as a
     * line to the file.
     *
     * @param <T> the type of the object being translated
     */
    private interface ILineOutputHandler<T> {

        /**
         * Converts an object, typically an entry in some collection, to a String representation that can be written
         * as a line to the save file.
         *
         * @param pEntry the object to be converted
         * @return the converted String to be written as a line
         */
        String convertToLine(T pEntry);

    }

}
