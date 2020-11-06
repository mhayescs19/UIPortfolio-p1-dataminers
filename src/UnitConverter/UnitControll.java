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

    public double MethodCheck(Methods pass) {
        return switch (pass) {
            case CTOF -> CToF();
            case FTOC -> FtoC();
            case FTOI -> Ftoi();
            case ITOF -> ItoF();
            case CMTOM -> CentimeterToMeeter();
            case MTOCM -> MeeterToCm();
        };
    }
    public void ConsoleUi() throws Exception { // starts the console version converter
        UnitConsole var = new UnitConsole();
        var.ConsoleUi();
    }






    







}
