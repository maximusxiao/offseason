package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.pedro.PedroOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.extendo;
import org.firstinspires.ftc.teamcode.Subsystems.lift;

@TeleOp(name = "Command OpMode")
public class command_tele extends PedroOpMode {
    public command_tele() {
        super(lift.INSTANCE, extendo.INSTANCE);
    }

    // Hardware declaration
    public String flMotorConfig = "motor_f_l";
    public String frMotorConfig = "motor_f_r";
    public String blMotorConfig = "motor_b_l";
    public String brMotorConfig = "motor_b_r";

    public MotorEx fl_motor;
    public MotorEx fr_motor;
    public MotorEx bl_motor;
    public MotorEx br_motor;

    public MotorEx[] drive_motors;

    public MecanumDriverControlled manual_drive;

    @Override()
    public void onInit() {
        fl_motor = new MotorEx(flMotorConfig).reverse();
        fr_motor = new MotorEx(frMotorConfig);
        bl_motor = new MotorEx(blMotorConfig).reverse();
        br_motor = new MotorEx(brMotorConfig);

        drive_motors = new MotorEx[] {fl_motor, fr_motor, bl_motor, br_motor};
    }

    @Override()
    public void onStartButtonPressed() {
        manual_drive = new MecanumDriverControlled(drive_motors, gamepadManager.getGamepad1());
        manual_drive.invoke();
        gamepadManager.getGamepad2().getLeftStick().setDisplacedCommand((value) -> lift.INSTANCE.lift_power(value.component2()));
        gamepadManager.getGamepad2().getRightStick().setDisplacedCommand((value) -> extendo.INSTANCE.extension_power(value.component2()));
    }
}





