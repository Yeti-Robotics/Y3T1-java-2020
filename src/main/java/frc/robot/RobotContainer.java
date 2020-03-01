/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autoRoutines.AdjustHoodAndShootAutoCommandGroup;
import frc.robot.autoRoutines.ShootAutoCommandGroup;
import frc.robot.commands.climbing.ClimbDownCommand;
import frc.robot.commands.climbing.ClimbUpCommand;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.funnel.FunnelOutCommand;
import frc.robot.commands.intake.RollOutCommand;
import frc.robot.commands.neck.MoveDownNeckCommand;
import frc.robot.commands.shifting.ToggleShiftingCommand;
import frc.robot.commands.funnel.FunnelInCommand;
import frc.robot.commands.neck.MoveUpNeckCommand;
import frc.robot.commands.shooting.SetHoodAngleCommand;
import frc.robot.commands.shooting.SpinFlywheelsCommand;
import frc.robot.commands.intake.ExtendIntakeCommand;
import frc.robot.commands.intake.RetractIntakeCommand;
import frc.robot.commands.intake.RollInCommand;
import frc.robot.commands.shooting.StopSpinningCommand;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
//  public final Joystick leftJoy;
//  public final Joystick rightJoy;
//  public final Joystick secondaryJoy;
//  private WheelOfFortuneSubsystem wheelOfFortuneSubsystem;
  public final Joystick driverStationJoy;
  private DrivetrainSubsystem drivetrainSubsystem;
  private IntakeSubsystem intakeSubsystem;
  private NeckSubsystem neckSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private FunnelSubsystem funnelSubsystem;
  private ClimberSubsystem climberSubsystem;
  private ShiftGearsSubsystem shiftGearsSubsystem;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
//    leftJoy = new Joystick(Constants.LEFT_JOYSTICK);
//    rightJoy = new Joystick(Constants.RIGHT_JOYSTICK);
//    secondaryJoy = new Joystick(Constants.SECONDARY_JOYSTICK);
    driverStationJoy = new Joystick(Constants.DRIVER_STATION_JOY);

    drivetrainSubsystem = new DrivetrainSubsystem();
//    wheelOfFortuneSubsystem = new WheelOfFortuneSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    neckSubsystem = new NeckSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    funnelSubsystem = new FunnelSubsystem();
    shiftGearsSubsystem = new ShiftGearsSubsystem();
    climberSubsystem = new ClimberSubsystem();

     drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.drive(-0, -0), drivetrainSubsystem));
     shooterSubsystem.setDefaultCommand(new RunCommand(() -> shooterSubsystem.setHoodAngle(getLeftThrottle()*180), shooterSubsystem));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    setJoystickButtonWhileHeld(driverStationJoy, 1, new MoveUpNeckCommand(neckSubsystem));

    setJoystickButtonWhileHeld(driverStationJoy, 2, new FunnelInCommand(funnelSubsystem));

    setJoystickButtonWhileHeld(driverStationJoy, 3, new FunnelOutCommand(funnelSubsystem));

    setJoystickButtonWhileHeld(driverStationJoy, 4, new ParallelCommandGroup(new RollInCommand(intakeSubsystem), new MoveUpNeckCommand(neckSubsystem), new FunnelOutCommand(funnelSubsystem)));
    //Stops the flywheel
    setJoystickButtonWhenPressed(driverStationJoy, 5, new ParallelCommandGroup(
            new StopSpinningCommand(shooterSubsystem)
    ));
    //Turns to target, calcs hood angle, runs hopper, shoots
    setJoystickButtonWhileHeld(driverStationJoy, 6, new ShootAutoCommandGroup(shooterSubsystem, funnelSubsystem, neckSubsystem, drivetrainSubsystem));
    //Rolls in intake
    setJoystickButtonWhileHeld(driverStationJoy, 7, new RollInCommand(intakeSubsystem));
    //Runs #4 Backwards
    setJoystickButtonWhileHeld(driverStationJoy, 8, new ParallelCommandGroup(new RollOutCommand(intakeSubsystem), new MoveDownNeckCommand(neckSubsystem), new FunnelInCommand(funnelSubsystem)));
    //Calcs angle, runs hopper, shoots, DOESNT TURN)
