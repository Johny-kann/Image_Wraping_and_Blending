package testers;

import com.computer_graphics.transforms.logics.VectorTransformations;

import javafx.geometry.Point2D;

public class UnitTesters {

	public static void main(String args[])
	{
				
		Point2D P = new Point2D(0, 0);
		Point2D P1 = new Point2D(4, 0);
		Point2D Q = new Point2D(0,4);
		Point2D Q1 = new Point2D(0,0);
		Point2D X = new Point2D(3,4);
		
		
		Point2D uv = new VectorTransformations().findUVForDestination(X, P, Q);
		
		
		System.out.println(new VectorTransformations().findXFromUV(P1, Q1, uv));
	}
}
