package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import org.firstinspires.ftc.teamcode.Resources.ConfigConstants;
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

    public static List<Servo> pto_servos = new ArrayList();


    public static final double ENGAGE_POS = SubsystemConstants.PTO_ENGAGE;
    public static final double DISENGAGE_POS = SubsystemConstants.PTO_DISENGAGE;

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
        pto_l_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, ConfigConstants.PTO_L_CONFIG);
        pto_r_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, ConfigConstants.PTO_R_CONFIG);
        pto_r_servo.setDirection(Servo.Direction.REVERSE);
        pto_l_servo.setPosition(ENGAGE_POS);
        pto_r_servo.setPosition(ENGAGE_POS);
        pto_servos.add(pto_l_servo);
        pto_servos.add(pto_r_servo);
    }
}