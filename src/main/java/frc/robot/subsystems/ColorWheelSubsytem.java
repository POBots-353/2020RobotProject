/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
//import com.revrobotics.CANSparkMax.IdleMode;
//import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import com.revrobotics.CANEncoder;
//import com.revrobotics.CANError;
//import com.revrobotics.CANPIDController;
//import com.revrobotics.ControlType;


public class ColorWheelSubsytem extends SubsystemBase {


  public CANSparkMax colorWheelMotor = new CANSparkMax(Constants.colorWheelDeviceID, MotorType.kBrushless); //constants

  public CANEncoder colorWheelEncoder = new CANEncoder(colorWheelMotor);

  public static I2C.Port conorWheelSensorPort = I2C.Port.kOnboard;

  public static ColorSensorV3 colorWheelSensor = new ColorSensorV3(conorWheelSensorPort);
  
  public static ColorMatch colorMatch = new ColorMatch();

  public static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);


  public void manualColorWheel(){
    colorWheelMotor.set(Constants.colorWheelMotorSpeed);
  }

  /**
   * Creates a new ColorWheelSubsytem.
   */
  public ColorWheelSubsytem() {

  }



  @Override
  public void periodic() {

  }



}
