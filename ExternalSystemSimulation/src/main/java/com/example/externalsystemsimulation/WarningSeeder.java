package com.example.externalsystemsimulation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarningSeeder implements CommandLineRunner {

    private final WarningRepository warningRepository;

    public WarningSeeder(WarningRepository warningRepository) {
        this.warningRepository = warningRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Warning> warnings = List.of(
                new Warning("Severe thunderstorm warning", "USA"),
                new Warning("Flood warning", "CAN"),
                new Warning("Heatwave advisory", "AUS"),
                new Warning("Earthquake alert", "JPN"),
                new Warning("Hurricane warning", "MEX"),
                new Warning("Heavy snowfall warning", "NOR"),
                new Warning("Tsunami alert", "IDN"),
                new Warning("Wildfire advisory", "BRA"),
                new Warning("Drought advisory", "ZAF"),
                new Warning("High winds warning", "GBR"),
                new Warning("Avalanche alert", "CHE"),
                new Warning("Fog advisory", "DEU"),
                new Warning("Volcanic eruption warning", "ISL"),
                new Warning("Severe rain warning", "IND"),
                new Warning("Tornado warning", "ARG"),
                new Warning("Landslide alert", "CHN"),
                new Warning("Freezing rain advisory", "SWE"),
                new Warning("Dust storm warning", "SAU"),
                new Warning("Storm surge warning", "PHL"),
                new Warning("Cyclone warning", "NZL")
        );

        warningRepository.saveAll(warnings);
    }
}