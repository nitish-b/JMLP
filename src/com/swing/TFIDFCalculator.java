package com.swing;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TFIDFCalculator {

    public double tfIdf(List<List<String>> docs,List<String> curr_doc, String term)
    {
        double tf_val=0;
        double result = 0;

        for (String word : curr_doc)
        {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        tf_val= result / curr_doc.size();
        double idf_val=0;
        double n = 0;
        for (List<String> doc_item : docs)
        {
            for (String word : doc_item)
            {
                if (term.equalsIgnoreCase(word))
                {
                    n++;
                    break;
                }
            }
        }
        idf_val= Math.log(docs.size() / n);
        return tf_val * idf_val;

    }

    public static void main(String[] args) throws IOException
    {

        //Dataset Preparation

        TFIDFCalculator calculator = new TFIDFCalculator();
        String dataset_folder_path="./res/tfidf";
        File folder = new File(dataset_folder_path);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);
        String[] termarray=new String[]{"ipsum","try","march","computer"};

        ArrayList<BufferedReader> csvFileReaderList=new ArrayList<BufferedReader>();
        BufferedReader csvreaderFile = null;

        List<String> csvFileList=new ArrayList<String>();
        String line="";
        List<List<String>> documents=new ArrayList<>();


        //create a csvreader file list
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                String datasetFileName= listOfFiles[i].getName();
                String datasetFileNamePath = dataset_folder_path + "/" + datasetFileName;
                File datasetNameFileObject=new File(datasetFileNamePath);

                if(getExtensionOfFile(datasetNameFileObject).equals("csv"))
                {
                    csvreaderFile = null;
                    try
                    {
                        csvreaderFile = new BufferedReader(new FileReader(datasetFileNamePath));
                        while ((line = csvreaderFile.readLine()) != null)
                        {
                            // use comma as separator
                            String[] linesplitArray=line.split(",");
                            List<String> doc = new ArrayList<>();
                            for (String s: linesplitArray)
                            {
                                //Do your stuff here
                                doc.add(s);
                            }

                            documents.add(doc);
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
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                    finally
                    {
                        if (csvreaderFile != null)
                        {
                            try
                            {
                                csvreaderFile.close();
                            } catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        //pick each file from the dataset and compare the result flag
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                String datasetFileName = listOfFiles[i].getName();
                String datasetFileNamePath = dataset_folder_path + "/" + datasetFileName;
                File datasetNameFileObject = new File(datasetFileNamePath);

                if (getExtensionOfFile(datasetNameFileObject).equals("csv"))
                {
                    csvreaderFile = null;
                    csvreaderFile = new BufferedReader(new FileReader(datasetFileNamePath));
                    line="";
                    List<String> currentDoc = new ArrayList<>();
                    while ((line = csvreaderFile.readLine()) != null)
                    {
                        // use comma as separator
                        String[] linesplitArray=line.split(",");
                        //Do your stuff here
                        currentDoc.addAll(Arrays.asList(linesplitArray));
                    }

                    //Call The tfidf Function
                    double tfidf=calculator.tfIdf(documents, currentDoc, termarray[i]);
                    System.out.println(tfidf);
                }
            }
        }


    }



    public static String getExtensionOfFile(File file)
    {
        String fileExtension="";
        // Get file Name first
        String fileName=file.getName();

        // If fileName do not contain "." or starts with "." then it is not a valid file
        if(fileName.contains(".") && fileName.lastIndexOf(".")!= 0)
        {
            fileExtension=fileName.substring(fileName.lastIndexOf(".")+1);
        }

        return fileExtension;
    }
}