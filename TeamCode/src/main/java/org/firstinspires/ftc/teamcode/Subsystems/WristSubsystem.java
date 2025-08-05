package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import org.firstinspires.ftc.teamcode.Resources.ConfigConstants;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class WristSubsystem extends Subsystem {

    // Standardized code
    public static final WristSubsystem INSTANCE = new WristSubsystem();
    public WristSubsystem() { }

    // Class Variables
    public Servo wrist_servo;

    public static final double SPECIMEN_INTAKE_POS = SubsystemConstants.WRIST_SPECIMEN_INTAKE;
    public static final double SPECIMEN_DEPOSIT_POS = SubsystemConstants.WRIST_SPECIMEN_DEPOSIT;
    public static final double TRANSFER_POS = SubsystemConstants.WRIST_TRANSFER;
    public static final double SAMPLe_DEPOSIT_POS = SubsystemConstants.WRIST_SAMPLE_DEPOSIT;

    // Specimen intake method
    public Command intake_specimen() {
        return new ServoToPosition(wrist_servo,
                                   SPECIMEN_INTAKE_POS,
                                   this);
    }

    // Specimen deposit method
    public Command deposit_specimen() {
        return new ServoToPosition(wrist_servo,
                                   SPECIMEN_DEPOSIT_POS,
                                   this);
    }

    // Specimen deposit method
    public Command transfer() {
        return new ServoToPosition(wrist_servo,
                                   TRANSFER_POS,
                                   this);
    }

    // Sample deposit method
    public Command deposit_sample() {
        return new ServoToPosition(wrist_servo,
                                   SAMPLE_DEPOSIT_POS,
                                   this);
    }

    @Override
    public void initialize() {
        wrist_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, ConfigConstants.WRIST_CONFIG);
        wrist_servo.setDirection(Servo.Direction.FORWARD);
        wrist_servo.setPosition(TRANSFER_POS);
    }
}