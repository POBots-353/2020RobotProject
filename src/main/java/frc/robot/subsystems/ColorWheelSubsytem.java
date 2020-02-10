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
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.CANError;


public class ColorWheelSubsytem extends SubsystemBase {



  public CANSparkMax colorWheelMotor = new CANSparkMax(Constants.colorWheelDeviceID, MotorType.kBrushless); //constants

  public CANEncoder colorWheelEncoder = new CANEncoder(colorWheelMotor);
  
  public CANError brake = colorWheelMotor.setIdleMode(IdleMode.kBrake);

  public static I2C.Port colorWheelSensorPort = I2C.Port.kOnboard;

  public static ColorSensorV3 colorWheelSensor = new ColorSensorV3(colorWheelSensorPort);
  
  public static ColorMatch colorMatch = new ColorMatch();

  public static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private CANPIDController pidController;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr; //put in constants later
  public CANPIDController motorController = colorWheelMotor.getPIDController();


  /**
   * Creates a new ColorWheelSubsytem.
   */
  public ColorWheelSubsytem() {

  }



  @Override
  public void periodic() {
    autoColorWheel();
  }



  public void manualColorWheel(){
    colorWheelMotor.set(Constants.colorWheelMotorSpeed);
  }

  public void autoColorWheel(){
    
    colorMatch.addColorMatch(kBlueTarget);
    colorMatch.addColorMatch(kGreenTarget);
    colorMatch.addColorMatch(kRedTarget);
    colorMatch.addColorMatch(kYellowTarget);

    colorWheelEncoder.setPosition(0.0);

    colorWheelMotor.restoreFactoryDefaults();

    kP = 5e-5; //change to constatnts later
    kI = 1e-6;
    kD = 0; 
    kIz = 0; 
    kFF = 0.000156; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    maxRPM = 5700;

    maxVel = 200; // rpm
    maxAcc = 150;

    pidController.setP(kP);
    pidController.setI(kI);
    pidController.setD(kD);
    pidController.setIZone(kIz);
    pidController.setFF(kFF);
    pidController.setOutputRange(kMinOutput, kMaxOutput);
    colorWheelEncoder.setPosition(0.0);

    pidController = colorWheelMotor.getPIDController();
    colorWheelEncoder = colorWheelMotor.getEncoder();

    int smartMotionSlot = 0;
    pidController.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
    pidController.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
    pidController.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
    pidController.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);

    double amountOfRevolutions = 0;
    int stopCounter = 0;
    int colorChange = 0;
    boolean revolutionButton = false;
    boolean positionButton = false;
    boolean buttonRed = false;
    boolean buttonYellow = false;
    boolean buttonGreen = false;
    boolean buttonBlue = false;
    int PIDCounter = 0;
    boolean buttonPress = false;
    boolean startsPositionControl = false;
    boolean mode = true;

    

    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    if (gameData.length() > 0){
      if (gameData.charAt(0) == 'B'){ //Blue represented by 0
        startsPositionControl = true;
        if (revolutionButton == true){
          buttonBlue = true;
        }
      }
      else if (gameData.charAt(0) == 'G'){ //Green represented by 1
        startsPositionControl = true;
        if (revolutionButton == true){
          buttonGreen = true;
        }
      }
      else if (gameData.charAt(0) == 'R'){ //Red represented by 2
        startsPositionControl = true;
        if (revolutionButton == true){
          buttonRed = true;
        }
      }
      else if (gameData.charAt(0) == 'Y'){ //Yellow represented by 3
        startsPositionControl = true;
        if (revolutionButton == true){
          buttonYellow = true;
        }
      }
    }
    else if(gameData.length() == 0){
    }

    Color detectedColor = colorWheelSensor.getColor();
    int currentColor = 0;
    ColorMatchResult match = colorMatch.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) { //Sets the variable to the current color the motor is on. Will change as wheel spins
      currentColor = 1;
    } else if (match.color == kRedTarget) {
      currentColor = 2;
    } else if (match.color == kGreenTarget) {
      currentColor = 3;
    } else if (match.color == kYellowTarget) {
      currentColor = 4;
    }

    if (RobotContainer.operatorStick.getRawButtonPressed(10) == true){
      revolutionButton = true;
      mode = true; //starts the revolution control
    }if(positionButton == false){
      buttonRed = false;
      buttonGreen = false;
      buttonBlue = false;
      buttonYellow = false;
    }

    //Manual control over the color wheel
    if (RobotContainer.operatorStick.getRawButtonPressed(11) == true){
      buttonPress = true;
    }else if (RobotContainer.operatorStick.getRawButtonReleased(11) == true){
      buttonPress = false;
      colorWheelMotor.set(0);
      startsPositionControl = false;
    }
    if(buttonPress == true){
      colorWheelMotor.set(0.1);
      positionButton = false;
      revolutionButton = false;
    }

    double p = 0.000050;
    double i = 0.000001;
    double d = 0;
    double iz = 0;
    double ff = 0.000156;
    double max = 1.000000;
    double min = -1.000000;
    double maxV = 200.000000;
    double minV = 0;
    double maxA = 150.000000;
    double allE = 0;

