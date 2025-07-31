package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToPosition;

import java.util.ArrayList;
import java.util.List;

public class LiftArmSubsystem extends Subsystem {
    // Standardized code
    public static final LiftArmSubsystem INSTANCE = new LiftArmSubsystem();
    public LiftArmSubsystem() { }

    // Class Variables
    public static Servo lift_arm_l_servo;
    public static Servo lift_arm_r_servo;
    public static String lift_arm_l_config = "servo_arm_l";
    public static String lift_arm_r_config = "servo_arm_r";

    public static List<Servo> lift_arm_servos = new ArrayList();

    public static final double SPECIMEN_INTAKE_POS = SubsystemConstants.LIFT_ARM_SPECIMEN_INTAKE;
    public static final double SPECIMEN_DEPOSIT_POS = SubsystemConstants.LIFT_ARM_SPECIMEN_DEPOSIT;
    public static final double TRANSFER_POS = SubsystemConstants.LIFT_ARM_TRANSFER;
    public static final double SAMPLE_DEPOSIT_POS = SubsystemConstants.LIFT_ARM_SAMPLE_DEPOSIT;

    // Specimen intake method
    public Command intake_specimen() {
        return new MultipleServosToPosition(lift_arm_servos,
                                            SPECIMEN_INTAKE_POS,
                                            this);
    }

    // Specimen deposit method
    public Command deposit_specimen() {
        return new MultipleServosToPosition(lift_arm_servos,
                                            SPECIMEN_DEPOSIT_POS,
                                            this);
    }

    // Transfer sample method
    public Command transfer() {
        return new MultipleServosToPosition(lift_arm_servos,
                                            TRANSFER_POS,
                                            this);
    }
    // Sample deposit method
    public Command deposit_sample() {
        return new MultipleServosToPosition(lift_arm_servos,
                                            SAMPLE_DEPOSIT_POS,
                                            this);
    }

    @Override
    public void initialize() {
        lift_arm_l_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, lift_arm_l_config);
        lift_arm_r_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, lift_arm_r_config);
        lift_arm_r_servo.setDirection(Servo.Direction.REVERSE);
        lift_arm_l_servo.setPosition(TRANSFER_POS);
        lift_arm_r_servo.setPosition(TRANSFER_POS);
        lift_arm_servos.add(lift_arm_l_servo);
        lift_arm_servos.add(lift_arm_r_servo);
    }
}