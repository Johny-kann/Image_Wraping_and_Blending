package testers;

import javafx.geometry.Point2D;

import com.computer_graphics.transforms.logics.SmallLogics;
import com.sun.corba.se.impl.interceptors.PINoOpHandlerImpl;

public class UnitTesters {

	public static void main(String args[])
	{
		Point2D pt = new SmallLogics().findArrowPoint(2.0, 3.0, 10.0, 10.0);
	//	System.out.println(pt.getX()+","+pt.getY());
	}
}
