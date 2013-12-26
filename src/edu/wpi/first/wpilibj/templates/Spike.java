package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.templates.subsystems.OI;

/**
 * It is important to read the comments here. Some things only work if
 * everything is set up correctly. They will need to be disabled or there might
 * be PWM timeout errors.
 */
public class Spike extends IterativeRobot {

    private static final DriverStationLCD LCD = DriverStationLCD.getInstance();
    public static boolean initEmergencyConstantValue = true;

    /**
     * Code here runs once when the robot starts. While loops should only be
     * used here.
     */
    public void robotInit() {
        SmartDashboard.putNumber("kP", 2);
    }

    /**
     * Code here loops every 20 milliseconds during the autonomous period. While
     * loops should not be used.
     */
    public void autonomousPeriodic() {
        SmartDashboard.putNumber("Gyro", DriveTrain.gyro.getAngle());
        DriveTrain.driveStraight(10000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Code here loops every 20 milliseconds during the autonomous period. While
     * loops should not be used. Everything is nice and tidy compared to last
     * year... Pieces of the robot are written in separate classes for ease of
     * reading and troubleshooting.
     */
    public void teleopPeriodic() {
        OI.controlDrive();
        OI.controlGyro();
    }

    /**
     * SmartDashboard is used to send diagnostic information back to the
     * DriverStation here.
     */
    private static void runSmartDashboard() {
    }
}
