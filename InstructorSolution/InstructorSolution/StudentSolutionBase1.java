package InstructorSolution;

import java.util.*;
import java.lang.*;
import java.io.*;

import DataObject.*;

import CheckResult.*;

public class StudentSolutionBase1
{


	public int getPointClassifierKNN( List<Point> pointlist, Point testpoint, int k)
	{
		/* your code here */
	 /* Point is an object => contains x(Xco-ordinate), y(Yco-ordinate),
	  classifier(ClassifiedPoint -0/1), distance (Distance from other points)

	  Use Euclidean distance method to compute distance between two points
	  Example distance[(x1,y1)->(x2,y2)]=sqrt((x1-x2)^2+(y1-y2)^2)

	   get x co-ordinate         => getX() method
	   get y co-ordinate         => getY() method
	   get distance measure      => getDistance() method
	   get classification value  => getClassifier() method  */


		for(int m=0;m< pointlist.size();m++)
		{
			Point dataitem=pointlist.get(m);
			double calcx=(dataitem.getX()-testpoint.getX())*(dataitem.getX()-testpoint.getX());
			double calcy=(dataitem.getY()-testpoint.getY())*(dataitem.getY()-testpoint.getY());
			double distance=Math.sqrt(calcx+calcy);
			pointlist.get(m).setDistance(distance);
		}

		Collections.sort(pointlist);

		int freq1=0; // Frequency for classifier 0
		int freq2=0; // Frequency for classifier 1
		for(int i=0;i<k;i++)
		{
			if(pointlist.get(i).getClassifier()==0)
				freq1++;
			else if (pointlist.get(i).getClassifier()==1)
				freq2++;

		}
		return (freq1 > freq2 ? 0 : 1);
	}
	public static void main(String [] args) {
		String input = "";
		StudentSolutionBase1 studentSolnObj=new StudentSolutionBase1();
		String filename=args[0];
		KNNDataObject knndataObject=new KNNDataObject(filename);
		int k=3;
		int val=studentSolnObj.getPointClassifierKNN(knndataObject.pointDataList,knndataObject.currentPoint,k);
		String output_file=System.getProperty("user.dir")+System.getProperty("file.separator")+"res"+System.getProperty("file.separator")+"output"+System.getProperty("file.separator")+"knn_Output.txt";
		try
		{
			Writer output= new BufferedWriter(new FileWriter( output_file, true));
			output.flush();
			output.append(Integer.toString(val)+"\n");
			output.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


}

