package util;


import static java.lang.Math.*;

public class Operation {
	
	public static enum OPERATOR { NOOP, PLUS, MINUS, DIVIDE, MULTIPLY, LOGy, LOG, POWERy, ACOSIN, ASIN, SQ, SQRT, SQRTy,POSNEG, FRACTION}
	public static double calculateIt(double arg1, OPERATOR mathOp, double arg2) {
		
		double calcAnswer;		
		switch(mathOp)
	    {
	        case PLUS:
	            calcAnswer = arg1 + arg2;
	            break;
	        case MINUS:
	            calcAnswer = arg1 - arg2;
	            break;
	        case DIVIDE:
	            calcAnswer = arg1 / arg2;
	            break;
	        case MULTIPLY:
	            calcAnswer = arg1 * arg2;
	            break;
			case LOGy:
				calcAnswer = log(arg2) / log(arg1);
				break;
			case LOG:
				calcAnswer = log10(arg1);
				break;
			case ACOSIN:
				if(arg1>arg2)
				{
					double temp;
					temp = arg1;
					arg1 = arg2;
					arg2 =temp;

				}
			calcAnswer=acos(arg1/arg2);
			break;
			case ASIN:
				if(arg1>arg2)
				{
					double temp;
					temp = arg1;
					arg1 = arg2;
					arg2 =temp;

				}
				calcAnswer = asin(arg1/arg2);
				break;
			case POWERy:calcAnswer = pow(arg1,arg2);
				break;
			case SQ:
				calcAnswer = pow(arg1,2);
				break;
			case SQRT:
				calcAnswer = sqrt(arg1);
				break;
			case SQRTy:
				calcAnswer = pow(arg2,1.0/arg1);
				break;
			case POSNEG:
				calcAnswer = -1 * arg1;
				break;
			case FRACTION:
				calcAnswer = 1 / arg1;
				break;
	        case NOOP:
	        default:	
	            calcAnswer = arg1;
	    }
		return calcAnswer;
	}
}
