/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/





//RILEY WILL FIX THIS I KNOW IT LOOKS WEIRD




package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.CANEncoder;


public class ColorWheelSubsytem extends SubsystemBase {

  public static CANSparkMax colorWheelMotor = new CANSparkMax(Constants.colorWheelDeviceID, MotorType.kBrushless);
  public static CANEncoder colorWheelEncoder = new CANEncoder(colorWheelMotor);

  public static I2C.Port colorSensorPort = I2C.Port.kOnboard;
  public static ColorSensorV3 colorSensor = new ColorSensorV3(colorSensorPort);
  public static ColorMatch colorMatch = new ColorMatch();

  public static Color kBlue = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public static Color kGreen = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static Color kRed = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public static Color kYellow = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public static int countSwitch = 0;
  public static Color previousColor = colorSensor.getColor();
  public static ColorMatchResult lastColorMatchResult = colorMatch.matchClosestColor(previousColor);

public static int counter = 0;
public static String color;

  public static void autoColorWheel(){

    colorMatch.addColorMatch(kBlue);
    colorMatch.addColorMatch(kGreen);
    colorMatch.addColorMatch(kRed);
    colorMatch.addColorMatch(kYellow);

    colorWheelMotor.restoreFactoryDefaults();

    colorWheelEncoder.setPosition(0.0);

    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    if (gameData.length() > 0){
      if (gameData.charAt(0) == 'B'){
        moveColorWheel("STAGE 3", 0);
      }
      else if (gameData.charAt(0) == 'G'){
        moveColorWheel("STAGE 3", 1);
      }
      else if (gameData.charAt(0) == 'R'){
        moveColorWheel("STAGE 3", 2);
      }
      else if (gameData.charAt(0) == 'Y'){
        moveColorWheel("STAGE 3", 3);
      }
    }
    else{
      moveColorWheel("STAGE 1 or 2", -1);
    }


  }

public static void moveColorWheel(String stageMode, int targetColor){

  SmartDashboard.putNumber("count Switch", countSwitch);
  Color match = colorSensor.getColor();
  ColorMatchResult currentColorMatchResult = colorMatch.matchClosestColor(match);
 

  int currentColor = -1;
  int lastColor = -1;

  if (currentColorMatchResult.color == kBlue){
    currentColor = 0;
    color = "Blue";
  }
  else if (currentColorMatchResult.color == kGreen){
    currentColor = 1;
    color = "Green";
  }
  else if (currentColorMatchResult.color == kRed){
    currentColor = 2;
    color = "Red";
  }
  else if (currentColorMatchResult.color == kYellow){
    currentColor = 3;
    color = "Yellow";
  }

if (counter == 0){
  if (currentColorMatchResult.color == kBlue){
    lastColor = 0;
  }
  else if (currentColorMatchResult.color == kGreen){
    lastColor = 1;
  }
  else if (currentColorMatchResult.color == kRed){
    lastColor = 2;
  }
  else if (currentColorMatchResult.color == kYellow){
    lastColor = 3;
  }
  counter++;
}

  SmartDashboard.putString("Color", color);

  if (countSwitch < 32){
    colorWheelMotor.set(Constants.colorWheelMotorSpeed);
    if (currentColor != lastColor){
      countSwitch++;
      lastColor = currentColor;
    }
  }
  else if (countSwitch >= 32){
    colorWheelMotor.set(0);
  }


  /*
  if (countSwitch < 32){
    colorWheelMotor.set(Constants.colorWheelMotorSpeed);
    if (previousColor != colorSensor.getColor()){
      countSwitch++;
      previousColor = colorSensor.getColor();
    }
  }
  else if (countSwitch >= 32){
    colorWheelMotor.set(0);
    finished = true;
  }
SmartDashboard.putNumber("count Switch", countSwitch);
*/

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
