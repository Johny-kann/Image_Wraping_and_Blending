package testers;

import com.computer_graphics.transforms.logics.SmallLogics;
import com.computer_graphics.transforms.logics.Transformations2D;
import com.computer_graphics.transforms.logics.VectorTransformations;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.util.Math;

public class UnitTesters {

	public static void main(String args[])
	{
				
	//	 Math.round(0.0);
		VectorTransformations vec = new VectorTransformations();
		Transformations2D sl = new Transformations2D();
		
		Point2D Xdest = new Point2D(3, 5);
		Point2D Pdest = new Point2D(0, 0);
		Point2D Qdest = new Point2D(0, 3);
		Point2D Psource = new Point2D(1, 0);
		Point2D Qsource = new Point2D(1, 4);
		
		
		
		Dimension2D uvDest = new Dimension2D(10, 10);
		Dimension2D xyDest =  new Dimension2D(20, 20);
		
		Dimension2D uvSource = new Dimension2D(10, 10);
		Dimension2D xySource = new Dimension2D(20, 20);
		
		System.out.println("New P "+sl.getXYFromUV(uvSource, xySource, Qsource)
				);
		
		Point2D vecOut = vec.getXfromSource(Xdest, Pdest, Qdest, Psource, Qsource, uvDest, xyDest, uvSource, xySource);
		System.out.println(vecOut);
		
	//	System.out.println(new Transformations2D().getXYFromUV(uvSource, xySource, Qsource));
	}
}
