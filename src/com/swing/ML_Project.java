package com.swing;

import CheckResult.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

import Result.ResultDetails;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;


public class ML_Project extends DefaultTableCellRenderer
{
    private JFrame textFrame;
    private JPanel cp;
    private RSyntaxTextArea textArea;
    String problemName;
    private ResultDetails resultDetailsObj;

    private static String defaultText_NaiveBayes = "\npublic static boolean mySolution(String inputText, int vocabSize, " +
                                 "int[] classCount)\n"+
                                 "{\n" +
                                 "\t/* your code here */\n" +
                                 "\n" +
                                 "\treturn false;\n" +
                                 "}" ;

    //  private static String defaultText_TfIdf = "\n public double getTfIdfValue( List<List<String>> docs,List<String> doc, " +
    private static String defaultText_TfIdf = "\n public double getTfIdfValue( List<List<String>> docs,List<String> curr_doc, " +
		    "String term)\n"+
            "{\n" +
		    "\t /* Tf- Term Frequency : Raw count of a term in a document.Find the frequency \n" +
		    "\t *  of the keyword('term') from  the curr_doc.           \n" +
		    "\n"+
		    "\t * Idf -Inverse Document Frequency: It is a measure of how much information \n" +
		    "\t * the word provides, that is, whether the term is common or rare across    \n" +
		    "\t * documents. To get the Idf Value use the following formula.\n"+
		    "\t * Source: https://en.wikipedia.org/wiki/Tf-idf       \n"+
		    "\n"+
		    "\t * idf= log(Size of the set of documents <<docs>>) / n \n"+
		    "\t * n= number of documents where the 'term' (the specific word) is present. \n"+
		    "\t * Use Math.log function to compute logarithm. \n"+
			"\n"+
		    "\t * docs     => Set of documents with each word(String Type) tokenized.\n"+
		    "\t * curr_doc => Given document with each word(String Type) tokenized.  \n"+
		    "\t * term     => Given string for which Tf-Idf value is to be computed.  */ \n"+
		    "\n" +
		    "\n" +
		    "\t/* your code here */\n" +
            "\n" +
            "\treturn 0;\n" +
            "}" ;

    private static String defaultText_Knn = "\n public int getPointClassifierKNN( List<Point> pointlist, Point testpoint, " +

          "int k)\n"+
          "{\n" +
            "\t /* Point is an object => contains x(Xco-ordinate), y(Yco-ordinate),          \n" +
            "\t * classifier(ClassifiedPoint -0/1), distance (Distance from other points) \n" +
            "\n" +
            "\t * Use Euclidean distance method to compute distance between two points    \n" +
            "\t * Example distance[(x1,y1)->(x2,y2)]=sqrt((x1-x2)^2+(y1-y2)^2)         \n"+
            "\n"+
            "\t * get x co-ordinate         => getX() method                                     \n" +
            "\t * get y co-ordinate         => getY() method                                     \n" +
            "\t * get distance measure      => getDistance() method                              \n" +
            "\t * get classification value  => getClassifier() method  */                        \n" +
            "\n" +
		    "\n" +
		    "\t/* your code here */\n" +
		    "\n" +
          "\t return 0;\n" +
          "}" ;


    private static String header = "package StudentSolutionBase;\n"+
            "\nimport java.util.*;\n"+
            "import java.lang.*;\n"+
            "import java.io.*;\n\n"+
            "import DataObject.*;\n\n"+
            "import CheckResult.*;\n\n"+
            "public class StudentSolutionBase\n"+
            "{\n";

    private static String footer = "\n}" ;
    private static  String main="";
    private static String main_Naive_Bayes = "public static void main(String [] args) {\n"+
            "\tString input = \"\";\n"+
            "}\n";

    private static String writeToFile_TfIdf="public void writeToFile(double val) { \n"+
            "\t String output_file=System.getProperty(\"user.dir\")+System.getProperty(\"file.separator\")+\"res\"+System.getProperty(\"file.separator\")+\"output\"+System.getProperty(\"file.separator\")+\"tfIdf_Output.txt\";\n"+
            "\t try "+
            "\t {"+
            "\t Writer output= new BufferedWriter(new FileWriter( output_file, true));\n"+
            "\t output.flush();"+
            "\t output.append(Double.toString(val)+\"\\n\");"+
            "\t output.close();"+
            "\t }"+
            "\t catch (IOException e)\n"+
            "\t {"+
            "\t e.printStackTrace();"+
            "\t }"+
            "}\n";