//    setJoystickButtonWhenPressed(driverStationJoy, 8, new AdjustHoodAndShootAutoCommandGroup(shooterSubsystem, funnelSubsystem, neckSubsystem));
    //Retracts Intake (WE NEED TO LOOK AT THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!)
    setJoystickButtonWhenPressed(driverStationJoy, 9, new ExtendIntakeCommand(intakeSubsystem));
    //Extends Intake (WE NEED TO LOOK AT THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!)
    setJoystickButtonWhenPressed(driverStationJoy, 10, new RetractIntakeCommand(intakeSubsystem));
    //Shifts the gearboxes
    setJoystickButtonWhenPressed(driverStationJoy, 11, new ToggleShiftingCommand(shiftGearsSubsystem));
    //Runs the climber with joystick
    setJoystickButtonWhileHeld(driverStationJoy, 12, new RunCommand(() -> climberSubsystem.toggleClimbUp(getRightY() / 3)));


    //    setJoystickButtonWhileHeld(driverStationJoy, 1, new RollInCommand(intakeSubsystem));
    //    setJoystickButtonWhileHeld(driverStationJoy, 2, new SpinFlywheelsCommand(shooterSubsystem));
    //
    //    setJoystickButtonWhenPressed(driverStationJoy, 3, new DriveForDistanceCommand(drivetrainSubsystem, 10, .5, .5));
    //
    //
    ////    setJoystickButtonWhenPressed(driverStationJoy, 3, new ShootCommand(shooterSubsystem));
    //
    //    //shooting command

    // 
//    setJoystickButtonWhenPressed(driverStationJoy, 4, new SequentialCommandGroup(
//                      new SetHoodAngleCommand(shooterSubsystem),
//                      new ParallelCommandGroup(
//                              new FunnelInCommand(funnelSubsystem),
//                              new MoveUpNeckCommand(neckSubsystem),
//                              new SpinFlywheelsCommand(shooterSubsystem)
//                      )
//                ).withInterrupt(() -> !neckSubsystem.getUpperBeamBreak()));

//    //Shoots without turning to target ?????????
//    setJoystickButtonWhileHeld(driverStationJoy, 6, new ParallelCommandGroup(
////            new SpinFlywheelsCommand(shooterSubsystem),
////            new MoveUpNeckCommand(neckSubsystem)
//    ));

    //Stops Flywheel


    //

    // setJoystickButtonWhenPressed(driverStationJoy, 7, new ClimbUpCommand(climberSubsystem));
    // setJoystickButtonWhenPressed(driverStationJoy, 9, new ClimbDownCommand(climberSubsystem));
//// //align and shoot
//     setJoystickButton(leftJoy, 4, new SequentialCommandGroup(
//             new TurnToTargetCommand(drivetrainSubsystem),
//             new SetHoodAngleCommand(shooterSubsystem, shooterSubsystem.calcHoodAngle()),
//             new ParallelCommandGroup(
//                     new MoveUpNeckCommand(neckSubsystem),
//                     new ShootCommand(shooterSubsystem)
//             )
//       ));
//     setJoystickButton(leftJoy, 5, new TurnToTargetCommand(drivetrainSubsystem));

    //toggle climb power. (when button pressed, you can use joystick to toggle power.)



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

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public double getLeftThrottle() {
    return (driverStationJoy.getThrottle()+1)/2;
  }


  private void setJoystickButtonWhenPressed(Joystick joystick, int button, CommandBase command){
    new JoystickButton(joystick, button).whenPressed(command);
  }

  private void setJoystickButtonWhileHeld(Joystick joystick, int button, CommandBase command){
    new JoystickButton(joystick, button).whileHeld(command);
  }
}
