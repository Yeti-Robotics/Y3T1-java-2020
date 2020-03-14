

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 * 
 */
public final class Constants {
    //drive constants
    public static final int LEFT_TALON = 14;
    public static final int LEFT_VICTOR_1 = 15;
    public static final int LEFT_VICTOR_2 = 1;
    public static final int RIGHT_TALON = 7;
    public static final int RIGHT_VICTOR_1 = 5;
    public static final int RIGHT_VICTOR_2 = 6;

    public static final double kTurnP = 1;
    public static final double kTurnI = 0;
    public static final double kTurnD = 0;

    public static final double DISTANCE_PER_PULSE = (6 * Math.PI) / 2048; //inches

    public static final double HIGH_GEAR_RATIO = 9.0/1.0;
    public static final double LOW_GEAR_RATIO = 19.6/1.0;

    //intake and hopper constants
    public static final int INTAKE_VICTOR = 2;
    public static final int HOPPER_LEFT_VICTOR = 5;
    public static final int HOPPER_RIGHT_VICTOR = 0;

    public static final double ROLL_IN_SPEED = 1.0;
    public static final double ROLL_OUT_SPEED = -1.0;

    public static final double FUNNEL_IN_SPEED = 0.75;
    public static final double FUNNEL_OUT_SPEED = -0.75;

    //neck constants
    public static final int NECK_BELT_TALON = 2;
    public static final int NECK_ROLLER_TALON = 10;

    public static final int UPPER_BEAM_BREAK = 0;
    public static final int LOWER_BEAM_BREAK = 1;

    public static final double NECK_UP_SPEED = 0.5;
    public static final double NECK_DOWN_SPEED = -0.5;

    //shooter constants
    public static final int SHOOTER_LEFT_TALON = 6; //left
    public static final int SHOOTER_RIGHT_TALON = 5; //right
    public static final int SHOOTER_SERVO_1 = 0;
    public static final int SHOOTER_SERVO_2 = 1;
    public static final double SERVO_GEAR_RATIO = 32.0 / 96;
    public static final double SERVO_RATIO = 0.72;

    public static final double SHOOT_1_SPEED = .9;
    public static final double SHOOT_2_SPEED = .9;
    public static final double REVERSE_SHOOT_1_SPEED = -0.5;
    public static final double REVERSE_SHOOT_2_SPEED = -0.5;

    //climber constants
    public static final int CLIMBER_TALON_1 = 4; //left
    public static final int CLIMBER_TALON_2 = 8; //right

    public static final double CLIMBER_SPEED = 0.9;

    public static final int CLIMBER_CONT_CURRENT_LIMIT = 15;
    public static final int CLIMBER_PEAK_CURRENT_LIMIT = 25;
    public static final int CLIMBER_PEAK_CURRENT_DURATION = 200;

    public static final int TOP_LIMIT_SWITCH = 1;
    public static final int BOTTOM_LIMIT_SWITCH = 0;

    //wheel of fortune constants
    public static final int WHEEL_OF_FORTUNE_TALON = 5;

    //pnuematics
    public static final int[] INTAKE_PISTONS_SOLENOID = {1,6};
    public static final int[] SHIFTER_SOLENOID = {0,7};

    //driver station constants
    public static final int RIGHT_JOYSTICK = 1;
    public static final int LEFT_JOYSTICK = 0;
    public static final int SECONDARY_JOYSTICK = 2;
    public static final int DRIVER_STATION_JOY = 0;

    //distance calc constants
    public static final double KNOWN_DISTANCE = 161.3; //inches
    public static final int PIXEL_WIDTH_KNOWN = 65; //pixels
    public static final double KNOWN_TAPE_BOUND_WIDTH = 39.25; //inches
    public static final double FOCAL_LENGTH = ( KNOWN_DISTANCE * PIXEL_WIDTH_KNOWN) / KNOWN_TAPE_BOUND_WIDTH;

    //trajectory constants
    public static final int SHOOTER_HEIGHT = 23; // inches
    public static final double GRAVITY = 386.09; // inches/ sec ^2

    //ugly jevois constants
    public static final int JEVOIS_BAUD_RATE = 115200;
}
