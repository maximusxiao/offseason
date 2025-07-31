package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class IntakePitchSubsystem extends Subsystem {

    // Standardized code
    public static final IntakePitchSubsystem INSTANCE = new IntakePitchSubsystem();
    public IntakePitchSubsystem() { }

    // Class Variables
    public Servo intake_pitch_servo;
    public String intake_pitch_config = "servo_pitch";

    public static final double TRANSFER_POS = SubsystemConstants.PITCH_TRANSFER;

    // Intake angle transfer method
    public Command transfer() {
        return new ServoToPosition(intake_pitch_servo,
                                   TRANSFER_POS,
                                   this);
    }

    // Intake angle intake method
    public Command intake() {
        return new ServoToPosition(intake_pitch_servo,
                                   INTAKE_POS,
                                   this);
    }

    @Override
    public void initialize() {
        intake_pitch_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, intake_pitch_config);
        intake_pitch_servo.setDirection(Servo.Direction.FORWARD);
        intake_pitch_servo.setPosition(TRANSFER_POS);
    }
}