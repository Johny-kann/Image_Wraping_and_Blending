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
				
		SmallLogics sl = new SmallLogics();
		Double wt = sl.calculateWeight(50.0, 10.0, 0.5, 1.5, 1.0);
		System.out.println(wt*3.0);
		wt = sl.calculateWeight(100.0, 10.0, 0.5, 1.5, 1.0);
		System.out.println(wt*3.0);
	//	System.out.println(new VectorTransformations().findUVFromDestination(new Point2D(6, 6), new Point2D(1, 0), new Point2D(1, 4)).getY());
		VectorTransformations vec = new VectorTransformations();
		
		Point2D X = new Point2D(10, 10);
		Point2D sourceP = new Point2D(1, 0);
		Point2D sourceQ = new Point2D(1, 5);
		Point2D destP = new Point2D(4, 0);
		Point2D destQ = new Point2D(4, 5);
		Dimension2D uvDest = new  Dimension2D(10, 10);
		Dimension2D xyDest = new Dimension2D(20, 20);
		
		Dimension2D uvSource = new Dimension2D(10, 10);
		Dimension2D xySource = new Dimension2D(20, 20);
		int noOfLines = 1;
		Double alpha = 1.0;
	//	Point2D vect = vec.getXAveragingAllLines(X, sourceP, sourceQ, destP, destQ, uvDest, xyDest, uvSource, xySource, noOfLines, alpha);
//		System.out.println(vect);
		
	//	System.out.println(vec.findXFromUV(new Point2D(1, 0), new Point2D(1, 5), new Point2D(1, 1)));
	}
}
