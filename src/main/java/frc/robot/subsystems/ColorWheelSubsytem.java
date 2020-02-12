/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/





//RILEY WILL FIX THIS I KNOW IT LOOKS WEIRD




package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ColorWheelSubsytem extends SubsystemBase {



  public static I2C.Port colorSensorPort = I2C.Port.kOnboard;
  public static ColorSensorV3 colorSensor = new ColorSensorV3(colorSensorPort);
  public static ColorMatch colorMatch = new ColorMatch();

  public static Color kBlue = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public static Color kGreen = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static Color kRed = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public static Color kYellow = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public static CANSparkMax colorWheelMotor = new CANSparkMax(Constants.colorWheelDeviceID, MotorType.kBrushless);
  public static CANEncoder colorWheelEncoder = new CANEncoder(colorWheelMotor);



  public static void autoColorWheel(){

    colorMatch.addColorMatch(kBlue);
    colorMatch.addColorMatch(kGreen);
    colorMatch.addColorMatch(kRed);
    colorMatch.addColorMatch(kYellow);

  }



  /**
   * Creates a new ColorWheelSubsytem.
   */
  public ColorWheelSubsytem() {

  }



  @Override
  public void periodic() {

    Color detectedColor = colorSensor.getColor();

    int change = -1;
    String colorString;
    ColorMatchResult match = colorMatch.matchClosestColor(detectedColor);
    if (match.color == kBlue) {
      colorString = "Blue";
      change++;
    } else if (match.color == kRed) {
      colorString = "Red";
      change++;
    } else if (match.color == kGreen) {
      colorString = "Green";
      change++;
    } else if (match.color == kYellow) {
      colorString = "Yellow";
      change++;
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);

    moveColorWheelMotor(change);

  }



  public void moveColorWheelMotor(int changeColor){

    if (changeColor < 32 && changeColor > 0){
      colorWheelMotor.set(Constants.colorWheelMotorSpeed);
    }
    else{
      colorWheelMotor.set(0);
    }

  }



}
