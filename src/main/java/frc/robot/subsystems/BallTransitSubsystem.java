/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.RobotContainer;

public class BallTransitSubsystem extends SubsystemBase {
  /**
   * Creates a new BallTransitSystem.
   */
  public CANSparkMax intakeMotor = new CANSparkMax(Constants.intakeMotorDeviceID,MotorType.kBrushless);
  public CANSparkMax conveyorMotor = new CANSparkMax(Constants.conveyorMotorDeviceID,MotorType.kBrushless); //we may in the future have a second conveyorMotor so you'd group them together
  public CANSparkMax shooterMotor = new CANSparkMax(Constants.shooterMotorDeviceID,MotorType.kBrushless);

  
  public DigitalInput shooterSensor = new DigitalInput(Constants.shooterLimitSwitch);
  public DigitalInput intakeSensor = new DigitalInput(Constants.intakeSensorNumber);
  public DigitalInput conveyorSensor = new DigitalInput(Constants.conveyorSensorNumber);

  public boolean intakeIn;
  public boolean intakeOut;
  public boolean shooterRunning;


  public BallTransitSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
     boolean intake = RobotContainer.driverStick.getRawButtonPressed(Constants.intakeButtonNumber);
     boolean outtake = RobotContainer.driverStick.getRawButtonPressed(Constants.outtakeButtonNumber);
     boolean conveyorUp = RobotContainer.operatorStick.getRawButton(Constants.conveyorUpButtonNumber);
     boolean conveyorDown = RobotContainer.operatorStick.getRawButton(Constants.conveyorUpButtonNumber);
     boolean shoot = RobotContainer.operatorStick.getRawButtonPressed(Constants.shootButtonNumber);

    if(intake||outtake){
      intake(intake,outtake);
    }

    if(shoot){
      shooter(shoot);
    }

    if(conveyorUp){ // these top 2 are simple conditional for if button for conveyor is pressed
      conveyorMotor.set(Constants.conveyorMotorSpeed);
    }
    else if(conveyorDown){
      conveyorMotor.set(Constants.conveyorMotorSpeed*-1);
    }
    else if(intakeIn){ // these next three respond to global querries to run conveyor, could be ors but style
      conveyorMotor.set(Constants.conveyorMotorSpeed);
    }
    else if(intakeOut){
      conveyorMotor.set(Constants.conveyorMotorSpeed*-1);
    }
    else if(shooterRunning){
      conveyorMotor.set(Constants.conveyorMotorSpeed);
    }
    else{
        conveyorMotor.set(0);
    }

  }

  public void intake( boolean intake,  boolean outtake){
    if (intake == true){
      intakeMotor.set(Constants.intakeMotorSpeed);
      if(intakeSensor.get() == true){
        //conveyorMotor.set(Constants.conveyorMotorSpeed);
        changeIntakeIn(true); //This is no garbage, it no work PZL FIX ~CR
        changeIntakeOut(false);
      }
    } else if (outtake == true ){
      intakeMotor.set(-Constants.conveyorMotorSpeed);
      changeIntakeIn(false);
      changeIntakeOut(true);
    } else{
      //conveyorMotor.set(0);
      intakeMotor.set(0);
      changeIntakeIn(false);
      changeIntakeOut(false);
    }
}

  public void shooter (boolean shoot){
    if (shoot == true){
      shooterMotor.set(Constants.shooterMotorSpeed);
      if (shooterSensor.get() == true){
        changeShooterRunning(false);
      }
      else{
        changeShooterRunning(true);
      }
    }
    else{
      changeShooterRunning(false);
      shooterMotor.set(0);
    }
  }

  public void changeIntakeIn(boolean polarity){
    intakeIn = polarity;
  }
  public void changeIntakeOut(boolean polarity){
    intakeOut = polarity;
  }
  public void changeShooterRunning(boolean polarity){
    shooterRunning = polarity;
  }
}
