package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToPosition;

import java.util.ArrayList;
import java.util.List;

public class StiltSubsystem extends Subsystem {
    // Standardized code
    public static final StiltSubsystem INSTANCE = new StiltSubsystem();
    public StiltSubsystem() { }

    // Class Variables
    public static Servo stilt_l_servo;
    public static Servo stilt_r_servo;
    public static String stilt_l_config = "servo_stilt_r";
    public static String stilt_r_config = "servo_stilt_l";

    public static List<Servo> stilt_servos = new ArrayList();


    public static final double STOW_POS = 0.17;
    public static final double ACTIVE_POS = 0.28;

    // Stilt stow method
    public Command stow() {
        return new MultipleServosToPosition(stilt_servos,
                                            STOW_POS,
                                            this);
    }

    // Stilt activate method
    public Command activate() {
        return new MultipleServosToPosition(stilt_servos,
                                            ACTIVE_POS,
                                            this);
    }

    @Override
    public void initialize() {
        stilt_l_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, stilt_l_config);
        stilt_r_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, stilt_r_config);
        stilt_r_servo.setDirection(Servo.Direction.REVERSE);
        stilt_l_servo.setPosition(STOW_POS);
        stilt_r_servo.setPosition(STOW_POS);
        stilt_servos.add(stilt_l_servo);
        stilt_servos.add(stilt_r_servo);
    }
}