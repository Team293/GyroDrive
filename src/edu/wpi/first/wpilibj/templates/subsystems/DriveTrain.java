package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 * Self explanatory code below. Controls the driveTrain using built in WPILib
 * class.
 */
public class DriveTrain {

    public static final Gyro gyro = new Gyro(RobotMap.gyro);
    public static final DigitalInput frontLimit = new DigitalInput(RobotMap.frontLimit);
    public static final Talon leftDrivePWM1 = new Talon(RobotMap.leftDrivePWM1),
            leftDrivePWM2 = new Talon(RobotMap.leftDrivePWM2),
            rightDrivePWM1 = new Talon(RobotMap.rightDrivePWM1),
            rightDrivePWM2 = new Talon(RobotMap.rightDrivePWM2);
    static final RobotDrive drive = new RobotDrive(leftDrivePWM1, leftDrivePWM2, rightDrivePWM1, rightDrivePWM2);

    /**
     * TankDrive is driving the robot like a... tank.
     */
    public static void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed * 0.8, rightSpeed * 0.8);
    }

    /**
     * ArcadeDrive is driving the robot similar to a RC car.
     */
    public static void arcadeDrive(double moveSpeed, double rotationalSpeed) {
        drive.arcadeDrive(moveSpeed, rotationalSpeed);
    }

    /**
     * Rotates the robot at a defined speed.
     */
    public static void rotateDrive(double rotationalSpeed) {
        drive.arcadeDrive(0, rotationalSpeed);
    }

    static double kP = 0;

    public static void driveStraight(long time) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < time) {
            //read the gyro
            double angle = gyro.getAngle();
            //calculate motor output
            kP = SmartDashboard.getNumber("kP", 4);
            double rightMotorOutput = 0.2 + kP * angle;
            double leftMotorOutput = 0.2 - kP * angle;
            if (rightMotorOutput > 1) {
                rightMotorOutput = 1;
            }
            if (leftMotorOutput > 1) {
                leftMotorOutput = 1;
            }
            if (rightMotorOutput < -1) {
                rightMotorOutput = - 1;
            }
            if (leftMotorOutput < -1) {
                leftMotorOutput = -1;
            }
            //set motor output
            tankDrive(leftMotorOutput, rightMotorOutput);
        }
    }

}
