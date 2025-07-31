package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class ClawSubsystem extends Subsystem {

    // Standardized code
    public static final ClawSubsystem INSTANCE = new ClawSubsystem();
    public ClawSubsystem() { }

    // Class Variables
    public Servo claw_servo;
    public String claw_config = "servo_claw";

    public static final double OPEN_POS = SubsystemConstants.CLAW_OPEN;
    public static final double CLOSE_POS = SubsystemConstants.CLAW_CLOSE;

    // Open claw method
    public Command open() {
        return new ServoToPosition(claw_servo,
                                   OPEN_POS,
                                   this);
    }

    // Close claw method
    public Command close() {
        return new ServoToPosition(claw_servo,
                                   CLOSE_POS,
                                   this);
    }

    @Override
    public void initialize() {
        claw_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, claw_config);
        claw_servo.setDirection(Servo.Direction.REVERSE);
        claw_servo.setPosition(OPEN_POS);
    }
}