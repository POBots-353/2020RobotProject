/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import frc.robot.Constants;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ExampleSubsystem extends SubsystemBase {
  

  //***** BEGIN SPARKMAX DRIVE CODE *****

  //* Creates a CANSparkMax varible leftFrontMotor, leftRearMotor, rightFrontMotor, and rightRearMotor that are all MotorType kBrushless *
  //*** These are all constructors of the CANSparkMax class and take two parameters CANSparkMax(int, int) ***
  public CANSparkMax leftFrontMotor = new CANSparkMax(Constants.leftFrontMotorPort,MotorType.kBrushless);
  public CANSparkMax leftRearMotor = new CANSparkMax(Constants.leftRearMotorPort, MotorType.kBrushless);
  public CANSparkMax rightFrontMotor = new CANSparkMax(Constants.rightFrontMotorPort, MotorType.kBrushless);
  public CANSparkMax rightRearMotor = new CANSparkMax(Constants.rightRearMotorPort, MotorType.kBrushless);


  //* Creates a SpeedControllerGroup with the (leftFrontMotor and leftRearMotor) and another SpeedControllerGroup with the (rightFrontMotor and rightRearMotor) *
  //*** This allows us to command both the right side motors or the left side motors at the same time ***
  //*** This is comparable to Talon motors controllers where there is a master motor controller and a slave motor controller that follows the master motor controller ***
  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);
  
  //*Creates a DifferentialDrive variable drive that pairs the leftMotorGroup and rightMotorGroup to take two parameters to drive (move and turn)
  public DifferentialDrive drive = new DifferentialDrive(leftMotorGroup,rightMotorGroup);

  //*Creates a manualDrive method that is called in 
  public void manualDrive(double move, double turn, double scale){
    drive.arcadeDrive(-move * Math.abs(move) * scale, turn * Math.abs(turn) * scale); //Riley came up with the absolute value idea--> very smart
  }
  
  //***** END SPARKMAX DRIVE CODE *****


  public ExampleSubsystem() {
    //leftRearMotor.follow(leftFrontMotor);
    //rightRearMotor.follow(rightFrontMotor);
    //leftRearMotor.(leftFrontMotor);
    //drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
    drive.setSafetyEnabled(true);
    
  }



  //***** BEGIN TALON DRIVE CODE *****

  //* Creates a WPI_TalonSRX variable leftMaster, leftSlave, rightMaster, and rightSlave *
  //*** These are all constructors of the WPI_TalonSRX class and take one parameter WPI_TalonSRX(int)
  //public WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.leftMasterPort);
  //public WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.leftSlavePort);
  //public WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.rightMasterPort);
  //public WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.rightSlavePort);
  
  //public DifferentialDrive drive = new DifferentialDrive(leftMaster,rightMaster);
  
  //public DriveSubsystem(){
    //leftSlave.follow(leftMaster);
    //rightSlave.follow(rightMaster);
  //}

  //public void manualDrive(double move, double turn, double scale){
    //drive.arcadeDrive(-move * Math.abs(move) * scale, turn * Math.abs(turn) * scale); //Riley came up with the absolute value idea--> very smart
  //}

  //***** END TALON DRIVE CODE *****/




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
