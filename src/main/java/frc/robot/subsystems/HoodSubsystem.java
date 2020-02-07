/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HoodSubsystem extends SubsystemBase {
  /**
   * Creates a new HoodSubsystem.
   */
  public CANSparkMax hoodMotor = new CANSparkMax(Constants.hoodMotorDeviceID, MotorType.kBrushless);
  public CANPIDController hoodMotorController = hoodMotor.getPIDController();
  public CANEncoder hoodMotorEncoder = hoodMotor.getEncoder();

  public int hoodToggleState = 1;


  double kP = 0.294; 
  double kI = 0;
  double kD = 0; 
  double kIz = 0;
  double kFF = 0.000156; 
  double kMaxOutput = 1; 
  double kMinOutput = -1;
  double maxRPM = 570;
  double allowedErr = 0;
  double minVel = 0;
  // Smart Motion Coefficients
  double maxVel = 200; // rpm
  double maxAcc = 150;


  public HoodSubsystem() {
    hoodMotor.restoreFactoryDefaults();
    hoodMotorEncoder.setPosition(0);
    // set PID coefficients
    hoodMotorController.setP(kP);
    hoodMotorController.setI(kI);
    hoodMotorController.setD(kD);
    hoodMotorController.setIZone(kIz);
    hoodMotorController.setFF(kFF);
    hoodMotorController.setOutputRange(kMinOutput, kMaxOutput);

    int smartMotionSlot = 0;
    hoodMotorController.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
    hoodMotorController.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
    hoodMotorController.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
    hoodMotorController.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);
    SmartDashboard.putNumber("Set Position", 0);
    //SmartDashboard.putNumber("Set Velocity", 0);
  }
  public void runAutoPos(){
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
 
    double setPoint, encoderPosition;
    
    encoderPosition = hoodMotorEncoder.getPosition();    
    setPoint = Constants.hoodLSRLA * Math.pow(ty, 2) + Constants.hoodLSRLB * ty + Constants.hoodLSRLC; //regression to convert limelight to encoder value
    // anticipate arctan here 
    hoodMotorController.setReference(setPoint, ControlType.kPosition);

    
    SmartDashboard.putNumber("Process Variable", encoderPosition);
    

  }
  public void runSetPos(double newPostion){


 
    // if PID coefficients on SmartDashboard hav
 
 
    double setPoint, encoderPosition;
    
    encoderPosition = hoodMotorEncoder.getPosition();
    
    setPoint = newPostion;

    hoodMotorController.setReference(setPoint, ControlType.kPosition);

    
    SmartDashboard.putNumber("Process Variable", encoderPosition);
    

  }


  
@Override
  public void periodic() {
    
}

}
