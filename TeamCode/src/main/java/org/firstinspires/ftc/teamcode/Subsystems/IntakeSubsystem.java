package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import org.firstinspires.ftc.teamcode.Resources.ConfigConstants;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class IntakeSubsystem extends Subsystem {

    // Standardized code
    public static final IntakeSubsystem INSTANCE = new IntakeSubsystem();
    public IntakeSubsystem() { }

    // Class Variables
    public Servo intake_servo;

    public static final double REJECT_POS = SubsystemConstants.INTAKE_REJECT;
    public static final double INTAKE_POS = SubsystemConstants.INTAKE;

    // Intake arm transfer method
    public Command intake_power(float power) {
        return new ServoToPosition(intake_servo,
                                   (power+1)/2,
                                   this);
    }

    @Override
    public void initialize() {
        intake_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, ConfigConstants.INTAKE_CONFIG);
        intake_servo.setPosition(0.5);
    }
}