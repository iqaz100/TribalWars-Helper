package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FakesCounter {

    private Map<String, Integer> attackingVillagesCounters;
    private List<PlayerFakes> players;

    FakesCounter() {
        attackingVillagesCounters = new HashMap<>();
        players =  new ArrayList<>();
    }

    void readFile(String path) throws IOException {
        PlayerFakes player = null;
        List<String> rawData = null;
        String attackedVillage = "";

        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            rawData = stream
                    .filter(line -> {
                        return line.trim().startsWith("gracz:") || line.trim().startsWith("[b]Wioska:[/b]")
                                || line.trim().startsWith("[command]attack[/command]");
                    })
                    .collect(Collectors.toList());

        }

        for(String line : rawData){
            if(line.trim().startsWith("gracz")){
                player = new PlayerFakes(line.replaceFirst("gracz: ", ""));
                players.add(player);
            } else if(line.trim().startsWith("[b]Wioska:[/b]")){
                attackedVillage = line.replaceFirst("\\[b]Wioska:\\[/b] \\[coord]","").replaceFirst("\\[/coord]", "");
            } else if(line.trim().startsWith("[command]attack[/command]")){
                Pattern pattern = Pattern.compile("\\[coord](.*)\\[\\/coord].*\\[player](.*)\\[\\/player]", Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(line);
                while(matcher.find()) {
                    String attackingPlayer = matcher.group(2);
                    String attackingVillage = matcher.group(1);
                    //player.getAttacks().add(new Attack(attackedVillage, attackingPlayer, attackingVillage));
                    if(attackingVillagesCounters.containsKey(attackingVillage)){
                        attackingVillagesCounters.merge(attackingVillage, 1, Integer::sum);
                    } else {
                        attackingVillagesCounters.put(attackingVillage, 1);
                    }
                }
            }
        }
    }

    void writeTableToFile() throws IOException {
        Map<String, Integer> sortedMap = attackingVillagesCounters.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        StringBuilder sb = new StringBuilder();
//        sb.append("[table]").append(System.lineSeparator());
//        sb.append("[**]Wioska atakujÄ…ca[||]liczba atakĂłw[/**]").append(System.lineSeparator());
        sb.append("Wioska atakujÄ…ca\tliczba atakĂłw").append(System.lineSeparator());

        for(String village : sortedMap.keySet()){
            //sb.append("[*] ").append(village).append(" [|] ").append(sortedMap.get(village)).append(System.lineSeparator());
            sb.append(village).append("\t").append(sortedMap.get(village)).append(System.lineSeparator());
        }

        //sb.append("[/table]");

        Files.write(Paths.get("zliczoneFejki.txt"), sb.toString().getBytes());
    }
}
