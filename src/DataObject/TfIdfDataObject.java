package DataObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TfIdfDataObject
{

    public List<List<String>> documents=new ArrayList<>();
    public List<String> CurrentDoc= new ArrayList<String>();
    public String term;

    public void generateWholeDataSetTokens()
    {
        String dataset_folder_path="./res/tfidf";
        File folder = new File(dataset_folder_path);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);
        BufferedReader csvreaderFile = null;
        String line="";


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

                            this.documents.add(doc);
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
    }

    public void generateCurrentDocumentToken(String currentfilename)
    {
        BufferedReader csvreaderFile = null;
        String line="";
        try
        {
            csvreaderFile = new BufferedReader(new FileReader(currentfilename));
            while ((line = csvreaderFile.readLine()) != null)
            {
                // use comma as separator
                String[] linesplitArray = line.split(",");
                List<String> doc = new ArrayList<>();
                for (String s : linesplitArray)
                {
                    //Do your stuff here
                    this.CurrentDoc.add(s);
                }
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setTermString(String datasetFileNamePath)
    {
        File f = new File(datasetFileNamePath);
        String filename=f.getName();
        //Set dataset file to term mapping
        String[] termarray=new String[]{"ipsum","try","march","computer"};
        HashMap<String,String> fileToTerm=new HashMap<String,String>();
        fileToTerm.put("tfidf_dataset1.csv","ipsum");
        fileToTerm.put("tfidf_dataset2.csv","try");
        fileToTerm.put("tfidf_dataset3.csv","march");
        fileToTerm.put("tfidf_dataset4.csv","computer");

        this.term=fileToTerm.get(filename);
    }

    public TfIdfDataObject(String datasetFileNamePath)
    {
        //Generate whole dataset token
        generateWholeDataSetTokens();

        //Generate current document tokens
        generateCurrentDocumentToken(datasetFileNamePath);

        setTermString(datasetFileNamePath);
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