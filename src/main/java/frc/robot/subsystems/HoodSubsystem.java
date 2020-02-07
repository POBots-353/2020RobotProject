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
  double kP = 5e-5; 
  double kI = 1e-6;
  double kD = 0; 
  double kIz = 0;       //My Dearest Salil, please use a more descriptive name ~CR
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

  @Override
  public void periodic() {
    double p = 0.294;
    double i = 0.0;
    double d = 0.0;
    double iz = 0.0;
    double ff = 1.56E-4;
    double max = 1.0;
    double min = -1.0;
    double maxV = 60.0;
    double minV = 0.0;
    double maxA = 150.0;
    double allE = 0.0;
 
    // if PID coefficients on SmartDashboard have changed, write new values to controller
 
    if((p != kP)) { hoodMotorController.setP(p); kP = p; }
    if((i != kI)) { hoodMotorController.setI(i); kI = i; }
    if((d != kD)) { hoodMotorController.setD(d); kD = d; }
    if((iz != kIz)) { hoodMotorController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { hoodMotorController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
 
      hoodMotorController.setOutputRange(min, max); 
 
      kMinOutput = min; kMaxOutput = max; 
 
    }
 
    if((maxV != maxVel)) { hoodMotorController.setSmartMotionMaxVelocity(maxV,0); maxVel = maxV; }
    if((minV != minVel)) { hoodMotorController.setSmartMotionMinOutputVelocity(minV,0); minVel = minV; }
    if((maxA != maxAcc)) { hoodMotorController.setSmartMotionMaxAccel(maxA,0); maxAcc = maxA; }
    if((allE != allowedErr)) { hoodMotorController.setSmartMotionAllowedClosedLoopError(allE,0); allowedErr = allE; }
 
 
 
    double setPoint, processVariable;
    
    processVariable = hoodMotorEncoder.getPosition();
    setPoint = SmartDashboard.getNumber("Set Position", 0);
    hoodMotorController.setReference(setPoint, ControlType.kPosition);
    //processVariable = hoodMotorEncoder.getPosition();

    
    SmartDashboard.putNumber("Process Variable", processVariable);
    

  }
}
