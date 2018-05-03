package CheckResult;

import Result.DatasetResultCheck_TfIdf;
import Result.ResultDetails;
import com.swing.*;
import DataObject.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Result_TfIdf
{
    public ArrayList<Double> studentSolutionResult = new ArrayList();
    public ArrayList<Double> refernceSolutionResult = new ArrayList();


    public ResultDetails test() throws Exception
    {
        // Create an Object for TfIdf Reference Class
        TFIDFCalculator tfidfCalculator_reference = new TFIDFCalculator();

        //Result Flag for Reference Class

        String dataset_folder_path = System.getProperty("user.dir") + System.getProperty("file.separator") + "res" + System.getProperty("file.separator") + "tfidf";
        File folder = new File(dataset_folder_path);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);

        String filepath = "";


        String targetStudentSolutionBaseClassPath = "/home/arpita/IdeaProjects/CodeTest/out/production/CodeTest/";
        String CommandLineParameter = "";
        RunJavaProgram userProgram = new RunJavaProgram();

        //pick each file from the dataset and compare the result flag
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {

                //Run the Student Reference Solution
                filepath = listOfFiles[i].toString();
                System.out.println(filepath);
                TfIdfDataObject tfidfdataobject = new TfIdfDataObject(filepath);
                double val_reference = tfidfCalculator_reference.tfIdf(tfidfdataobject.documents, tfidfdataobject.CurrentDoc, tfidfdataobject.term);
                refernceSolutionResult.add(val_reference);

                //Run the Student Solution
                CommandLineParameter = filepath;

	            String compileFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"+System.getProperty("file.separator") ;
	            String packageDependencyPath=System.getProperty("user.dir") + System.getProperty("file.separator") + "StudentSolutionBase"+System.getProperty("file.separator") ;
	            String runpath= compileFilePath+":"+packageDependencyPath;

	            String targetStudentSolutionClass = "StudentSolutionBase/StudentSolutionBase";
	            String compileFileName =System.getProperty("user.dir")+System.getProperty("file.separator")+"StudentSolutionBase"+System.getProperty("file.separator")+"StudentSolutionBase/StudentSolutionBase.java";
                int res = userProgram.runProcess("javac -cp " + compileFilePath + " " + compileFileName);

                if (res == 1)
                {
                    String command = "java -cp " + runpath + " " + targetStudentSolutionClass + " " + CommandLineParameter;
                    try
                    {
                        double ret_val = userProgram.runProcess(command);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                } else
                {
                    System.out.println("Compilation Failed");
                }

            }
        }
        System.out.println(refernceSolutionResult);
        ResultDetails resultDetails_ =new ResultDetails();


        //Access the file and read each line
        String output_file = System.getProperty("user.dir") + System.getProperty("file.separator") + "res" + System.getProperty("file.separator") + "output" + System.getProperty("file.separator") + "tfIdf_Output.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(output_file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                // process the line.
                System.out.println(line);
                studentSolutionResult.add(Double.parseDouble(line));
            }
        }

        //Check whether two arraylist are equal or not
        ArrayList<String> resultNonMatchedList = new ArrayList<String>();
        String resultMessage = "";
        if (refernceSolutionResult.equals(studentSolutionResult))
        {
            resultMessage = "Program Passed all test cases";
            resultDetails_.setMessage(resultMessage);

            for (int counter = 0; counter < studentSolutionResult.size(); counter++)
            {

                String filename=listOfFiles[counter].getName();
                double expectedOutput=refernceSolutionResult.get(counter);
                double actualOutput=studentSolutionResult.get(counter);

                DatasetResultCheck_TfIdf resultdetails= new DatasetResultCheck_TfIdf(filename,expectedOutput,actualOutput);
                resultDetails_.addObjectToDatasetResultCheck_TfIdf(resultdetails);

            }

        } else
        {
            for (int counter = 0; counter < studentSolutionResult.size(); counter++)
            {

                String filename=listOfFiles[counter].getName();
                double expectedOutput=refernceSolutionResult.get(counter);
                double actualOutput=studentSolutionResult.get(counter);

                DatasetResultCheck_TfIdf resultdetails= new DatasetResultCheck_TfIdf(filename,expectedOutput,actualOutput);
                resultDetails_.addObjectToDatasetResultCheck_TfIdf(resultdetails);

                if (studentSolutionResult.get(counter) != refernceSolutionResult.get(counter))
                {
                    if (listOfFiles[counter].isFile())
                    {
                        resultMessage = resultMessage + "\n \t" + "Dataset" + Integer.toString(counter+1) + "   \t" + "Expected output:" + refernceSolutionResult.get(counter) + "  \t Program Output:" + studentSolutionResult.get(counter) + "\n";
                    }

                }
            }
            resultMessage=resultMessage+" \n Please check your tf and idf calculation";
            resultDetails_.setMessage(resultMessage);

        }

        return resultDetails_;

    }


    public static  void main(String[] args) throws Exception
    {
        Result_TfIdf result_tfidf_obj=new Result_TfIdf();
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