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
 */
public final class Constants {

	//Computer outlets are called Port
	//CANSparkMax motors are called DeviceID
	//Sensors placements are called Number
	//Buttons on the joysticks are called Number

	//Joystick and controller port constants
	public static int driverStickPort = 0;
	public static int operatorStickPort = 3;
	public static int colorWheelControllerPort = -1;

	//CANSparkMax Device ID constants
	public static int leftFrontMotorDeviceID = 4; 
	public static int leftRearMotorDeviceID = 2;
	public static int rightFrontMotorDeviceID = 1;
	public static int rightRearMotorDeviceID = 3;
	public static int intakeMotorDeviceID = -1;
	public static int conveyorMotorDeviceID = -1;
	public static int shooterMotorDeviceID = -1;
	public static int climberMotorDeviceID = -1;
	public static int colorWheelDeviceID = 5;
	public static int hoodMotorDeviceID = -1;

	//CANSParkMax Motor Speeds
	public static double intakeMotorSpeed = 0.5;
	public static double conveyorMotorSpeed = 0.15;
	public static double shooterMotorSpeed = 0.9;
	public static double climberMotorSpeed = 0.3;
	public static double colorWheelMotorSpeed = 0.1;

	//Spike Relay Number constants
	public static int intakeSpikeNumber = -1;

	//Sensor number constants
	public static int intakeSensorNumber = -1;
	public static int conveyorSensorNumber = -1;
	public static int shooterSensorNumber = -1;
	public static int colorSensorNumber = -1;
	 
	//Driver button constants
	public static int turboButtonNumber = 2;
	public static int slowButtonNumber = 3;
	public static int AutoAlignButtonNumber = 1;
	public static int magicTurnButtonNumber = 4;

	//Operator button constants
	public static int shootButtonNumber = 1;
	public static int intakeButtonNumber = 3;
	public static int outtakeButtonNumber = 4;
	public static int conveyorUpButtonNumber = 5;
	public static int conveyorDownButtonNumber = 6;
	public static int climberUpButtonNumber = 7;
	public static int climberDownButtonNumber = 8;
	public static int dropIntakeButtonNumber = 9;
	public static int hoodUpButtonNumber = 10;
	public static int hoodDownButtonNumber = -1;
	public static int AutoColorButtonNumber = 11;
	public static int manualColorButtonNumber = -1;
;

	//Constants for angular and linear alignment within AlignRobotCommand()
	public static double kPAim = 0.075;
	public static double kPDistance = 0.1;
	public static double maxMove = 0.5;
	public static double maxTurn = 0.25;
	public static double minHeadingError = 0.50;
	public static double minDistanceError = 0.50;

	// Constants to angle the hood
	public static double kPHoodAim = 0.294;
	
	public static double hoodLSRLA = 1; //represents coefficient of x ^ 2
	public static double hoodLSRLB = 1; //represents coefficient of x
	public static double hoodLSRLC = 1; //represents constant
	public static double hoodAngle1 = 0.000;
	public static double hoodAngle2 = 21.875;
	public static double hoodAngle3 = 29.167;

	//Constants for driving modes within ManualDriveCommand()
	public static double turboScale = 1.00; //faster speed for the robot's drivetrain
	public static double slowScale = 0.5; //Slower speed for the robot's drivetrain
	public static double driverScale = 0.88; //Original speed for the robot's drivetrain

	//Constants for Solenoids within DropIntakeSubsystem()
	public static double solenoidHoldTime = 2.0;
	
	//Constants for limit switch
	public static int shooterLimitSwitch=0;


}
