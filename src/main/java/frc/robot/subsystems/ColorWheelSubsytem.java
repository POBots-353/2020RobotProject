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



  /**
   * Creates a new ColorWheelSubsytem.
   */
  public ColorWheelSubsytem() {

  }



  @Override
  public void periodic() {

  }



  public void manualColorWheel(){
    colorWheelMotor.set(Constants.colorWheelMotorSpeed);
  }

  public void autoColorWheel(){
    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    if (gameData.length() > 0){
      if (gameData.charAt(0) == 'B'){ //Blue represented by 0
        moveAutoColorWheel(3, 0);
      }
      else if (gameData.charAt(0) == 'G'){ //Green represented by 1
        moveAutoColorWheel(3, 1);
      }
      else if (gameData.charAt(0) == 'R'){ //Red represented by 2
        moveAutoColorWheel(3, 2);
      }
      else if (gameData.charAt(0) == 'Y'){ //Yellow represented by 3
        moveAutoColorWheel(3, 3);
      }
    }
    else if(gameData.length() == 0){
      moveAutoColorWheel(12, 4);
    }
  }

public void moveAutoColorWheel(int stageMode, int targetColor){
  Color currentColor = colorWheelSensor.getColor();
  if (stageMode == 12){

  }
  else if (stageMode == 3){

  }
}

}
