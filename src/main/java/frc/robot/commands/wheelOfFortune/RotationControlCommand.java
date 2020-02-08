package frc.robot.commands.wheelOfFortune;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelOfFortuneSubsystem;
import frc.robot.subsystems.WheelOfFortuneSubsystem.WheelColor;


public class RotationControlCommand extends CommandBase {
    private final WheelOfFortuneSubsystem wheelOfFortuneSubsystem;
    private int colorCount;
    private WheelColor previousColor;
   

    public RotationControlCommand(WheelOfFortuneSubsystem wheelOfFortuneSubsystem) {
        this.wheelOfFortuneSubsystem = wheelOfFortuneSubsystem;
        addRequirements(wheelOfFortuneSubsystem);
    }

    @Override
    public void initialize() {
        colorCount = 0;
        previousColor = WheelOfFortuneSubsystem.wheelColor;
      
    }

    @Override
    public void execute() {
        wheelOfFortuneSubsystem.moveWheel(.4);
        if (WheelOfFortuneSubsystem.wheelColor != previousColor && WheelOfFortuneSubsystem.wheelColor != WheelColor.IDKBRO){
            colorCount++;
            previousColor = WheelOfFortuneSubsystem.wheelColor;
        }
    }

    @Override
    public boolean isFinished() {
        return colorCount >= 30;
    }



    @Override
    public void end(boolean interrupted) {
        wheelOfFortuneSubsystem.moveWheel(0);
    }
}