if((p != kP)) { 
  pidController.setP(p); 
  kP = p; }
if((i != kI)) { 
  pidController.setI(i); 
  kI = i; }
if((d != kD)) { 
  pidController.setD(d); 
  kD = d; }
if((iz != kIz)) { 
  pidController.setIZone(iz); 
  kIz = iz; }
if((ff != kFF)) { 
  pidController.setFF(ff); 
  kFF = ff; }
if((max != kMaxOutput) || (min != kMinOutput)) { 
  pidController.setOutputRange(min, max); 
  kMinOutput = min; 
  kMaxOutput = max; 
  }
if((maxV != maxVel)) { 
  pidController.setSmartMotionMaxVelocity(maxV,0); 
  maxVel = maxV; }
if((minV != minVel)) { 
  pidController.setSmartMotionMinOutputVelocity(minV,0); 
  minVel = minV; }
if((maxA != maxAcc)) { 
  pidController.setSmartMotionMaxAccel(maxA,0); 
  maxAcc = maxA; }
if((allE != allowedErr)) { 
  pidController.setSmartMotionAllowedClosedLoopError(allE,0); 
  allowedErr = allE; }
  double setPoint;
  //starts mode
if(mode == true) {
  //Start of amount of revolutions
  if (revolutionButton == true) { //Starts the mode for revolution control
    if (stopCounter == 0){
      //Set the color it starts on, is not subject to change
      if (match.color == kBlueTarget){
        colorChange = 1;
      } else if (match.color == kRedTarget){
        colorChange = 2;
      }else if (match.color == kGreenTarget){
        colorChange =3;
      } else if (match.color == kYellowTarget){
        colorChange = 4;
      }
      stopCounter = 1;
    }
    colorWheelMotor.set(.075);
      if (currentColor != colorChange){ //Starts counting the amount of times the color changes
        amountOfRevolutions += 1; 
        colorChange = currentColor;
    }
    if (amountOfRevolutions >= 34){ //This stops the motor from turning the color wheel
    colorWheelMotor.set(0);
    if (startsPositionControl == true){
      positionButton = true;
      PIDCounter = 0;
      mode = false;
    }
    revolutionButton = false;
    if (revolutionButton == false){
      amountOfRevolutions = 0;
    }
    }
  }
  // end of amount of revoultions

  } else {

    //position control

    //Blue
  if (positionButton == true){
    if (buttonBlue == true){//blue color
      if (currentColor == 1){
        if (PIDCounter == 0){
          colorWheelMotor.set(0);
          colorWheelEncoder.setPosition(0);
          PIDCounter = 1;
        }
        setPoint = -0.5;
        pidController.setReference(setPoint, ControlType.kSmartMotion);
        
      }else if (currentColor != 1){
        PIDCounter = 0;
        colorWheelMotor.set(0.065);
      }
    }

     //Red 
     else if (buttonRed == true){//red color
      if (currentColor == 2){
        if (PIDCounter == 0){
          colorWheelMotor.set(0);
          colorWheelEncoder.setPosition(0);
          PIDCounter = 1;
        }
        setPoint = -0.5;
        pidController.setReference(setPoint, ControlType.kSmartMotion);
        
      }else if (currentColor != 2){
        PIDCounter = 0;
        colorWheelMotor.set(0.065);
      }
    }
    
    //Green
     else if (buttonGreen == true){//green color
      if (currentColor == 3){
        if (PIDCounter == 0){
          colorWheelMotor.set(0);
          colorWheelEncoder.setPosition(0);
          PIDCounter = 1;
        }
        setPoint = -0.5;
        pidController.setReference(setPoint, ControlType.kSmartMotion);
        
      }else if (currentColor != 3){
        PIDCounter = 0;
        colorWheelMotor.set(0.065);
      }
    }

    //Yellow
    else if (buttonYellow == true){//Yellow color
      if (currentColor == 4){
        if (PIDCounter == 0){
          colorWheelMotor.set(0);
          colorWheelEncoder.setPosition(0);
          PIDCounter = 1;
        }
        setPoint = -0.5;
        pidController.setReference(setPoint, ControlType.kSmartMotion);
        
      }else if (currentColor != 4){
        PIDCounter = 0;
        colorWheelMotor.set(0.065);
      }
    }
  } 
}


  }

}
