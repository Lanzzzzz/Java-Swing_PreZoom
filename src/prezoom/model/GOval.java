package prezoom.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/** The oval objects
 * @author Zhijie Lan<p>
 * create date: 2020/11/1
 **/
public class GOval extends GObject
{
    public GOval(int x, int y, int w, int h, Color col, Boolean filled, int lineWidth)
    {
        super(x, y, col, filled, lineWidth, w, h, 0,0,true);
    }


    /**
     * draw this object.
     * use {@link GAttributes#col} as color.
     * use {@link GAttributes#filled} to depend whether calling {@link Graphics2D#fillOval(int, int, int, int)} or {@link Graphics2D#draw(Shape)}.
     * use {@link GAttributes#stroke} to set the line style
     * @param g the Graphics to paint
     */
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(getCurrentAttributes().getCol());
        double x = getCurrentAttributes().getX(), y = getCurrentAttributes().getY();
        int w = getCurrentAttributes().getWidth(), h = getCurrentAttributes().getHeight();

        if (getCurrentAttributes().getFilled()) g.fillOval((int)x, (int)y, w, h);
        else
        {
            g.setStroke(new BasicStroke(getCurrentAttributes().getLineWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.draw(new Ellipse2D.Double(x, y, w, h));
        }
    }

}
