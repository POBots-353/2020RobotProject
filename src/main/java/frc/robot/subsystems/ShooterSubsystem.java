/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */
  public ShooterSubsystem() {

  }
  public CANSparkMax shooterMotor=new CANSparkMax(Constants.shooterMotorDeviceID, MotorType.kBrushless);
  //public CANSparkMax shooterSensor=new CANSparkMax(Constants.shooterSensorNumber, MotorType.kBrushless);
  public Robot robot = new Robot();
  boolean manualShooter = RobotContainer.operatorStick.getRawButtonPressed(Constants.manualshooterButtonNumber);
  public DigitalInput shooterSensor = new DigitalInput(Constants.shooterLimitSwitch);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (manualShooter == true){
shooterMotor.set(Constants.shooterMotorSpeed);
  if (shooterSensor.get() == true){
  robot.changeShooterRunning(false);

}
else{
  robot.changeShooterRunning(true);
}
    }
    else{
    robot.changeShooterRunning(false);
    shooterMotor.set(0);
    }
  }
}
