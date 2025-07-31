package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
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
    public String intake_config = "crservo_intake";

    public static final double REJECT_POS = 0.0;
    public static final double INTAKE_POS = 1.0;

    // Intake arm transfer method
    public Command intake_power(float power) {
        return new ServoToPosition(intake_servo,
                                   (power+1)/2,
                                   this);
    }

    @Override
    public void initialize() {
        intake_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, intake_config);
        intake_servo.setPosition(0.5);
    }
}