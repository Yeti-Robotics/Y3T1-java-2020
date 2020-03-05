/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autoRoutines.ShootCommandGroup;
import frc.robot.commands.climbing.ClimbDownCommand;
import frc.robot.commands.climbing.ClimbUpCommand;
import frc.robot.commands.drivetrain.TurnToTargetCommand;
import frc.robot.commands.hopper.HopperOutCommand;
import frc.robot.commands.intake.IntakeOutCommand;
import frc.robot.commands.intake.ToggleIntakeCommand;
import frc.robot.commands.neck.MoveDownNeckCommand;
import frc.robot.commands.shifting.ToggleShiftingCommand;
import frc.robot.commands.neck.MoveUpNeckCommand;
import frc.robot.commands.hopper.HopperInCommand;
import frc.robot.commands.intake.IntakeInCommand;
import frc.robot.commands.shooting.SetHoodAngleCommand;
import frc.robot.commands.shooting.StartSpinCommand;
import frc.robot.commands.shooting.ToggleShooterCommand;
import frc.robot.subsystems.*;
//import frc.robot.utils.DoubleButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // public final Joystick leftJoy;
  // public final Joystick rightJoy;
  // public final Joystick secondaryJoy;
  // private WheelOfFortuneSubsystem wheelOfFortuneSubsystem;
  public final Joystick driverStationJoy;
  public DrivetrainSubsystem drivetrainSubsystem;
  public IntakeSubsystem intakeSubsystem;
  public NeckSubsystem neckSubsystem;
  public ShooterSubsystem shooterSubsystem;
  public HopperSubsystem hopperSubsystem;
  private ClimberSubsystem climberSubsystem;
  private ShiftGearsSubsystem shiftGearsSubsystem;
  // private DoubleButton toggleClimb;

  public RobotContainer() {
    driverStationJoy = new Joystick(Constants.DRIVER_STATION_JOY);

    drivetrainSubsystem = new DrivetrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    neckSubsystem = new NeckSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    hopperSubsystem = new HopperSubsystem();
    shiftGearsSubsystem = new ShiftGearsSubsystem();
    climberSubsystem = new ClimberSubsystem();

    // JoystickButton button8 = new JoystickButton(driverStationJoy, 8);
    // JoystickButton button11 = new JoystickButton(driverStationJoy, 11);
    // toggleClimb = new DoubleButton( button8, button11);
//    drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.drive(getLeftY(), getRightY()), drivetrainSubsystem));
//    shooterSubsystem.setDefaultCommand(
//        new RunCommand(() -> shooterSubsystem.setServo(getLeftY() ), shooterSubsystem));
    configureButtonBindings();
  }

  private void configureButtonBindings() {

    setJoystickButtonWhileHeld(driverStationJoy, 1, new ParallelCommandGroup(new IntakeOutCommand(intakeSubsystem),
            new MoveDownNeckCommand(neckSubsystem), new HopperOutCommand(hopperSubsystem)));
    setJoystickButtonWhileHeld(driverStationJoy, 6, new ParallelCommandGroup(new IntakeInCommand(intakeSubsystem),
            new MoveUpNeckCommand(neckSubsystem), new HopperInCommand(hopperSubsystem)));
    setJoystickButtonWhenPressed(driverStationJoy, 2, new ToggleShooterCommand(shooterSubsystem));
    setJoystickButtonWhenPressed(driverStationJoy, 7, new ToggleIntakeCommand(intakeSubsystem));
//    setJoystickButtonWhenPressed(driverStationJoy, 8, new ShootCommandGroup(shooterSubsystem, hopperSubsystem, neckSubsystem, drivetrainSubsystem));
    setJoystickButtonWhileHeld(driverStationJoy, 3, new RunCommand(()->climberSubsystem.toggleClimbUp(getRightY()),climberSubsystem));
    setJoystickButtonWhenPressed(driverStationJoy, 4, new RunCommand(() -> climberSubsystem.stopClimb(), climberSubsystem));
//    setJoystickButtonWhileHeld(driverStationJoy, 9, new ParallelCommandGroup(new ));

    setJoystickButtonWhileHeld(driverStationJoy, 5, new ClimbUpCommand(climberSubsystem));
    setJoystickButtonWhileHeld(driverStationJoy, 10, new ClimbDownCommand(climberSubsystem));
    setJoystickButtonWhenPressed(driverStationJoy, 11, new ToggleShiftingCommand(shiftGearsSubsystem));
    setJoystickButtonWhenPressed(driverStationJoy, 12, new TurnToTargetCommand(drivetrainSubsystem));

    setJoystickButtonWhenPressed(driverStationJoy, 9, new SetHoodAngleCommand(shooterSubsystem));


//    setJoystickButtonWhenPressed(driverStationJoy, 9,
//        new ShootCommandGroup(shooterSubsystem, hopperSubsystem, neckSubsystem, drivetrainSubsystem));
//    setJoystickButtonWhenPressed(driverStationJoy, 7,
//        new ShootCommandGroup(shooterSubsystem, hopperSubsystem, neckSubsystem, drivetrainSubsystem));
//    setJoystickButtonWhileHeld(driverStationJoy, 8,
//        new RunCommand(() -> drivetrainSubsystem.drive(0, 0), drivetrainSubsystem));
//    setJoystickButtonWhileHeld(driverStationJoy, 12,
//        new ParallelCommandGroup(new RunCommand(() -> drivetrainSubsystem.drive(0, 0), drivetrainSubsystem),
//            new RunCommand(() -> climberSubsystem.toggleClimbUp(getRightY()), climberSubsystem)));
    // setJoystickButtonWhileHeld(driverStationJoy, );
  }

  public double getLeftY() {
    return driverStationJoy.getRawAxis(1);
    // return leftJoy.getRawAxis(RobotMap.DRIVERSTATION_LEFT_Y_AXIS);
  }

  // Gets the Y direction of the left drive joystick
  public double getLeftX() {
    return driverStationJoy.getX();
  }

  // Gets the Y direction of the right drive joystick
  public double getRightY() {
    return driverStationJoy.getRawAxis(3);
    // return rightJoy.getRawAxis(RobotMap.DRIVERSTATION_RIGHT_Y_AXIS);
  }

  // Gets the X direction of the right drive joystick
  public double getRightX() {
    return driverStationJoy.getX();
  }

  public double getLeftThrottle() {
    return (driverStationJoy.getThrottle() + 1) / 2;
  }

  private void setJoystickButtonWhenPressed(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whenPressed(command);
  }

  private void setJoystickButtonWhileHeld(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whileHeld(command);
  }

  // playing with pressing both buttons to execute a command different from the
  // command bound to pressing a button by itself
  // private void setJoystickButtonsWhileHeld(Joystick joystick, int button1, int
  // button2, Command command){
  // JoystickButton ButtonOne = new JoystickButton(joystick, button1);
  // JoystickButton ButtonTwo = new JoystickButton(joystick, button2);
  //
  // DoubleButton doubleButton = new DoubleButton(ButtonOne, ButtonTwo);
  // doubleButton.whileHeld(command);
  // }
}
