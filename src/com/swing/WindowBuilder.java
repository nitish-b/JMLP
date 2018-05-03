package com.swing;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import CheckResult.Result_KNN;
import CheckResult.Result_TfIdf;
import Result.ResultDetails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class WindowBuilder
{

    private JFrame mainFrame;
    private JFrame resultFrame;
    private JPanel panel;
    private JPanel controlPanel;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private RSyntaxTextArea textArea;
    private BoxLayout boxLayout;
    public JTable table;
    private JScrollPane scrollPane;
    private ML_Project mlProject;
    private HashMap<String,String> problemNameToClassMapping;
    private ResultDetails resultDetailsObj;

    private String folderPath=new String();

    private Object  rowData[][] = {  { null,null,null },{ null,null,null },{ null,null,null },
                                    { null,null,null }
                                  };
    private Object columnNames[] = { "Dataset","Expected Output", "Actual Output" };
    private String selectedProblemName=new String();
    private java.lang.String[][] instructorRowData= new java.lang.String[][]{ { "Student1", "2","2" },
            { "Student2", "3","1" },{ "Student3", "1","3" },{ "Student4", "4","0" },{ "Student5", "4","0" },{ "Student6", "1","3" } };

    private java.lang.String[] instructorColumn= { "Number Of Students", "Correct","Wrong" };
    private JTable instructortable;

    public Object[] getColumnNames()
    {
        return columnNames;
    }

    public Object[][] getRowData()
    {
        return rowData;
    }

    public void setResultDetailsObj(ResultDetails resultDetailsObj)
    {
        this.resultDetailsObj = resultDetailsObj;
    }

    public WindowBuilder()
    {
        prepareGUI();

        problemNameToClassMapping=new HashMap<>();
        problemNameToClassMapping.put("TfIdf","ML_Project");
        problemNameToClassMapping.put("NaiveBayes","ML_Project");
        problemNameToClassMapping.put("KNN","ML_Project");
        problemNameToClassMapping.put("LogisticRegression","ML_Project");

    }

    private void prepareGUI()
    {
	    mainFrame = new JFrame("JMLP");
	    mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
	    Box box = Box.createVerticalBox();
	    box.add(Box.createVerticalStrut(25));

	    JPanel controlpanel=new JPanel();
	    controlpanel.setLayout(new FlowLayout());
	    controlpanel.setSize(200,600);

	    controlpanel.add(new JLabel("JMLP: A Machine Learning Tool."));
	    controlpanel.add(new JLabel(""));

	    JButton jbutton1 = new JButton("Naive Bayes");
	    jbutton1.setPreferredSize(new Dimension(300, 30));
	    jbutton1.setForeground(Color.black);
	    jbutton1.setFont(new Font("Sans Serif", Font.BOLD, 15));
	    controlpanel.add(jbutton1);

	    jbutton1.addActionListener(ae ->
	    {
		    this.selectedProblemName="NaiveBayes";
		    textEditor(this.selectedProblemName);
		    mainFrame.setVisible(false);
		    resultGUI();
		    int i;
		    for(i=0;i<this.rowData.length;i++)
		    {
			    this.rowData[i][0]=null;
			    this.rowData[i][1]=null;
			    this.rowData[i][2]=null;

		    }
		    this.table.setModel(new DefaultTableModel(this.getRowData(), this.getColumnNames()));

	    });

	    jbutton1.addMouseListener(new MouseAdapter()
	    {
		    public void mouseEntered(MouseEvent evt)
		    {
			    jbutton1.setBackground((new Color(204,229,255)));

		    }
		    public void mouseExited(MouseEvent evt)
		    {
			    jbutton1.setBackground(UIManager.getColor("control"));
		    }
	    });


	    JButton jbutton2 = new JButton("Logistic Regression");
	    jbutton2.setPreferredSize(new Dimension(300, 30));
	    jbutton2.setForeground(Color.black);
	    jbutton2.setFont(new Font("Sans Serif", Font.BOLD, 15));
	    controlpanel.add(jbutton2);
	    jbutton2.addActionListener(ae ->
	    {
		    this.selectedProblemName="Logistic Regression";
		    textEditor(this.selectedProblemName);
		    mainFrame.setVisible(false);
		    resultGUI();
		    int i;
		    for(i=0;i<this.rowData.length;i++)
		    {
			    this.rowData[i][0]=null;
			    this.rowData[i][1]=null;
			    this.rowData[i][2]=null;

		    }
		    this.table.setModel(new DefaultTableModel(this.getRowData(), this.getColumnNames()));


	    });

	    jbutton2.addMouseListener(new MouseAdapter()
	    {
		    public void mouseEntered(MouseEvent evt)
		    {
			    jbutton2.setBackground((new Color(204,229,255)));
		    }

		    public void mouseExited(MouseEvent evt)
		    {
			    jbutton2.setBackground(UIManager.getColor("control"));
		    }
	    });



	    JButton jbutton3 = new JButton("TF/IDF");
	    jbutton3.setForeground(Color.black);
	    jbutton3.setPreferredSize(new Dimension(300, 30));
	    jbutton3.setFont(new Font("Sans Serif", Font.BOLD, 15));
	    controlpanel.add(jbutton3);
	    jbutton3.addActionListener(ae ->
	    {
		    this.selectedProblemName="TfIdf";
		    textEditor(this.selectedProblemName);
		    mainFrame.setVisible(false);
		    resultGUI();
		    int i;
		    for(i=0;i<this.rowData.length;i++)
		    {
			    this.rowData[i][0]=null;
			    this.rowData[i][1]=null;
			    this.rowData[i][2]=null;

		    }
		    this.table.setModel(new DefaultTableModel(this.getRowData(), this.getColumnNames()));


	    });
	    jbutton3.addMouseListener(new MouseAdapter()
	    {
		    public void mouseEntered(MouseEvent evt)
		    {
			    jbutton3.setBackground((new Color(204,229,255)));
		    }
		    public void mouseExited(MouseEvent evt)
		    {
			    jbutton3.setBackground(UIManager.getColor("control"));
		    }
	    });



	    JButton jbutton4 = new JButton("KNN");

	    jbutton4.setForeground(Color.black);
	    jbutton4.setPreferredSize(new Dimension(300, 30));
	    jbutton4.setFont(new Font("Sans Serif", Font.BOLD, 15));
	    controlpanel.add(jbutton4);
	    jbutton4.addActionListener(ae ->
	    {
		    this.selectedProblemName="KNN";
		    textEditor(this.selectedProblemName);
		    mainFrame.setVisible(false);
		    resultGUI();
		    int i;
		    for(i=0;i<this.rowData.length;i++)
		    {
			    this.rowData[i][0]=null;
			    this.rowData[i][1]=null;
			    this.rowData[i][2]=null;

		    }
		    this.table.setModel(new DefaultTableModel(this.getRowData(), this.getColumnNames()));


	    });
	    jbutton4.addMouseListener(new MouseAdapter()
	    {
		    public void mouseEntered(MouseEvent evt)
		    {
			    jbutton4.setBackground((new Color(204,229,255)));
		    }
		    public void mouseExited(MouseEvent evt)
		    {
			    jbutton4.setBackground(UIManager.getColor("control"));
		    }
	    });


	    JButton jbutton5 = new JButton("Instructor Tool");
	    jbutton5.setPreferredSize(new Dimension(300, 30));
	    jbutton5.setForeground(Color.black);
	    jbutton5.setFont(new Font("Sans Serif", Font.BOLD, 15));
	    controlpanel.add(jbutton5);



	    jbutton5.addMouseListener(new MouseAdapter()
	    {
		    public void mouseEntered(MouseEvent evt)
		    {
			    jbutton5.setBackground((new Color(204,229,255)));
		    }
		    public void mouseExited(MouseEvent evt)
		    {
			    jbutton5.setBackground(UIManager.getColor("control"));
		    }
	    });

	    jbutton5.addActionListener(ae ->
	    {
		    mainFrame.setVisible(false);
		    getInput();
	    });

	    box.add(controlpanel);
	    box.add(Box.createVerticalStrut(10));

	    mainFrame.add(box, BorderLayout.CENTER);
	    mainFrame.setSize(300, 300);
	    mainFrame.setVisible(true);

    }

    protected void textEditor(String problemName)
    {

        mlProject = new ML_Project(this,problemName);
        textArea = mlProject.getTextArea();
        this.setResultDetailsObj(mlProject.getResultDetailsObj());
        textArea.requestFocusInWindow();

    }

    protected void getInput()
    {

        JFrame  inputFrame = new JFrame("Give Input");
        inputFrame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowEvent)
            {
                inputFrame.dispose();
                mainFrame.setVisible(true);

            }
        });

        inputFrame.setVisible(true);
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setSize(500, 130);
        inputFrame.setLocation(430, 100);

        JPanel panel=new JPanel();
        inputFrame.add(panel);

        JLabel lbl = new JLabel("Algorithm::");
        lbl.setVisible(true);

        panel.add(lbl);

        String[] choices = { "KNN","TfIdf", "NaiveBayes","Logistic Regression"};
        final JComboBox<String> cb=new JComboBox<>(choices);
        cb.setVisible(true);
        panel.add(cb);

        JButton btn = new JButton("Upload Solutions");
        panel.add(btn);

        JLabel folderpathLabel= new JLabel("");
        panel.add(folderpathLabel);

        JButton submitbtn = new JButton("Submit");
        panel.add(submitbtn);

        btn.addActionListener(ae ->
        {

            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                folderPath=selectedFile.getAbsolutePath();
                folderpathLabel.setText("Selected Folder Directory :: "+ "/home/username/StudentSolutionFolder");
            }


        });

        submitbtn.addActionListener(ae ->
        {
            if(folderPath.length()==0)
            {
                JOptionPane.showMessageDialog(null,  "Please upload Student Solutions Folder:"+cb.getSelectedItem().toString(),"Error",JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
                String problemname=cb.getSelectedItem().toString();
                try
                {
                    instructorTool(folderPath,problemname);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        });





    }
    protected void instructorTool(String folderPath,String problemname) throws Exception
    {

        String destination_folder_path = System.getProperty("user.dir") + System.getProperty("file.separator") + "InstructorSolution" + System.getProperty("file.separator") + "InstructorSolution";
        File source=new File(folderPath);
        File destination= new File(destination_folder_path);

        File[] listOfFiles = destination.listFiles();
        Arrays.sort(listOfFiles);

        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(instructorRowData,instructorColumn);
        instructortable = new JTable(dm);

        System.out.println("Length::"+listOfFiles.length);
        for (int counter = 0; counter < listOfFiles.length; counter++)
        {
            System.out.println(listOfFiles[counter].getAbsolutePath());
            if(listOfFiles[counter].isFile())
            {
                int correct=0;
                int wrong=0;

                if(problemname.equals("TfIdf"))
                {
                    String output_file=System.getProperty("user.dir")+System.getProperty("file.separator")+"res"+System.getProperty("file.separator")+"output"+System.getProperty("file.separator")+"tfIdf_Output.txt";
                    FileOutputStream writer = new FileOutputStream(output_file);

                    Process theProcess = null;
                    Result_TfIdf resultTfIdfObj=new Result_TfIdf();

                    ResultDetails resultDetailsObj=resultTfIdfObj.test();

                    for(int i=0;i<resultDetailsObj.getDatasetResultCheckObject_TfIdf().size();i++)
                    {
                        if(resultDetailsObj.getDatasetResultCheckObject_TfIdf().get(i).getExpectedoutput()==resultDetailsObj.getDatasetResultCheckObject_TfIdf().get(i).getActualoutput())
                        {
                            correct++;
                        }
                        else
                        {
                            wrong++;
                        }

                    }
                    writer = new FileOutputStream(output_file);


                }

                if(problemname.equals("KNN"))
                {
                    String output_file=System.getProperty("user.dir")+System.getProperty("file.separator")+"res"+System.getProperty("file.separator")+"output"+System.getProperty("file.separator")+"knn_Output.txt";
                    FileOutputStream writer = new FileOutputStream(output_file);

                    Process theProcess = null;
                    Result_KNN resultKNNObj=new Result_KNN();

                    ResultDetails resultDetailsObj=resultKNNObj.test();

                    for(int i=0;i<resultDetailsObj.getDatasetResultCheckObject_KNN().size();i++)
                    {
                        if(resultDetailsObj.getDatasetResultCheckObject_KNN().get(i).getExpectedoutput()==resultDetailsObj.getDatasetResultCheckObject_KNN().get(i).getActualoutput())
                        {
                            correct++;
                        }
                        else
                        {
                            wrong++;
                        }

                    }
                    writer = new FileOutputStream(output_file);


                }

                instructorRowData[counter][0]="Student"+Integer.toString(counter+1);
                instructorRowData[counter][1]=Integer.toString(correct);
                instructorRowData[counter][2]=Integer.toString(wrong);
            }
        }

        instructortable.setModel(new DefaultTableModel(instructorRowData, instructorColumn));

        JFrame  instructorFrame = new JFrame("Instructor Tool");
        instructorFrame.setSize(450, 400);
        instructorFrame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowEvent)
            {
                instructorFrame.dispose();
                mainFrame.setVisible(true);

            }
        });

        boxLayout = new BoxLayout(instructorFrame.getContentPane(), BoxLayout.Y_AXIS);
        instructorFrame.setLayout(boxLayout);

        headerLabel = new JLabel("Instructor Tool", JLabel.CENTER);


        JButton backToMainMenuButton = new JButton("Back to Menu");
        backToMainMenuButton.setPreferredSize(new Dimension(100, 40));
        backToMainMenuButton.setForeground(Color.black);
        backToMainMenuButton.setFocusPainted(false);
        backToMainMenuButton.setFont(new Font("Sans Serif", Font.BOLD, 15));

        backToMainMenuButton.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                backToMainMenuButton.setBackground((new Color(204,229,255)));
            }
            public void mouseExited(MouseEvent evt)
            {
                backToMainMenuButton.setBackground(UIManager.getColor("control"));
            }
        });

        backToMainMenuButton.addActionListener(ae ->
        {
            mainFrame.setVisible(true);
            instructorFrame.dispose();
        });

        instructortable.getColumn("Number Of Students").setCellRenderer(new ButtonRenderer());


        scrollPane = new JScrollPane( instructortable );
        scrollPane.setPreferredSize(new Dimension(100,100));
        for (int i =0; i<columnNames.length;i++)
        {

        }

        double avgCorrectAns=0;
        double medianCorrectAns=0;
        double sdCorrectAns=0;

        int totalcorrect=0;
        int length=instructorRowData.length;
        double [] correctArr=new double[length];
        for(int counter=0;counter<instructorRowData.length;counter++)
        {
            totalcorrect=totalcorrect+ Integer.parseInt(instructorRowData[counter][1].toString());
            correctArr[counter]=Double.parseDouble(instructorRowData[counter][1].toString());
        }

        avgCorrectAns=(totalcorrect/instructorRowData.length);
        Median median=new Median();
        medianCorrectAns=median.evaluate(correctArr);

        double standardDeviation=0;
        for(int counter=0;counter<instructorRowData.length;counter++)
        {
            int num=Integer.parseInt(instructorRowData[counter][1].toString());
            standardDeviation=standardDeviation+Math.pow(num-avgCorrectAns,2);
        }
        sdCorrectAns=Math.sqrt(standardDeviation/instructorRowData.length);
        //Median
        JLabel correctLabel= new JLabel("Average Number Of Correct Answer="+avgCorrectAns,JLabel.CENTER);
        JLabel medianLabel= new JLabel("Median Of Correct Answer="+medianCorrectAns,JLabel.CENTER);
        JLabel sdLabel= new JLabel("Standard Deviation Of Correct Answer="+sdCorrectAns,JLabel.CENTER);


        JPanel statPanel=new JPanel();
        FlowLayout flowlayout=new FlowLayout();
        statPanel.setLayout(flowlayout);
        statPanel.add(correctLabel);
        statPanel.add(medianLabel);
        statPanel.add(sdLabel);

        instructorFrame.add(Box.createVerticalStrut(20));
        instructorFrame.add(backToMainMenuButton, BorderLayout.CENTER);
        backToMainMenuButton.setAlignmentX(backToMainMenuButton.CENTER_ALIGNMENT);
        instructorFrame.add(Box.createVerticalStrut(20));
        instructorFrame.add(scrollPane, BorderLayout.CENTER );
        instructorFrame.add(statPanel);
        instructorFrame.setVisible(true);

    }

    protected void resultGUI()
    {
        resultFrame = new JFrame("Result "+this.selectedProblemName);
        resultFrame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowEvent)
            {
                resultFrame.dispose();
                mainFrame.setVisible(true);

            }
        });

        boxLayout = new BoxLayout(resultFrame.getContentPane(), BoxLayout.Y_AXIS);
        resultFrame.setLayout(boxLayout);

        headerLabel = new JLabel("Result", JLabel.CENTER);


        JButton backToMainMenuButton = new JButton("Back to Menu");
        backToMainMenuButton.setPreferredSize(new Dimension(100, 40));
        backToMainMenuButton.setForeground(Color.black);
        backToMainMenuButton.setFocusPainted(false);
        backToMainMenuButton.setFont(new Font("Sans Serif", Font.BOLD, 12));


        backToMainMenuButton.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                backToMainMenuButton.setBackground((new Color(204,229,255)));
            }
            public void mouseExited(MouseEvent evt)
            {
                backToMainMenuButton.setBackground(UIManager.getColor("control"));
            }
        });

        backToMainMenuButton.addActionListener(ae ->
        {
            mlProject.destroyGUI();
            mainFrame.setVisible(true);
            resultFrame.dispose();
        });

        table = new JTable(rowData,columnNames);
        table.setRowHeight(30);
        table.setGridColor((new Color(204,229,255)));

        scrollPane = new JScrollPane( table );


        for (int i =0; i<columnNames.length;i++) {
        }
        resultFrame.add(Box.createVerticalStrut(20));
        resultFrame.add(backToMainMenuButton, BorderLayout.CENTER);
        backToMainMenuButton.setAlignmentX(backToMainMenuButton.CENTER_ALIGNMENT);
        resultFrame.add(scrollPane, BorderLayout.CENTER );

        resultFrame.pack();
        resultFrame.setVisible(true);


    }



    private void showGridLayout()
    {
        headerLabel.setText("JMLP: A Machine Learning Tool");

        panel = new JPanel();

        GridLayout layout = new GridLayout(2,2);
        layout.setHgap(10);
        layout.setVgap(10);

        JButton jbutton1 = new JButton("Naive Bayes");
        jbutton1.setPreferredSize(new Dimension(130, 80));
        jbutton1.setForeground(Color.black);
        jbutton1.setFocusPainted(false);
        jbutton1.setFont(new Font("Sans Serif", Font.BOLD, 10));
        panel.add(jbutton1);


        jbutton1.addActionListener(ae ->
        {
            statusLabel.setText("Naive Bayes was selected.");
            this.selectedProblemName="NaiveBayes";
            textEditor(this.selectedProblemName);
            mainFrame.setVisible(false);
            resultGUI();
            int i;
            for(i=0;i<this.rowData.length;i++)
            {
                this.rowData[i][0]=null;
                this.rowData[i][1]=null;
                this.rowData[i][2]=null;

            }
            this.table.setModel(new DefaultTableModel(this.getRowData(), this.getColumnNames()));

        });

        jbutton1.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                jbutton1.setBackground((new Color(204,229,255)));
            }
            public void mouseExited(MouseEvent evt)
            {
                jbutton1.setBackground(UIManager.getColor("control"));
            }
        });


        JButton jbutton2 = new JButton("Logistic Regression");
        jbutton2.setPreferredSize(new Dimension(130, 80));
        jbutton2.setForeground(Color.black);
        jbutton2.setFocusPainted(false);
        jbutton2.setFont(new Font("Sans Serif", Font.BOLD, 9));
        panel.add(jbutton2);
        jbutton2.addActionListener(ae ->
        {
            statusLabel.setText("Logistic Regression was selected.");
            this.selectedProblemName="Logistic Regression";
            textEditor(this.selectedProblemName);
            mainFrame.setVisible(false);
            resultGUI();
            int i;
            for(i=0;i<this.rowData.length;i++)
            {
                this.rowData[i][0]=null;
                this.rowData[i][1]=null;
                this.rowData[i][2]=null;

            }
            this.table.setModel(new DefaultTableModel(this.getRowData(), this.getColumnNames()));


        });

        jbutton2.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                jbutton2.setBackground((new Color(204,229,255)));
            }

            public void mouseExited(MouseEvent evt)
            {
                jbutton2.setBackground(UIManager.getColor("control"));
            }
        });



        JButton jbutton3 = new JButton("TF/IDF");
        jbutton3.setPreferredSize(new Dimension(100, 80));
        jbutton3.setForeground(Color.black);
        jbutton3.setFocusPainted(false);
        jbutton3.setFont(new Font("Sans Serif", Font.BOLD, 10));
        panel.add(jbutton3);
        jbutton3.addActionListener(ae ->
        {
            statusLabel.setText("TF/IDF was selected.");
            this.selectedProblemName="TfIdf";
            textEditor(this.selectedProblemName);
            mainFrame.setVisible(false);
            resultGUI();
            int i;
            for(i=0;i<this.rowData.length;i++)
            {
                this.rowData[i][0]=null;
                this.rowData[i][1]=null;
                this.rowData[i][2]=null;

            }
            this.table.setModel(new DefaultTableModel(this.getRowData(), this.getColumnNames()));


        });
        jbutton3.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                jbutton3.setBackground((new Color(204,229,255)));
            }
            public void mouseExited(MouseEvent evt)
            {
                jbutton3.setBackground(UIManager.getColor("control"));
            }
        });



        JButton jbutton4 = new JButton("KNN");
        jbutton4.setPreferredSize(new Dimension(100, 80));
        jbutton4.setForeground(Color.black);
        jbutton4.setFocusPainted(false);
        jbutton4.setFont(new Font("Sans Serif", Font.BOLD, 10));
        panel.add(jbutton4);
        jbutton4.addActionListener(ae ->
        {
            statusLabel.setText("KNN was selected.");
            this.selectedProblemName="KNN";
            textEditor(this.selectedProblemName);
            mainFrame.setVisible(false);
            resultGUI();
            int i;
            for(i=0;i<this.rowData.length;i++)
            {
                this.rowData[i][0]=null;
                this.rowData[i][1]=null;
                this.rowData[i][2]=null;

            }
            this.table.setModel(new DefaultTableModel(this.getRowData(), this.getColumnNames()));


        });
        jbutton4.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                jbutton4.setBackground((new Color(204,229,255)));
            }
            public void mouseExited(MouseEvent evt)
            {
                jbutton4.setBackground(UIManager.getColor("control"));
            }
        });


	    panel.isVisible();

        mainFrame.add(panel);
        controlPanel.add(panel);
        mainFrame.setVisible(true);

    }

    public static void main(String[] args)
    {
        new WindowBuilder();
    }
}


class ButtonRenderer extends JButton implements TableCellRenderer
{

    public ButtonRenderer()
    {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor
{
    protected JButton button;

    private String label;

    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {

            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        }
        isPushed = false;
        return new String(label);
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}