package Result;

public class DatasetResultCheck_TfIdf
{
    String filename;
    double expectedoutput;
    double actualoutput;

    public DatasetResultCheck_TfIdf(String filename,double expectedoutput,double actualoutput)
    {
        this.filename=filename;
        this.expectedoutput=expectedoutput;
        this.actualoutput=actualoutput;
    }


    public String getFilename()
    {
        return this.filename;
    }

    public double getExpectedoutput()
    {
        return this.expectedoutput;
    }

    public double getActualoutput()
    {
        return this.actualoutput;
    }
}
