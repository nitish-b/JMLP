package Result;

public class DatasetResultCheck_KNN
{
    String filename;
    int expectedoutput;
    int actualoutput;

    public DatasetResultCheck_KNN(String filename,int expectedoutput,int actualoutput)
    {
        this.filename=filename;
        this.expectedoutput=expectedoutput;
        this.actualoutput=actualoutput;
    }


    public String getFilename()
    {
        return this.filename;
    }


    public int getExpectedoutput()
    {
        return this.expectedoutput;
    }

    public int getActualoutput()
    {
        return this.actualoutput;
    }

}
