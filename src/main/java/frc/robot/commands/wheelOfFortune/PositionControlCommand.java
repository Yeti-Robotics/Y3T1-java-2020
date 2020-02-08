package frc.robot.commands.wheelOfFortune;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelOfFortuneSubsystem;
import frc.robot.subsystems.WheelOfFortuneSubsystem.WheelColor;
import edu.wpi.first.wpilibj.DriverStation;

public class PositionControlCommand extends CommandBase {
    private final WheelOfFortuneSubsystem wheelOfFortuneSubsystem;
    private String gameData;
    private WheelColor finalColor;

    public PositionControlCommand(WheelOfFortuneSubsystem wheelOfFortuneSubsystem) {
        this.wheelOfFortuneSubsystem = wheelOfFortuneSubsystem;
        addRequirements(wheelOfFortuneSubsystem);
    }

    @Override
    public void initialize() {

        gameData = DriverStation.getInstance().getGameSpecificMessage();
        
        if ((gameData.charAt(0)) == 'Y') {
            finalColor = WheelColor.GREEN;
        } else if ((gameData.charAt(0)) == 'G') {
            finalColor = WheelColor.YELLOW;
        } else if ((gameData.charAt(0)) == 'R') {
            finalColor = WheelColor.BLUE;
        } else if ((gameData.charAt(0)) == 'B') {
            finalColor = WheelColor.RED;
        } else {
            finalColor = WheelColor.IDKBRO;
        }

    }

    @Override
    public void execute() {
        wheelOfFortuneSubsystem.moveWheel(.4);
        System.out.println("wanted color: " + finalColor + ". current color: " + WheelOfFortuneSubsystem.wheelColor);
    }

    @Override
    public boolean isFinished() {
        return WheelOfFortuneSubsystem.wheelColor == finalColor;
    }

    @Override
    public void end(final boolean interrupted) {
        wheelOfFortuneSubsystem.stopWheel();
    }
}