    private static String main_tfidf = "public static void main(String [] args) {\n"+
            "\tString input = \"\";\n"+
            "\t StudentSolutionBase studentSolnObj=new StudentSolutionBase(); \n"+
            "\t String filename=args[0]; \n"+
            "\t TfIdfDataObject tfidfdataobject=new TfIdfDataObject(filename);\n" +
            "\t System.out.println(studentSolnObj.getTfIdfValue(tfidfdataobject.documents,tfidfdataobject.CurrentDoc,tfidfdataobject.term)); \n"+
            "\t double val=studentSolnObj.getTfIdfValue(tfidfdataobject.documents,tfidfdataobject.CurrentDoc,tfidfdataobject.term); \n"+
            "\t String output_file=System.getProperty(\"user.dir\")+System.getProperty(\"file.separator\")+\"res\"+System.getProperty(\"file.separator\")+\"output\"+System.getProperty(\"file.separator\")+\"tfIdf_Output.txt\";\n"+
            "\t try \n"+
            "\t { \n"+
            "\t Writer output= new BufferedWriter(new FileWriter( output_file, true));\n"+
            "\t output.flush();\n"+
            "\t output.append(Double.toString(val)+\"\\n\");\n"+
            "\t output.close();\n"+
            "\t }\n"+
            "\t catch (IOException e)\n"+
            "\t {\n"+
            "\t e.printStackTrace();\n"+
            "\t }\n"+
            "}\n";

    private static String main_knn = "public static void main(String [] args) {\n"+
            "\tString input = \"\";\n"+
            "\t StudentSolutionBase studentSolnObj=new StudentSolutionBase(); \n"+
            "\t String filename=args[0]; \n"+
            "\t KNNDataObject knndataObject=new KNNDataObject(filename);\n" +
            "\t int k=3; \n"+
            "\t int val=studentSolnObj.getPointClassifierKNN(knndataObject.pointDataList,knndataObject.currentPoint,k); \n"+
            "\t String output_file=System.getProperty(\"user.dir\")+System.getProperty(\"file.separator\")+\"res\"+System.getProperty(\"file.separator\")+\"output\"+System.getProperty(\"file.separator\")+\"knn_Output.txt\";\n"+
            "\t try \n"+
            "\t { \n"+
            "\t Writer output= new BufferedWriter(new FileWriter( output_file, true));\n"+
            "\t output.flush();\n"+
            "\t output.append(Integer.toString(val)+\"\\n\");\n"+
            "\t output.close();\n"+
            "\t }\n"+
            "\t catch (IOException e)\n"+
            "\t {\n"+
            "\t e.printStackTrace();\n"+
            "\t }\n"+
            "}\n";

    private WindowBuilder windowBuilder;

    public ML_Project(WindowBuilder windowBuilderObj,String problemName)
    {
        this.windowBuilder=windowBuilderObj;
        this.problemName=problemName;
        resultDetailsObj=new ResultDetails();
        generateGUI();
    }


    public ResultDetails getResultDetailsObj()
    {
        return resultDetailsObj;
    }

    public void setResultDetailsObj(ResultDetails resultDetailsObj)
    {
        this.resultDetailsObj = resultDetailsObj;
    }

    public void generateGUI()
    {
        textFrame=new JFrame("Java Code Editor");
        cp = new JPanel();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

        if(problemName.equals("TfIdf"))
        {
            textArea = new RSyntaxTextArea(defaultText_TfIdf, 50, 85);
            main=main_tfidf;
        }

        else if(problemName.equals("KNN"))
        {
            textArea = new RSyntaxTextArea(defaultText_Knn, 50, 85);
            main=main_knn;
        }

        else
        {
            textArea = new RSyntaxTextArea(defaultText_NaiveBayes, 50, 85);
            main=main_Naive_Bayes;
        }

        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);

