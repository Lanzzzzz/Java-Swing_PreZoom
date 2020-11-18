package prezoom.model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/** The rectangle objects
 * @author Zhijie Lan<p>
 * create date: 2020/11/1
 **/
public class GRectangle extends GObject
{
    public GRectangle(double x, double y, int w, int h, Color col, Boolean filled, int lineWidth)
    {
        super(x, y, col, filled, lineWidth,w,h,null,null,true);
        this.drawShape = new Rectangle2D.Double(x, y, w, h);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g)
    {
        double x = getCurrentAttributes().getX(), y = getCurrentAttributes().getY();
        int w = getCurrentAttributes().getWidth(), h = getCurrentAttributes().getHeight();

        this.drawShape = new Rectangle2D.Double(x, y, w, h);

        drawing(g);
    }

}
