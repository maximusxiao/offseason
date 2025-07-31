package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class IntakeArmSubsystem extends Subsystem {

    // Standardized code
    public static final IntakeArmSubsystem INSTANCE = new IntakeArmSubsystem();
    public IntakeArmSubsystem() { }

    // Class Variables
    public Servo intake_arm_servo;
    public String intake_arm_config = "servo_intake_arm";

    public static final double TRANSFER_POS = SubsystemConstants.I_ARM_TRANSFER;
    public static final double INTAKE_POS = SubsystemConstants.I_ARM_INTAKE;

    // Intake arm transfer method
    public Command transfer() {
        return new ServoToPosition(intake_arm_servo,
                                   TRANSFER_POS,
                                   this);
    }

    // Intake arm intake method
    public Command intake() {
        return new ServoToPosition(intake_arm_servo,
                                   INTAKE_POS,
                                   this);
    }

    @Override
    public void initialize() {
        intake_arm_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, intake_arm_config);
        intake_arm_servo.setDirection(Servo.Direction.FORWARD);
        intake_arm_servo.setPosition(TRANSFER_POS);
    }
}