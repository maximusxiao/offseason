package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToPosition;

import java.util.ArrayList;
import java.util.List;

public class PTOSubsystem extends Subsystem {
    // Standardized code
    public static final PTOSubsystem INSTANCE = new PTOSubsystem();
    public PTOSubsystem() { }

    // Class Variables
    public static Servo pto_l_servo;
    public static Servo pto_r_servo;
    public static String pto_l_config = "servo_pto_l";
    public static String pto_r_config = "servo_pto_r";

    public static List<Servo> pto_servos = new ArrayList();


    public static final double ENGAGE_POS = 0.17;
    public static final double DISENGAGE_POS = 0.28;

    // PTO engage method
    public Command engage() {
        return new MultipleServosToPosition(pto_servos,
                                            ENGAGE_POS,
                                           this);
    }

    // PTO disengage method
    public Command disengage() {
        return new MultipleServosToPosition(pto_servos,
                                            DISENGAGE_POS,
                                            this);
    }

    @Override
    public void initialize() {
        pto_l_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, pto_l_config);
        pto_r_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, pto_r_config);
        pto_r_servo.setDirection(Servo.Direction.REVERSE);
        pto_l_servo.setPosition(ENGAGE_POS);
        pto_r_servo.setPosition(ENGAGE_POS);
        pto_servos.add(pto_l_servo);
        pto_servos.add(pto_r_servo);
    }
}