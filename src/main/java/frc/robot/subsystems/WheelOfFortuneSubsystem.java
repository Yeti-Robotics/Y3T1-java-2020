package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WheelOfFortuneSubsystem extends SubsystemBase {
    public enum WheelColor {
        RED, YELLOW, GREEN, BLUE, IDKBRO
    }

    public static WheelColor wheelColor;

    private TalonSRX talon;
    private ColorSensorV3 colorSensor;
//    public final static WheelOfFortuneSubsystem INSTANCE = new WheelOfFortuneSubsystem();

    public WheelOfFortuneSubsystem() {
        talon = new TalonSRX(Constants.WHEEL_OF_FORTUNE_TALON);
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    }

    /**
     * Returns the Singleton instance of this WheelOfFortuneSubsystem. This static method
     * should be used -- {@code WheelOfFortuneSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
//    public static WheelOfFortuneSubsystem getInstance() {
//        return INSTANCE;
//    }

    @Override
    public void periodic() {
        Color detectedColor = colorSensor.getColor();

        if(detectedColor.red > 0.5 && detectedColor.green < 0.4 && detectedColor.blue < 0.3) {
            System.out.println("it's a red");
            wheelColor = WheelColor.RED;
        } else if(detectedColor.red < 0.3 && detectedColor.green > 0.5 && detectedColor.blue < 0.3) {
            System.out.println("she's green");
            wheelColor = WheelColor.GREEN;
        } else if(detectedColor.red < 0.2 && detectedColor.blue > 0.3 && detectedColor.green > 0.3){
            System.out.println("iT'S cYaN. it's blue but ok karen");
            wheelColor = WheelColor.BLUE;
        } else if(detectedColor.red > 0.3 && detectedColor.green > 0.5 && detectedColor.blue < 0.3){
            System.out.println("yellow :)");
            wheelColor = WheelColor.YELLOW;
        } else {
            // System.out.println("idk bro");
            wheelColor = WheelColor.IDKBRO;
        }
    }

    public void moveWheel(double power){
        talon.set(ControlMode.PercentOutput, power);
    }

    public void stopWheel(){
        talon.set(ControlMode.PercentOutput, 0);
    }

    public WheelColor getColor(){
        return wheelColor;
    }
}

