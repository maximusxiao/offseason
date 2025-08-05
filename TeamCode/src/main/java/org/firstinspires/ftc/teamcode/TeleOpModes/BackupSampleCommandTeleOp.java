package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.conditionals.BlockingConditionalCommand;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.pedro.PedroOpMode;

import org.firstinspires.ftc.teamcode.Resources.ConfigConstants;
import org.firstinspires.ftc.teamcode.Subsystems.*;
import org.firstinspires.ftc.teamcode.Subsystems.ExtendoSubsystem;

@TeleOp(name = "COMMAND OpMode - No Color Detection", group = "COMMAND TELEOP")
public class BackupSampleCommandTeleOp extends PedroOpMode {
    public BackupSampleCommandTeleOp() {
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
        gamepadManager.getGamepad2().getLeftStick().setDisplacedCommand(
                (value) -> LiftSubsystem.INSTANCE.lift_power(value.component2())
        );

        // Extendo
        gamepadManager.getGamepad2().getRightStick().setDisplacedCommand(
                (value) -> ExtendoSubsystem.INSTANCE.extend_power(value.component2())
        );

        // Intake Process
        // Right Trigger
        // Checks if the Right Trigger is held, to set power
        gamepadManager.getGamepad2().getRightTrigger().setHeldCommand(
            value -> new ParallelGroup(
                IntakeSubsystem.INSTANCE.intake_power(value-gamepadManager.getGamepad2().getLeftTrigger().getValue()),
                IntakeArmSubsystem.INSTANCE.intake(),
                IntakePitchSubsystem.INSTANCE.intake()
            )
        );
        // Checks when Right Trigger is released, if left trigger val is also 0
        gamepadManager.getGamepad2().getRightTrigger().setReleasedCommand(
            value -> new BlockingConditionalCommand(
                () -> gamepadManager.getGamepad2().getLeftTrigger().getValue() == 0,
                // If it is also zero, then set the power of intake to 0, and retract the intake system
                () -> new ParallelGroup(
                    IntakeArmSubsystem.INSTANCE.transfer(),
                    IntakePitchSubsystem.INSTANCE.transfer()
                ),
                // If it isn't zero then just ignore the stuff, ideally the below code should be empty
                () -> new ParallelGroup(
                    IntakeArmSubsystem.INSTANCE.intake(),
                    IntakePitchSubsystem.INSTANCE.intake()
                )
            )
        );

        gamepadManager.getGamepad2().getRightTrigger().setHeldCommand(
            value -> new ParallelGroup(
                IntakeSubsystem.INSTANCE.intake_power(value-gamepadManager.getGamepad2().getLeftTrigger().getValue()),
                IntakeArmSubsystem.INSTANCE.intake(),
                IntakePitchSubsystem.INSTANCE.intake()
            )
        );
        gamepadManager.getGamepad2().getLeftTrigger().setReleasedCommand(
            value -> new BlockingConditionalCommand(
                () -> gamepadManager.getGamepad2().getRightTrigger().getValue() == 0,
                () -> new ParallelGroup(
                    IntakeArmSubsystem.INSTANCE.transfer(),
                    IntakePitchSubsystem.INSTANCE.transfer()
                ),
                () -> new ParallelGroup(
                    IntakeArmSubsystem.INSTANCE.intake(),
                    IntakePitchSubsystem.INSTANCE.intake()
                )
            )
        );

        gamepadManager.getGamepad2().getRightBumper().setPressedCommand(
                () -> new SequentialGroup(
                    new ParallelGroup(
                        ExtendoSubsystem.INSTANCE.retract(),
                        LiftSubsystem.INSTANCE.retract()
                    ),
                    new ParallelGroup(
                        ClawSubsystem.INSTANCE.open(),
                        WristSubsystem.INSTANCE.transfer(),
                        LiftArmSubsystem.INSTANCE.transfer()
                    )
                )
        );

        gamepadManager.getGamepad2().getLeftBumper().setPressedCommand(
                () -> new SequentialGroup(
                    new ParallelGroup(
                        LiftArmSubsystem.INSTANCE.deposit_sample(),
                        WristSubsystem.INSTANCE.deposit_sample()
                    ),
                    new Delay(0.25),
                    ClawSubsystem.INSTANCE.open()
                )
        );
    }
}

// Gamepad Mappings
/*
* Right Trigger activates the intake process
* Left Trigger activates the outtake process
* Normal robot-centric Mecanum drivetrain using Gamepad 1
* */