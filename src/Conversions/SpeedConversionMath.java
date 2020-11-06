package Conversions;
import static java.lang.Math.*;

public class SpeedConversionMath {

    public static enum SpdConversion {MphtoKph, MphtoFps, MphtoMeterPerSecond, MphtoKnot, KphtoKnots,KphtoMetersPerSecond,FeetperSecondtoKph};
    public static double Speed(double speed1, SpdConversion conversion) {
        double outputSpeed;
        switch (conversion){
            case MphtoKph:
                outputSpeed = speed1* 1.609;
                break;
            case MphtoFps:
                outputSpeed = speed1 *1.467;
                break;
            case MphtoMeterPerSecond:
                outputSpeed = speed1/2.237;
                break;
            case MphtoKnot:
                outputSpeed = speed1/(1.151);
                break;
            case KphtoKnots:
                outputSpeed = speed1/1.852;
                break;
            case KphtoMetersPerSecond:
                outputSpeed = speed1/3.6;
                break;
            case FeetperSecondtoKph:
                outputSpeed = speed1 * 1.097;
                break;
            default:
                outputSpeed = speed1;
        }
        return outputSpeed;
    }
}
