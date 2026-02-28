package com.viveka01.copymail;

import java.awt.*;
import java.awt.datatransfer.*;

public class ClipBoardReader {
    public static String readClipBoard() {
        try {
            Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable t = CLIPBOARD.getContents(null);

            if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String TEXT = (String) t.getTransferData(DataFlavor.stringFlavor);
                System.out.println("Text: " + TEXT);
                return TEXT;
            } else {
                System.out.println("Clipboard does not contain text.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}