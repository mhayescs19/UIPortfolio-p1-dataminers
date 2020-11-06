package UnitConverter;

import java.util.Scanner;

public class UnitConsole {
    private UnitControll console;
    private double arg1;
    private boolean exit = false;
    private UnitControll.Methods Methods;

    public UnitConsole() {
        arg1 = 0.0;
        console = new UnitControll();
    }

    public void SetArg1(double pass) throws Exception {
        arg1 = pass;
        console.Arg1Setter(arg1);
        MethodCheck();
        System.out.println("New unit = "+ this.arg1);
    }
    public void MethodCheck() throws Exception {
       this.arg1= console.MethodCheck(Methods);
    }
    public void ConsoleUi() throws Exception {
        int number;
        String exitinput;
        while (!exit)

        {
            System.out.println("Enter the corresponding number to select that conversion");
            System.out.println("1 = fahrenheit to celsius\n"+"2= celsius to fahrenheit\n"+"3= Feet to Inches\n"+"4= Inches to Feet\n"+"5= Meeter to Centimeter\n"+"6= Centimeter to Meeter");
            Scanner input = new Scanner(System.in);
            number = input.nextInt();
            switch (number) {
                case 1 -> Methods = UnitControll.Methods.FTOC;
                case 2 -> Methods = UnitControll.Methods.CTOF;
                case 3 -> Methods = UnitControll.Methods.FTOI;
                case 4 -> Methods = UnitControll.Methods.ITOF;
                case 5 -> Methods = UnitControll.Methods.MTOCM;
                case 6 -> Methods = UnitControll.Methods.CMTOM;
            }
            System.out.println("enter the number of units you wish to convert");
            input =new Scanner(System.in);
            SetArg1(input.nextDouble());
            input = new Scanner(System.in);
            System.out.println("Do you wish to continue to use console y/n");
            exitinput = input.nextLine();
            if(exitinput.equals("N")||exitinput.equals("n"))
            {
                exit= true;
                System.out.println("returning to ui");
            }





        }
    }








}
