package com.swing;

import DataObject.KNNDataObject;
import DataObject.Point;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class KNN
{

    public int testviaKNN(List<Point> pointlist, Point testpoint, int k)
    {
        // Access point0 Arraylist

        //0th element is the classifier value
        System.out.println(pointlist.size());
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



    public static void main(String[] args) throws IOException
    {
        KNN knnObject=new KNN();

        String dataset_folder_path = System.getProperty("user.dir") + System.getProperty("file.separator") + "res" + System.getProperty("file.separator") + "KNN";
        File folder = new File(dataset_folder_path);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);
        int k=3;

        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                String filepath = listOfFiles[i].toString();
                KNNDataObject knndataObject=new KNNDataObject(filepath);

                int classifier=knnObject.testviaKNN(knndataObject.pointDataList,knndataObject.testpointDataList.get(i),k);
            }

        }

    }
}
