package UnitConverter;

public class UnitControll {

    private double arg1;
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
        this.arg1 = this.arg1*12;
        return this.arg1;
    }
    public double ItoF()
    {
        this.arg1 = this.arg1/12;
        return this.arg1;
    }



    







}
