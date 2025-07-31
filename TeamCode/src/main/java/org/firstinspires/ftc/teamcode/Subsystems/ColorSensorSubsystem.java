package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.ftc.OpModeData;

public class ColorSensorSubsystem extends Subsystem {
    public static final ColorSensorSubsystem INSTANCE = new ColorSensorSubsystem();
    public ColorSensorSubsystem() {}

    public static ColorSensor color_sensor;
    public static final int common_color = 0;
    public static final int alliance_color = 0;

    public boolean check_sample() {
        int detected_color = color_sensor.argb();
        if (((detected_color > common_color-10) && (detected_color > common_color+10)) || ((detected_color > alliance_color-10) && (detected_color < alliance_color+10))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean check_specimen() {
        int detected_color = color_sensor.argb();
        if ((detected_color > alliance_color-10) && (detected_color < alliance_color+10)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize() {
        color_sensor = OpModeData.INSTANCE.getHardwareMap().get(ColorSensor.class, "sensor_color");
    }
}