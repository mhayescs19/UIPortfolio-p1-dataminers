package Conversions;

import static java.lang.Math.*;

public class TemperatureConversions {

    public static enum TempConversions {KelvinToCelsius, FarenheitToCelsius, CelsiustoFarenheit, CelsiusToKelvin};
    public static double Temperature(double temp1, TempConversions conversion) {
        double outputTemp;
        switch (conversion){
            case KelvinToCelsius:
                outputTemp = temp1 - 273.15;
                break;
            case CelsiusToKelvin:
                outputTemp = temp1 + 273.15;
                break;
            case FarenheitToCelsius:
                double FiveNinths = 5D / 9D;
                outputTemp = (temp1 -  32) * (FiveNinths);
                break;
            case CelsiustoFarenheit:
                outputTemp = (temp1 * (1.8)) + 32;
                break;
            default:
                outputTemp = temp1;
        }
        return outputTemp;
    }
    }
