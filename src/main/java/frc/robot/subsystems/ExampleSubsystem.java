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
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import frc.robot.Constants;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ExampleSubsystem extends SubsystemBase {
  

  /**
   * Creates a new ExampleSubsystem.
   */
  public PWMSparkMax leftFrontMotor = new PWMSparkMax(Constants.leftFrontMotorPort);
  public PWMSparkMax leftRearMotor = new PWMSparkMax(Constants.leftRearMotorPort);
  public PWMSparkMax rightFrontMotor = new PWMSparkMax(Constants.rightFrontMotorPort);
  public PWMSparkMax rightRearMotor = new PWMSparkMax(Constants.rightRearMotorPort);
  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);
  
  public DifferentialDrive drive;

  public ExampleSubsystem() {
    //leftRearMotor.follow(leftFrontMotor);
    //rightRearMotor.follow(rightFrontMotor);
    drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
  
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

  //***** END TALON DRIVE CODE *****/





  //***** BEGIN SPARKMAX DRIVE CODE *****

  //* Creates a CANSparkMax varible leftFrontMotor, leftRearMotor, rightFrontMotor, and rightRearMotor that are all MotorType kBrushless *
  //*** These are all constructors of the CANSparkMax class and take two parameters CANSparkMax(int, int) ***
  
  //* Creates a SpeedControllerGroup with with the (leftFrontMotor and leftRearMotor) and another SpeedControllerGroup with the (rightFrontMotor and rightRearMotor) *
  //*** This allows us to command both the right side motors or the left side motors at the same time ***
  //*** This is comparable to Talon motors controllers where there is a master motor controller and a slave motor controller that  follows the master motor controller ***
  
  
  //*Creates a DifferentialDrive variable drive that 
  
  //***** END SPARKMAX DRIVE CODE *****





  public void manualDrive(double move, double turn){
    drive.arcadeDrive(move, turn);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
