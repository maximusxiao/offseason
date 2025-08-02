package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.pedro.PedroOpMode;

import org.firstinspires.ftc.teamcode.Resources.ConfigConstants;
import org.firstinspires.ftc.teamcode.Subsystems.*;
import org.firstinspires.ftc.teamcode.Subsystems.ExtendoSubsystem;

@TeleOp(name = "COMMAND OpMode - No Color Detection", group = "COMMAND TELEOP")
public class CommandTeleOp extends PedroOpMode {
    public CommandTeleOp() {
        super(ClawSubsystem.INSTANCE, ExtendoSubsystem.INSTANCE, IntakeArmSubsystem.INSTANCE, IntakePitchSubsystem.INSTANCE, IntakeSubsystem.INSTANCE, LiftArmSubsystem.INSTANCE, LiftSubsystem.INSTANCE, PTOSubsystem.INSTANCE, StiltSubsystem.INSTANCE, WristSubsystem.INSTANCE);
    }

    // Drive Motor Hardware Declaration
    public static MotorEx motor_fl;
    public static MotorEx motor_bl;
    public static MotorEx motor_fr;
    public static MotorEx motor_br;

    public static MotorEx[] drive_motors;

    public MecanumDriverControlled manual_drivetrain;

    @Override
    public void onInit() {
        motor_fl = new MotorEx(ConfigConstants.DRIVE_FL_CONFIG).reverse();
        motor_bl = new MotorEx(ConfigConstants.DRIVE_BL_CONFIG).reverse();
        motor_fr = new MotorEx(ConfigConstants.DRIVE_FR_CONFIG);
        motor_br = new MotorEx(ConfigConstants.DRIVE_BR_CONFIG);

        drive_motors = new MotorEx[]{motor_fl, motor_fr, motor_bl, motor_br};
    }

    @Override
    public void onStartButtonPressed() {
        manual_drivetrain = new MecanumDriverControlled(drive_motors, gamepadManager.getGamepad1());
        manual_drivetrain.invoke();

        // Lift
        gamepadManager.getGamepad2().getLeftStick().setDisplacedCommand((value) -> LiftSubsystem.INSTANCE.lift_power(value.component2()));

        // Extendo
        gamepadManager.getGamepad2().getRightStick().setDisplacedCommand((value) -> ExtendoSubsystem.INSTANCE.extend_power(value.component2()));


    }
}