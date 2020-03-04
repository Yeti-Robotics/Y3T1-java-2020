package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;


public class DriveForDistanceCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrainSubsystem;
    private double distanceGoal;
    private double leftPower;
    private double rightPower;

    public DriveForDistanceCommand(DrivetrainSubsystem drivetrainSubsystem, double encoderGoal, double leftPower, double rightPower) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.distanceGoal = encoderGoal;
        this.leftPower = leftPower;
        this.rightPower = rightPower;
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        drivetrainSubsystem.resetEncoder();
    }

    @Override
    public void execute() {

        System.out.println("drive train encoder value" + drivetrainSubsystem.getAverageEncoder());
        System.out.println("left, right" + leftPower +";" + rightPower);
        drivetrainSubsystem.drive(leftPower, rightPower);
    }

    @Override
    public boolean isFinished() {
        System.out.println("is finished!!!!!!!!");
        System.out.println(this.drivetrainSubsystem.getAverageEncoder() >= distanceGoal);
        return this.drivetrainSubsystem.getAverageEncoder() >= distanceGoal;

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("is ended !!!!!!!!!!");
        drivetrainSubsystem.stopDrive();
    }
}
