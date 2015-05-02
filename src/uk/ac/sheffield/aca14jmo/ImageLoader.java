package uk.ac.sheffield.aca14jmo; /**
 * Created by joshua on 02/05/15.
 */

import javax.swing.*;
import java.io.File;
import java.util.*;

public class ImageLoader {
    private HashMap<String, ImageIcon> imageStore;
    private String pathPrefix, pathSuffix;

    public ImageLoader(String pathPrefix, String pathSuffix) {
        this.pathPrefix = pathPrefix;
        this.pathSuffix = pathSuffix;
        imageStore = new HashMap<>();
    }

    public ImageIcon getImage(String id) {
        if (imageStore.containsKey(id)) {
            DebugLog.println("Already have " + id + ", retrieving it.");
            return imageStore.get(id);
        }
        else {
            DebugLog.println("Loading " + id + " from file.");
            String path = pathPrefix + id + pathSuffix;
            if (! new File(path).exists()) {
                System.out.println("Error: could not load image " + id + " (" + path + ")");
                System.exit(1);
            }
            ImageIcon image = new ImageIcon(path);
            imageStore.put(id, image);
            return image;
        }
    }

    public ImageIcon getImageForPiece(Piece p) {
        String pieceName = p.getClass().getSimpleName();
        if (p.getColour() == PieceCode.BLACK) {
            pieceName += "B";
        }
        return getImage(pieceName);
    }

    public static void main(String[] args) {
        DebugLog.enable();
        ImageLoader img = new ImageLoader("images/", ".png");
    }
}
