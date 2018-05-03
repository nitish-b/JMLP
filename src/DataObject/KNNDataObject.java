package DataObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KNNDataObject
{

    public List<Point> pointDataList=new ArrayList<Point>();
    public List<Point> testpointDataList=new ArrayList<Point>();
    public Point currentPoint;

    public  void generateDataSetPoints(String datasetFileNamePath)
    {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = " ";

        try {

            br = new BufferedReader(new FileReader(datasetFileNamePath));
            while ((line = br.readLine()) != null)
            {
                // use comma as separator
                String[] points = line.split(" ");
                //number of points object
                int number=points.length-1;
                int classifier=Integer.parseInt(points[0]);
                for(int counter=1;counter<=number;counter++)
                {
                    String pointString=points[counter];
                    pointString=pointString.replace('(',' ');
                    pointString=pointString.replace(')',' ');
                    String[] pointStringArray=pointString.split(",");
                    double xCordinate=Double.parseDouble(pointStringArray[0].toString().trim());
                    double yCordinate=Double.parseDouble(pointStringArray[1].toString().trim());
                    Point p=new Point(xCordinate,yCordinate,classifier);
                    this.pointDataList.add(p);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void setTestDataPoints(String filepath)
    {
        //Set Test Data Points
        Point testDatapoint0 = new Point(1, 2);
        Point testDatapoint1 = new Point(1, 2);
        Point testDatapoint2 = new Point(1, 2);
        Point testDatapoint3 = new Point(1, 2);

        File f = new File(filepath);
        String filename=f.getName();
        //Set dataset file to term mapping

        HashMap<String,Point> fileToTerm=new HashMap<String,Point>();
        fileToTerm.put("KNN_dataset1.csv",testDatapoint0);
        fileToTerm.put("KNN_dataset2.csv",testDatapoint1);
        fileToTerm.put("KNN_dataset3.csv",testDatapoint2);
        fileToTerm.put("KNN_dataset4.csv",testDatapoint3);

        this.currentPoint=fileToTerm.get(filename);


    }

    public KNNDataObject(String datasetFileNamePath)
    {
        generateDataSetPoints(datasetFileNamePath);
        setTestDataPoints(datasetFileNamePath);
    }

    public static  void  main(String args[])
    {
        String datasetFileNamePath="/home/arpita/IdeaProjects/CodeTest/res/KNN/KNN_dataset1.csv";
        KNNDataObject knndataobject=new KNNDataObject(datasetFileNamePath);
    }
}