package interfaces;

import java.awt.Toolkit;
import java.awt.Dimension;


/**
 * Interface that contain default values to be used in any frame
 */
public interface IFrameConstants {

    static final   Toolkit     TOOLKIT          = Toolkit.getDefaultToolkit();
    static final   Dimension   SCREEN_DIMENSION = TOOLKIT.getScreenSize();
    static final   double      SCREEN_WIDTH     = SCREEN_DIMENSION.getWidth();
    static final   double      SCREEN_HEIGHT    = SCREEN_DIMENSION.getHeight();
    static final   String      TITLE            = "Minecraft Server by Pedro Braghin";
    static final   int         WINDOW_WIDTH     = 400;
    static final   int         WINDOW_HEIGHT    = 600;
    static final   int         BUTTON_WIDTH     = 150;
    static final   int         BUTTON_HEIGHT    = 40;
}
