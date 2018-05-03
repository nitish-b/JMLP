package Result;

import java.util.ArrayList;


public class ResultDetails
{

    String message="";
    ArrayList<DatasetResultCheck_KNN> datasetResultCheckObject_KNN=new ArrayList<DatasetResultCheck_KNN>();
    ArrayList<DatasetResultCheck_TfIdf> datasetResultCheckObject_TfIdf=new ArrayList<DatasetResultCheck_TfIdf>();

    public ResultDetails()
    {
     //this.message=message;
     //this.datasetResultCheckObject=datasetResultCheckObject;
    }

    public String getMessage()
    {
        return this.message;
    }

    public ArrayList<DatasetResultCheck_KNN> getDatasetResultCheckObject_KNN()
    {
        return this.datasetResultCheckObject_KNN;
    }

    public ArrayList<DatasetResultCheck_TfIdf> getDatasetResultCheckObject_TfIdf()
    {
        return this.datasetResultCheckObject_TfIdf;
    }

    public void setDatasetResultCheckObject_KNN(ArrayList<DatasetResultCheck_KNN> datasetResultCheckObject)
    {
        this.datasetResultCheckObject_KNN = datasetResultCheckObject;
    }

    public void setDatasetResultCheckObject_TfIdf(ArrayList<DatasetResultCheck_TfIdf> datasetResultCheckObject_TfIdf)
    {
        this.datasetResultCheckObject_TfIdf = datasetResultCheckObject_TfIdf;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void addObjectToDatasetResultCheck_KNN(DatasetResultCheck_KNN obj)
    {
     this.datasetResultCheckObject_KNN.add(obj);
    }

    public void addObjectToDatasetResultCheck_TfIdf(DatasetResultCheck_TfIdf obj)
    {
        this.datasetResultCheckObject_TfIdf.add(obj);
    }

}
