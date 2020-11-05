package UnitConverter;

public class UnitControll {

    private double arg1;
    public enum Methods {CTOF,FTOC,FTOI,ITOF,MTOCM,CMTOM,}
    public UnitControll()
    {
        arg1 = 0.0;
    }
    public void Arg1Setter(double pass1)
    {
        this.arg1 = pass1;

    }
    public double CToF()
    {
        this.arg1 = (arg1*(9.0/5.0))+32;
        return arg1;
    }
    public double FtoC()
    {
        this.arg1 = (arg1-32) *5.0/9.0;
        return this.arg1;
    }
    public double Ftoi()
    {
        this.arg1 = this.arg1*12.0;
        return this.arg1;
    }
    public double ItoF()
    {
        this.arg1 = this.arg1/12.0;
        return this.arg1;
    }
    public double MeeterToCm()
    {
        this.arg1 = this.arg1*100.0;
        return this.arg1;
    }
    public double CentimeterToMeeter()
    {
        this.arg1 = this.arg1/100.0;
        return this.arg1;
    }

    public double MethodCheck(Methods pass) throws Exception {
        switch (pass) {
            case CTOF: return CToF();
            case FTOC: return FtoC();
            case FTOI: return Ftoi();
            case ITOF: return ItoF();
            case CMTOM: return CentimeterToMeeter();
            case MTOCM: return MeeterToCm();
            default: throw new Exception("enum has not been set");
        }
    }






    







}
