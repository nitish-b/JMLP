package CheckResult;

import Result.DatasetResultCheck_KNN;
import Result.ResultDetails;
import com.swing.RunJavaProgram;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import com.swing.*;
import DataObject.*;

public class Result_KNN
{

    public ArrayList<Integer> studentSolutionResult = new ArrayList();
    public ArrayList<Integer> refernceSolutionResult = new ArrayList();

    public ResultDetails test() throws Exception
    {
        // Create an Object for KNN Reference Class
        KNN knn_reference = new KNN();
        //Result Flag for Reference Class

        String dataset_folder_path = System.getProperty("user.dir") + System.getProperty("file.separator") + "res" + System.getProperty("file.separator") + "KNN";
        File folder = new File(dataset_folder_path);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);

        String filepath = "";
        String CommandLineParameter = "";
        RunJavaProgram userProgram = new RunJavaProgram();

        int k = 3;

        //pick each file from the dataset and compare the result flag
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                filepath = listOfFiles[i].toString();
                System.out.println(filepath);
                KNNDataObject knndataObject = new KNNDataObject(filepath);

                KNN knnObject = new KNN();
                int classifier = knnObject.testviaKNN(knndataObject.pointDataList, knndataObject.currentPoint, k);
                refernceSolutionResult.add(classifier);

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
                        System.out.println(command);
                        double ret_val = userProgram.runProcess(command);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    System.out.println("Compilation Failed");
                }
            }
        }

        System.out.println(refernceSolutionResult);
        ResultDetails resultDetails_ =new ResultDetails();

        //Access the file and read each line
        String output_file = System.getProperty("user.dir") + System.getProperty("file.separator") + "res" + System.getProperty("file.separator") + "output" + System.getProperty("file.separator") + "knn_Output.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(output_file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                // process the line.
                System.out.println(line);
                studentSolutionResult.add(Integer.parseInt(line));
            }
        }

        System.out.println(studentSolutionResult);
        String resultMessage = "";

        if (refernceSolutionResult.equals(studentSolutionResult))
        {
            resultMessage = "Congratulations! \n Program passed all test cases!";
            resultDetails_.setMessage(resultMessage);
            for (int counter = 0; counter < studentSolutionResult.size(); counter++)
            {
                int datasetNo = counter + 1;
                String filename = listOfFiles[counter].getName();

                int expectedOutput = refernceSolutionResult.get(counter);
                int actualOutput = studentSolutionResult.get(counter);
                DatasetResultCheck_KNN resultdetails = new DatasetResultCheck_KNN(filename, expectedOutput, actualOutput);
                resultDetails_.addObjectToDatasetResultCheck_KNN(resultdetails);
            }

            }
        else
        {
            for (int counter = 0; counter < studentSolutionResult.size(); counter++)
            {
                int datasetNo=counter+1;
                String filename=listOfFiles[counter].getName();

                int expectedOutput=refernceSolutionResult.get(counter);
                int actualOutput=studentSolutionResult.get(counter);
                DatasetResultCheck_KNN resultdetails= new DatasetResultCheck_KNN(filename,expectedOutput,actualOutput);
                resultDetails_.addObjectToDatasetResultCheck_KNN(resultdetails);

                if (studentSolutionResult.get(counter) != refernceSolutionResult.get(counter))
                {
                    if (listOfFiles[counter].isFile())
                    {
                        resultMessage = resultMessage + "\n \t" + "Dataset" + Integer.toString(counter+1) + "   \t" + "Expected output:" + refernceSolutionResult.get(counter) + "  \t Program Output:" + studentSolutionResult.get(counter) + "\n";
                        System.out.println(resultMessage);

                    }

                }

            }

            resultMessage=resultMessage+" \n Check the return values for the condition!";
            resultDetails_.setMessage(resultMessage);
        }

        return resultDetails_;
    }

        public static  void main(String[] args) throws Exception
        {
            Result_KNN result_knn=new Result_KNN();
            result_knn.test();
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