        JButton saveButton = new JButton("Save to File");
	    saveButton.setFont(new Font("Sans Serif", Font.BOLD, 15));
        saveButton.setAlignmentX(saveButton.CENTER_ALIGNMENT);
        saveButton.addActionListener((ActionEvent arg0) ->
                saveTextToFileAndCompile());

        cp.add(Box.createVerticalStrut(10));
        cp.add(saveButton, BorderLayout.CENTER);
        cp.add(Box.createVerticalStrut(10));
        cp.add(sp);

        textFrame.add(cp);
        textFrame.setContentPane(cp);
        textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textFrame.setVisible(true);
        textFrame.pack();
        textFrame.setLocationRelativeTo(null);
    }

    public void destroyGUI()
    {
        if(textFrame.isDisplayable())
        {
            textFrame.dispose();
        }

    }

    public RSyntaxTextArea getTextArea()
    {
        return textArea;
    }

    public void checkResult() throws IOException
    {
        Result_TfIdf result_tfidf_obj = new Result_TfIdf();
    }

    public void saveTextToFileAndCompile()
    {
        try
        {
            //Delete the content of the file
            RunJavaProgram userProgram = new RunJavaProgram();

            String filepath=System.getProperty("user.dir")+System.getProperty("file.separator")+"src";
            String fileName =System.getProperty("user.dir")+System.getProperty("file.separator")+"StudentSolutionBase"+System.getProperty("file.separator")+"StudentSolutionBase/StudentSolutionBase.java";

	        File file = new File(fileName);
            // Now write to the file
            PrintWriter output = new PrintWriter(new FileWriter(file));
            System.out.println(file.getAbsolutePath());
            output.println(header);
            output.println(textArea.getText());
            output.println(main);
            output.println(footer);
            output.close();
            JOptionPane.showMessageDialog(textArea, "File saved successfully");

            int res=userProgram.runProcess("javac -cp "+filepath+" "+fileName);

            if(res==1 && problemName.equals("TfIdf"))
            {
                String output_file=System.getProperty("user.dir")+System.getProperty("file.separator")+"res"+System.getProperty("file.separator")+"output"+System.getProperty("file.separator")+"tfIdf_Output.txt";
                FileOutputStream writer = new FileOutputStream(output_file);

                Process theProcess = null;
                Result_TfIdf resultTfIdfObj=new Result_TfIdf();
                System.out.println("HI");
                ResultDetails resultDetailsObj=resultTfIdfObj.test();
                String[] resultCountNextLine=resultDetailsObj.getMessage().split("\n");
                this.setResultDetailsObj(resultDetailsObj);
                System.out.println("Len::"+this.getResultDetailsObj().getDatasetResultCheckObject_TfIdf().size());
                int i;
                this.windowBuilder.table.setModel(new DefaultTableModel(this.windowBuilder.getRowData(), this.windowBuilder.getColumnNames()));

                for(i=0;i<this.getResultDetailsObj().getDatasetResultCheckObject_TfIdf().size();i++)
                {
                    System.out.println("Expected::"+this.getResultDetailsObj().getDatasetResultCheckObject_TfIdf().get(i).getExpectedoutput());
                    System.out.println("Actual::"  +this.getResultDetailsObj().getDatasetResultCheckObject_TfIdf().get(i).getActualoutput());

                    this.windowBuilder.getRowData()[i][0] = getBaseName(this.getResultDetailsObj().getDatasetResultCheckObject_TfIdf().get(i).getFilename());
                    this.windowBuilder.getRowData()[i][1] = this.getResultDetailsObj().getDatasetResultCheckObject_TfIdf().get(i).getExpectedoutput();
                    this.windowBuilder.getRowData()[i][2] = this.getResultDetailsObj().getDatasetResultCheckObject_TfIdf().get(i).getActualoutput();


                }
                this.windowBuilder.table.setModel(new DefaultTableModel(this.windowBuilder.getRowData(), this.windowBuilder.getColumnNames()));

                for(i=0;i<this.windowBuilder.getColumnNames().length;i++)
                {
                    this.windowBuilder.table.setDefaultRenderer(this.windowBuilder.table.getColumnClass(i), new ResultWindowRowRenderer());
                }

                this.windowBuilder.table.setModel(new DefaultTableModel(this.windowBuilder.getRowData(), this.windowBuilder.getColumnNames()));

                int popupLength=0;
                int popupBreadth=0;
                if(resultCountNextLine.length==1)
                {
                    popupLength=200;
                    popupBreadth=200;
                }
                else if(resultCountNextLine.length==2)
                {
                    popupLength=300;
                    popupBreadth=200;
                }
                else if(resultCountNextLine.length==3)
                {
                    popupLength=450;
                    popupBreadth=200;
                }
                else if(resultCountNextLine.length==4)
                {
                    popupLength=550;
                    popupBreadth=200;
                }

                UIManager.put("OptionPane.minimumSize",new Dimension(popupLength,popupBreadth));

                JOptionPane.showMessageDialog(null,  resultDetailsObj.getMessage());

                //Delete the content of the file
                writer = new FileOutputStream(output_file);
            }

            else if(res==1 && problemName.equals("KNN"))
            {
                String output_file=System.getProperty("user.dir")+System.getProperty("file.separator")+"res"+System.getProperty("file.separator")+"output"+System.getProperty("file.separator")+"knn_Output.txt";
                FileOutputStream writer = new FileOutputStream(output_file);

                Process theProcess = null;
                Result_KNN resultKnnObj=new Result_KNN();

                ResultDetails resultDetailsObj=resultKnnObj.test();
                String[] resultCountNextLine=resultDetailsObj.getMessage().split("\n");

                this.setResultDetailsObj(resultDetailsObj);
                //Set the rowdata table
                int i;

                this.windowBuilder.table.setModel(new DefaultTableModel(this.windowBuilder.getRowData(), this.windowBuilder.getColumnNames()));
                for(i=0;i<this.getResultDetailsObj().getDatasetResultCheckObject_KNN().size();i++)
                {
                    this.windowBuilder.getRowData()[i][0]=getBaseName(this.getResultDetailsObj().getDatasetResultCheckObject_KNN().get(i).getFilename());
                    this.windowBuilder.getRowData()[i][1]=this.getResultDetailsObj().getDatasetResultCheckObject_KNN().get(i).getExpectedoutput();
                    this.windowBuilder.getRowData()[i][2]=this.getResultDetailsObj().getDatasetResultCheckObject_KNN().get(i).getActualoutput();

                }

                this.windowBuilder.table.setModel(new DefaultTableModel(this.windowBuilder.getRowData(), this.windowBuilder.getColumnNames()));

                for(i=0;i<this.windowBuilder.getColumnNames().length;i++)
                {
                    System.out.println("Column Class="+this.windowBuilder.table.getColumnClass(i));
                    this.windowBuilder.table.setDefaultRenderer(this.windowBuilder.table.getColumnClass(i), new ResultWindowRowRenderer());

                }

                this.windowBuilder.table.setModel(new DefaultTableModel(this.windowBuilder.getRowData(), this.windowBuilder.getColumnNames()));

                int popupLength=0;
                int popupBreadth=0;
                if(resultCountNextLine.length==1)
                {
                    popupLength=200;
                    popupBreadth=200;
                }
                else if(resultCountNextLine.length==2)
                {
                    popupLength=300;
                    popupBreadth=200;
                }
                else if(resultCountNextLine.length==3)
                {
                    popupLength=450;
                    popupBreadth=200;
                }
                else if(resultCountNextLine.length==4)
                {
                    popupLength=550;
                    popupBreadth=200;
                }


                UIManager.put(JOptionPane.DEFAULT_OPTION,new Dimension(popupLength,popupBreadth));
                JOptionPane.showMessageDialog(null,  resultDetailsObj.getMessage(),"Output Message",JOptionPane.PLAIN_MESSAGE);

                //Delete the content of the file
                writer = new FileOutputStream(output_file);
            }
        }
        catch (IOException e)
        {
            System.out.println("Exception0");
            JOptionPane.showMessageDialog(textArea, "Can't save file "
                    + e.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println("Exception1");
            System.out.println(ex.getMessage());
        }
    }

    public static String getBaseName(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return fileName;
        } else {
            return fileName.substring(0, index);
        }
    }
}